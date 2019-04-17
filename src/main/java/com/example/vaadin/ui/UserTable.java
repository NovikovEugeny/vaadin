package com.example.vaadin.ui;

import com.example.vaadin.backend.entity.User;
import com.example.vaadin.backend.repository.UserRepository;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;

@UIScope
@SpringComponent
public class UserTable extends VerticalLayout {

    private final UserRepository userRepository;

    private Grid<User> grid = new Grid<>(User.class);

    public UserTable(UserRepository userRepository) {
        this.userRepository = userRepository;

        add(createUserPanel(), userGrid());
    }

    private Grid<User> userGrid() {
        grid.setColumns("id", "name", "age");
        grid.setItems(userRepository.findAll());
        return grid;
    }

    private HorizontalLayout createUserPanel() {
        TextField nameTextField = new TextField();
        nameTextField.setMaxLength(200);
        nameTextField.setMinLength(50);

        Button createButton = new Button("create");

        createButton.addClickListener(e -> {
           userRepository.save(new User(nameTextField.getValue(), 0));
           nameTextField.clear();
           grid.setItems(userRepository.findAll());
        });

        return new HorizontalLayout(nameTextField, createButton);
    }
}
