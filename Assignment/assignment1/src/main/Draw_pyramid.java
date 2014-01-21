package main;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;

public class Draw_pyramid implements GLEventListener{

	@Override
	public void display(GLAutoDrawable drawable) {
	    update();
	    render(drawable);
	}


	@Override
	public void dispose(GLAutoDrawable arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init(GLAutoDrawable arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void reshape(GLAutoDrawable arg0, int arg1, int arg2, int arg3,
			int arg4) {
		// TODO Auto-generated method stub
		
	}


	private void update() {
		// TODO Auto-generated method stub
		
	}
	
	private void render(GLAutoDrawable drawable) {
	    GL2 gl = drawable.getGL().getGL2();
	    
	    // draw a triangle filling the window
	    gl.glBegin(GL.GL_TRIANGLES);
	    
	    gl.glColor3f(1, 0, 0);
	    gl.glVertex2f(-1, -1);
	    
	    gl.glColor3f(0, 1, 0);
	    gl.glVertex2f(0, 1);
	    
	    gl.glColor3f(0, 0, 1);
	    gl.glVertex2f(1, -1);
	    
	    gl.glEnd();
	}

}
