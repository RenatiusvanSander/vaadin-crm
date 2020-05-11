package com.vaadin.tutorial.crm.ui;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.tutorial.crm.backend.entity.Contact;

/**
 * This handles the main router to localhost:8080 and its layout.
 */
@Route("")
public class MainView extends VerticalLayout {

    Grid<Contact> grid = new Grid<>(Contact.class);

    public MainView() {
        addClassName("list-view");
        setSizeFull();

        add(grid);
    }

}