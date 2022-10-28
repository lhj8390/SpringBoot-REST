package com.lhj8390.dashboard.model.entity;

import com.lhj8390.dashboard.model.RoleType;
import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private RoleType name;
}
