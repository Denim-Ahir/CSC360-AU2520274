\# Reflection — 22 October 2026



\## Topics Covered



\- Professional GitHub repository standards

\- Maven directory structure and automated testing

\- Conventional Commits and Semantic Versioning

\- Javadoc and API contracts

\- Digital Image Processing (DIP)

\- Image sampling, quantization, and coordinate systems

\- ImageJ architecture: `ImagePlus` and `ImageProcessor`

\- Integrating ImageJ through Maven



\---



\## 1. Professional GitHub Repository Standards



A GitHub repository should be a self-documenting, reproducible software project, not merely a collection of source files.



\### Essential files



| File | Purpose |

|---|---|

| `README.md` | Project purpose, features, architecture, prerequisites, setup, and execution instructions |

| `pom.xml` | Maven configuration, dependencies, and build settings |

| `.gitignore` | Excludes generated files, IDE metadata, and unnecessary files |

| `src/main/java/` | Production Java source code |

| `src/main/resources/` | Images, configuration, FXML, CSS, and other runtime assets |

| `src/test/java/` | Automated tests |

| `src/test/resources/` | Test inputs, fixtures, and sample configurations |



\### What makes a README useful?



\- Explains the problem the project solves and its main features.

\- Identifies the technology stack and software prerequisites.

\- Provides reproducible commands to build, test, and run the application.

\- Includes screenshots, demonstrations, and architecture diagrams where relevant.

\- Documents known limitations and potential improvements.



Example Maven commands:



&#x20;   mvn clean compile

&#x20;   mvn test

&#x20;   mvn clean verify



\*\*Key idea:\*\* Standard project structures reduce setup effort and allow IDEs, build tools, and CI pipelines to recognize the project automatically.



\### Repository hygiene



\- Keep production code and tests separate.

\- Exclude `target/`, compiled `.class` files, and unnecessary IDE/OS metadata.

\- Never commit secrets, credentials, or generated build output unnecessarily.

\- Keep instructions accurate so another developer can reproduce the project.



\---



\## 2. Git Commit Conventions and Semantic Versioning



A commit should explain what changed and, when necessary, why it changed.



\### Conventional Commits



Format:



&#x20;   <type>(<scope>): <short description>



Common types:



| Type | Use |

|---|---|

| `feat` | New functionality |

| `fix` | Bug fix |

| `docs` | Documentation changes |

| `test` | Adding or modifying tests |

| `refactor` | Internal restructuring without intended behavioural changes |

| `style` | Formatting or non-functional styling |

| `chore` | Maintenance, dependency updates, or configuration |



Example:



&#x20;   fix(geometry): handle collinear points



A good commit should be:

\- \*\*Atomic:\*\* One logical, cohesive change.

\- \*\*Descriptive:\*\* Explains the change clearly.

\- \*\*Reviewable:\*\* Avoids mixing unrelated modifications.

\- \*\*Reversible:\*\* Can be reverted without unnecessarily affecting other work.



Atomic commits also help `git bisect` identify which change introduced a defect.



\### Semantic Versioning



Format:



&#x20;   MAJOR.MINOR.PATCH



\- \*\*MAJOR:\*\* Incompatible or breaking changes.

\- \*\*MINOR:\*\* Backward-compatible functionality.

\- \*\*PATCH:\*\* Backward-compatible bug fixes.



\*\*Connection:\*\* Clear commits document development history, while semantic versioning communicates the impact of changes between releases.



\---



\## 3. Javadoc and API Contracts



Javadoc documents Java classes, constructors, and methods for developers who use or maintain the code.



\### Two levels of documentation



| Level | Purpose | Audience |

|---|---|---|

| README | Explains project purpose, architecture, setup, and execution | Users, evaluators, contributors |

| Javadoc | Defines method behaviour, parameters, return values, and exceptions | Developers and API consumers |



\### Important Javadoc tags



| Tag | Meaning |

|---|---|

| `@param` | Describes a parameter and its constraints |

| `@return` | Describes the returned value |

| `@throws` | Documents conditions that cause an exception |

| `@see` | Links related methods, classes, or references |

| `@since` | Indicates the version in which an API was introduced |



Javadoc should document behaviour and edge cases rather than merely repeat a method's name.



Example:



&#x20;   /\*\*

&#x20;    \* Computes the area of a rectangle.

&#x20;    \*

&#x20;    \* @param width the rectangle's width; must be non-negative

&#x20;    \* @param height the rectangle's height; must be non-negative

&#x20;    \* @return the rectangle's area

