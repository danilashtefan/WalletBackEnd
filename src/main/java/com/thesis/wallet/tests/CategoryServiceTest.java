package com.thesis.wallet.tests;

import com.thesis.wallet.DAO.ExpanseCategoryRepository;
import com.thesis.wallet.entity.Category;
import com.thesis.wallet.entity.Expense;
import com.thesis.wallet.entity.Wallet;
import com.thesis.wallet.service.ExpenseCategoriesService;
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
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

@RunWith(MockitoJUnitRunner.class)
public class CategoryServiceTest {

    @Mock
    ExpanseCategoryRepository expenseCategoriesRepo;
    @Mock
    Category category1;
    @Mock
    Category category2;
    @Mock
    Category category3;
    @Mock
    Wallet wallet;

    ExpenseCategoriesService expenseCategoriesService;
    @Before
    public void setUp() {
        expenseCategoriesService = new ExpenseCategoriesService(expenseCategoriesRepo);
    }

    @Test
    public void getTopExpenseCategory() throws ParseException {
        Expense ex1 = new Expense("", 1, new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
                .parse("2022-11-03"), "", "", "Expense", "", category1, wallet);
        Expense ex2 = new Expense("", 2, new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
                .parse("2022-11-03"), "", "", "Expense", "", category1, wallet);
        Expense ex3 = new Expense("", 30, new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
                .parse("2022-11-05"), "", "", "Expense", "", category2, wallet);
        Expense ex4 = new Expense("", 31, new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
                .parse("2022-11-05"), "", "", "Expense", "", category2, wallet);
        Expense ex5 = new Expense("", 999, new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
                .parse("2022-11-10"), "", "", "Expense", "", category3, wallet);
        Expense ex6 = new Expense("", 1000, new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
                .parse("2022-11-10"), "", "", "Expense", "", category3, wallet);

        Expense in7 = new Expense("", 1, new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
                .parse("2022-11-03"), "", "", "Income", "", category1, wallet);
        Expense in8 = new Expense("", 2, new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
                .parse("2022-11-03"), "", "", "Income", "", category1, wallet);
        Expense in9 = new Expense("", 30, new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
                .parse("2022-11-05"), "", "", "Income", "", category2, wallet);
        Expense in10 = new Expense("", 31, new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
                .parse("2022-11-05"), "", "", "Income", "", category2, wallet);
        Expense in11 = new Expense("", 999, new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
                .parse("2022-11-10"), "", "", "Income", "", category3, wallet);
        Expense in12 = new Expense("", 1000, new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
                .parse("2022-11-10"), "", "", "Income", "", category3, wallet);

        Set setTransactions1 = new HashSet();
        setTransactions1.add(ex1);
        setTransactions1.add(ex2);
        setTransactions1.add(in11);
        setTransactions1.add(in12);

        Set setTransactions2 = new HashSet();
        setTransactions2.add(ex3);
        setTransactions2.add(ex4);
        setTransactions2.add(in7);
        setTransactions2.add(in8);

        Set setTransactions3 = new HashSet();
        setTransactions3.add(ex5);
        setTransactions3.add(ex6);
        setTransactions3.add(in9);
        setTransactions3.add(in10);

        ArrayList<Category> listOfCategories = new ArrayList<>();

        listOfCategories.add(category1);
        listOfCategories.add(category2);
        listOfCategories.add(category3);

        Mockito.when(expenseCategoriesRepo.findAllUserExpenseCategories(Mockito.any())).thenReturn(listOfCategories);
        Mockito.when(category1.getExpenses()).thenReturn(setTransactions1);
        Mockito.when(category2.getExpenses()).thenReturn(setTransactions2);
        Mockito.when(category3.getExpenses()).thenReturn(setTransactions3);

        Assert.assertEquals(category3, expenseCategoriesService.getTopExpenseCategory("","2022-11-02", "2022-11-11").getCategory());
        Assert.assertEquals(category3, expenseCategoriesService.getTopExpenseCategory("","2022-11-02", "2022-11-11").getCategory());
        Assert.assertEquals(category2, expenseCategoriesService.getTopExpenseCategory("","2022-11-02", "2022-11-06").getCategory());
        Assert.assertEquals(category1, expenseCategoriesService.getTopExpenseCategory("","2022-11-02", "2022-11-04").getCategory());

    }

