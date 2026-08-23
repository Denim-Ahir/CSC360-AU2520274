# Class Reflection (# 07 Aug 2026)

## Topics

- Working from the command line using Windows PowerShell
- Setting up the Java development environment
- Installing and verifying Java
- Installing and verifying Maven
- Git and GitHub
- SSH authentication for GitHub
- Cloning the course repository
- Navigating to the project root
- Maven project structure
- `pom.xml`
- `src/main` and `src/test`
- Maven build workflow
- Compiling and testing Java code
- `target/` and generated build files
- Running Java classes from PowerShell
- Basic computer graphics workflow
- Coordinates and 2D drawing
- Drawing a square using Java graphics

## Notes

### 1. Starting from the Command Line

The session started from the command line rather than directly working through a graphical interface.

Windows PowerShell was used as the main command-line environment.

Basic workflow:

```text
PowerShell
    ↓
run commands
    ↓
install/check software
    ↓
navigate through directories
    ↓
run Git commands
    ↓
run Maven commands
    ↓
compile and run Java
```

The command line allows the development environment and project workflow to be controlled directly through commands.

---

### 2. Java

Java is required because the course project is written in Java.

Java was installed and then verified from PowerShell.

```powershell
java -version
```

This checks whether Java is available through the command line and displays the installed Java version.

The Java compiler can also be checked using:

```powershell
javac -version
```

Important distinction:

```text
java
→ runs Java programs

javac
→ compiles Java source code
```

Java source code uses:

```text
.java
```

The Java compiler converts source code into:

```text
.class
```

bytecode.

The JVM then executes the bytecode.

Basic pipeline:

```text
.java
  ↓
Java compiler
  ↓
.class
  ↓
JVM
  ↓
program execution
```

---

### 3. Maven

Maven is a build and project management tool used for Java projects.

Instead of manually handling every step of compiling, testing, and packaging the project, Maven provides a standard project structure and commands for these tasks.

Maven uses:

```text
pom.xml
```

as the main project configuration file.

Maven was installed and verified from PowerShell.

```powershell
mvn -version
```

This checks that Maven is available and displays information about the Maven installation and Java environment being used.

---

### 4. Git

Git is used for version control.

It keeps track of changes made to the project and allows different versions of the project to be stored.

Git was checked from the command line using:

```powershell
git --version
```

Basic relationship:

```text
Working directory
        ↓
Git
        ↓
Local repository
        ↓
GitHub remote repository
```

---

### 5. GitHub and SSH

The course repository is hosted on GitHub.

SSH was set up so Git could communicate securely with GitHub.

The repository remote uses an SSH address:

```text
git@github.com:Denim-Ahir/CSC360-AU2520274.git
```

Important distinction:

```text
Git
→ version control system

GitHub
→ remote hosting/service for Git repositories

SSH
→ secure authentication/connection method
```

The configured remote can be checked using:

```powershell
git remote -v
```

This shows where Git fetches from and pushes to.

The remote is conventionally named:

```text
origin
```

Example:

```text
origin  git@github.com:Denim-Ahir/CSC360-AU2520274.git (fetch)
origin  git@github.com:Denim-Ahir/CSC360-AU2520274.git (push)
```

---

### 6. Cloning the Repository

The GitHub repository was cloned to the local computer.

Cloning means creating a local copy of a remote Git repository.

General command:

```powershell
git clone <repository>
```

After cloning, the project existed locally and could be worked on from PowerShell.

The local project directory was:

```text
C:\Users\denim\CSC360-AU2520274
```

PowerShell was then used from inside this project directory.

---

### 7. Project Root

The project root is the top-level directory of the project.

For this project:

```text
C:\Users\denim\CSC360-AU2520274
```

The project root contains important project-level files and directories.

Basic structure:

```text
CSC360-AU2520274
│
├── .gitignore
├── pom.xml
├── reflections
│
└── src
```

When PowerShell shows:

```text
PS C:\Users\denim\CSC360-AU2520274>
```

commands are being executed from the project root.

---

### 8. Maven Project Structure

Maven follows a standard project structure.

Important directories:

```text
src
├── main
│   └── java
│
└── test
    └── java
```

#### `src/main`

Contains the actual application/source code.

Example:

```text
src/main/java/com/denim/csc360/
```

This is where the main Java programs are stored.

#### `src/test`

Contains test code.

Example:

```text
src/test/java/com/denim/csc360/
```

Main distinction:

```text
src/main
→ actual program

src/test
→ tests for the program
```

---

### 9. `pom.xml`

`pom.xml` is the Maven project configuration file.

POM = Project Object Model.

It contains information Maven needs to manage the project, including project configuration and dependencies/plugins.

The presence of `pom.xml` allows Maven to recognize and manage the project as a Maven project.

---

### 10. `.gitignore`

`.gitignore` specifies files and directories that Git should not track.

This is useful for generated files and other files that do not need to be stored in the Git repository.

For a Maven project, generated build output such as `target/` should generally not be committed.

Basic idea:

```text
Source/configuration
→ commit

Generated build output
→ normally ignore
```

---

### 11. Maven Build Workflow

Maven was used to build and test the project.

Basic workflow:

```text
Source code
    ↓
Maven compile
    ↓
.class files
    ↓
Tests
    ↓
Package/build
```

A Maven test/build process can be started using:

```powershell
mvn test
```

