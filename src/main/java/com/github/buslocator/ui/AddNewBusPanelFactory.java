package com.github.buslocator.ui;

import com.github.buslocator.model.Bus;
import com.github.buslocator.model.BusMovement;
import com.github.buslocator.model.PassedStop;
import com.github.buslocator.model.Route;
import com.github.buslocator.repository.BusMovementRepository;
import com.vaadin.data.Property;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.ObjectProperty;
import com.vaadin.ui.*;

import java.util.ArrayList;

class AddNewBusPanelFactory {

  private AddNewBusPanelFactory() {
  }

  static Panel create(BusMovementRepository repository) {
    Panel result = new Panel();
    VerticalLayout layout = new VerticalLayout();
    result.setContent(layout);

    Property<Long> busId = new ObjectProperty<>(0L);
    Property<String> busName = new ObjectProperty<>("");

    layout.addComponent(new TextField("Bus ID", busId));
    layout.addComponent(new TextField("Bus Name", busName));


    layout.addComponent(new Button("OK", (Button.ClickListener) event -> {
      try {
        Bus b = new Bus(busId.getValue(), busName.getValue());
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
