package gui;

public class Figure_deployment_type {

	enum Fig_type {Pyramid, Square, Star, Sphere, Cube, Light;}

	private int id;
	Fig_type type;
	float[] xyz; 
	float size;
	float[] ambient, specular, diffuse, shininess;

	Float rotate=null;

	private void setvariables(int id, Fig_type type, float[] xyz, float size, float[] ambient, float[] specular, float[] diffuse, float[] shininess){
		this.id = id;
		this.type = type;
		this.xyz = xyz;
		this.size=size;
		this.ambient = ambient;
		this.specular = specular;
		this.diffuse = diffuse;
		this.shininess = shininess;
	}

	//square, star, sphere
	public Figure_deployment_type(int id, Fig_type type, float[] xyz, float size, float[] ambient, float[] specular, float[] diffuse, float[] shininess) {
		setvariables(id, type, xyz, size, ambient, specular, diffuse, shininess);
	}

	//pyramid
	public Figure_deployment_type(int id, Fig_type type, float[] xyz, float size, float[] ambient, float[] specular, float[] diffuse, float[] shininess, float rotate) {
		this.rotate = rotate;
		setvariables(id, type, xyz, size, ambient, specular, diffuse, shininess);
	}

	//light
	public Figure_deployment_type(int id, Fig_type type, float[] xyz, float[] ambient, float[] diffuse) {
		this.id=id;
		this.type=type;
		this.xyz=xyz;
		this.ambient = ambient;
		this.diffuse = diffuse;
	} 

	public void modify(float[] xyz, float size, float[] ambient, float[] specular, float[] diffuse, float[] shininess){
		this.xyz=xyz;
		this.size=size;
		this.ambient=ambient;
		this.specular=specular;
		this.diffuse=diffuse;
		this.shininess=shininess;
	}

	public void modify(float[] xyz, float size, float[] ambient, float[] specular, float[] diffuse, float[] shininess, float rotate){
		this.rotate=rotate;
		modify(xyz, size, ambient, specular, diffuse, shininess);
	}

	public void modify(Figure_modification_type mod) {
		if (mod.rotate != null)
			modify(mod.xyz, mod.size, mod.ambient, mod.specular, mod.diffuse, mod.shininess, mod.rotate);
		else
			modify(mod.xyz, mod.size, mod.ambient, mod.specular, mod.diffuse, mod.shininess);
	}

	public int getId() {
		return id;
	}

	public float getZ() {
		return xyz[2];
	}

	@Override
	public String toString() {
		String rotate = "";
		String spe = "";
		String shi = "";

		String id = 	"int id = " +this.id;
		String type = 	", Fig_type type = "+this.type;
		String xyz = 	", float[] xyz = "+ "[" +this.xyz[0]+", " +this.xyz[1]+", "+this.xyz[2]+"]";

		if(this.rotate!= null)
			rotate = ", Float rotate = "+ this.rotate;

		String amb = 	", float[] ambient = "+"["+this.ambient[0]+", "+this.ambient[1]+", "+ this.ambient[2]+", "+this.ambient[3]+"]";
		String dif = 	", float[] diffuse = "+"["+this.diffuse[0]+", "+this.diffuse[1]+", "+ this.diffuse[2]+", "+this.diffuse[3]+"]";
		if (this.type!=Fig_type.Light){
			spe = 		", float[] specular = "+"["+this.specular[0]+", "+this.specular[1]+", "+ this.specular[2]+", "+this.specular[3]+"]";
			shi = 		", float[] shininess = "+"["+this.shininess[0]+", "+this.shininess[1]+", "+ this.shininess[2]+", "+this.shininess[3]+"]";
		}

		return id+type+xyz+rotate+amb+spe+dif+shi;
	}

}
