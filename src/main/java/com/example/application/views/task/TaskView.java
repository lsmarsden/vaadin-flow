package com.example.application.views.task;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.InitializingBean;

@Route("task")
public class TaskView extends VerticalLayout implements InitializingBean {
    @Override
    public void afterPropertiesSet() throws Exception {
        TextField textField = new TextField("Viewing the task!");

        add(textField);
    }
}
