Tools used:
1. Selenium WebDriver for UI automation
2. TestNG for test execution and reporting
3. Page Object Model (POM) design pattern
4. Maven for dependency management

Prerequisites:

1. Install Java JDK (Version 8 or later)
2. Apache Maven
3. Google Chrome (for executing tests on Chrome)
4. Git (if cloning the repository)

Installation & Setup:

1. Clone the repository: git clone https://github.com/jess1194/Enfin-Automation.git
2. Navigate to the project directory:
3. Install dependencies using Maven:
4. mvn clean install
5. Run the test using command : mvn test
6. OR run the test classes using TestNG:mvn test -Dtest=demoblaze.testsuit.DemoBlazeTests 

Project Structure

Enfin-Automation/
│-- src/main/java        # Page classes
│-- src/test/java        # Test suite and test cases
│-- pom.xml              # Maven configuration file
│-- README.md            # Project documentation

Reporting

After test execution, reports will be generated under the test-output folder
