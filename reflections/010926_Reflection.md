# CSC360 — Session 07 Reflection
**Date:** 01 September 2026

## Topics Covered

- Drawing a triangle using three coordinates
- Drawing circles on a JavaFX `Canvas` using mouse input
- Connecting circles with arrows
- Redrawing a JavaFX `Canvas`
- Testing whether a point lies inside a circle
- Trees and tree terminology
- Binary trees
- Binary Search Trees (BST)
- Tree traversals

---

## Class Notes

### 1. Drawing a Triangle from Three Coordinates

A triangle is defined by three non-collinear points:

\[
A=(x_1,y_1), \quad B=(x_2,y_2), \quad C=(x_3,y_3)
\]

The triangle can be represented using a matrix:

\[
\begin{bmatrix}
x_1 & y_1 & 1\\
x_2 & y_2 & 1\\
x_3 & y_3 & 1
\end{bmatrix}
\]

To draw the triangle, connect:

- A → B
- B → C
- C → A

In JavaFX, `strokePolygon()` can draw the outline and `fillPolygon()` can fill the triangle.

The signed double area is:

\[
D=x_1(y_2-y_3)+x_2(y_3-y_1)+x_3(y_1-y_2)
\]

- `D = 0` → points are collinear
- `D ≠ 0` → valid triangle
- Area = \(\frac{|D|}{2}\)

For floating-point coordinates, a tolerance should be used instead of checking exactly for zero.

---

### 2. Drawing Circles Using Mouse Input

JavaFX `Canvas` provides a drawing surface through `GraphicsContext`.

A circle can be created when the user right-clicks:

