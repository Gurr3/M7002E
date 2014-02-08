package gui;

import gui.Figure_deployment_type.Fig_type;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.media.opengl.GLCapabilities;
import javax.media.opengl.awt.GLCanvas;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import main.Draw;

import com.jogamp.opengl.util.FPSAnimator;

public class Gui_frame {
	public GLCanvas canvas;
	public FPSAnimator animator;

	//configs
	//refresh rate
	private static int REFRESH_RATE = 60;
	//Dimensions
	private static int WINDOW_HEIGHT = 600;
	private static int WINDOW_WIDTH = 800;
	//Strings
	private static String headerstring = "M7002E assignment 2";
	//end configs

	private JFrame frame;
	private Draw draw;

	private int figure_id = 99;

	public Gui_frame(Draw draw) {
		this.draw = draw;
	}

	public JFrame getFrame(GLCapabilities caps) {
		canvas = new GLCanvas(caps);

		frame = new JFrame(headerstring);
		frame.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		frame.setJMenuBar(createbar());
		frame.add(canvas);
		frame.setVisible(true);

		// by default, an AWT Frame doesn't do anything when you click
		// the close button; this bit of code will terminate the program when
		// the window is asked to close
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});

		animator = new FPSAnimator(canvas, REFRESH_RATE);

		return frame;	
	}

	private JMenuBar createbar(){
		//Create the menu bar.
		JMenuBar menuBar = new JMenuBar();

		//Add a JMenu
		JMenu c = new JMenu("Create");
		JMenu m = new JMenu("Manipulate"); 
		JMenu o = new JMenu("Other");

		menuBar.add(c);
		menuBar.add(m);
		menuBar.add(o);

		//Create menu
		JMenuItem cPyramid = new JMenuItem("Pyramid");
		JMenuItem cSquare = new JMenuItem("Square");
		JMenuItem cStar = new JMenuItem("Star");
		JMenuItem cSphere = new JMenuItem("Sphere");
		JMenuItem cCube = new JMenuItem("Cube");
		JMenuItem cLight = new JMenuItem("Light");

		c.add(cPyramid);
		c.add(cSquare);
		c.add(cStar);
		c.add(cSphere);
		c.add(cCube);
		c.add(cLight);
		

		//Manipulate menu
		JMenuItem mModify = new JMenuItem("Modify");
		JMenuItem mDelete = new JMenuItem("Delete");

		m.add(mModify);
		m.add(mDelete);

		JMenuItem oDeleteAll = new JMenuItem("Delete All (lights remain but will be overwritten)"); 
		JMenuItem oPrint = new JMenuItem("Print Structure");

		o.add(oPrint);
		o.add(oDeleteAll);


		cPyramid.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				figure_id+=1;
				CustomCreateDialog cdialog = new CustomCreateDialog(frame, true, Fig_type.Pyramid, figure_id);
				if (cdialog.getAnswer()!=null)
					draw.figurelist.add(cdialog.getAnswer());
				System.out.println(cdialog.getAnswer().rotate);
			}

		});

		cSquare.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				figure_id+=1;
				CustomCreateDialog cdialog = new CustomCreateDialog(frame, true, Fig_type.Square, figure_id);
				if (cdialog.getAnswer()!=null)
					draw.figurelist.add(cdialog.getAnswer());
			}
		});

		cStar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				figure_id+=1;
				CustomCreateDialog cdialog = new CustomCreateDialog(frame, true, Fig_type.Star, figure_id);
				if (cdialog.getAnswer()!=null)
					draw.figurelist.add(cdialog.getAnswer());
			}
		});

		cSphere.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				figure_id+=1;
				CustomCreateDialog cdialog = new CustomCreateDialog(frame, true, Fig_type.Sphere, figure_id);
				if (cdialog.getAnswer()!=null)
					draw.figurelist.add(cdialog.getAnswer());
			}
		});
		
		cCube.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				figure_id+=1;
				CustomCreateDialog cdialog = new CustomCreateDialog(frame, true, Fig_type.Cube, figure_id);
				if (cdialog.getAnswer()!=null)
					draw.figurelist.add(cdialog.getAnswer());
			}
		});

		cLight.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				figure_id+=1;
				CustomCreateDialog cdialog = new CustomCreateDialog(frame, true, Fig_type.Light, figure_id);
				if (cdialog.getAnswer()!=null)
					draw.figurelist.add(cdialog.getAnswer());
			}
		});

		mModify.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				for (Figure_deployment_type fig: draw.figurelist) {
					if (fig.getId()==draw.picked_figure){
						CustomCreateDialog cdialog = new CustomCreateDialog(frame, true, fig.type, draw.picked_figure);
						if (cdialog.getAnswer()!=null){
							fig.modify(cdialog.getModify());
						}
					}
				}
			}
		});

		mDelete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				int n =-1;
				for (int i = 0; i < draw.figurelist.size(); i++) {
					if(draw.figurelist.get(i).getId()==draw.picked_figure)
						n=i;
				}
				if (n!=-1)
				draw.figurelist.remove(n);
			}
		});

		oDeleteAll.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				draw.figurelist.clear();
			}
		});

		oPrint.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				System.out.println("\nArrayList<Figure_deployment_type> : {");
				for (Figure_deployment_type fig : draw.figurelist) {
					System.out.print("[ ");
					System.out.print(fig.toString());
					System.out.println(" ]");
				}
				System.out.println("}");
				
			}
		});
		return menuBar;
	}
}



