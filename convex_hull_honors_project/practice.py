import matplotlib.pyplot as plt
import numpy as np
import random

#$ -> done

#goal -> plot points on a graph $
#goal -> plot straight lines between those points $
#goal -> create a random selection of points based on the user's choice of how many points

#next step -> implementing an nlogn divide & conquer algorithm to sort the list,
#            then implement the graham scan algorithm to find the convex hull
# DELETE REMOTE, MAKE A NEW REMOTE WITH NEW GIT REPO

class Point:
    def __init__(self, x, y):
        self.x = x
        self.y = y


plt.style.use('_mpl-gallery')

fig, ax = plt.subplots()  # Create a figure containing a single axes.

#ax.scatter([1, 2, 3, 4], [1, 4, 2, 3])  # Plots the points on the graph

#ax.plot([1, 2, 3, 4], [1, 4, 2, 3])  # Draws a line between the points on the graph

print("How many random points would you like to plot?")
numPoints = int(input())

points = []

for i in range(numPoints):
    x = random.randint(0, 100)
    y = random.randint(0, 100)
    points.append(Point(x, y))
    ax.scatter(x, y)

ax.plot([points[0].x, points[1].x], [points[0].y, points[1].y]) # Draws a line between 2 points on the graph


def graham_scan():
    return


plt.show() # Display the figure.