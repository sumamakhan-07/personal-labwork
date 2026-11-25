// Project-level Gradle file
plugins {
    id("com.android.application") version "8.7.3" apply false
    id("org.jetbrains.kotlin.android") version "2.0.21" apply false
    id("org.jetbrains.kotlin.kapt") version "2.0.21" apply false
    // Safe Args: generates classes for passing data between screens (Practical 2, Step 7)
    id("androidx.navigation.safeargs.kotlin") version "2.9.0" apply false
}
