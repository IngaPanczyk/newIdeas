package com.kodilla.newideas.mainView;

import com.kodilla.newideas.controller.IdeaController;
import com.kodilla.newideas.domain.IdeaExpert;
import com.kodilla.newideas.domain.IdeaNotification;
import com.kodilla.newideas.domain.IdeaStatus;
import com.kodilla.newideas.domain.User;
import com.kodilla.newideas.service.DbService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.PropertyId;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.stream.Collectors;

public class IdeaForm extends FormLayout {

    @Autowired
    IdeaController ideaController;

    @Autowired
    DbService dbService;

    MainView mainView;

    private Binder<IdeaNotification> binder = new Binder<>(IdeaNotification.class);

    @PropertyId("subject")
    private TextField subject = new TextField("Subject");

    @PropertyId("description")
    private TextField description = new TextField("Description");
    @PropertyId("reportingDate")
    private DatePicker reportingDate = new DatePicker("ReportingDate");
    @PropertyId("status")
    private ComboBox<IdeaStatus> status = new ComboBox<>("Status");
    @PropertyId("ideaExpert")
    private ComboBox<IdeaExpert> ideaExpert = new ComboBox<>("IdeaExpert");
    @PropertyId("user")
    private ComboBox<User> user = new ComboBox<>("User");

    private Button save = new Button("Save");
    private Button delete = new Button("Delete");

    public IdeaForm(MainView mainView) {
        this.mainView = mainView;

        //ideaExpert.setItems(mainView.getDbService().getAllExperts().stream().map(e->e.getExpertName()).collect(Collectors.toSet()));
        ideaExpert.setItems(mainView.getExperts());
        HorizontalLayout buttons = new HorizontalLayout(save, delete);
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        add(subject, description, reportingDate, status, ideaExpert, user, buttons);
        binder.forField(subject)
                .bind(
                        IdeaNotification::getSubject,
                        IdeaNotification::setSubject
                );

        binder.forField(description)
                .bind(
                        IdeaNotification::getDescription,
                        IdeaNotification::setDescription
                );

        binder.bindInstanceFields(this);

        save.addClickListener(event -> save());
        delete.addClickListener(event -> delete());
    }

    public void setForm(IdeaNotification ideaNotification) {
        binder.setBean(ideaNotification);

        if (ideaNotification == null) {
            setVisible(false);
        } else {
            setVisible(true);
            subject.focus();
        }
    }

    private void save() {
        IdeaNotification idea = binder.getBean();
        mainView.getDbService().saveIdea(idea);
        mainView.refresh();
        setForm(null);
    }

    private void delete() {
        IdeaNotification idea = binder.getBean();
        mainView.getDbService().deleteIdeaById(idea.getId());
        mainView.refresh();
        setForm(null);
    }
}
