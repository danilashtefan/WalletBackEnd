package com.thesis.wallet.entity.requests;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter
@Setter
public class ExpenseAddOrEditRequest {

    private Long id;
    private String name;
    private Integer amount;
    private String photoUrl;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date date;

    private String comments;
    private String location;
    private String type;
    private String username;
    private String category;
    private String wallet;
}
