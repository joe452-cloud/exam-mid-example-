# Higher-order list functions

Once you have written quite a few functions to process/manipulate lists, you will realise that you will 
find yourself doing the same kind of operations/applying the same patterns repeatedly.

The so-called higher-order list functions abstract these patterns in such a way that you can significantly 
reduce code replication in your programs, and write much more concise code.

The goal of this set of exercises is to practice with these sort of functions in the standard functional Java library.
You can find the documentation of such functions [here](https://comp.anu.edu.au/courses/comp1110/notes/functional-java/stdlib/#lib-higherorder).

## Exercise 1 - squaring the elements in a list of `Integer`s

*In a file `SquareIntegerList.java`* 

Write a function, `squareIntegerList`, that given a list of `Integer`s, i.e., `ConsList<Integer>`,
returns a list of `Integer`s with the elements of the input list squared. 

Some examples of this function are as follows:

  * Given: `[]`; Expect: `[]`
  * Given: `[1,2,3,-4,5]`; Expect: `[1*1, 2*2, 3*3, -4*-4, 5*5]` = `[1,4,9,16,25]`

Your function's body should only contain a single `return` statement.

Hint: the lambda expression `x->x*x` combined with a call to the `Map` higher-order function may help to solve this exercise

Some tests for this exercise are as follows:
 
```java
void testExercise1() {
    // [1]
    ConsList<Integer> justOne = MakeList(1);
    // [3,2]
    ConsList<Integer> twoElements = MakeList(3,2);
    // [1,2,3]
    ConsList<Integer> oneTwoThree = MakeList(1,2,3);
    // [4,5,6]
    ConsList<Integer> fourFiveSix = MakeList(4,5,6);
    // [7,8,9]
    ConsList<Integer> sevenEightNine = MakeList(7,8,9);

    // [-1,-2,3,0]
    ConsList<Integer> negativeAndZero = MakeList(-1,-2,3,0);

    ConsList<Integer> result1 = MakeList(1);
    ConsList<Integer> result2 = MakeList(9,4);
    ConsList<Integer> result3 = MakeList(1,4,9);
    ConsList<Integer> result4 = MakeList(16,25,36);
    ConsList<Integer> result5 = MakeList(49,64,81);
    ConsList<Integer> result6 = MakeList(1,4,9,0);

    
    testEqual(new Nil(), new Nil(), "Empty list doesn't do anything");
    testEqual(squareIntegerList(justOne), result1, "Singleton list");
    testEqual(squareIntegerList(twoElements), result2, "List with two elements");
    testEqual(squareIntegerList(oneTwoThree), result3, "Three elements, [1,2,3]");
    testEqual(squareIntegerList(fourFiveSix), result4, "Three elements, [4,5,6]");
    testEqual(squareIntegerList(sevenEightNine), result5, "Three elements, [7,8,9]");
    testEqual(squareIntegerList(negativeAndZero), result6, "Negative elements and zero");
}
```

## Exercise 2 - summing the elements in a list of `Integer`s

*In a file `SumIntegerList.java`*

Write a function, `sumIntegerList`, that given a list of `Integer`s, i.e., `ConsList<Integer>`,
returns the sum of all elements in the list. If the input list is empty, the function returns `0`.

Some examples of this function are as follows:

  * Given: `[]`; Expect: `0`
  * Given: `[1]`; Expect: `1`
  * Given: `[1,2,3]` ; Expect: `1+2+3=6`

Your function's body should only contain a single `return` statement.

Hint: either the `Fold` or `FoldLeft` higher-order function may help to solve this exercise

Some tests for the function are as follows:

```java
void testExercise2() {
    // [1]
    ConsList<Integer> justOne = MakeList(1);
    // [3,2]
    ConsList<Integer> twoElements = MakeList(3,2);
    // [1,2,3]
    ConsList<Integer> oneTwoThree = MakeList(1,2,3);
    // [4,5,6]
    ConsList<Integer> fourFiveSix = MakeList(4,5,6);
    // [7,8,9]
    ConsList<Integer> sevenEightNine = MakeList(7,8,9);

    // [-1,-2,3,0]
    ConsList<Integer> negativeAndZero = MakeList(-1,-2,3,0);

    testEqual(sumIntegerList(new Nil<Integer>()), 0, "Empty list doesn't do anything");
    testEqual(sumIntegerList(justOne), 1, "Singleton list");
    testEqual(sumIntegerList(twoElements), 5, "List with two elements");
    testEqual(sumIntegerList(oneTwoThree), 6, "Three elements, [1,2,3]");
    testEqual(sumIntegerList(fourFiveSix), 15, "Three elements, [4,5,6]");
    testEqual(sumIntegerList(sevenEightNine), 24, "Three elements, [7,8,9]");
    testEqual(sumIntegerList(MakeList(-1,-2,3)), 0, "Negative elements");
}
```

## Exercise 3 - remove even elements from a list of `Integer`s

*In a file `RemoveEvenIntegerList.java`*

Write a function, `removeEvenIntegerList`, that given a list of `Integer`s, i.e., `ConsList<Integer>`,
returns a list where all even elements in the input list are no longer present in the output list. 

Some examples of this function are as follows:

 * Given: `[]`; Expect: `[]`
 * Given: `[1,2,3,4]`; Expect: `[1,3]`
 * Given: `[1,3,5,7]`; Expect: `[1,3,5,7]`
 * Given: `[2,4,6,8]`; Expect: `[]`

Your function's body should only contain a single `return` statement.

Hint: the `Filter` higher-order list function may help to solve this exercise

Some tests for this function are as follows:

```java
void testExercise3() {
    // [1]
    ConsList<Integer> justOne = MakeList(1);
    // [3,2]
    ConsList<Integer> twoElements = MakeList(3,2);
    // [1,2,3]
    ConsList<Integer> oneTwoThree = MakeList(1,2,3);
    // [4,5,6]
    ConsList<Integer> fourFiveSix = MakeList(4,5,6);
    // [7,8,9]
    ConsList<Integer> sevenEightNine = MakeList(7,8,9);

    ConsList<Integer> result1 = MakeList(1);
    ConsList<Integer> result2 = MakeList(3);
    ConsList<Integer> result3 = MakeList(1,3);
    ConsList<Integer> result4 = MakeList(5);
    ConsList<Integer> result5 = MakeList(7,9);
    ConsList<Integer> result6 = MakeList(-1,3);

    testEqual(new Nil<Integer>(), new Nil<Integer>(), "Empty list doesn't do anything");
    testEqual(removeEvenIntegerList(justOne), result1, "Singleton list");
    testEqual(removeEvenIntegerList(twoElements), result2, "List with two elements");
    testEqual(removeEvenIntegerList(oneTwoThree), result3, "Three elements, [1,2,3]");
    testEqual(removeEvenIntegerList(fourFiveSix), result4, "Three elements, [4,5,6]");
    testEqual(removeEvenIntegerList(sevenEightNine), result5, "Three elements, [7,8,9]");
    testEqual(removeEvenIntegerList(MakeList(-1,-2,3,0)), result6, "Negative elements and zero");
}
```


## Exercise 4 - revisiting list exercise from Workshop 3A

*In a file `ConcatenateStrings.java`*

Solve Exercise 4 [here](../ws3a/ListsAndStructuralRecursionExercises.md) using higher-order list functions. Which higher-order list function can be useful here?

## Exercise 5 - revisiting list exercise from Workshop 3A

*In a file `LargestInteger.java`*

Solve Exercise 6 [here](../ws3a/ListsAndStructuralRecursionExercises.md) using higher-order list functions. Which higher-order list function can be useful here?

## Exercise 6 - revisiting list exercise from Workshop 3A 

*In a file `AllIntegersEqual.java`*

Solve Exercise 7 [here](../ws3a/ListsAndStructuralRecursionExercises.md) using higher-order list functions. Which higher-order list function can be useful here?

## Exercise 7 - revisiting list exercise from workshop 3A 

*In a file `Sum2DListIntegers.java`*

Solve Exercise 10 [here](../ws3a/ListsAndStructuralRecursionExercises.md) using higher-order list functions. Which higher-order list function can be useful here?

## Exercise 8 - evaluation of the composition of two functions (warm up for the next exercises)

*In a file `EvaluateFunctionComposition.java`*

Write a function, `evaluateFunctionComposition`, that given two arguments of type `Function<Integer,Integer>`, say, `f` and `g`,
and a third argument `x` of type `int`, returns the result of evaluating `f(g(x))`, that is, we first evaluate `g` with `x`, and 
the result is passed as an input to `f`. 

Some examples for this function are as follows:

 * Given: `x->x*2`, `y->y+2`, and `9`; Expect: `2*(9+2) = 22`
 * Given: `y->y*y`, `z->2-z`, and `10`; Expect: `(2-10)*(2-10) = 64`

 Note: lambdas can use `x` as the variable name. Or `y`. Or `z`. It doesn't make a difference.

 This exercise is a warm up for the next two exercises. 
 You do not actually have to use a higher-order list function to solve this exercise.

```java
void testExercise8() {
    Function<Integer, Integer> f1 = x -> x;
    Function<Integer, Integer> f2 = x -> x + 2;
    Function<Integer, Integer> f3 = x -> x / 3;
    Function<Integer, Integer> f4 = x -> x * x;
    Function<Integer, Integer> f5 = x -> x - 5;
    Function<Integer, Integer> f6 = y -> y*y;
    Function<Integer, Integer> f7 = z -> 2-z;

    testEqual(evaluateFunctionComposition(f1, f1, 0), 0, "Identity and identity");
    testEqual(evaluateFunctionComposition(f2, f1, 1), 3, "Identify and other function");
    testEqual(evaluateFunctionComposition(f1, f3, -3), -1, "Other function and identity");
    testEqual(evaluateFunctionComposition(f2, f2, -1), 3, "f2(f2(-1)) = 3");
    testEqual(evaluateFunctionComposition(f3, f2, 0), 0, "f3(f2(0)) = 0");
    testEqual(evaluateFunctionComposition(f4, f5, 2), 9, "f4(f5(2)) = 9");
    testEqual(evaluateFunctionComposition(f5, f4, 5), 20, "f5(f4(5)) = 20");
    testEqual(evaluateFunctionComposition(f2, f3, 10), 5, "f2(f3(10)) = 6");
    testEqual(evaluateFunctionComposition(f3, f5, -10), -5, "f3(f5(-10)) = -5");
    testEqual(evaluateFunctionComposition(f4, f4, 20), 160000, "f4(f4(20)) = 8000");
    testEqual(evaluateFunctionComposition(f6, f7, 10), 64, "f6(f7(10)) = 64");
}
```


## Exercise 9 - Composing a list of functions left to right

*In a file `ComposeFunctionsLeftToRight.java`*

As we have seen in the previous exercise, **Function composition** is the process of combining functions in a 
particular way. 
 
If we compose $f(x)$ and $g(x)$, written $(f \circ g)(x)$, then we get a new function which is equal to $f(g(x))$. 
For example, if $f(x) = x + 2$, and $g(x) = 4*x$, $(f\circ g)(x) = f(g(x)) = (4*x) + 2$.

However, notice that, if we swap the order of $f$ and $g$, we get a different result, $(g \circ f)(x) = g(f(x)) = 4*(x + 2)  = 4*x + 8$.

We can also do the composition of more than two functions, e.g., three functions, $f(x)$, $g(x)$, and $h(x)$,
written as $(f \circ g \circ h)(x) = f(g(h(x)))$.

Thus, in general, function composition is when we have a chain of functions and the output of a function in the chain becomes the input of the 
next function in the chain. (By the way, for those interested in deep learning, function composition is at the root of 
deep neural networks; indeed deep neural networks can be expressed mathematically as the composition of 
functions across multiple layers.)

Write a function, `composeFunctionsLeftToRight`, that given a list of `Function<Integer,Integer>`, returns a `Function<Integer,Integer>`
resulting from composing all the functions in the list from left to right. 

Thus, for example, for the list of functions `[f, g, h]`, we want the function $f \circ g \circ h=f(g(h(x)))$ as the output.

If the list is empty, return the **identity function**, which is a function that returns its input unchanged. E.g. `x -> x`. 

Hint: for this exercise, you must use the `Fold` higher-order function from the functional Java standard library, as it starts 
at the end of the list, and finishes at the beginning, i.e., it goes right to left. 

Hint: the value passed to the `start` argument for the `Fold` function should be the identity function.  Note that any function
composed with the identify function, returns the function itself.

Some tests for the function are as follows:

```java
void testExercise9() {
    // In general, there is no way of determining (within finite time) 
    // whether two functions are equal.

    // Instead, we can become approximately certain that two functions
    // are equal if they consistently produce the same output when 
    // given the same input.

    Function<Integer, Integer> f1 = x -> x;
    Function<Integer, Integer> f2 = x -> x + 2;
    Function<Integer, Integer> f3 = x -> x * x;
    Function<Integer, Integer> f4 = x -> x - 5;

    // Becomes x -> x
    ConsList<Function<Integer,Integer>> functions1 = MakeList();

    // Becomes x -> x*x
    ConsList<Function<Integer,Integer>> functions2 = MakeList(f3);
    
    // Becomes x -> x^2 + 2
    ConsList<Function<Integer,Integer>> functions3 = MakeList(f2,f3);

    // Becomes (x-5)^2 + 2
    ConsList<Function<Integer,Integer>> functions4 = MakeList(f1,f2,f3,f4);
    
    ConsList<Integer> numbers = MakeList(-5,-1,0,1,3,5);

    ConsList<Integer> result1 = numbers;
    ConsList<Integer> result2 = MakeList(25,1,0,1,9,25);
    ConsList<Integer> result3 = MakeList(27,3,2,3,11,27);
    ConsList<Integer> result4 = MakeList(102,38,27,18,6,2);

    testEqual(Map(composeFunctionsLeftToRight(functions1), numbers), result1, "Empty list");
    testEqual(Map(composeFunctionsLeftToRight(functions2), numbers), result2, "Singleton");
    testEqual(Map(composeFunctionsLeftToRight(functions3), numbers), result3, "Two functions");
    testEqual(Map(composeFunctionsLeftToRight(functions4), numbers), result4, "Four functions");
}
```

## Exercise 10 - Composing a list of functions right to left

*In a file `ComposeFunctionsRightToLeft.java`*

Write a function, `composeFunctionsRightToLeft`, that given a list of `Function<Integer,Integer>`, returns a `Function<Integer,Integer>`
resulting from composing all the functions in the list from right to left.

Thus, for example, for the list of functions `[f, g, h]`, we want the function $h \circ g \circ f=h(g(f(x)))$ as the output.

Hint: for this exercise, you must use the `FoldLeft` higher-order function from the functional Java standard library, as it starts 
at the beginning of the list, and finishes at the end, i.e., it goes left to right.

Some tests for this function are as follows:

```java

void testExercise10() {
    // In general, there is no way of determining (within finite time) 
    // whether two functions are equal.

    // Instead, we can become approximately certain that two functions
    // are equal if they consistently produce the same output when 
    // given the same input.

    Function<Integer, Integer> f1 = x -> x;
    Function<Integer, Integer> f2 = x -> x + 2;
    Function<Integer, Integer> f3 = x -> x * x;
    Function<Integer, Integer> f4 = x -> x - 5;

    // Becomes x -> x
    ConsList<Function<Integer,Integer>> functions1 = MakeList();

    // Becomes x -> x*x
    ConsList<Function<Integer,Integer>> functions2 = MakeList(f3);
    
    // Becomes x -> (x+2)^2
    ConsList<Function<Integer,Integer>> functions3 = MakeList(f2,f3);
        new Cons(f2, new Cons(f3, new Nil()));

    // Becomes x -> (x+2)^2 - 5
    ConsList<Function<Integer,Integer>> functions4 = MakeList(f1,f2,f3,f4);
    
    ConsList<Integer> numbers = MakeList(-5,-1,0,1,3,5);

    ConsList<Integer> result1 = numbers;
    ConsList<Integer> result2 = MakeList(25,1,0,1,9,25);
    ConsList<Integer> result3 = MakeList(9,1,4,9,25,49);
    ConsList<Integer> result4 = MakeList(4,-4,-1,4,20,44);

    testEqual(Map(composeFunctionsRightToLeft(functions1), numbers), result1, "Empty list");
    testEqual(Map(composeFunctionsRightToLeft(functions2), numbers), result2, "Singleton");
    testEqual(Map(composeFunctionsRightToLeft(functions3), numbers), result3, "Two functions");
    testEqual(Map(composeFunctionsRightToLeft(functions4), numbers), result4, "Four functions");
}
```

## Exercise 11 - Generics (distinction-level content)

Write **generic** variants of the functions in Exercise 9 and 10. Thus, the input list type should be `ConsList<Function<T,T>>`, where `T`
is the type parameter of the generic function, and the return type should be `Function<T,T>`.