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
            s.wg AS priority_value,
            CASE 
                WHEN v.vMgr > 0 THEN 'Vacancy Available'
                ELSE 'No Vacancy'
            END AS vacancy_status
        FROM
            tbl_seniority_list_final s
        JOIN
            tbl_trans_requests r ON s.uid = r.vch_emp_code
        JOIN
            tbl_trans_request_child c ON r.int_req_id = c.int_req_id
        LEFT JOIN
            tbl_allocations a ON s.uid = a.vch_emp_code AND a.int_to_office = c.int_office_id
        LEFT JOIN
            tbl_to_allot ta ON s.uid = ta.vch_emp_code AND ta.int_office_id = c.int_office_id
        LEFT JOIN
            tbl_vacancy v ON c.int_office_id = v.officeId
        WHERE
            c.int_office_id = :officeId
            AND (a.id IS NULL OR a.int_to_office != c.int_office_id OR c.int_sl > 1) -- Include lower priority options
            AND ta.int_id IS NULL -- Exclude already allotted employees
        GROUP BY
            s.uid, s.firstName, c.int_office_id, s.wg, v.vMgr
        ORDER BY
            s.wg DESC, -- Sort by priority value (higher wg first)
            preference_order ASC -- Sort by preference order (1st option first)
        """, nativeQuery = true)
    List<Object[]> findQueueListByOfficeId(@Param("officeId") Integer officeId);
}

// @Query(value = """
//     SELECT DISTINCT
//         s.uid AS employee_code,
//         s.firstName AS employee_name,
//         c.int_office_id AS preferred_office,
//         MIN(c.int_sl) AS preference_order,
//         s.wg AS priority_value,
//         p.date_of_joining AS date_of_joining, -- Date of joining in cadre position
//         CASE 
//             WHEN v.vMgr > 0 THEN 'Vacancy Available'
//             ELSE 'No Vacancy'
//         END AS vacancy_status
//     FROM
//         tbl_seniority_list_final s
//     JOIN
//         tbl_trans_requests r ON s.uid = r.vch_emp_code
//     JOIN
//         tbl_trans_request_child c ON r.int_req_id = c.int_req_id
//     LEFT JOIN
//         tbl_allocations a ON s.uid = a.vch_emp_code AND a.int_to_office = c.int_office_id
//     LEFT JOIN
//         tbl_to_allot ta ON s.uid = ta.vch_emp_code AND ta.int_office_id = c.int_office_id
//     LEFT JOIN
//         tbl_vacancy v ON c.int_office_id = v.officeId
//     LEFT JOIN
//         tbl_profile p ON s.uid = p.uid -- Join with profile table for date of joining
//     WHERE
//         c.int_office_id = :officeId
//         AND (a.id IS NULL OR a.int_to_office != c.int_office_id OR c.int_sl > 1) -- Include lower priority options
//         AND ta.int_id IS NULL -- Exclude already allotted employees
//     GROUP BY
//         s.uid, s.firstName, c.int_office_id, s.wg, v.vMgr, p.date_of_joining
//     ORDER BY
//         s.wg DESC, -- Sort by priority value (higher wg first)
//         preference_order ASC, -- Sort by preference order (1st option first)
//         p.date_of_joining ASC, -- Sort by date of joining (earlier first)
//         s.uid ASC -- Sort by UID (ascending order)
//     """, nativeQuery = true)
// List<Object[]> findQueueListByOfficeId(@Param("officeId") Integer officeId);

// SELECT DISTINCT
//     s.uid AS employee_code,
//     s.firstName AS employee_name,
//     c.int_office_id AS preferred_office,
//     MIN(c.int_sl) AS preference_order,
//     s.wg AS priority_value,
//     p.date_of_joining AS date_of_joining, -- Date of joining in cadre position
//     CASE 
//         WHEN v.vMgr > 0 THEN 'Vacancy Available'
//         ELSE 'No Vacancy'
//     END AS vacancy_status
// FROM
//     tbl_seniority_list_final s
// JOIN
//     tbl_trans_requests r ON s.uid = r.vch_emp_code
// JOIN
//     tbl_trans_request_child c ON r.int_req_id = c.int_req_id
// LEFT JOIN
//     tbl_allocations a ON s.uid = a.vch_emp_code AND a.int_to_office = c.int_office_id
// LEFT JOIN
//     tbl_to_allot ta ON s.uid = ta.vch_emp_code AND ta.int_office_id = c.int_office_id
// LEFT JOIN
//     tbl_vacancy v ON c.int_office_id = v.officeId
// LEFT JOIN
//     tbl_profile p ON s.uid = p.uid -- Join with profile table for date of joining
// WHERE
//     c.int_office_id = :officeId
//     AND (a.id IS NULL OR a.int_to_office != c.int_office_id OR c.int_sl > 1) -- Include lower priority options
//     AND ta.int_id IS NULL -- Exclude already allotted employees
// GROUP BY
//     s.uid, s.firstName, c.int_office_id, s.wg, v.vMgr, p.date_of_joining
// ORDER BY
//     s.wg DESC, -- Sort by priority value (higher wg first)
//     preference_order ASC, -- Sort by preference order (1st option first)
//     p.date_of_joining ASC, -- Sort by date of joining (earlier first)
//     s.uid ASC; -- Sort by UID (ascending order)