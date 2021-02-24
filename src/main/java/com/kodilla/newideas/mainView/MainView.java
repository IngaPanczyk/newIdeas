package com.kodilla.newideas.mainView;

import com.kodilla.newideas.controller.IdeaController;
import com.kodilla.newideas.domain.IdeaNotification;
import com.kodilla.newideas.service.DbService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.concurrent.atomic.AtomicLongFieldUpdater;

@CssImport("./my-styles/styles.css")
@Route
public class MainView extends VerticalLayout {


    private TextField filterByDescription = new TextField();
    private NumberField  filterById = new NumberField();
    private IdeaForm form = new IdeaForm();

    @Autowired
    IdeaController ideaController;

    private Grid grid = new Grid<>(IdeaNotification.class);

    public MainView(DbService dbService) {

        add(new Label("KAIZEN Employee suggestion system"));
        add((new com.vaadin.flow.component.Component[]{new Label("Numbner of idea notifications: " + dbService.countIdeas())}));

        //Show all ideas
        grid.setItems(dbService.getAllIdeas());
        grid.setColumns("id", "description", "subject", "reportingDate", "status", "ideaExpert", "user");

        HorizontalLayout mainContent = new HorizontalLayout(grid, form);
        mainContent.setSizeFull();
        grid.setSizeFull();

        //Filters
        filterByDescription.setPlaceholder("Filter by subject");
        filterByDescription.setClearButtonVisible(true);
        filterByDescription.setValueChangeMode(ValueChangeMode.EAGER);
        filterByDescription.addValueChangeListener(e -> grid.setItems(dbService.filterIdeasByDescription(filterByDescription.getValue())));

        filterById.setPlaceholder("Filter by id");
        filterById.setClearButtonVisible(true);
        filterById.setValueChangeMode(ValueChangeMode.EAGER);
        filterById.addValueChangeListener(e -> grid.setItems(dbService.filterIdeasById(filterById.getValue().longValue())));

        add(filterByDescription, filterById, mainContent);
        setSizeFull();

        grid.asSingleSelect().addValueChangeListener(event -> form.setForm((IdeaNotification) grid.asSingleSelect().getValue()));

    }

}
