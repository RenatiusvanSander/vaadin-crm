package com.vaadin.tutorial.crm.ui;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.tutorial.crm.backend.entity.Company;
import com.vaadin.tutorial.crm.backend.entity.Contact;
import com.vaadin.tutorial.crm.backend.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * This handles the main router to localhost:8080 and its layout.
 */
@Route("")
public class MainView extends VerticalLayout {

    Grid<Contact> grid = new Grid<>(Contact.class);
    TextField filterText = new TextField();

    public MainView(@Autowired ContactService contactService) {
        this.contactService = contactService;

        addClassName("list-view");
        setSizeFull();
        configureGrid();

        add(filterText, grid);
        updateList();
    }

    private void updateList() {
        grid.setItems(contactService.findAll());
    }

    private void configureGrid() {
        grid.addClassName("contact.grid");
        grid.setSizeFull();
        grid.removeColumnByKey("company");
        grid.setColumns("firstName", "lastName", "email", "status");
        grid.addColumn(contact -> {
            Company company = contact.getCompany();
            return company == null ? "-" : company.getName();
        }).setHeader("Company");

        grid.getColumns().forEach(col -> col.setAutoWidth(true));
    }

    private final ContactService contactService;
}