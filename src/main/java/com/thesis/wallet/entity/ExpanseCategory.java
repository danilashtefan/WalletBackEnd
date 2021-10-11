package com.thesis.wallet.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "expanse_category")
@Getter
@Setter
public class ExpanseCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String expanseCategoryName;

    @Column(name = "type")
    private String type;

    @Column(name = "icon")
    private String icon;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "category")
    private Set<Expanse> expanses;

}