    @Test
    public void getTopIncomeCategory() throws ParseException {
        Expense in1 = new Expense("", 1, new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
                .parse("2022-11-03"), "", "", "Income", "", category1, wallet);
        Expense in2 = new Expense("", 2, new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
                .parse("2022-11-03"), "", "", "Income", "", category1, wallet);
        Expense in3 = new Expense("", 30, new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
                .parse("2022-11-05"), "", "", "Income", "", category2, wallet);
        Expense in4 = new Expense("", 31, new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
                .parse("2022-11-05"), "", "", "Income", "", category2, wallet);
        Expense in5 = new Expense("", 999, new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
                .parse("2022-11-10"), "", "", "Income", "", category3, wallet);
        Expense in6 = new Expense("", 1000, new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
                .parse("2022-11-10"), "", "", "Income", "", category3, wallet);

        Expense ex7 = new Expense("", 1, new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
                .parse("2022-11-03"), "", "", "Expense", "", category1, wallet);
        Expense ex8 = new Expense("", 2, new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
                .parse("2022-11-03"), "", "", "Expense", "", category1, wallet);
        Expense ex9 = new Expense("", 30, new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
                .parse("2022-11-05"), "", "", "Expense", "", category2, wallet);
        Expense ex10 = new Expense("", 31, new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
                .parse("2022-11-05"), "", "", "Expense", "", category2, wallet);
        Expense ex11 = new Expense("", 999, new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
                .parse("2022-11-10"), "", "", "Expense", "", category3, wallet);
        Expense ex12 = new Expense("", 1000, new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
                .parse("2022-11-10"), "", "", "Expense", "", category3, wallet);

        Set setTransactions1 = new HashSet();
        setTransactions1.add(in1);
        setTransactions1.add(in2);
        setTransactions1.add(ex11);
        setTransactions1.add(ex12);


        Set setTransactions2 = new HashSet();
        setTransactions2.add(in3);
        setTransactions2.add(in4);
        setTransactions2.add(ex7);
        setTransactions2.add(ex8);

        Set setTransactions3 = new HashSet();
        setTransactions3.add(in5);
        setTransactions3.add(in6);
        setTransactions3.add(ex9);
        setTransactions3.add(ex10);

        ArrayList<Category> listOfCategories = new ArrayList<>();

        listOfCategories.add(category1);
        listOfCategories.add(category2);
        listOfCategories.add(category3);

        Mockito.when(expenseCategoriesRepo.findAllUserExpenseCategories(Mockito.any())).thenReturn(listOfCategories);
        Mockito.when(category1.getExpenses()).thenReturn(setTransactions1);
        Mockito.when(category2.getExpenses()).thenReturn(setTransactions2);
        Mockito.when(category3.getExpenses()).thenReturn(setTransactions3);

        Assert.assertEquals(category3, expenseCategoriesService.getTopIncomeCategory("","2022-11-02", "2022-11-11").getCategory());
        Assert.assertEquals(category3, expenseCategoriesService.getTopIncomeCategory("","2022-11-02", "2022-11-11").getCategory());
        Assert.assertEquals(category2, expenseCategoriesService.getTopIncomeCategory("","2022-11-02", "2022-11-06").getCategory());
        Assert.assertEquals(category1, expenseCategoriesService.getTopIncomeCategory("","2022-11-02", "2022-11-04").getCategory());

    }

