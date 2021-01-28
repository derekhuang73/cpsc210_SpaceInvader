# My Personal Project

## *Space Invaders*

 **What will the application do?**
- It is a 2D game that a player will control a *jet* and shoot down the *space invaders*.
- It records the score of current player. The higher score will generate more difficult level.

**Who will use it?**
- It can be used by single player;

**Why is this project of interest to you?**
- I have been a game player for long time, which 
provides me a lot of knowledge of game as well as the 
interest of making my own game 

## *user story*
- As a user, I want to be able to creat a new jet and add it to game.
- As a user, I want to be able to select the level of difficult from 1 to 5 to start the game,
and add a new statistic for my-jet accordingly;
for example  moving speed.
- As a user, I want to be able to move my jet up down left right.
- As a user, I want to be able to shoot missile.
- As a user, I want to be able to save my progress of the game.
- As a user, I want to  have a option to load my previous game progress. 

*How to play*
- press keyboard arrow key to move your jet up down left right.
- press space key to shoot in your jet coordinate
- click mouse to shoot in any place if you have mouse shoot chance
- if you get hit by missile from invaders you lose
- if invaders pass the bottom line you lose
- 10 point to level up
- press keyboard X to exit and auto save, game is auto load.
 
 *Phase 4 task 2*
 - Option: type hierarchy;
 - Classes Jet and Invader Extended Class Airplane;
 - Method that override : shoot();
 - Both Jet and Invader override shoot and they shoot missiles in different type.
 
 *Phase 4 task 3*
 - Reduce coupling in class GameGraphic by adding a new method drawInCoordinate(), 
 which take care of image shifting due to the image width and height, instead of having them separately
 - Increase cohesion by moving the method relate to invade (move every invader in the invaderlist) to the class invaderlist
  instead of leaving it in class Game
 - UML_Design_Diagram saved in src -> main -> image