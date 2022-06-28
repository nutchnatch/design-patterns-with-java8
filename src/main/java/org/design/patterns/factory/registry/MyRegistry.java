package org.design.patterns.factory.registry;

import org.design.patterns.factory.Factory;
import org.design.patterns.factory.model.Rectangle;
import org.design.patterns.factory.model.Shape;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Stream;

/**
 * Registry can be seen as a type of factory, as it is also used to build other objects
 * Th difference  is that fo the registry we pass a key to build the right object
 * If we transform this class into an interface, it can be implemented using a lambda expression
 */
//public class MyRegistry<T> {
public interface MyRegistry<T> {

	/**
	 * Can be implemented by a lambda expression, receiving a share and returning a Factory
	 * This registry is a functional interface, implemented in a lambda expression
	 * which is integrated inside the api code
	 * The client code does not see that this is a lambda expression
	 * Give a very clean and compact code, and also very safe because the registry (map) is absolutely hidden from the outside
	 * We cannot get a reference to that map, because it is a local variable (it is no a field)
	 * And there is absolutely no way to get a reference to it, even with reflection from the outside
	 * This code entirely build on lambda expression, is very safe, very robust and very performant
	 * @param shape
	 * @return
	 */
	Factory<? extends T> buildShapeFactory(String shape);
	
	static <T> MyRegistry<T> createRegistry(Consumer<Builder<T>> consumer, Function<String, Factory<T>> errorFunction) {
		Map<String, Factory<T>> map = new HashMap<>();
		Builder<T> builder = (label, factory) -> map.put(label, factory);
		consumer.accept(builder);

		/**
		 * This code can return a null pointer exception, whenever there is no expected shape on the map
		 * We can evenly pass the shape as a parameter to the map, using computeIfAbsent method which receives a function
		 * So this way, we can use this function passed as parameter in order to handle the error
 		 */
//		return shape -> map.get(shape);
//		return shape -> map.getOrDefault(shape, () -> {
//			throw new IllegalArgumentException("Unknown shape: " + shape);
//		});
		return shape -> map.computeIfAbsent(shape, errorFunction);
	}
}
