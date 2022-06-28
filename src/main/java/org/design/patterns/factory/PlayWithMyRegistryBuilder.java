package org.design.patterns.factory;

import org.design.patterns.factory.model.Rectangle;
import org.design.patterns.factory.model.Shape;
import org.design.patterns.factory.model.Square;
import org.design.patterns.factory.model.Triangle;
import org.design.patterns.factory.registry.Builder;
import org.design.patterns.factory.registry.MyRegistry;

import java.util.function.Consumer;

public class PlayWithMyRegistryBuilder {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {

		/**
		 *This 2 lines of code are just specifying a consumer, so it can be replaced by a consumer
		 * So I can pass this consumer to the registry and the registry can initialise its hasmap
 		 */
//		Builder<Rectangle> builder = null;
//		builder.register("rectangle", Rectangle::new);
//		Consumer<Builder<Rectangle>> consumer1 = builder -> builder.register("rectangle", Rectangle::new);
		Consumer<Builder<Shape>> consumer1 = builder -> builder.register("rectangle", Rectangle::new);
		Consumer<Builder<Shape>> consumer2 = builder -> builder.register("triangle", Triangle::new);
		Consumer<Builder<Shape>> initializer = consumer1.andThen(consumer2);

		MyRegistry registry = MyRegistry.createRegistry(
				initializer,
				s -> {
					throw new IllegalArgumentException("Unknown shape: " + s); }
		);
		Factory<Rectangle> rectangleFactory = (Factory<Rectangle>) registry.buildShapeFactory("rectangle");
		Factory<Triangle> triangleFactory = (Factory<Triangle>) registry.buildShapeFactory("triangle");
		Rectangle rectangle = rectangleFactory.newInstance();
		System.out.println("Rectangle " + rectangle);
		Triangle triangle = triangleFactory.newInstance();
		System.out.println("Triangle " + triangle);

		Factory<Square> squareFactory = (Factory<Square>) registry.buildShapeFactory("Square");
		Square square = squareFactory.newInstance();
	}
}
