package trees;

public class Notes {

/*
 * order to ensure that you reach all the nodes in a tree, use one of the following methods:
 * pre order
 * post order
 * in order (only for binary trees)
 * level order
 * 
 * binary tree:
 * always has 2 children
 * left child < parent
 * right child > parent
 * keep track of root always
 * 
 * Binary Trees are efficient for sorting and searching
 * but the root has to be good.
 * a good root divides the tree in half evenly.
 * iterate through to sort and find middle
 * and it only works well in RAM, not hard disc file
 * on a disc, every level is a rotation of the disc and and fetch ..
 * 
 * do not need to keep tree balanced by filling a level before going on to the next
 * following a straight path up, children must be </> than all its ancestors
 * 
 * to insert:
 * keep track of root, current and parent
 * API provides ability to add RC and LC (right and left children)
 * 
 * node with no children is a leaf
 * 
 * these data structures a means to an end to store the data you need
 * 
 * 
 * to traverse and sort binary tree:
 * go all the way left till can't go any more
 * then take the data in the node
 * need to go all the way right
 * and on the way, need to go left till can't go any more
 * before you can work your way back and pick up data from each node
 * 
 * post order traverse
 * take data in left and right and their parent last
 * do left child [operation] right child
 * this is how the compiler stores arithmetic expressions
 * 
 * pre order traverse
 * 
 * see methods, recursion ... it was on the board
 * to traverse a node:
 * get left, get node, get right
 * 
 * removing a node from the tree:
 * easy- leaf
 * medium- 1 child
 * hard- 2 children
 * 
 * remove a node that has 2 children-
 * too complicated to remove this node
 * instead need to restructure this branch
 * go down left 1 level and then go all the way down left.
 * this is the greatest number that is less than the right child of this node.
 * if there is no right child of the left child, use the left child
 * 
 * then you have to remove the node you just took the data from.
 * it's definitely down the left side. and it's definitely a leaf/only has 1 child.
 */
}
