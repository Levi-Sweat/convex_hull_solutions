from math import atan2
from random import randint
import matplotlib.pyplot as plt


def orientation(p1, p2, p3):

    """ 
    Gets the determinant of a matrix to determine if the points make a CCW turn.
    
    Args:
    p1 - The first point.
    p2 - The second point.
    p3 - The third point.
    
    Returns: > 0: CCW turn, < 0 CW turn, = 0: colinear
    """

    return (p2.x - p1.x) * (p3.y - p1.y) \
        -  (p2.y - p1.y) * (p3.x - p1.x)


def polar_angle(p0, p1 = None):
    """
    Calculates the polar angle of a point about the anchor point.
    
    Args:
    p0 - The first point.
    p1 - The second point, typically the anchor point.
    
    Returns: The polar angle of the point about the anchor point.
    """
    if p1 == None: p1 = anchor
    y_span = p0.y - p1.y
    x_span = p0.x - p1.x
    return atan2(y_span, x_span)


def distance(p0, p1 = None):
    """
    Calculates the distance between two points.
    
    Args:
    p0 - The first point.
    p1 - The second point.
    
    Returns: The distance between the two points.
    """

    if p1 == None: p1 = anchor
    y_span = p0.y - p1.y
    x_span = p0.x - p1.x
    return y_span**2 + x_span**2

def polarsort(list):
    """
    Recursive method that sorts the list by polar angle about the anchor point.
    
    Args:
    list- the list of points to be sorted
    
    Returns: the sorted list
    """
    if len(list) <= 1: return list

    smaller, equal, larger = [], [], []
    
    piv_ang = polar_angle(list[randint(0, len(list) - 1)])

    for pt in list:
        pt_ang = polar_angle(pt)
        if pt_ang < piv_ang: smaller.append(pt)
        elif pt_ang == piv_ang: equal.append(pt)
        else: larger.append(pt)
    return polarsort(smaller) + sorted(equal, key = distance) + polarsort(larger)

def merge_sort(array):
    """
    Sorts the input array using the merge sort algorithm.
    
    Args:
    array- the array to be sorted
    
    Returns: the sorted array
    """

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
    """
    Helper method for merge_sort that merges the two sorted arrays.
    
    Args:
    left- the left array to be merged
    right- the right array to be merged
    
    Returns: the merged arrays
    """

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

def setup_plot():
    """
    Used to setup the initial style and figure for plotting each algorithm.
    """

    plt.style.use('_mpl-gallery')

    plt.figure()



def graham_scan(points):
    """
    Implements the graham's scan algorithm to compute the convex hull of a set of points. Sorts the
    points by smallest y value then polarly sorts them about an anchor point. Then, starting with
    the first two points, checks if the next point makes a CCW turn. If it does, it is added to
    the hull. If not, the last point is removed.
    
    Args: points- the list of points to be sorted"""
    global anchor

    setup_plot()


    points = merge_sort(points) #points sorted by y value, 
                                #then by smalllest x value if y values are equal
    anchor = points[0] # anchor is the point with the msmallest y value

    points = polarsort(points)

    plt.clf()

    for a in range(len(points)): # plot points
        plt.scatter(points[a].x, points[a].y)
    
    #plot the line from the anchor to the first point on the hull
    plt.plot([anchor.x, points[1].x], [anchor.y, points[1].y])

    plt.show(block=False)
    plt.pause(1)

    hull = [anchor, points[1]]

    for s in points[2:]:
        while orientation(hull[-2], hull[-1], s) <= 0:  # keep deleting until a CCW turn

            #############
            plt.clf() # clear the previous plot

            for a in range(len(points)): # plot points
                plt.scatter(points[a].x, points[a].y)
            
            for b in range(len(hull) - 1): # plot the hull thusfar
                plt.plot([hull[b].x, hull[b + 1].x], [hull[b].y, hull[b + 1].y])
            
            #plot the orientation we are currently checking
            plt.plot([hull[-2].x, hull[-1].x], [hull[-2].y, hull[-1].y])
            plt.plot([hull[-1].x, s.x], [hull[-1].y, s.y])
            
            #show the plot for a short time before continuing
            plt.show(block=False) 
            plt.pause(0.00001)
            #############

            del hull[-1] #backtrack
            if len(hull) < 2: break
        
       #############
        plt.clf() # clear the plot

        for a in range(len(points)): # plot points
            plt.scatter(points[a].x, points[a].y)
        
        for b in range(len(hull) - 1): # plot the hull thusfar
            plt.plot([hull[b].x, hull[b + 1].x], [hull[b].y, hull[b + 1].y])
        
        #plot the orientation we are currently checking
        plt.plot([hull[-2].x, hull[-1].x], [hull[-2].y, hull[-1].y])
        plt.plot([hull[-1].x, s.x], [hull[-1].y, s.y])

        plt.show(block=False)
        plt.pause(1)
        #############

        hull.append(s)

    #plot the final hull
    #########
    plt.clf()

    for a in range(len(points)):
        plt.scatter(points[a].x, points[a].y)
    
    for b in range(len(hull) - 1): 
        plt.plot([hull[b].x, hull[b + 1].x], [hull[b].y, hull[b + 1].y])

    plt.plot([hull[-1].x, hull[0].x], [hull[-1].y, hull[0].y])

    plt.show()
    #########

