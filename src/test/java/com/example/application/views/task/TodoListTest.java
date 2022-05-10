package com.example.application.views.task;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = TodoList.class)
class TodoListTest {

    @Autowired
    private TodoList underTest;

    @Test
    void afterPropertiesSet() {

        // exercise - afterPropertiesSet() called in initialization

        // verify
        assertEquals("todoList", underTest.getId().get());

        int componentCount = 0;

        VerticalLayout taskList = (VerticalLayout) underTest.getComponentAt(componentCount++);
        assertFalse(taskList.isVisible());
        assertEquals(0, taskList.getComponentCount());

        HorizontalLayout addTaskLayout = (HorizontalLayout) underTest.getComponentAt(componentCount++);

        int layoutComponentCount = 0;
        TextField taskField = (TextField) addTaskLayout.getComponentAt(layoutComponentCount++);
        assertEquals("todoField", taskField.getId().get());
        assertTrue(taskField.isEmpty());
        assertTrue(taskField.isClearButtonVisible());

        Button addButton = (Button) addTaskLayout.getComponentAt(layoutComponentCount++);
        assertEquals("addTodoButton", addButton.getId().get());
        assertEquals("Add", addButton.getText());

        assertEquals(underTest.getComponentCount(), componentCount);
    }

    @Test
    void addEmptyTaskToListShouldNotAdd() {

        // set up
        underTest.getTaskField().clear();
        // the taskField and addButton should be present
        VerticalLayout taskList = (VerticalLayout) underTest.getComponentAt(0);
        assertEquals(0, taskList.getComponentCount());

        // exercise
        underTest.getAddButton().click();

        // verify
        assertFalse(taskList.isVisible());
        assertEquals(0, taskList.getComponentCount());
    }

    @Test
    void addTaskShouldAddToTaskListAndClearTaskField() {

        // set up
        TextField taskField = underTest.getTaskField();
        Button addButton = underTest.getAddButton();
        taskField.setValue("Task to add");

        VerticalLayout taskList = (VerticalLayout) underTest.getComponentAt(0);
        assertEquals(0, taskList.getComponentCount());

        // exercise
        addButton.click();

        // verify
        assertTrue(taskList.isVisible());
        assertEquals(1, taskList.getComponentCount());

        Checkbox task = (Checkbox) taskList.getComponentAt(0);
        assertEquals("todoItem", task.getClassName());
        assertEquals("Task to add", task.getLabel());
        assertFalse(task.getValue());

        assertTrue(taskField.isEmpty());
    }
}