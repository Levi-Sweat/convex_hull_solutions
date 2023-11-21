from math import atan2
from random import randint


def orientation(p1, p2, p3):

    """ 
    > 0: CCW turn
    < 0 CW turn
    = 0: colinear
    """

    return (p2.x - p1.x) * (p3.y - p1.y) \
        -  (p2.y - p1.y) * (p3.x - p1.x)


def polar_angle(p0, p1 = None):
    if p1 == None: p1 = anchor
    y_span = p0.y - p1.y
    x_span = p0.x - p1.x
    return atan2(y_span, x_span)


def distance(p0, p1 = None):
    if p1 == None: p1 = anchor
    y_span = p0.y - p1.y
    x_span = p0.x - p1.x
    return y_span**2 + x_span**2

def polarsort(list):
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

def graham_scan(points):
    global anchor

    points = merge_sort(points) #points sorted by y value, then by smalllest x value if y values are equal
    
    anchor = points[0]

    print("After sorting by y value:")

    for i in range(len(points)):
        print(points[i])

    points = polarsort(points)

    print("After sorting by polar angle:")
    
    for i in range(len(points)):
        print(points[i])


    hull = [anchor, points[1]]
    for s in points[2:]:
        while orientation(hull[-2], hull[-1], s) <= 0: #############CHANGE ORIENTATION TO BE LIKE DET IN EXAMPLE
            del hull[-1] #backtrack
            if len(hull) < 2: break
        hull.append(s)

    print("Output of grahams scan: ")
    for i in range(len(hull)):
        print(hull[i])

    return hull

def output_sensitive(points):
    leftmost = points[0]
    for i in range(len(points)): #get the leftmost point (smallest x value)
        if points[i].x < leftmost.x:
            leftmost = points[i]

    hull = []
    currentVertex = leftmost
    hull.append(currentVertex)
    nextVertex = points[1]
    index = 2
    keepGoing = True
    while keepGoing:

        checking = points[index]

        crossProduct = orientation(currentVertex, nextVertex, checking)
        if crossProduct < 0:
            nextVertex = checking
        index += 1
        if index == len(points):
            if nextVertex == leftmost:
                keepGoing = False
            index = 0
            hull.append(nextVertex)
            currentVertex = nextVertex
            nextVertex = leftmost

    return hull

def brute_force(points):
    result = []
    for i in range(len(points)):
        for j in range(len(points)):
            if i != j:
                k = 0
                keepGoing = True
                while k < len(points) and keepGoing:
                    if k != i and k != j: #issue with: ijk & ikj
                        if orientation(points[i], points[k], points[j]) >= 0:
                            keepGoing = False
                    k += 1
                if keepGoing:
                    result.append([points[i], points[j]])

                
    return result