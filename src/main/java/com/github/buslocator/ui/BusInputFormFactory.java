package com.github.buslocator.ui;

import com.github.buslocator.model.Bus;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;

public class BusInputFormFactory {

    private BusInputFormFactory() {
    }

    public static FormLayout create(UI parent) {
        FormLayout layout = new FormLayout();
        layout.setCaption("Bus Input Form"); // TODO: figure out why it does not get
        layout.addComponent(MenuBarFactory.create(parent));

        Bus bus = new Bus();
        bus.setName("");

        final BeanFieldGroup<Bus> binder = new BeanFieldGroup<>(Bus.class);
        binder.setItemDataSource(bus);
        layout.addComponent(binder.buildAndBind("Name", "name"));

        binder.setBuffered(true);
        layout.addComponent(new Button("OK", (Button.ClickListener) event -> {
            try {
                binder.commit();
                Bus b = binder.getItemDataSource().getBean();
                b.setId(1100);
                Notification.show(b.toString());
            } catch (FieldGroup.CommitException e) {
                throw new IllegalStateException("Cannot process event: " + event, e);
            }
        }));

        return layout;
    }
}
