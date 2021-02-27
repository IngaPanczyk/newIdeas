package com.kodilla.newideas.mainView;

import com.kodilla.newideas.domain.User;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;

public class UserForm extends VerticalLayout {

    MainView mainView;

    TextField userName = new TextField("User name");
    private Button save = new Button("Save");
    private Binder<User> binder = new Binder<>(User.class);

    public UserForm(MainView mainView) {
        this.mainView = mainView;

        HorizontalLayout buttons = new HorizontalLayout(save);
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        add(userName, buttons);

        binder.forField(userName)
                .bind(
                        User::getUsername,
                        User::setUsername
                );

        binder.bindInstanceFields(this);

        save.addClickListener(event -> save());

    }

    public void setFormUser(User user) {
        binder.setBean(user);

        if (user == null) {
            setVisible(false);
        } else {
            setVisible(true);
        }
    }

    private void save() {
        User user = binder.getBean();
        mainView.getDbService().saveUser(user);
        mainView.refresh();
        setFormUser(null);
    }
}
