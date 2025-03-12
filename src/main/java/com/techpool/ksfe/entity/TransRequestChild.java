package com.techpool.ksfe.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "tbl_trans_request_child")
public class TransRequestChild {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "int_child_id")
    private Long childId;

    @Column(name = "int_req_id")
    private Long reqId;

    @Column(name = "int_office_id")
    private Integer officeId;

    @Column(name = "int_sl")
    private Integer preferenceOrder;
}
