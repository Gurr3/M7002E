package main;

import static javax.media.opengl.GL.GL_COLOR_BUFFER_BIT;
import static javax.media.opengl.GL.GL_DEPTH_BUFFER_BIT;
import static javax.media.opengl.GL.GL_DEPTH_TEST;
import static javax.media.opengl.GL.GL_LEQUAL;
import static javax.media.opengl.GL.GL_NICEST;
import static javax.media.opengl.GL2ES1.GL_PERSPECTIVE_CORRECTION_HINT;
import static javax.media.opengl.fixedfunc.GLLightingFunc.GL_SMOOTH;
import static javax.media.opengl.fixedfunc.GLMatrixFunc.GL_MODELVIEW;
import static javax.media.opengl.fixedfunc.GLMatrixFunc.GL_PROJECTION;
import gui.Draw_figures;
import gui.Figure_deployment_type;
import gui.Figures;

import java.awt.Point;
import java.util.ArrayList;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.fixedfunc.GLLightingFunc;
import javax.media.opengl.glu.GLU;

public class Draw implements GLEventListener{

	Figures figures = new Figures();
	GLU glu = new GLU();
	public Pick pick = new Pick();
	Draw_figures paint = new Draw_figures();
	float aspect;
	
	static public final int NOTHING = 0, UPDATE = 1, SELECT = 2;
	public int cmd = UPDATE;

	public int picked_figure; //id of the currently chosen figure

	private int bufsize = 512;

	//public ArrayList<ArrayList<Float>> displaylist = new ArrayList<>(); //modified from gui_frame 
	public ArrayList<Figure_deployment_type> figurelist = new ArrayList<>();
	public Point p = new Point();

	
	@Override
	public void display(GLAutoDrawable drawable) {

		GL2 gl = drawable.getGL().getGL2(); 
		
		gl.glClear(GL.GL_COLOR_BUFFER_BIT);	//used to clear buffer
		gl.glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); //clear color and depth buffers

		gl.glPushMatrix();

		gl.glEnable(GLLightingFunc.GL_LIGHTING);
		gl.glEnable(GLLightingFunc.GL_COLOR_MATERIAL);
		gl.glColorMaterial ( GL.GL_FRONT_AND_BACK, GLLightingFunc.GL_AMBIENT);
		gl.glColorMaterial ( GL.GL_FRONT_AND_BACK, GLLightingFunc.GL_SPECULAR);
		gl.glColorMaterial ( GL.GL_FRONT_AND_BACK, GLLightingFunc.GL_DIFFUSE);
		gl.glEnable ( GLLightingFunc.GL_COLOR_MATERIAL ) ;
		paint.paint(gl, drawable, figurelist);

		if (cmd == SELECT){
			picked_figure= pick.pick(gl, glu, drawable, p, bufsize, figurelist, aspect);
			cmd = UPDATE;
		}
		gl.glPopMatrix();

	}




	@Override
	public void init(GLAutoDrawable drawable) {
		GL2 gl = drawable.getGL().getGL2(); 

		gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);		// set background (clear) color
		gl.glClearDepth(1.0f);      					// set clear depth value to farthest
		gl.glEnable(GL_DEPTH_TEST); 					// enables depth testing
		gl.glDepthFunc(GL_LEQUAL);  					// the type of depth test to do
		gl.glHint(GL_PERSPECTIVE_CORRECTION_HINT, GL_NICEST); // best perspective correction
		gl.glShadeModel(GL_SMOOTH); 					// blends colors nicely, and smoothes out lighting
		gl.glLoadIdentity();                 			// reset the model-view matrix

		gl.glEnable(GL2.GL_LIGHTING);
		for (int i = 0; i < 7; i++) {
			gl.glEnable(GL2.GL_LIGHT0+i);
		}
		
	}

	@Override
	public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
		GL2 gl = drawable.getGL().getGL2();  // get the OpenGL 2 graphics context

		// Setup perspective projection, with aspect ratio matches viewport
		if (height == 0) height = 1;   // prevent divide by zero
		this.aspect = (float)width / height;

		// Set the view port (display area) to cover the entire window
		gl.glViewport(0, 0, width, height);

		// Setup perspective projection, with aspect ratio matches viewport
		gl.glMatrixMode(GL_PROJECTION);  // choose projection matrix
		gl.glLoadIdentity();             // reset projection matrix
		glu.gluPerspective(45.0, aspect, 0.1, 100.0); // fov, aspect, zNear, zFar

		// Enable the model-view transform
		gl.glMatrixMode(GL_MODELVIEW);
		gl.glLoadIdentity(); // reset
	}

	@Override
	public void dispose(GLAutoDrawable arg0) {}

}
