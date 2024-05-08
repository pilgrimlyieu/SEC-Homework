""" Homework 5: Nonlocal and Generators"""

from ADT import tree, label, branches, is_leaf, print_tree

#####################
# Required Problems #
#####################

def make_withdraw(balance, password):
    """Return a password-protected withdraw function.

    >>> w = make_withdraw(100, 'hax0r')
    >>> w(25, 'hax0r')
    75
    >>> error = w(90, 'hax0r')
    >>> error
    'Insufficient funds'
    >>> error = w(25, 'hwat')
    >>> error
    'Incorrect password'
    >>> new_bal = w(25, 'hax0r')
    >>> new_bal
    50
    >>> w(75, 'a')
    'Incorrect password'
    >>> w(10, 'hax0r')
    40
    >>> w(20, 'n00b')
    'Incorrect password'
    >>> w(10, 'hax0r')
    "Your account is locked. Attempts: ['hwat', 'a', 'n00b']"
    >>> w(10, 'l33t')
    "Your account is locked. Attempts: ['hwat', 'a', 'n00b']"
    >>> type(w(10, 'l33t')) == str
    True
    """
    times = 0
    attempts = []
    def lock(amount, attempt):
        nonlocal balance, times, attempts
        if times == 3:
            return f"Your account is locked. Attempts: {attempts}"
        if attempt != password:
            times += 1
            attempts.append(attempt)
            return "Incorrect password"
        else:
            if amount > balance:
                return "Insufficient funds"
            balance -= amount
            return balance
    return lock


def make_joint(withdraw, old_pass, new_pass):
    """Return a password-protected withdraw function that has joint access to
    the balance of withdraw.

    >>> w = make_withdraw(100, 'hax0r')
    >>> w(25, 'hax0r')
    75
    >>> make_joint(w, 'my', 'secret')
    'Incorrect password'
    >>> j = make_joint(w, 'hax0r', 'secret')
    >>> w(25, 'secret')
    'Incorrect password'
    >>> j(25, 'secret')
    50
    >>> j(25, 'hax0r')
    25
    >>> j(100, 'secret')
    'Insufficient funds'

    >>> j2 = make_joint(j, 'secret', 'code')
    >>> j2(5, 'code')
    20
    >>> j2(5, 'secret')
    15
    >>> j2(5, 'hax0r')
    10

    >>> j2(25, 'password')
    'Incorrect password'
    >>> j2(5, 'secret')
    "Your account is locked. Attempts: ['my', 'secret', 'password']"
    >>> j(5, 'secret')
    "Your account is locked. Attempts: ['my', 'secret', 'password']"
    >>> w(5, 'hax0r')
    "Your account is locked. Attempts: ['my', 'secret', 'password']"
    >>> make_joint(w, 'hax0r', 'hello')
    "Your account is locked. Attempts: ['my', 'secret', 'password']"
    """
    react = withdraw(0, old_pass)
    if type(react) == str:
        return react
    return lambda b, p: withdraw(b, old_pass if p == new_pass else p)


def permutations(seq):
    """Generates all permutations of the given sequence. Each permutation is a
    list of all elements in seq. The permutations could be yielded in any order.

    >>> perms = permutations([100])
    >>> type(perms)
    <class 'generator'>
    >>> next(perms)
    [100]
    >>> try: #this piece of code prints "No more permutations!" if calling next would cause an error
    ...     next(perms)
    ... except StopIteration:
    ...     print('No more permutations!')
    No more permutations!
    >>> sorted(permutations([1, 2, 3])) # Returns a sorted list containing elements of the generator
    [[1, 2, 3], [1, 3, 2], [2, 1, 3], [2, 3, 1], [3, 1, 2], [3, 2, 1]]
    >>> sorted(permutations((10, 20, 30)))
    [[10, 20, 30], [10, 30, 20], [20, 10, 30], [20, 30, 10], [30, 10, 20], [30, 20, 10]]
    >>> sorted(permutations("ab"))
    [['a', 'b'], ['b', 'a']]
    """
    yield from sum([[[seq[i]] + j for j in permutations(seq[:i] + seq[i+1:])] for i in range(len(seq))], []) if seq else [[]]


def lookups(k, key):
    """Yield one lookup function for each node of k that has the label key.
    >>> k = tree(5, [tree(7, [tree(2)]), tree(8, [tree(3), tree(4)]), tree(5, [tree(4), tree(2)])])
    >>> v = tree('Go', [tree('C', [tree('C')]), tree('A', [tree('S'), tree(6)]), tree('L', [tree(1), tree('A')])])
    >>> type(lookups(k, 4))
    <class 'generator'>
    >>> sorted([f(v) for f in lookups(k, 2)])
    ['A', 'C']
    >>> sorted([f(v) for f in lookups(k, 3)])
    ['S']
    >>> [f(v) for f in lookups(k, 6)]
    []
    """
    # Too difficult, and I ask Copilot for answer :=( xikuxiku
    # Below is my answer get the same list(but not use generator)
    # """
    # >>> lookups(k, 2)(v)
    # ['C', 'A']
    # >>> lookups(k, 3)(v)
    # ['S']
    # >>> lookups(k, 6)(v)
    # []
    # """
    # def lookup(k, v):
    #     return [label(v)] if label(k) == key else sum([lookup(b_k, b_v) for b_k, b_v in zip(branches(k), branches(v))], [])
    # return lambda v: lookup(k, v)
    def paths(t, key):
        if label(t) == key:
            yield []
        for i, b in enumerate(branches(t)):
            for path in paths(b, key):
                yield [i] + path
    def lookup(v, path):
        for i in path:
            v = branches(v)[i]
        return label(v)
    for path in paths(k, key):
        yield lambda v, path=path: lookup(v, path)
        # `yield lambda v: lookup(v, path)` works too, since to be that the lambda function is yielded and used immediately
        # ref. https://pylint.readthedocs.io/en/latest/user_guide/messages/warning/cell-var-from-loop.html
    # Simpler
    # from functools import reduce
    # def helper(t, path):
    #     if label(t) == key:
    #         yield lambda v: label(reduce(lambda b, i: branches(b)[i], path, v))
    #     for i, b in enumerate(branches(t)):
    #         yield from helper(b, path + [i])
    # return helper(k, [])

##########################
# Just for fun Questions #
##########################

def remainders_generator(m):
    """
    Yields m generators. The ith yielded generator yields natural numbers whose
    remainder is i when divided by m.

    >>> import types
    >>> [isinstance(gen, types.GeneratorType) for gen in remainders_generator(5)]
    [True, True, True, True, True]
    >>> remainders_four = remainders_generator(4)
    >>> for i in range(4):
    ...     print("First 3 natural numbers with remainder {0} when divided by 4:".format(i))
    ...     gen = next(remainders_four)
    ...     for _ in range(3):
    ...         print(next(gen))
    First 3 natural numbers with remainder 0 when divided by 4:
    4
    8
    12
    First 3 natural numbers with remainder 1 when divided by 4:
    1
    5
    9
    First 3 natural numbers with remainder 2 when divided by 4:
    2
    6
    10
    First 3 natural numbers with remainder 3 when divided by 4:
    3
    7
    11
    """
    def remainders(n):
        i = 0 if n else 1
        while True:
            yield m * i + n
            i += 1
    yield from [remainders(i) for i in range(m)]

if __name__ == '__main__':
    import doctest
#    doctest.testmod()
    doctest.run_docstring_examples(lookups, globals())