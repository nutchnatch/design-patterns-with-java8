package org.design.patterns.visitor;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * Complexity of this pattern is hidden in the api, so the pattern used in the client code can be kept readable and easy
 * @param <R>
 */
public interface MyVisitor<R> {

	R visit(Object o);

//	static <R> X<R> forType(Class<?> type) {
	static <T, R> X<T, R> forType(Class<T> type) {
		return () -> type;
	}

	// So I have to declare the T type
//	interface X<R> {
	interface X<T, R> {
		// Turning it generic
//		Class<?> type();
		Class<T> type();

		// We will replace this interface for an interface where we can call forType()
//		default Consumer<MyVisitorBuilder<R>> execute(Function<Object, R> function) {
//		default Y<R> execute(Function<Object, R> function) {
		default Y<R> execute(Function<T, R> function) {
			return visitorBuilder -> visitorBuilder.register(type(), function);
		}
	}

	interface Y<R> extends Consumer<MyVisitorBuilder<R>> {
		default <T> Z<T, R> forType(Class<T> type) {
			return index -> index == 0 ? this : type;
		}

		// Chaining consumers (mimic the same functionality in Consumer class)
		default <T> Y<R> andThen(Y<R> after) {
			return t -> { this.accept(t); after.accept(t);};
		}
	}

	interface Z<T, R> {

		Object get(int index);

//		default Class<?> type() {
		default Class<T> type() {
			return (Class<T>) get(1);
		}

		default Y<R> previousConsumer() {
			return (Y<R>) get(0);
		}

		// Chaining consumers
		default Y<R> execute(Function<T, R> function) {
			return previousConsumer().andThen(
					visitorBuilder -> visitorBuilder.register(type(), function));
		}
	}

	static <R> MyVisitor<R> of(Consumer<MyVisitorBuilder<R>> consumer) {
		Map<Class<?>, Function<Object, R>> registry = new HashMap<>();
		// This this class is not compiling because it cannot infer the type of function,
		// we need to define it on the old way
//		consumer.accept((type, function) -> registry.put(type, function));
		MyVisitorBuilder<R> visitorBuilder = new MyVisitorBuilder<R>() {

			@Override
			public <T> void register(Class<T> type, Function<T, R> function) {
				// The compose here is used to cast the right type for function
				registry.put(type, function.compose(type::cast));
			}
		};
		consumer.accept(visitorBuilder);
		System.out.println("Registry: " + registry.keySet());
		return o -> registry.get(o.getClass()).apply(o);
	}
}
