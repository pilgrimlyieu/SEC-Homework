""" Homework 4: Data Abstraction and Trees"""

from ADT import is_tree, make_city, get_name, get_lat, get_lon, tree, label, branches, is_leaf, print_tree

#####################
# Required Problems #
#####################

def couple(lst1, lst2):
    """Return a list that contains lists with i-th elements of two sequences
    coupled together.
    >>> lst1 = [1, 2, 3]
    >>> lst2 = [4, 5, 6]
    >>> couple(lst1, lst2)
    [[1, 4], [2, 5], [3, 6]]
    >>> lst3 = ['c', 6]
    >>> lst4 = ['s', '1']
    >>> couple(lst3, lst4)
    [['c', 's'], [6, '1']]
    """
    assert len(lst1) == len(lst2)
    return [[lst1[i], lst2[i]] for i in range(len(lst1))]

from math import sqrt
def distance(city1, city2):
    """
    >>> city1 = make_city('city1', 0, 1)
    >>> city2 = make_city('city2', 0, 2)
    >>> distance(city1, city2)
    1.0
    >>> city3 = make_city('city3', 6.5, 12)
    >>> city4 = make_city('city4', 2.5, 15)
    >>> distance(city3, city4)
    5.0
    """
    p = lambda f, x1, x2: (f(x1) - f(x2)) ** 2
    return sqrt(p(get_lat, city1, city2) + p(get_lon, city1, city2))

def closer_city(lat, lon, city1, city2):
    """
    Returns the name of either city1 or city2, whichever is closest to
    coordinate (lat, lon).

    >>> berkeley = make_city('Berkeley', 37.87, 112.26)
    >>> stanford = make_city('Stanford', 34.05, 118.25)
    >>> closer_city(38.33, 121.44, berkeley, stanford)
    'Stanford'
    >>> bucharest = make_city('Bucharest', 44.43, 26.10)
    >>> vienna = make_city('Vienna', 48.20, 16.37)
    >>> closer_city(41.29, 174.78, bucharest, vienna)
    'Bucharest'
    """
    city0 = make_city("city0", lat, lon)
    d1 = distance(city0, city1)
    d2 = distance(city0, city2)
    return get_name(city1 if d1 < d2 else city2)

def nut_finder(t):
    """Returns True if t contains a node with the value 'nut' and
    False otherwise.

    >>> scrat = tree('nut')
    >>> nut_finder(scrat)
    True
    >>> sproul = tree('roots', [tree('branch1', [tree('leaf'), tree('nut')]), tree('branch2')])
    >>> nut_finder(sproul)
    True
    >>> numbers = tree(1, [tree(2), tree(3, [tree(4), tree(5)]), tree(6, [tree(7)])])
    >>> nut_finder(numbers)
    False
    >>> t = tree(1, [tree('nut',[tree('not nut')])])
    >>> nut_finder(t)
    True
    """
    if is_leaf(t):
        return label(t) == "nut"
    else:
        for branch in branches(t):
            if nut_finder(branch):
                return True
        return label(t) == "nut"

def sprout_leaves(t, values):
    """Sprout new leaves containing the data in values at each leaf in
    the original tree t and return the resulting tree.

    >>> t1 = tree(1, [tree(2), tree(3)])
    >>> print_tree(t1)
    1
      2
      3
    >>> new1 = sprout_leaves(t1, [4, 5])
    >>> print_tree(new1)
    1
      2
        4
        5
      3
        4
        5

    >>> t2 = tree(1, [tree(2, [tree(3)])])
    >>> print_tree(t2)
    1
      2
        3
    >>> new2 = sprout_leaves(t2, [6, 1, 2])
    >>> print_tree(new2)
    1
      2
        3
          6
          1
          2
    """
    if is_leaf(t):
        return tree(label(t), [tree(value) for value in values])
    else:
        return tree(label(t), [sprout_leaves(branch, values) for branch in branches(t)])

