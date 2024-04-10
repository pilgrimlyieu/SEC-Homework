# Homework02

[toc]

## Problem 1: Compose

Define a function `compose` so that `compose(h, g)(x)` returns `h(g(x))`. That is, `compose(h, g)` returns another function a function f, such that `f(x) = h(g(x))`.

```python
def compose(h, g):
    """Return a function f, such that f(x) = h(g(x)).
    
    >>> compose(square, triple)(5)
    225
    >>> double_inc = compose(increment, increment)
    >>> double_inc(3)
    5
    >>> double_inc(4)
    6
    """
    "*** YOUR CODE HERE ***"

```

## Problem 2: Product

The `summation(n, f)` function from the higher-order functions lecture adds up `f(1) + ... + f(n)`. Write a similar function called product that returns `f(1) * ... * f(n)`.

```python
def product(n, f):
    """Return the product of the first n terms in a sequence.
    n -- a positive integer
    f -- a function that takes one argument to produce the term

    >>> product(3, identity)  # 1 * 2 * 3
    6
    >>> product(5, identity)  # 1 * 2 * 3 * 4 * 5
    120
    >>> product(3, square)    # 1^2 * 2^2 * 3^2
    36
    >>> product(5, square)    # 1^2 * 2^2 * 3^2 * 4^2 * 5^2
    14400
    >>> product(3, increment) # (1+1) * (2+1) * (3+1)
    24
    >>> product(3, triple)    # 1*3 * 2*3 * 3*3
    162
    """
    "*** YOUR CODE HERE ***"
    
```

## Problem 3: Accumulate

Let's take a look at how `summation` and `product` are instances of a more general function called `accumulate`:

```python
def accumulate(combiner, base, n, f):
    """Return the result of combining the first n terms in a sequence and base.
    The terms to be combined are f(1), f(2), ..., f(n).  combiner is a
    two-argument commutative, associative function.

    >>> accumulate(add, 0, 5, identity)  # 0 + 1 + 2 + 3 + 4 + 5
    15
    >>> accumulate(add, 11, 5, identity) # 11 + 1 + 2 + 3 + 4 + 5
    26
    >>> accumulate(add, 11, 0, identity) # 11
    11
    >>> accumulate(add, 11, 3, square)   # 11 + 1^2 + 2^2 + 3^2
    25
    >>> accumulate(mul, 2, 3, square)    # 2 * 1^2 * 2^2 * 3^2
    72
    >>> accumulate(lambda x, y: x + y + 1, 2, 3, square)
    19
    >>> accumulate(lambda x, y: (x + y) % 17, 19, 20, square)
    16
    """
    "*** YOUR CODE HERE ***"

```

`accumulate` has the following parameters:

- *f* and *n*: the same parameters as in `summation` and `product`
- *combiner*: a two-argument function that specifies how the current term is combined with the previously accumulated terms.
- *base*: value at which to start the accumulation.

For example, the result of `accumulate(add, 11, 3, square)` is

```python
11 + square(1) + square(2) + square(3) = 25
```

**Note: The function square ,triple , increment, identity is defined in the 2.0 segment.**

> Note2: You may assume that *combiner* is associative and commutative. That is, `combiner(a, combiner(b, c)) == combiner(combiner(a, b), c)` and `combiner(a, b) == combiner(b, a)` for all a, b, and c. However, you may not assume *combiner* is chosen from a fixed function set and hard-code the solution.

After implementing `accumulate`, show how `summation` and `product` can both be defined as simple calls to `accumulate`:

```python
def summation_using_accumulate(n, f):
    """Returns the sum of f(1) + ... + f(n). The implementation
    uses accumulate.

    >>> summation_using_accumulate(5, square)
    55
    >>> summation_using_accumulate(5, triple)
    45
    >>> from construct_check import check
    >>> # ban iteration and recursion
    >>> check(HW_SOURCE_FILE, 'summation_using_accumulate',
    ...       ['Recursion', 'For', 'While'])
    True
    """
    "*** YOUR CODE HERE ***"

def product_using_accumulate(n, f):
    """An implementation of product using accumulate.

    >>> product_using_accumulate(4, square)
    576
    >>> product_using_accumulate(6, triple)
    524880
    >>> from construct_check import check
    >>> # ban iteration and recursion
    >>> check(HW_SOURCE_FILE, 'product_using_accumulate',
    ...       ['Recursion', 'For', 'While'])
    True
    """
    "*** YOUR CODE HERE ***"

```

## Problem 4: Get the Cake

Nanami Chiaki heard that you are learning SICP and feel interested in high order functions. She designed the following missions to test your understanding. If you solve the missions correctly, Nanami will give you a "cake" as gift.

The `missions` function consists of three sub missions: `mission1`, `mission2` and `mission3`. The inner function `mission3_inner` returns a variable `cake`.

Your task is to write a higher order function so that it calls three mission functions in turn and return the hidden `cake`. Please note that you are not allowed to return variable `cake` or print the messages directly. **A correct solution contains only one expression.**

Wish you success!

