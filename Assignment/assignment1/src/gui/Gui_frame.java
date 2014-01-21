package gui;

import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.media.opengl.GLCapabilities;
import javax.media.opengl.awt.GLCanvas;

public class Gui_frame {
	public Frame getFrame(GLCapabilities caps) {
		 GLCanvas canvas = new GLCanvas(caps);
		
		Frame frame = new Frame("M7002E assignment 1");
		frame.setSize(400, 300);
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
		return frame;	
	}
}



