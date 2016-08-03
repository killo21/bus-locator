package com.github.buslocator.ui;

import com.github.buslocator.model.Bus;
import com.github.buslocator.model.BusMovement;
import com.github.buslocator.model.PassedStop;
import com.github.buslocator.model.Route;
import com.github.buslocator.repository.BusMovementRepository;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.ui.*;

import java.util.ArrayList;

class BusInputFormFactory {

  private BusInputFormFactory() {
  }

  static Panel create(BusMovementRepository repository) {
    Panel result = new Panel();
    VerticalLayout layout = new VerticalLayout();
    result.setContent(layout);

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
        b.setId(System.currentTimeMillis());
        BusMovement bm = new BusMovement(System.currentTimeMillis(),
                b,
                new Route(System.currentTimeMillis(), "", new ArrayList<>()),
                new ArrayList<>());
        repository.save(bm);
        Notification.show(b.toString());
      } catch (Exception e) {
        throw new IllegalStateException("Cannot process event: " + event, e);
      }
    }));

    return result;
  }
}
