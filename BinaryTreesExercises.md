# Binary Tree Exercises

In this workshop, we will start working with another family of arbitrarily large data definitions: **binary trees**. 
As lists, binary trees are also self-referential (**recursive**) data definitions. That is, the data definition of
lists and binary trees refer to themselves. 
In general, trees are used to represent hierarchical relationships among different pieces of information. 

A binary tree can be defined as a hierarchical collection of nodes. There is an special node called the root node, at the top
of the hierarchy, which does not have a parent. Every other node in the hierarchy has one and only one parent. In a binary tree, each node has either one or two children nodes, or none.  If a node does not have children,
it is called a **leaf node**. Nodes with children are called **inner nodes**. 

The nodes in a binary tree may also carry values, e.g., a programmer-defined record which represents a person. Which kind of nodes carry data values highly depends on the application problem at hand. For example, one may have binary trees in which all nodes carry data values, or binary trees in which only the inner or the leaf nodes carry data values. 

For this reason, among others, **the functional Java library does NOT provide a data definition for binary trees.** You will have to come up with the most suitable data definition in your program depending on the application problem requirements.

In the exercises in this set, we are going to work with the data definition for binary trees below (again, this is not the only possible data definition for binary trees). Spend some time to familiarize with it, and try to answer to the following questions:

* Which is the type used to represent the binary tree?
* And the ones used to present the inner and leaf nodes?
* Do the leaf nodes carry data values in such data definition?
* What about the inner nodes?
* Which is the type of the data values carried by inner nodes (if it applies)?
* Which is the type of the data values carried by leaf nodes (if it applies)?
* Is this a generic (a.k.a.) parameterized data definition?

```java
/**
 * A recursive data-type representing a binary tree.
 * 
 * A `BinaryTree` may be either:
 * - a `Leaf`, representing an empty tree
 * - or a `Node`, containing a value and two subtrees (left and right).
 * 
 * Examples:
 * - Leaf() [an empty tree]
 * - Node(Leaf(), 5, Leaf()) [a single node with value 5]
 * - Node(Node(Leaf(), 1, Leaf()), 3, Node(Leaf(), 4, Leaf())) [root 3, left child 1, right child 4]
 *
 */
sealed interface BinaryTree permits Leaf, Node {}
/**
 * ... switch(x) {
 *      case Leaf() -> ... 
 *      case Node(var l, var v, var r) -> 
 *          ...[left recursive call] ... [right recursive call] ...)
 * } ... ;
 */

/**
 * Represents an empty subtree in a `BinaryTree`.
 * 
 * Used as the base case in recursive tree operations.
 * 
 * Example:
 * - Leaf() [an empty binary tree]
 */
record Leaf() implements BinaryTree {}

/**
 * Represents a non-empty subtree in a `BinaryTree`, containing a value and two subtrees.
 * 
 * Example:
 * - Node(Leaf(), 7, Leaf()) [a single-node tree with value 7]
 * 
 * @param left  The left subtree (may be Leaf or Node)
 * @param value The value stored at this node
 * @param right The right subtree (may be Leaf or Node)
 */
record Node(BinaryTree left, int value, BinaryTree right) implements BinaryTree {}
```

## Exercise 1 - Compute the number of non-leaf nodes in a binary tree

*In a file `NumNonLeafNodes.java`*

Write a recursive function, `numNonLeafNodes`, that given a `BinaryTree`, returns the number
of non-leaf nodes in the tree.

Some examples of this function are as follows:
* Given: `Leaf()`; expect: `0`
* Given: `Node(Leaf(), 5, Leaf())`; expect: `1`
* Given: `Node(Node(Leaf(), 1, Leaf()), 3, Node(Leaf(), 4, Leaf()))`; expect: `3`
 
For the tests of this exercise (and for those of the functions below), 
we will use the following binary trees:

```java
// For diagrams denoting binary trees, `x` will indicate a leaf.

// x
BinaryTree leaf = new Leaf();

//   1
//  / \
// x   x
BinaryTree basicTree1 = new Node(leaf, 1, leaf);

//   5
//  / \
// x   x
BinaryTree basicTree2 = new Node(leaf, 5, leaf);

//     4
//    / \
//   5   x
//  / \
// x   x
BinaryTree mediumTree1 = new Node(basicTree2, 4, leaf);

//      2
//     / \
//    /   \
//   1     1
//  / \   / \
// x   x x   x
BinaryTree mediumTree2 = new Node(basicTree1, 2, basicTree1);

//
//               3
//              / \
//             /   \
//            /     \
//           /       \
//          /         \
//         /           \
//        2             4
//       / \           / \
//      /   \         /   \ 
//     1     1       5     x
//    / \   / \     / \ 
//   x   x x   x   x   x
BinaryTree complexTree = new Node(mediumTree2, 3, mediumTree1);
```
 
