// LAB 2 (document title: "Lab 1 - Kotlin Basics") - Tenant Management System
// Project name: TenantManagementSystem. All parts run from main().
// Comments answer the questions asked in the lab.

fun main() {
    println("Welcome to the Tenant Management System")   // PART 0
    part1(); part2(); part3(); part4(); part5(); part6(); part7(); part8()
}

fun header(t: String) = println("\n===== $t =====")

// ------------------------------------------------------------
// PART 1 - Variables
// ------------------------------------------------------------
fun part1() {
    header("PART 1 - Variables")
    // Task 1.1: val = cannot be reassigned, var = can be.
    val tenantId = 1001            // an ID never changes once issued
    val name = "Jane Wanjiku"      // (a legal name change is rare; treat as fixed)
    val phone = "0712345678"       // fixed for this record
    val houseNumber = "A-204"      // the tenant stays in this house
    val monthlyRent = 25000        // fixed by the lease
    var amountPaid = 15000         // ONLY amountPaid must be var: it grows with every payment
    // Answer: only amountPaid genuinely needs var; everything else is fixed for the record.

    // Task 1.2
    println("Amount paid before: $amountPaid")
    amountPaid += 5000
    println("Amount paid after: $amountPaid")

    // Task 1.3 (break it deliberately) - uncomment to see the error:
    // tenantId = 1002
    // Error: "Val cannot be reassigned". The compiler refuses because val is read-only.
    // Single-word fix: change `val` to `var`.
}

// ------------------------------------------------------------
// PART 2 - Data types and casting
// ------------------------------------------------------------
fun part2() {
    header("PART 2 - Data Types and Casting")
    // Task 2.1 explicit types
    val tenantId: Int = 1001
    val name: String = "Jane Wanjiku"
    val phoneNumber: String = "0712345678"
    val houseNumber: String = "A-204"
    val monthlyRentInt: Int = 25000
    val amountPaid: Int = 15000
    // Why String for phone? (1) A leading 0 would be dropped by an Int (0712... -> 712...).
    // (2) We never do arithmetic on phone numbers, and they can contain '+' (+254...).
    // Also: 0712345678 is larger than Int.MAX_VALUE (2,147,483,647) in some formats.

    // Task 2.2
    val block: Char = 'A'
    val isActive: Boolean = true
    println("$name ($tenantId) phone $phoneNumber, house $houseNumber, block $block, active=$isActive, paid $amountPaid")

    // Task 2.3
    // val rentAsDouble: Double = monthlyRentInt   // does NOT compile: type mismatch (Int vs Double)
    val rentAsDouble: Double = monthlyRentInt.toDouble()   // fix: explicit conversion
    println("Rent as Double: $rentAsDouble")
    // Why explicit? Implicit widening can hide precision loss and surprising bugs;
    // Kotlin makes you state the conversion so you see it.

    // Task 2.4
    val companyRegNumber: Long = 999_999_999L
    println("Company registration number: $companyRegNumber")   // no underscores printed
}

// ------------------------------------------------------------
// PART 3 - Strings
// ------------------------------------------------------------
fun part3() {
    header("PART 3 - Strings")
    val name = "Jane Wanjiku"
    val house = "A-204"
    val monthlyRent = 25000

    // 3.1 concatenation
    println(name + " lives in house " + house)
    // 3.2 template - easier to read: fewer quotes and + signs
    println("$name lives in house $house")
    // 3.3 expression inside template
    println("Total rent for 6 months: KES ${monthlyRent * 6}")

    // 3.4 triple-quoted string
    val receipt = """
        ===== RENT RECEIPT =====
        Tenant: $name
        House: $house
        Paid: KES 20000
    """.trimIndent()
    println(receipt)
    // Without trimIndent() the leading spaces from your code are PRINTED.
    // trimIndent() removes the common leading indentation (and the blank first/last lines).

    // 3.5 Predict: prints "Dear Tenant" (unchanged)
    val greeting = "Dear Tenant"
    greeting.uppercase()          // returns a NEW string which we threw away
    println(greeting)
    // Strings are immutable. To see uppercase: println(greeting.uppercase())
    println(greeting.uppercase())
}

