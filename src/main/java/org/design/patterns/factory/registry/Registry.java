package org.design.patterns.factory.registry;

import org.design.patterns.factory.Factory;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * Registry can be seen as a type of factory, as it is also used to build other objects
 * Th difference  is that fo the registry we pass a key to build the right object
 * @param <T>
 */
public interface Registry<T> {

	Factory<? extends T> buildShapeFactory(String shape);
	
	public static <T> Registry<T> createRegistry(
			Consumer<Builder<T>> consumer, Function<String, Factory<T>> errorFunction) {
		
		Map<String, Factory<T>> map = new HashMap<>();
		Builder<T> builder = (label, factory) -> map.put(label, factory);
		consumer.accept(builder);
		
		return shape -> map.computeIfAbsent(shape, errorFunction);
	}
}
