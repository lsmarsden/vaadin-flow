package com.example.application.views.main;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;

@Route("new")
public class NewView extends VerticalLayout {

    public NewView() {
        add(new TextField("A new view!"));
        add(new RouterLink("Back", MainView.class));
    }
}
