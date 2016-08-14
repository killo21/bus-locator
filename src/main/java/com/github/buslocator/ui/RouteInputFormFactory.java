package com.github.buslocator.ui;

import com.github.buslocator.model.Bus;
import com.github.buslocator.model.BusStop;
import com.github.buslocator.model.Route;
import com.github.buslocator.repository.RouteRepository;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.ui.*;
import com.vaadin.ui.renderers.NumberRenderer;

import java.util.ArrayList;


public class RouteInputFormFactory {
  private RouteInputFormFactory() {
  }

  static Panel create(RouteRepository repository) {
    Panel result = new Panel();
    VerticalLayout layout = new VerticalLayout();
    result.setContent(layout);

    Route route = new Route();
    route.setName("");

    final BeanFieldGroup<Route> binder = new BeanFieldGroup<>(Route.class);
    binder.setItemDataSource(route);
    layout.addComponent(binder.buildAndBind("Name", "name"));
    layout.addComponent(RouteGridFactory.create(repository));

    binder.setBuffered(true);
    layout.addComponent(new Button("OK", (Button.ClickListener) event -> {
      try {
        binder.commit();
        Route r = binder.getItemDataSource().getBean();
        r.setId(System.currentTimeMillis());
        Route ro = new Route(System.currentTimeMillis(), "", new ArrayList<BusStop>());
        repository.save(ro);
        Notification.show(r.toString());
      } catch (Exception e) {
        throw new IllegalStateException("Cannot process event: " + event, e);
      }
    }));
    return result;
  }
}
