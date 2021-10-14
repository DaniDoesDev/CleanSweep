# CleanSweep
Agile Group Project - Team Dirt Destroyers!

# Sprint 1

For Sprint 1 we wanted to concentrate on both getting local environments set up, getting everyone successfully connected to the shared GitHub repo, and getting basic Clean Sweep functionality in place. This functionality includes:

* Navigation - Basic Movement
    * Acceptance criteria - Clean sweep is able to move horizontally and vertically one grid space at a time. If it is blocked, it will not move in the direction specified, print out a message indicating this, and return false. Otherwise, it will print out a message that it successfully navigated to the new grid space and return true. 
    * Testing - JUnit tests have been implemented in order to test each directional movement method and assert that the return coordinates are as expected based on whether the Clean Sweep was blocked or not.
* Navigation - Basic Obstacle Detection
* Basic Dirt Detection
* Basic Cleaning
* Basic Floor Type Detection

# System Description

* Main.java: This file creates a new robot by calling `new Robot();`.
* Robot.java: Whenever the robot starts, it turns on its object detection system,
 its floor plan system, adds 5 sensors, turns on its sensor simulator system, and 
 sets its coordinates to (1,1).  
* ObjectDetection.java:
To run the system, call `blocked("<direction>")`. This will read all the sensors, update the floor plan,
and return whether the direction is blocked.
* FloorPlan.java: Starts with empty Hashmap and has two useful functions: `updateFloorPlan()`
to write any object to floor plan coordinate; and `readFloorPlanCoordinate()` to read a coordinate.
* Sensor.java: Reads from the sensor simulator. Each sensor has a direction 1-5, 
1-4 = directional and 5 = down. (Todo: add floor type sensor.)
* SensorSimulator.java: Creates a 10 x 10 layout where the first and last
rows and the first and last columns are always walls. The other cells are randomized.
(The robot's starting point is always "clear".)
