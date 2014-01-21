package main;

import gui.Gui_frame;

import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLProfile;

public class Main {
    public static void main(String[] args) {
    	GLProfile.initSingleton();
        GLProfile glp = GLProfile.getDefault();
        GLCapabilities caps = new GLCapabilities(glp);
        
        gui.Gui_frame frame = new Gui_frame();
        
        frame.getFrame(caps);
        frame.canvas.addGLEventListener(new Draw_pyramid());
    }
}

