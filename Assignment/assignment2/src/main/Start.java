package main;

import gui.Gui_frame;

import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLProfile;
import javax.swing.UIManager;

import listener.Mouse;

public class Start {

	private gui.Gui_frame frame;
	
	public Start(){
    	GLProfile.initSingleton();
        GLProfile glp = GLProfile.getDefault();
        
        GLCapabilities caps = new GLCapabilities(glp);
        
        //force UI to use operative systems menus instead of swing
        try { 
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {e.printStackTrace();}
        
        
        //create GlEventlistener
        Draw draw = new Draw();
        //get frame
        frame = new Gui_frame(draw);
        
        
        frame.getFrame(caps);
        frame.canvas.addGLEventListener(draw);
        frame.canvas.addMouseListener(new Mouse(draw));
        //start, Draw takes over from here
        frame.animator.start();

	}

}