&#x20;    \* @throws IllegalArgumentException if either dimension is negative

&#x20;    \*/

&#x20;   public double area(double width, double height) {

&#x20;       if (width < 0 || height < 0) {

&#x20;           throw new IllegalArgumentException(

&#x20;               "Dimensions must be non-negative"

&#x20;           );

&#x20;       }

&#x20;       return width \* height;

&#x20;   }



\### Generating documentation



&#x20;   mvn javadoc:javadoc



The generated HTML documentation is typically available under:



&#x20;   target/site/apidocs/



Javadoc can improve IDE tooltips, code completion, onboarding, and API consistency. Build tooling can also validate documentation and detect malformed comments or missing information.



\*\*Key idea:\*\* Javadoc acts as an API contract by clarifying expected behaviour, constraints, and failure conditions.



\---



\## 4. Automated Testing



Automated testing should be part of regular development rather than something performed only before submission.



\### Types of tests



\- \*\*Unit tests:\*\* Test individual methods or classes in isolation.

\- \*\*Integration tests:\*\* Verify that multiple components work together.

\- \*\*System/UI tests:\*\* Test complete workflows, including user interaction.



JUnit 5 is a common Java testing framework.



\### Arrange–Act–Assert (AAA)



1\. \*\*Arrange:\*\* Prepare inputs and expected conditions.

2\. \*\*Act:\*\* Execute the method being tested.

3\. \*\*Assert:\*\* Verify the result or expected exception.



Example:



&#x20;   @Test

&#x20;   void shouldCalculateRectangleArea() {

&#x20;       // Arrange

&#x20;       double width = 4;

&#x20;       double height = 5;



&#x20;       // Act

&#x20;       double result = width \* height;



&#x20;       // Assert

&#x20;       assertEquals(20, result);

&#x20;   }



\### Why testing matters



\- \*\*Regression prevention:\*\* Detects previously fixed problems returning.

\- \*\*Executable documentation:\*\* Demonstrates expected behaviour through examples.

\- \*\*Refactoring confidence:\*\* Helps verify that changes preserve existing behaviour.

\- \*\*CI integration:\*\* Automated checks can prevent faulty changes from being merged.



Run the tests using:



&#x20;   mvn test



A test suite should be deterministic, repeatable, and independent of unnecessary external side effects.



\---



\## 5. Introduction to Digital Image Processing



Digital Image Processing (DIP) uses computational techniques to manipulate images, improve visual quality, or extract information from visual data.



Unlike human perception, a computer represents an image numerically and processes it using algorithms.



\### Major operations



\- \*\*Intensity transformations:\*\* Modify pixel brightness or intensity.

\- \*\*Histogram equalization:\*\* Redistribute intensity values to improve contrast.

\- \*\*Spatial filtering:\*\* Process neighbourhoods of pixels to blur, sharpen, or detect features.

\- \*\*Edge detection:\*\* Identify boundaries using operators such as Sobel and Prewitt.

\- \*\*Morphological operations:\*\* Process shapes and structures in images.

\- \*\*Frequency-domain processing:\*\* Analyse or transform image information using techniques such as the Discrete Fourier Transform.

\- \*\*Colour-space conversion:\*\* Represent colours using systems such as RGB, HSB, and CIELab.



Burger and Burge's \*Digital Image Processing: An Algorithmic Introduction Using Java\* connects the mathematical foundations of image processing with practical Java implementations. ImageJ provides a platform for implementing and experimenting with these algorithms.



\*\*Key idea:\*\* An image is numerical data that can be manipulated through algorithms rather than merely a visual object displayed on screen.



\---



\## 6. Image Sampling, Quantization, and Coordinates



A real-world scene is continuous, but a digital image must represent it using discrete positions and numerical values.



\### Sampling vs. quantization



| Concept | Meaning |

|---|---|

| Spatial sampling | Divides the image plane into discrete pixels |

| Intensity quantization | Maps intensity values to a finite set of digital levels |

| Pixel | A discrete spatial element of an image |

| Bit depth | Determines the number of representable intensity levels |



For a bit depth of \\(b\\):



&#x20;   Number of intensity levels = 2^b



For an 8-bit grayscale image:



&#x20;   2^8 = 256 intensity levels



The values range from 0 to 255, where 0 typically represents black and 255 represents white.



An image of width \\(W\\) and height \\(H\\) contains:



&#x20;   Total pixels = W \* H



\### Image coordinate system



Digital images generally use the top-left corner as the origin.



\- The horizontal coordinate \\(x\\) increases from left to right.

