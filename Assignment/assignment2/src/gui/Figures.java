package gui;

import static javax.media.opengl.GL.GL_TRIANGLES;
import static javax.media.opengl.GL2.GL_ALL_ATTRIB_BITS;
import static javax.media.opengl.GL2GL3.GL_QUADS;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;
import javax.media.opengl.GL2GL3;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.fixedfunc.GLLightingFunc;

import com.jogamp.opengl.util.gl2.GLUT;

public class Figures {

	private GLUT glut = new GLUT();
	
	private void begin(GL2 gl){
		gl.glPushMatrix();
		gl.glPushAttrib(GL_ALL_ATTRIB_BITS);
	}
	private void end(GL2 gl){
		gl.glPopAttrib();
		gl.glPopMatrix();
	}

	/**
	 * Paint function for square
	 * @param drawable
	 * @param gl
	 * @param fig
	 */
	public void render_square_filled(GLAutoDrawable drawable, GL2 gl, Figure_deployment_type fig){
		begin(gl);

		gl.glMaterialfv(GL.GL_FRONT, GLLightingFunc.GL_AMBIENT, fig.ambient, 0);
		gl.glLightModelfv(GL2.GL_LIGHT_MODEL_AMBIENT, fig.ambient, 0);
		gl.glLightModelfv(GL2.GL_LIGHT_MODEL_LOCAL_VIEWER, new float[]{0}, 0);
		
		gl.glMaterialfv(GL.GL_FRONT, GL2.GL_AMBIENT, fig.ambient, 0);
		gl.glMaterialfv(GL.GL_FRONT, GL2.GL_DIFFUSE, fig.diffuse, 0);
		gl.glMaterialfv(GL.GL_FRONT, GL2.GL_SPECULAR, fig.specular, 0);
		gl.glMaterialfv(GL.GL_FRONT, GL2.GL_SHININESS, fig.shininess, 0);
		
		gl.glTranslatef(fig.xyz[0],fig.xyz[1],fig.xyz[2]);
		
		gl.glBegin(GL_QUADS);

		gl.glVertex3f(fig.size*1f, fig.size*1f, fig.size*0f);
		gl.glVertex3f(fig.size*0f, fig.size*1f, fig.size*0f);
		gl.glVertex3f(fig.size*0f, fig.size*0f, fig.size*0f);
		gl.glVertex3f(fig.size*1f, fig.size*0f, fig.size*0f);
		gl.glEnd();
		
		end(gl);
	}

	/**
	 * Paint function for star
	 * @param drawable
	 * @param gl
	 * @param fig
	 */
	public void render_star(GLAutoDrawable drawable, GL2 gl, Figure_deployment_type fig){
		begin(gl);
		
		gl.glMaterialfv(GL.GL_FRONT, GLLightingFunc.GL_AMBIENT, fig.ambient, 0);
		gl.glLightModelfv(GL2.GL_LIGHT_MODEL_AMBIENT, fig.ambient, 0);
		gl.glLightModelfv(GL2.GL_LIGHT_MODEL_LOCAL_VIEWER, new float[]{0}, 0);
		
		gl.glMaterialfv(GL.GL_FRONT, GL2.GL_AMBIENT, fig.ambient, 0);
		gl.glMaterialfv(GL.GL_FRONT, GL2.GL_DIFFUSE, fig.diffuse, 0);
		gl.glMaterialfv(GL.GL_FRONT, GL2.GL_SPECULAR, fig.specular, 0);
		gl.glMaterialfv(GL.GL_FRONT, GL2.GL_SHININESS, fig.shininess, 0);
		
		gl.glTranslatef(fig.xyz[0], fig.xyz[1], fig.xyz[2]);
		
		gl.glBegin(GL_TRIANGLES);

		gl.glVertex3f(fig.size*-1f, fig.size*0.5f, fig.size*0f);
		gl.glVertex3f(fig.size*1f, fig.size*0.5f, fig.size*0f);
		gl.glVertex3f(fig.size*0f, fig.size*-1f, fig.size*0f);

		gl.glVertex3f(fig.size*-1f, fig.size*-0.5f, fig.size*0f);
		gl.glVertex3f(fig.size*1f, fig.size*-0.5f, fig.size*0f);
		gl.glVertex3f(fig.size*0f, fig.size*1f, fig.size*0f);
		gl.glEnd();
		
		end(gl);
	}



