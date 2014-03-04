package figures;

import com.jme3.asset.AssetManager;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Box;

public class FigureBox {
	
	private Geometry geo;
	
	public FigureBox(AssetManager assetManager, int[] boxSize, Vector3f translationcoordinate, ColorRGBA color) {
		 /** create a red box straight above the blue one at (1,3,1) */
        Box box = new Box(boxSize[0], boxSize[1], boxSize[2]);      
        geo = new Geometry("Box", box);
        geo.setLocalTranslation(translationcoordinate);
        Material mat2 = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        mat2.setColor("Color", color);
        geo.setMaterial(mat2);
	}
	
	public Geometry get(){
		return geo;
	}
}
