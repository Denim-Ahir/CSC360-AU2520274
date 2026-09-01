# Session 05

**Date:** 25 August 2026  
**Course:** CSC360  
**Topics:** Git Synchronization, README, Maven, `pom.xml`, Swing Accessibility, Multithreading, Event Dispatch Thread, and Swing Thread Safety

---

# Class Notes

## 1. Synchronizing the Local Git Repository

Before starting new work, the local repository should be synchronized with the remote repository.

The first step is to check the current state:

```bash
git status
This shows:
•	Current branch
•	Modified files
•	Untracked files
•	Changes staged for commit
•	Whether the local branch is ahead of or behind the remote branch
The current branch can also be checked using:
git branch --show-current
After checking the local state, changes from the remote repository can be retrieved and integrated using:
git pull
Basic Workflow
Local Repository
      |
      v
  git status
      |
      v
Check current changes
      |
      v
  git pull
      |
      v
Latest remote changes
      |
      v
Start new work
Pulling before beginning new work reduces the chance of developing on top of an outdated version of the project and makes potential conflicts easier to identify.
________________________________________
2. Purpose of README.md
A project should contain a README.md file that introduces the project and explains how to work with it.
The README acts as the initial documentation for someone who encounters the repository for the first time.
A useful README can contain:
•	Project name
•	Project overview
•	Purpose
•	Main features
•	Prerequisites
•	Installation instructions
•	Setup instructions
•	Build commands
•	Run commands
•	Testing instructions
•	Project structure
•	Usage examples
•	Contribution information
•	License information
The general flow is:
Repository
    |
    v
README.md
    |
    +--> What is the project?
    |
    +--> What is required?
    |
    +--> How is it installed?
    |
    +--> How is it built?
    |
    +--> How is it run?
    |
    +--> How is it tested?
The main purpose is to reduce the amount of time required for a new user or developer to understand and use the project.
________________________________________
3. Maven in IntelliJ IDEA
Maven is a build automation and dependency management tool used for Java projects.
Maven allows project configuration, dependency management, compilation, testing, and packaging to follow a standardized process.
IntelliJ IDEA provides a Maven tool window through which Maven operations can be performed.
Common Maven lifecycle operations include:
clean
compile
test
package
Maven Operations
Maven operation	Purpose
clean	Removes previously generated build files
compile	Compiles the project's source code
test	Compiles and runs tests
package	Builds the project into its configured package format
The Maven tool window can also be used to:
•	Reload/synchronize the Maven project
•	Download dependencies
•	View dependencies
•	Run lifecycle phases
•	Run Maven plugins
•	View project modules
•	View profiles
•	Generate build output
Maven operations can therefore be performed either through IntelliJ IDEA or through the command line.
________________________________________
4. pom.xml
pom.xml is the central configuration file of a Maven project.
POM stands for:
Project Object Model
The file describes important information about the project and tells Maven how the project should be built.
A Maven project commonly contains:
pom.xml
    |
    +--> Project identity
    |
    +--> Java/compiler configuration
    |
    +--> Dependencies
    |
    +--> Plugins
    |
    +--> Build configuration
    |
    +--> Packaging
    |
    +--> Other project metadata
Important POM Elements
groupId
Identifies the organization or group that owns the project.
Example:
<groupId>com.denim</groupId>
artifactId
Identifies the project/artifact.
Example:
<artifactId>csc360</artifactId>
version
Specifies the version of the project.
Example:
<version>1.0-SNAPSHOT</version>
Together:
groupId + artifactId + version
identify a particular Maven artifact.
________________________________________
5. Dependencies in Maven
External libraries required by a project can be declared inside pom.xml.
Instead of manually downloading and managing every library, Maven can resolve dependencies from configured repositories.
Conceptually:
pom.xml
   |
   v
Dependency declaration
   |
   v
Maven resolves dependency
   |
   v
Downloads required library
   |
   v
Library becomes available to project
This makes dependency management more consistent and reproducible.
________________________________________
6. Maven Build Lifecycle
Maven follows a defined build lifecycle.
Some commonly used phases are:
clean
  ↓
compile
  ↓
test
  ↓
package
These phases perform different stages of the build process.
The command:
mvn compile
compiles the main source code.
The command:
mvn test
runs the testing phase.
The command:
mvn package
packages the compiled project according to the configuration in pom.xml.
Maven therefore provides a consistent process for taking source code through compilation, testing, and packaging.
________________________________________
7. Accessibility in Swing
Swing components support Java's accessibility framework.
Accessibility allows assistive technologies to obtain information about interface components.
However:
Using Swing
      ≠
Automatically accessible application
Accessibility still requires deliberate design and implementation.
Important considerations include:
•	Meaningful accessible names
•	Accessible descriptions where appropriate
•	Proper labels for input controls
•	Keyboard navigation
•	Visible focus indicators
•	Readable color choices
•	Sufficient contrast
•	Not communicating information through color alone
•	Testing with appropriate accessibility tools
Accessibility should therefore be considered as part of the interface design rather than added only at the end.
________________________________________
8. Why Multithreading Is Needed
A program can contain multiple independent sequences of work.
A thread is an execution path within a process.
Multithreading allows multiple threads to perform work concurrently.
It can be useful for:
•	Keeping a graphical interface responsive
•	Performing input/output operations
•	Processing independent tasks
•	Running suitable computations concurrently
•	Performing background work
However, multithreading also introduces additional complexity.
Potential problems include:
•	Race conditions
•	Deadlocks
•	Synchronization requirements
•	Shared-state problems
•	More difficult debugging
Therefore:
Multithreading
      |
      +--> Can improve responsiveness
      |
      +--> Can allow concurrent work
      |
      +--> Also introduces synchronization complexity
Multithreading is therefore not automatically beneficial for every program.
________________________________________
9. Processes vs Threads
A process is an independent running program with its own memory space.
Threads are execution paths within a process and generally share the process's memory.
Conceptually:
Process
│
├── Thread 1
├── Thread 2
└── Thread 3
Threads can communicate through shared memory more easily than separate processes, but shared memory also creates the possibility of race conditions and synchronization problems.
Basic Comparison
Process	Thread
Has its own process memory	Shares memory within its process
Stronger isolation	Less isolated
Higher overhead	Generally lower overhead
Communication can be more expensive	Shared-memory communication can be easier
Failure is more isolated	A thread problem can affect the process
________________________________________
10. Why a GUI Can Freeze
A graphical interface needs to continuously respond to user actions.
Examples:
Mouse clicks
Keyboard input
Repainting
Window events
Component updates
If the thread responsible for processing these events becomes busy performing a long-running operation, the interface cannot respond until that operation finishes.
Examples of long-running operations include:
•	Network requests
•	File operations
•	Database queries
•	Large calculations
•	Blocking operations
•	Artificial delays
The result is an apparently frozen interface.
Conceptually:
GUI Thread
    |
    +--> Mouse events
    +--> Keyboard events
    +--> Repaint requests
    +--> Component updates
    |
    X
    |
Long-running operation
    |
    v
GUI cannot process new events
    |
    v
Application appears frozen
The solution is generally to keep long-running operations away from the GUI event-processing thread.
________________________________________
11. Swing Event Dispatch Thread
Swing uses a special thread called the:
Event Dispatch Thread
(EDT)
The EDT is responsible for processing Swing events and performing Swing interface operations.
Examples include:
•	User input events
•	Component updates
•	Repaint-related work
•	GUI event handling
The important principle is:
EDT
  |
  +--> GUI events
  +--> GUI updates
  +--> Swing component interaction
Long-running operations should not block the EDT.
________________________________________
12. Why a Single Long-Running Task Freezes Swing
Consider:
EDT
 |
 +--> Button click
 |
 +--> Long calculation
 |
 +--> Long calculation
 |
 +--> Long calculation
 |
 +--> Finished
 |
 +--> Process next GUI event
While the long-running calculation is executing, the EDT cannot process other events.
Therefore:
Long task on EDT
        ↓
EDT becomes busy
        ↓
Events wait
        ↓
Painting/input cannot be processed normally
        ↓
GUI appears frozen
The task should instead be moved to a background thread.
________________________________________
13. Swing Thread Safety
Swing components are generally not thread-safe.
This means that arbitrary simultaneous access to Swing components from multiple threads can lead to:
•	Race conditions
•	Inconsistent component state
•	Painting problems
•	Unpredictable behavior
Therefore, Swing GUI creation and modification should normally happen on the EDT.
The general model is:
Background Thread
       |
       | performs long-running work
       v
    Result
       |
       v
Event Dispatch Thread
       |
       v
Update Swing GUI
This separates background computation from interface operations.
________________________________________
14. SwingUtilities.invokeLater()
SwingUtilities.invokeLater() can be used to schedule GUI-related work on the Event Dispatch Thread.
Example:
SwingUtilities.invokeLater(() -> {
    JFrame frame = new JFrame("Application");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(500, 400);
    frame.setVisible(true);
});
The important idea is:
invokeLater()
      |
      v
EDT
      |
      v
Execute GUI code
This is useful when GUI creation or modification needs to be performed from code that may currently be running on another thread.
________________________________________
15. SwingWorker
For longer-running background operations, Swing provides SwingWorker.
A SwingWorker allows the expensive work to execute away from the EDT while providing a mechanism for updating the GUI after the work is complete.
Example:
SwingWorker<String, Void> worker = new SwingWorker<>() {

    @Override
    protected String doInBackground() throws Exception {
        return performLongRunningTask();
    }

    @Override
    protected void done() {
        try {
            resultLabel.setText(get());
        } catch (Exception exception) {
            resultLabel.setText("The operation failed.");
        }
    }
};

worker.execute();
doInBackground()
protected String doInBackground()
Performs the long-running operation away from the EDT.
done()
protected void done()
Called after the background operation completes and used for updating the GUI.
execute()
worker.execute();
Starts the SwingWorker.
The overall flow is:
execute()
    ↓
doInBackground()
    ↓
Long-running work
    ↓
Task completes
    ↓
done()
    ↓
Update GUI
The key separation is:
doInBackground()
→ background work

done()
→ GUI update
________________________________________
16. GUI Threading Model
The overall Swing threading model discussed can be summarized as:
Swing Application
       |
       +----------------------+
       |                      |
       v                      v
Event Dispatch Thread     Background Worker
       |                      |
       +--> Events            +--> Long-running work
       +--> Painting          |
       +--> GUI updates       v
                           Result
                              |
                              v
                           EDT
                              |
                              v
                          Update GUI
The EDT remains available for the interface while expensive work is performed separately.
________________________________________
Class Reflection
Topics Covered
•	Synchronizing a local Git repository using git pull
•	Checking repository state using git status
•	Checking the active branch using git branch --show-current
•	Purpose and structure of README.md
•	Maven support in IntelliJ IDEA
•	Maven lifecycle operations
•	pom.xml and the Project Object Model
•	Maven project coordinates
•	Maven dependencies
•	Accessibility in Swing
•	Processes and threads
•	Reasons for using multithreading
•	GUI responsiveness
•	Event Dispatch Thread (EDT)
•	Swing thread safety
•	SwingUtilities.invokeLater()
•	SwingWorker
•	Separating background work from GUI operations
________________________________________
Key Takeaways
•	Repository synchronization: Run git status and check the current branch before starting work, then use git pull to synchronize with the remote repository.
•	README: README.md provides the first layer of project documentation and should explain the project's purpose, setup, usage, build process, and other information needed by users or contributors.
•	Maven: Maven standardizes Java project building, dependency management, testing, and packaging.
•	pom.xml: The POM is the central Maven configuration file containing project identity, dependencies, compiler settings, plugins, packaging, and other build information.
•	Accessibility: Swing provides accessibility support, but an accessible application still requires meaningful names, keyboard navigation, focus handling, readable contrast, and deliberate interface design.
•	Multithreading: Threads allow independent work to happen concurrently, but shared state introduces synchronization and debugging challenges.
•	GUI responsiveness: A long-running operation on the GUI thread prevents the interface from processing events and can make the application appear frozen.
•	EDT: Swing uses the Event Dispatch Thread for processing interface events and performing Swing GUI operations.
•	Thread safety: Swing components should normally be created and modified on the EDT rather than being accessed arbitrarily from multiple threads.
•	invokeLater(): SwingUtilities.invokeLater() schedules GUI work to run on the EDT.
•	SwingWorker: SwingWorker provides a structure for running long-running work in the background while allowing the completed result to be passed back to the GUI.
________________________________________
Questions
1.	What exactly happens internally when git pull combines remote changes with the local branch?
2.	How does Maven locate and download dependencies declared in pom.xml?
3.	How does IntelliJ IDEA detect and synchronize changes made to pom.xml?
4.	How does Swing's Event Dispatch Thread interact with the operating system's event system?
5.	How does Swing prevent or manage race conditions when multiple threads interact with application data?
6.	What happens internally when SwingUtilities.invokeLater() places a task onto the EDT?
7.	How does SwingWorker communicate results from doInBackground() back to done()?
8.	How should background threads safely communicate with a graphical application when the background operation continuously produces results?
________________________________________
What I Did on My Own
•	Reviewed the Git workflow and connected git pull with the synchronization process already used in the project.
•	Examined the role of README.md as project-level documentation rather than simply a description of the project.
•	Connected Maven's IntelliJ integration with the existing pom.xml used in the CSC360 project.
•	Traced the difference between GUI work performed on the EDT and long-running work that should be performed in the background.
•	Studied the relationship between invokeLater(), the EDT, and Swing component updates.
•	Compared the purpose of SwingWorker with directly performing a long-running operation on the GUI thread.
•	Connected thread safety and GUI responsiveness to the design of future interactive graphics projects.
________________________________________
Quick Revision
Git
git status
    ↓
Check local state

git branch --show-current
    ↓
Check active branch

git pull
    ↓
Synchronize with remote
Maven
pom.xml
   |
   +--> groupId
   +--> artifactId
   +--> version
   +--> dependencies
   +--> plugins
   +--> build configuration
   |
   v
Maven
   |
   +--> compile
   +--> test
   +--> package
Swing
Swing GUI
    |
    v
Event Dispatch Thread
    |
    +--> Events
    +--> Painting
    +--> GUI updates
Long Task
Long task on EDT
      ↓
EDT blocked
      ↓
GUI stops responding
Correct Model
EDT
 |
 +--> GUI / events / painting
 |
 +--> Background worker
          |
          +--> Long-running work
          |
          v
        Result
          |
          v
         EDT
          |
          v
      Update GUI
Swing Utilities
SwingUtilities.invokeLater()
    ↓
Schedule GUI work on EDT

SwingWorker
    ↓
doInBackground()
    ↓
Background work
    ↓
done()
    ↓
Update GUI
