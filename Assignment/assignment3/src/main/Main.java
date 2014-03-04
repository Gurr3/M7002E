package main;

import com.jme3.app.SimpleApplication;
import com.jme3.asset.TextureKey;
import com.jme3.cinematic.MotionPath;
import com.jme3.cinematic.MotionPathListener;
import com.jme3.cinematic.events.MotionEvent;
import com.jme3.input.KeyInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.light.DirectionalLight;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.FastMath;
import com.jme3.math.Quaternion;
import com.jme3.math.Spline.SplineType;
import com.jme3.math.Vector3f;
import com.jme3.post.FilterPostProcessor;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import com.jme3.shadow.DirectionalLightShadowFilter;
import com.jme3.shadow.DirectionalLightShadowRenderer;
import com.jme3.texture.Texture;
import com.jme3.texture.Texture.WrapMode;

import figures.FigureBlender;
import figures.FigureBox;

public class Main extends SimpleApplication{

	//	protected Geometry box1, box2, box3, sphere;
	protected Geometry floor; //instancing of box
	protected Geometry table, leg1, leg2, leg3,leg4; //more instancing of box
	protected Geometry chair, cleg1, cleg2, cleg3, cleg4;
	protected Spatial blendspat, blendspat2, opponent;
	
	
    private boolean active = false;
    private boolean playing = false;
    private boolean spinning = false;
	MotionPath blendspatPath = new MotionPath(), blendspat2Path = new MotionPath(), tablepath = new MotionPath();
	MotionEvent motionControl, motionControl2, motionControl3;

	private Material material_table_chess, material_floor, material_legs, material_chair;

	/**
	 * @param args
	 */
	public static void main(String[] args){
		Main app = new Main();
		app.start(); // start the game
	}

	@Override
	public void simpleInitApp() {
		flyCam.setMoveSpeed(25);

		initMaterials();
		initModels();
		setmaterials();
		setshadows();

		blendspat.setLocalScale(6);
		blendspat2.setLocalScale(5);
		blendspat.setLocalTranslation(15, 1, 11);
		blendspat2.setLocalTranslation(-15, 1, -10);

		opponent.setLocalTranslation(-50, -30, 0);
		opponent.setLocalScale(20);
		
		
		//1st pawn path
		blendspatPath.addWayPoint(new Vector3f(15,1,11));
		blendspatPath.addWayPoint(new Vector3f(-1,1,-4));
		blendspatPath.addWayPoint(new Vector3f(-15,1,-10));
		
		motionControl = new MotionEvent(blendspat,blendspatPath);
		motionControl.setDirectionType(MotionEvent.Direction.Path);
		motionControl.setInitialDuration(10f);
		motionControl.setSpeed(2f);  

		blendspatPath.addListener(new MotionPathListener() {
			@Override
			public void onWayPointReach(MotionEvent control, int wayPointIndex) {
				if (blendspatPath.getNbWayPoints() == wayPointIndex + 1) {
					System.out.println("pawn move finished");
				} else if (wayPointIndex!=0) {
					motionControl2.play(); 
				} else {
					System.out.println(0);
					motionControl2.setTime(0);
				}
			}
		});

		
		//2nd pawns path
		blendspat2Path.addWayPoint(new Vector3f(-15,1,-10));
		blendspat2Path.addWayPoint(new Vector3f(-20,2,-12));
		blendspat2Path.addWayPoint(new Vector3f(-25,1,-14));
		
		motionControl2 = new MotionEvent(blendspat2,blendspat2Path);
		motionControl2.setDirectionType(MotionEvent.Direction.PathAndRotation);
		motionControl2.setRotation(new Quaternion().fromAngleNormalAxis(-FastMath.HALF_PI, Vector3f.UNIT_Y));
		motionControl2.setInitialDuration(10f);
		motionControl2.setSpeed(4f); 
		
		//table spin
		tablepath.addWayPoint(new Vector3f(0,0,0));
		tablepath.addWayPoint(new Vector3f(0,1,0));
		tablepath.addWayPoint(new Vector3f(0,0,0));
		tablepath.addWayPoint(new Vector3f(0,-1,0));
		tablepath.addWayPoint(new Vector3f(0,0,0));
		motionControl3 = new MotionEvent(table, tablepath);
		motionControl3.setDirectionType(MotionEvent.Direction.PathAndRotation);
		motionControl3.setInitialDuration(5f);
		
		//Set lights
		DirectionalLight sun = new DirectionalLight();
		sun.setColor(ColorRGBA.White);
		sun.setDirection(new Vector3f(.5f,-.5f,-.5f).normalizeLocal());
		rootNode.addLight(sun);

		// Set shadows
		final int SHADOWMAP_SIZE=1024;
		DirectionalLightShadowRenderer dlsr = new DirectionalLightShadowRenderer(assetManager, SHADOWMAP_SIZE, 3);
		dlsr.setLight(sun);
		viewPort.addProcessor(dlsr);

		DirectionalLightShadowFilter dlsf = new DirectionalLightShadowFilter(assetManager, SHADOWMAP_SIZE, 3);
		dlsf.setLight(sun);
		dlsf.setEnabled(true);
		FilterPostProcessor fpp = new FilterPostProcessor(assetManager);
		fpp.addFilter(dlsf);
		viewPort.addProcessor(fpp);

		//create a node to which to attach all things
		Node pivot = new Node("pivot");
		rootNode.attachChild(pivot);
		attachToNode(pivot);
		initInputs();
	}
	private void setshadows(){
		blendspat.setShadowMode(com.jme3.renderer.queue.RenderQueue.ShadowMode.CastAndReceive);
		blendspat2.setShadowMode(com.jme3.renderer.queue.RenderQueue.ShadowMode.CastAndReceive);
		
		table.setShadowMode(com.jme3.renderer.queue.RenderQueue.ShadowMode.CastAndReceive);
		leg1.setShadowMode(com.jme3.renderer.queue.RenderQueue.ShadowMode.CastAndReceive);
		leg2.setShadowMode(com.jme3.renderer.queue.RenderQueue.ShadowMode.CastAndReceive);
		leg3.setShadowMode(com.jme3.renderer.queue.RenderQueue.ShadowMode.CastAndReceive);
		leg4.setShadowMode(com.jme3.renderer.queue.RenderQueue.ShadowMode.CastAndReceive);
		
		chair.setShadowMode(com.jme3.renderer.queue.RenderQueue.ShadowMode.CastAndReceive);
		cleg1.setShadowMode(com.jme3.renderer.queue.RenderQueue.ShadowMode.CastAndReceive);
		cleg2.setShadowMode(com.jme3.renderer.queue.RenderQueue.ShadowMode.CastAndReceive);
		cleg3.setShadowMode(com.jme3.renderer.queue.RenderQueue.ShadowMode.CastAndReceive);
		cleg4.setShadowMode(com.jme3.renderer.queue.RenderQueue.ShadowMode.CastAndReceive);
		
		opponent.setShadowMode(com.jme3.renderer.queue.RenderQueue.ShadowMode.CastAndReceive);
		floor.setShadowMode(com.jme3.renderer.queue.RenderQueue.ShadowMode.Receive);
	}