\- The vertical coordinate \\(y\\) increases from top to bottom.

\- Valid horizontal coordinates range from `0` to `width - 1`.

\- Valid vertical coordinates range from `0` to `height - 1`.



This differs from the usual Cartesian coordinate system, where the vertical axis normally increases upwards.



\### Row-major storage



A two-dimensional image may be stored as a one-dimensional array, with each row placed after the previous row.



&#x20;   index = y \* width + x



Example for an image of width 5:



&#x20;   Coordinate (2, 1)

&#x20;   index = 1 \* 5 + 2

&#x20;   index = 7



Therefore, pixel `(2, 1)` is stored at linear array index `7`, assuming zero-based indexing.



\*\*Key idea:\*\* Correct image processing requires understanding both the pixel coordinate system and the underlying memory representation.



\---



\## 7. ImageJ Architecture



ImageJ is an extensible Java platform for scientific image processing, including biomedical imaging and computer vision.



Its architecture separates the image container and associated information from the underlying pixel-processing operations.



\### `ImagePlus`



`ij.ImagePlus` is the high-level image container. It can hold:

\- Image title and metadata.

\- Image dimensions and calibration information.

\- A reference to an image processor.

\- Region of Interest (ROI) information.

\- One or more image slices.



Common operations include retrieving the processor, displaying the image, and saving it.



\### `ImageProcessor`



`ij.process.ImageProcessor` is the abstract processing layer responsible for pixel access and manipulation.



It provides operations such as:



\- `getWidth()` and `getHeight()`

\- `getPixel(x, y)`

\- `setPixel(x, y, value)`

\- `getPixels()`

\- `invert()`

\- `filter(type)`



\### Processor hierarchy



| Class | Representation | Typical use |

|---|---|---|

| `ByteProcessor` | `byte\[]` | 8-bit images |

| `ShortProcessor` | `short\[]` | 16-bit images |

| `FloatProcessor` | `float\[]` | 32-bit floating-point images |

| `ColorProcessor` | `int\[]` | Packed RGB colour images |



The concrete processor depends on the image's representation and bit depth.



\### Why this separation matters



`ImagePlus` manages the image as a higher-level object, while `ImageProcessor` handles pixel-level operations.



This separation allows algorithms to use common processing methods without needing to manage every storage detail themselves. When performance matters, direct access to the underlying pixel array can reduce overhead.



ImageJ can also run in \*\*headless mode\*\*, allowing automated image processing without opening graphical windows. This is useful for batch processing and automated testing.



\---



\## 8. Integrating ImageJ Through Maven



Maven dependencies allow external libraries to be managed through `pom.xml` rather than manually adding JAR files to each development environment.



ImageJ 1.x can be included using the dependency coordinates:



&#x20;   <dependency>

&#x20;       <groupId>net.imagej</groupId>

&#x20;       <artifactId>ij</artifactId>

&#x20;       <version>1.54f</version>

&#x20;   </dependency>



The version shown is the example from the class material; the version used in a project should match its requirements.



\### Benefits of Maven integration



\- Dependencies are declared centrally.

\- Maven resolves the library and adds it to the classpath.

\- Teammates can reproduce the same project setup.

\- Build and CI environments can resolve dependencies consistently.

\- Image-processing code can be tested without manual JAR installation.



\### Programmatic image processing



ImageJ provides utilities such as `ij.IJ` for opening and saving images.



A typical workflow is:



1\. Open an image.

2\. Retrieve its `ImageProcessor`.

3\. Apply pixel transformations or filters.

4\. Save the processed image.



This allows image-processing algorithms to be used independently of the interactive ImageJ interface.



\*\*Key idea:\*\* Maven makes ImageJ a managed project dependency, while ImageJ's API provides reusable image-processing functionality inside Java applications.



\---



\## Key Takeaways



\- A professional repository needs clear documentation, standard structure, clean version control, and automated tests.

\- Conventional Commits improve the readability and traceability of development history.

\- Semantic Versioning communicates whether a release contains breaking changes, new features, or fixes.

\- Javadoc documents API contracts, constraints, and exceptional cases.

\- Automated tests help detect regressions and make refactoring safer.

\- Digital images are discrete numerical representations of continuous scenes.

\- Sampling determines pixel positions; quantization determines representable intensity values.

\- The top-left image origin and row-major indexing are important for correct pixel access.

\- `ImagePlus` manages image-level information, while `ImageProcessor` handles pixel operations.

\- Maven dependency management makes ImageJ easier to integrate into reproducible Java projects.

