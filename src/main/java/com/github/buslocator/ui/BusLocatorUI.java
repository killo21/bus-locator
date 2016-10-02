package com.github.buslocator.ui;

import com.github.buslocator.model.Role;
import com.github.buslocator.repository.BusMovementRepository;
import com.github.buslocator.repository.RouteRepository;
import com.github.buslocator.security.Authenticator;
import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.bridge.SLF4JBridgeHandler;

import java.io.File;
import java.security.MessageDigest;

@Theme("BusLocator")
public class BusLocatorUI extends UI {
  static {
    SLF4JBridgeHandler.install();
  }

  private static final Logger logger = LoggerFactory.getLogger(BusLocatorUI.class);
  private static final BusMovementRepository busMovementRepository = createBusMovementRepository();
  private static final RouteRepository routeRepository = createRouteRepository();
  private static final LoginForm loginForm = new LoginForm();
  private static final Authenticator authenticator = new Authenticator();


  private static BusMovementRepository createBusMovementRepository() {
    try {
      return new BusMovementRepository(new File("./busMovements.json"));
    } catch (Exception e) {
      throw new IllegalStateException("Cannot create bus movement repository", e);
    }
  }

  private static RouteRepository createRouteRepository() {
    try {
      return new RouteRepository(new File("./routes.json"));
    } catch (Exception e) {
      throw new IllegalStateException("Cannot create bus movement repository", e);
    }
  }

  @Override
  protected void init(VaadinRequest request) {
    logger.info("initializing...");
    loginForm.addLoginListener(createLoginListener());
    setContent(loginForm);
  }

  private LoginForm.LoginListener createLoginListener() {
    return new LoginForm.LoginListener() {
      @Override
      public void onLogin(LoginForm.LoginEvent event) {
        Role role = authenticator.getRole(event.getLoginParameter("username"), event.getLoginParameter("password"));
        if (role != null) {
          setContent(createMainLayout(role));
          Notification.show("You are a(n)" + role);
        } else {
          Notification.show("Wrong username or password.");
        }
      }
    };
  }

  private Role getRole(LoginForm.LoginEvent event) {
    if (event.getLoginParameter("username").equals("Dmitry")
        && event.getLoginParameter("password").equals("123456")) {
      return Role.USER;
    } else {
      return null;
    }
  }

  private VerticalLayout createMainLayout(Role user) {
    VerticalLayout mainLayout = new VerticalLayout();
    Panel formPanel = new Panel();
    mainLayout.addComponent(MenuBarFactory.create(formPanel, busMovementRepository, routeRepository));
    mainLayout.addComponent(formPanel);
    return mainLayout;
  }
}