	private void setmaterials(){
		table.setMaterial(material_table_chess);
		leg1.setMaterial(material_legs);
		leg2.setMaterial(material_legs);
		leg3.setMaterial(material_legs);
		leg4.setMaterial(material_legs);
		
		floor.setMaterial(material_floor);
		
		chair.setMaterial(material_chair);
		cleg1.setMaterial(material_chair);
		cleg2.setMaterial(material_chair);
		cleg3.setMaterial(material_chair);
		cleg4.setMaterial(material_chair);
	}

	private void initModels(){
		table = new FigureBox(assetManager, new int[]{28,1,20}, new Vector3f(0,0,0), ColorRGBA.Orange).get();
		leg1 = new FigureBox(assetManager, new int[]{1,14,1}, new Vector3f(27,-15,19), ColorRGBA.White).get();
		leg2 = new FigureBox(assetManager, new int[]{1,14,1}, new Vector3f(-27,-15,19), ColorRGBA.White).get();
		leg3 = new FigureBox(assetManager, new int[]{1,14,1}, new Vector3f(27,-15,-19), ColorRGBA.White).get();
		leg4 = new FigureBox(assetManager, new int[]{1,14,1}, new Vector3f(-27,-15,-19), ColorRGBA.White).get();
		
		floor = new FigureBox(assetManager, new int[]{100,1,100}, new Vector3f(0,-29,0), ColorRGBA.Red).get();
		
		cleg1 = new FigureBox(assetManager, new int[]{1,7,1}, new Vector3f(34,-21, -9), ColorRGBA.White).get();
		cleg2 = new FigureBox(assetManager, new int[]{1,7,1}, new Vector3f(46,-21, -9), ColorRGBA.White).get();
		cleg3 = new FigureBox(assetManager, new int[]{1,7,1}, new Vector3f(34,-21, 9), ColorRGBA.Blue).get();
		cleg4 = new FigureBox(assetManager, new int[]{1,7,1}, new Vector3f(46,-21, 9), ColorRGBA.White).get();
		chair = new FigureBox(assetManager, new int[]{10,1,12}, new Vector3f(40,-14,0), ColorRGBA.Green).get();
		
		blendspat = new FigureBlender(assetManager,"chess-pawn_white.blend").get();
		blendspat2 = new FigureBlender(assetManager,"chess-pawn_black.blend").get();
		
		opponent = new FigureBlender(assetManager, "chess-pawn_black.blend").get();
	}
	private void attachToNode(Node pivot){
		pivot.attachChild(floor);
		
		pivot.attachChild(table);
		pivot.attachChild(leg1);
		pivot.attachChild(leg2);
		pivot.attachChild(leg3);
		pivot.attachChild(leg4);
		
		pivot.attachChild(chair);
		pivot.attachChild(cleg1);
		pivot.attachChild(cleg2);
		pivot.attachChild(cleg3);
		pivot.attachChild(cleg4);
		
		pivot.attachChild(blendspat);
		pivot.attachChild(blendspat2);		
		pivot.attachChild(opponent);
	}

