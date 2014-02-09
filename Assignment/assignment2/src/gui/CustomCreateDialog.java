package gui;

// based on http://darksleep.com/player/DialogExample/CustomDialog.java.html

import gui.Figure_deployment_type.Fig_type;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class CustomCreateDialog extends JDialog implements ActionListener {
	private static final long serialVersionUID = -1739638496397821989L;
	private JPanel myPanel = null;
	private JButton yesButton = null;
	private JTextField x, y, z, s, la, ls, ld, lshine, r;
	private int id;
	private Figure_deployment_type.Fig_type type;
	private Figure_deployment_type answer = null;
	private Figure_modification_type modifyvalues= null;
	public Figure_deployment_type getAnswer() { return answer; }
	public Figure_modification_type getModify() { return modifyvalues; }

	/**
	 * Create the popup window asking for Figure specs
	 * @param frame
	 * @param modal
	 * @param type
	 * @param id
	 */
	public CustomCreateDialog(JFrame frame, boolean modal, Figure_deployment_type.Fig_type type, int id) {

		super(frame, modal);

		myPanel = new JPanel(new GridLayout(0,2));
		getContentPane().add(myPanel);


		this.type = type;
		this.id = id;
		x = new JTextField("0");
		y = new JTextField("0");
		z = new JTextField("-7");
		if(type==Fig_type.Light){
			z = new JTextField("10");
		}

		s = new JTextField("1");
		ls = new JTextField("0.0, 0.0, 0.0, 1.0");
		lshine = new JTextField("0.0, 0.0, 0.0, 1.0");

		la = new JTextField("0.0, 0.0, 0.0, 1.0");
		ld = new JTextField("0.0, 0.0, 0.0, 1.0");


		myPanel.add(new JLabel("x"));
		myPanel.add(x);
		myPanel.add(new JLabel("y"));
		myPanel.add(y);
		myPanel.add(new JLabel("z"));
		myPanel.add(z);
		if(this.type!=Fig_type.Light){
			myPanel.add(new JLabel("Size"));
			myPanel.add(s);
		}
		if (this.type==Fig_type.Pyramid){
			r = new JTextField("0");
			myPanel.add(new JLabel("Rotation"));
			myPanel.add(r);
		}

		myPanel.add(new JLabel("Ambient (R,G,B,A)"));
		myPanel.add(la);
		myPanel.add(new JLabel("Diffuse (R,G,B,A)"));
		myPanel.add(ld);

		if(this.type!=Fig_type.Light){
			myPanel.add(new JLabel("Specular (R,G,B,A)"));
			myPanel.add(ls);
			myPanel.add(new JLabel("Shininess (R,G,B,A)"));
			myPanel.add(lshine);
		}

		yesButton = new JButton("Ok");
		yesButton.addActionListener(this);
		myPanel.add(yesButton); 


		pack();
		setLocationRelativeTo(frame);
		setVisible(true);
	}


	public void actionPerformed(ActionEvent e) {
		if(yesButton == e.getSource()) {
			float[] amb = new float[]{Float.parseFloat(la.getText().split(",")[0]), Float.parseFloat(la.getText().split(",")[1]), Float.parseFloat(la.getText().split(",")[2]), Float.parseFloat(la.getText().split(",")[3])};
			float[] dif = new float[]{Float.parseFloat(ld.getText().split(",")[0]), Float.parseFloat(ld.getText().split(",")[1]), Float.parseFloat(ld.getText().split(",")[2]), Float.parseFloat(ld.getText().split(",")[3])};
			float[] spe = new float[]{Float.parseFloat(ls.getText().split(",")[0]), Float.parseFloat(ls.getText().split(",")[1]), Float.parseFloat(ls.getText().split(",")[2]), Float.parseFloat(ls.getText().split(",")[3])};
			float[] shi = new float[]{Float.parseFloat(lshine.getText().split(",")[0]), Float.parseFloat(lshine.getText().split(",")[1]), Float.parseFloat(lshine.getText().split(",")[2]), Float.parseFloat(lshine.getText().split(",")[3])};
			float[] xyz = new float[]{Float.parseFloat(x.getText()),Float.parseFloat(y.getText()),Float.parseFloat(z.getText())};
			
			if(this.type==Fig_type.Pyramid){
				modifyvalues = new Figure_modification_type(xyz, Float.parseFloat(s.getText()), amb, spe, dif, shi, Float.parseFloat(r.getText()));
				answer = new Figure_deployment_type(this.id, this.type, xyz, Float.parseFloat(s.getText()), amb, spe, dif, shi, Float.parseFloat(r.getText()));
			}else if(this.type==Fig_type.Light){
				answer = new Figure_deployment_type(this.id, this.type, xyz, amb, dif);
				modifyvalues = new Figure_modification_type(xyz, Float.parseFloat(s.getText()), amb, spe, dif, shi);
			}else{
				answer = new Figure_deployment_type(this.id, this.type, xyz, Float.parseFloat(s.getText()), amb, spe, shi ,dif);
				modifyvalues = new Figure_modification_type(xyz, Float.parseFloat(s.getText()), amb, spe, dif, shi);
			}
			setVisible(false);
		}
	}

}

