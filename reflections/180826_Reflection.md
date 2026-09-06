# Class Reflection (# 18 Aug 2026)

## Topics

- Abstraction of drawing a square
- Designing the arguments required by a `drawSquare()` function
- Position / starting coordinate
- `Vector2`
- X and Y coordinates
- Using the center of a square as its reference position
- Square side length
- `int` / `float`
- Function/API arguments
- Converting a center position and length into corner coordinates
- Top-left, top-right, bottom-left and bottom-right corners
- Drawing a square using four lines
- Pseudocode for geometric construction
- Basic Java project structure
- `.gitignore`
- `README.md`
- `src/main/java`
- `src/test/java`
- Separation of application code and test code

---

## 1. Drawing a Square as an Abstraction

The session began by looking at the problem of drawing a square at a higher level.

Instead of thinking directly about individual drawing commands, the square can be represented through a function:

```text
drawSquare(position, length)
```

The purpose of the abstraction is to give the function the information it needs and let the function perform the lower-level calculations and drawing operations internally.

The square therefore needs two main inputs:

```text
position
length
```

---

## 2. Required Arguments for `drawSquare()`

The required function/API call can be represented as:

```text
drawSquare(xPos, yPos, length)
```

The arguments describe:

```text
(xPos, yPos)
    ↓
starting/reference position

length
    ↓
side length of the square
```

The position represents the **center of the square** in this situation.

---

## 3. Position / Starting Coordinate

The position can be represented using a `Vector2`.

```text
Vector2
    ↓
x position
y position
```

The position represents the center of the square.

If:

```text
C = (Cx, Cy)
```

then:

```text
Cx → x-coordinate of the center
Cy → y-coordinate of the center
```

Using the center as the reference point means that the four corners can be calculated relative to this central position.

---

## 4. Square Length

The second required input is the side length.

```text
length
```

This can be represented using a numeric type such as:

```text
int
```

or:

```text
float
```

The length determines the size of the square.

For a square:

```text
width = length
height = length
```

All four sides therefore have the same length.

---

## 5. Function/API Representation

The complete abstraction can therefore be represented as:

```text
drawSquare(xPos, yPos, length)
```

or conceptually:

```text
drawSquare(Vector2 position, length)
```

The function receives the information describing the square rather than receiving all of the individual line coordinates directly.

The internal steps can then calculate the required coordinates.

---

# 6. Calculating the Corners

Once the center and side length are known, the positions of the four corners can be calculated.

Let:

```text
Center = (Cx, Cy)
Length = L
```

Since the center is being used as the reference point, half of the side length is used to determine the distance from the center to each side.

```text
L / 2
```

The four corners are calculated as follows.

### Top-left corner

```text
(Cx - L/2, Cy - L/2)
```

### Top-right corner

```text
(Cx + L/2, Cy - L/2)
```

### Bottom-left corner

```text
(Cx - L/2, Cy + L/2)
```

### Bottom-right corner

```text
(Cx + L/2, Cy + L/2)
```

---

## 7. Corner Diagram

```text
                  y
                  ↓

        Top-left          Top-right
          (TL)               (TR)
             ┌───────────────┐
             │               │
             │               │
             │       C       │
             │   (Cx, Cy)    │
             │               │
             │               │
             └───────────────┘
        Bottom-left       Bottom-right
          (BL)               (BR)
```

The center acts as the reference point.

The distance from the center to each side is:

```text
L / 2
```

Therefore:

```text
Left  → Cx - L/2
Right → Cx + L/2

Top    → Cy - L/2
Bottom → Cy + L/2
```

---

# 8. Why `length / 2` Is Used

The position given to the function represents the **center** of the square.

Therefore, the square extends equally in both directions from the center.

For the horizontal direction:

```text
left              center              right
  |----------------|--------------------|
       L / 2              L / 2
```

For the vertical direction:

```text
              top
               |
              L/2
               |
             center
               |
              L/2
               |
             bottom
```

This produces:

```text
left   = Cx - L/2
right  = Cx + L/2

top    = Cy - L/2
bottom = Cy + L/2
```

Using the same length in both directions preserves the square's geometry.

---

# 9. Drawing the Four Sides

Once all four corners are known, the square can be drawn using four lines.

The required operations can be represented as:

```text
drawLine(TopLeft, TopRight)

drawLine(TopLeft, BottomLeft)

drawLine(BottomRight, TopRight)

drawLine(BottomRight, BottomLeft)
```

Each operation connects two calculated corner positions.

The four lines form the boundary of the square.

---

## 10. Pseudocode

The complete abstract process can therefore be represented as:

```text
drawSquare(center, length)

    calculate TopLeft
    calculate TopRight
    calculate BottomLeft
    calculate BottomRight

    drawLine(TopLeft, TopRight)
    drawLine(TopLeft, BottomLeft)
    drawLine(BottomRight, TopRight)
    drawLine(BottomRight, BottomLeft)
```

The important separation is:

```text
Input
  ↓
Position + Length
  ↓
Calculate geometry
  ↓
Find four corners
  ↓
Draw four lines
  ↓
Square
```

