package com.github.buslocator.ui;

import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import com.vaadin.server.VaadinServlet;

@WebServlet(
    asyncSupported=false,
    urlPatterns={"/*","/VAADIN/*"},
    initParams={
        @WebInitParam(name="ui", value="com.github.buslocator.ui.BusLocatorUI")
    })
public class BusLocatorServlet extends VaadinServlet { }
