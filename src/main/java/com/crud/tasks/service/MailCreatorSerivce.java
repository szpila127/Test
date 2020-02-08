package com.crud.tasks.service;

import com.crud.tasks.trello.config.AdminConfig;
import com.crud.tasks.trello.config.CompanyConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.ArrayList;
import java.util.List;

@Service
public class MailCreatorSerivce {

    @Autowired
    private AdminConfig adminConfig;

    @Autowired
    private CompanyConfig companyConfig;

    @Autowired
    @Qualifier("templateEngine")
    private TemplateEngine templateEngine;

    public String buildTrelloCardEmail(String message) {

        List<String> functionality = new ArrayList<>();
        functionality.add("You can menage your tasks");
        functionality.add("Provides connection with Trello Account");
        functionality.add("Application allows sending tasks to Trello");

        Context context = new Context();
        context.setVariable("preview_message", message.substring(0, 50));
        context.setVariable("message", message);
        context.setVariable("task_url", "https://szpila127.github.io/");
        context.setVariable("button", "Visit website");
        context.setVariable("admin_name", adminConfig.getAdminName());
        context.setVariable("goodbye_message", "Best regards, " + companyConfig.getCompanyName());
        context.setVariable("show_button", false);
        context.setVariable("is_friend", true);
        context.setVariable("admin_config", adminConfig);
        context.setVariable("company_config", companyConfig);
        context.setVariable("application_functionality", functionality);
        return templateEngine.process("mail/created-trello-card-mail", context);
    }

    public String buildTaskAmountEmail(String message) {

        Context context = new Context();
        context.setVariable("preview_message", message.substring(0, 30));
        context.setVariable("message", message);
        context.setVariable("task_url", "https://szpila127.github.io/");
        context.setVariable("button", "Your app");
        context.setVariable("admin_name", adminConfig.getAdminName());
        context.setVariable("goodbye_message", adminConfig.getAdminName() + ", have a nice day ! :)");
        context.setVariable("show_button", true);
        context.setVariable("admin_config", adminConfig);
        context.setVariable("company_config", companyConfig);
        return templateEngine.process("mail/amount-of-tasks-mail", context);
    }
}
