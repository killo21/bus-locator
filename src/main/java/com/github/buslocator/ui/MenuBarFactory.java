package com.github.buslocator.ui;

import com.vaadin.ui.MenuBar;
import com.vaadin.ui.UI;

public class MenuBarFactory {
    private MenuBarFactory() {
    }

    public static MenuBar create(UI parent) {
        MenuBar result = new MenuBar();
        result.addItem("Show All Buses", null, null);
        result.addItem("Add New Bus", null,
                (MenuBar.Command) selectedItem -> parent.setContent(BusInputFormFactory.create(parent)));
        result.addItem("Show All Routes", null, null);
        result.addItem("Add New Route", null, null);
        return result;
    }
}
