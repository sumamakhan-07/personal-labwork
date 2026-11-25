// Concept Note: https://docs.google.com/document/REPLACE-WITH-YOUR-GROUP-LINK
//Group 1 - Name1, AdmNo1 | Name2, AdmNo2 | Name3, AdmNo3 | Name4, AdmNo4

// ============================================================
// TENANT RENT TRACKER - console prototype
// Concepts: val/var, data types, input/output, if/when, loops, collections
// ============================================================

fun main() {
    // ---- CONSTANTS (val) and settings ----
    val systemName = "TENANT RENT TRACKER"

    // ---- COLLECTIONS: one entry per tenant (same index = same tenant) ----
    val names = mutableListOf<String>()
    val houses = mutableListOf<String>()
    val rents = mutableListOf<Double>()
    val paid = mutableListOf<Double>()

    println("========================================")
    println("  $systemName")
    println("========================================")
    println("Welcome! This program records tenants, checks what they owe")
    println("and shows a rent summary for the property manager.")
    println("You will be asked for each tenant's details.")
    println("Type the word 'done' as a tenant name when you have finished.\n")

    // ---- INPUT LOOP ----
    while (true) {
        println("Please enter the tenant's name (or 'done' to finish):")
        print("> ")
        val name = readLine()?.trim() ?: ""
        if (name.equals("done", ignoreCase = true)) break
        if (name.isEmpty()) {
            println("  Name cannot be empty. Try again.\n")
            continue
        }

        println("Please enter the house number (e.g. A-204):")
        print("> ")
        val house = readLine()?.trim() ?: ""

        // Keep asking until the rent is a valid positive number
        var rent: Double? = null
        while (rent == null || rent <= 0) {
            println("Please enter the monthly rent in KES (numbers only):")
            print("> ")
            rent = readLine()?.trim()?.toDoubleOrNull()
            if (rent == null || rent <= 0) println("  Invalid amount. Please enter a number greater than 0.")
        }

        var amountPaid: Double? = null
        while (amountPaid == null || amountPaid < 0) {
            println("Please enter the amount paid so far in KES:")
            print("> ")
            amountPaid = readLine()?.trim()?.toDoubleOrNull()
            if (amountPaid == null || amountPaid < 0) println("  Invalid amount. Please enter 0 or more.")
        }

        names.add(name)
        houses.add(house)
        rents.add(rent!!)
        paid.add(amountPaid!!)
        println("  Tenant '$name' recorded.\n")
    }

    if (names.isEmpty()) {
        println("\nNo tenants were entered, so there is nothing to report. Goodbye!")
        return
    }

    // ---- PROCESSING + OUTPUT ----
    println("\nCalculating outstanding rent for ${names.size} tenant(s)...")
    println("----------------------------------------")
    println("RENT PAYMENT SUMMARY")
    println("----------------------------------------")

    var totalRent = 0.0
    var totalPaid = 0.0
    val reminderList = mutableListOf<String>()

    for (i in names.indices) {
        val balance = rents[i] - paid[i]
        totalRent += rents[i]
        totalPaid += paid[i]

        // when with ranges/conditions classifies the account
        val status = when {
            balance <= 0 -> "FULLY PAID"
            balance < rents[i] / 2 -> "SMALL BALANCE"
            balance < rents[i] -> "LARGE BALANCE"
            else -> "NOTHING PAID"
        }

        // Reminder rule: nothing paid at all this month
        if (status == "NOTHING PAID") {
            reminderList.add(names[i])
        }

        println("${i + 1}. Tenant: ${names[i]}  (House ${houses[i]})")
        println("   Monthly Rent:        KES ${"%,.0f".format(rents[i])}")
        println("   Amount Paid:         KES ${"%,.0f".format(paid[i])}")
        println("   Outstanding Balance: KES ${"%,.0f".format(if (balance > 0) balance else 0.0)}")
        println("   Status:              $status")
        println()
    }

    println("----------------------------------------")
    println("TOTALS")
    println("----------------------------------------")
    println("Expected rent:  KES ${"%,.0f".format(totalRent)}")
    println("Collected:      KES ${"%,.0f".format(totalPaid)}")
    println("Outstanding:    KES ${"%,.0f".format(totalRent - totalPaid)}")
    println("Collection rate: ${"%.1f".format(totalPaid / totalRent * 100)}%")

    println("\nTenants to send a reminder to (nothing paid yet):")
    if (reminderList.isEmpty()) println("  None - everyone has paid something. Well done!")
    else for (n in reminderList) println("  - $n")

    println("\nThank you for using $systemName. Goodbye!")
}
