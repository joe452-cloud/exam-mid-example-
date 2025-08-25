# Recursion exercises

As mentioned in the workshop, recursion is a computational problem-solving pattern that can be
applied to solve problems beyond those that involve self-referential
data definitions (i.e., structural recursion).

In this set of exercises we will tackle examples of such problems.

## Exercise 1 - Factorial

*In a file `Factorial.java`*

Write a **recursive** function, `factorial`, that given an input positive integer number 
(i.e., an `int`), returns as an `int` the factorial of the input `int`.

The factorial of `1` is `1`. The factorial of a number larger than one, `n>1`, is defined as the
product of `n` and the factorial of `n-1`.

Some examples are as follows:

* Given: `1`; Expect: `1`
* Given: `2`; Expect: `1*2=2`
* Given: `4`; Expect: `1*2*3*4=24`

Some tests for this function are as follows:

```java
void testExercise1()
{
  testEqual(factorial(1), 1   , "Base case: factorial of 1");
  testEqual(factorial(2), 2   , "Recursive case: factorial of 2");
  testEqual(factorial(4), 24  , "Recursive case: factorial of 4");  
  testEqual(factorial(6), 720 , "Recursive case: factorial of 6");
}
```

## Exercise 2 - Sum positive numbers

*In a file `SumPositive.java`*

Write a **recursive** function, `sumPositive`, that given a integer number `n` (`int`) greater or equal than one, 
returns an integer with the sum of the first `n` positive integer numbers. 

Some examples are as follows:

* Given: `1`; Expect: `1`
* Given: `2`; Expect: `1+2=3`
* Given: `3`; Expect: `1+2+3=6`

Some tests for this function are as follows:

```java
void testExercise2() 
{
  testEqual(sumPositive(1),  1,  "Base case: Sum of the first positive integers");
  testEqual(sumPositive(2),  3,  "Recursive case: Sum of the first 2 positive integers is 3");
  testEqual(sumPositive(3),  6,  "Recursive case: Sum of the first 3 positive integers is 6");
  testEqual(sumPositive(5),  15, "Recursive case: Sum of the first 5 positive integers is 15");
  testEqual(sumPositive(10), 55, "Recursive case: Sum of the first 10 positive integers is 55");
}
```

## Exercise 3 - Sum positive numbers within an interval

*In a file `SumPositiveInterval.java`*

Write a **recursive** function, `sumPositiveInterval`, that given two positive numbers `n` and `m`, with `n` being smaller or equal than `m`,
returns the sum of all numbers larger or equal than `n` and smaller or equal than `m`.
 
Some examples are as follows:

* Given: `2` and `2`; Expect: `2`
* Given: `3` and `5`; Expect: `3+4+5=12`

Some tests for this function are as follows:

```java
void testExercise3() 
{
  testEqual(sumPositiveInterval(1,1), 1, "Base case: n = m = 1");
  testEqual(sumPositiveInterval(2,2), 2, "Base case: n = m = 2");
  testEqual(sumPositiveInterval(3,5), 12, "Recursive case: Sum of integers on the interval [3,5]");
  testEqual(sumPositiveInterval(1,5), sumPositive(5), "Edge case: If n = 1, then it should behave identically to sumPositive");
  testEqual(sumPositiveInterval(3,7), 25, "Recursive case: Sum of integers on the interval [3,7]");
  testEqual(sumPositiveInterval(9,10), 19, "Recursive case: Sum of integers on the interval [9,10]");
}
```

## Exercise 4 - Repeat

*In a file `Repeat.java`*

Write a **recursive** function, `repeat`, that given an `String` and an integer
number `n` (`int`) greater or equal than zero, returns a `String` with `n` copies
of itself. If `n` is zero, then the function must return the empty string.

Some examples are as follows:

* Given: `"wow"` and `0`; Expect: `""`
* Given: `"comp1110"` and `1`; Expect: `"comp1110"`
* Given: `"hello"` and `3`; Expect: `"hellohellohello"`
* Given: `"comp1110/1140/6710 "` and `5`; Expect: `"comp1110/1140/6710 comp1110/1140/6710 comp1110/1140/6710 comp1110/1140/6710 comp1110/1140/6710 "`

Some tests for this function are as follows:

```java
void testExercise4()
{
  testEqual(recursive("wow",0), 
            "", 
            "Base case: n = 0");
  testEqual(recursive("comp1110",1), 
            "comp1110", 
            "Recursive case: n = 1");
  testEqual(recursive("",3), 
            "", 
            "Edge case: Input string is empty");
  testEqual(recursive("hello",3), 
            "hellohellohello", 
            "Recursive case: n = 3");
  testEqual(recursive("comp1110/1140/6710 ",5), 
            "comp1110/1140/6710 comp1110/1140/6710 comp1110/1140/6710 " +
            "comp1110/1140/6710 comp1110/1140/6710 ", 
            "Recursive case: n = 5");
  testEqual(recursive("around the world ", 144),
            "around the world around the world around the world around the world " +
            "around the world around the world around the world around the world " + 
            "around the world around the world around the world around the world " +
            "around the world around the world around the world around the world " +
            "around the world around the world around the world around the world " + 
            "around the world around the world around the world around the world " +
            "around the world around the world around the world around the world " +
            "around the world around the world around the world around the world " + 
            "around the world around the world around the world around the world " +
            "around the world around the world around the world around the world " +
            "around the world around the world around the world around the world " + 
            "around the world around the world around the world around the world " +
            "around the world around the world around the world around the world " +
            "around the world around the world around the world around the world " + 
            "around the world around the world around the world around the world " +
            "around the world around the world around the world around the world " +
            "around the world around the world around the world around the world " + 
            "around the world around the world around the world around the world " +
            "around the world around the world around the world around the world " +
            "around the world around the world around the world around the world " + 
            "around the world around the world around the world around the world " +
            "around the world around the world around the world around the world " +
            "around the world around the world around the world around the world " + 
            "around the world around the world around the world around the world " +
            "around the world around the world around the world around the world " +
            "around the world around the world around the world around the world " + 
            "around the world around the world around the world around the world " +
            "around the world around the world around the world around the world " +
            "around the world around the world around the world around the world " + 
            "around the world around the world around the world around the world " +
            "around the world around the world around the world around the world " +
            "around the world around the world around the world around the world " + 
            "around the world around the world around the world around the world " +
            "around the world around the world around the world around the world " +
            "around the world around the world around the world around the world " + 
            "around the world around the world around the world around the world ",
            "Edge case: Daft Punk");
}
```

