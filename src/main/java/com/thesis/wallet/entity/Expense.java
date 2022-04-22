package com.thesis.wallet.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "transaction")
@Getter
@Setter
public class Expense {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "amount")
    private Integer amount;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "date")
    private Date date;

    @Column(name = "comments")
    private String comments;

    @Column(name = "location")
    private String location;

    @Column(name = "type")
    private String type;

    @Column(name = "username")
    private String username;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @ManyToOne
    @JoinColumn(name = "wallet_id", nullable = false)
    private Wallet wallet;

    public Expense(Long id, String name, Integer amount, Date date, String comments, String location, String type, String username, Category category, Wallet wallet) {
        this.id = id;
        this.name = name;
        this.amount = amount;
        this.date = date;
        this.comments = comments;
        this.location = location;
        this.type = type;
        this.username = username;
        this.category = category;
        this.wallet = wallet;
    }

    public Expense(String name, Integer amount, Date date, String comments, String location, String type, String username, Category category, Wallet wallet) {
        this.name = name;
        this.amount = amount;
        this.date = date;
        this.comments = comments;
        this.location = location;
        this.type = type;
        this.username = username;
        this.category = category;
        this.wallet = wallet;
    }

    public Expense() {

    }


}
