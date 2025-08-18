# General trees, intertwined data definitions, and mutual recursion

In this set of exercises, we are going to code operations on general trees, also known as rose trees, or $n$-ary trees, 
where $n\geq 2$ is a number that denotes the maximum number of children any node in the tree may have.

General trees are examples of **intertwined** data definitions. 

Operations with intertwined data definitions involve the use of
the so-called **mutually recursive** functions. 

Two functions, say, `f` and `g`, are **mutually recursive** if `f` calls `g` and then `g`
calls `f` again. Thus, `f` ends calling itself indirectly through a call to `g`. In general, 
mutual recursion can span a sequence of multiple function calls (i.e., not necessarily two), 
where the last function in this sequence ends up being the first in the sequence.

## Exercise 1 -  Write the code template for a data definition and a helper function

You are given the following data definition:

```java
record RoseTree(ConsList<RoseTree> children, Integer value) {} 
```

Spend some time trying to dissect the data definition. Is it a recursive/self-referential data definition?

`RoseTree` is a data definition that can be used to represent general trees, a.k.a. rose trees. 
A rose tree is a generalization of a binary tree where each node may have any number of children, 
represented as a `ConsList<RoseTree>`. 

In this particular data definition, each node of the tree (no matter
whether it is an inner or a leaf node) has a data value of type `Integer`.

Write the template for traversing or processing values of this data type.
The template involves a call to a helper function that will process the list of children of the 
`RoseTree` value. 

* Would such helper function in turn call the function where we apply the template for `RoseTree`?
*  If yes, which type of recursive pattern would this be? 
* Can you write the code template for the function that processes the list of children of a `RoseTree` node?

## Exercise 2 -  Sum of all the elements in a `RoseTree`

*In a file `RoseTreeSum.java`*

Write a function `roseTreeSum` that given a `RoseTree`, returns the sum of all elements in the tree.
**You must solve this exercise without list higher-order functions.**

Some examples are as follows:

* Given `RoseTree([],7)`; expect `7`
* Given `RoseTree([RoseTree([],3),RoseTree([],2)],5)`; expect: `10`

Hint: write a helper function, `listSum`, that given the list of children of a rose tree node, 
sums the entries of all trees rooted at the children.

Hint: you can use the code template for lists in order to implement `listSum`. 
Would it make sense that `listSum` calls `roseTreeSum` and to itself
recursively as well? If yes, would `listSum` and `roseTreeSum` be 
mutually recursive functions?

Some tests for this function are provided below. 
Do these tests stress all possible execution paths of the functions (helper and not helper)
that you wrote?

```java
void testExercise2() {
    // Tree0: single node
    // 7
    RoseTree tree0 = new RoseTree(new Nil<RoseTree>(), 7);
    testEqual(7, roseTreeSum(tree0), "roseSum for tree with no children");

    // Tree1:
    //    5
    //   / \
    //  3   2
    RoseTree child1 = new RoseTree(new Nil<RoseTree>(), 3);
    RoseTree child2 = new RoseTree(new Nil<RoseTree>(), 2);
    RoseTree tree1 = new RoseTree(MakeList(child1, child2), 5);
    testEqual(10, roseTreeSum(tree1), "roseSum for tree with children");

    // Tree2:
    //       1
    //      / \
    //     3   7
    //    / \   \
    //   4   6   8
    RoseTree grand1 = new RoseTree(new Nil<RoseTree>(), 4);
    RoseTree grand2 = new RoseTree(new Nil<RoseTree>(), 6);
    RoseTree child3 = new RoseTree(MakeList(grand1, grand2), 3);
    RoseTree grand3 = new RoseTree(new Nil<RoseTree>(), 8);
    RoseTree child4 = new RoseTree(MakeList(grand3), 7);
    RoseTree tree2 = new RoseTree(MakeList(child3, child4), 1);
    testEqual(29, roseTreeSum(tree2), "roseSum for tree where all children have children");

    // Tree3:
    //    2
    //   / \
    // 10   3
    //     / \
    //    4   6
    RoseTree child5 = new RoseTree(new Nil<RoseTree>(), 10);
    RoseTree tree3 = new RoseTree(MakeList(child5, child3), 2);
    testEqual(25, roseTreeSum(tree3), "roseSum for mixed-child tree");

    // Tree4:
    // 1
    // |
    // 2
    // |
    // 3
    // |
    // 4
    // |
    // 5
    RoseTree gc4 = new RoseTree(new Nil<RoseTree>(), 5);
    RoseTree gc3 = new RoseTree(MakeList(gc4), 4);
    RoseTree gc2 = new RoseTree(MakeList(gc3), 3);
    RoseTree gc1 = new RoseTree(MakeList(gc2), 2);
    RoseTree tree4 = new RoseTree(MakeList(gc1), 1);
    testEqual(15, roseTreeSum(tree4), "roseSum for depth-4 tree");

    // Tree5: root 10 with five children [1, 2, 3, 4, 5]
    //        10
    //   /  / | \  \
    //  1  2  3  4  5
    RoseTree c1 = new RoseTree(new Nil<RoseTree>(), 1);
    RoseTree c2 = new RoseTree(new Nil<RoseTree>(), 2);
    RoseTree c3 = new RoseTree(new Nil<RoseTree>(), 3);
    RoseTree c4 = new RoseTree(new Nil<RoseTree>(), 4);
    RoseTree c5 = new RoseTree(new Nil<RoseTree>(), 5);
    RoseTree tree5 = new RoseTree(MakeList(c1,c2,c3,c4,c5),10);
    testEqual(25, roseTreeSum(tree5), "roseSum for node with five children");
}
```


