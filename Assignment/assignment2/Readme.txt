Program Structure:

gui
	CustomCreateDialog
		Contains the code for the popup that is used when modifying or creating objects.
	Draw_figures
		Contains the loop that goes through the list of figures and calls each paint function.
	Figure_deployment_type
		The figures are of this type
	Figure_modification_type
		When modifying a deployment type, this is used to store the intermediate vairables
	Figures
		Each figures paint function is contained here
	Gui_frame
		The window that is used to hold everything and its listeners are contained here

listener
	Mouse
		Contains a mouse listener that tracks the point clicked and sends it to draw for storage until its use.
main
	Draw
		implements GLEventListener, has the displayloop and is also owner of variables that passes between different classes, such as the picked figure and the list of figures.
	Main
		Main function, calls Start.
	Pick
		Used for picking which object was hit on mouseclick.
	Start
		Starts the program up.

.............

Sites used:

CustomCreateDialog is based on http://darksleep.com/player/DialogExample/CustomDialog.java.html
Pick is based on http://www.java-tips.org/other-api-tips/jogl/another-picking-example-in-jogl.html and http://jogamp.org/jogl-demos/src/demos/misc/Picking.java
Material usage and lights for the painting of figures was inspired by http://www.java-tips.org/other-api-tips/jogl/several-spheres-are-drawn-using-different-material-characteri-2.html

.............

What's implemented

• Create and manipulate shapes. The program should allow three different types of shapes,
such as cube, pyramid, sphere, star, or whatever you like.

Used the 3 figures from the first lab, but also included from glut the cube and the sphere to get better visibility of the changes that lighting gives you.

• To select an object on the screen to be moved, resized, or deleted, the user should click on it
with the mouse.

Pick is used for this, it creates a new scene that only contains a small box around where the mouse was clicked, and then chooses the object that has the closest z coordinate. This should probably check the exterior of the figure with something like ray casting, but does only check the figures z coordinate.

• Create a light source, specify the light components (ambient, specular, diffuse etc).
	Decided that this should become a figure type, so it's accessible in the menu Create, in the program it is of the the Figure_deployment_type.

• Create shapes with customized material components (ambient, specular, diffuse).
• Create shapes at a given position and of a given size.
	Accessible from the Create menu, in the program it is of the the Figure_deployment_type.

• Move an object.
	From the modify toolbar all components that can be clicked(so lights are not accessible) can get a total overhaul with the same menu as when creating them. It's necessary to click on a figure before doing this.

• Delete an object.
	Accessible from the modify toolbar, you need to have clicked on an object to do this.

• Delete all objects.
	Accessible from the Other toolbar, clears the arraylist with all figures in it(the list is contained in Draw).

• Print out your data structure as text (i.e., the data you would need to redraw the screen or to
start up the program again with the same arrangement of shapes).
	Print is accessible from the Other toolbar, it calls the deployment types toString from the listener.

• Exit the program.
	The window gets the operative systems exit function for windows, in windows it's the red X in the top right corner(unless modified).

.............

Program flow

Main calls Start.
Start creates the gui_frame and then starts of the animator draw.

gui_frame has listeners and knows of draw, it also owns the popups that occur when creating or modifying figures.

Draw has important variables such as the list of active objects and objects picked. It also implements GLEventListener and does the work of displaying and such.

	If the mouse was clicked, in the display function this will call pick.

On create from the toolbar, the popup then reads what the user has written and creates a figure_deployment_type, which the gui_frame then receives and puts into the figurelist in draw.


