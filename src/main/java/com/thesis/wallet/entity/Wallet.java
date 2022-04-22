package com.thesis.wallet.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "wallet")
@Getter
@Setter
public class Wallet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "wallet_name")
    private String walletName;


    @Column(name = "type")
    private String currency;

    @Column(name = "username")
    private String username;

    @Column(name = "icon")
    private String icon;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "wallet")
    private Set<Expense> expenses;

}
