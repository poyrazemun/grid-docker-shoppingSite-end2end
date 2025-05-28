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

### 2. Run the test suite

```bash
mvn test -P end2end
```

### 3. Serve the Allure report

```bash
allure serve target/allure-results
```

> ℹ️ Ensure Allure CLI is installed and accessible via system path.

### Optional: Generate static Allure report

```bash
allure generate target/allure-results --clean -o target/allure-report
```

---

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
