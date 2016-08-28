package com.github.buslocator.ui;

import com.github.buslocator.model.Route;
import com.github.buslocator.repository.RouteRepository;
import com.vaadin.ui.*;

class ShowAllRoutesPanelFactory {
  static Component create(RouteRepository routeRepository) {
    Panel result = new Panel();
    Layout layout = new VerticalLayout();

    Grid grid = new Grid();
    grid.addColumn("Route ID", Long.class);
    grid.addColumn("Route Name", String.class);
    grid.addColumn("Stops", Integer.class);

    for (Route it : routeRepository.loadAll()) {
      grid.addRow(it.getId(), it.getName(), it.getBusStops().size());
    }

    layout.addComponent(grid);
    result.setContent(layout);

    return result;
  }
}