```python
def missions(f):
    """DO NOT EDIT THIS FUNCTION"""
    def mission1(f):
        if f(0) == 0 and f(1) == 2:
            print('MISSION 1 SOLVED')
            return lambda g: mission2(g(f))
        else:
            print('MISSION 1 FAILED')

    def mission2(f):
        if f(0) == 0 and f(1) == 2:
            print('MISSION 2 SOLVED')
            return mission3(0, 0)
        else:
            print('MISSION 2 FAILED')

    def mission3(f, g):
        def mission3_inner(f):
            if f == g:
                return mission3(f, g + 1)

        if g == 5:
            print('MISSION 3 SOLVED')
            return cake
        else:
            return mission3_inner

    return mission1(f)


def get_the_cake(missions):
    """
    Write a higher order function so that it calls three
    mission functions in turn and return the hidden cake.
    You are not allowed to return variable cake or print
    the messages directly. A correct solution contains
    only one expression.

    >>> cake = 'cherry cake'
    >>> the_cake = get_the_cake(missions)
    MISSION 1 SOLVED
    MISSION 2 SOLVED
    MISSION 3 SOLVED
    >>> the_cake
    'cherry cake'
    >>> # check that your answer consists of nothing but an
    >>> # expression (this docstring) and a return statement
    >>> import inspect, ast
    >>> [type(x).__name__ for x in ast.parse(inspect.getsource(get_the_cake)).body[0].body]
    ['Expr', 'Return']
    """
    return "*** YOUR CODE HERE ***"

```

## Problem 5: Protected Secret

Write a function `protected_secret` which takes in a `password`, `secret`, and `num_attempts`.

`protected_secret` should return another function which takes in a password and prints `secret` if the password entered matches the `password` given as an argument to `protected_secret`. Otherwise, the returned function should print "INCORRECT PASSWORD". After `num_attempts` incorrect passwords are used, the secret is locked forever and the function should print "SECRET LOCKED".

We recommend you using self-referencing functions to achieve this problem.

For example:

```shell
>>> my_secret = protected_secret("sicp2022", "I love python.", 1)
>>> my_secret = my_secret("sicp2022")
I love python.
>>> my_secret = my_secret("abcdefg")
INCORRECT PASSWORD # 0 Attempts left
>>> my_secret = my_secret("NanjingUniversity")
SECRET LOCKED
```

See the doctests for a detailed example.

```python
def protected_secret(password, secret, num_attempts):
    """
    Returns a function which takes in a password and prints the SECRET if the password entered matches
    the PASSWORD given to protected_secret. Otherwise it prints "INCORRECT PASSWORD". After NUM_ATTEMPTS
    incorrect passwords are entered, the secret is locked and the function should print "SECRET LOCKED".

    >>> my_secret = protected_secret("correcthorsebatterystaple", "I love NJU", 2)
    >>> my_secret = my_secret("hax0r_1") # 2 attempts left
    INCORRECT PASSWORD
    >>> my_secret = my_secret("correcthorsebatterystaple")
    I love NJU
    >>> my_secret = my_secret("hax0r_2") # 1 attempt left
    INCORRECT PASSWORD
    >>> my_secret = my_secret("hax0r_3") # No attempts left
    SECRET LOCKED
    >>> my_secret = my_secret("correcthorsebatterystaple")
    SECRET LOCKED
    """
    def get_secret(password_attempt):
        "*** YOUR CODE HERE ***"
    return get_secret
```

## Church Numerals

The logician Alonzo Church invented a system of representing non-negative integers entirely using functions. The purpose was to show that functions are sufficient to describe all of number theory: if we have functions, we do not need to assume that numbers exist, but instead we can invent them.

Your goal in this problem is to rediscover this representation known as *Church numerals*. Here are the definitions of `zero`, as well as a function that returns one more than its argument:

```python
def zero(f):
    return lambda x: x

def successor(n):
    return lambda f: lambda x: f(n(f)(x))
```

First, define functions `one` and `two` such that they have the same behavior as `successor(zero)` and `successor(successor(zero))` respectively, but do not call `successor` in your implementation.

Next, implement a function `church_to_int` that converts a church numeral argument to a regular Python integer.

Finally, implement functions `add_church`, `mul_church`, and `pow_church` that perform addition, multiplication, and exponentiation on church numerals. For deeper understanding, `church2int`, iteration or recursion is forbidden from your implementation.

```python
def one(f):
    """Church numeral 1: same as successor(zero)"""
    "*** YOUR CODE HERE ***"

def two(f):
    """Church numeral 2: same as successor(successor(zero))"""
    "*** YOUR CODE HERE ***"

three = successor(two)

def church_to_int(n):
    """Convert the Church numeral n to a Python integer.

    >>> church_to_int(zero)
    0
    >>> church_to_int(one)
    1
    >>> church_to_int(two)
    2
    >>> church_to_int(three)
    3
    """
    "*** YOUR CODE HERE ***"

def add_church(m, n):
    """Return the Church numeral for m + n, for Church numerals m and n.

    >>> church_to_int(add_church(two, three))
    5
    """
    "*** YOUR CODE HERE ***"

def mul_church(m, n):
    """Return the Church numeral for m * n, for Church numerals m and n.

    >>> four = successor(three)
    >>> church_to_int(mul_church(two, three))
    6
    >>> church_to_int(mul_church(three, four))
    12
    """
    "*** YOUR CODE HERE ***"

def pow_church(m, n):
    """Return the Church numeral m ** n, for Church numerals m and n.

    >>> church_to_int(pow_church(two, three))
    8
    >>> church_to_int(pow_church(three, two))
    9
    """
    "*** YOUR CODE HERE ***"
```
