package com.cardboardcode.example;

import processing.core.PApplet;

/**
 * Minimal example that draws a moving line
 */
public class DrawExample extends PApplet {

	private int y = 100;

	@Override
	public void settings() {
		size(1024, 768);
	}

	@Override
	public void setup() {
		stroke(255);
	}

	@Override
	public void draw() {

		// Draw background
		background(0);
		
		// Move line up (or wrap around the screen)
		y = y - 1;
		if (y < 0) {
			y = height;
		}
		
		// Draw line
		line(0, y, width, y);

	}

	public static void main(String[] args) {

		// Start the application
		PApplet.main(DrawExample.class.getName());

	}

}
