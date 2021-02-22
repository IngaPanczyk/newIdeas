package com.kodilla.newideas.mainView;

import com.kodilla.newideas.controller.IdeaController;
import com.kodilla.newideas.domain.IdeaExpert;
import com.kodilla.newideas.service.DbService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

@CssImport("./my-styles/styles.css")
@Route
public class MainView extends VerticalLayout {

    @Autowired
    IdeaController ideaController;


    private Grid grid = new Grid<>(IdeaExpert.class);

    public MainView(DbService dbService){
        add(new Button("Click me" ,e -> Notification.show("Hello Word")));

        grid.setItems(dbService.getAllExperts());

        grid.setColumns("expertId", "expertName","ideaNotificationList");

        add(grid);
        setSizeFull();


    }

/*    public void refresh() {
        grid.setItems(dbService.getAllExperts());
    }*/

}