A package/build can be created using:

```powershell
mvn package
```

The generated build output is placed inside:

```text
target/
```

---

### 12. `target/`

`target/` is Maven's generated build directory.

It contains compiled and generated files.

Example structure:

```text
target
├── classes
├── test-classes
├── surefire-reports
└── csc360-1.0-SNAPSHOT.jar
```

Important distinction:

```text
src/
→ source files written by us

target/
→ files generated by Maven
```

The `.class` files inside `target/classes` are compiled versions of the Java source files.

For example:

```text
App.java
    ↓ compile
App.class
```

---

### 13. Running a Java Class from PowerShell

Once the project was compiled, the compiled classes could be run from PowerShell.

General command:

```powershell
java -cp target/classes <fully-qualified-class-name>
```

For example:

```powershell
java -cp target/classes com.denim.csc360.App
```

Important parts:

```text
java
→ Java runtime

-cp
→ classpath

target/classes
→ location of compiled classes

com.denim.csc360.App
→ class being executed
```

The fully-qualified class name includes the package structure.

---

### 14. First Graphics Project — Drawing a Square

The practical task of the session was to create a simple graphics program that draws a square.

The goal was intentionally basic:

```text
Create window
    ↓
Create drawing area
    ↓
Draw square
```

The square was drawn as an outline rather than a filled square.

The graphics idea was that a computer does not understand a "square" as a physical object. It has to be represented using graphical primitives.

Conceptually:

```text
points
  ↓
lines
  ↓
shape
```

A square can therefore be represented using four connected line segments.

---

### 15. Coordinates and Drawing

The drawing takes place on a 2D coordinate system.

A point can be represented using:

```text
(x, y)
```

The position determines where something is drawn on the screen.

A square can be represented using multiple coordinate points.

```text
A -------- B
|          |
|          |
|          |
D -------- C
```

Each corner has a coordinate.

Lines connect the points:

```text
A → B
B → C
C → D
D → A
```

The square is therefore constructed from simpler graphical elements.

---

### 16. Graphics as Instructions

The computer does not "see" the square in the same way a human does.

The program provides instructions describing what needs to appear.

Conceptually:

```text
window
  ↓
drawing surface
  ↓
coordinates
  ↓
lines
  ↓
square appears
```

This connects back to the basic graphics concepts from Session 1:

```text
pixel
  ↓
points
  ↓
lines
  ↓
2D shapes
```

The square project was the first practical application of these ideas.

---

### 17. What the Session Established

By the end of the session, the complete development workflow had been connected:

```text
PowerShell
    ↓
Java
    ↓
Maven
    ↓
Git
    ↓
GitHub + SSH
    ↓
Repository
    ↓
Maven project
    ↓
Java source
    ↓
Compile
    ↓
Test
    ↓
Run
    ↓
Graphics output
```

This established the basic environment required for later graphics work.

---

## What I Learned

### Command Line

- PowerShell can be used as the main development environment.
- Programs and development tools can be controlled through commands.
- The current directory matters when running project commands.

### Java

- Java source files use `.java`.
- Java source is compiled into `.class` files.
- `java` runs compiled Java programs.
- `javac` is the Java compiler.

### Maven

- Maven manages the Java project's build process.
- `pom.xml` defines the Maven project.
- `src/main` contains application code.
- `src/test` contains test code.
- `target` contains generated build output.

### Git

- Git tracks changes to the project.
- The local repository and GitHub repository are separate.
- `origin` refers to the configured remote.
- `git remote -v` can be used to inspect the remote connection.

### GitHub + SSH

- GitHub stores the remote repository.
- SSH provides authentication for GitHub operations.
- A remote repository can be cloned locally and later pushed to.

### Graphics

- Graphics are constructed from simpler primitives.
- Coordinates determine where graphical elements are placed.
- Lines can be combined to form 2D shapes.
- A square can be represented using four connected line segments.

---

## What I Did on My Own

- Used PowerShell to navigate to the project root.
- Checked the project status using Git.
- Verified the Java/Maven environment from the command line.
- Worked through the Maven build process.
- Ran the compiled Java class from PowerShell.
- Debugged the issue when the class did not initially run.
- Identified that the source file had not been saved in VS Code before compiling/running.
- Saved the file and rebuilt the project.
- Verified that the square program ran successfully.

---

## Questions / Things to Clarify

- How exactly does the Java drawing system convert coordinate instructions into pixels on the screen?
- How are coordinate values mapped to the actual window dimensions?
- What happens internally between calling a drawing method and seeing the resulting shape?
- How does the graphics system decide which pixels belong to a line?
- What is the exact relationship between Java graphics libraries and the underlying operating system/windowing system?
- How does Maven determine which source files need to be compiled?
- How does Maven locate and execute the test classes?

---

## Quick Revision

```text
PowerShell
→ command-line environment

Java
→ programming language/runtime

javac
→ compiles .java → .class

java
→ runs compiled Java

Maven
→ Java build/project management

pom.xml
→ Maven project configuration

src/main
→ application source

src/test
→ test source

target
→ generated Maven output

Git
→ version control

GitHub
→ remote Git repository hosting

SSH
→ secure GitHub authentication

origin
→ name of remote repository

git remote -v
→ check remote

(x, y)
→ 2D coordinate

points
→ lines
→ shapes

square
→ four connected line segments
```