package org.design.patterns.factory;

import org.design.patterns.factory.model.Circle;

import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public interface MyFactory<T> extends Supplier<T> {

    static MyFactory<Circle> createFactory() {
        return () -> new Circle();
    }

//    static MyFactory<Circle> createFactory(Function<Color, Circle> constructor,Color red) {
    static <P, T> MyFactory<T> createFactory(Function<P, T> constructor, P red) {
        // Partial application concept - allows to transform a function into a supplier, by fixing the parameters received
//        Function<Color, Circle> constructor = c -> new Circle(c);
        return () -> constructor.apply(red);
    }

    /**
     * To create a singleton and return always the same instance, we can do the following
     * The singleton id hidden in a static method, so no one can access it, even using reflection, because it is a local
     * variable, it is not a field.
     * This way of implementing a singleton can avoid a lot of problems we can find using the classic ways.
     * @param supplier
     * @param <T>
     * @return
     */
    static <T> Factory<T> createFactory2(Supplier<T> supplier) {
        T singleton = supplier.get();
        return () -> singleton;
    }

    static <T> MyFactory<T> createFactory(Supplier<T> supplier) {
        return () -> supplier.get();
    }

    default T newInstance() {
        return this.get();
    }

    default List<T> create5() {
        return IntStream.range(0, 5)
                .mapToObj(index -> newInstance())
                .collect(Collectors.toList());
    }
}
