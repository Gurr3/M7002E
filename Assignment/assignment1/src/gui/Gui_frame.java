package gui;

import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.media.opengl.GLCapabilities;
import javax.media.opengl.awt.GLCanvas;

import com.jogamp.opengl.util.FPSAnimator;

public class Gui_frame {
	public GLCanvas canvas;
	public FPSAnimator animator;
	
	public Frame getFrame(GLCapabilities caps, int REFRESH_RATE, int WINDOW_HEIGHT, int WINDOW_WIDTH) {
		canvas = new GLCanvas(caps);
		
		Frame frame = new Frame("M7002E assignment 1");
		frame.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		frame.add(canvas);
		frame.setVisible(true);

		// by default, an AWT Frame doesn't do anything when you click
		// the close button; this bit of code will terminate the program when
		// the window is asked to close
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		
		animator = new FPSAnimator(canvas, REFRESH_RATE);
	        
		return frame;	
	}
	
	public GLCanvas getcanvas(){
		return canvas;
	}
}



