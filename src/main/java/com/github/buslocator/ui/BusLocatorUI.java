package com.github.buslocator.ui;

import com.github.buslocator.repository.BusMovementRepository;
import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.Panel;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

import java.io.File;

@Theme("BusLocator")
public class BusLocatorUI extends UI {

  private final BusMovementRepository busMovementRepository = createBusMovementRepository();

  private BusMovementRepository createBusMovementRepository() {
    try {
      return new BusMovementRepository(new File("./busMovement.json"));
    } catch (Exception e) {
      throw new IllegalStateException("Cannot create bus movement repository", e);
    }
  }

  @Override
  protected void init(VaadinRequest request) {
    VerticalLayout mainLayout = new VerticalLayout();
    Panel formPanel = new Panel();
    mainLayout.addComponent(MenuBarFactory.create(formPanel, busMovementRepository));
    mainLayout.addComponent(formPanel);
    setContent(mainLayout);
  }
}
