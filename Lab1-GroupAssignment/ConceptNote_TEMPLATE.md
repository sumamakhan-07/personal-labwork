# Concept Note - Tenant Rent Tracker (Group __)

**Members:** Name, AdmNo ...

## 1. Problem Statement
Property managers in Nairobi often track rent in notebooks or WhatsApp messages, so they lose track of who has paid, who owes, and how much.

## 2. Proposed Solution
A Tenant Management System (mobile app) that records tenants, rent paid and outstanding balances, and flags tenants who need a reminder.

## 3. Target Users
Landlords, property managers and caretakers of residential apartments.

## 4. Functionality Implemented in this Assignment
A console prototype that: registers tenants (name, house, rent, amount paid), validates input, calculates each tenant's balance, classifies each account, prints a payment summary with totals and a reminder list.

## 5. Kotlin Concepts Used
- **Variables and constants:** `val systemName`, `var totalRent`
- **Data types:** String, Double (rent), Int (index), Boolean
- **Input and output:** `readLine()`, `println`, formatted KES values
- **Conditionals:** `if` for validation, `when` for account status
- **Loops:** `while` for input and validation, `for` over `names.indices` for the summary
- **Collections:** `mutableListOf` for names, houses, rents and payments
