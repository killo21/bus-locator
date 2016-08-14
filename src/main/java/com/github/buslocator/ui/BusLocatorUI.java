package com.github.buslocator.ui;

import com.github.buslocator.repository.BusMovementRepository;
import com.github.buslocator.repository.RouteRepository;
import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.Panel;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

import java.io.File;

@Theme("BusLocator")
public class BusLocatorUI extends UI {

  private final BusMovementRepository busMovementRepository = createBusMovementRepository();
  private final RouteRepository routeRepository = createRouteRepository();

  private BusMovementRepository createBusMovementRepository() {
    try {
      return new BusMovementRepository(new File("./busMovements.json"));
    } catch (Exception e) {
      throw new IllegalStateException("Cannot create bus movement repository", e);
    }
  }

  private RouteRepository createRouteRepository() {
    try {
      return new RouteRepository(new File("./routes.json"));
    } catch (Exception e) {
      throw new IllegalStateException("Cannot create bus movement repository", e);
    }
  }

  @Override
  protected void init(VaadinRequest request) {
    VerticalLayout mainLayout = new VerticalLayout();
    Panel formPanel = new Panel();
    mainLayout.addComponent(MenuBarFactory.create(formPanel, busMovementRepository, routeRepository));
    mainLayout.addComponent(formPanel);
    setContent(mainLayout);
  }
}
