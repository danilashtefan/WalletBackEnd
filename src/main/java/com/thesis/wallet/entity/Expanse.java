package com.thesis.wallet.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

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
    private ExpanseCategory category;

    @ManyToOne
    @JoinColumn(name = "wallet_id", nullable = false)
    private Wallet wallet;

    public Expanse(Long id, String name, Integer amount, String photoUrl, Date date, String comments, String location, String type, String username, ExpanseCategory category, Wallet wallet) {
        this.id = id;
        this.name = name;
        this.amount = amount;
        this.photoUrl = photoUrl;
        this.date = date;
        this.comments = comments;
        this.location = location;
        this.type = type;
        this.username = username;
        this.category = category;
        this.wallet = wallet;
    }

    public Expanse() {

    }


//    public Expanse(Long id, String name, Integer amount, String photoUrl, Date date, String comments, String location, String type, String username, String category_id, String wallet_id) {
//        this.id = id;
//        this.name = name;
//        this.amount = amount;
//        this.photoUrl = photoUrl;
//        this.date = date;
//        this.comments = comments;
//        this.location = location;
//        this.type = type;
//        this.username = username;
//        this.category = category;
//        this.wallet = wallet;
//    }
}
