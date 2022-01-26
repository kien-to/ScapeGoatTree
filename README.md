# ScapeGoatTree

Just obeying the BST rule is not sufficient to achieve O(log n) performance when accessing a binary search tree. The tree must be balanced, or close to it. But,  implementation of balance was likely at least O(n).

Therefore it does not make sense to call balance before or after each method, as it will make all methods asymptotically slower than O(log n), negating the performance advantage that motivated binary search trees in the first place!
The solution to this paradox is to build a self-balancing binary tree, that is, a tree that maintains some invariant across calls to add or remove. This invariant enforces that the tree remains approximately balanced, and does so at an amortized low cost. 

The scapegoat tree is based on the common wisdom that, when something goes wrong, the first thing people tend to do is find someone to blame (the scapegoat). Once blame is firmly established, we can leave the scapegoat to fix the problem.
A ScapegoatTree keeps itself approximately balanced by partial rebuilding operations. During a partial rebuilding operation, an entire subtree is deconstructed and rebuilt into a perfectly balanced subtree.

A ScapegoatTree is a binary search tree that, in addition to keeping track of the number of nodes in the tree (its size), it also keeps a counter, upperBound, that maintains an upper-bound on the number of nodes.

Suppose n = size and q = upperBound. Then, after each add or remove, we require that the tree obey a form of the following inequalities, known together as the scapegoat rule. First, that:
q/2 ≤ n ≤ q
In addition, a ScapegoatTree has logarithmic height (recall that logx(y) = logz(y) / logz(x) for
any real x, y, z); at all times, the height of the scapegoat tree does not exceed:

height ≤ log3/2(q) ≤ log3/2(2n) < log3/2(n) + 2

By obeying the scapegoat rule, a scapegoat tree will retain asymptotically logarithmic (amortized) performance on the relevant operations: add, remove, etc. 
