# Project2 Autocorrected Typing Software

![img](.\pic\cats_typing.gif)

<center>Programmers dream of<br>Abstraction, recursion, and<br>
Typing really fast.</center>

[toc]

## Introduction

In this project, you will write a program that measures typing speed. Additionally, you will implement typing autocorrect, which is a feature that attempts to correct the spelling of a word after a user types it. GUI is also provided. 

You may have fun with full version of this game for a while [here](https://cats.cs61a.org/). This project is inspired by [typeracer](https://play.typeracer.com/).

## Project Structure

Below is a list of all the files you will see in the `project2.zip`. However, you only have to make changes to `cats.py` in this project.

```
cats
  |-gui_files   # A directory of various things used by the web gui.
  |-tests       # Your local `ok` tests for each problem. 
  |-data
  |  |-sample_paragraphs.txt    # A file containing text samples to be typed.
  |  |-common_words.txt         # A file containing common English words in order of frequency.
  |  `-words.txt                # A file containing many more English words in order of frequency.
  |-cats.py     # The typing test logic.
  |-gui.py      # A web server for the web-based graphical user interface (GUI).
  |-ucb.py      # Utility functions for CS 61A projects.
  `-utils.py    # Utility functions for interacting with files and strings.
```

For the functions that we ask you to complete, there may be some initial code that we provide. If you would rather not use that code, feel free to delete it and start from scratch. You may also add new function definitions as you see fit.

However, please do **not** modify any other functions. Doing so may result in your code failing our framework. Also, please do **not** change any function signatures (names, argument order, or number of arguments).

And, check the given tests when you are confused about how some functions should behave.

## Phase 1: Typing

> Phase 1 needs 50~100 lines code.

### Problem 1: `choose`

Implement `choose`, which selects which paragraph the user will type. It takes a list of `paragraphs` (strings), a `select` function that returns `True` for paragraphs that can be selected, and a non-negative index `k`. The `choose` function return's the `k`th paragraph for which `select` returns `True`. If no such paragraph exists (because `k` is too large), then `choose` returns the empty string.

Index starts from `0`.

### Problem 2: `about`

Implement `about`, which takes a list of `topic` words. It returns a function that can be passed to `choose` as the `select` argument. The returned function takes a paragraph and returns a boolean indicating whether that paragraph contains any of the words in `topic`.

To make this comparison accurately, you will need to ignore case (that is, assume that uppercase and lowercase letters don't change what word it is) and punctuation.

Assume that all words in the `topic` list are already lowercased and do not contain punctuation.

> **Hint**: You may use the string utility functions in `utils.py`.

### Problem 3: `accuracy`

Implement `accuracy`, which takes a `typed` paragraph and a `reference` paragraph. It returns the percentage of words in `typed` that exactly match the corresponding words in `reference`. Case and punctuation must match as well.

A *word* in this context is any sequence of characters separated from other words by whitespace, so treat "dog;" as all one word.

If a typed word has no corresponding word in the reference because `typed` is longer than `reference`, then the extra words in `typed` are all incorrect.

If `typed` is empty, then the accuracy is zero.

### Problem 4: `wpm`

Implement `wpm`, which computes the *words per minute*, a measure of typing speed, given a string `typed` and the amount of `elapsed` time in seconds. Despite its name, *words per minute* is not based on the number of words typed, but instead the number of characters, so that a typing test is not biased by the length of words. The formula for *words per minute* is the ratio of the number of characters (including spaces) typed divided by 5 (a typical word length) to the elapsed time in minutes.

For example, the string `"I am glad!"` contains three words and ten characters (not including the quotation marks). The words per minute calculation uses 2 as the number of words typed (because 10 / 5 = 2). If someone typed this string in 30 seconds (half a minute), their speed would be 4 words per minute.

---

**Time to test your typing speed!** You can use the command line to test your typing speed on paragraphs about a particular topic. For example, the command below will load paragraphs about cats or kittens. See the `run_typing_test` function for the implementation if you're curious (but it is defined for you).

```bash
python cats.py -t cats kittens
```

You can try out the web-based graphical user interface (GUI) using the following command.

```bash
python gui.py
```

Congratulations! You have finished Phase 1 of this project!

## Phase 2: Autocorrect

In the web-based GUI, there is an autocorrect button, but right now it doesn't do anything. Let's implement automatic correction of typos. Whenever the user presses the space bar, if the last word they typed doesn't match a word in the dictionary but is close to one, then that similar word will be substituted for what they typed.

### Problem 5: `autocorrect`

Implement `autocorrect`, which takes a `user_word`, a list of all `valid_words`, a `diff_function`, and a `limit`.

If the `user_word` is contained inside the `valid_words` list, `autocorrect` returns that word. Otherwise, `autocorrect` returns the word from `valid_words` that has the lowest difference from the provided `user_word` based on the `diff_function`. However, if the lowest difference between `user_word` and any of the `valid_words` is greater than `limit`, then `user_word` is returned instead.

A diff function takes in three arguments, which are the two strings to be compared (first the `user_word` and then a word from `valid_words`), as well as the `limit`. The output of the diff function, which is a non-negative number, represents the amount of difference between the two strings.

Assume that `user_word` and all elements of `valid_words` are lowercase and have no punctuation.

**Important**: if multiple strings have the same lowest difference according to the `diff_function`, `autocorrect` should return the string that appears fi rst in `valid_words`.

> **Hint**: Try using `max` or `min` with the optional `key` argument.

### Problem 6: `sphinx_swap`

Implement `sphinx_swap`, which is a diff function that takes two strings. It returns the minimum number of characters that must be changed in the `start` word in order to transform it into the `goal` word. If the strings are not of equal length, the difference in lengths is added to the total.

Here are some examples:

```python
>>> big_limit = 10
>>> sphinx_swap("nice", "rice", big_limit)    # Substitute: n -> r
1
>>> sphinx_swap("range", "rungs", big_limit)  # Substitute: a -> u, e -> s
2
>>> sphinx_swap("pill", "pillage", big_limit) # Don't substitute anything, length difference of 3.
3
>>> sphinx_swap("roses", "arose", big_limit)  # Substitute: r -> a, o -> r, s -> o, e -> s, s -> e
5
>>> sphinx_swap("rose", "hello", big_limit)   # Substitue: r->h, o->e, s->l, e->l, length difference of 1.
5
```

If the number of characters that must change is greater than `limit`, then `sphinx_swap` should return any number larger than `limit` and should minimize the amount of computation needed to do so.

These two calls to `sphinx_swap` should take about the same amount of time to evaluate:

```python
>>> limit = 4
>>> sphinx_swap("roses", "arose", limit) > limit
True
>>> sphinx_swap("rosesabcdefghijklm", "arosenopqrstuvwxyz", limit) > limit
True
```

> **Important**: You may not use `while` or `for` statements in your implementation. Use recursion.

Try turning on autocorrect in the GUI. Does it help you type faster? Are the corrections accurate? You should notice that inserting a letter or leaving one out near the beginning of a word is not handled well by this diff function. Let's fix that!

### Problem 7: `feline_fixes`

Implement `feline_fixes`, which is a diff function that returns the minimum number of edit operations needed to transform the `start` word into the `goal` word.

There are three kinds of edit operations:

1. Add a letter to `start`,
2. Remove a letter from `start`,
3. Substitute a letter in `start` for another.

Each edit operation contributes 1 to the difference between two words.

```python
>>> big_limit = 10
>>> feline_fixes("cats", "scat", big_limit)       # cats -> scats -> scat
2
>>> feline_fixes("purng", "purring", big_limit)   # purng -> purrng -> purring
2
>>> feline_fixes("ckiteus", "kittens", big_limit) # ckiteus -> kiteus -> kitteus -> kittens
3
```

We have provided a template of an implementation in `cats.py`. This is a recursive function with three recursive calls. One of these  recursive calls will be similar to the recursive call in `sphinx_swap`.

You may modify the template however you want or delete it entirely.

If the number of edits required is greater than `limit`, then `feline_fixes` should return any number larger than `limit` and should minimize the amount of computation needed to do so.

These two calls to `feline_fixes` should take about the same amount of time to evaluate:

```python
>>> limit = 2
>>> feline_fixes("ckiteus", "kittens", limit) > limit
True
>>> sphinx_swap("ckiteusabcdefghijklm", "kittensnopqrstuvwxyz", limit) > limit
True
```

Try typing again. Are the corrections more accurate?

```bash
python gui.py
```

**Extensions**: You may optionally design your own diff function called `final_diff`. Here are some ideas for making even more accurate corrections:

- Take into account which additions and deletions are more likely than others. For example, it's much more likely that you'll accidentally leave out a letter if it appears twice in a row.
- Treat two adjacent letters that have swapped positions as one change, not two.
- Try to incorporate common misspellings

## Phase 3: Multiplayer

Typing is more fun with friends! You'll now implement multiplayer functionality, so that when you run `gui.py` on your computer, it connects to the course server at [cats.cs61a.org](https://cats.cs61a.org/) and looks for someone else to race against.

To race against a friend, 5 different programs will be running:

- Your GUI, which is a program that handles all the text coloring and display in your web browser.
- Your `gui.py`, which is a web server that communicates with your GUI using the code you wrote in `cats.py`.
- Your opponent's `gui.py`.
- Your opponent's GUI.
- The CS 61A multiplayer server, which matches players together and passes messages around. It is not running on your machine.

When you type, your GUI sends what you have typed to your `gui.py` server, which computes how much progress you have made and returns a progress update. It also sends a progress update to the multiplayer server, so that your opponent's GUI can display it.

Meanwhile, your GUI display is always trying to keep current by asking for progress updates from `gui.py`, which in turn requests that info from the multiplayer server.

Each player has an `id` number that is used by the server to track typing progress.

> Phase 3 needs ~50 lines code.

### Problem 8: `report_progress`

Implement `report_progress`, which is called every time the user finishes typing a word. It takes a list of the words `typed`, a list of the words in the `prompt`, the user `id`, and a `send` function that is used to send a progress report to the multiplayer server. Note that there will never be more words in `typed` than in `prompt`.

Your progress is a ratio of the words in the `prompt` that you have typed correctly, up to the first incorrect word, divided by the number of `prompt` words. For example, this example has a progress of `0.25`:

```python
report_progress(["Hello", "ths", "is"], ["Hello", "this", "is", "wrong"], ...)
```

Your `report_progress` function should return this number. Before that, it should send a message to the multiplayer server that is a two-element dictionary containing the keys `'id'` and `'progress'`. The `id` is passed into `report_progress` from the GUI. The progress is the fraction you compute. Call `send` on this dictionary to send it to the multiplayer server.

### Problem 9: `time_per_word`

Implement `time_per_word`, which takes in `times_per_player`, a list of lists for each player with timestamps indicating when each player finished typing each word. It also takes in a list `words`. It returns a `game` with the given information.

A `game` is a data abstraction that has a list of `words` and `times`. The `times` are stored as a list of lists of how long it took each player to type each word. `times[i][j]` indicates how long it took player `i` to type word `j`.

For example, if `times_per_player = [[1, 3, 5], [2, 5, 6]]`, the corresponding `time` attribute of the `game` would be `[[2, 2], [3, 1]]`. Timestamps are cumulative and always increasing, while the values in `time` are differences between consecutive timestamps.

Be sure to use the `game` constructor when returning a `game`, rather than assuming a particular data format.

### Problem 10: `fastest_words`

Implement `fastest_words`, which returns which words each player typed fastest. This function is called once both players have finished typing. It takes in a `game`.

The `game` argument is a `game` data abstraction, like the one returned in Problem 9. You can access words in the `game` with selectors `word_at`, which takes in a `game` and the `word_index` (an integer). You can access the time it took any player to type any word using the `time` function provided in `cats.py`.

The `fastest_words` function returns a list of lists of words, one list for each player, and within each list the words they typed the fastest. In the case of a tie, consider the earliest player in the list (the smallest player index) to be the one who typed it the fastest.

Be sure to use the accessor functions for the `game` data abstraction, rather than assuming a particular data format.

Congratulations! Now you can play against other students in the course. Set `enable_multiplayer` to True near the bottom of cats.py and type swiftly!

```bash
python gui.py
```

> You can open multitabs in a brower to visit your game and test your implementation alone. Or you can call your friend up to compete with you :D
>
> It seems that your `gui.py` is running in your own brower, then how could they communicate with each other? In fact, `gui.py`s do not know each other, they are just a client which will connect to `cats.cs61a.org`'s server program. That program will handle all clients requests and response back.

**Congratulations, you have reached the end of your second project! If you haven't already, relax and enjoy a few games of Cats with a friend.** 