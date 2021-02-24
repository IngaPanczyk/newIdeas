package com.kodilla.newideas.mainView;

import com.kodilla.newideas.controller.IdeaController;
import com.kodilla.newideas.domain.IdeaExpert;
import com.kodilla.newideas.domain.IdeaNotification;
import com.kodilla.newideas.domain.IdeaStatus;
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


public class IdeaForm extends FormLayout {
    @PropertyId("subject")
    private TextField subject = new TextField("Subject");
    @PropertyId("description")
    private TextField description = new TextField("Description");
    @PropertyId("reportingDate")
    private DatePicker reportingDate = new DatePicker("ReportingDate");
    // @PropertyId("status")
    //private ComboBox <IdeaStatus> status=new ComboBox<>("Status");
    @PropertyId("ideaExpert")
    private ComboBox<IdeaExpert> ideaExpert = new ComboBox<>("IdeaExpert");
    //private TextField user=new TextField("User");

    private Button save = new Button("Save");
    private Button delete = new Button("Delete");

    @Autowired
    IdeaController ideaController;




    private Binder<IdeaNotification> binder = new Binder<>(IdeaNotification.class);

    public IdeaForm() {

        //ideaExpert.setItems(dbService.getAllExperts());
        HorizontalLayout buttons = new HorizontalLayout(save, delete);
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        add(subject, description, reportingDate,/*status*/ideaExpert, /*user,*/ buttons);
        binder.bindInstanceFields(this);
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

    public void setValuesForExperts(DbService dbService){
        ideaExpert.setItems(dbService.getAllExperts());
    }
}
