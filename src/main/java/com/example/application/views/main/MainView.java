package com.example.application.views.main;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import lombok.AccessLevel;
import lombok.Getter;
import org.springframework.beans.factory.InitializingBean;

@Route("")
public class MainView extends VerticalLayout implements InitializingBean {

    private final H1 title = new H1("Vaadin Todo");

    @Getter(AccessLevel.PACKAGE)
    private final VerticalLayout todosList = new VerticalLayout();
    @Getter(AccessLevel.PACKAGE)
    private final Button addButton = new Button("Add");

    @Getter(AccessLevel.PACKAGE)
    private final TextField taskField = new TextField();

    @Override
    public void afterPropertiesSet() throws Exception {
        todosList.setId("todoList");

        taskField.setId("todoField");
        taskField.setClearButtonVisible(true);

        addButton.setId("addTodoButton");
        addButton.addClickListener(click -> addTaskToList());
        //TODO: untested - figure out how to validate click shortcuts added
        addButton.addClickShortcut(Key.ENTER);

        add(
                title,
                todosList,
                new HorizontalLayout(
                        taskField,
                        addButton
                )
        );
    }

    private void addTaskToList() {

        if (taskField.isEmpty()) {
            return;
        }

        Checkbox task = new Checkbox(taskField.getValue());
        task.setClassName("todoItem");
        todosList.add(task);

        taskField.clear();
        //TODO: untested - figure out how to validate focus called - probably mock?
        taskField.focus();
    }


}