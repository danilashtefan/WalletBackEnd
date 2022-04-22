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
    public void getTopTransactionTest() throws ParseException {
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
        Assert.assertEquals(expenseService.getTopTransaction("", "2022-11-02", "2022-11-11", "Expense"), ex3);
        Assert.assertEquals(expenseService.getTopTransaction("", "2022-11-02", "2022-11-06", "Expense"), ex2);
        Assert.assertEquals(expenseService.getTopTransaction("", "2022-11-02", "2022-11-04", "Expense"), ex1);
    }
}
