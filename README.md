# Master Selenium TestNG Automation Test Suite

## Run TestNG Suites with Maven

Use these commands from the project root directory.

### Run Login Suite

```bash
mvn clean test -Dsurefire.suiteXmlFiles=loginTest_testng.xml
```

### Run Jays Company Front-End Suite

```bash
mvn clean test -Dsurefire.suiteXmlFiles=jayscompany_staging_testng.xml
```

## Run Individual Tests

### Login Suite Individual Tests

```bash
# Run single login test
mvn clean test -Dtest=login_tests_automation_suite.testClasses.TC_001_Login

# Run wrong username test
mvn clean test -Dtest=login_tests_automation_suite.testClasses.TC_login_WrongUserName

# Run wrong password test
mvn clean test -Dtest=login_tests_automation_suite.testClasses.TC_login_WrongPassword
```

### Jays Company Front-End Individual Tests

```bash
# Run login test
mvn clean test -Dtest=jayscompany_staging_test_automation_suite.testClasses.frontEnd.TC_03_Login

# Run quick checkout test
mvn clean test -Dtest=jayscompany_staging_test_automation_suite.testClasses.frontEnd.TC_07_QuickRegisteredUsercheckout

# Run create account test
mvn clean test -Dtest=jayscompany_staging_test_automation_suite.testClasses.frontEnd.TC_01_Create_Account

# Run create account form validations test
mvn clean test -Dtest=jayscompany_staging_test_automation_suite.testClasses.frontEnd.TC_02_Create_Account_Form_Validations
```

## Notes

- Suite XML files are located at the project root.
- Use `-Dtest` parameter to run individual test classes without needing a suite XML file.
- If Chrome/CDP warnings appear, they are warnings and do not necessarily fail the build.
