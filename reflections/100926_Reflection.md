# Reflection — 10 September 2026

## Topics Covered

- Streaming vs downloading
- Streaming in graphics applications
- Server-side rendering (SSR) vs client-side rendering (CSR)
- Image storage and metadata
- AWT and platform abstraction

---

## 1. Streaming vs Downloading

Streaming and downloading both transfer data, but differ mainly in **when the data is consumed and whether a complete local copy is retained**.

| Aspect | Streaming | Downloading |
|---|---|---|
| Consumption | Can begin before complete transfer | Usually after enough/all data arrives |
| Connection | Usually required during use | Mainly required during transfer |
| Storage | Temporary buffer/cache | Complete local file |
| Repeated use | May require another transfer | Reuses local copy |
| Quality | Can adapt to network conditions | Usually fixed |
| Live content | Naturally supported | Cannot download a complete file that does not yet exist |

Streaming can use less data when only part of something is consumed, while downloading can be more efficient when the same content is repeatedly used.

Streaming uses **buffering** to handle temporary network problems. Adaptive bitrate streaming can switch between different quality levels depending on bandwidth and device capacity.

---

## 2. Streaming in Graphics

Streaming is not limited to video. Graphics applications can progressively receive or load visual data.

### Examples

- **Video streaming:** encoded frames are divided into segments and delivered to the player.
- **Texture/model streaming:** games load nearby or visible assets instead of keeping the entire world in memory.
- **Cloud gaming:** the server renders the game, encodes the frames and streams them to the client while user input is sent back.
- **Remote desktop/visualization:** complex rendering happens remotely while the client receives the resulting view.
- **VR/AR:** high-resolution scenes or remotely rendered frames can be streamed, but latency becomes critical.
- **Progressive delivery:** large images, maps, point clouds and 3D models can appear at low detail first and become more detailed over time.

An important idea here is that **streaming can manage memory and computational limits, not just network bandwidth**.

---

## 3. Server-Side vs Client-Side Rendering

### Server-Side Rendering (SSR)

The server generates the HTML and sends rendered content to the browser. JavaScript can later hydrate it and add interaction.

Advantages:

- Earlier visible content
- Better support for search indexing
- Link previews can work immediately
- Some content can remain visible if JavaScript is slow
- Server/CDN caching can be useful

Costs:

- More server-side rendering work
- Possible increase in time to first byte
- Hydration still requires JavaScript
- Personalized pages are harder to cache safely
- More architectural complexity

### Client-Side Rendering (CSR)

The server initially sends an HTML shell and JavaScript. The browser executes the JavaScript, retrieves data and creates the interface.

Advantages:

- Fast navigation after initial loading
- Rich local state and interaction
- Useful for dashboards, design tools and admin applications
- Less server-side HTML rendering

Costs:

- Larger initial JavaScript dependency
- Initial load can be slower
- More dependent on JavaScript
- SEO/link previews can require additional handling

| Concern | SSR | CSR |
|---|---|---|
| Initial HTML | Rendered content | Usually application shell |
| Server workload | Higher | Lower HTML-rendering workload |
| Initial visit | Can show content earlier | Can be slower with large JS bundle |
| Later navigation | May require server requests | Often fast after initial load |
| SEO/previews | Generally straightforward | May require additional handling |
| Interactivity | After scripts/hydration | After initialization |

Modern applications can combine SSR and CSR rather than treating them as mutually exclusive.

Accessibility is also not automatically determined by rendering strategy. It depends on semantic HTML, keyboard support, focus management, labels, contrast and correct handling of dynamic content.

---

## 4. Storing Images

A common architecture separates the **image itself** from its **metadata**.

- Object storage → stores image bytes.
- Database → stores information about the image.

Typical metadata includes:

- Object key / URL
- Filename
- MIME type
- Width and height
- File size
- Owner ID
- Upload timestamp
- Permissions
- Checksum/hash

Example:

    Database
    └── object_key: images/users/42/profile.webp

    Object storage
    └── images/users/42/profile.webp → image bytes

Amazon S3 is **object storage, not a database**.

Databases can store images directly using binary/BLOB fields, but object storage is generally more practical for large-scale media because of scalability, durability, access control, lifecycle policies and CDN integration.

---

## 5. Why is AWT "Abstract"?

AWT stands for **Abstract Window Toolkit**.

The "abstract" part mainly refers to its **platform-independent API**. Java code can use classes such as `Frame`, `Button`, `TextField` and `Graphics` without directly dealing with the operating system's windowing API.

The basic abstraction is:

    Java application
        ↓
    AWT platform-independent API
        ↓
    Platform-specific toolkit / peers
        ↓
    Native operating-system window system

Traditional AWT components are often called **heavyweight components** because they use native platform peers for much of their rendering and interaction.

### AWT vs Swing

| AWT | Swing |
|---|---|
| Core windowing, graphics and event infrastructure | Builds on AWT |
| Many components use native peers | Most components are lightweight |
| `Frame`, `Button` | `JFrame`, `JButton` |
| Native appearance can vary | Pluggable look and feel |

Swing still depends on AWT for top-level windows, events, layouts, fonts, colours and Java2D.

---

## Key Takeaways

- Streaming allows progressive consumption; downloading normally creates a complete local copy.
- Streaming is also useful for managing graphics memory and computation.
- SSR and CSR involve different trade-offs rather than one universally replacing the other.
- Image bytes and image metadata are commonly stored separately.
- S3 is object storage, not a database.
- AWT abstracts platform-specific windowing and graphics systems behind a Java API.
- Swing builds on AWT rather than replacing it completely.