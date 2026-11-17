rootProject.name = "VTL-TierTagger"

pluginManagement {
    repositories {
        maven { url = uri("https://maven.fabricmc.net/") }
        gradlePluginPortal()
    }
}

include("common", "fabric")