```java
if (event.getButton() == MouseButton.SECONDARY)
The mouse position becomes the circle's center.
For example, with radius 25:
gc.fillOval(x - radius, y - radius,
            2 * radius, 2 * radius);
fillOval() and strokeOval() use the top-left corner of the bounding box, not the center.
Equal width and height produce a circle.
A circle can be stored as a model object:
record CircleNode(double centerX, double centerY, double radius) {}
Multiple circles can be stored using:
List<CircleNode> circles = new ArrayList<>();
Keeping the circles in a model makes it possible to redraw, move, test, and connect them later.
________________________________________
3. Connecting Circles with Arrows
An arrow consists of:
1.	Shaft
2.	Arrowhead
The arrow should connect to the boundaries of the circles, not their centers.
For two circle centers:
(x1,y1),(x2,y2)(x_1,y_1),\quad(x_2,y_2)
Calculate:
dx=x2−x1dx=x_2-x_1 dy=y2−y1dy=y_2-y_1 length=dx2+dy2length=\sqrt{dx^2+dy^2}
Unit direction:
ux=dxlength,uy=dylengthu_x=\frac{dx}{length},\qquad u_y=\frac{dy}{length}
If the circle radii are r1r_1 and r2r_2:
startX=x1+uxr1startX=x_1+u_xr_1 startY=y1+uyr1startY=y_1+u_yr_1 endX=x2−uxr2endX=x_2-u_xr_2 endY=y2−uyr2endY=y_2-u_yr_2
The centers must be different because length = 0 would cause division by zero.
For the arrowhead:
•	Math.atan2(dy, dx) gives the direction angle.
•	Arrow length can be set to 12.
•	Arrow angle can be set to 25°.
•	Math.cos() and Math.sin() calculate the two arrowhead points.
•	strokeLine() draws the shaft and arrowhead.
When two circles are selected, calculate their boundary points and call the arrow-drawing method.
________________________________________
4. Redrawing a JavaFX Canvas
JavaFX Canvas works in immediate mode. Drawing something does not automatically preserve it as a reusable graphical object.
Therefore, the application should store the model:
•	Circles
•	Arrows
•	Other required data
When something changes, redraw the complete canvas:
1.	Clear canvas
2.	Draw arrows
3.	Draw circles
Circles are drawn last so that they cover any overlapping part of the arrows.
This makes moving, selecting, and updating objects easier.
________________________________________
5. Checking Whether a Point Is Inside a Circle
For circle center:
(cx,cy)(c_x,c_y)
radius:
rr
and point:
(px,py)(p_x,p_y)
The point is inside or on the circle when:
(px−cx)2+(py−cy)2≤r2(p_x-c_x)^2+(p_y-c_y)^2\leq r^2
A containsPoint() method can implement this test.
There is no need to calculate the actual distance using sqrt(). Comparing squared distances is sufficient and avoids unnecessary computation.
This can also be used to select a circle when the user clicks on it.
If circles overlap, checking them in reverse drawing order allows the topmost circle to be selected first.
________________________________________
6. Trees
A tree is a hierarchical data structure made of nodes and edges.
Properties of a rooted tree:
•	One node is the root.
•	The root has no parent.
•	Every other node has exactly one parent.
•	A node can have zero or more children.
•	Trees contain no cycles.
•	There is exactly one path from the root to any node.
•	A tree with n nodes has n - 1 edges.
Term	Meaning
Root	Top node with no parent
Parent	Node directly above another node
Child	Node directly below another node
Sibling	Nodes with the same parent
Leaf	Node with no children
Internal node	Node with at least one child
Edge	Connection between two nodes
Path	Sequence of connected nodes
Depth	Distance from root to a node
Height	Longest downward path from a node
Subtree	A node together with its descendants
Applications include:
•	File systems
•	UI hierarchies
•	Organizational structures
•	Expression syntax
•	Search indexes
________________________________________
7. Binary Trees
A binary tree is a tree where each node has at most two children.
The children are specifically identified as:
•	Left child
•	Right child
A general binary tree does not necessarily provide efficient searching. The binary-tree structure only limits the number of children.
________________________________________
8. Binary Search Trees
A Binary Search Tree (BST) maintains an ordering:
•	Values in the left subtree are smaller.
•	Values in the right subtree are greater.
•	A consistent policy is required for duplicate values.
Search, insertion, and deletion depend on the tree height hh:
O(h)O(h)
For a balanced tree:
h≈log⁡nh\approx\log n
so operations can be:
O(log⁡n)O(\log n)
For an unbalanced tree:
h≈nh\approx n
so operations can become:
O(n)O(n)
________________________________________
9. Other Binary Tree Applications
Binary trees can also be used for:
•	Heaps
•	Expression trees
•	Syntax trees
•	Decision trees
•	Huffman coding
________________________________________
10. Tree Traversals
Common tree traversal methods:
Traversal	Order
Preorder	Root → Left → Right
Inorder	Left → Root → Right
Postorder	Left → Right → Root
Level order	Level by level
For a BST, inorder traversal produces the values in sorted order.
________________________________________
Class Reflection
This session connected the graphics concepts from earlier classes with more structured data and interaction. Drawing circles was not just about using Canvas; storing the circles as objects makes the graphics state reusable for operations such as selecting, connecting, and testing points.
The arrow calculation showed why geometric calculations are needed when drawing between graphical objects. Starting and ending an arrow at the circle boundaries gives a cleaner result than drawing directly between the centers.
The tree section introduced a different type of structure where relationships between objects are represented using nodes and edges. The distinction between a general binary tree and a Binary Search Tree was important because having two children does not automatically provide efficient searching; the ordering property of a BST is what enables efficient search when the tree is balanced.
________________________________________
Questions
•	How should overlapping circles and multiple possible connections be handled?
•	How can arrows be stored as model objects so they can also be moved or deleted?
•	How can a BST be kept balanced to maintain O(log⁡n)O(\log n) operations?
•	How are trees represented internally in Java?
________________________________________
What I Did on My Own
•	Practised the coordinate calculations for triangle and circle drawing.
•	Reviewed how circle boundaries are calculated for arrows.
•	Connected the point-in-circle test with mouse-based object selection.
•	Compared binary trees with Binary Search Trees.
•	Reviewed the four main tree traversal methods.
________________________________________
Key Takeaways
•	Three non-collinear coordinates define a triangle.
•	The signed double-area formula can determine whether three points are collinear.
•	JavaFX Canvas uses immediate-mode drawing, so graphical objects should be stored separately when they need to persist.
•	Circle drawing uses the top-left corner of the bounding box.
•	Arrows between circles should start and end at their boundaries.
•	Squared distance is enough for point-in-circle testing.
•	A tree has nodes, edges, a root, and hierarchical parent-child relationships.
•	A binary tree allows at most two children.
•	A BST adds an ordering rule to support efficient searching.
•	BST operations are O(h)O(h), where h is the tree height.
•	Inorder traversal of a BST produces sorted values.
________________________________________
Quick Revision
Triangle:
3 points → connect A-B, B-C, C-A
D = x₁(y₂-y₃) + x₂(y₃-y₁) + x₃(y₁-y₂)
D = 0 → collinear
Area = |D| / 2

Circle:
Canvas + mouse input
center = mouse position
fillOval(x-r, y-r, 2r, 2r)

Arrow:
center → boundary
dx = x₂-x₁
dy = y₂-y₁
length = √(dx²+dy²)
unit direction = (dx/length, dy/length)

Point in circle:
(px-cx)² + (py-cy)² ≤ r²

Tree:
root → parent → child
n nodes → n-1 edges
no cycles

Binary Tree:
≤ 2 children
left + right

BST:
left < root < right
search/insert/delete = O(h)
balanced → O(log n)
unbalanced → O(n)

Traversals:
Preorder  → Root-Left-Right
Inorder   → Left-Root-Right
Postorder → Left-Right-Root
Level     → level by level
