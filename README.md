# Koalabear
# Introduction
Koala is java based and the main idea of the game was to learn and implement Object Oriented Programming. The developer's guide contains information on how to use the games and how they function internally.Koala Bear is a one player game with maze where you have to guide three koala bears to get through the exit safely.For this game, I was able to reuse the some of the pattern and classes same as the first game. I implemented seven classes with the resources file for this game. It is one player game where player has to save three koala bear and take them to exit safely to continue to next level. I programed it to have two levels. All three of the bears need to be saved to get to next level. 
# Development environment
a. Version of Java Used: Java 11.0.3
b. IDE Used: IntelliJ IDEA 2019.2.2 (Ultimate edition)

# Rules to control my second game (KoalaBr8):
Up Arrow: Move up
Down Arrow: Move down
Right Arrow: Move right
Left Arrow: Move left

# How to Build/Import your Project
First, Open git bash clone the repository from GitHub.Then, Open the IntelliJ app and click on Import Project and then get the folder where your game is stored and import it. Next select on create project from existing sources and do next. After that it asks for project name, write the same name as your folder that you got from git hub and do next. Then, keep doing next until you reach the page where it says finish and click on the finish button. Then you can see the build tab on top of the IntelliJ app where you can see build project or find the build icon and click on it build your project.

# List what Commands that were ran when building the JAR. Or Steps taken to build jar.
The steps that I took to create the jar is that on IntelliJ I went to files and clicked on project structure. Then a window appeared and then on the left side of the window we can see many options and from the option I clicked on artifacts and clicked on the plus sign that appears in the window where I clicked on the JAR option and clicked on empty. Then I selected the main of the game and then the window with META/MANIFEST file appeared and I clicked on the ok button. After that I went on the build tab on top of the window and clicked on build artifact and selected the option build. This is how I created my jar. It got created on the out folder inside artifact so to run it from IntelliJ I clicked on out and opened the jar and did right click and choose the option run the jar and my game opened on the jar.

# How to run your game. As well as the rules and controls of the game.
To run the game open the main of the program which is Game_Start for KoalaBeargame. Then, the first way is to locate and click the small green button at the top of the window. Another way to run your program is to click on the "Run" tab at the top of the window. We can also run the jar to run the game.

# Class Descriptions of classes specific to KoalaBr
# Object (ImageObject and GameObject)
GameObject is used for KoalaBr8. This is an abstract class that is the parent class of all other game objects ( Koalas, Walls, etc.). It contains a basic draw function that the objects can use. It also supplies all methods and fields that the objects have in common, including object width, height, y-position, and y-position.
# Control (Control_Panel and GameControl)
GameControl is used for KoalaBr8. This class extends Observable and implements KeyListener. It checks which keys are pressed and then invokes the corresponding object’s  Koala) method. The main class has a Control (Control_Panel) object for each moveable object  (Koala).
# Wall (Wall and GameWall)
GameWall is used in KoalaBr8. This class extends the object class that is listed above. It contains the resources to create the wall and supplies methods for wall width, height, and width height for map.
# Background (Picture_icon and GameBackground)
Picture_icon is used in tank game and GameBackground is used in KoalaBr8. This class draws a tiled Background from an Image file.
# KoalaBrGame
This is the main class. It extends JApplet and implements Runnable. This class uses Java SWING for the GUI. It contains a method addKoala() that creates and adds new Koala objects to the game. Like with the Tank Game, this main class essentially creates the whole game environment, drawing the background and map, calling update and draw on all game objects.
# KoalaBears
This class extends GameObject and implements Observer. This class also has an update method to move the Koala. One of the most import things the Koala class does is checks for collisions with walls, hazards, and other Koalas. It uses Rectangle to check for these collisions. This class also keeps track of the number of Koalas that have been rescued.
# GameEvents
This class extends Observable.
