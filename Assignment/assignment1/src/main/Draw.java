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
import gui.Figures;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.glu.GLU;

public class Draw implements GLEventListener{

	Figures figures = new Figures();
	GLU glu = new GLU();

	private float pyramid_transl_x = -1.6f;
	private float pyramid_transl_y = 0.0f;
	private float pyramid_transl_z = -6.0f;
	private float pyramid_rotate = 0f;
	
	private float square_transl_x = 0f;
	private float square_transl_y = 0f;
	private float square_transl_z = -6.0f;
	
	private float star_transl_x = 3f;
	private float star_transl_y = -2f;
	private float star_transl_z = -8.0f;

	@Override
	public void display(GLAutoDrawable drawable) {
		GL2 gl = drawable.getGL().getGL2(); 

		gl.glClear(GL.GL_COLOR_BUFFER_BIT);	//used to clear buffer
		gl.glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); //clear color and depth buffers

		gl.glPushMatrix();
		gl.glViewport(0, 0, drawable.getWidth()/2, drawable.getHeight());		
		gl.glLoadIdentity();  //reset the model-view matrix

		figures.render_pyramid_unfilled(drawable, gl, pyramid_transl_x, pyramid_transl_y, pyramid_transl_z, pyramid_rotate);
		figures.render_square_filled(drawable, gl, square_transl_x, square_transl_y, square_transl_z);
		figures.render_star(drawable, gl, star_transl_x, star_transl_y, star_transl_z);
		gl.glPopMatrix();

		gl.glViewport(drawable.getWidth()/2, 0, drawable.getWidth()/2, drawable.getHeight());
		gl.glLoadIdentity();  //reset the model-view matrix
		
		gl.glPushMatrix();
		gl.glMatrixMode(GL_MODELVIEW);
		gl.glRotatef(45f, 0f, 1f, 0f);
		gl.glTranslatef(5, 0, 0);
		
		figures.render_pyramid_unfilled(drawable, gl, pyramid_transl_x, pyramid_transl_y, pyramid_transl_z, pyramid_rotate);
		figures.render_square_filled(drawable, gl, square_transl_x, square_transl_y, square_transl_z);
		figures.render_star(drawable, gl, star_transl_x, star_transl_y, star_transl_z);
		gl.glPopMatrix();
	}



	@Override
	public void init(GLAutoDrawable drawable) {
		GL2 gl = drawable.getGL().getGL2(); 
		// initialization from [1]
		gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);		// set background (clear) color
		gl.glClearDepth(1.0f);      					// set clear depth value to farthest
		gl.glEnable(GL_DEPTH_TEST); 					// enables depth testing
		gl.glDepthFunc(GL_LEQUAL);  					// the type of depth test to do
		gl.glHint(GL_PERSPECTIVE_CORRECTION_HINT, GL_NICEST); // best perspective correction
		gl.glShadeModel(GL_SMOOTH); 					// blends colors nicely, and smoothes out lighting
		gl.glLoadIdentity();                 			// reset the model-view matrix
	}

	@Override
	public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
		GL2 gl = drawable.getGL().getGL2();  // get the OpenGL 2 graphics context

		// Setup perspective projection, with aspect ratio matches viewport
		if (height == 0) height = 1;   // prevent divide by zero
		float aspect = (float)width/2 / height;

		// Set the view port (display area) to cover the entire window
		gl.glViewport(0, 0, width/2, height);

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