---

# 11. Abstraction

The important idea is that the user of the function does not need to manually calculate all four corners every time a square needs to be drawn.

Instead:

```text
drawSquare(position, length)
```

provides a higher-level interface.

The function internally performs:

```text
position + length
        ↓
corner calculations
        ↓
line drawing
```

This separates **what we want to draw** from **how the drawing is constructed**.

---

# 12. From Shape to Drawing Operations

A square is a higher-level geometric object.

The graphics system can construct it from simpler operations.

```text
Square
  ↓
4 sides
  ↓
4 lines
  ↓
corner coordinates
  ↓
2D coordinates
```

Therefore, the square itself can be treated as an abstraction over the lower-level drawing operations.

---

# 13. Java Project Structure

The session also covered the basic structure expected for a Java programming project/repository.

A basic project can be organized as:

```text
MyJavaProject/
├── .gitignore
├── README.md
├── src/
│   ├── main/
│   │    └── java/
│   │       └── Main.java
│   └── test/
│       └── java/
│           └── MainTest.java
```

The structure separates different types of files and code.

---

# 14. `.gitignore`

```text
.gitignore
```

The `.gitignore` file tells Git which files or directories should not be tracked.

This is useful for generated files or files that should remain local to the development environment.

---

# 15. `README.md`

```text
README.md
```

The README provides documentation/information about the project.

It can contain information such as:

- what the project is
- how it is structured
- how to run it
- other useful project information

---

# 16. `src/`

The `src` directory contains the source code of the project.

It is divided into different areas:

```text
src/
├── main/
└── test/
```

The separation allows the actual application code and testing code to be kept distinct.

---

# 17. `src/main/java`

The main application source code is placed under:

```text
src/main/java/
```

Example:

```text
src/
└── main/
    └── java/
        └── Main.java
```

This is where the program's main Java source files belong.

---

# 18. `src/test/java`

Testing code is placed under:

```text
src/test/java/
```

Example:

```text
src/
└── test/
    └── java/
        └── MainTest.java
```

Basic distinction:

```text
src/main/java
→ application/program code

src/test/java
→ testing code
```

Keeping these separate makes the project structure clearer and allows the build system to treat application code and tests differently.

---

# 19. Project Structure — Quick View

```text
MyJavaProject/
│
├── .gitignore
│
├── README.md
│
└── src/
    │
    ├── main/
    │   └── java/
    │       └── Main.java
    │
    └── test/
        └── java/
            └── MainTest.java
```

General structure:

```text
Project
  ↓
src
  ↓
main / test
  ↓
java
  ↓
Java source files
```

---

# What I Learned

### Square abstraction

- A square can be represented using a position and a side length.
- The position can be represented using a `Vector2`.
- In this approach, the position represents the center of the square.
- The side length determines the size of the square.
- The four corner coordinates can be calculated from the center and half the side length.
- Once the corners are known, the square can be constructed from four lines.

### Geometry

```text
Center = (Cx, Cy)
Length = L

Top-left:
(Cx - L/2, Cy - L/2)

Top-right:
(Cx + L/2, Cy - L/2)

Bottom-left:
(Cx - L/2, Cy + L/2)

Bottom-right:
(Cx + L/2, Cy + L/2)
```

### Abstraction

- `drawSquare()` hides the lower-level calculations.
- The caller provides the information needed to define the square.
- The function performs the coordinate calculations and drawing operations internally.

### Java project structure

- `.gitignore` controls which files Git ignores.
- `README.md` provides project documentation.
- `src/main/java` contains application source code.
- `src/test/java` contains test source code.
- Separating `main` and `test` keeps application code and testing code organized.

---

# What I Did on My Own

- Followed the square project from the previous session and connected it to the higher-level idea of a `drawSquare()` abstraction.
- Worked with the project structure created for the course.
- Used the command line and Maven project structure from the previous session to continue working on the graphics project.
- Connected the geometric representation of a square to the actual drawing process.

---

# Questions / Things to Clarify

- How should `Vector2` be implemented internally?
- What is the exact relationship between a `Vector2` object and the individual `x` and `y` values?
- How does the `drawSquare()` abstraction eventually connect to the actual Java graphics API?
- How are the calculated corner coordinates passed to the line-drawing operation?
- How does the Java project structure connect to Java packages?
- How does Maven use the `src/main/java` and `src/test/java` structure during compilation and testing?

---

# Quick Revision

```text
drawSquare(position, length)
        ↓
position = center of square
length = side length
        ↓
calculate L/2
        ↓
calculate four corners
        ↓
draw four lines
        ↓
square
```

```text
Center = (Cx, Cy)
Length = L

TL = (Cx - L/2, Cy - L/2)
TR = (Cx + L/2, Cy - L/2)
BL = (Cx - L/2, Cy + L/2)
BR = (Cx + L/2, Cy + L/2)
```

```text
MyJavaProject/
├── .gitignore
├── README.md
└── src/
    ├── main/
    │   └── java/
    │       └── Main.java
    └── test/
        └── java/
            └── MainTest.java
```

```text
main
→ application code

test
→ test code
```