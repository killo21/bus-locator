package com.github.buslocator.ui;

import com.github.buslocator.model.BusMovement;
import com.github.buslocator.repository.BusMovementRepository;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Layout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

class ShowAllBusesPanelFactory {
  private ShowAllBusesPanelFactory() {
  }

  static Panel create(BusMovementRepository repository) {
    Panel result = new Panel();
    Layout layout = new VerticalLayout();

    Grid grid = new Grid();
    grid.addColumn("Bus ID", Long.class);
    grid.addColumn("Bus Name", String.class);

    for (BusMovement it : repository.loadAll()) {
      grid.addRow(it.getBus().getId(), it.getBus().getName());
    }

    layout.addComponent(grid);
    result.setContent(layout);

    return result;
  }
}
