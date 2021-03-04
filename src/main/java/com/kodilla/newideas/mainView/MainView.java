package com.kodilla.newideas.mainView;

import com.kodilla.newideas.client.NbpClient;
import com.kodilla.newideas.domain.IdeaExpert;
import com.kodilla.newideas.domain.IdeaNotification;
import com.kodilla.newideas.domain.User;
import com.kodilla.newideas.mainView.graf.StatusGraf;
import com.kodilla.newideas.service.DbService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;


@CssImport("./my-styles/styles.css")
@Route
public class MainView extends VerticalLayout {

    DbService dbService;
    IdeaForm ideaForm;
    NbpClient nbpClient;

    StatusGraf statusGraf = new StatusGraf();

    private TextField filterByDescription = new TextField();
    private NumberField filterById = new NumberField();

    private ExpertForm expertForm = new ExpertForm(this);
    private UserForm userForm = new UserForm(this);

    private Button addNewIdea = new Button("Add new idea",
           new Icon(VaadinIcon.LIGHTBULB));
    private Button addNewExpert = new Button("Add new expert",
            new Icon((VaadinIcon.SPECIALIST)));
    private Button addNewUser = new Button("Add new user",
            new Icon(VaadinIcon.USER));
    private Button updateGraf = new Button("Update graf",
            new Icon(VaadinIcon.REFRESH));

    private Grid grid = new Grid<>(IdeaNotification.class);

    public MainView(DbService service, IdeaForm form, NbpClient nbpClient) {

        this.dbService = service;
        this.ideaForm = form;
        this.nbpClient = nbpClient;

        add(new Label("KAIZEN Employee suggestion system"));
        add((new com.vaadin.flow.component.Component[]{new Label("Number of idea notifications: " + dbService.countIdeas())}));
        add((new com.vaadin.flow.component.Component[]{new Label("Euro exchange rate: " + nbpClient.getEur())}));

        Image image = new Image(statusGraf.createGraf(dbService),"Graf");

        //Show all ideas
        grid.setItems(dbService.getAllIdeas());


        grid.setColumns("id", "description", "subject", "reportingDate", "status", "ideaExpert", "user");

        //Filters
        filterByDescription.setPlaceholder("Filter by subject");
        filterByDescription.setClearButtonVisible(true);
        filterByDescription.setValueChangeMode(ValueChangeMode.EAGER);
        filterByDescription.addValueChangeListener(e -> grid.setItems(dbService.filterIdeasByDescription(filterByDescription.getValue())));

        filterById.setPlaceholder("Filter by id");
        filterById.setClearButtonVisible(true);
        filterById.setValueChangeMode(ValueChangeMode.EAGER);
        filterById.addValueChangeListener(e -> grid.setItems(dbService.filterIdeasById(filterById.getValue().longValue())));

        addNewIdea.addClickListener(e -> {
            grid.asSingleSelect().clear();
            form.setForm(new IdeaNotification());
        });

        addNewExpert.addClickListener(e -> {
            grid.asSingleSelect().clear();
            expertForm.setFormExpert(new IdeaExpert());
        });

        addNewUser.addClickListener(e -> {
            grid.asSingleSelect().clear();
            userForm.setFormUser(new User());
        });

        HorizontalLayout toolbar = new HorizontalLayout(filterByDescription, filterById, addNewIdea, addNewExpert, addNewUser, updateGraf);

        VerticalLayout graf = new VerticalLayout( new Image(statusGraf.createGraf(dbService),"Graf"));

        updateGraf.addClickListener(e -> {
        graf.removeAll();
        graf.add(new Image(statusGraf.createGraf(dbService),"Graf"));
        });

        HorizontalLayout mainContent = new HorizontalLayout(grid, form, expertForm,userForm);
        mainContent.setSizeFull();
        grid.setSizeFull();

        add(toolbar, mainContent,graf);
        form.setForm(null);
        expertForm.setFormExpert(null);
        userForm.setFormUser(null);
        setSizeFull();


        grid.asSingleSelect().addValueChangeListener(event -> form.setForm((IdeaNotification) grid.asSingleSelect().getValue()));

    }

    public DbService getDbService() {
        return dbService;
    }

    public void refresh() {
        grid.setItems(dbService.getAllIdeas());
    }


}