	private void initMaterials(){
		material_table_chess = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
		TextureKey ceiling_key = new TextureKey("/test.png");
		ceiling_key.setGenerateMips(true);
		Texture ceiling_texture = assetManager.loadTexture(ceiling_key);
		ceiling_texture.setWrap(WrapMode.Repeat);
		material_table_chess.setTexture("ColorMap", ceiling_texture);

		material_legs = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
		TextureKey leg_key = new TextureKey("/Wood-Textures-Wallpaper-HD.jpg");
		leg_key.setGenerateMips(true);
		Texture leg_texture= assetManager.loadTexture(leg_key);
		leg_texture.setWrap(WrapMode.Repeat);
		material_legs.setTexture("ColorMap", leg_texture);
		
		material_floor = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
		Texture floor_texture= assetManager.loadTexture("wood _texture1582.jpg");
		floor_texture.setWrap(WrapMode.MirroredRepeat);
		material_floor.setTexture("ColorMap", floor_texture);
		
		material_chair = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
		Texture chair_texture= assetManager.loadTexture("wood _texture33.jpg");
		chair_texture.setWrap(WrapMode.Repeat);
		material_chair.setTexture("ColorMap", chair_texture);
		

	}

	private void initInputs() {
		inputManager.addMapping("display_hidePath", new KeyTrigger(KeyInput.KEY_P));
		inputManager.addMapping("SwitchPathInterpolation", new KeyTrigger(KeyInput.KEY_I));
		inputManager.addMapping("tensionUp", new KeyTrigger(KeyInput.KEY_U));
		inputManager.addMapping("tensionDown", new KeyTrigger(KeyInput.KEY_J));
		inputManager.addMapping("play_stop", new KeyTrigger(KeyInput.KEY_SPACE));
		inputManager.addMapping("table_up_and_down", new KeyTrigger(KeyInput.KEY_N));
		ActionListener acl = new ActionListener() {

			public void onAction(String name, boolean keyPressed, float tpf) {
				if (name.equals("display_hidePath") && keyPressed) {
					if (active) {
						active = false;
						blendspatPath.disableDebugShape();
						blendspat2Path.disableDebugShape();
						tablepath.disableDebugShape();
					} else {
						active = true;
						blendspatPath.enableDebugShape(assetManager, rootNode);
						blendspat2Path.enableDebugShape(assetManager, rootNode);
						tablepath.enableDebugShape(assetManager, rootNode);
					}
				}
				if (name.equals("play_stop") && keyPressed) {
					if (playing) {
						playing = false;
						motionControl.stop();
						motionControl2.stop();
					} else {
						playing = true;
						motionControl.play();
					}
				}

				if (name.equals("SwitchPathInterpolation") && keyPressed) {
					if (blendspatPath.getPathSplineType() == SplineType.CatmullRom){
						blendspatPath.setPathSplineType(SplineType.Linear);
					} else {
						blendspatPath.setPathSplineType(SplineType.CatmullRom);
					}
				}

				if (name.equals("tensionUp") && keyPressed) {
					blendspatPath.setCurveTension(blendspatPath.getCurveTension() + 0.1f);
					System.err.println("Tension : " + blendspatPath.getCurveTension());
				}
				if (name.equals("tensionDown") && keyPressed) {
					blendspatPath.setCurveTension(blendspatPath.getCurveTension() - 0.1f);
					System.err.println("Tension : " + blendspatPath.getCurveTension());
				}
				if (name.equals("table_up_and_down") && keyPressed) {
					if (spinning){
						spinning = false;
						motionControl3.stop();
					} else {
						spinning = true;
						motionControl3.play();
					}

				}
				
			}
		};

		inputManager.addListener(acl, "display_hidePath", "play_stop", "SwitchPathInterpolation", "tensionUp", "tensionDown", "table_up_and_down");

	}


	@Override
	public void simpleUpdate(float tpf) {


	}

}
