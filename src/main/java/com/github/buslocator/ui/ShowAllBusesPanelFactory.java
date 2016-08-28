package com.github.buslocator.ui;

import com.github.buslocator.model.BusMovement;
import com.github.buslocator.repository.BusMovementRepository;
import com.vaadin.data.Container;
import com.vaadin.data.Item;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.ui.*;

import java.io.IOException;

class ShowAllBusesPanelFactory {
  private ShowAllBusesPanelFactory() {
  }

  static Panel create(BusMovementRepository repository) {
    Panel result = new Panel();
    Layout layout = new VerticalLayout();

    Container.Indexed container = new IndexedContainer();
    container.addContainerProperty("busMovementId", Long.class, 0L);
    container.addContainerProperty("busId", Long.class, 0L);
    container.addContainerProperty("busName", String.class, "");
    Grid grid = new Grid();
    grid.setContainerDataSource(container);
    grid.setSelectionMode(Grid.SelectionMode.MULTI);

    final Grid.MultiSelectionModel selection = (Grid.MultiSelectionModel) grid.getSelectionModel();


    Button delSelected = new Button("Delete Selected Bus", e -> {
      for (Object id: selection.getSelectedRows()) {
        Item item = container.getItem(id);
        Long busMovementId = (Long) item.getItemProperty("busMovementId").getValue();
        container.removeItem(id);
        try {
          repository.remove(busMovementId);
        } catch (IOException e1) {
          throw new IllegalStateException("Cannot remove Bus Movement: " + busMovementId);
        }
      }
      grid.getSelectionModel().reset();
    });

    for (BusMovement it : repository.loadAll()) {
      grid.addRow(it.getId(), it.getBus().getId(), it.getBus().getName());
    }

    layout.addComponent(grid);
    result.setContent(layout);
    layout.addComponent(delSelected);
    return result;
  }
}
