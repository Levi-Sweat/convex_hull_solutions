import matplotlib.pyplot as plt
import numpy as np
import random
from functools import cmp_to_key

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
        else: #if the y values are equal, sort by x value
            if self.x < other.x:
                result = -1
            else:
                result = 1
        return result
    
    def __str__ (self):
        return "(" + str(self.x) + ", " + str(self.y) + ")"

#used for sorting the points by angle in the graham scan
def orientation(p0, p1, p2):
    print("What is p0? " + str(p0))
    val = ((p1.y - p0.y) * (p2.x - p1.x) - (p1.x - p0.x) * (p2.y - p1.y))
    if val == 0:
        return 0  # collinear
    elif val > 0:
        return 1  # clock wise
    else:
        return 2  # counterclock wise

def distSq(p1, p2):
    return ((p1.x - p2.x) * (p1.x - p2.x) +
            (p1.y - p2.y) * (p1.y - p2.y))

def sort_slope(p1, p2):
    if p1.x == p2.x:
        return float('inf')
    else:
        return 1.0*(p1.y-p2.y)/(p1.x-p2.x)

def compare(p1, p2):

    result = 0
    # Find orientation
    o = orientation(p0, p1, p2)
    if o == 0:
        if distSq(p0, p2) >= distSq(p0, p1): 
            result = -1 #if p2 is closer to p0 than p1, then p2 comes before p1
        else:
            result = 1
    else:
        if o == 2:
            result = -1
        else:
            result = 1
    return result

def graham_scan(points):
    points = merge_sort(points) #points sorted by y value, then by smalllest x value if y values are equal
    
    print("After sorting by y value:")

    for i in range(len(points)):
        print(points[i])

    p0 = points[0] #updated first point after points have been sorted so that at 0 is the point with the smallest y value

    print("First sort by angle: ")

    points = sorted(points, key=cmp_to_key(compare)) #sorts points in counterclockwise order from p0 (which has to be on convex hull)

    for i in range(len(points)):
        print(points[i])
    
    start = points[0]
    points.sort(key=lambda p: (sort_slope(p,start), -p.y,p.x))
    
    
    print("Second sort by angle:")

    for i in range(len(points)):
        print(points[i])






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
        if left[index_left].y_val_equals(right[index_right]) == -1:
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

p0 = points[0] #first point, used in calculating the orientation for the graham scan

points = graham_scan(points)






plt.show() # Display the figure.