## Exercise 3 -  Count descendants of a person

*In a file `CountDescendants.java`*

In this, and the following exercises, we are going to work with the following 
data definitions:

```java
/**
 * Represents a person with basic information.
 * 
 * Examples:
 * - Person("Alice Smith", "female", 1950, 2020)
 * - Person("Bob Jones", "male", 1980, 0) [0 indicates still alive]
 * 
 * @param name   The full name of the person
 * @param gender The gender of the person ("male", "female", etc.)
 * @param birth  The year of birth
 * @param death  The year of death [0 if still alive]
 */
record Person(String name, String gender, int birth, int death) {}
/**
 * ... x.name() ... x.gender() ... x.birth() ... x.death() ...
 */

/**
 * A recursive data-type representing a person and all of their descendants.
 * 
 * @param person    The `Person` instance at this node
 * @param children  A `ConsList<Descendants>` representing all direct children
 * 
 * Examples:
 * - Descendants(Person("Alice", ...), []) [Alice with no children]
 * - Descendants(Person("Bob", ...), [Descendants(Person("John", ...),[]),Descendants(Person("Amanda", ...),[])]) [Bob with two children, John and Amanda]
 */
record Descendants(Person person, ConsList<Descendants> children) {}
/**
 * ... x.person() ... [ConsList function](x.children()) ...
 */
```

Spend some time familiarizing with these data definitions and their code templates.
What do these data definitions represent?

Design a function, `countDescendants`, that given a descendant tree of a person,
computes its total number of descendants. **You must solve this exercise without list higher-order functions.**

Some examples of this function are as follows:

* Given `Descendants(Person("Alice", ...), [])`; expect `0`
* Given `Descendants(Person("Bob", ...), [Descendants(Person("John", ...),[]),Descendants(Person("Amanda", ...),[])])`; expect `2`

Note that the root of the tree is not included in the output count, as a person is not a descendant of itself.

Some tests of this function are as follows:

```java
void testExercise3() {
    // Tree0: single person A with no children
    // A
    ConsList<Descendants> children0 = new Nil<Descendants>();
    Descendants tree0 = new Descendants(
        new Person("A", "x", 0, 0),
        children0
    );
    testEqual(0, countDescendants(tree0), "Tree0: A has 0 descendants");

    // Tree1: person A with three children B, C, D (no grandchildren)
    //     A
    //    /|\
    //   / | \
    //  B  C  D
    Descendants B = new Descendants(new Person("B", "x", 0, 0), new Nil<Descendants>());
    Descendants C = new Descendants(new Person("C", "x", 0, 0), new Nil<Descendants>());
    Descendants D = new Descendants(new Person("D", "x", 0, 0), new Nil<Descendants>());
    ConsList<Descendants> children1 = 
        new Cons<Descendants>(B,
        new Cons<Descendants>(C,
        new Cons<Descendants>(D, 
        new Nil<Descendants>())));
    Descendants tree1 = new Descendants(new Person("A", "x", 0, 0), children1);
    println(countDescendants(tree1));
    testEqual(3, countDescendants(tree1), "Tree1: A has 3 descendants");

    // Tree2: A with B (two children), C (no children), D1 (chain of grandchildren)
    //         A
    //        /|\
    //       / | \
    //      /  |  \
    //     B   C  D1
    //    / \      |
    //   B1 B2    D11
    //             |
    //            D111
    Descendants B1 = new Descendants(new Person("B1", "x", 0, 0), new Nil<Descendants>());
    Descendants B2 = new Descendants(new Person("B2", "x", 0, 0), new Nil<Descendants>());
    ConsList<Descendants> bChildren = new Cons<Descendants>(B1, new Cons<Descendants>(B2, new Nil<Descendants>()));
    Descendants B_root = new Descendants(new Person("B", "x", 0, 0), bChildren);

    Descendants C_root = new Descendants(new Person("C", "x", 0, 0), new Nil<Descendants>());

    Descendants D111 = new Descendants(new Person("D111", "x", 0, 0), new Nil<Descendants>());
    ConsList<Descendants> d11Children = new Cons<Descendants>(D111, new Nil<Descendants>());
    Descendants D11 = new Descendants(new Person("D11", "x", 0, 0), d11Children);

    ConsList<Descendants> d1Children = new Cons<Descendants>(D11, new Nil<Descendants>());
    Descendants D1 = new Descendants(new Person("D1", "x", 0, 0), d1Children);

    ConsList<Descendants> children2 = 
        new Cons<Descendants>(B_root,
        new Cons<Descendants>(C_root,
        new Cons<Descendants>(D1, 
        new Nil<Descendants>())));
    Descendants tree2 = new Descendants(new Person("A", "x", 0, 0), children2);
    testEqual(7, countDescendants(tree2), "Tree2: A has 7 descendants");
}
```

## Exercise 4 -  Count descendants of a person given its name

*In a file `CountDescendantsByPersonName.java`*


Design a function, `countDescendantsByPersonName`, that given a descendant tree, and the
name of a person (which may or may not be a descendant of the person at the root of the tree),
returns the number of descendants of such a a person. For simplicity, you can assume that there cannot be
two persons in the tree with the same name. **You must solve this exercise without list higher-order functions.**

Some examples of this function are as follows:
* Given: (1) a descendant tree rooted at `"Alice"`, and (2) `"Alice"`; Expect: total number of descendants of `"Alice"`
* Given: (1) a descendant tree rooted at `"Alice"` where a descendant of `"Alice"` is called `"Bob"`
             and `"Bob"` has three children, and no grand-children, and (2) `"Bob"`; Expect: `3` 
* Given: (1) a descendant tree rooted at `"Alice"` where no descendant of `"Alice"` is called `"Bob"` and
         (2) `"Bob"`; Expect: `0`

Some tests for this exercise are as follows:

