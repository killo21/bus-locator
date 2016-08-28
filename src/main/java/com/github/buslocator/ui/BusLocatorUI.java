package com.github.buslocator.ui;

import com.github.buslocator.repository.BusMovementRepository;
import com.github.buslocator.repository.RouteRepository;
import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.Panel;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.bridge.SLF4JBridgeHandler;

import java.io.File;

@Theme("BusLocator")
public class BusLocatorUI extends UI {
  static {
    SLF4JBridgeHandler.install();
  }

  private final Logger logger = LoggerFactory.getLogger(this.getClass());
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
    logger.info("initializing...");
    VerticalLayout mainLayout = new VerticalLayout();
    Panel formPanel = new Panel();
    mainLayout.addComponent(MenuBarFactory.create(formPanel, busMovementRepository, routeRepository));
    mainLayout.addComponent(formPanel);
    setContent(mainLayout);
  }
}