def add_trees(t1, t2):
    """
    >>> numbers = tree(1,
    ...                [tree(2,
    ...                      [tree(3),
    ...                       tree(4)]),
    ...                 tree(5,
    ...                      [tree(6,
    ...                            [tree(7)]),
    ...                       tree(8)])])
    >>> print_tree(add_trees(numbers, numbers))
    2
      4
        6
        8
      10
        12
          14
        16
    >>> print_tree(add_trees(tree(2), tree(3, [tree(4), tree(5)])))
    5
      4
      5
    >>> print_tree(add_trees(tree(2, [tree(3)]), tree(2, [tree(3), tree(4)])))
    4
      6
      4
    >>> print_tree(add_trees(tree(2, [tree(3, [tree(4), tree(5)])]), \
    tree(2, [tree(3, [tree(4)]), tree(5)])))
    4
      6
        8
        5
      5
    """
    if is_tree(t1) and not is_tree(t2):
        return t1
    elif is_tree(t2) and not is_tree(t1):
        return t2
    elif is_leaf(t1) and is_leaf(t2):
        return tree(label(t1) + label(t2))
    else:
        b1 = branches(t1)
        b2 = branches(t2)
        max_len = max(len(b1), len(b2))
        b1 += [[]] * (max_len - len(b1))
        b2 += [[]] * (max_len - len(b2))
        return tree(label(t1) + label(t2), [add_trees(b1[i], b2[i]) for i in range(max_len)])

def bigpath(t, n):
    """Return the number of paths in t that have a sum larger or equal to n.

    >>> t = tree(1, [tree(2), tree(3, [tree(4), tree(5)])])
    >>> bigpath(t, 3)
    4
    >>> bigpath(t, 6)
    2
    >>> bigpath(t, 9)
    1
    """
    return (1 if n <= label(t) else 0) + sum([bigpath(b, n - label(t)) for b in branches(t)])

def bigger_path(t, n):
    """Return the number of paths in t that have a sum larger or equal to n.

    >>> t = tree(1, [tree(2), tree(3, [tree(4), tree(5)])])
    >>> bigger_path(t, 3)
    9
    >>> bigger_path(t, 6)
    4
    >>> bigger_path(t, 9)
    1
    """
    return bigpath(t, n) + sum([bigger_path(b, n) for b in branches(t)])

##########################
# Just for fun Questions #
##########################

# def fold_tree(t, base_func, merge_func):
#     """Fold tree into a value according to base_func and merge_func"""
#     def r(s):
#         if is_leaf(s):
#             return base_func(s)
#         ret = merge_func(s)
#         for b in branches((s)):
#             ret += r(b)
#         return ret
#     return r(t)

def fold_tree(t, base_func, merge_func):
    """Fold tree into a value according to base_func and merge_func"""
    return base_func(t) if is_leaf(t) else merge_func(t)([fold_tree(i, base_func, merge_func) for i in branches(t)])

def count_leaves(t):
    """Count the leaves of a tree.

    >>> t = tree(1, [tree(2), tree(3, [tree(4), tree(5)])])
    >>> count_leaves(t)
    3
    """
    return fold_tree(t, lambda x: 1, lambda t: sum)

def label_sum(t):
    """Sum up the labels of all nodes in a tree.

    >>> t = tree(1, [tree(2), tree(3, [tree(4), tree(5)])])
    >>> label_sum(t)
    15
    """
    return fold_tree(t, label, lambda t: lambda s: label(t) + sum(s))

def preorder(t):
    """Return a list of the entries in this tree in the order that they
    would be visited by a preorder traversal.

    >>> t = tree(1, [tree(2), tree(3, [tree(4), tree(5)])])
    >>> preorder(t)
    [1, 2, 3, 4, 5]
    """
    return fold_tree(t, lambda t: [label(t)], lambda t: lambda s: [label(t)] + sum(s, []))