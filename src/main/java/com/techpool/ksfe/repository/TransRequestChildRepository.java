package com.techpool.ksfe.repository;

import com.techpool.ksfe.entity.TransRequestChild;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransRequestChildRepository extends JpaRepository<TransRequestChild, Long> {

    @Query("SELECT c FROM TransRequestChild c " +
           "JOIN SeniorityListFinal s ON s.uid = c.reqId " +
           "LEFT JOIN Allocation a ON s.uid = a.empCode AND a.toOffice = :officeId " +
           "WHERE c.officeId = :officeId AND (a.id IS NULL OR a.toOffice != :officeId) " +
           "ORDER BY s.wg DESC")
    List<TransRequestChild> findQueueListByOfficeId(@Param("officeId") Integer officeId);
}