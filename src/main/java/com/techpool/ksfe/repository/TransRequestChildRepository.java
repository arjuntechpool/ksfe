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
            senior.uid AS employee_code,
            senior.firstName AS employee_name,
            req_child.int_office_id AS preferred_office,
            req_child.int_sl AS preference_order,
            senior.wg AS priority_value
        FROM
            tbl_seniority_list_final senior
        JOIN
            tbl_trans_requests request ON senior.uid = request.vch_emp_code
        JOIN
            tbl_trans_request_child req_child ON request.int_req_id = req_child.int_req_id
        LEFT JOIN
            tbl_allocations alloc ON senior.uid = alloc.vch_emp_code
        LEFT JOIN
            tbl_trans_request_child child ON request.int_req_id = child.int_req_id
                                            AND alloc.int_to_office = child.int_office_id
        WHERE
            req_child.int_office_id = :officeId -- Office we are looking for
            AND (
                alloc.id IS NULL -- Employee not allocated to any office
                OR (
                    alloc.int_to_office != :officeId -- Employee not allocated to the office we are looking for
                    AND req_child.int_sl < child.int_sl -- Check preference order
                )
            )
        ORDER BY
            senior.wg DESC, -- Sort by priority value (higher wg first)
            req_child.int_sl ASC, -- Sort by preference order (1st option first)
            senior.uid ASC -- Sort by employee code (UID) in ascending order
        """, nativeQuery = true)
    List<Object[]> findQueueListByOfficeId(@Param("officeId") Integer officeId);
}