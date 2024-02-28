pluginManagement {
  repositories {
    google()
    mavenCentral()
    gradlePluginPortal()
  }
}
dependencyResolutionManagement {
  repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
  repositories {
    google()
    mavenCentral()
  }
}

rootProject.name = "Something Calc"
include(":app")
include(":core:designsystem")
include(":core:ui")
include(":core:utility")
include(":feature:circles-2")
include(":feature:prime-numbers")
include(":feature:thermohydrometer")
include(":feature:speech")
include(":core:logger")
