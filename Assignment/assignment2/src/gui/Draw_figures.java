package gui;

import java.util.ArrayList;

import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;


public class Draw_figures {

	Figures figures = new Figures();
	int lightnr;
	
	public void paint(GL2 gl, GLAutoDrawable drawable, ArrayList<Figure_deployment_type> displaylist){
		gl.glInitNames();
		lightnr = 0;
		for (Figure_deployment_type figureValues : displaylist) {

			gl.glPushName(figureValues.getId());

			switch (figureValues.type) {
			case Pyramid:
				figures.render_pyramid_unfilled(drawable, gl, figureValues);
				break;
			case Square:
				figures.render_square_filled(drawable, gl, figureValues);
				break;
			case Star:
				figures.render_star(drawable, gl, figureValues);
				break;
			case Sphere:
				figures.render_sphere(drawable, gl, figureValues);
				break;
			case Cube:
				figures.render_cube(drawable, gl, figureValues);
				break;
			case Light:
				figures.lettherebelight(gl, figureValues, lightnr);
				lightnr++;
				break;
			default:
				System.out.println("faulty type in print: "+ figureValues.type);
				break;
			}
		}
	}

}
