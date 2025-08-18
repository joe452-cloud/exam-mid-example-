# Lists and structural recursion exercises

The data definitions we have seen so far are characterized by the fact that they describe data of a fixed size.
This applies to all basic data types, enumerations, records and general itemizations. Although such data definitions can be combined
to create deeply nested data definitions (e.g., a record inside a record, or a record where one of its fields is a general itemization),
we always know the exact number of data pieces (that is, the size of) any specific instance of such data definitions. 

In many programming problems, though, we need to process an undetermined (but finite) number of pieces of information.
For example, imagine a world program which, per each mouse click or key typed in the keyboard, needs to add a new geometrical shape to the existing
world, instead of just handling a fixed number of objects.

This week we will start working with a flagship example of arbitrarily large data definitions: **lists**. Arranging information in the form of lists
is an ubiquitous part our life. We write TO-DO lists, lists of invitees to an event, lists of products to be purchased at a supermarket, etc.

Lists are also great to introduce two key concepts in which we are going to insist over again and again and that here to stay, namely: 
(1) self-referential data definitions, and (2) recursion and recursive thinking.

The functional Java standard library provides a (self-referential) data definition for lists in the form of a generic (a.k.a.) parameterized
sealed interface named `ConsList<T>`, and a set of functions to manipulate and work with lists. The data definition for lists can be 
found [here](https://comp.anu.edu.au/courses/comp1110/notes/functional-java/lib/#lib-lists-conslist) and functions to manipulate and work with
lists [here](https://comp.anu.edu.au/courses/comp1110/notes/functional-java/#lib-lists-functions).

For any of the exercises below, you are not allowed to use higher-order functions for lists in the functional Java standard library, i.e., 
the functions documented [here](https://comp.anu.edu.au/courses/comp1110/notes/functional-java/#lib-higherorder).

## Exercise 1 - data definition for lists and associated functions in the functional Java standard library

Spend some time looking at the data definition for lists in the functional Java standard library. While we have already seen examples of 
parameterized sealed interfaces in the workshops (e.g., `Maybe<T>`), do you note anything special in the data definition for lists?

Look at the functions to manipulate lists in the standard functional Java library. Which function would you use 
to build a list with 10 `Integer`s (i.e., a list of length 10) filled with zeros? Write a small program that calls such a function, and print
the resulting list on screen using `println`.

## Exercise 2 - extract the first element of a list of `String`s 

*In a file `FirstString.java`*

Write a function, `firstString`, that given a list of strings, returns the first 
element of the list. The input list might be empty. Thus, the return type of 
the function should be `Maybe<String>`. 
Some examples for such a function are as follows:

- Given `["COMP1110", "COMP1140", "COMP6710"]`, expect `Something<String>("COMP1110")`.
- Given `[]`, return `Nothing<String>()`.

Some tests for such a function are as follows:

```java
void testExercise2() {
    ConsList<String> list1 = MakeList();
    testEqual(firstString(list1), new Nothing<String>(), "Edge case: empty list");

    ConsList<String> list2 = MakeList("COMP1110");
    testEqual(firstString(list2), new Something<String>("COMP1110"), "Edge case: singleton list");

    ConsList<String> list3 = MakeList("COMP1110","COMP1140","COMP6710");
    testEqual(firstString(list3), new Something<String>("COMP1110"), "General case: a list with several elements");
}
```

## Exercise 3 - calculate the length of a list of `Double`s

*In a file `LengthDoubles.java`*

Write a **recursive** function, `lengthDoubles`, that given a list of `Double`s, returns the length
of the list as an `int`. The input list might be empty, and in such a case, the function should return zero.
Some examples for such a function are as follows:

- Given `[3.2, 4.5, 7.3]`, expect `3`.
- Given `[]`, expect `0`.

Following the design recipe (i.e., step 6), write tests for such a function.
See the tests for Exercise 2 above, and those for the exercises below
as a guide on how to write tests with lists. 

## Exercise 4 - concatenate all the strings in a list of `String`s

*In a file `ConcatenateStrings.java`*

Write a **recursive** function, `concatenateStrings`, that given a list of strings, returns the result
of concatenating all the strings in the list. If the input list is empty, the function 
should return the empty string.

Some examples for such a function are as follows:

- Given `["COMP1110", "COMP1140", "COMP6710"]`, expect `"COMP1110COMP1140COMP6710"`.
- Given `["abc"]`, expect `"abc"`
- Given `[""]`, expect `""`

Following the design recipe (i.e., step 6), write tests for such a function.
See the tests for Exercise 2 above, and those for the exercises below
as a guide on how to write tests with lists. 

## Exercise 5 - remove an element from a list of `Integer`s 

*In a file `RemoveInteger.java`*

Write a **recursive** function, `removeInteger`, that given a list of `Integer`s and an `Integer`, 
returns a list of `Integer`s where the first occurrence of that `Integer` in the list is removed. 
If such element is not present, return the list unchanged.
Recall that we do not compare values of type `Integer` using the `==` operator,
but the `Equals` function in the standard functional Java library.

Some examples for such a function are as follows:

- Given `[3, 2, -1, 2]`, and `2`, expect `[3, -1, 2]`.
- Given `[3, 2, -1, 2]`, and `0`, expect `[3, 2, -1, 2]`.
- Given `[]`, and `-2`, expect `[]`.

Some tests are as follows:

```java
void testExercise5() {
    ConsList<Integer> input1  = MakeList();
    ConsList<Integer> result1 = MakeList();
    testEqual(removeInteger(input1, 1), result1, "Base case: Empty list");

    ConsList<Integer> input2  = MakeList(1);
    ConsList<Integer> result2 = MakeList();
    testEqual(removeInteger(input2, 1), result2, "Edge case: Remove the only element");

    ConsList<Integer> input3  = MakeList(1,2,3);
    ConsList<Integer> result3 = MakeList(1,2,3);
    testEqual(removeInteger(input3, 4), result3, "Try to remove element not in list");

    ConsList<Integer> input4  = MakeList(1,2,3);
    ConsList<Integer> result4 = MakeList(2,3);
    testEqual(removeInteger(input4, 1), result4, "Remove first element");

    ConsList<Integer> input5  = MakeList(1,2,3);
    ConsList<Integer> result5 = MakeList(1,3);
    testEqual(removeInteger(input5, 2), result5, "Remove middle element");

    ConsList<Integer> input6  = MakeList(1,2,3);
    ConsList<Integer> result6 = MakeList(1,2);
    testEqual(removeInteger(input6, 3), result6, "Remove last element");
}
```

## Exercise 6 -  find the largest element in a list of `Integer`s

*In a file `LargestInteger.java`*

Write a **recursive** function, `largestInteger`, that given a list of `Integer`s,
returns the largest element in the list as an `Integer`. You can assume that the 
input list will have at least one element.

Some examples for such a function are as follows:

- Given `[3, 1, 7, 2]`, expect `7`.
- Given `[4, 5, 3, 4]`, expect `5`.
- Given `[-1,-2]`, expect `-1`.

Some tests are as follows:

```java
void testExercise6() {
    ConsList<Integer> list1 = MakeList(1);
    testEqual(largestInteger(list1), 1, "Base case: singleton list");

    ConsList<Integer> list2 = MakeList(1,2);
    testEqual(largestInteger(list2), 2, "Step case: Two elements");

    ConsList<Integer> list3 = MakeList(1,2,3,4,5);
    testEqual(largestInteger(list3), 5, "Largest element at the end");

    ConsList<Integer> list4 = MakeList(1,2,5,4,3);
    testEqual(largestInteger(list4), 5, "Largest element in the middle");

    ConsList<Integer> list5 = MakeList(5,4,3,2,1);
    testEqual(largestInteger(list5), 5, "Largest element at the start");

    ConsList<Integer> list6 = MakeList(1,2,3,4,5,5,5);
    testEqual(largestInteger(list6), 5, "Duplicates of the largest element");

    ConsList<Integer> list7 = MakeList(1,1,2,2,3,5,7,3,3);
    testEqual(largestInteger(list7), 7, "Duplicates of non-largest elements");

    ConsList<Integer> list8 = MakeList(8,8,8,8,8,8,8,8);
    testEqual(largestInteger(list8), 8, "All elements equal");

    ConsList<Integer> list9 = MakeList(7,2,15,8,12,5,9,10,23,12);
    testEqual(largestInteger(list9), 23, "Non-monotonic list");

    ConsList<Integer> list10 = MakeList(7,-2,-15,8,-12,5,-9,10,-23,12);
    testEqual(largestInteger(list10), 12, "Positive and negative values");

    ConsList<Integer> list11 = MakeList(-7,-2,-15,-8,-12,-5,-9,-10,-23,-12);
    testEqual(largestInteger(list10), 12, "All negative values");

}
```

Hint: the function `First(list)` returns the first element in `list`, while the function `Rest(list)` returns the rest of `list`.
Applying the code template for lists to the result of the latter function may help in the solution of this exercise.

Hint: alternatively, `largestInteger(list)` can call a helper function, say, `largestIntegerEqualHelper(first, rest)`, which in turn calls `largestInteger(rest)` recursively.
This pattern is known as **mutual recursion**, i.e., `largestInteger` and `largestIntegerEqualHelper` are mutually recursive functions.

## Exercise 7 - determine if all elements in a list of `Integer`s are equal

*In a file `AllIntegersEqual.java`*

Write a function, `allIntegersEqual`, that given a list of `Integer`s,
returns `true` if all elements are equal, and `false` otherwise. If the function receives
an empty list, it returns `true`.

As always, we do not compare values of type `Integer` using the `==` operator,
but the `Equals` function in the standard functional Java library.

Hint: `Length(list)` returns the number of elements in a list. If the list is empty, it returns zero.

Hint: see also hints for Exercise 6.

Some tests for this exercise are as follows:

```java
void testExercise7() {
    ConsList<Integer> list1 = MakeList(); 
    testTrue(allIntegersEqual(list1), "Empty list");

    ConsList<Integer> list2 = MakeList(1); 
    testTrue(allIntegersEqual(list2), "One element");

    ConsList<Integer> list3 = MakeList(1,1,1); 
    testTrue(allIntegersEqual(list3), "Multiple elements (equal)");

    ConsList<Integer> list4 = MakeList(2,2,2,2,2,2); 
    testTrue(allIntegersEqual(list4), "Multiple elements (equal)");

    ConsList<Integer> list5 = MakeList(2,2,2,2,2,1); 
    testFalse(allIntegersEqual(list5), "Last element unequal");

    ConsList<Integer> list6 = MakeList(4,3,3,3,3); 
    testFalse(allIntegersEqual(list6), "First element unequal");

    ConsList<Integer> list7 = MakeList(2,2,2,3,2,2,2); 
    testFalse(allIntegersEqual(list7), "Middle element unequal");

    ConsList<Integer> list8 = MakeList(1,2,3,4,5,6); 
    testFalse(allIntegersEqual(list8), "No elements equal");
}
```

## Exercise 8 - determine if two lists of `Integer`s are equal

*In a file `IntegerListsEqual.java`*

Write a **recursive** function, `integerListsEqual`, that given two lists of `Integer`s,
returns `true` if they have elements with the same values in the same order, 
and `false` otherwise.

As always, we do not compare values of type `Integer` using the `==` operator,
but the `Equals` function in the standard functional Java library.

Some examples for such a function are:

* Given: `[]`, `[]`;  Expect: `true`
* Given: `[1,2,3]`, `[1,2,3]`; Expect: `true`
* Given: `[1,2,3]`, `[1,2,2]`; Expect: `false`

Some tests for this exercise are as follows:

```java
void testExercise8() {
    ConsList<Integer> list1a = MakeList();
    ConsList<Integer> list1b = MakeList();
    testTrue(integerListsEqual(list1a, list1b), "Base case: Empty lists");

    ConsList<Integer> list2a = MakeList(1);
    ConsList<Integer> list2b = MakeList(1);
    testTrue(integerListsEqual(list2a, list2b), "Lists of length 1 (equal)");

    ConsList<Integer> list3a = MakeList(1);
    ConsList<Integer> list3b = MakeList(2);
    testFalse(integerListsEqual(list3a, list3b), "Lists of length 1 (unequal)");

    ConsList<Integer> list4a = MakeList(1,7,3,1);
    ConsList<Integer> list4b = MakeList(1,7,3,1);
    testTrue(integerListsEqual(list4a, list4b), "Multiple elements (equal)");

    ConsList<Integer> list5a = MakeList(1,7,3,7,1);
    ConsList<Integer> list5b = MakeList(1,7,0,7,1);
    testFalse(integerListsEqual(list5a, list5b), "Multiple elements (unequal)");

    ConsList<Integer> list6a = MakeList(1,9,7,1,2);
    ConsList<Integer> list6b = MakeList(5,9,7,1,2);
    testFalse(integerListsEqual(list6a, list6b), "First elements are unequal");

    ConsList<Integer> list7a = MakeList(1,2,3,4,5);
    ConsList<Integer> list7b = MakeList(1,2,3,4,6);
    testFalse(integerListsEqual(list7a, list7b), "Last elements are unequal");

    ConsList<Integer> list8a = MakeList(1,2,3,4,5);
    ConsList<Integer> list8b = MakeList(1,2,3,4,5,6);
    testFalse(integerListsEqual(list8a, list8b), "Lists are different lengths");
}
```

## Exercise 9 - find an element in a list of lists (2D list) of `Integer`s

*In a file `IsPresent2DListIntegers.java`*

With Java generics, the template parameter of a generic type 
can be in turn the result of instantiating a generic type.
This allows, e.g., to build a list of lists of integers, that is 
a value of type `ConsList<ConsList<Integer>>`.

An element (of type `Integer`) is contained in a list of lists of
`Integer`s if it appears in at least one of the lists of the list of lists.

Write a **recursive** function `isPresent2DListIntegers` that given a list of lists
of `Integer`s, and an `Integer`, returns true if such `Integer`
appears in the list of lists.

Some examples are as follows:
  
 * Given: `[[]]`, 2; expect: `false`
 * Given: `[[1,2,3],[4,5,6],[7,8,9]]`, 7; expect: `true`
 * Given: `[[1,2,3],[4,5,6],[7,8,9]]`, 10; expect: `false`

Hint: it might be helpful to write a helper function that determines whether
an `Integer` is present in a (1D) list of `Integer`s.

Some tests for this exercise are as follows:

```java
void testExercise9() {
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
    
    // []
    ConsList<ConsList<Integer>> emptyList = new Nil<ConsList<Integer>>();
    // [[],[],[]]
    ConsList<ConsList<Integer>> emptySublists = MakeList(MakeList(),MakeList(),MakeList());
         
    // [[1,2,3]]
    ConsList<ConsList<Integer>> singleton = MakeList(MakeList(1,2,3));
    
    // [[1,2,3],[4,5,6],[7,8,9]]
    ConsList<ConsList<Integer>> multipleSublists = MakeList(oneTwoThree,fourFiveSix,sevenEightNine);

    // [[1,6],[],[891,91,33,1,3],[1],[1,6]]
    ConsList<ConsList<Integer>> disuniformList = 
         MakeList(MakeList(1,6),MakeList(),MakeList(891,91,33,1,3),MakeList(1,6));
        
    // [[1,2,3],[1,2,3],[1,2,3]]
    ConsList<ConsList<Integer>> repeatedList = MakeList(oneTwoThree,oneTwoThree,oneTwoThree);
    
    // [[1],[1],[1]]
    ConsList<ConsList<Integer>> oneElement = MakeList(justOne, justOne, justOne);

    testFalseisPresent2DListIntegers(emptyList, 3), "Empty list");

    testFalse(isPresent2DListIntegers(emptySublists, 2), "List of empty lists");

    testTrue(isPresent2DListIntegers(singleton, 1), "Single list (first element)");
    testTrue(isPresent2DListIntegers(singleton, 2), "Single list (middle element)");
    testTrue(isPresent2DListIntegers(singleton, 3), "Single list (last element)");
    testFalse(isPresent2DListIntegers(singleton, 4), "Single list (absent)");

    testTrue(isPresent2DListIntegers(multipleSublists, 1), "Multiple sublists (inside first list)");
    testTrue(isPresent2DListIntegers(multipleSublists, 5), "Multiple sublists (inside middle list)");
    testTrue(isPresent2DListIntegers(multipleSublists, 9), "Multiple sublists (inside last list)");
    testFalse(isPresent2DListIntegers(multipleSublists, 0), "Multiple sublists (absent)");

    testTrue(isPresent2DListIntegers(disuniformList, 1), "Disuniform list (present)");
    testTrue(isPresent2DListIntegers(disuniformList, 91), "Disuniform list (present)");
    testTrue(isPresent2DListIntegers(disuniformList, 6), "Disuniform list (present)");
    testFalse(isPresent2DListIntegers(disuniformList, 99), "Disuniform list (absent)");

    testTrue(isPresent2DListIntegers(repeatedList, 2), "Repeated list (present)");
    testFalse(isPresent2DListIntegers(repeatedList, -1), "Repeated list (absent)");

    testTrue(isPresent2DListIntegers(oneElement, 1), "One element repeated (present)");
    testFalse(isPresent2DListIntegers(oneElement, 0), "One element repeated (absent)");
}
```

## Exercise 10 - sum all the elements in a list of lists of `Integer`s

*In a file `Sum2DListIntegers.java`*

Write a **recursive** function `sum2DListIntegers` that given a list of lists
of `Integer`s, returns the sum of all elements in the list 
of lists. For an empty list of lists, or a list of empty lists, the function 
should return 0.

Some examples for this function are as follows:

* Given `[[]]`; expect `0`.
* Given `[[],[],[]]`; expect `0`.
* Given `[[1,2,3], [], [4]]`; expect `10`

Hint: it might be helpful to write a helper function that sums all elements
in a (1D) list of `Integer`s.

Some tests for this function are as follows:

```java
void testExercise10() {
    // [1]
    ConsList<Integer> justOne = MakeList(1);
    // [1,2,3]
    ConsList<Integer> oneTwoThree = MakeList(1,2,3);
    // [4,5,6]
    ConsList<Integer> fourFiveSix = MakeList(4,5,6);
    // [7,8,9]
    ConsList<Integer> sevenEightNine = MakeList(7,8,9);
    
    // []
    ConsList<ConsList<Integer>> emptyList = new Nil<ConsList<Integer>>();
    // [[],[],[]]
    ConsList<ConsList<Integer>> emptySublists = MakeList(MakeList(),MakeList(),MakeList());
         
    // [[1,2,3]]
    ConsList<ConsList<Integer>> singleton = MakeList(MakeList(1,2,3));
    
    // [[1,2,3],[4,5,6],[7,8,9]]
    ConsList<ConsList<Integer>> multipleSublists = MakeList(oneTwoThree,fourFiveSix,sevenEightNine);

    // [[1,6],[],[891,91,33,1,3],[1],[1,6]]
    ConsList<ConsList<Integer>> disuniformList = 
         MakeList(MakeList(1,6),MakeList(),MakeList(891,91,33,1,3),MakeList(1),MakeList(1,6));
        
    // [[1,2,3],[1,2,3],[1,2,3]]
    ConsList<ConsList<Integer>> repeatedList = MakeList(oneTwoThree,oneTwoThree,oneTwoThree);

    // [[1],[1],[1]]
    ConsList<ConsList<Integer>> oneElement = MakeList(justOne, justOne, justOne);
    
    testEqual(sum2DListIntegers(emptyList), 0, "Empty list");
    testEqual(sum2DListIntegers(emptySublists), 0, "List of empty lists");
    testEqual(sum2DListIntegers(singleton), 6, "Single sublist");
    testEqual(sum2DListIntegers(multipleSublists), 45, "Multiple sublists");
    testEqual(sum2DListIntegers(disuniformList), 1034, "Disuniform list");
    testEqual(sum2DListIntegers(repeatedList), 18, "Each sublist is repeated");
    testEqual(sum2DListIntegers(oneElement), 3, "Each sublist contains the same single element");
}
```

## Exercise 11 - multiply by next

*In a file `MultiplyByNextIntegers.java`*

Design a function `multiplyByNextIntegers` that given a list of `Integer`s, 
returns a new list which contains every number in the input list multiplied
by the number that comes right after it, and does not have the last element
of the input list. If the input list has only one element, returns an empty
list. You may assume that the input list has at least one element.

Some examples of this function are as follows:

 * Given: `[1]`; Expect: `[]`
 * Given: [1,2,3,4]; Expect: `[1*2, 2*3, 3*4] = [2, 6, 12]`

Hint: the `Length`, `First` and `Rest` list functions from the standard functional 
Java library might be useful.

Some tests for this function are as follows:

```java
void testExercise11() {
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

    // []
    ConsList<Integer> result1 = MakeList();
    // [6]
    ConsList<Integer> result2 = MakeList(6);
    // [2,6]
    ConsList<Integer> result3 = MakeList(2,6);
    // [20,30]
    ConsList<Integer> result4 = MakeList(20,30);
    // [56,72]
    ConsList<Integer> result5 = MakeList(56,72);
    
    testEqual(multiplyByNextIntegers(justOne), result1, "List with one element");
    testEqual(multiplyByNextIntegers(twoElements), result2, "List with two elements");
    testEqual(multiplyByNextIntegers(oneTwoThree), result3, "List with three elements [1,2,3]");
    testEqual(multiplyByNextIntegers(fourFiveSix), result4, "List with three elements [4,5,6]");
    testEqual(multiplyByNextIntegers(sevenEightNine), result5, "List with three elements [7,8,9]");
}
```


## Exercise 12 - divide by previous

*In a file `DivideByPreviousDoubles.java`*

Design a function `divideByPreviousDoubles` that given a list of `Double`s, 
returns a new list which contains every number in the input list dividing
by the number that comes right before it, and does not have the first element
of the input list. If the input list has only one element, returns an empty
list. You may assume that no elements in the input list are equal to zero.

Some examples of this function are as follows:

 * Given: `[1]`; Expect: `[]`
 * Given: [1,2,3,4]; Expect: `[2/1, 3/2, 4/3] = [2, 1.5, 1.333]`

Hint: the `Length`, `First` and `Rest` list functions from the standard functional 
Java library might be useful.


Some tests for this function are as follows:

```java
void testExercise12() {
    // [1]
    ConsList<Double> list1 = MakeList(1.0);
        
    // [3.0, 2.0]
    ConsList<Double> list2 = MakeList(3.0, 2.0);
    
    // [1.0, 2.0, 3.0]
    ConsList<Double> list3 = MakeList(1.0,2.0,3.0);
    
    // [4.0, 5.0, 6.0]
    ConsList<Double> list4 = MakeList(4.0,5.0,6.0);

    // [7.0, 8.0, 9.0]
    ConsList<Double> list5 = MakeList(7.0,8.0,9.0);

    // []
    ConsList<Double> result1 = MakeList();
    
    // [2/3]
    ConsList<Double> result2 = MakeList(2.0/3.0);

    // [2/1, 3/2]
    ConsList<Double> result3 = MakeList(2.0/1.0, 3.0/2.0);

    // [5/4, 6/5]
    ConsList<Double> result4 = MakeList(5.0/4.0, 6.0/5.0);
        
    // [8/7, 9/8]
    ConsList<Double> result5 = MakeList(8.0/7.0, 9.0/8.0);

    testEqual(divideByPreviousDoubles(list1), result1, "List with one element");
    testEqual(divideByPreviousDoubles(list2), result2, "List with two elements");
    testEqual(divideByPreviousDoubles(list3), result3, "List with three elements [1,2,3]");
    testEqual(divideByPreviousDoubles(list4), result4, "List with three elements [4,5,6]");
    testEqual(divideByPreviousDoubles(list5), result5, "List with three elements [7,8,9]");
}
```


## Exercise 13 - Generic functions (distinction-level content)

Whenever it applies, write generic versions of the functions written in previous exercises.

Following the design recipe (i.e., step 6), write tests for all functions written.

The tests for each function should check at least with two different element types.

## More practice exercises with lists

* Design a function, called `removeNegativeIntegers`, that given a list of `Integer`s,
returns a new list where the negative elements in the input list are no longer present
in the output list.
* Design a function, called `squareIntegers`, that given a list of `Integer`s,
returns a new list where the elements in the input list are squared.
* Design a function, called `copyStringList`, that given a list of `String`s,
returns a new list with the same elements as the input list
* Design a function, called `lastStringList`, that given a list of `String`s, returns the last
element of the list
* Design a function, called `appendStringList`, that given: (1) a list of `String`s, and (2) an
`String`, returns a new list resulting from appending (2) at the end of (1)
* Design a function, called `getString`, that given: (1) a list of `String`s, and (2) the
index position of an element in the list, returns the element located in such
position. The position of the first element is `0`, and that of the last element,
`Length(list)-1`. Assume that the user always provides a valid index position.
* Design a function, called `reverseStringList`, that given a list of `String`s, returns a list 
  with the elements of the input list reversed.