package main;

import gui.Draw_figures;
import gui.Figure_deployment_type;

import java.awt.Point;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.glu.GLU;

import com.jogamp.common.nio.Buffers;

public class Pick {

	/*
	 * sets up selection mode, name stack, and projection matrix for picking. Then
	 * the objects are drawn.
	 */
	Draw_figures paint = new Draw_figures();

	public int pick(GL2 gl, GLU glu, GLAutoDrawable drawable, Point p, int bufsize, ArrayList<Figure_deployment_type> displaylist, float aspect){
		//int[] selectBuf = new int[bufsize];
		//		IntBuffer selectBuffer = Buffers.newDirectIntBuffer(bufsize);
		IntBuffer selectBuffer = Buffers.newDirectIntBuffer(bufsize);
		int hits;
		int viewport[] = new int[4];

		// int x, y;

		gl.glGetIntegerv(GL.GL_VIEWPORT, viewport, 0);

		gl.glSelectBuffer(bufsize, selectBuffer);
		gl.glRenderMode(GL2.GL_SELECT);

		//		gl.glInitNames();
		//		gl.glPushName(-1);

		gl.glMatrixMode(GL2.GL_PROJECTION);
		gl.glPushMatrix();
		gl.glLoadIdentity();

		/* create 5x5 pixel picking region near cursor location */
		glu.gluPickMatrix((double) p.x,
				(double) (viewport[3] - p.y), //
				5.0, 5.0, viewport, 0);

		glu.gluPerspective(45.0f,  aspect, 0.1f, 100.0f); 
		paint.paint(gl, drawable, displaylist);

		gl.glPopMatrix();
		gl.glFlush();

		hits = gl.glRenderMode(GL2.GL_RENDER);
		//	selectBuffer.get(selectBuf);
		//		System.out.println(hits);
		return processHits(hits, selectBuffer, displaylist);
	}

	/*
	 * prints out the contents of the selection array.
	 */
	private int processHits(int hits, IntBuffer buffer, ArrayList<Figure_deployment_type> displaylist)
	{
		System.out.println("---------------------------------");
		System.out.println(" HITS: " + hits);
		int offset = 0;
		int names;
		float z1, z2;
		ArrayList<Integer> figs_hit= new ArrayList<>(); 
		ArrayList<float[]> closest = new ArrayList<>();
		
		for (int i=0;i<hits;i++)
		{
			System.out.println("- - - - - - - - - - - -");
			System.out.println(" hit: " + (i + 1));
			names = buffer.get(offset); offset++;
			z1 = (float) (buffer.get(offset)& 0xffffffffL) / 0x7fffffff; offset++;
			z2 = (float) (buffer.get(offset)& 0xffffffffL) / 0x7fffffff; offset++;
			System.out.println(" number of names: " + names);
			System.out.println(" z1: " + z1);
			System.out.println(" z2: " + z2);
			System.out.println(" names: ");

			for (int j=0;j<names;j++)
			{
				System.out.print("       " + buffer.get(offset)); 
				if (j==(names-1)){
					System.out.println("<-");
					figs_hit.add(buffer.get(offset)); //everytime a new id for hit is found, add to figs_hit
				}
				else
					System.out.println();
				offset++;
			}
			System.out.println("- - - - - - - - - - - -");
		}
		System.out.println("---------------------------------");

		//create list of figures hit
		for (Figure_deployment_type fig : displaylist) {
			for (int integer : figs_hit) {
				if(integer==fig.getId()){
					closest.add(new float[]{fig.getId(), fig.getZ()}); //fetch z coordinates for each object
				}
			}
		}
		
		//sort by z coordinate
		Collections.sort(closest, new Comparator<float[]>() {
			@Override
			public int compare(float[] a, float[] b) {
				return (int) (a[1]-b[1]);
			}
		});
		
		if (closest.isEmpty())
			return -1;
		System.out.println("Youve selected object with id "+ closest.get(closest.size()-1)[0]);
		return (int) Math.round(closest.get(closest.size()-1)[0]);
	}

}
