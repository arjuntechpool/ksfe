package com.techpool.ksfe.repository;

import com.techpool.ksfe.entity.TransRequestChild;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransRequestChildRepository extends JpaRepository<TransRequestChild, Long> {

    @Query(value = """
        SELECT DISTINCT
            s.uid AS employee_code,
            s.firstName AS employee_name,
            c.int_office_id AS preferred_office,
            MIN(c.int_sl) AS preference_order,
            s.wg AS priority_value
        FROM
            tbl_seniority_list_final s
        JOIN
            tbl_trans_requests r ON s.uid = r.vch_emp_code
        JOIN
            tbl_trans_request_child c ON r.int_req_id = c.int_req_id
        LEFT JOIN
            tbl_allocations a ON s.uid = a.vch_emp_code AND a.int_to_office = :officeId
        LEFT JOIN
            tbl_to_allot ta ON s.uid = ta.vch_emp_code AND ta.int_office_id = :officeId
        JOIN
            tbl_vacancy v ON c.int_office_id = v.officeId
        WHERE
            c.int_office_id = :officeId
            AND (a.id IS NULL OR a.int_to_office != :officeId OR c.int_sl > 1) -- Include lower priority options
            AND ta.int_id IS NULL -- Exclude already allotted employees
            AND v.vMgr > 0 -- Check for Manager vacancies
        GROUP BY
            s.uid, s.firstName, c.int_office_id, s.wg
        ORDER BY
            s.wg DESC, -- Sort by priority value (higher wg first)
            preference_order ASC -- Sort by preference order (1st option first)
        """, nativeQuery = true)
    List<Object[]> findQueueListByOfficeId(@Param("officeId") Integer officeId);
}