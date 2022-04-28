package com.thesis.wallet.tests;


import com.thesis.wallet.DAO.ExpanseRepository;
import com.thesis.wallet.entity.Category;
import com.thesis.wallet.entity.Expense;
import com.thesis.wallet.entity.Wallet;
import com.thesis.wallet.service.ExpenseService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

@RunWith(MockitoJUnitRunner.class)
public class ExpenseServiceTests {

    @Mock
    ExpanseRepository expenseRepo;
    @Mock
    Category category;
    @Mock
    Wallet wallet;

    ExpenseService expenseService;

    @Before
    public void setUp() {
        expenseService = new ExpenseService(expenseRepo);
    }

    @Test
    public void getTopExpenseTest() throws ParseException {
        ArrayList listOfExpenses = new ArrayList();

        Expense ex1 = new Expense("", 1, new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
                .parse("2022-11-03"), "", "", "Expense", "", category, wallet);
        Expense ex2 = new Expense("", 30, new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
                .parse("2022-11-05"), "", "", "Expense", "", category, wallet);
        Expense ex3 = new Expense("", 999, new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
                .parse("2022-11-10"), "", "", "Expense", "", category, wallet);


        listOfExpenses.add(ex1);
        listOfExpenses.add(ex2);
        listOfExpenses.add(ex3);

        Mockito.when(expenseRepo.findAllUserExpenses(Mockito.any())).thenReturn(listOfExpenses);
        Assert.assertEquals(ex3, expenseService.getTopTransaction("", "2022-11-02", "2022-11-11", "Expense"));
        Assert.assertEquals(ex2, expenseService.getTopTransaction("", "2022-11-02", "2022-11-06", "Expense"));
        Assert.assertEquals(ex1, expenseService.getTopTransaction("", "2022-11-02", "2022-11-04", "Expense"));


    }

    @Test
    public void getTopIncomeTest() throws ParseException {
        ArrayList listOfIncomes = new ArrayList();

        Expense in1 = new Expense("", 1, new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
                .parse("2022-11-03"), "", "", "Income", "", category, wallet);
        Expense in2 = new Expense("", 30, new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
                .parse("2022-11-05"), "", "", "Income", "", category, wallet);
        Expense in3 = new Expense("", 999, new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
                .parse("2022-11-10"), "", "", "Income", "", category, wallet);


        listOfIncomes.add(in1);
        listOfIncomes.add(in2);
        listOfIncomes.add(in3);

        Mockito.when(expenseRepo.findAllUserExpenses(Mockito.any())).thenReturn(listOfIncomes);

        Assert.assertEquals(in3, expenseService.getTopTransaction("", "2022-11-02", "2022-11-11", "Income"));
        Assert.assertEquals(in2, expenseService.getTopTransaction("", "2022-11-02", "2022-11-06", "Income"));
        Assert.assertEquals(in1, expenseService.getTopTransaction("", "2022-11-02", "2022-11-04", "Income"));

    }

}
