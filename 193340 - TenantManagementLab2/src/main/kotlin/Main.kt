// LAB 3 (document title "Lab 2") - Tenant Management System with classes
// Rename project to: <StudentNo1>, <StudentNo2> - TenantManagementLab2

// ---------------- Tenant class ----------------
// Task 2: the PRIMARY CONSTRUCTOR receives name, apartmentNumber and rentAmount when the object is created.
// Advantage: an object is never half-built; no need to set every property one by one afterwards.
class Tenant(
    val name: String,              // property created from constructor parameter
    val apartmentNumber: Int,
    rent: Double                   // plain parameter, used to initialise rentAmount below
) {
    // Task 1: isPaid defaults to false
    var isPaid: Boolean = false

    // Task 3 + Task 4: custom setter and getter on rentAmount.
    var rentAmount: Double = if (rent >= 0) rent else 0.0
        // GETTER runs every time the property is READ (e.g. println(tenant.rentAmount)).
        get() {
            println("Rent amount accessed.")
            return field            // 'field' = the real stored value (backing field)
        }
        // SETTER runs every time the property is ASSIGNED (tenant.rentAmount = 5000).
        // Validation keeps bad data (negative rent) out of the object.
        set(value) {
            if (value >= 0) {
                field = value
            } else {
                println("Rent amount cannot be negative.")
            }
        }

    // Task 1: payRent() marks the rent as paid
    fun payRent() {
        isPaid = true
        println("Rent paid successfully by $name")
    }
}

// ---------------- Apartment class (composition) ----------------
// Task 5: An Apartment HAS tenants (has-a relationship = composition).
class Apartment(val apartmentNumber: Int) {
    val tenants: MutableList<Tenant> = mutableListOf()

    // Task 6
    fun addTenant(tenant: Tenant) {
        tenants.add(tenant)
    }

    // Task 7
    fun showTenants() {
        println("Apartment: $apartmentNumber")
        for (tenant in tenants) {
            println("Tenant: ${tenant.name}")
            println("Rent: ${tenant.rentAmount}")
            println("Rent paid: ${tenant.isPaid}")
            println()
        }
    }
}

fun main() {
    println("===== TASK 1 & 2: Tenants, constructor and payRent() =====")
    // Two objects from the same class can differ because each object has its OWN copy
    // of the properties (state); the class is only the blueprint.
    val john = Tenant("John", 101, 15000.0)
    val mary = Tenant("Mary", 101, 18000.0)
    john.payRent()                                   // only John pays
    println("${john.name} paid? ${john.isPaid}")
    println("${mary.name} paid? ${mary.isPaid}")

    println("\n===== TASK 3: Setter validation =====")
    // Validating before storing protects the system from impossible data.
    john.rentAmount = -500.0                         // rejected, message printed
    println("Rent after attempted change: ${john.rentAmount}")
    println("Was the negative value accepted? ${john.rentAmount < 0}")
    john.rentAmount = 16000.0                        // accepted
    println("Rent after valid change: ${john.rentAmount}")

    println("\n===== TASK 4: Getter =====")
    // Getter runs when the property is READ; the setter runs when it is WRITTEN.
    println(john.rentAmount)

    println("\n===== TASKS 5-7: Apartment with tenants =====")
    val apartment = Apartment(101)
    apartment.addTenant(john)
    apartment.addTenant(mary)
    println("Tenants stored: ${apartment.tenants.size}")
    apartment.showTenants()
}
