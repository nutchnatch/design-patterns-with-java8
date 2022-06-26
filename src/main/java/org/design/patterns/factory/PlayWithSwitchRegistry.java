package org.design.patterns.factory;

import org.design.patterns.factory.model.Rectangle;
import org.design.patterns.factory.registry.SwitchRegistry;

public class PlayWithSwitchRegistry {
	
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {

		SwitchRegistry registry = new SwitchRegistry();
		
		Factory<Rectangle> rectangleFactory =
				(Factory<Rectangle>)registry.buildShapeFactory("rectangle");
		System.out.println("Rectangle : " + rectangleFactory.newInstance());
	}
}
