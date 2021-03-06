package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.config.CompanyDetailsConfig;
import com.crud.tasks.config.UserConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;


@Service
public class MailCreatorService {

    @Autowired
    AdminConfig adminConfig;

    @Autowired
    UserConfig userConfig;

    @Autowired
    CompanyDetailsConfig companyDetailsConfig;

    @Autowired
    @Qualifier("templateEngine")
    private TemplateEngine templateEngine;

    public String buildTrelloCardEmail(String message) {

//        List<String> funcionality = new ArrayList<>();
//        funcionality.add("You can manage Your tasks");
//        funcionality.add("Provides connection with Trello Account");
//        funcionality.add("Application allows sending tasks to Trello");

        Context context = new Context();
        context.setVariable("message", message);
        context.setVariable("gbMessage", userConfig.getUserName()+" "+userConfig.getUserSurname());
        context.setVariable("tasks_url", "http://localhost:8888/tasks_frontend/");
        context.setVariable("button", "Visit website");
        context.setVariable("admin_name", adminConfig.getAdminName());
        context.setVariable("company_details",
                companyDetailsConfig.getCompanyName()+'\n'+companyDetailsConfig.getCompanyGoal()+'\n'
                        +companyDetailsConfig.getCompanyPhone()+'\n'+companyDetailsConfig.getCompanyEmail());
        context.setVariable("show_button", true);
        context.setVariable("is_friend", true);
        context.setVariable("admin_config", adminConfig);
        //context.setVariable("application_funcionality", funcionality);


        return templateEngine.process("mail/created-trello-card-mail", context);
    }
}