    @Test
    public void getCategoriesWithAmounts() throws ParseException {
        Expense in1 = new Expense("", 1, new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
                .parse("2022-11-03"), "", "", "Income", "", category1, wallet);
        Expense in2 = new Expense("", 2, new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
                .parse("2022-11-03"), "", "", "Income", "", category1, wallet);
        Expense in3 = new Expense("", 30, new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
                .parse("2022-11-05"), "", "", "Income", "", category2, wallet);
        Expense in4 = new Expense("", 31, new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
                .parse("2022-11-05"), "", "", "Income", "", category2, wallet);
        Expense in5 = new Expense("", 999, new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
                .parse("2022-11-10"), "", "", "Income", "", category3, wallet);
        Expense in6 = new Expense("", 1000, new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
                .parse("2022-11-10"), "", "", "Income", "", category3, wallet);

        Expense ex7 = new Expense("", 1, new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
                .parse("2022-11-03"), "", "", "Expense", "", category1, wallet);
        Expense ex8 = new Expense("", 2, new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
                .parse("2022-11-03"), "", "", "Expense", "", category1, wallet);
        Expense ex9 = new Expense("", 30, new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
                .parse("2022-11-05"), "", "", "Expense", "", category2, wallet);
        Expense ex10 = new Expense("", 31, new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
                .parse("2022-11-05"), "", "", "Expense", "", category2, wallet);
        Expense ex11 = new Expense("", 999, new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
                .parse("2022-11-10"), "", "", "Expense", "", category3, wallet);
        Expense ex12 = new Expense("", 1000, new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
                .parse("2022-11-10"), "", "", "Expense", "", category3, wallet);

        ArrayList<Category> listOfCategories = new ArrayList<>();

        Set setTransactions1 = new HashSet();
        setTransactions1.add(in1);
        setTransactions1.add(in2);
        setTransactions1.add(ex11);
        setTransactions1.add(ex12);


        Set setTransactions2 = new HashSet();
        setTransactions2.add(in3);
        setTransactions2.add(in4);
        setTransactions2.add(ex7);
        setTransactions2.add(ex8);

        Set setTransactions3 = new HashSet();
        setTransactions3.add(in5);
        setTransactions3.add(in6);
        setTransactions3.add(ex9);
        setTransactions3.add(ex10);

        listOfCategories.add(category1);
        listOfCategories.add(category2);
        listOfCategories.add(category3);

        Mockito.when(expenseCategoriesRepo.findAllUserExpenseCategories(Mockito.any())).thenReturn(listOfCategories);
        Mockito.when(category1.getExpenses()).thenReturn(setTransactions1);
        Mockito.when(category2.getExpenses()).thenReturn(setTransactions2);
        Mockito.when(category3.getExpenses()).thenReturn(setTransactions3);

        Assert.assertEquals(3, expenseCategoriesService.getExpenseCategoriesWithAmounts("","2022-11-02", "2022-11-11").size());

        Assert.assertEquals(1999, expenseCategoriesService.getExpenseCategoriesWithAmounts("","2022-11-02", "2022-11-11").get(0).getExpenseAmount());
        Assert.assertEquals(3, expenseCategoriesService.getExpenseCategoriesWithAmounts("","2022-11-02", "2022-11-11").get(0).getIncomeAmount());

        Assert.assertEquals(3, expenseCategoriesService.getExpenseCategoriesWithAmounts("","2022-11-02", "2022-11-11").get(1).getExpenseAmount());
        Assert.assertEquals(61, expenseCategoriesService.getExpenseCategoriesWithAmounts("","2022-11-02", "2022-11-11").get(1).getIncomeAmount());

        Assert.assertEquals(61, expenseCategoriesService.getExpenseCategoriesWithAmounts("","2022-11-02", "2022-11-11").get(2).getExpenseAmount());
        Assert.assertEquals(1999, expenseCategoriesService.getExpenseCategoriesWithAmounts("","2022-11-02", "2022-11-11").get(2).getIncomeAmount());


    }



}
