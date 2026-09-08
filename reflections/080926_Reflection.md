# Reflection — 8 September 2026

## Topics Covered

- Java Collections Framework
- Collection views
- Exceptions, logging, and debugging
- Events and event handling in AWT and Swing
- Java event-class hierarchy
- Master-detail UI layouts

---

## 1. Java Collections Framework

The **Java Collections Framework** provides interfaces and classes for storing, retrieving, and manipulating groups of objects, along with common algorithms such as sorting and searching.

| Interface | Main characteristic | Common implementations |
|---|---|---|
| `List` | Ordered, allows duplicates | `ArrayList`, `LinkedList` |
| `Set` | Unique elements | `HashSet`, `LinkedHashSet`, `TreeSet` |
| `Queue` / `Deque` | Ordered processing from one or both ends | `ArrayDeque`, `LinkedList`, `PriorityQueue` |
| `Map` | Key-value associations; keys are unique | `HashMap`, `LinkedHashMap`, `TreeMap` |

### List

A `List` is ordered and supports indexed access. Duplicate elements are allowed.

    List<String> names = new ArrayList<>();
    names.add("Asha");
    names.add("Drumil");
    names.add("Asha");

- `ArrayList` → resizable array, fast indexed access.
- `LinkedList` → doubly linked list and also implements `Deque`; arbitrary index access is slower.

### Set

A `Set` does not allow duplicate elements.

    Set<String> uniqueNames = new HashSet<>();
    uniqueNames.add("Asha");
    uniqueNames.add("Asha");

The set still contains only one `"Asha"`.

- `HashSet` → hashing, no guaranteed iteration order.
- `LinkedHashSet` → hashing + insertion order.
- `TreeSet` → sorted order using natural ordering or a `Comparator`.

### Queue / Deque

A `Queue` stores elements for processing in some order. FIFO is common but not universal.

- `ArrayDeque` → efficient queue and double-ended queue.
- `LinkedList` → implements both `List` and `Deque`.
- `PriorityQueue` → removes elements according to priority rather than insertion order.

    Queue<String> tasks = new ArrayDeque<>();
    tasks.offer("first");
    tasks.offer("second");
    String nextTask = tasks.poll();

### Map

A `Map` associates unique keys with values.

    Map<String, Integer> scores = new HashMap<>();
    scores.put("Asha", 90);
    scores.put("Drumil", 95);

- `HashMap` → hashing, no guaranteed iteration order.
- `LinkedHashMap` → preserves insertion order, or optionally access order.
- `TreeMap` → sorted keys.

Important distinction: **`Map` is part of the Collections Framework but does not extend `Collection`**, because a map stores key-value associations rather than individual elements.

---

## 2. Collection Views

A **view** represents all or part of another collection without necessarily copying its elements. A view is commonly backed by the original collection, so changes can affect both.

### `subList()`

    List<String> values = new ArrayList<>(
        List.of("A", "B", "C", "D")
    );

    List<String> middle = values.subList(1, 3);
    middle.set(0, "Updated");

Because `middle` is backed by `values`, the original list becomes:

    ["A", "Updated", "C", "D"]

Structural modification of the original list outside the sublist while the view is being used can invalidate the view and cause a `ConcurrentModificationException` or undefined semantics for later view operations.

### Map Views

A `Map` provides three important views:

    Set<String> keys = scores.keySet();
    Collection<Integer> values = scores.values();
    Set<Map.Entry<String, Integer>> entries = scores.entrySet();

These are connected to the original map. For example, removing a key through `keySet()` also removes its mapping from the original map.

### View vs Copy

    List<String> view = Collections.unmodifiableList(values);
    List<String> copy = List.copyOf(values);

- `Collections.unmodifiableList(values)` → read-only **view**; changes to `values` remain visible.
- `List.copyOf(values)` → unmodifiable **copy**; later changes to `values` do not affect it.

The important question when using a view is: **Is this object backed by the original mutable data?**

---

## 3. Exceptions, Logging and Debugging

These solve different problems:

- **Exception** → represents a failure or unusual condition during execution.
- **Logging** → records information about application behaviour.
- **Debugging** → investigates a problem and finds its cause.

### Exceptions

Java uses:

`try` → code that may fail  
`catch` → handles an exception  
`finally` → cleanup code  
`throw` → explicitly throws an exception  
`throws` → declares that a method may throw an exception

    try {
        String content = Files.readString(path);
        process(content);
    } catch (IOException exception) {
        logger.log(Level.SEVERE, "Unable to read " + path, exception);
    }

### Checked vs Unchecked Exceptions

**Checked exceptions**
- Must be caught or declared.
- Often represent conditions a caller may recover from.
- Examples: `IOException`, `SQLException`.

**Unchecked exceptions**
- Extend `RuntimeException`.
- Compiler does not require them to be caught or declared.
- Often indicate invalid input, invalid state, or programming mistakes.
- Examples: `NullPointerException`, `IllegalArgumentException`, `IndexOutOfBoundsException`.

`Error` types such as `OutOfMemoryError` are also unchecked, but represent serious JVM/environment failures and are separate from exceptions in the hierarchy.

### Logging

Logging gives evidence about what the application was doing.

Common options:

- `java.util.logging` → included with the JDK.
- Log4j 2 → configurable logging implementation.
- SLF4J → logging facade used with a chosen implementation.

