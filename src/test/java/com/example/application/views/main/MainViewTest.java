package com.example.application.views.main;

import com.example.application.views.task.TodoList;
import com.vaadin.flow.component.html.H1;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = {MainView.class, TodoList.class})
class MainViewTest {

    @Autowired
    private MainView underTest;

    @Test
    void afterPropertiesSet() throws Exception {

        // exercise - afterPropertiesSet() called in initialization

        // verify
        int componentCount = 0;

        H1 title = (H1) underTest.getComponentAt(componentCount++);
        assertEquals("Vaadin Todo", title.getText());

        TodoList todoList = (TodoList) underTest.getComponentAt(componentCount++);
        assertEquals("todoList", todoList.getId().get());
    }
}