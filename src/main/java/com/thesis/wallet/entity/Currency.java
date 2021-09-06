package com.thesis.wallet.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "currency")
@Getter
@Setter
public class Currency {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "currency_name")
    private String currencyName;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "currency")
    private Set<Wallet> wallets;
}
