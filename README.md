# 🛒 grid-docker-shoppingSite-end2end

An end-to-end testing framework built with Java, Selenium, TestNG, and Allure Reports. This project tests an e-commerce
website using Dockerized Selenium Grid.

---

## 📁 Project Structure

```
grid-docker-shoppingSite-end2end/
├── pom.xml
├── testngOne.xml
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   ├── base/                   # WebDriver and base page setup
│   │   │   ├── pages/                  # Page Object Model classes
│   │   │   └── utils/                  # Utility classes and helpers
│   │   └── resources/                  # Config and logging files
│   └── test/
│        ├──java/   
│           ├── base/                       # Base test class
│           ├── data/                       # Test data (JSON)
│           ├── listeners/                  # TestNG Listeners
│           └── tests/                      # Test classes
```

---

## 🚀 Tech Stack

- **Java 21**
- **Selenium 4.33.0**
- **TestNG 7.10.2**
- **Allure Reports 2.29.1**
- **Log4j2 (2.24.3)**
- **Docker (Selenium Grid)**
- **Jackson, Apache POI, Commons IO**

---

## 🛠 Features

- ✅ Page Object Model (POM) design pattern
- ✅ TestNG framework integration
- ✅ Allure reporting (auto-generated in `target/allure-results`)
- ✅ Retry mechanism and listeners for robust test reporting
- ✅ Utility classes for screenshots, JSON data, alerts, and more

---

## ⚙️ How to Run

### 1. Clean and install dependencies

```bash
mvn clean install
```

### 2. Start Selenium Grid with Docker

```bash
docker-compose up -d
> ℹ️ This will start the Selenium Hub along with Chrome and Firefox nodes as defined in your docker-compose.yml
```

### 3. Run the test suite

```bash
mvn test -Pend2end
```

### 4. Serve the Allure report

```bash
allure serve target/allure-results
```

> ℹ️ Ensure Allure CLI is installed and accessible via system path.

### Optional: Generate static Allure report

```bash
allure generate target/allure-results --clean -o target/allure-report
```

---

## Browser Compatibility Note

### Important Notice for Chrome Users

When logging in with valid credentials using Google Chrome, a security pop-up may appear indicating that the password
was found in a data breach.
This browser-level warning cannot be handled through JavaScript alerts and may interrupt your test flow.

🔄 __Recommendation__:
For uninterrupted automation testing, please use Firefox or Microsoft Edge with the Selenium Grid setup or locally. Or
if you really want to
execute the tests on Chrome browser either add __--incognito__ to the chromeOptions. In fact, I have applied this
approach in my framework.
Please see the `Driver` class.

## 📌 Notes

- Test results are automatically written to `target/allure-results` using `maven-surefire-plugin` configuration.
- Listeners (`TestListener`, `RetryAnalyzer`, etc.) automatically log failures and take screenshots.
- Allure annotations can be used in test classes to enrich the report:

```java

@Severity(SeverityLevel.CRITICAL)
@Description("Verifies product is added to cart")
@Test
public void testAddToCart() {
}
```

---

## 🧰 Resources

- [Allure Framework](https://docs.qameta.io/allure/)
- [TestNG](https://testng.org/doc/)
- [Selenium](https://www.selenium.dev/documentation/)
- [Docker Selenium Grid](https://github.com/SeleniumHQ/docker-selenium)

---

## 🧑‍💻 Author

poyrazemun

This framework was built for robust and scalable e-commerce test automation using modern practices.
