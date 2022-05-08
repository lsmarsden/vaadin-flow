package com.example.application.views.main;

import com.example.application.views.task.TaskView;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;

@Route("")
public class MainView extends VerticalLayout {

    public MainView() {
        VerticalLayout todosList = new VerticalLayout();
        TextField taskField = new TextField();
        taskField.setId("todoField");
        Button addButton = new Button("Add");
        addButton.setId("addTodoButton");
        addButton.addClickListener(click -> {
            if (taskField.isEmpty()) {
                return;
            }

            HorizontalLayout taskLayout = createTaskLayout(taskField.getValue());

            todosList.add(taskLayout);
            todosList.setId("todoList");
            taskField.clear();
            taskField.focus();
        });
        addButton.addClickShortcut(Key.ENTER);

        add(
                new H1("Vaadin Todo"),
                todosList,
                new HorizontalLayout(
                        taskField,
                        addButton
                )
        );
        add(new RouterLink("Somewhere else", NewView.class));
    }

    private HorizontalLayout createTaskLayout(String text) {
        HorizontalLayout layout = new HorizontalLayout();

        Checkbox checkbox = new Checkbox(text);
        checkbox.setClassName("todoItem");
        RouterLink link = createTaskRouterLink();
        layout.add(checkbox, link);
        return layout;
    }

    private RouterLink createTaskRouterLink() {
        return new RouterLink("View task", TaskView.class);
    }
}