Some tests for this function are as follows:

```java
void testExercise1() {
    testEqual(0, numNonLeafNodes(leaf), "numNonLeafNodes() on leaf");
    testEqual(1, numNonLeafNodes(basicTree1), "numNonLeafNodes() on basicTree1");
    testEqual(1, numNonLeafNodes(basicTree2), "numNonLeafNodes() on basicTree2");
    testEqual(2, numNonLeafNodes(mediumTree1), "numNonLeafNodes() on mediumTree1");
    testEqual(3, numNonLeafNodes(mediumTree2), "numNonLeafNodes() on mediumTree2");
    testEqual(6, numNonLeafNodes(complexTree), "numNonLeafNodes() on complexTree");
}
```

**How would you modify the function such that it also counts the leaf nodes?**

## Exercise 2 - Compute the depth of a binary tree

*In a file `MaxDepth.java`*

Design a recursive function `maxDepth` that returns the maximum depth of a binary tree. 
The maximum depth of a binary tree is the length of the longest path from the root `Node` to any `Leaf` node. 
The length of a path is the number of nodes in such path minus one.
A tree consisting of just a `Leaf` has a depth of `0`.

Some examples of this function are as follows:
* Given: `Leaf()`; expect: `0`
* Given: `Node(Leaf(), 5, Leaf())`; expect: `1`
* Given: `Node(Node(Leaf(), 1, Leaf()), 3, Node(Leaf(), 4, Leaf()))`; expect: `2`
* Given: `Node(Node(Node(Leaf(), -2, Leaf()), 3, Leaf()), 3, Leaf())`; expect: `3`

Some tests for this function are as follows:

```java
void testExercise2() {
    testEqual(0, maxDepth(leaf), "maxDepth() on leaf");
    testEqual(1, maxDepth(basicTree1), "maxDepth() on basicTree1");
    testEqual(1, maxDepth(basicTree2), "maxDepth() on basicTree2");
    testEqual(2, maxDepth(mediumTree1), "maxDepth() on mediumTree1");
    testEqual(2, maxDepth(mediumTree2), "maxDepth() on mediumTree2");
    testEqual(3, maxDepth(complexTree), "maxDepth() on complexTree");
}
```

## Exercise 3 - Determine if a value is present in a binary tree

*In a file `IsPresent.java`*

Design the `isPresent` recursive function that given a binary tree and an integer value,
determines whether such value exists in any `Node` of the provided binary tree.
The function returns `true` if the value is found and `false` otherwise.

Some examples are as follows:
* Given `Leaf()` and any integer value; expect: `false`
* Given `Node(Node(Leaf(), 1, Leaf()), 3, Node(Leaf(), 4, Leaf()))` and `1`; expect `true`
* Given `Node(Node(Leaf(), 1, Leaf()), 3, Node(Leaf(), 4, Leaf()))` and `0`; expect `false`

Some tests for this function are as follows:

```java
void testExercise3() {
    testEqual(false, isPresent(leaf, 1), "leaf does not contain 1");

    testEqual(true, isPresent(basicTree1, 1), "basicTree1 contains 1");
    testEqual(false, isPresent(basicTree1, 2), "basicTree1 does not contain 2");

    testEqual(true, isPresent(basicTree2, 5), "basicTree2 contains 5");
    testEqual(false, isPresent(basicTree2, 3), "basicTree2 does not contain 3");

    testEqual(true, isPresent(mediumTree1, 4), "mediumTree1 contains 4");
    testEqual(true, isPresent(mediumTree1, 5), "mediumTree1 contains 5");
    testEqual(false, isPresent(mediumTree1, 1), "mediumTree1 does not contain 1");

    testEqual(true, isPresent(mediumTree2, 2), "mediumTree2 contains 2");
    testEqual(true, isPresent(mediumTree2, 1), "mediumTree2 contains 1");
    testEqual(false, isPresent(mediumTree2, 5), "mediumTree2 does not contain 5");

    testEqual(true, isPresent(complexTree, 3), "complexTree contains 3");
    testEqual(true, isPresent(complexTree, 5), "complexTree contains 5");
    testEqual(true, isPresent(complexTree, 1), "complexTree contains 1");
    testEqual(false, isPresent(complexTree, 99), "complexTree does not contain 99");
}
```

