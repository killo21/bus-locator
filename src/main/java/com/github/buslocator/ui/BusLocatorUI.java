package com.github.buslocator.ui;

import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.UI;

@Theme("BusLocator")
public class BusLocatorUI extends UI{
	
	@Override
	protected void init(VaadinRequest request){
        setContent(MenuBarFactory.create(this));
	}
}
