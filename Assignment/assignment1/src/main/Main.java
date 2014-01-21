package main;

import gui.Gui_frame;

import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLProfile;

import com.jogamp.opengl.util.*;
import com.jogamp.opengl.util.FPSAnimator;

public class Main {
	private static final int REFRESH_RATE = 60;
	private static final int WINDOW_HEIGHT = 300;
	private static final int WINDOW_WIDTH = 400;
	
	static float anglePyramid = 0;    // rotational angle in degree for pyramid
	static float speedPyramid = 2.0f; // rotational speed for pyramid
	
    public static void main(String[] args) {
    	
    	GLProfile.initSingleton();
        GLProfile glp = GLProfile.getDefault();
        
        GLCapabilities caps = new GLCapabilities(glp);
        
        gui.Gui_frame frame = new Gui_frame();
        
        frame.getFrame(caps, REFRESH_RATE, WINDOW_HEIGHT, WINDOW_WIDTH);
        frame.canvas.addGLEventListener(new Draw_pyramid());
        
        frame.animator.start();
     
    }
}

