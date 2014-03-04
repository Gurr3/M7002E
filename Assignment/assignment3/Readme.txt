Installation instructions:

You must include the blenders folder and the textures folder into the workspace, github- note that no texture folder was included here, so these needs replacements.

-------------

Textures
http://bgfons.com/upload/wood%20_texture33.jpg
http://walldie.com/wp-content/uploads/2013/11/Wood-Textures-Wallpaper-HD.jpg
http://bgfons.com/upload/wood%20_texture1582.jpg
http://fc01.deviantart.net/fs70/i/2012/023/5/e/wood_texture_02_by_angryshaolin-d4nd1w6.jpg
http://www.pptbackgroundstemplates.com/backgrounds/wood-textures-ppt-backgrounds-powerpoint.jpg

Models
Pawn- made by me using Blender
Box, standard box from jme3

--------------------

References:

Lighting
http://hub.jmonkeyengine.org/wiki/doku.php/jme3:advanced:light_and_shadow

Tutorials for getting started, materials, assets and basic usage of the loop
http://hub.jmonkeyengine.org/wiki/doku.php/jme3#tutorials_for_beginners

Motion:
http://code.google.com/p/jmonkeyengine/source/browse/trunk/engine/src/test/jme3test/animation/TestMotionPath.java
--------------
Buttons 

move around: jme3 standard buttons

space, change between play/stop pawn motions
n, move table slightly up and down

other, from motion reference example:
P, display or hide motionpath
I, switch path interpolation (pawns path)
U, tension up (pawns path)
J, tension down (pawns path)

-------------
Bugs
	Main.java:91
		motionControl2.setCurrentWayPoint(0);
	Does not seem to work the way I intended which is to move the spatial that motionControl2 controls to the first waypoint, which would be an effective reset of its placement.
	But since the assignment does not require that the scene must be resetable I will leave this as is.

-------------
Main points

Model creation, created the chess pieces using blender, the .blend files are included

Instancing, using chess-pawn_black.blend multiple times, box from jme3 is also used multiple times

Smooth motion, using motionpath to move the pawns/table

Enclosed environment, The showdown of the chess tournament, final move, only you, your opponent and the chess exists in your mind.

-------------
Extra Points

Textures and complex shapes, most things got textures, like the table
	The chess pieces was made from blender to look like that

Simple Constraints
	The 2nd chess piece may only move when the first has hit the 2nd waypoint.

-------------

Program Flow

	Figures, contains classes that encapsulates code for getting models
	
	Main
		The first important things it does is
		
		init
			materials
			models
		set
			materials
			shadows (for models)
		
		Then, still inside the simpleInitApp i've defined the paths and motioncontrols as well as the listener of when the first piece is in a certain position
		
		how lights works are then set, as well as the shadows
		
		Then all the models are attached to a node called pivot that's bound to the root node
		
		and lastly all the keyboard commands are initialized.
		
		
