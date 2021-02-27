package com.kodilla.newideas.mainView;

import com.kodilla.newideas.domain.IdeaExpert;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;

public class ExpertForm extends VerticalLayout {

    MainView mainView;

    TextField  expertName = new TextField("Expert name");
    private Button save = new Button("Save");
    private Binder<IdeaExpert> binder = new Binder<>(IdeaExpert.class);

    public ExpertForm(MainView mainView) {
        this.mainView = mainView;

        HorizontalLayout buttons = new HorizontalLayout(save);
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        add(expertName, buttons);

        binder.forField(expertName)
                .bind(
                        IdeaExpert::getExpertName,
                        IdeaExpert::setExpertName
                );

        binder.bindInstanceFields(this);

        save.addClickListener(event -> save());

    }

    public void setFormExpert(IdeaExpert ideaExpert) {
        binder.setBean(ideaExpert);

        if (ideaExpert == null) {
            setVisible(false);
        } else {
            setVisible(true);
            //ideaExpert.focus();
        }
    }

    private void save() {
        IdeaExpert expert = binder.getBean();
        mainView.getDbService().saveExpert(expert);
        mainView.refresh();
        setFormExpert(null);
    }
}
