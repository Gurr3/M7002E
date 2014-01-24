For setup and installation:
https://sites.google.com/site/justinscsstuff/jogl-tutorial-1
[2] https://sites.google.com/site/justinscsstuff/jogl-tutorial-2
https://sites.google.com/site/justinscsstuff/jogl-tutorial-3

For hints on how to use jogl, was used and copied from:
[1] http://www3.ntu.edu.sg/home/ehchua/programming/opengl/JOGL2.0.html

Multiple Viewport, inspiration sites:
http://www.java-tips.org/other-api-tips/jogl/multiple-viewports-nehe-tutorial-jogl-port.html
http://www.opengl.org/sdk/docs/man/xhtml/glViewport.xml
http://www.talisman.org/opengl-1.1/Reference/glRotate.html
http://stackoverflow.com/questions/9852791/opengl-glrotate-and-gltranslate-order

In the file Draw, some of the initialization, reshape and display was copied from [1].
In the file Figures, the "glRotatef " which chooses how to rotate the pyramid as well as the vertex values and color of the pyramid was copied from [1].
The frame was inspired by [2].


-------------
What's implemented

-Draws the outline of a square based pyramid.
uses glPolygonMode(GL.GL_FRONT_AND_BACK, GL2GL3.GL_LINE); to only show lines, and since only the outline was requested no square at the bottom was deemed necessary.


-Draws a filled in blue square.
a simple blue 2d square.

-Draws a green star (any kind of star you want).

Used two triangles in the same z position but rotated 180 degrees in the x,y space to make a 6-sided star.


-Has code that allows you to easily create additional squares, stars, and pyramids.

All figures are in the file figures and requires coordinates to be placed, the pyramid also takes a rotational variable.


-Preserves the openGL state, therefore your openGL commands will need to be encapsulated
in a way that the openGL state before one of your shapes is drawn, is preserved after the
shape is drawn. See glPushAttrib(), glPushMatrix(), glPopAttrib(), glPopMatrix().

The functions are called in the beginning and end of the figures.
glLoadIdentity(); is also used in Draw to preserve states in cases such as resizing the window.

-Have more than one viewport where you draw the scene from a different point of view.

In the function display, file = Draw. using 2 viewports to view the scene drawn from different angles. 
--------------
Program flow

Main starts the program and fetches the frame from gui.Gui_frame.
Main then starts an animator using draw.

draw implements GLEventListener and the four functions therein, of which init, reshape and display are used. init does the initialization where it clears cache, sets shaders and such.

reshape handles window resizing.

display is called to initiate rendering, in my case, it will clear buffers, reset the model view matrix with glLoadIdentity and then proceed to call the figures it wishes to render.

