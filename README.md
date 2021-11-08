[![DaniDoesDev](https://circleci.com/gh/DaniDoesDev/CleanSweep.svg?style=shield)](https://app.circleci.com/pipelines/github/DaniDoesDev/CleanSweep)

# CleanSweep
Agile Group Project - Team Dirt Destroyers!

# Sprint 3

For Sprint 3 we wanted to add extra functionality to our portal and ensure proper testing and automated building and running of these tests is employed with each build, as well as wrap up previously in progress stories such as complete dirt detection and cleaning capabilities.

* Note - The cleaning portal has been moved to its own repository for separate building and running with a separate pom file. The files can still be found here, but the complete build can now be found at https://github.com/DaniDoesDev/Clean-Sweep-Portal

# Sprint 2

For Sprint 2 we wanted to start adding onto the base functionality completed in Sprint 1. Our priorities included power management, and the user portal component. Our stories include:

* Cleaning - Refactor
    * Increase the modularity and encapsulation of the previous cleaning story by moving cleaning to its own method and adding dirt decrementing capabilities for the Robot to use as required.
    * Acceptance criteria - Cleaning has same base functionality but can now be used by calling encapsulated method.
* Power Management - Battery Percentage
    * Create methods that will drain the Robot battery appropriately when moving or cleaning based on floor type.
    * Ensure that Robot moves back to a charging station before dying.
* Floor Plan - Surfaces
    * Related to above, ensures that the sensor can detect the type of floor the Robot is on and can detect if the surface must be cleaned.
* Dirt Detection - Full Implementation
    * Add varying amounts of dirt to the layout and include a method that allows the sensor to detect if an area is dirty.
* Activity Log
    * Allow the Clean Sweep to log its history for future use.
* Registration
    * Allow users to register their Clean Sweep on a portal.
* Repeated Cleaning
    * Allow users to schedule cleanings.

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