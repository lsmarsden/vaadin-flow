package com.example.application.views.main;

import com.example.application.views.main.MainView;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MainViewTest {

    private MainView underTest = new MainView();

    @Test
    void afterPropertiesSet() throws Exception {

        // exercise
        underTest.afterPropertiesSet();

        // verify
        int componentCount = 0;

        H1 title = (H1) underTest.getComponentAt(componentCount++);
        assertEquals("Vaadin Todo", title.getText());

        VerticalLayout todosList = (VerticalLayout) underTest.getComponentAt(componentCount++);
        assertEquals("todoList", todosList.getId().get());

        HorizontalLayout taskLayout = (HorizontalLayout) underTest.getComponentAt(componentCount++);

        int layoutComponentCount = 0;
        TextField taskField = (TextField) taskLayout.getComponentAt(layoutComponentCount++);
        assertEquals("todoField", taskField.getId().get());
        assertTrue(taskField.isEmpty());
        assertTrue(taskField.isClearButtonVisible());

        Button addButton = (Button) taskLayout.getComponentAt(layoutComponentCount++);
        assertEquals("addTodoButton", addButton.getId().get());
        assertEquals("Add", addButton.getText());

        assertEquals(underTest.getComponentCount(), componentCount);
    }

    @Test
    void addEmptyTaskToListShouldNotAdd() {

        // set up
        underTest.getTaskField().clear();

        // exercise
        underTest.getAddButton().click();

        // verify
        assertEquals(0, underTest.getTodosList().getComponentCount());
    }

    @Test
    void addTaskShouldAddToTaskListAndClearTaskField() {

        // set up
        VerticalLayout todosList = underTest.getTodosList();

        TextField taskField = underTest.getTaskField();
        Button addButton = underTest.getAddButton();
        assertEquals(0, todosList.getComponentCount());

        taskField.setValue("Task to add");

        // exercise
        addButton.click();

        // verify
        assertEquals(1, todosList.getComponentCount());

        Checkbox task = (Checkbox) todosList.getComponentAt(0);
        assertEquals("todoItem", task.getClassName());
        assertEquals("Task to add", task.getLabel());
        assertFalse(task.getValue());

        assertTrue(taskField.isEmpty());
    }
}