	/**
	 * Paint function for pyramid
	 * @param drawable
	 * @param gl
	 * @param fig
	 */
	public void render_pyramid_unfilled(GLAutoDrawable drawable, GL2 gl, Figure_deployment_type fig) {
		begin(gl);

		gl.glPolygonMode(GL.GL_FRONT_AND_BACK, GL2GL3.GL_LINE);

		gl.glMaterialfv(GL.GL_FRONT, GLLightingFunc.GL_AMBIENT, fig.ambient, 0);
		gl.glLightModelfv(GL2.GL_LIGHT_MODEL_AMBIENT, fig.ambient, 0);
		gl.glLightModelfv(GL2.GL_LIGHT_MODEL_LOCAL_VIEWER, new float[]{0}, 0);
		
		gl.glMaterialfv(GL.GL_FRONT, GL2.GL_AMBIENT, fig.ambient, 0);
		gl.glMaterialfv(GL.GL_FRONT, GL2.GL_DIFFUSE, fig.diffuse, 0);
		gl.glMaterialfv(GL.GL_FRONT, GL2.GL_SPECULAR, fig.specular, 0);
		gl.glMaterialfv(GL.GL_FRONT, GL2.GL_SHININESS, fig.shininess, 0);
		
		gl.glTranslatef(fig.xyz[0], fig.xyz[1], fig.xyz[2]); // translate left and into the screen
		gl.glRotatef(fig.rotate, 0, 0, 0.0f); // rotate about the y-axis
//		System.out.println("rotate: "+fig.rotate);
		gl.glBegin(GL_TRIANGLES); // of the pyramid

		// Font-face triangle
		gl.glVertex3f(fig.size*0.0f, fig.size*1.0f, fig.size*0.0f);
		gl.glVertex3f(-fig.size*1.0f, fig.size*-1.0f, fig.size*1.0f);
		gl.glVertex3f(fig.size*1.0f, fig.size*-1.0f, fig.size*1.0f);

		// Right-face triangle
		gl.glVertex3f(fig.size*0.0f, fig.size*1.0f, fig.size*0.0f);
		gl.glVertex3f(fig.size*1.0f, fig.size*-1.0f, fig.size*1.0f);
		gl.glVertex3f(fig.size*1.0f, fig.size*-1.0f, fig.size*-1.0f);

		// Back-face triangle
		gl.glVertex3f(fig.size*0.0f, fig.size*1.0f, fig.size*0.0f);
		gl.glVertex3f(fig.size*1.0f, fig.size*-1.0f, fig.size*-1.0f);
		gl.glVertex3f(fig.size*-1.0f, fig.size*-1.0f, fig.size*-1.0f);

		// Left-face triangle
		gl.glVertex3f(fig.size*0.0f, fig.size*1.0f, fig.size*0.0f);
		gl.glVertex3f(fig.size*-1.0f, fig.size*-1.0f, fig.size*-1.0f);
		gl.glVertex3f(fig.size*-1.0f, fig.size*-1.0f, fig.size*1.0f);

		gl.glPolygonMode( GL.GL_FRONT_AND_BACK, GL2GL3.GL_FILL );
		gl.glEnd();
		end(gl);
	}


	/**
	 * Paint function for sphere
	 * @param drawable
	 * @param gl
	 * @param fig
	 */
	public void render_sphere(GLAutoDrawable drawable, GL2 gl, Figure_deployment_type fig){	
		begin(gl);
		gl.glTranslatef(fig.xyz[0], fig.xyz[1], fig.xyz[2]);
		gl.glMaterialfv(GL.GL_FRONT, GL2.GL_AMBIENT, fig.ambient, 0);
		gl.glMaterialfv(GL.GL_FRONT, GL2.GL_DIFFUSE, fig.diffuse, 0);
		gl.glMaterialfv(GL.GL_FRONT, GL2.GL_SPECULAR, fig.specular, 0);
		gl.glMaterialfv(GL.GL_FRONT, GL2.GL_SHININESS, fig.shininess, 0);
		glut.glutSolidSphere(fig.size*1.0f, 30, 30);
		end(gl);
	}
	
	/**
	 * Paint function for cube
	 * @param drawable
	 * @param gl
	 * @param fig
	 */
	public void render_cube(GLAutoDrawable drawable, GL2 gl, Figure_deployment_type fig){	
		begin(gl);
		gl.glTranslatef(fig.xyz[0], fig.xyz[1], fig.xyz[2]);
		gl.glMaterialfv(GL.GL_FRONT, GL2.GL_AMBIENT, fig.ambient, 0);
		gl.glMaterialfv(GL.GL_FRONT, GL2.GL_DIFFUSE, fig.diffuse, 0);
		gl.glMaterialfv(GL.GL_FRONT, GL2.GL_SPECULAR, fig.specular, 0);
		gl.glMaterialfv(GL.GL_FRONT, GL2.GL_SHININESS, fig.shininess, 0);
		gl.glRotatef(35, 2, 1, 0.0f); // rotate about the y-axis
		glut.glutSolidCube(fig.size*1.0f);
		end(gl);
	}
	
	/**
	 * Paint function for lights
	 * @param gl
	 * @param fig
	 * @param lightnr
	 */
	public void lettherebelight(GL2 gl, Figure_deployment_type fig, int lightnr){
		gl.glLightfv(GL2.GL_LIGHT0+lightnr, GL2.GL_AMBIENT, fig.ambient, 0);
		gl.glLightfv(GL2.GL_LIGHT0+lightnr, GL2.GL_DIFFUSE, fig.diffuse, 0);
		gl.glLightfv(GL2.GL_LIGHT0+lightnr, GL2.GL_POSITION, new float[]{fig.xyz[0],fig.xyz[1],fig.xyz[2], 0}, 0);
	}
}

