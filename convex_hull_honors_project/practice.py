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

    #used for sorting the points by y value in the merge sort
    def y_val_equals(self, other):
        result = 0
        if self.y < other.y:
            result = -1
        elif self.y > other.y:
            result = 1
        return result
    
    def __str__ (self):
        return "(" + str(self.x) + ", " + str(self.y) + ")"


def graham_scan(points):
    return merge_sort(points)


def merge_sort(array):
    # If the input array contains fewer than two elements,
    # then return it as the result of the function
    if len(array) < 2:
        return array

    midpoint = len(array) // 2

    # Sort the array by recursively splitting the input
    # into two equal halves, sorting each half and merging them
    # together into the final result
    return merge(
        left=merge_sort(array[:midpoint]),
        right=merge_sort(array[midpoint:]))

def merge(left, right):
    # If the first array is empty, then nothing needs
    # to be merged, and you can return the second array as the result
    if len(left) == 0:
        return right

    # If the second array is empty, then nothing needs
    # to be merged, and you can return the first array as the result
    if len(right) == 0:
        return left

    result = []
    index_left = index_right = 0

    # Now go through both arrays until all the elements
    # make it into the resultant array
    while len(result) < len(left) + len(right):
        # The elements need to be sorted to add them to the
        # resultant array, so you need to decide whether to get
        # the next element from the first or the second array
        if left[index_left].y_val_equals(right[index_right]) == -1 or left[index_left].y_val_equals(right[index_right]) == 0:
            #left[index_left] <= right[index_right]:
            result.append(left[index_left])
            index_left += 1
        else:
            result.append(right[index_right])
            index_right += 1

        # If you reach the end of either array, then you can
        # add the remaining elements from the other array to
        # the result and break the loop
        if index_right == len(right):
            result += left[index_left:]
            break

        if index_left == len(left):
            result += right[index_right:]
            break

    return result

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

for i in range(len(points)):
    print(points[i])

points = graham_scan(points)

print("After sorting by y value:")

for i in range(len(points)):
    print(points[i])


ax.plot([points[0].x, points[1].x], [points[0].y, points[1].y]) # Draws a line between 2 points on the graph



#plt.show() # Display the figure.