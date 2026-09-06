# CSC360 — Session 08 Reflection
**Date:** 03 September 2026

## Topics Covered

- Terminal vs GUI
- SSH (Secure Shell)
- Matching elements between lists
- Drawing arrows in JavaFX
- Reproducible software failures
- FXML in JavaFX

---

## Class Notes

### 1. Terminal vs GUI

| Terminal | GUI |
|---|---|
| Uses typed commands and text output | Uses windows, menus, buttons, icons |
| Low resource usage | Usually requires more graphical resources |
| Fast and efficient for repetitive tasks | Easier for beginners to discover features |
| Easy to automate using scripts | Better visual feedback |
| Useful for remote systems | Useful for interactive workflows |
| Precise control | Dragging, selecting, resizing, previewing |

Neither is always better. IDEs often combine both through a graphical interface and an integrated terminal.

**Terminal use cases:**
- Development
- Server administration
- Deployment
- File processing
- Automated tasks
- Remote systems

**GUI advantages:**
- Discoverability
- Visual feedback
- Accessibility for new users
- Interactive operations

---

### 2. SSH

**SSH = Secure Shell**

SSH is a protocol used to securely connect to and control a remote computer.

```bash
ssh username@example.com
The communication between the local and remote machines is encrypted.
Authentication:
•	Password authentication
•	Public-key authentication — generally more secure and convenient
Common uses:
•	Running commands on remote servers
•	Server monitoring and administration
•	Building and deploying software
•	Editing configuration files
•	File transfer using scp or sftp
•	Creating tunnels
SSH transfers mostly text/control information, so it uses much less bandwidth than a remote graphical desktop. Scripts can also automate repetitive administration tasks.
________________________________________
3. Matching Common Elements Between Lists
Example:
List<String> leftItems = List.of("A", "B", "C");
List<String> rightItems = List.of("C", "A", "D");
The goal is to identify equal values and preserve their positions so that connections can later be drawn between them.
A simple approach uses nested loops:
for (int leftIndex = 0; leftIndex < leftItems.size(); leftIndex++) {
    for (int rightIndex = 0; rightIndex < rightItems.size(); rightIndex++) {
        if (leftItems.get(leftIndex).equals(rightItems.get(rightIndex))) {
            matches.add(new Match(leftIndex, rightIndex));
        }
    }
}
For lists of sizes n and m, the complexity is:
O(n × m)
For larger lists, a Map<String, List<Integer>> can store the indexes of values in the right-hand list. computeIfAbsent() can be used to build this map efficiently.
Important design decisions:
•	Case sensitivity
•	Whitespace
•	Duplicate values
•	Multiple arrows
•	One-to-many matches
•	null handling
________________________________________
4. Drawing Arrows in JavaFX
To draw a connection, each item needs a known graphical position.
•	First list → left side
•	Second list → right side
•	Arrow starts near the center-right of the left item
•	Arrow ends near the center-left of the right item
A JavaFX Line can represent the main arrow:
Line line = new Line(startX, startY, endX, endY);
The arrowhead can be calculated using:
Math.atan2(...)
The arrowhead uses:
•	Length = 10
•	Angle = 25°
•	Math.cos() and Math.sin() for the endpoint calculations
•	Two additional Line objects for the arrowhead
The lines are added to a JavaFX Pane.
If the items move, the arrow coordinates must be recalculated or bound to the item positions.
A useful structure is to separate:
1.	Data
2.	Matching logic
3.	Presentation/drawing
This makes the matching logic easier to test independently from the GUI.
________________________________________
5. Reproducible Software Failures
A failure is reproducible when it can be triggered consistently using known steps.
Reproducibility is important because it allows us to:
•	Find the root cause
•	Verify a fix
•	Create regression tests
•	Communicate the problem clearly
A useful bug report should contain:
Information	Purpose
Summary	What went wrong
Exact steps	How to reproduce it
Expected result	What should happen
Actual result	What actually happened
Input/minimal example	Data needed to reproduce it
Errors/logs	Technical evidence
Versions	Application, libraries, JDK, OS
Configuration/environment	Conditions of failure
Frequency	Always or intermittent
Screenshots/recordings	Additional evidence
Example:
Right-clicking an empty canvas causes a NullPointerException.
Environment could include:
•	Java 21
•	JavaFX 21
•	macOS
Debugging workflow:
Observe
  ↓
Reduce to smallest failing case
  ↓
Inspect state
  ↓
Find root cause
  ↓
Create automated regression test
  ↓
Fix
  ↓
Repeat verification
For intermittent failures, useful information includes:
•	Logs
•	Timestamps
•	Random seeds
•	Thread information
•	Environment details
________________________________________
6. FXML
FXML is XML-based markup for JavaFX user interfaces.
It separates the structure of the interface from most of the Java application logic.
FXML can define:
•	Layout containers
•	Controls
•	Menus and tables
•	fx:id values
•	Event handler names
•	Styles and resources
•	Controllers
Example:
<VBox fx:controller="com.example.Controller">
    <Label fx:id="messageLabel" />
    <Button text="Continue" onAction="#handleContinue" />
</VBox>
The controller can reference the UI elements:
@FXML
private Label messageLabel;

@FXML
private void handleContinue() {
    messageLabel.setText("Continue clicked");
}
FXML is loaded using FXMLLoader:
FXMLLoader loader = new FXMLLoader(getClass().getResource("view.fxml"));
Parent root = loader.load();
Why use FXML?
•	Separates UI structure from Java behavior
•	Helps organize large interfaces
•	Allows designers and developers to work separately
•	Works with tools such as Scene Builder
•	Provides a structured controller-based approach
FXML is optional. Small or highly dynamic interfaces can still be created directly in Java.
________________________________________
Class Reflection
This session connected several concepts that are useful when building actual software rather than only writing individual pieces of code. The terminal and GUI comparison showed that both interfaces have different strengths. SSH showed how the terminal becomes especially useful when working with remote computers.
The list-matching example connected algorithms with graphics: first the program must determine which elements correspond, and only then can it draw the correct connections. Separating matching logic from presentation also makes the program easier to test.
The discussion of reproducible failures showed why being able to reliably recreate a bug is important for debugging and regression testing. FXML showed another way to separate different parts of a JavaFX application by keeping UI structure in a separate file from controller logic.
________________________________________
Questions
•	How should duplicate values be handled when several items have the same name?
•	When is a Map significantly better than nested loops for matching lists?
•	How should arrow coordinates be updated when GUI elements move?
•	What is the best way to reproduce an intermittent multithreading failure?
•	When is FXML preferable to creating the entire JavaFX interface directly in Java?
________________________________________
What I Did on My Own
•	Compared the advantages and disadvantages of terminal-based and GUI-based workflows.
•	Understood how SSH can be used to work with remote computers without requiring a graphical desktop.
•	Studied how list matching can be optimized using a Map.
•	Connected list matching with the graphical task of drawing arrows between corresponding elements.
•	Reviewed how reproducible failures help with debugging and automated regression testing.
•	Studied the relationship between FXML files and JavaFX controllers.
________________________________________
Key Takeaways
•	Terminal and GUI are different tools with different strengths.
•	SSH provides encrypted remote computer access.
•	Nested loops can match list elements in O(n × m) time.
•	A Map can improve matching performance for larger lists.
•	Matching logic and graphical presentation should be separated.
•	Reproducible failures make debugging and regression testing much easier.
•	FXML separates JavaFX UI structure from Java controller logic.
•	FXML is optional; JavaFX interfaces can also be created directly in Java.
________________________________________
Quick Revision
Terminal → commands, automation, low resources, remote systems
GUI → visual interaction, discoverability, interactive workflows

SSH → secure remote access
ssh username@example.com

List matching → compare values + preserve indexes
Nested loops → O(n × m)
Map → faster lookup, useful for larger lists

JavaFX arrow → Line + atan2 + cos/sin
Moving elements → recalculate/bind arrow coordinates

Reproducible failure → consistent trigger + known steps
Debug → reproduce → reduce → inspect → root cause → test → fix → verify

FXML → XML-based JavaFX UI structure
FXML ↔ Controller
FXMLLoader → loads FXML
