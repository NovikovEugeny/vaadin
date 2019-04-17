package com.example.vaadin.ui;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route(value = "users")
public class MainView extends VerticalLayout {

    private UserTable userTable;

    public MainView(UserTable userTable) {
        this.userTable = userTable;

        add(userTable);
    }
}