package com.example.application.views.main;

import com.example.application.views.task.TodoList;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

@Route("")
public class MainView extends VerticalLayout implements InitializingBean {

    private final H1 title = new H1("Vaadin Todo");

    @Autowired
    private TodoList todoList;

    @Override
    public void afterPropertiesSet() throws Exception {
        add(title, todoList);
    }
}