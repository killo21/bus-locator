package com.github.buslocator.ui;

import com.github.buslocator.repository.BusMovementRepository;
import com.github.buslocator.repository.RouteRepository;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.Panel;

class MenuBarFactory {
  private MenuBarFactory() {
  }

  static MenuBar create(Panel parent, BusMovementRepository busMovementRepository, RouteRepository routeRepository) {
    MenuBar result = new MenuBar();
    MenuBar.MenuItem menu = result.addItem("Menu", null, null);
    menu.addItem("Show All Buses", null, (MenuBar.Command) selectedItem -> {
      menu.setText(selectedItem.getText());
      parent.setContent(ShowAllBusesPanelFactory.create(busMovementRepository));
    });
    menu.addItem("Add New Bus", null, (MenuBar.Command) selectedItem -> {
      menu.setText(selectedItem.getText());
      parent.setContent(AddNewBusPanelFactory.create(busMovementRepository));
    });
    menu.addItem("Show All Routes", null, (MenuBar.Command) selectedItem -> {
      menu.setText(selectedItem.getText());
      parent.setContent(ShowAllRoutesPanelFactory.create(routeRepository));
    });
    menu.addItem("Add New Route", null, (MenuBar.Command) selectedItem -> {
      menu.setText(selectedItem.getText());
      parent.setContent(AddNewRoutePanelFactory.create(routeRepository));
    });
    return result;
  }
}
