package com.thesis.wallet.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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


    @Column(name = "currency")
    private String currency;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "wallet")
    private Set<Expanse> expanses;

}
