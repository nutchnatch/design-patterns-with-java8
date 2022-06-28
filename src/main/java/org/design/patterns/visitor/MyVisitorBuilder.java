package org.design.patterns.visitor;

import java.util.function.Function;

/**
 * I could define the T directly on MyVisitorBuilder class,
 * but this would mean that all consumers should be of this type, which is not the case here
 * because of the andThen(), we want to chain two different type os consumers
 * @param <R>
 */
//public interface MyVisitorBuilder<T, R> {
public interface MyVisitorBuilder<R> {
//	void register(Class<?> type, Function<Object, R> function);
	<T> void register(Class<T> type, Function<T, R> function);
}