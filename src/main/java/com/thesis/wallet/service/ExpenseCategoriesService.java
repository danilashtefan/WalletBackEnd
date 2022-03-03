package com.thesis.wallet.service;

import com.thesis.wallet.DAO.ExpanseCategoryRepository;
import com.thesis.wallet.entity.Expanse;
import com.thesis.wallet.entity.ExpanseCategory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class ExpenseCategoriesService {
    private final ExpanseCategoryRepository expanseCategoryRepository;

    public List<ExpanseCategory> getAllExpenseCategories(String username){
        return expanseCategoryRepository.findAllUserExpenseCategories(username);
    }

    public String editByIdAndUsername(Long id, ExpanseCategory category, String username){
        expanseCategoryRepository.editByIdAndUsername(id, category.getExpanseCategoryName(), category.getIcon(), category.getType() , username);
        return "Process of editing the category on the server started...";
    }

    public List<Expanse> getCategoryFilteredExpenses(String username, Long id){
        return expanseCategoryRepository.findAllCategoriesExpenses(username, id);
    }


}
