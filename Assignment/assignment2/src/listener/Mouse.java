package listener;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import main.Draw;



public class Mouse implements MouseListener{

	Draw draw;
	
	public Mouse(Draw draw) {this.draw=draw;}

	@Override
	public void mouseClicked(MouseEvent arg0) {}
	@Override
	public void mouseEntered(MouseEvent arg0) {}
	@Override
	public void mouseExited(MouseEvent arg0) {}
	@Override
	public void mousePressed(MouseEvent mouse) {}

	@Override
	public void mouseReleased(MouseEvent mouse) {
		Point p = new Point(mouse.getX(), mouse.getY());
		draw.p=p;
		draw.cmd=Draw.SELECT;
	}
}
