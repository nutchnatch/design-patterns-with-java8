package org.design.patterns.factory;

import org.design.patterns.factory.model.Rectangle;
import org.design.patterns.factory.model.Shape;
import org.design.patterns.factory.model.Square;
import org.design.patterns.factory.model.Triangle;
import org.design.patterns.factory.registry.Builder;
import org.design.patterns.factory.registry.Registry;

import java.util.function.Consumer;

public class PlayWithRegistryBuilder {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {

		Consumer<Builder<Shape>> consumer1 =
				builder -> builder.register("rectangle",  Rectangle::new);
		Consumer<Builder<Shape>> consumer2 = 
				builder -> builder.register("triangle",  Triangle::new);
		
		Consumer<Builder<Shape>> initializer = consumer1.andThen(consumer2);
				
		Registry<Shape> registry = Registry.createRegistry(
				initializer, s -> { throw new IllegalArgumentException("Unknown shape: " + s);});
		
		Factory<Rectangle> buildRectangleFactory = 
				(Factory<Rectangle>)registry.buildShapeFactory("rectangle");
		
		Rectangle rectangle = buildRectangleFactory.newInstance();
		System.out.println("Rectangle = " + rectangle);
		
		Factory<Triangle> buildTriangleFactory = 
				(Factory<Triangle>)registry.buildShapeFactory("triangle");
		
		Triangle triangle = buildTriangleFactory.newInstance();
		System.out.println("Triangle = " + triangle);
		
		Factory<Square> buildSquareFactory =
				(Factory<Square>)registry.buildShapeFactory("square");
		Square square = buildSquareFactory.newInstance();
	}
}
