# More functional Java and standard library exercises

## Exercise 1 - Quadratic discriminant calculator

*In a file named `NumRoots.java`.*

The quadratic discriminant of a quadratic equation of the form $a*x^2+b*x+c=0$ is defined by the formula $b^2 - 4*a*c$. If the discriminant is positive, the corresponding quadratic  equation has 2 real roots; if the discriminant is negative it has 0 real roots; and if the discriminant is exactly 0, then there is 1 real root.

Design a function, `numRoots`, that given `a`, `b` and `c` as inputs of type `double`, returns as an `int` denoting how many roots are there in the corresponding quadratic equation.

Following the design recipe (step 6.), write tests for the function. 

You can consider tests, e.g., for the following scenarios:

* Zero discriminant
* Negative discriminant
* Positive discriminant
* Negative inputs


**Note**: in order to check whether a value of type `double` is exactly 0 in the computer, we **never** check for equality (i.e., we never write an expression such as `value == 0.0` or `Equals(value,0.0)`). Instead, we check whether such value is *"close enough"* to the value `0.0`. We can do that with the expression such as `Abs(value-0.0) < TOLERANCE`, where `TOLERANCE` is a *"small"* (e.g., `1.0e-12`) constant of type `double`, and `Abs` is a function provided by the functional Java standard library; click [here](https://comp.anu.edu.au/courses/comp1110/notes/functional-java/lib/#lib-math-fun-abs) for details.

## Exercise 2 - Clock arithmetic

*In a file named `Clock.java`.*

Write a function `clock` that updates time on a 12-hour clock. 

The function has two input arguments of time `int`, named `current` and `increment`, and one output of type `int`.

`current` denotes the current time, always non-negative and less than or equal to `12`.

`increment` denotes how much time (measured in hours) has passed since the current time. You can assume it to be non-negative.

Finally, the output of the function should be what the time will be after adding `increment` to `current` (on a 12-hour clock).

For example, if the function is given the numbers `8` and `3`, it should return the number `11`, and if it is given the numbers `11` and `4` it should return the number `3`.

**Hint**: The modulo operand `%` in Functional Java calculates the remainder of division, e.g., `5 % 2` is equal to `1`.

Following the design recipe (step 6.), write tests for the function. 

You can consider tests, e.g., for the following scenarios:

* Addition of 0 hours (e.g., `current` equals `3` and `increment` equals `0`)
* Current time 0 o'clock (e.g., `current` equals `0`, and `increment` equals `4`)
* Cycle back after 12 (e.g., `current` equals `11`, and `increment` equals `3`)
* Multiple cycles (e.g., `current` equals `8` and `increment` equals `24`)
* Time shift without cycles (e.g., `current` equals `3` and increment equals `4`)

## Exercise 3 - Fizzbuzz

*In a file named `FizzBuzz.java`.*

 Write a function named `fizzBuzz` that takes an integer argument (`int`) and returns a `String`.
 If the input is divisible by `3` return `"fizz"`, if the input is
 divisible by `5`, return `"buzz"`, and if the input is divisible by `3` and
 `5`, return `"fizzbuzz"`. If the input is divisible by neither, return the
 empty string, `""`.

 Following the design recipe (step 6.), write tests for the function. 


## Exercise 4 - Fizzbuzzpopskibiditoylet

*In a file named `FizzBuzzPopSkibidiToyLet.java`*

Follow the same specifications as in the previous exercise, however with the following extra rules:
  
 * If the input is divisible by `7`, return `"skibidi"`. 
 * If the input is divisible by `11`, return `"toy"`.
 * If the input is divisible by `13`, return `"let"`.

 Besides, if the input is divisible by more than one number, return the strings associated with those numbers
 concatenated in increasing order by number.

 For example, if the input is divisible by `5` and `7`, return `"fizzskibidi"`. 
 If the input is divisible by `3`, `7`, `11`, and `13`, return `"buzzskibiditoylet"`.

 Following the design recipe (step 6.), write tests for the function. 


 ## Exercise 5 - Fizz buzz skibidi toy let!

 *In a file named `FizzFormatted.java`*

Write a function `fizzFormatted` that follows the same specification as the previous exercise, but with the following additional twist.

Namely, the first word in the output String must be capitalized, there must be a space between each word, and the whole phrase must end in an exclamation mark (i.e., `!`).

Some examples are as follows:

* `fizzFormatted(3)`  returns `"Fizz!"`
* `fizzFormatted(21)` returns `"Fizz skibidi!"`
* `fizzFormatted(715)` returns `"Buzz toy let!"`

Remember, in Functional Java, once you have assigned a variable a value, you cannot modify (mutate) its value anymore. In other words, 
you're not allowed to assign it a new value after it's already had a previous value assigned.

Following the design recipe (step 6.), write tests for the function. 

**Hint:**  The Functional Java standard library has some functions that might be useful to solve this exercise. 
Look at the section for Strings [here](https://comp.anu.edu.au/courses/comp1110/notes/functional-java/stdlib/#lib-strings).


 