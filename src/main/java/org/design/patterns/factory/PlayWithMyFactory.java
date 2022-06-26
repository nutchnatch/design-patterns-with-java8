package org.design.patterns.factory;

import org.design.patterns.factory.model.Circle;

import java.awt.*;
import java.util.List;

public class PlayWithMyFactory {

	public static void main(String[] args) {

//		MyFactory<Circle> factory = Circle::new;
//		MyFactory<Circle> factory = MyFactory.createFactory();
//		MyFactory<Circle> factory = MyFactory.createFactory(Color.RED);
		MyFactory<Circle> factory = MyFactory.createFactory(Circle::new, Color.RED);
		MyFactory<Circle> factory2 = MyFactory.createFactory(Circle::new);

		Circle circle = factory.newInstance();
		System.out.println("Circle: " + circle);

		List<Circle> list = factory.create5();
		System.out.println("List: " + list);
	}
}
