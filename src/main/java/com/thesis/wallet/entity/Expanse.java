package com.thesis.wallet.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "expanse")
@Getter
@Setter
public class Expanse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "amount")
    private Integer amount;

    @Column(name = "photo_url")
    private String photoUrl;

    @Column(name = "date")
    private Date date;

    @Column(name = "comments")
    private String comments;

    @Column(name = "location")
    private String location;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private ExpanseCategory category;

    @ManyToOne
    @JoinColumn(name = "wallet_id", nullable = false)
    private Wallet wallet;



}
