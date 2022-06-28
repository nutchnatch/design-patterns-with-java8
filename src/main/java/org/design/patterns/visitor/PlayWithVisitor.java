package org.design.patterns.visitor;

import org.design.patterns.visitor.model.Body;
import org.design.patterns.visitor.model.Car;
import org.design.patterns.visitor.model.Engine;

import java.util.function.Consumer;

public class PlayWithVisitor {

	public static void main(String[] args) {

        Car car = new Car();
        Engine engine = new Engine();
        Body body = new Body();

        Consumer<VisitorBuilder<String>> consumer = 
        		Visitor.<Car, String>forType(Car.class).execute((Car c) -> "Visiting car: " + c)
        		       .forType(Engine.class).execute((Engine e) -> "Visiting engine: " + e)
        		       .forType(Body.class).execute((Body b) -> "Visiting body: " + b);
        
        Visitor<String> visitor = Visitor.of(consumer);
		
        String visitedEngine = visitor.visit(engine);
        System.out.println("Engine: "+ visitedEngine);

        String visitedCar = visitor.visit(car);
        System.out.println("Car: "+ visitedCar);
        
        String visitedBody = visitor.visit(body);
        System.out.println("Body: " + visitedBody);
	}
}
