package figures;

import com.jme3.asset.AssetManager;
import com.jme3.asset.BlenderKey;
import com.jme3.scene.Spatial;

public class FigureBlender {

	private Spatial scene;
	
	public FigureBlender(AssetManager assetManager, String path) {
		
		BlenderKey bk = new BlenderKey(path);
		scene = (Spatial) assetManager.loadModel(bk);

	}
	public Spatial get() {
		return scene;
	}
}
