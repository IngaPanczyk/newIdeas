package com.kodilla.newideas.mainView;

import com.kodilla.newideas.domain.IdeaNotification;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;


public class IdeaForm extends FormLayout {

    private TextField subject = new TextField("Subject");
    private TextField description = new TextField("Description");
    private DatePicker reportingDate =new DatePicker("ReportingDate");
    //private TextField status=new TextField("Status");
   // private TextField ideaExpert=new TextField("IdeaExpert");
   // private TextField user=new TextField("User");

    private Button save = new Button("Save");
    private Button delete = new Button("Delete");

    private Binder<IdeaNotification> binder = new Binder<>(IdeaNotification.class);

    public IdeaForm() {
        HorizontalLayout buttons = new HorizontalLayout(save, delete);
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        add(subject,description, reportingDate, /*status, ideaExpert, user,*/ buttons);
        binder.bindInstanceFields(this);
    }

    public void setForm(IdeaNotification ideaNotification) {
        binder.setBean(ideaNotification);

        if (ideaNotification == null) {
            setVisible(true);
        } else {
            setVisible(true);
            subject.focus();
        }
    }
}