```java
void testExercise4() {
    // Tree: A with B (two children), C (no children), D1 (chain of grandchildren)
    //         A
    //        /|\
    //       / | \
    //      /  |  \
    //     B   C  D1
    //    / \      |
    //   B1 B2    D11
    //             |
    //            D111 
    Descendants B1 = new Descendants(new Person("B1", "x", 0, 0), new Nil<Descendants>());
    Descendants B2 = new Descendants(new Person("B2", "x", 0, 0), new Nil<Descendants>());
    ConsList<Descendants> bChildren = new Cons<Descendants>(B1, new Cons<Descendants>(B2, new Nil<Descendants>()));
    Descendants B_root = new Descendants(new Person("B", "x", 0, 0), bChildren);

    Descendants C_root = new Descendants(new Person("C", "x", 0, 0), new Nil<Descendants>());

    Descendants D111 = new Descendants(new Person("D111", "x", 0, 0), new Nil<Descendants>());
    ConsList<Descendants> d11Children = new Cons<Descendants>(D111, new Nil<Descendants>());
    Descendants D11 = new Descendants(new Person("D11", "x", 0, 0), d11Children);

    ConsList<Descendants> d1Children = new Cons<Descendants>(D11, new Nil<Descendants>());
    Descendants D1 = new Descendants(new Person("D1", "x", 0, 0), d1Children);

    ConsList<Descendants> children2 = 
        new Cons<Descendants>(B_root,
        new Cons<Descendants>(C_root,
        new Cons<Descendants>(D1, 
        new Nil<Descendants>())));
    Descendants tree = new Descendants(new Person("A", "x", 0, 0), children2);

    testEqual(7, countDescendantsByPersonName(tree, "A"),   "A has 7 descendants");
    testEqual(2, countDescendantsByPersonName(tree, "B"),   "B has 2 descendants");
    testEqual(0, countDescendantsByPersonName(tree, "B1"),  "B1 has 0 descendants");
    testEqual(0, countDescendantsByPersonName(tree, "B2"),  "B2 has 0 descendants");
    testEqual(0, countDescendantsByPersonName(tree, "C"),   "C has 0 descendants");
    testEqual(2, countDescendantsByPersonName(tree, "D1"),  "D1 has 2 descendants");
    testEqual(1, countDescendantsByPersonName(tree, "D11"), "D11 has 1 descendant");
    testEqual(0, countDescendantsByPersonName(tree, "D111"),"D111 has 0 descendants");
    testEqual(0, countDescendantsByPersonName(tree, "Z"),  "Z not present returns 0");
}
```

## Exercise 5 -  Count people that satisfy a predicate without list higher-order functions

*In a file `CountPeopleByPredicate.java`*

Design a function, `countPeopleByPredicate`, that given a descendant tree, and a value of
function type `Predicate<Descendants>`, returns the number of people in the tree for which the 
predicate evaluates to true. **You must solve this exercise without list higher-order functions.**
Using `countPeopleByPredicate` write a call to the function that returns the
number of people in the tree who have exactly two children.

Some examples of this function are as follows:
 
* Given: a descendant tree with two females and Predicate: "Is female?"; expect `2`
* Given: a descendants tree in which no person was born before 1900, and Predicate: "Born before 1980?"; expect `0`


Some tests for this function are as follows:

```java
void testExercise5() {
    // Tree0:
    // A
    ConsList<Descendants> children0 = new Nil<Descendants>();
    Descendants tree0 = new Descendants(
        new Person("A", "x", 0, 0),
        children0
    );
    testEqual(1, countPeopleByPredicate(tree0, x -> true), "tree0: all nodes");
    testEqual(0, countPeopleByPredicate(tree0, x -> false), "tree0: no nodes");
    testEqual(1, countPeopleByPredicate(tree0, x -> x.person().name().equals("A")), "tree0: non-trivial predicate");

    // Tree1:
    //     A
    //    /|\
    //   B C D
    Descendants B = new Descendants(new Person("B", "x", 0, 0), new Nil<Descendants>());
    Descendants C = new Descendants(new Person("C", "x", 0, 0), new Nil<Descendants>());
    Descendants D = new Descendants(new Person("D", "x", 0, 0), new Nil<Descendants>());
    ConsList<Descendants> children1 =
        new Cons<Descendants>(B,
        new Cons<Descendants>(C,
        new Cons<Descendants>(D,
        new Nil<Descendants>())));
    Descendants tree1 = new Descendants(new Person("A", "x", 0, 0), children1);
    testEqual(4, countPeopleByPredicate(tree1, x -> true), "tree1: all nodes");
    testEqual(0, countPeopleByPredicate(tree1, x -> false), "tree1: no nodes");
    testEqual(1, countPeopleByPredicate(tree1, x -> x.person().name().equals("B")), "tree1: some nodes");

    // Tree2:
    //          A
    //         /|\
    //        / | \
    //       B  C  D1
    //      / \     |
    //     B1 B2   D11
    //              |
    //             D111
    Descendants B1 = new Descendants(new Person("B1", "x", 0, 0), new Nil<Descendants>());
    Descendants B2 = new Descendants(new Person("B2", "x", 0, 0), new Nil<Descendants>());
    ConsList<Descendants> bChildren = new Cons<Descendants>(B1, new Cons<Descendants>(B2, new Nil<Descendants>()));
    Descendants B_root = new Descendants(new Person("B", "x", 0, 0), bChildren);

    Descendants C_root = new Descendants(new Person("C", "x", 0, 0), new Nil<Descendants>());

    Descendants D111 = new Descendants(new Person("D111", "x", 0, 0), new Nil<Descendants>());
    ConsList<Descendants> d11Children = new Cons<Descendants>(D111, new Nil<Descendants>());
    Descendants D11 = new Descendants(new Person("D11", "x", 0, 0), d11Children);
    ConsList<Descendants> d1Children = new Cons<Descendants>(D11, new Nil<Descendants>());
    Descendants D1 = new Descendants(new Person("D1", "x", 0, 0), d1Children);

    ConsList<Descendants> children =
        new Cons<Descendants>(B_root,
        new Cons<Descendants>(C_root,
        new Cons<Descendants>(D1,
        new Nil<Descendants>())));
    Descendants tree2 = new Descendants(new Person("A", "x", 0, 0), children);
    testEqual(8, countPeopleByPredicate(tree2, x -> true), "tree2: all nodes");
    testEqual(0, countPeopleByPredicate(tree2, x -> false), "tree2: no nodes");
    testEqual(3, countPeopleByPredicate(tree2, x -> Equals(Length(x.person().name()),2)), "tree2: some nodes");
    testEqual(
        2,
        countPeopleByPredicate(tree2, x ->
            Equals(x.person().name(), "A") ||
            Equals(x.person().name(), "D11")),
        "tree2: root and grandchild only"
    );
}
```