Common levels include `TRACE`, `DEBUG`, `INFO`, `WARN`, and `ERROR`.

Parameterized logging is preferable when supported:

    logger.info("Loaded {} records for user {}", recordCount, userId);

Logs should contain useful context but must not expose passwords, tokens, or other sensitive information.

### Debugging

Debugging is a systematic process:

**Reproduce → Isolate → Understand → Fix**

Useful tools:

- Breakpoints
- Step into / over / out
- Variable and expression inspection
- Call stack
- Conditional breakpoints
- Exception breakpoints
- Thread and heap inspection
- `jdb`
- Focused tests
- Carefully placed logs

**Exceptions and logs provide evidence; the debugger lets us inspect program state and control flow directly.**

---

## 4. Events and Event Handling in AWT/Swing

An **event** represents something that happened, such as a button click, mouse action, key press, focus change, or window action.

AWT and Swing use the **delegation event model**:

1. An event source produces an event object.
2. A listener is registered with the source.
3. The source sends the event to the listener.
4. The listener's callback handles the event.

Example:

    JButton saveButton = new JButton("Save");

    saveButton.addActionListener(event -> {
        saveDocument();
    });

Here:

- `saveButton` → event source
- `ActionEvent` → event type
- `ActionListener` → listener
- Lambda → event handler

### Common Events

| Event | Listener | Typical cause |
|---|---|---|
| `ActionEvent` | `ActionListener` | Button/menu action |
| `MouseEvent` | `MouseListener` | Mouse press/click/etc. |
| Mouse movement | `MouseMotionListener` | Move/drag |
| `MouseWheelEvent` | `MouseWheelListener` | Wheel rotation |
| `KeyEvent` | `KeyListener` | Key press/release/type |
| `WindowEvent` | `WindowListener` | Window actions |
| `FocusEvent` | `FocusListener` | Focus gained/lost |
| `ItemEvent` | `ItemListener` | Selection state changed |
| `ComponentEvent` | `ComponentListener` | Move/resize/show/hide |
| `ContainerEvent` | `ContainerListener` | Child added/removed |

Swing text components normally use `DocumentListener` for text changes, while AWT text components use the older `TextListener`.

### Adapter Classes

Some listener interfaces have many methods. Adapter classes provide empty implementations so only the required method needs to be overridden.

    frame.addWindowListener(new WindowAdapter() {
        @Override
        public void windowClosing(WindowEvent event) {
            confirmExit();
        }
    });

### Event Dispatch Thread

Most Swing event handlers execute on the **Event Dispatch Thread (EDT)**.

Important rule:

> **Do not perform long-running work inside an event handler.**

A long-running handler blocks painting and makes the UI unresponsive. Background work can use `SwingWorker`, while Swing component updates remain on the EDT.

---

## 5. Java Event-Class Hierarchy

Event classes are organized through inheritance from general event types to more specific ones.

    java.util.EventObject
    └── java.awt.AWTEvent
        ├── ActionEvent
        ├── AdjustmentEvent
        ├── ComponentEvent
        │   ├── ContainerEvent
        │   ├── FocusEvent
        │   ├── InputEvent
        │   │   ├── KeyEvent
        │   │   └── MouseEvent
        │   │       └── MouseWheelEvent
        │   └── WindowEvent
        ├── HierarchyEvent
        ├── InputMethodEvent
        ├── ItemEvent
        ├── PaintEvent
        └── TextEvent

Important distinction:

**Event classes ≠ listener interfaces**

For example:

- `MouseEvent` → object containing information about a mouse action.
- `MouseListener` → interface that receives selected mouse events.

Event objects can contain information such as:

- Source component
- Event time
- Keyboard/mouse modifiers
- Cursor coordinates
- Key code or character
- Click count

---

## 6. Master-Detail UI Layout

A **master-detail layout** separates the interface into two related views:

- **Master** → collection/list of items.
- **Detail** → information or editing interface for the selected item.

Examples:

- Email list → selected email
- Contact list → contact details
- File browser → preview
- Product list → editing form

A Swing implementation can use:

- `JList` or `JTable` → master
- `JPanel` → detail
- `JSplitPane` → displays both
- `ListSelectionListener` → reacts to selection changes

    JList<Customer> customerList = new JList<>(listModel);
    JPanel detailPanel = createDetailPanel();

    JSplitPane splitPane = new JSplitPane(
        JSplitPane.HORIZONTAL_SPLIT,
        new JScrollPane(customerList),
        detailPanel
    );

    customerList.addListSelectionListener(event -> {
        if (!event.getValueIsAdjusting()) {
            showCustomer(customerList.getSelectedValue());
        }
    });

The selected object should be maintained in the **application model**, rather than reconstructed only from text shown in the UI. This keeps the data, selection state, and presentation responsibilities separate.

On narrow screens, master and detail can also appear on separate screens while preserving the same interaction pattern.

---

## Key Takeaways

- Choose collections based on **ordering, uniqueness, lookup and processing requirements**.
- `Map` belongs to the Collections Framework but does not implement `Collection`.
- A **view** may remain connected to its original collection; a **copy** is independent.
- Exceptions signal problems, logs provide evidence, and debugging finds root causes.
- AWT/Swing events follow the **source → event → listener → handler** model.
- Swing event handlers run on the **EDT**, so long-running work should be moved to background threads.
- Event classes and listener interfaces are separate concepts.
- A **master-detail** layout connects selection in one view to focused information in another.