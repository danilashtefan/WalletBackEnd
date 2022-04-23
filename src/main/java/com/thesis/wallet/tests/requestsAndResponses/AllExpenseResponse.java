package com.thesis.wallet.tests.requestsAndResponses;

import com.thesis.wallet.entity.Expense;
import lombok.Data;

import java.util.ArrayList;

@Data
public class AllExpenseResponse {
    private ArrayList<Expense> expenses;
}
