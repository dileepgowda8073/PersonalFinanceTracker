
---
# 💰 Personal Finance Tracker

Master your personal finances with a robust Java application for tracking income, expenses, budgets, and insightful financial reports.

---

## 🚀 Features

### 📌 Transaction Management

* Add income/expenses with amount, date, category, and notes
* Edit or delete past transactions
* Support for recurring transactions (e.g., monthly rent)

### 📊 Financial Dashboard

* Real-time balance overview
* Income vs Expense visualization
* Monthly & yearly spending trends
* Filter by date range or category

### 💸 Budgeting Tools

* Set monthly budgets per category
* Track spending with progress indicators
* Overspending alerts and notifications
* Savings goal tracking (e.g., vacation fund)

### 📈 Reporting & Analytics

* Category-wise spending reports
* Export reports to CSV/PDF
* Identify top spending categories
* Insights for saving opportunities

### 🔐 Data & Security

* Local file-based storage
* Automatic backups
* Password protection
* Import/export bank CSV data

### 🧑‍💻 User Experience

* Simple CLI interface
* Searchable command history
* Multiple account support
* Custom categories & currency symbols

---

## 🛠️ Prerequisites

Make sure you have:

* Java Development Kit (JDK) 11+

  ```bash
  java -version
  ```
* Maven 3.6+

  ```bash
  mvn -version
  ```
* Git

  ```bash
  git --version
  ```

**Optional:**

* IntelliJ IDEA / Eclipse / VS Code
* JUnit 5 (included via Maven)

---

## ⚙️ Installation

### Clone the Repository

```bash
git clone https://github.com/yourusername/personal-finance-tracker.git
cd personal-finance-tracker
```

### Build with Maven

```bash
mvn clean compile
mvn package
```

### Run the Application

```bash
java -cp target/classes com.yourpackage.FinanceTrackerApp
```

### Initialize Data Directory

```bash
java -cp target/classes com.yourpackage.FinanceTrackerApp init
```

---

## ▶️ Usage

### Start Application

```bash
java -cp target/classes com.yourpackage.FinanceTrackerApp
```

### Example Session

```
=== Personal Finance Tracker v1.0 ===

1. Add Transaction
2. View Dashboard
3. Reports
4. Accounts
5. Set Budget
6. Import CSV
7. Backup Data
8. Exit

Amount: 2500
Type: income
Category: Salary
Date: 2026-04-20
Notes: Monthly paycheck

✓ Saved! Current balance: ₹45,670
```

### Command Line Options

```bash
--help
--account=business
--version
```

---

## 📁 Data Storage

* Transactions: `data/transactions.json`
* Backups: `data/backups/`

---

## 🗺️ Roadmap

### 🔹 Near-term (3 months)

* JavaFX GUI
* Multi-currency support
* Bank CSV import (HDFC, SBI)
* Email/SMS alerts

### 🔹 Mid-term (6 months)

* Web dashboard (Spring Boot + React)
* Cloud sync (Google Drive, Dropbox)
* Investment tracking
* Tax calculation module

### 🔹 Long-term (1 year+)

* Mobile apps (Android/iOS)
* AI-powered insights
* Family account sharing
* UPI integration

---

## 🤝 Contributing

Contributions are welcome!

### Steps:

```bash
git checkout -b feature/your-feature-name
mvn clean test compile
git commit -m "Add new feature"
git push origin feature/your-feature-name
```

### Guidelines:

* Follow Google Java Style
* Write unit tests (target 80% coverage)
* Update README for new features
* Avoid breaking changes

---

## 💡 Ways to Contribute

* Report bugs
* Suggest features
* Improve documentation
* Add bank/currency support

---
## Author

Dileep 


---



