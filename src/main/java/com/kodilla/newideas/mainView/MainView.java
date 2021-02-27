package com.kodilla.newideas.mainView;

import com.kodilla.newideas.controller.IdeaController;
import com.kodilla.newideas.domain.IdeaExpert;
import com.kodilla.newideas.domain.IdeaNotification;
import com.kodilla.newideas.domain.User;
import com.kodilla.newideas.service.DbService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;


@CssImport("./my-styles/styles.css")
@Route
public class MainView extends VerticalLayout {

    @Autowired
    IdeaController ideaController;

    DbService dbService;
    IdeaForm ideaForm;

    private TextField filterByDescription = new TextField();
    private NumberField filterById = new NumberField();

   // private IdeaForm form = new IdeaForm(this);
    private ExpertForm expertForm = new ExpertForm(this);
    private UserForm userForm = new UserForm(this);

    private Button addNewIdea = new Button("Add new idea");
    private Button addNewExpert = new Button("Add new expert");
    private Button addNewUser = new Button("Add new user");

    private Grid grid = new Grid<>(IdeaNotification.class);
    private ComboBox<IdeaExpert> expertComboBox = new ComboBox<>("Expert");
    //Image image = new Image("https://snipboard.io/Qi2Ud7.jpg", "Logo");

    public MainView(DbService service, IdeaForm form) {

        this.dbService = service;
        this.ideaForm = form;

        add(new Label("KAIZEN Employee suggestion system"));
        add((new com.vaadin.flow.component.Component[]{new Label("Number of idea notifications: " + dbService.countIdeas())}));

        //Show all ideas
        grid.setItems(dbService.getAllIdeas());

        expertComboBox.setItems(dbService.getAllExperts());

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

        HorizontalLayout toolbar = new HorizontalLayout(filterByDescription, filterById, addNewIdea, addNewExpert, addNewUser, expertComboBox);

        HorizontalLayout mainContent = new HorizontalLayout(grid, form, expertForm,userForm);
        mainContent.setSizeFull();
        grid.setSizeFull();

        add(toolbar, mainContent);
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

    public List<IdeaExpert> getExperts() {

        try {
            List<IdeaExpert> experts1 = dbService.getAllExperts();
            System.out.println("Moi experci to: " + experts1);
            return dbService.getAllExperts();
        } catch (Exception e) {
            List<IdeaExpert> experts = new ArrayList<>();
            experts.add(new IdeaExpert("Joanna Nowak", null));
            experts.add(new IdeaExpert("Joanna Kowalska", null));
            experts.add(new IdeaExpert("Joanna Zupa", null));
            return experts;
        }
    }

    public List<IdeaExpert> getExpertsForCombo() {
        List<IdeaExpert> list = dbService.getAllExperts();

        return list;
    }

}
