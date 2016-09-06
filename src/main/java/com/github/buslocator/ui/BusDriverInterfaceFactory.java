package com.github.buslocator.ui;

import com.github.buslocator.model.BusMovement;
import com.github.buslocator.model.BusStop;
import com.github.buslocator.model.PassedStop;
import com.github.buslocator.model.Route;
import com.github.buslocator.repository.BusMovementRepository;
import com.github.buslocator.repository.RouteRepository;
import com.vaadin.data.Container;
import com.vaadin.data.Item;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.ui.*;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
      result.setContent(createBusDriverDialog(route, busMovementRepository));
    });

    for (Route it : routeRepository.loadAll()) {
      grid.addRow(it.getId(), it.getName(), it.getBusStops().size());
    }

    layout.addComponent(grid);
    result.setContent(layout);
    return result;
  }

  private static Layout createBusDriverDialog(Route route, BusMovementRepository busMovementRepository)  {
    BusMovement busMovement = new BusMovement(System.currentTimeMillis(), route, new ArrayList<>());
    try {
      busMovementRepository.save(busMovement);
    } catch (IOException e) {
      throw new IllegalStateException("Cannot save busMovement" + busMovement);
    }

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
    for (BusStop busStop : route.getBusStops()) {
      grid.addRow(busStop.getId(), busStop.getName(), isPassed(busMovement.getPassedStops(), busStop));
    }

    Button next = new Button("Next");
    Button back = new Button("Back");

    next.addClickListener(new Button.ClickListener() {
      @Override
      public void buttonClick(Button.ClickEvent event) {
        BusMovement updatedBusMovement = reportNextBusStop(busMovement.getId(), busMovementRepository);
        Notification.show("Next button was pressed");
        container.removeAllItems();
        for (BusStop busStop : updatedBusMovement.getRoute().getBusStops()) {
          grid.addRow(busStop.getId(), busStop.getName(), isPassed(updatedBusMovement.getPassedStops(), busStop));
        }
      }
    });

    back.addClickListener(new Button.ClickListener() {
      @Override
      public void buttonClick(Button.ClickEvent event) {
        BusMovement updatedBusMovement = reportPreviousStop(busMovement.getId(), busMovementRepository);
        Notification.show("Back button was pressed");
        container.removeAllItems();
        for (BusStop busStop : updatedBusMovement.getRoute().getBusStops()) {
          grid.addRow(busStop.getId(), busStop.getName(), isPassed(updatedBusMovement.getPassedStops(), busStop));
        }
      }
    });
    layout.addComponent(grid);
    layout.addComponent(back);
    layout.addComponent(next);

    return layout;
  }

  private static BusMovement reportNextBusStop(Long busMovementId, BusMovementRepository busMovementRepository) {
    BusMovement busMovement = busMovementRepository.load(busMovementId);
    for (BusStop busStop: busMovement.getRoute().getBusStops()) {
      if (!isPassed(busMovement.getPassedStops(), busStop)){
        List<PassedStop> updatedPassedStops = new ArrayList<>(busMovement.getPassedStops());
        updatedPassedStops.add(new PassedStop(busStop.getId(), LocalDateTime.now(), busStop));
        BusMovement updatedBusMovement = new BusMovement(busMovement.getId(), busMovement.getRoute(), updatedPassedStops);
        try {
          busMovementRepository.save(updatedBusMovement);
        } catch (IOException e) {
          throw new IllegalStateException("Cannot save busMovement" + updatedBusMovement);
        }
        return updatedBusMovement;
      }
    }
    return busMovement;
  }

  private static BusMovement reportPreviousStop(Long busMovementId, BusMovementRepository busMovementRepository) {
    BusMovement busMovement = busMovementRepository.load(busMovementId);
    ArrayList<PassedStop> updatedPassedStops = new ArrayList<>(busMovement.getPassedStops());
    final int passedStopsNum = updatedPassedStops.size();
    if (passedStopsNum > 0) {
      updatedPassedStops.remove(passedStopsNum - 1);
      BusMovement updatedBusMovement = new BusMovement(busMovement.getId(), busMovement.getRoute(), updatedPassedStops);
      try {
        busMovementRepository.save(updatedBusMovement);
      } catch (IOException e) {
        throw new IllegalStateException("Cannot save busMovement" + updatedBusMovement);
      }
      return updatedBusMovement;
    }
    return busMovement;
  }

  private static boolean isPassed(List<PassedStop> passedStops, BusStop busStop) {
    Set<Long> passedStopIds = new HashSet<>();
    for (PassedStop passedStop: passedStops) {
      passedStopIds.add(passedStop.getBusStop().getId());
    }
    return passedStopIds.contains(busStop.getId());
  }
}
