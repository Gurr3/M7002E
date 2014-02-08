package gui;

public class Figure_modification_type {


	float[] xyz; 
	float size;
	float[] ambient, specular, diffuse, shininess;

	Float rotate;



	private void modify(float[] xyz, float size, float[] ambient, float[] specular, float[] diffuse, float[] shininess){
		this.xyz=xyz;
		this.size=size;
		this.ambient=ambient;
		this.specular=specular;
		this.diffuse=diffuse;
		this.shininess=shininess;
	}

	public Figure_modification_type(float[] xyz, float size, float[] ambient, float[] specular, float[] diffuse, float[] shininess){
		this.rotate = null;
		modify(xyz, size, ambient, specular, diffuse, shininess);
	}
	
	public Figure_modification_type(float[] xyz, float size, float[] ambient, float[] specular, float[] diffuse, float[] shininess, float rotate){
		this.rotate=rotate;
		modify(xyz, size, ambient, specular, diffuse, shininess);
	}



}
