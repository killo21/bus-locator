package com.github.buslocator.ui;

import com.github.buslocator.model.BusMovement;
import com.github.buslocator.model.BusStop;
import com.github.buslocator.model.PassedStop;
import com.github.buslocator.model.Route;
import com.github.buslocator.repository.BusMovementRepository;
import com.vaadin.data.Container;
import com.vaadin.data.Item;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.ui.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class UserInterfaceFactory {
  public static Panel create(BusMovementRepository busMovementRepository) {
    Panel result = new Panel();
    Layout layout = new VerticalLayout();

    Container.Indexed container = new IndexedContainer();
    container.addContainerProperty("busMovementId", Long.class, 0L);
    container.addContainerProperty("routeName", String.class, "");
    container.addContainerProperty("totalStops", Integer.class, 0);
    container.addContainerProperty("lastStop", String.class, "");
    container.addContainerProperty("lastStopTimestamp", LocalDateTime.class, null);
    Grid grid = new Grid();
    grid.setContainerDataSource(container);
    grid.setSelectionMode(Grid.SelectionMode.NONE);
    grid.setEditorEnabled(false);

    for (BusMovement it : busMovementRepository.loadAll()) {
      List<PassedStop> passedStops = it.getPassedStops();
      String lastStopName = "Not Reported";
      LocalDateTime lastStopTimestamp = null;
      if (passedStops.size() > 0) {
        PassedStop last = passedStops.get(passedStops.size() - 1);
        lastStopName = last.getBusStop().getName();
        lastStopTimestamp = last.getTimestamp();
      }
      grid.addRow(it.getId(), it.getRoute().getName(), it.getRoute().getBusStops().size(), lastStopName, lastStopTimestamp);
    }

    layout.addComponent(grid);
    result.setContent(layout);
    return result;
  }

}
