package com.thesis.wallet.config;

import com.thesis.wallet.entity.Category;
import com.thesis.wallet.entity.Expense;
import com.thesis.wallet.entity.Wallet;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

@Configuration
public class SpringDataRestConfig implements RepositoryRestConfigurer {
    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {
        config.exposeIdsFor(Expense.class);
        config.exposeIdsFor(Category.class);
        config.exposeIdsFor(Wallet.class);
    }

}
