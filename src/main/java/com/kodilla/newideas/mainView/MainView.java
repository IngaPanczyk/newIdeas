package com.kodilla.newideas.mainView;

import com.kodilla.newideas.controller.IdeaController;
import com.kodilla.newideas.domain.IdeaExpert;
import com.kodilla.newideas.domain.IdeaNotification;
import com.kodilla.newideas.service.DbService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

@CssImport("./my-styles/styles.css")
@Route
public class MainView extends VerticalLayout {

    private TextField filter = new TextField();
    private IdeaForm form = new IdeaForm();

    @Autowired
    IdeaController ideaController;


    private Grid grid = new Grid<>(IdeaNotification.class);

    public MainView(DbService dbService) {

        add(new Button("Click me", e -> Notification.show("Hello Word")));
        add(new Label("KAIZEN Employee suggestion system"));

        //Show all ideas
        grid.setItems(dbService.getAllIdeas());
        grid.setColumns("id", "description", "subject", "reportingDate", "status", "ideaExpert", "user");

        HorizontalLayout mainContent = new HorizontalLayout(grid, form);
        mainContent.setSizeFull();
        grid.setSizeFull();

        //Filter
        filter.setPlaceholder("Filter by idea id");
        filter.setClearButtonVisible(true);
        filter.setValueChangeMode(ValueChangeMode.EAGER);
        filter.addValueChangeListener(e -> grid.setItems(dbService.getIdea(Long.valueOf(filter.getValue()))));

        add(filter,grid,form);
        setSizeFull();

        grid.asSingleSelect().addValueChangeListener(event -> form.setForm((IdeaNotification) grid.asSingleSelect().getValue()));

    }

}
