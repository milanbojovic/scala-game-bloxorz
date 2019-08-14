# **Functional programming game development assignment**

----------

University of Belgrade																									
Faculty of Electrical Engineering,
Course: Functional programming 13M111FP (2018-2019)




### **Assignment** 
Write a program in the Scala programming language to realize one variant of the game
Bloxorz (http://www.bloxorz.org.uk/). The game is based on the movement of a block of dimensions 1x1x2 through terrain of complex shape. The playground consists of plates on which the block
moves by rolling with four commands (up, down, left, right). The object of the game is
bring the block from the starting position to the target position without dropping from edge of terrain. The game can be completed in two ways:
• Victory - when the block reaches the target position so that it is on the target board set upright;
• Defeat - when the block moves beyond the boundaries of the terrain or stands upright to one of the special plates.

Full assignment requirements for the game can be found in pdf file which is added to this repo itself: https://github.com/milanbojovic/scala-game-bloxorz/blob/master/FP_Projekat_2019.pdf 
Please note that it is in Serbian language and you might need to use some translator app.


### **Implementation details**
Game is developed using Scala programming language and InteliJ IDE as a SBT project.
There are three possible modes to play the game:


 1. **Manual play** (block is moved through the board using arrow keys: &#8593;, &#8595;, &#8592;, &#8594; or w, s, a, d).
 2. **Automatic play** (moves are loaded from file *"input_moves_sequence.txt"* structure of the file should be one character represending direction per line allowed characters: *u, d, l, r*).
 3. **Solution finder** (game will find the best solution for moving the block from starting to target position and results will be put to file *"output_moves_sequence.txt"*).


### **How to run the game:**


 1. Clone github repository
 2. cd scala-game-bloxorz
 3. sbt run
 4. Use: w, s, a, d keys to select meny option and press enter