def output_sensitive(points):
    """
    Implements the output sensitive algorithm to compute the convex hull of a set of points. 
    Sorts the points by the smallest x value, then checks if there is ever a clockwise turn 
    between 2 points and every other point. If so, move on to the next point. If not, the point
    must be on the convex hull, so add it.
    
    Args: points- the list of points to be sorted
    """

    setup_plot()

    leftmost = 0
    currentVertex = 0
    for i in range(len(points)): #get the leftmost point (smallest x value)
        if points[i].x < points[leftmost].x:
            leftmost = i
            currentVertex = i


    hull = []
    hull.append(points[currentVertex])
    nextVertex = 1
    index = 2
    keepGoing = True
    while keepGoing:

        checking = index

        crossProduct = orientation(points[currentVertex], points[nextVertex], points[checking])

        #############
        plt.clf()

        for a in range(len(points)):
            plt.scatter(points[a].x, points[a].y)
        
        for b in range(len(hull) - 1):
            plt.plot([hull[b].x, hull[b + 1].x], [hull[b].y, hull[b + 1].y])
        
        plt.plot([points[currentVertex].x, points[nextVertex].x], 
                 [points[currentVertex].y, points[nextVertex].y])
        plt.plot([points[nextVertex].x, points[checking].x], 
                 [points[nextVertex].y, points[checking].y])

        plt.show(block=False)
        plt.pause(0.00000001)
        ###############

        #if the points don't turn CCW, move on to the next point
        if crossProduct < 0:
            nextVertex = checking
            index = 0
        index += 1
        #if we've checked all the points, we've found a point that is on the hull
        if index == len(points):
            if points[nextVertex] == points[leftmost]:
                keepGoing = False
            index = 0
            hull.append(points[nextVertex])
            currentVertex = nextVertex
            nextVertex = leftmost

    ############
    plt.clf()

    for c in range(len(points)):
        plt.scatter(points[c].x, points[c].y)    


    for b in range(len(hull) - 1): 
        plt.plot([hull[b].x, hull[b + 1].x], [hull[b].y, hull[b + 1].y])

    plt.plot([hull[-1].x, hull[0].x], [hull[-1].y, hull[0].y])

    plt.show()
    ############

def brute_force(points):
    """
    Implements the brute force algorithm to compute the convex hull of a set of points.
    Randomly selects 2 points, checks if they are on the hull, then moves on to the next 2 random
    points.
    
    Args: points- the list of points to be sorted
    """
    
    setup_plot()

    result = []
    for i in range(len(points)):
        for j in range(len(points)):
            if i != j:
                k = 0
                keepGoing = True
                while k < len(points) and keepGoing:
                    if k != i and k != j: 
                    #if the randomly selected k doesn't equal i or j, check orientation

                        #############
                        plt.clf()

                        for a in range(len(points)):
                            plt.scatter(points[a].x, points[a].y)
                        
                        b = 0
                        while b < (len(result) - 1):
                            plt.plot([result[b].x, result[b + 1].x], 
                                     [result[b].y, result[b + 1].y])
                            b += 2
                        
                        plt.plot([points[i].x, points[j].x], 
                                [points[i].y, points[j].y])
                        plt.plot([points[j].x, points[k].x], 
                                [points[j].y, points[k].y])

                        plt.show(block=False)
                        plt.pause(0.00000001)
                        ###############

                        if orientation(points[i], points[k], points[j]) >= 0:
                            keepGoing = False #if the points make a CCW turn, move on
                    k += 1
                if keepGoing: 
                #if every point is checked and the points never make a CCW turn, they must be on the hull
                    result.append(points[i])
                    result.append(points[j])

    ############
    plt.clf()

    for c in range(len(points)):
        plt.scatter(points[c].x, points[c].y)    

    d = 0
    while d < len(result) - 1:
        plt.plot([result[d].x, result[d + 1].x], [result[d].y, result[d + 1].y])
        d += 2

    plt.show()
    ############
