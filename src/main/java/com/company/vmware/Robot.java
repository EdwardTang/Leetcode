package com.company.vmware;

import java.util.LinkedList;

/**
 * Design Robot class that can move around on a two dimensional plane. It needs to be able to change
 * its position, report its position and report its last move as described below. Implement a Robot
 * class per following specifications: Fields: integer currentX - The robot's current x-coordinate
 * in the 2D plane integer currentY - The robot's current y-coordinate in the 2D plane integer
 * previousX - The robot's x-coordinate in the 2D plane prior to its most recent movement integer
 * previousY - The robot's y-coordinate in the 2D plane prior to its most recent movement **Note: **
 * The robot's initial location is at (x,y) coordinate (0,5)
 *
 * <p>Parameterized Constructor: x - The value of currentX for the new Robot y - The value of
 * currentY for the new Robot
 *
 * <p>public Robot(integer x, integer y) { } The robot created by this constructor is considered to
 * have spawned at(0,5) and moved to (currentX, currentY) so (previousX, previousY) starts as (0,5)
 *
 * <p>Methods:
 *
 * <p>void printCurrentCoordinates() - Print two space-separated integers describing the robot's
 * current x and y coordinates.
 *
 * <p>void moveX(integer dx) - Move the robot from current position(x,y) to new position(x+dx, y).
 * Remember to maintain previousX.
 *
 * <p>void moveY(integer dy) - Move the robot from current position(x,y) to new position(x, y+dy).
 * Remember to maintain previousY.
 *
 * <p>void printLastCoordinates() - Print two space-separated integers describing the robot's
 * previousX and previousY coordinates. This will be called after the robot has moved from position
 * (0,5) at least once
 *
 * <p>void printLastMove() - Print two space-separated values describing the robot's most recent
 * movement
 *
 * <p>Sample input: 2 1, 1, 2, 0
 *
 * <p>Sample output: 0 5 , 2 1, x 1, 3 1, 3 1, x 2, 5 2, 5 2
 *
 * <p>Explanation: The firstRobot object is initially at position (0,5), so the call to
 * firstRobot.printCurrentCoordinates() prints 0 5 For the secondRobot object created with the
 * parameterized constructor, it was created and moved from (0,5) -> (2,1) so
 * secondRobot.printCurrentCoordinates() prints 2 1. Next, we call the following sequence of methods
 * on the secondRobot object.
 *
 * <p>secondRobot.moveX(1) moves the robot 1 unit from (2,1) -> (3,1)
 *
 * <p>secondRobot.printLastMove() prints x 1 as its last movement was moveX(1)
 *
 * <p>secondRobot.printCurrentCoordinates() prints 3 1 because it moved from (2,1) ->(3,1)
 *
 * <p>secondRobot.moveY(1) moves the robot 1 unit from (3,1) -> (3,2)
 *
 * <p>secondRobot.printLastCoordinates() prints 3 1 bcause its last movement was (3,1) -> (3,2), so
 * the coordinates of its last location prior to the movement was (3,1) At this point, the test code
 * adds 1 to dx => dx = 2 and subtracts 1 from dy => dy = 0
 *
 * <p>secondRobot.moveX(2) moves the robot from the (3,2) -> (5,2)
 *
 * <p>secondRobot.printLastMove() prints x 2 as its last movement was moveX(2)
 *
 * <p>secondRobot.printCurrentCoordinates() prints 5 2 because it moved from (3,2) -> (5,2)
 *
 * <p>secondRobot.moveY(0) moves the robot 0 units from (5,2) -> (5,2)
 * secondRobot.printLastCoordinates() prints 5 2 because its last movment was (5,2) -> (5,2)
 */

/**
 * High level: Use 4 stacks to keep track on robot's coordinate X and Y's position history and move
 * history respectively. So the above example looks like the following with 4 stacks:
 *
 * <p>X  [0  2 3 3 5 5
 *
 * <p>dX [0  2 1 0 2 0
 *
 * <p>Y  [5  1 1 2 2 2
 *
 * <p>dY [0 -4 0 1 0 0
 */
public class Robot {
  public static int INIT_X = 0;
  public static int INIT_Y = 5;

  private LinkedList<Integer> xStack =
      new LinkedList<Integer>() {
        {
          add(INIT_X);
        }
      };
  private LinkedList<Integer> dxStack =
      new LinkedList<Integer>() {
        {
          add(0);
        }
      };
  private LinkedList<Integer> yStack =
      new LinkedList<Integer>() {
        {
          add(INIT_Y);
        }
      };
  private LinkedList<Integer> dyStack =
      new LinkedList<Integer>() {
        {
          add(0);
        }
      };

  public Robot(int x, int y) {
    dxStack.addLast(x - xStack.getLast());
    xStack.addLast(x);
    dyStack.addLast(y - yStack.getLast());
    yStack.addLast(y);
    System.out.println("initial curr (x, y): " + xStack.getLast() + " " + yStack.getLast());
    System.out.println(
        "initial last (x, y): "
            + xStack.get(xStack.size() - 1 - 1)
            + " "
            + yStack.get(yStack.size() - 1 - 1));
  }

  public void move(int dx, int dy) {
    dxStack.addLast(dx);
    dyStack.addLast(dy);
    xStack.addLast(xStack.getLast() + dx);
    yStack.addLast(yStack.getLast() + dy);
  }

  public void moveX(int dx) {
    move(dx, 0);
  }

  public void moveY(int dy) {
    move(0, dy);
  }

  public void printCurrentCoordinates() {
    System.out.println("current (x, y): " + xStack.peekLast() + " " + yStack.peekLast());
  }

  public void printLastCoordinates() {
    int lastX = xStack.getLast() - dxStack.getLast();
    int lastY = yStack.getLast() - dyStack.getLast();
    System.out.println("last (x, y): " + lastX + " " + lastY);
  }

  public void printLastMove() {
    int lastDx = dxStack.getLast();
    int lastDy = dyStack.getLast();
    System.out.println(
        "last move: " + (lastDx == 0 ? "x" : lastDx) + " " + (lastDy == 0 ? "x" : lastDy));
  }

  public static void main(String[] args) {

    Robot secondRobot = new Robot(2, 1);
    secondRobot.moveX(1); // (2, 1) -> (3, 1)
    secondRobot.printLastMove(); // (1, x)
    secondRobot.printCurrentCoordinates(); // (3,1)
    secondRobot.moveY(1); // (3, 1) - > (3, 2)
    secondRobot.printLastCoordinates(); // (3, 1)
    secondRobot.moveX(2); // (3,2) -> (5, 2)
    secondRobot.printLastMove(); // (2, x)
    secondRobot.printCurrentCoordinates(); // (5, 2)
    secondRobot.moveY(0); // (5, 2) -> (5, 2)
    secondRobot.printLastCoordinates(); // (5, 2)
  }
}
