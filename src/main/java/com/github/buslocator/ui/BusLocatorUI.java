package com.github.buslocator.ui;

import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.*;
import com.vaadin.annotations.Theme;

@Theme("BusLocator")
public class BusLocatorUI extends UI{
	
	@Override
	protected void init(VaadinRequest request){
		Layout layout = new VerticalLayout();

		layout.addComponent(new Label("Bus"));

		TextField busId = new TextField("Bus ID");
		layout.addComponent(busId);

		TextField driverId = new TextField("Driver");
		layout.addComponent(driverId);

		Button button = new Button("Save");
		button.addClickListener(e -> Notification.show("Saved"));
		layout.addComponent(button);

		setContent(layout);
	}
}
