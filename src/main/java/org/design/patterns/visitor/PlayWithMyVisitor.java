package org.design.patterns.visitor;
;
import org.design.patterns.visitor.model.Body;
import org.design.patterns.visitor.model.Car;
import org.design.patterns.visitor.model.Engine;

import java.util.function.Consumer;

public class PlayWithMyVisitor {

	public static void main(String[] args) {

        Car car = new Car();
        Engine engine = new Engine();
        Body body = new Body();

        /**
        * Creating dynamic visitors from outside existing class, without modifying the hierarchy and without adding the
         * accept() methods that may be costly to add
        */
        Consumer<MyVisitorBuilder<String>> consumer = MyVisitor
                .<Car, String>forType(Car.class)
//                .execute(c -> "Visiting car: " + c)
                // We can now cast our class which will be visited
                .execute((Car c) -> "Visiting car: " + c)
                .forType(Engine.class).execute((Engine e) -> "Visiting engine: " + e)
                .forType(Body.class).execute((Body b) -> "Visiting body: " + b);
        MyVisitor<String> visitor = MyVisitor.of(consumer);

        String visitedCar = visitor.visit(car);
        System.out.println("Car: " + visitedCar);
        String visitedEngine = visitor.visit(engine);
        System.out.println("Engine: " + visitedEngine);
        String visitedBody = visitor.visit(body);
        System.out.println("Engine: " + visitedBody);
	}
}