## Exercise 6 -  Count people that satisfy a predicate using list higher-order functions

*In a file `CountPeopleByPredicateHigherOrder.java`*

Solve exercise 5 using list higher-order functions.

## Exercise 7 - Find the person who became a parent at the youngest age

*In a file `YoungestParent.java`*

Design a function, `youngestParent`, that given a descendant tree,
returns the `Person` in the tree who became a parent at the youngest
age among all parents in the tree. 

You can assume the current year is 2025 for calculating age. 
If multiple people became parents at the same age, return any one of them.
You can also assume that the root of the descendant tree has at least
one child.

Try to first implement the function without `Fold` and `Map`.
Once you are done, how would you implement this function using `Fold` and `Map`?

Hint: for this exercise, it might be helpful to think how to split the problem into
functions that solve helper subproblems? For example, given a list of children, which is the age of the youngest
children? What else might be helpful?

Some examples are as follows:

* Given: tree where `Person` Alice became a parent at 20, and `Person` Bob at 25; Expect: `Person` Alice
* Given: tree where `Person` Alice became a parent at 20, and `Person` Bob at 20; Expect: either `Person` Alice or `Person` Bob
* Given: tree with only one parent; Expect: the `Person` at the root of the tree

Some tests for this function are as follows:

```java
void testExercise7() {
    // Tree1:
    //     A
    //    /|\
    //   B C D
    Person person1A = new Person("A", "x", 1970, 0);
    Descendants B = new Descendants(new Person("B", "x", 1994, 0), new Nil<Descendants>());
    Descendants C = new Descendants(new Person("C", "x", 1995, 0), new Nil<Descendants>());
    Descendants D = new Descendants(new Person("D", "x", 1993, 0), new Nil<Descendants>());
    ConsList<Descendants> children1 =
        new Cons<Descendants>(B,
        new Cons<Descendants>(C,
        new Cons<Descendants>(D,
        new Nil<Descendants>())));
    Descendants tree1 = new Descendants(person1A, children1);
    testEqual(person1A, youngestParent(tree1), "Tree with children but no grandchildren");
    

    // Tree2:
    //          A
    //         /|\
    //        / | \
    //       B  C  D1
    //      / \     |
    //     B1 B2   D11
    //              |
    //             D111

    // -1 
    Descendants B1 = new Descendants(new Person("B1", "x", 1975, 0), new Nil<Descendants>());

    // -1
    Descendants B2 = new Descendants(new Person("B2", "x", 1977, 0), new Nil<Descendants>());
    ConsList<Descendants> bChildren = new Cons<Descendants>(B1, new Cons<Descendants>(B2, new Nil<Descendants>()));
    // 25
    Descendants B_root = new Descendants(new Person("B", "x", 1950, 0), bChildren);

    Descendants C_root = new Descendants(new Person("C", "x", 1952, 0), new Nil<Descendants>());

    // -1
    Descendants D111 = new Descendants(new Person("D111", "x", 1991, 0), new Nil<Descendants>());
    ConsList<Descendants> d11Children = new Cons<Descendants>(D111, new Nil<Descendants>());
    // 19
    Descendants D11 = new Descendants(new Person("D11", "x", 1972, 0), d11Children);
    ConsList<Descendants> d1Children = new Cons<Descendants>(D11, new Nil<Descendants>());
    // 18
    Person personD1 = new Person("D1", "x", 1954, 0);
    Descendants D1 = new Descendants(personD1, d1Children);

    ConsList<Descendants> children =
        new Cons<Descendants>(B_root,
        new Cons<Descendants>(C_root,
        new Cons<Descendants>(D1,
        new Nil<Descendants>())));
    // 20
    Descendants tree2 = new Descendants(new Person("A", "x", 1930, 0), children);

    testEqual(personD1, youngestParent(tree2), "Complex tree");
}
```

