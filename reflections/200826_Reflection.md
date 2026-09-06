# Session 04

**Date:** 20 August 2026  
**Course:** CSC360 Graphics design and image processing 
**Topic:** Java Swing, Inheritance, `@Override`, `super.paintComponent()`, Project Structure, and Triangle Rasterization

---

## Class Notes

### 1. GitHub Workflow & Documentation Structure

The session included a review of the GitHub workflow used for the project. The repository should remain synchronized with the remote repository using Git commands such as `git status`, `git add`, `git commit`, and `git push origin main`.

The purpose of `git push origin main` is to send the local commits on the `main` branch to the remote `main` branch. SSH authentication allows Git to communicate with GitHub without repeatedly entering credentials.

The project documentation was also standardized. Session documentation should follow a consistent structure and naming system so that files can be located and reviewed easily.

### Git workflow

```text
Working Directory
       |
       v
   git status
       |
       v
     git add
       |
       v
   Staging Area
       |
       v
   git commit
       |
       v
 Local Repository
       |
       v
git push origin main
       |
       v
 Remote Repository
Important commands:
Command	Purpose
git status	Shows the current state of the working tree and staging area
git add .	Stages the current changes for the next commit
git commit -m "message"	Creates a commit containing the staged changes
git push origin main	Pushes local main commits to the remote main branch
________________________________________
2. Markdown Documentation Structure
The reflection/documentation system should be treated as a structured technical record rather than a normal written essay.
A useful document structure is:
Session Document
│
├── Date / Session Information
│
├── Class Notes
│   ├── Topic 1
│   ├── Topic 2
│   ├── Topic 3
│   └── ...
│
└── Class Reflection
    ├── Topics Covered
    ├── Key Takeaways
    ├── Questions
    └── What I Did on My Own
Markdown provides different elements for organizing information:
•	# → main document title
•	## → major section
•	### → subsection
•	- → unordered information
•	1. → ordered steps
•	backticks → inline code
•	fenced code blocks → actual code or directory structures
•	tables → comparisons and structured information
•	Mermaid diagrams → workflows and relationships
The important point is that formatting should communicate the structure of the information rather than being used only for appearance.
________________________________________
3. Java Classes, Subclasses and Inheritance
Java supports inheritance through the extends keyword.
Example:
public class App extends JPanel
Here:
•	App is the subclass.
•	JPanel is the superclass.
•	extends establishes the inheritance relationship.
The subclass can use functionality provided by its superclass while also defining its own behavior.
Inheritance can therefore be represented as:
JComponent
    |
    v
JPanel
    |
    v
App
App receives the functionality of JPanel through inheritance and can specialize that behavior by overriding methods.
________________________________________
4. The @Override Annotation
@Override is placed above a method when a subclass is replacing or providing its own implementation of a method inherited from a superclass.
Example:
@Override
protected void paintComponent(Graphics g) {
    ...
}
The annotation tells the compiler that the method is intended to override a method from the parent class.
This is useful because the compiler can detect mistakes in the method signature. For example, if the method name or parameters do not match the inherited method, the compiler can report the problem instead of allowing an incorrect method to silently behave as a separate method.
Therefore:
Superclass method
       |
       v
Subclass method with @Override
       |
       v
Compiler checks that the override is valid
________________________________________
5. The super Keyword
The super keyword refers to the immediate superclass of the current class.
In the Swing project:
super.paintComponent(g);
calls the superclass implementation of paintComponent.
The relationship can be visualized as:
JPanel
  |
  | provides paintComponent(Graphics g)
  |
  v
App
  |
  | overrides paintComponent(Graphics g)
  |
  +--> super.paintComponent(g)
          |
          v
     JPanel implementation
The distinction between paintComponent(g) and super.paintComponent(g) is important:
•	paintComponent(g) refers to the method being implemented/overridden in the current class.
•	super.paintComponent(g) explicitly calls the implementation belonging to the parent class.
________________________________________
6. Swing paintComponent(Graphics g) Execution
Custom drawing in a Swing component is performed through paintComponent(Graphics g).
The method receives a Graphics object that provides the drawing operations used to render onto the component.
Basic execution flow:
Swing repaint
     |
     v
paintComponent(Graphics g)
     |
     v
super.paintComponent(g)
     |
     v
Set drawing properties
     |
     v
Draw shape
The Graphics object acts as the interface through which drawing operations are performed.
For example:
g.setColor(Color.BLUE);
g.fillRect(x, y, width, height);
The first operation selects the drawing color and the second operation draws the rectangle.
________________________________________
7. Why super.paintComponent(g) is Called
The superclass implementation should be called before performing custom drawing:
super.paintComponent(g);
This allows Swing's normal component painting behavior to occur before the custom graphics are drawn.
The basic sequence is:
paintComponent(g)
       |
       v
super.paintComponent(g)
       |
       v
Clear / prepare component background
       |
       v
Custom drawing
This is particularly important because Swing components may be repainted multiple times. The custom drawing should therefore happen on the properly prepared component rather than simply accumulating previous drawings.
________________________________________
8. Basic Graphics Operations
The Graphics object provides primitive drawing operations.
Two operations discussed were:
g.setColor(Color.BLUE);
and
g.fillRect(...);
Their roles are different:
Operation	Purpose
g.setColor(...)	Sets the color used by subsequent drawing operations
g.fillRect(...)	Draws a filled rectangular region
For example:
g.setColor(Color.BLUE);
g.fillRect(100, 100, 200, 200);
The first statement changes the current drawing color. The second uses that color to rasterize a rectangular region.
________________________________________
9. Primitive Drawing and Rasterization
Computer graphics ultimately has to represent shapes using pixels.
For a primitive such as a triangle, the general process is:
Geometric description
        |
        v
Determine region containing shape
        |
        v
Evaluate individual pixels
        |
        v
Determine whether pixel belongs to shape
        |
        v
Assign pixel color
A triangle can be represented using three vertices:
V1 = (x1, y1)
V2 = (x2, y2)
V3 = (x3, y3)
From these vertices, the triangle's boundaries and the region containing the triangle can be determined.
________________________________________
10. Triangle Bounding Box
The first step of the triangle filling process is finding its bounding box.
Given:
V1 = (x1, y1)
V2 = (x2, y2)
V3 = (x3, y3)
the minimum and maximum coordinates are determined:
xmin = min(x1, x2, x3)
xmax = max(x1, x2, x3)

ymin = min(y1, y2, y3)
ymax = max(y1, y2, y3)
This gives the rectangular region:
[xmin, xmax] × [ymin, ymax]
Only pixels inside this bounding box need to be tested.
________________________________________
11. Edge Functions
Each edge of the triangle can be represented by an edge function.
For an edge from vertex Vi to vertex Vj:
Eij(x,y) =
(x - xi)(yj - yi)
-
(y - yi)(xj - xi)
For a point (x,y), the sign of the edge function determines which side of the directed edge the point lies on.
Eij(x,y) > 0  → one side of the edge
Eij(x,y) < 0  → opposite side
Eij(x,y) = 0  → point lies on the edge
The same test can be applied to all three edges.
________________________________________
12. Triangle Boundary Test
For each pixel (x,y) inside the bounding box, evaluate the three edge functions:
E12(x,y)
E23(x,y)
E31(x,y)
If the chosen orientation gives:
E12(x,y) >= 0
E23(x,y) >= 0
E31(x,y) >= 0
then the pixel lies inside the triangle or directly on its boundary and can be filled.
The complete process is:
Input V1, V2, V3
       |
       v
Calculate bounding box
       |
       v
Iterate through pixels
       |
       v
Evaluate E12, E23, E31
       |
       v
Are all edge tests satisfied?
      / \
    Yes  No
     |    |
     v    v
Fill    Ignore
pixel   pixel
________________________________________
Class Reflection
Topics Covered
•	GitHub repository synchronization and Git workflow
•	Markdown documentation structure and formatting
•	Java classes, subclasses and inheritance
•	extends keyword
•	@Override annotation
•	super keyword
•	Swing paintComponent(Graphics g)
•	super.paintComponent(g)
•	Graphics drawing operations
•	Primitive shape rasterization
•	Triangle bounding-box calculation
•	Edge functions
•	Triangle boundary/pixel-fill algorithm
________________________________________
Key Takeaways
•	Git workflow: git add stages changes, git commit records them locally, and git push origin main synchronizes the local repository with GitHub.
•	Documentation structure: A technical reflection should be organized so that topics, explanations, diagrams, and takeaways can be located and reviewed quickly.
•	Inheritance: extends creates an inheritance relationship where a subclass can reuse and specialize functionality from its superclass.
•	@Override: The annotation indicates that a subclass method is intended to replace a superclass method and allows the compiler to verify the method signature.
•	super: super refers to the immediate parent class and can be used to call the parent's implementation of an overridden method.
•	Swing painting: paintComponent(Graphics g) is the method used for custom component rendering, with the Graphics object providing drawing operations.
•	super.paintComponent(g): Calling the superclass implementation before custom drawing allows the normal Swing painting process to prepare the component before the new graphics are rendered.
•	Rasterization: A geometric shape must ultimately be converted into pixels, requiring a method to determine which pixels belong to the shape.
•	Bounding box: Restricting triangle testing to [xmin,xmax] × [ymin,ymax] avoids checking pixels outside the region containing the triangle.
•	Edge functions: The sign of an edge function determines which side of a directed edge a pixel lies on.
•	Triangle fill: A pixel is filled when it satisfies the boundary tests for all three triangle edges.
________________________________________
Questions
1.	How exactly does Swing decide when paintComponent(Graphics g) needs to be called again?
2.	How does Swing's repainting process interact with its event-dispatch mechanism?
3.	How is the orientation of the triangle related to choosing >= 0 versus <= 0 for the edge-function test?
4.	How does the triangle rasterization process handle pixels exactly on an edge?
5.	What is the relationship between the Graphics object used by Swing and the actual pixel buffer displayed on the screen?
________________________________________
What I Did on My Own
•	Reviewed the Git workflow used throughout the project and the purpose of each command.
•	Connected the extends, @Override, and super concepts to the existing Swing project instead of treating them as isolated Java syntax.
•	Traced the execution sequence from Swing repainting to paintComponent(g) and then to the custom drawing operations.
•	Studied the triangle bounding-box and edge-function approach as the underlying logic for deciding which pixels belong to a triangle.
•	Used Markdown structure, code blocks, tables, and diagrams to document technical concepts in a way that can be used for later revision.
________________________________________
Quick Revision
extends
  → establishes inheritance

@Override
  → tells compiler that a method is overriding a parent method

super
  → refers to the immediate superclass

paintComponent(Graphics g)
  → Swing method used for custom component painting

super.paintComponent(g)
  → invokes the parent's painting implementation

Graphics
  → provides drawing operations

Triangle rasterization
  → Bounding Box
  → Edge Functions
  → Pixel Boundary Test
  → Fill qualifying pixels
