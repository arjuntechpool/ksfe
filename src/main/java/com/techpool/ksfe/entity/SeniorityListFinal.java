package com.techpool.ksfe.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "tbl_seniority_list_final")
public class SeniorityListFinal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "int_id")
    private Long id;

    @Column(name = "uid")
    private String uid;

    @Column(name = "firstName")
    private String firstName;

    @Column(name = "wg")
    private Integer wg;
}