## Exercise 8 - Prune a tree using a predicate

*In a file `PruneByPredicate.java`*

Design a function, `pruneByPredicate`, that given a descendant tree, and a value of 
`Predicate<Person>`, returns a descendant tree (i.e., a value of type `Descendants`) where those people that satisfy a
predicate are removed from the input tree along with all its descendants.
If the root of the tree satisfies the predicate, it is not removed from the output
tree, only its descendants. You can use list higher-order functions if you find them
fit.

Some examples are as follows:

* Given: descendant tree, and Predicate: "is male"; Expect: a descendant tree where all males and their descendants are removed from the input tree.
* Given: descendant tree, and Predicate: "born before 1950"; Expect: a descendant tree where every person born before 1950 and all of their descendants
         are removed from the input tree.
* Given: a descendant where no one was born before 1950, and Predicate: "born before 1950"; Expect: the same tree as the input tree.         

```java
void testExercise8() {
       // ---------- Tree0 ----------
    // A
    ConsList<Descendants> children0 = new Nil<Descendants>();
    Descendants tree0 = new Descendants(new Person("A", "x", 0, 0), children0);

    // Predicate: false -> no change
    testEqual(tree0, pruneByPredicate(tree0, p -> false), "tree0: predicate false, unchanged");

    // Predicate: true  -> root kept (cannot prune root), no descendants anyway
    testEqual(tree0, pruneByPredicate(tree0, p -> true), "tree0: predicate true, root stays");

    // Predicate: matches root only -> still unchanged (root never pruned)
    testEqual(tree0, pruneByPredicate(tree0, p -> Equals(p.name(),"A")), "tree0: root match ignored");


    // ---------- Tree1 ----------
    //     A
    //    /|\
    //   B C D
    Descendants B = new Descendants(new Person("B", "x", 0, 0), new Nil<Descendants>());
    Descendants C = new Descendants(new Person("C", "x", 0, 0), new Nil<Descendants>());
    Descendants D = new Descendants(new Person("D", "x", 0, 0), new Nil<Descendants>());
    ConsList<Descendants> children1 =
        new Cons<Descendants>(B,
        new Cons<Descendants>(C,
        new Cons<Descendants>(D, new Nil<Descendants>())));
    Descendants tree1 = new Descendants(new Person("A", "x", 0, 0), children1);

    // Predicate: false -> unchanged
    testEqual(tree1, pruneByPredicate(tree1, p -> false), "tree1: predicate false, unchanged");

    // Predicate: true -> remove all children, keep root
    Descendants tree1AllGone = new Descendants(new Person("A", "x", 0, 0), new Nil<Descendants>());
    testEqual(tree1AllGone, pruneByPredicate(tree1, p -> true), "tree1: predicate true, children removed");

    // Predicate: only "B" -> remove B (no descendants anyway), keep C, D
    Descendants tree1NoB = new Descendants(
        new Person("A", "x", 0, 0),
        new Cons<Descendants>(C, new Cons<Descendants>(D, new Nil<Descendants>()))
    );
    testEqual(tree1NoB, pruneByPredicate(tree1, p -> Equals(p.name(),"B")), "tree1: prune B only");


    // ---------- Tree2 ----------
    //          A
    //         /|\
    //        B C D1
    //       / \   \
    //      B1 B2  D11
    //                \
    //                D111
    Descendants B1 = new Descendants(new Person("B1", "x", 0, 0), new Nil<Descendants>());
    Descendants B2 = new Descendants(new Person("B2", "x", 0, 0), new Nil<Descendants>());
    ConsList<Descendants> bChildren = new Cons<Descendants>(B1, new Cons<Descendants>(B2, new Nil<Descendants>()));
    Descendants B_root = new Descendants(new Person("B", "x", 0, 0), bChildren);

    Descendants C_root = new Descendants(new Person("C", "x", 0, 0), new Nil<Descendants>());

    Descendants D111 = new Descendants(new Person("D111", "x", 0, 0), new Nil<Descendants>());
    ConsList<Descendants> d11Children = new Cons<Descendants>(D111, new Nil<Descendants>());
    Descendants D11 = new Descendants(new Person("D11", "x", 0, 0), d11Children);
    ConsList<Descendants> d1Children = new Cons<Descendants>(D11, new Nil<Descendants>());
    Descendants D1 = new Descendants(new Person("D1", "x", 0, 0), d1Children);

    ConsList<Descendants> children2 =
        new Cons<Descendants>(B_root,
        new Cons<Descendants>(C_root,
        new Cons<Descendants>(D1, new Nil<Descendants>())));
    Descendants tree2 = new Descendants(new Person("A", "x", 0, 0), children2);

    // Predicate: false -> unchanged
    testEqual(tree2, pruneByPredicate(tree2, p -> false), "tree2: predicate false, unchanged");

    // Predicate: true -> all descendants gone, root kept
    Descendants tree2AllGone = new Descendants(new Person("A", "x", 0, 0), new Nil<Descendants>());
    testEqual(tree2AllGone, pruneByPredicate(tree2, p -> true), "tree2: predicate true, all descendants removed");

    // Predicate: Equals(Length(name),2). Prunes B1, B2, D1 (and its subtree). B and C stay.
    // Expected:
    //    A
    //   / \
    //  B   C
    Descendants B_noKids = new Descendants(new Person("B", "x", 0, 0), new Nil<Descendants>());
    Descendants tree2Len2 =
        new Descendants(new Person("A", "x", 0, 0),
            new Cons<Descendants>(B_noKids,
            new Cons<Descendants>(C_root, new Nil<Descendants>())));

    testEqual(
        tree2Len2,
        pruneByPredicate(tree2, p -> Equals(Length(p.name()),2)),
        "tree2: prune all names of length 2"
    );


    // Predicate: root or grandchild ("A" or "D11"):
    // - "A" ignored (root can't be pruned)
    // - "D11" pruned, so D11 and D111 are removed; keep B subtree, C, and D1 (now with no children)
    Descendants D1_noKids = new Descendants(new Person("D1", "x", 0, 0), new Nil<Descendants>());
    Descendants tree2PruneD11 = new Descendants(
        new Person("A", "x", 0, 0),
        new Cons<Descendants>(B_root, new Cons<Descendants>(C_root, new Cons<Descendants>(D1_noKids, new Nil<Descendants>())))
    );
    testEqual(
        tree2PruneD11,
        pruneByPredicate(tree2, p -> Equals(p.name(), "A") || Equals(p.name(), "D11")),
        "tree2: prune D11 (and its subtree), root kept even if matched"
    );
}
```


## Exercise 9 -  List of all leaf data values in a generic `RoseTree` - (distinction-level content)

*In a file `ExtractLeafNodesData.java`*

Generalize the data definition of `RoseTree` above such that it becomes a parameterized
data definition depending on a single type parameter `T`, where `T` is the type of the `value`
field of the record.

Write a generic function, `extractLeafNodesData`, that given a `RoseTree<T>` value, returns
a `ConsList<T>` with all the data values of the leaf nodes **ordered in decreasing order**.

Write comprehensive tests for such a function, with at least two different types for the 
type parameter, e.g., `Integer` and `String`.

You can use list higher-order list functions if you find them fit.

Hint: the `Sort` higher-order list function from the functional Java library might be helpful
to implement such a function. 