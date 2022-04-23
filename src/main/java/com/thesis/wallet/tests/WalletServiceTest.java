package com.thesis.wallet.tests;

import com.thesis.wallet.DAO.WalletRepository;
import com.thesis.wallet.entity.Category;
import com.thesis.wallet.entity.Expense;
import com.thesis.wallet.entity.Wallet;
import com.thesis.wallet.service.ExpenseCategoriesService;
import com.thesis.wallet.service.WalletService;
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
public class WalletServiceTest {
    @Mock
    WalletRepository walletRepository;
    @Mock
    Wallet wallet1;
    @Mock
    Wallet wallet2;
    @Mock
    Wallet wallet3;
    @Mock
    Category category;

    WalletService walletService;
    @Before
    public void setUp() {
        walletService = new WalletService(walletRepository);
    }

    @Test
    public void getTopExpenseWallet() throws ParseException {
        Expense ex1 = new Expense("", 1, new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
                        .parse("2022-11-03"), "", "", "Expense", "", category, wallet1);
        Expense ex2 = new Expense("", 2, new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
                        .parse("2022-11-03"), "", "", "Expense", "", category, wallet1);
        Expense ex3 = new Expense("", 30, new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
                        .parse("2022-11-05"), "", "", "Expense", "", category, wallet2);
        Expense ex4 = new Expense("", 31, new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
                        .parse("2022-11-05"), "", "", "Expense", "", category, wallet2);
        Expense ex5 = new Expense("", 999, new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
                        .parse("2022-11-10"), "", "", "Expense", "", category, wallet3);
        Expense ex6 = new Expense("", 1000, new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
                        .parse("2022-11-10"), "", "", "Expense", "", category, wallet3);

        Expense in7 = new Expense("", 1, new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
                        .parse("2022-11-03"), "", "", "Income", "", category, wallet1);
        Expense in8 = new Expense("", 2, new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
                        .parse("2022-11-03"), "", "", "Income", "", category, wallet1);
        Expense in9 = new Expense("", 30, new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
                        .parse("2022-11-05"), "", "", "Income", "", category, wallet2);
        Expense in10 = new Expense("", 31, new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
                        .parse("2022-11-05"), "", "", "Income", "", category, wallet2);
        Expense in11 = new Expense("", 999, new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
                        .parse("2022-11-10"), "", "", "Income", "", category, wallet3);
        Expense in12 = new Expense("", 1000, new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
                        .parse("2022-11-10"), "", "", "Income", "", category, wallet3);

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

        ArrayList<Wallet> listOfWallets = new ArrayList<>();

        listOfWallets.add(wallet1);
        listOfWallets.add(wallet2);
        listOfWallets.add(wallet3);

        Mockito.when(walletRepository.findAllUserWallets(Mockito.any())).thenReturn(listOfWallets);
        Mockito.when(wallet1.getExpenses()).thenReturn(setTransactions1);
        Mockito.when(wallet2.getExpenses()).thenReturn(setTransactions2);
        Mockito.when(wallet3.getExpenses()).thenReturn(setTransactions3);

        Assert.assertEquals(wallet3, walletService.getTopExpenseWallet("","2022-11-02", "2022-11-11").getWallet());
        Assert.assertEquals(wallet3, walletService.getTopExpenseWallet("","2022-11-02", "2022-11-11").getWallet());
        Assert.assertEquals(wallet2, walletService.getTopExpenseWallet("","2022-11-02", "2022-11-06").getWallet());
        Assert.assertEquals(wallet1, walletService.getTopExpenseWallet("","2022-11-02", "2022-11-04").getWallet());

    }

