package org.design.patterns.factory.registry;

import org.design.patterns.factory.Factory;
import org.design.patterns.factory.model.Rectangle;
import org.design.patterns.factory.model.Shape;
import org.design.patterns.factory.model.Square;
import org.design.patterns.factory.model.Triangle;

public class SwitchRegistry {

	public Factory<? extends Shape> buildShapeFactory(String shape) {
		
		switch(shape) {
			case "square" : return () -> new Square();
			case "triangle" : return () -> new Triangle();
			case "rectangle" : return () -> new Rectangle();
			default:
				throw new IllegalArgumentException("Unknown shape: " + shape);
		}
	}
}