## Exercise 5 - Symmetric sequence of numbers

*In a file `SymmetricSequence.java`*

Write a **recursive** function, `symmetricSequence`, that given a positive integer number `n>0`, returns a `String` containing
a symmetric sequence of integer numbers separated by spaces with descending integers from `n` till `1`, and then ascending integers
from 1 till `n`. 

Some examples are as follows:

* Given: `1`; Expect: `"1 1"`
* Given: `2`; Expect: `"2 1 1 2"`
* Given: `3`; Expect: `"3 2 1 1 2 3"`

Hint: consider splitting the function into two helpers, one that returns the descending sequence, and another one that returns the ascending 
sequence.

Some tests for this function are as follows:

```java
void testExercise5() 
{
  testEqual(symmetricSequence(1), "1 1", "Base case: n = 1");
  testEqual(symmetricSequence(2), "2 1 1 2", "Recursive case: n = 2");
  testEqual(symmetricSequence(3), "3 2 1 1 2 3", "Recursive case: n = 3");
  testEqual(symmetricSequence(5), "5 4 3 2 1 1 2 3 4 5", "Recursive case: n = 5");
  testEqual(symmetricSequence(10),"10 9 8 7 6 5 4 3 2 1 1 2 3 4 5 6 7 8 9 10", "Recursive case: n = 10");
}
```

## Exercise 6 - Logical comparison

The following recursive function implements a logical (i.e., boolean) comparison among two non-negative 
integer numbers `a` and `b`. 

Without executing the function, can you figure out which comparison is it and why? 

Now execute the function for different combination of the inputs, and double check whether your answer
was correct. 

How would you implement recursively the opposite logical comparison?

```java
boolean comparison(int a, int b)
{
  if (b==0) {
    return false;
  }
  else if (a==0) {
    return true;
  }
  else {
    return comparison(a-1,b-1);
  }
}
```

## Exercise 7 - Find next character

*In a file `FindNextCharacter.java`*

Design a **recursive** function, `findNextCharacter`, that given an `String` , an index within such
`String` (i.e., an integer number within `0` and `Length(string)-1`), and a `char`,
returns the index of the first occurrence of the character within the string **after**
the provided index. That is, if the character occurs at or before the 
given index, you should ignore it. If the character is not found, the function
returns `-1`. 


Some examples are as follows:

* Given: `""`, `0`, `'a'`; Expect: `-1` 
* Given: `"a"`, `0`, `'a'`; Expect: `-1`
* Given: `"bba"`, `0`, `'a'`; Expect: `2`
* Given: `"babab"`, `0`, `'a'`; Expect: `1`
* Given: `"babab"`, `2`, `'a'`; Expect: `3`

Some tests for this function are as follows:

```java
void testExercise7() {
    testEqual(findNextCharacter("", 0,'a'), -1, "Edge case: Empty string");
    testEqual(findNextCharacter("k", 0,'b'), -1, "Edge case: Length = 1");
    testEqual(findNextCharacter("c",0,'c'), -1, "Edge case: Length = 1 and character present");
    testEqual(findNextCharacter("ko", 0,'d'), -1, "char not present");
    testEqual(findNextCharacter("korrh", 0,'e'), -1, "char not present");
    testEqual(findNextCharacter("kf", 0,'f'), 1, "char present after index");
    testEqual(findNextCharacter("korrhoho", 0,'o'), 1, "char present after index multiple times");
    testEqual(findNextCharacter("korrho", 3,'o'), 5, "char present before and after index"); 
    testEqual(findNextCharacter("korrh", 4,'o'), -1, "char present before and not after index"); 
    testEqual(findNextCharacter("korrh", 1,'o'), -1, "char present at and not after index");
    testEqual(findNextCharacter("korrho", 1,'o'), 5, "char present at and after index");
    testEqual(findNextCharacter("korrh", 5,'o'), -1, "Edge case: Index is at the end of the string");
}
```
## Additional Exercises

* Write a recursive function, `countConsonants`, that given a `String`, counts the number of consonants in the 
  `String`. Following the design recipe, write comprehensive tests for such a function.
* Write a recursive function, `reverseString`, that given a `String`, returns the reversed String. For example,
   given `"abc"`, it returns `"cba"`. Following the design recipe, write comprehensive tests for such a function.
* Write a recursive function, `findFirstUpperCaseVowel`, that given a `String`, returns the index of the first upper case vowel. If no upper case vowel, returns `-1`. Following the design recipe, write comprehensive tests for such a function.
* Write a recursive function, `findLastUpperCaseVowel`, that given a `String`, returns the index of the last upper case vowel. If no upper case vowel, returns `-1`. Following the design recipe, write comprehensive tests for such a function.  
 
