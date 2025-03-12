package com.techpool.ksfe.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "tbl_allocations")
public class Allocation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "vch_emp_code")
    private String empCode;

    @Column(name = "int_from_office")
    private Integer fromOffice;

    @Column(name = "int_to_office")
    private Integer toOffice;

    @Column(name = "int_req_id")
    private Long reqId;

    @Column(name = "vch_replaced_by")
    private String replacedBy;

    @Column(name = "tny_option")
    private Integer option;
}