// ------------------------------------------------------------
// PART 4 - Operators
// ------------------------------------------------------------
fun part4() {
    header("PART 4 - Operators")
    val monthlyRent = 25000
    val amountPaid = 20000

    println("Balance: KES ${monthlyRent - amountPaid}")             // 4.1

    // 4.2 Int / Int is INTEGER division: 20000/25000 = 0, then 0*100 = 0 -> "Paid: 0%"
    val wrong = (amountPaid / monthlyRent) * 100
    println("Wrong way: Paid: $wrong%")
    val fix1 = amountPaid * 100 / monthlyRent                        // multiply first
    val fix2 = (amountPaid.toDouble() / monthlyRent * 100).toInt()   // convert to Double
    println("Paid: $fix1%")
    println("Paid: $fix2%")

    // 4.3
    val instalment = 6000
    println("Full instalments: ${monthlyRent / instalment}")
    println("Remaining amount: KES ${monthlyRent % instalment}")

    // 4.4
    val totalRent = monthlyRent.times(6)
    println("Total rent (times): $totalRent")

    // 4.5
    val isRentPaid = amountPaid >= monthlyRent
    println("isRentPaid = $isRentPaid")

    // 4.6
    var monthsInArrears = 2
    val outstanding = monthlyRent - amountPaid > 0
    var needsReminder = outstanding && monthsInArrears > 1
    println("needsReminder (2 months) = $needsReminder")
    monthsInArrears = 1
    needsReminder = outstanding && monthsInArrears > 1
    println("needsReminder (1 month) = $needsReminder")
    // false: && needs BOTH sides true, and 1 > 1 is false.
}

// ------------------------------------------------------------
// PART 5 - Making decisions
// ------------------------------------------------------------
fun part5() {
    header("PART 5 - Making Decisions")
    val monthlyRent = 25000

    // 5.1 test with 20000, 25000, 30000
    for (amountPaid in listOf(20000, 25000, 30000)) {
        val balance = monthlyRent - amountPaid
        if (balance <= 0) println("Paid $amountPaid: Rent is fully paid")
        else println("Paid $amountPaid: Rent is outstanding")
    }

    // 5.2 if / else if / else
    // 5.3 same as a when statement (when reads better for many branches)
    for (amountPaid in listOf(25000, 20000, 10000)) {
        val balance = monthlyRent - amountPaid
        if (balance <= 0) println("[if]   $balance -> Rent is fully paid")
        else if (balance < 10000) println("[if]   $balance -> Small outstanding balance")
        else println("[if]   $balance -> Large outstanding balance")

        when {
            balance <= 0 -> println("[when] $balance -> Rent is fully paid")
            balance < 10000 -> println("[when] $balance -> Small outstanding balance")
            else -> println("[when] $balance -> Large outstanding balance")
        }
    }

    // 5.4 when with ranges
    for (months in listOf(0, 2, 4, 8, 15)) {
        val classification = when (months) {
            0 -> "Rent is up to date"
            in 1..2 -> "Early arrears"
            in 3..5 -> "Serious arrears"
            in 6..12 -> "Critical arrears"
            else -> "Review tenant account"
        }
        println("$months months -> $classification")
    }

    // 5.5 when on a String
    for (status in listOf("ACTIVE", "VACATED", "PENDING", "UNKNOWN")) {
        when (status) {
            "ACTIVE" -> println("Tenant is currently living in the house")
            "VACATED" -> println("Tenant has moved out; house is available")
            "PENDING" -> println("Tenant application is awaiting approval")
            else -> println("Unknown status: $status")
        }
    }
}

// ------------------------------------------------------------
// PART 6 - Ranges and loops
// ------------------------------------------------------------
fun part6() {
    header("PART 6 - Ranges and Loops")
    for (m in 1..12) println(m)                                        // 6.1
    for (m in 1..12 step 2) println("Checking payment history for month $m")   // 6.2
    for (m in 5 downTo 1) println(m)                                   // 6.3

    val tenants = listOf("Jane", "Brian", "Mary", "David")              // 6.4
    for ((index, tenant) in tenants.withIndex()) println("${index + 1}. $tenant")
    // index starts at 0 (computers count from 0), people count from 1, so we add 1.

    // 6.5
    val vacantHouses = 0
    // Version A: checks FIRST -> prints 0 lines
    while (vacantHouses > 0) { println("Checking vacant houses...") }
    // Version B: runs the body FIRST, then checks -> prints 1 line
    do { println("Checking vacant houses...") } while (vacantHouses > 0)
    // Difference: while tests before the first run, do-while tests after it (always runs once).

    repeat(3) { println("Please pay your rent.") }                      // 6.6
}