    @Test
    public void getTopIncomeWallet() throws ParseException {
        Expense in1 = new Expense("", 1, new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
                        .parse("2022-11-03"), "", "", "Income", "", category, wallet1);
        Expense in2 = new Expense("", 2, new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
                        .parse("2022-11-03"), "", "", "Income", "", category, wallet1);
        Expense in3 = new Expense("", 30, new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
                        .parse("2022-11-05"), "", "", "Income", "", category, wallet2);
        Expense in4 = new Expense("", 31, new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
                        .parse("2022-11-05"), "", "", "Income", "", category, wallet2);
        Expense in5 = new Expense("", 999, new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
                        .parse("2022-11-10"), "", "", "Income", "", category, wallet3);
        Expense in6 = new Expense("", 1000, new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
                        .parse("2022-11-10"), "", "", "Income", "", category, wallet3);

        Expense ex7 = new Expense("", 1, new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
                        .parse("2022-11-03"), "", "", "Expense", "", category, wallet1);
        Expense ex8 = new Expense("", 2, new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
                        .parse("2022-11-03"), "", "", "Expense", "", category, wallet1);
        Expense ex9 = new Expense("", 30, new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
                        .parse("2022-11-05"), "", "", "Expense", "", category, wallet2);
        Expense ex10 = new Expense("", 31, new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
                        .parse("2022-11-05"), "", "", "Expense", "", category, wallet2);
        Expense ex11 = new Expense("", 999, new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
                        .parse("2022-11-10"), "", "", "Expense", "", category, wallet3);
        Expense ex12 = new Expense("", 1000, new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
                        .parse("2022-11-10"), "", "", "Expense", "", category, wallet3);

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

        ArrayList<Wallet> listOfWallets= new ArrayList<>();

        listOfWallets.add(wallet1);
        listOfWallets.add(wallet2);
        listOfWallets.add(wallet3);

        Mockito.when(walletRepository.findAllUserWallets(Mockito.any())).thenReturn(listOfWallets);
        Mockito.when(wallet1.getExpenses()).thenReturn(setTransactions1);
        Mockito.when(wallet2.getExpenses()).thenReturn(setTransactions2);
        Mockito.when(wallet3.getExpenses()).thenReturn(setTransactions3);

        Assert.assertEquals(wallet3, walletService.getTopIncomeWallet("","2022-11-02", "2022-11-11").getWallet());
        Assert.assertEquals(wallet3, walletService.getTopIncomeWallet("","2022-11-02", "2022-11-11").getWallet());
        Assert.assertEquals(wallet2, walletService.getTopIncomeWallet("","2022-11-02", "2022-11-06").getWallet());
        Assert.assertEquals(wallet1, walletService.getTopIncomeWallet("","2022-11-02", "2022-11-04").getWallet());

    }

    @Test
    public void getWalletsWithAmounts() throws ParseException {
        Expense in1 = new Expense("", 1, new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
                        .parse("2022-11-03"), "", "", "Income", "", category, wallet1);
        Expense in2 = new Expense("", 2, new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
                        .parse("2022-11-03"), "", "", "Income", "", category, wallet1);
        Expense in3 = new Expense("", 30, new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
                        .parse("2022-11-05"), "", "", "Income", "", category, wallet2);
        Expense in4 = new Expense("", 31, new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
                        .parse("2022-11-05"), "", "", "Income", "", category, wallet2);
        Expense in5 = new Expense("", 999, new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
                        .parse("2022-11-10"), "", "", "Income", "", category, wallet3);
        Expense in6 = new Expense("", 1000, new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
                        .parse("2022-11-10"), "", "", "Income", "", category, wallet3);

        Expense ex7 = new Expense("", 1, new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
                        .parse("2022-11-03"), "", "", "Expense", "", category, wallet1);
        Expense ex8 = new Expense("", 2, new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
                        .parse("2022-11-03"), "", "", "Expense", "", category, wallet1);
        Expense ex9 = new Expense("", 30, new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
                        .parse("2022-11-05"), "", "", "Expense", "", category, wallet2);
        Expense ex10 = new Expense("", 31, new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
                        .parse("2022-11-05"), "", "", "Expense", "", category, wallet2);
        Expense ex11 = new Expense("", 999, new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
                        .parse("2022-11-10"), "", "", "Expense", "", category, wallet3);
        Expense ex12 = new Expense("", 1000, new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
                        .parse("2022-11-10"), "", "", "Expense", "", category, wallet3);

        ArrayList<Wallet> listOfWallets = new ArrayList<>();

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

        listOfWallets.add(wallet1);
        listOfWallets.add(wallet2);
        listOfWallets.add(wallet3);

        Mockito.when(walletRepository.findAllUserWallets(Mockito.any())).thenReturn(listOfWallets);
        Mockito.when(wallet1.getExpenses()).thenReturn(setTransactions1);
        Mockito.when(wallet2.getExpenses()).thenReturn(setTransactions2);
        Mockito.when(wallet3.getExpenses()).thenReturn(setTransactions3);

        Assert.assertEquals(3, walletService.getWalletsWithAmounts("","2022-11-02", "2022-11-11").size());

        Assert.assertEquals(1999, walletService.getWalletsWithAmounts("","2022-11-02", "2022-11-11").get(0).getExpenseAmount());
        Assert.assertEquals(3, walletService.getWalletsWithAmounts("","2022-11-02", "2022-11-11").get(0).getIncomeAmount());

        Assert.assertEquals(3, walletService.getWalletsWithAmounts("","2022-11-02", "2022-11-11").get(1).getExpenseAmount());
        Assert.assertEquals(61, walletService.getWalletsWithAmounts("","2022-11-02", "2022-11-11").get(1).getIncomeAmount());

        Assert.assertEquals(61, walletService.getWalletsWithAmounts("","2022-11-02", "2022-11-11").get(2).getExpenseAmount());
        Assert.assertEquals(1999, walletService.getWalletsWithAmounts("","2022-11-02", "2022-11-11").get(2).getIncomeAmount());


    }


}
