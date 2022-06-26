package org.design.patterns.factory.model;

import java.awt.Color;

public class Circle {

	private Color color;

	public Circle() {
		this(Color.WHITE);
	}
	
	public Circle(Color color) {
		this.color = color;
	}

	@Override
	public String toString() {
		return "Circle [color=" + color + "]";
	}
}
