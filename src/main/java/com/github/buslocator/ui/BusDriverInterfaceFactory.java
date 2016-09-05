package com.github.buslocator.ui;

import com.github.buslocator.model.BusStop;
import com.github.buslocator.model.Route;
import com.github.buslocator.repository.BusMovementRepository;
import com.github.buslocator.repository.RouteRepository;
import com.vaadin.data.Container;
import com.vaadin.data.Item;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.ui.*;

public class BusDriverInterfaceFactory {
  public static Panel create(BusMovementRepository busMovementRepository, RouteRepository routeRepository) {
    Panel result = new Panel();
    Layout layout = new VerticalLayout();

    Container.Indexed container = new IndexedContainer();
    container.addContainerProperty("routeId", Long.class, 0L);
    container.addContainerProperty("routeName", String.class, "");
    container.addContainerProperty("stopNumber", Integer.class, 0);
    Grid grid = new Grid();
    grid.setContainerDataSource(container);
    grid.setSelectionMode(Grid.SelectionMode.NONE);
    grid.setEditorEnabled(false);
    grid.addItemClickListener((ItemClickEvent.ItemClickListener) event -> {
      Item item = event.getItem();
      Long routeId = (Long) item.getItemProperty("routeId").getValue();
      Route route = routeRepository.load(routeId);
      result.setContent(createBusDriverDialog(route));
    });

    for (Route it : routeRepository.loadAll()) {
      grid.addRow(it.getId(), it.getName(), it.getBusStops().size());
    }

    layout.addComponent(grid);
    result.setContent(layout);
    return result;
  }

  private static Layout createBusDriverDialog(Route route) {
    Layout layout = new VerticalLayout();
    layout.addComponent(new Label("" + route.getId() + " - " + route.getName()));

    Container.Indexed container = new IndexedContainer();
    container.addContainerProperty("busStopId", Long.class, 0L);
    container.addContainerProperty("busStopName", String.class, "");
    container.addContainerProperty("isPassed", Boolean.class, false);
    Grid grid = new Grid();
    grid.setContainerDataSource(container);
    grid.setSelectionMode(Grid.SelectionMode.NONE);
    grid.setEditorEnabled(false);
    for (BusStop busStop:  route.getBusStops()) {
      grid.addRow(busStop.getId(), busStop.getName(), false);
    }
    layout.addComponent(grid);

    return layout;
  }
}
