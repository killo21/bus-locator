package com.github.buslocator.ui;

import com.github.buslocator.repository.BusMovementRepository;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.Panel;
import com.vaadin.ui.UI;

class MenuBarFactory {
  private MenuBarFactory() {
  }

  static MenuBar create(Panel parent, BusMovementRepository repository) {
    MenuBar result = new MenuBar();
    MenuBar.MenuItem menu = result.addItem("Menu", null, null);
    menu.addItem("Show All Buses", null, (MenuBar.Command) selectedItem -> {
      menu.setText(selectedItem.getText());
      parent.setContent(BusListGridFactory.create(repository));
    });
    menu.addItem("Add New Bus", null,
            (MenuBar.Command) selectedItem -> {
              menu.setText(selectedItem.getText());
              parent.setContent(BusInputFormFactory.create(repository));
            });
    menu.addItem("Show All Routes", null, null);
    menu.addItem("Add New Route", null, null);
    return result;
  }
}
