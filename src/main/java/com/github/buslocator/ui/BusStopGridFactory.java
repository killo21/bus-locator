package com.github.buslocator.ui;

import com.vaadin.data.Container;
import com.vaadin.data.Item;
import com.vaadin.ui.*;

class BusStopGridFactory {
  private BusStopGridFactory() {
  }

  static Panel create(Container.Indexed container) {
    Panel result = new Panel();
    Layout layout = new VerticalLayout();
    result.setContent(layout);

    Grid grid = new Grid();
    grid.setSelectionMode(Grid.SelectionMode.MULTI);
    grid.setContainerDataSource(container);
    grid.setCaption("Bus Stops");
    grid.setSizeFull();
    grid.setEditorEnabled(true);
    layout.addComponent(grid);

    final Grid.MultiSelectionModel selection = (Grid.MultiSelectionModel) grid.getSelectionModel();

    layout.addComponent(new Button("Add Bus Stop", (Button.ClickListener) event -> {
      container.addItem();
    }));

    Button delSelected = new Button("Delete Selected Bus Stops", e -> {
      selection.getSelectedRows().forEach(container::removeItem);
      grid.getSelectionModel().reset();
    });
    layout.addComponent(delSelected);

    return result;
  }
}
