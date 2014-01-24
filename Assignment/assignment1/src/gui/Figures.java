package gui;

import static javax.media.opengl.GL.GL_TRIANGLES;
import static javax.media.opengl.GL2.GL_ALL_ATTRIB_BITS;
import static javax.media.opengl.GL2GL3.GL_QUADS;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;
import javax.media.opengl.GL2GL3;
import javax.media.opengl.GLAutoDrawable;
// GL constants
// GL2 constants

public class Figures {
	static float anglePyramid = 0;    // rotational angle in degree for pyramid

	public void render_square_filled(GLAutoDrawable drawable, GL2 gl, float transl_x, float transl_y, float transl_z){
		gl.glPushMatrix();
		gl.glPushAttrib(GL_ALL_ATTRIB_BITS);
		
		gl.glTranslatef(transl_x, transl_y, transl_z);
		gl.glBegin(GL_QUADS);
		
		gl.glColor3f(0, 0, 1);
		gl.glVertex3f(1f, 1f, 0f);
		gl.glVertex3f(0f, 1f, 0f);
		gl.glVertex3f(0f, 0f, 0f);
		gl.glVertex3f(1f, 0f, 0f);
		
		gl.glEnd();
		gl.glPopAttrib();
		gl.glPopMatrix();
	}
	
	public void render_star(GLAutoDrawable drawable, GL2 gl, float transl_x, float transl_y, float transl_z){
		gl.glPushMatrix();
		gl.glPushAttrib(GL_ALL_ATTRIB_BITS);
		
		gl.glTranslatef(transl_x, transl_y, transl_z);
		gl.glBegin(GL_TRIANGLES);
		
		gl.glColor3f(0, 1, 0);
		
		gl.glVertex3f(-1f, 0.5f, 0f);
		gl.glVertex3f(1f, 0.5f, 0f);
		gl.glVertex3f(0f, -1f, 0f);
		
		gl.glVertex3f(-1f, -0.5f, 0f);
		gl.glVertex3f(1f, -0.5f, 0f);
		gl.glVertex3f(0f, 1f, 0f);
		
		gl.glEnd();
		gl.glPopAttrib();
		gl.glPopMatrix();
	}
	
	public void render_pyramid_unfilled(GLAutoDrawable drawable, GL2 gl, float transl_x, float transl_y, float transl_z) {
		gl.glPushMatrix();
		gl.glPushAttrib(GL_ALL_ATTRIB_BITS);
		
		gl.glPolygonMode(GL.GL_FRONT_AND_BACK, GL2GL3.GL_LINE);

		gl.glTranslatef(transl_x, transl_y, transl_z); // translate left and into the screen
		gl.glRotatef(anglePyramid, -0.2f, 1.0f, 0.0f); // rotate about the y-axis

		gl.glBegin(GL_TRIANGLES); // of the pyramid

		// Font-face triangle
		gl.glColor3f(1.0f, 0.0f, 0.0f); // Red
		gl.glVertex3f(0.0f, 1.0f, 0.0f);
		gl.glColor3f(0.0f, 1.0f, 0.0f); // Green
		gl.glVertex3f(-1.0f, -1.0f, 1.0f);
		gl.glColor3f(0.0f, 0.0f, 1.0f); // Blue
		gl.glVertex3f(1.0f, -1.0f, 1.0f);

		// Right-face triangle
		gl.glColor3f(1.0f, 0.0f, 0.0f); // Red
		gl.glVertex3f(0.0f, 1.0f, 0.0f);
		gl.glColor3f(0.0f, 0.0f, 1.0f); // Blue
		gl.glVertex3f(1.0f, -1.0f, 1.0f);
		gl.glColor3f(0.0f, 1.0f, 0.0f); // Green
		gl.glVertex3f(1.0f, -1.0f, -1.0f);

		// Back-face triangle
		gl.glColor3f(1.0f, 0.0f, 0.0f); // Red
		gl.glVertex3f(0.0f, 1.0f, 0.0f);
		gl.glColor3f(0.0f, 1.0f, 0.0f); // Green
		gl.glVertex3f(1.0f, -1.0f, -1.0f);
		gl.glColor3f(0.0f, 0.0f, 1.0f); // Blue
		gl.glVertex3f(-1.0f, -1.0f, -1.0f);

		// Left-face triangle
		gl.glColor3f(1.0f, 0.0f, 0.0f); // Red
		gl.glVertex3f(0.0f, 1.0f, 0.0f);
		gl.glColor3f(0.0f, 0.0f, 1.0f); // Blue
		gl.glVertex3f(-1.0f, -1.0f, -1.0f);
		gl.glColor3f(0.0f, 1.0f, 0.0f); // Green
		gl.glVertex3f(-1.0f, -1.0f, 1.0f);

		gl.glEnd(); // of the pyramid

		gl.glPolygonMode( GL.GL_FRONT_AND_BACK, GL2GL3.GL_FILL );
		gl.glPopAttrib();
		gl.glPopMatrix();
	}


}
