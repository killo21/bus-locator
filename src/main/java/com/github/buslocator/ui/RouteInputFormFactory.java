package com.github.buslocator.ui;

import com.github.buslocator.model.BusStop;
import com.github.buslocator.model.Route;
import com.github.buslocator.repository.RouteRepository;
import com.vaadin.data.Container;
import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.data.util.ObjectProperty;
import com.vaadin.ui.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class RouteInputFormFactory {
  private RouteInputFormFactory() {
  }

  static Panel create(RouteRepository repository) {
    Panel result = new Panel();
    VerticalLayout layout = new VerticalLayout();
    result.setContent(layout);

    Property<Long> routeId = new ObjectProperty<>(0L);
    Property<String> routeName = new ObjectProperty<>("");

    layout.addComponent(new TextField("Route ID", routeId));
    layout.addComponent(new TextField("Route Name", routeName));

    Container.Indexed container = new IndexedContainer();
    container.addContainerProperty("busStopId", Long.class, 0L);
    container.addContainerProperty("busStopName", String.class, "");

    layout.addComponent(BusStopGridFactory.create(container));

    layout.addComponent(new Button("OK", (Button.ClickListener) event -> {
      List<BusStop> stops = new ArrayList<>();
      for (Object id : container.getItemIds()) {
        Item item = container.getItem(id);
        long busStopId = (Long) item.getItemProperty("busStopId").getValue();
        final String busStopName = (String) item.getItemProperty("busStopName").getValue();
        BusStop stop = new BusStop(busStopId, busStopName);
        stops.add(stop);
      }
      Route route = new Route(routeId.getValue(), routeName.getValue(), stops);
      repository.save(route);
      Notification.show("Added a new route: " + route);
    }));
    return result;
  }
}
