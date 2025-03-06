 # Selenium EventFiring Demo 
 
## Project Description 
This project demonstrates the use of Selenium's `EventFiringDecorator` and `WebDriverListener` to add custom event-handling functionality to browser automation. It is a Maven-based Java project that showcases how to intercept and log actions such as clicking elements on a webpage.

## Features
- Implements a custom `WebDriverListener` to log actions like clicks.
- Demonstrates the use of Selenium's `EventFiringDecorator` for enhanced event handling.
- Extracts and prints `href` attributes from anchor tags on a webpage.
- Includes a robust error handling mechanism to ensure browser cleanup.

## Technologies Used
- **Java** (JDK 17 or higher recommended)
- **Selenium WebDriver** (Version compatible with your ChromeDriver)
- **Maven** (for project dependency management)

## Prerequisites
1. Install Java JDK 17 or higher.
2. Install Maven.
3. Download the ChromeDriver corresponding to your Chrome version and set the `webdriver.chrome.driver` system property.

## Setup Instructions
1. Clone the repository:
   ```bash
   git clone https://github.com/arunima-rastogi-1/selenium-eventfiring-demo.git
    ```
2. Navigate to the project directory:

```bash
cd selenium-eventfiring-demo
Build the project using Maven:
```
```mvn clean compile```

3. Run the project:

```bash
mvn exec:java
```

4. Usage
Modify the main() method in EventFiringWebDriverExample.java to test with different URLs or WebElements.
Run the program to see custom event logging and output of extracted anchor tags.
Observe the logs for the actions being triggered, such as clicks on elements.

5. Example Output
After running the code, you will see something like the following in the console:

```bash
Before clicking on: [Element details]
After clicking on: [Element details]
Extracting href attributes from the first 10 anchor tags:
1. https://example.com
2. https://another-example.com
3. https://yetanother-example.com
```
...

5. License
This project is licensed under the MIT License.

6. Contributing
Contributions are welcome! Feel free to open issues or submit pull requests to improve this project.
