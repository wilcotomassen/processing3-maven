package com.cardboardcode.example;

import com.cardboardcode.util.NativeLibraryPath;

import processing.core.PApplet;
import processing.video.Movie;

/**
 * Minimal example for playing video
 */
public class VideoExample extends PApplet {
	
	private Movie movie;
	
	@Override
	public void settings() {
		size(1024, 768);
	}

	@Override
	public void setup() {
		movie = new Movie(this, "myMovie.mp4");
		movie.play();
	}
	
	@Override
	public void draw() {
		if (movie != null) {
			image(movie, 0, 0);
		}
	}
	
	public void movieEvent(Movie m) {
		m.read();
	}

	public static void main(String[] args) {
		
		// Set the native library paths that are required for  
		// the video library to work
		NativeLibraryPath.setVideoLibraryPaths();
		
		// Start the application
		PApplet.main(VideoExample.class.getName());
		
	}
	
}