## Exercise 4 - Flatten a binary tree

*In a file `Flatten.java`*

Write a recursive function, named `flatten`, that given a binary tree, returns a `ConsList<Integer>`
with all the values stored in the inner nodes of the tree. The order of the values in the list
should correspond to an in-order traversal of the tree. For a given inner node of the tree,
an in-order traversal can be recursively defined as follows: (1) visit left subtree; 
(2) visit value of the node; (3) visit right subtree.

Some examples are as follows:
* Given `Leaf()`; expect: []
* Given `Node(Node(Leaf(), 1, Leaf()), 3, Node(Leaf(), 4, Leaf()))`; expect [1,3,4]
* Given: `Node(Node(Node(Leaf(), -2, Leaf()), 3, Leaf()), 4, Leaf())`; expect: [-2,3,4]


Some tests for `flatten` are as follows:

```java
void testExercise4() {
    testEqual(new Nil<Integer>(), flatten(leaf), "leaf flattens to []");

    testEqual(new Cons<Integer>(1, new Nil<Integer>()), flatten(basicTree1), "basicTree1 flattens to [1]");
    testEqual(new Cons<Integer>(5, new Nil<Integer>()), flatten(basicTree2), "basicTree2 flattens to [5]");

    testEqual(
        new Cons<Integer>(5, new Cons<Integer>(4, new Nil<Integer>())),
        flatten(mediumTree1),
        "mediumTree1 flattens to [5, 4]"
    );

    testEqual(
        new Cons<Integer>(1, new Cons<Integer>(2, new Cons<Integer>(1, new Nil<Integer>()))),
        flatten(mediumTree2),
        "mediumTree2 flattens to [1, 2, 1]"
    );

    testEqual(
        new Cons<Integer>(1, new Cons<Integer>(2, new Cons<Integer>(1, new Cons<Integer>(3, new Cons<Integer>(5, new Cons<Integer>(4, new Nil<Integer>())))))),
        flatten(complexTree),
        "complexTree flattens to [1, 2, 1, 3, 5, 4]"
    );
}
```

## Exercise 5 - Square a binary tree 

*In a file `Square.java`*

Write a recursive function, named `square`, that given a binary tree, returns a binary tree
with the same structure as the input tree, but with the data values within the inner
nodes squared.

Some examples are as follows:
* Given `Leaf()`; expect: `Leaf()`
* Given `Node(Node(Leaf(), 1, Leaf()), 3, Node(Leaf(), 4, Leaf()))`; expect `Node(Node(Leaf(), 1, Leaf()), 9, Node(Leaf(), 16, Leaf()))`
* Given: `Node(Node(Node(Leaf(), -2, Leaf()), -1, Leaf()), 3, Leaf())`; expect: `Node(Node(Node(Leaf(), 4, Leaf()), 1, Leaf()), 9, Leaf())`

Write a set of comprehensive tests for `square`.

## Exercise 6 - ZigZag path in a binary tree

*In a file `ZigZag.java`*

Write a recursive function, `zigZag`, that given a binary tree, returns a list that contains those
elements of the tree that belong to a "Zig-Zag" path in the tree. A "Zig-Zag" 
path starts at the root node of the tree, then visits the left child of 
of the root, followed by the right child of the left child of the root, and so on, 
till a leaf is reached, i.e., for each new step, the path visits the 
opposite child from the one visited in the previous step.

Write a set of comprehensive tests for `zigZag`.


## Exercise 7 - Generic Binary Tree and Filter (distinction-level content)

*In a file `TreeFilter.java`*

Design a generic binary tree data type `Tree<T>` with one type parameter (`T`).
Each node (inner and leaf) of the tree stores a value of type `T`.

Implement the following function and write comprehensive 
tests for such a function:

```java
/**
 * A filter function for your binary tree.
 * The predicate evaluates the value of each inner node. If the predicate
 * returns true, then the inner node stays an inner node. If the predicate
 * returns false, then the inner node is turned into a leaf containing
 * the same value, and its children are removed from the tree.
 * For example, for the tree:
 *               4
 *              / \
 *             3   6
 *            / \  | \
 *           1  2  3  4
 * and the predicate: x -> x % 2 == 0
 * The result should be:
 *             4
 *            / \
 *           3   6
 *               | \
 *               3  4
 */
<T> Tree<T> treeFilter(Predicate<T> predicate, Tree<T> tree);
```






