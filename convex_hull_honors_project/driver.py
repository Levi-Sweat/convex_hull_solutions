import random
import algorithms

class Point:
    """
    A class to represent a point in the plane.

    Attributes:
    x - The x coordinate of the point.
    y - The y coordinate of the point.
    """

    def __init__(self, x, y):
        """
        Constructs a new point with the given x and y coordinates.
        """
        
        self.x = x
        self.y = y

    #used for sorting the points by y value in the merge sort
    def y_val_equals(self, other):
        """
        Compares the y values of two points. If they are equal, compare the x values.

        Args:
        other - The point to compare to.

        Returns: -1 if self is less than other, 1 if self is greater than other, 
        0 if they are the same.
        """

        result = 0
        if self.y < other.y:
            result = -1
        elif self.y > other.y:
            result = 1
        else: #if the y values are equal, sort by x value
            if self.x < other.x:
                result = -1
            else:
                result = 1
        return result
    
    def __str__ (self):
        """
        To string method for the point class

        Returns: (x, y)
        """

        return "(" + str(self.x) + ", " + str(self.y) + ")"


def build_points(numPoints):
    """
    Creates a list of random points in the range [0, 100] for both x and y.
    
    Args:
    numPoints - The number of points to create.
    
    returns: A list of random points.
    """

    points = []

    for i in range(numPoints):
        x = random.randint(0, 100)
        y = random.randint(0, 100)
        points.append(Point(x, y))

    return points

def user_input():
    """
    Prompts the user for how many points to create and returns their input.

    Returns: How many points to create.
    """
    keep_going = True
    while keep_going:
        print("How many random points would you like to plot?")
        try:
            numPoints = int(input())
            if numPoints < 3: # algorithms work best with at least 3 points
                print("Please enter a value greater than 2.")
            else:
                keep_going = False
        except ValueError:
            print("Please enter an integer value.")
    return numPoints

def decide_method():
    """
    Prompts the user for which algorithm to use and returns their input.
    
    Returns: 1 for Graham's Scan, 2 for Brute Force, 3 for Output Sensitive.
    """

    keep_going = True
    while keep_going:
        print("Which algorithm would you like to use to comput the convex hull?")
        print("1. Graham's Scan\n2. Brute Force\n3. Output Sensitive")
        method = input()
        if method == "1" or method == "2" or method == "3":
            keep_going = False
        else:
            print("Please enter either 1, 2, or 3.")
    return method

def main():
    """
    Entry point for the program. Takes in the user's input for how many points to create and
    which algorithm to use.
    """

    points = build_points(user_input())
    algo = decide_method()
    if algo == "1":
        algorithms.graham_scan(points)
    elif algo == "2":
        algorithms.brute_force(points)
    elif algo == "3":
        algorithms.output_sensitive(points)


if __name__ == "__main__":
    main()