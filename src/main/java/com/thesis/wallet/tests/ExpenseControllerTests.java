package com.thesis.wallet.tests;

import com.thesis.wallet.controllers.ExpenseController;
import com.thesis.wallet.entity.Expense;
import com.thesis.wallet.service.ExpenseCategoriesService;
import com.thesis.wallet.service.ExpenseService;
import com.thesis.wallet.service.WalletService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.core.Authentication;

import java.util.ArrayList;

@RunWith(MockitoJUnitRunner.class)
public class ExpenseControllerTests {

    @Mock
    private ExpenseService expenseService;

    @Mock
    private ExpenseCategoriesService expenseCategoriesService;

    @Mock
    private WalletService walletService;

    @Mock
    Authentication authentication;

    private ExpenseController expenseController;

    @Before
    public void setUp(){
        expenseController = new ExpenseController(expenseService,expenseCategoriesService,walletService);
    }


    @Test
    public void getAllExpensesTest(){
        ArrayList response = new ArrayList();
        response.add(new Expense());
        response.add(new Expense());
        System.out.println(response.size());
        Mockito.when(expenseService.getAllExpenses(Mockito.any())).thenReturn(response);
        Assert.assertEquals(2,expenseController.getAllExpenses(authentication).getBody().size());
    }
}
