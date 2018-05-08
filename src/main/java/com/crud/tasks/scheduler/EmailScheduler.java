package com.crud.tasks.scheduler;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.Mail;
import com.crud.tasks.repository.TaskRepository;
import com.crud.tasks.service.SimpleEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
//import sun.misc.Signal;

@Component
public class EmailScheduler {

    @Autowired
    private SimpleEmailService simplemailService;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private AdminConfig adminConfig;

    private static final String SUBJECT = "Task: Once a day email.";

    @Scheduled(fixedDelay = 86400000)
    public void sendInformationEmail() {

        long size = taskRepository.count();
        simplemailService.send(
                new Mail(adminConfig.getAdminMail(),
                        SUBJECT,
                        "Current in database you got: "+
                                size+(size!=1?" tasks":" task")));}
}
