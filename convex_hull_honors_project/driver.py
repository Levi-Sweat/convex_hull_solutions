from math import atan2
from matplotlib.pylab import det, randint
import matplotlib.pyplot as plt
import numpy as np
import random
from functools import cmp_to_key
import algorithms

class Point:
    def __init__(self, x, y):
        self.x = x
        self.y = y

    #used for sorting the points by y value in the merge sort
    def y_val_equals(self, other):
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
        return "(" + str(self.x) + ", " + str(self.y) + ")"

def display_result(points):
        
    plt.style.use('_mpl-gallery')

    fig, ax = plt.subplots()  # Create a figure containing a single axes.

def build_points(numPoints):
    points = []

    for i in range(numPoints):
        x = random.randint(0, 100)
        y = random.randint(0, 100)
        points.append(Point(x, y))

    return points

def user_input():
    keep_going = True
    while keep_going:
        print("How many random points would you like to plot?")
        try:
            numPoints = int(input())
            keep_going = False
        except ValueError:
            print("Please enter an integer value.")
    return numPoints

def decide_method():
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

def display_graham_scan(points, hull):

    plt.style.use('_mpl-gallery')

    fig, ax = plt.subplots()  # Create a figure containing a single axes.

    for i in range(len(points)):
        ax.scatter(points[i].x, points[i].y)
    
    for i in range(len(hull) - 1):
        print("init")
        ax.plot([hull[i].x, hull[i + 1].x], [hull[i].y, hull[i+1].y])

    ax.plot([hull[-1].x, hull[0].x], [hull[-1].y, hull[0].y])

    plt.show()

def display_brute_foce(points, hull):
    plt.style.use('_mpl-gallery')

    fig, ax = plt.subplots()  # Create a figure containing a single axes.

    for i in range(len(points)):
        ax.scatter(points[i].x, points[i].y)    

    for i in range(len(hull)):
        ax.plot([hull[i][0].x, hull[i][1].x], [hull[i][0].y, hull[i][1].y])

    plt.show()


def main():
    points = build_points(user_input())
    algo = decide_method()
    if algo == "1":
        hull = algorithms.graham_scan(points)
        display_graham_scan(points, hull)
    elif algo == "2":
        hull = algorithms.brute_force(points)
        display_brute_foce(points, hull)
    elif algo == "3":
        hull = algorithms.output_sensitive(points)
        display_graham_scan(points, hull) #works!


if __name__ == "__main__":
    main()