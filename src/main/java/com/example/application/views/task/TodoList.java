package com.example.application.views.task;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.spring.annotation.SpringComponent;
import lombok.AccessLevel;
import lombok.Getter;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;

@SpringComponent
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Getter(AccessLevel.PACKAGE)
public class TodoList extends VerticalLayout implements InitializingBean {
    private final Button addButton = new Button("Add");

    private final TextField taskField = new TextField();

    private final VerticalLayout taskList = new VerticalLayout();

    @Override
    public void afterPropertiesSet() throws Exception {
        setId("todoList");

        taskField.setId("todoField");
        taskField.setClearButtonVisible(true);

        addButton.setId("addTodoButton");
        addButton.addClickListener(click -> addTaskToList());
        //TODO: untested - figure out how to validate click shortcuts added
        addButton.addClickShortcut(Key.ENTER);

        HorizontalLayout buttonLayout = new HorizontalLayout(taskField, addButton);
        taskList.setVisible(false);
        add(taskList, buttonLayout);
    }

    private void addTaskToList() {

        if (taskField.isEmpty()) {
            return;
        }

        Checkbox task = new Checkbox(taskField.getValue());
        task.setClassName("todoItem");
        taskList.add(task);
        taskList.setVisible(true);

        taskField.clear();
        //TODO: untested - figure out how to validate focus called - probably mock?
        taskField.focus();
    }
}