// ------------------------------------------------------------
// PART 7 - Lists and arrays
// ------------------------------------------------------------
fun part7() {
    header("PART 7 - Lists and Arrays")
    // 7.1
    val tenantsRO = listOf("Jane Wanjiku", "Brian Otieno", "Mary Achieng", "John Kamau")
    println("First: ${tenantsRO.first()}  Last: ${tenantsRO.last()}  Size: ${tenantsRO.size}")

    // 7.2 tenantsRO.add("David Mwangi")  -> ERROR: Unresolved reference 'add' (listOf is read-only)
    // Fix: use mutableListOf
    val tenants = mutableListOf("Jane Wanjiku", "Brian Otieno", "Mary Achieng", "John Kamau")
    tenants.add("David Mwangi")
    tenants.remove("Brian Otieno")
    println("Final list: $tenants  size=${tenants.size}")

    // 7.3
    val houses = arrayOf("A-101", "A-102", "A-103", "A-104")
    println("Second house: ${houses[1]}")
    houses[0] = "A-201"
    println(houses.contentToString())
    println(houses.joinToString(" "))

    // 7.4 println(arrayOf(...)) prints the object's type/identity (e.g. [Ljava.lang.String;@1b6d3586)
    // because arrays do not override toString(). Proper ways:
    val hn = arrayOf("A-101", "A-102")
    println(hn.contentToString())
    println(hn.joinToString())

    // 7.5 "+" appends the right array AFTER the left one: 1, 2, 3, 4, 5, 6
    val blockA = intArrayOf(1, 2, 3)
    val blockB = intArrayOf(4, 5, 6)
    println((blockA + blockB).joinToString())
    println((blockB + blockA).joinToString())   // 4, 5, 6, 1, 2, 3 - order is left then right

    // 7.6
    // MutableList can grow/shrink (add/remove); an Array has a fixed size.
    // An Array's elements can be changed in place (arr[0] = x); a read-only List cannot.
}

// ------------------------------------------------------------
// PART 8 - Null safety
// ------------------------------------------------------------
fun part8() {
    header("PART 8 - Null Safety")
    // 8.1 val tenantEmail: String = null  -> "Null can not be a value of a non-null type String"
    // Add a "?" :  val tenantEmail: String? = null

    var tenantEmail: String? = null
    println(tenantEmail)         // 8.2 prints "null" - not something to show a property manager

    println("Email: ${tenantEmail ?: "Not provided"}")           // 8.3
    tenantEmail = "jane@example.com"
    println("Email: ${tenantEmail ?: "Not provided"}")           // same line, now real address

    emailDemo(null)                                              // 8.4
    nextOfKin(null)                                              // 8.5
    nextOfKin("Peter Wanjiku")

    // ONCE DONE - answers:
    // 1. var when a value changes over time (amountPaid, status).
    // 2. Integer division truncated 20000/25000 to 0.
    // 3. if/else if for conditions of any kind; when is cleaner for many branches/ranges/exact values.
    // 4. List = read-only; MutableList = can add/remove; Array = fixed size, elements editable.
    // 5. ?. returns null safely if the value is null; !! throws NullPointerException.
}

fun emailDemo(tenantEmail: String?) {
    println(tenantEmail?.length)            // null
    println(tenantEmail?.length ?: 0)       // 0
    try {
        println(tenantEmail!!.length)       // throws NullPointerException
    } catch (e: NullPointerException) {
        println("!! threw NullPointerException (the 'not-null assertion'). Use only when you are 100% sure it isn't null.")
    }
}

fun nextOfKin(name: String?) {
    println(name?.uppercase() ?: "No next of kin on record")
}
