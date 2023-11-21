import numpy as np
import matplotlib.pyplot as plt
from matplotlib.animation import FuncAnimation
import time


class Point:
    def __init__(self, x, y):
        self.x = x
        self.y = y
    
    def __str__ (self):
        return "(" + str(self.x) + ", " + str(self.y) + ")"
    
points = [Point(5,0), Point(12,3), Point(24,5), Point(13,2), Point(0,5), Point(9,3)]


result = [[points[2], points[3]], [points[3], points[5]], [points[5], points[0]]]

# Generate some random points
num_points = 50
x = np.random.rand(num_points)
y = np.random.rand(num_points)

# Create a figure and axis
fig, ax = plt.subplots()

for i in range(len(points)):
    ax.plot(points[i].x, points[i].y, 'ro')

# Update function for the animation
def update(frame):
    # Choose two random points to draw a line
    ax.plot([result[frame][0].x, result[frame][1].x], [result[frame][0].y, result[frame][1].y], 'b-')

# Create the animation
ani = FuncAnimation(fig, update, frames=np.arange(0, len(result)), interval=100)

# Show the plot
plt.show()