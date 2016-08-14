package com.github.buslocator.ui;

import com.github.buslocator.model.Bus;
import com.github.buslocator.model.BusMovement;
import com.github.buslocator.model.Route;
import com.github.buslocator.repository.RouteRepository;
import com.vaadin.ui.*;

import java.util.ArrayList;

public class RouteGridFactory {
  private RouteGridFactory() {
  }

  static Panel create(RouteRepository repository) {
    Panel result = new Panel();
    Layout layout = new VerticalLayout();
    result.setContent(layout);

    Grid grid = new Grid();
    grid.setCaption("Bus Stops");
    grid.setSizeFull();
    grid.setEditorEnabled(true);
    grid.setSelectionMode(Grid.SelectionMode.NONE);
    grid.addColumn("Bus ID", Long.class);
    grid.addColumn("Name", String.class);
    layout.addComponent(grid);

    layout.addComponent(new Button("Add", (Button.ClickListener) event -> {
      try {
        grid.addRow(0L, "");
      } catch (Exception e) {
        throw new IllegalStateException("Cannot process event: " + event, e);
      }
    }));

    return result;
  }
}
