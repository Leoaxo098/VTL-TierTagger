plugins {
    id("multiloader-base")
    id("com.modrinth.minotaur")
}

val common = project(":common")
val commonMain = common.sourceSets["main"]
val commonCompileJava = common.tasks.getByName<JavaCompile>(commonMain.compileJavaTaskName)
val commonProcessResources =
    common.tasks.getByName<ProcessResources>(commonMain.processResourcesTaskName)

dependencies {
    implementation(common)
}

sourceSets.main {
    runtimeClasspath += commonMain.runtimeClasspath
    compileClasspath += commonMain.compileClasspath
}

tasks {
    processResources {
        from(commonProcessResources.destinationDir)
        dependsOn(commonProcessResources)

        inputs.property("version", version)

        filesMatching(listOf("fabric.mod.json")) {
            expand(mapOf("version" to inputs.properties["version"]))
        }
    }

    jar {
        duplicatesStrategy = DuplicatesStrategy.FAIL
        from(rootDir.resolve("LICENSE"))
        from(commonCompileJava.destinationDirectory)
    }
}

var releaseType = "release"
if (version.toString().contains("alpha")) releaseType = "alpha"
else if (version.toString().contains("beta")) releaseType = "beta"

modrinth {
    token = System.getenv("MODRINTH_TOKEN")
    projectId = BuildConfig.MODRINTH_PROJECT_ID
    versionNumber = "$version-${project.name}"
    versionType = releaseType
    uploadFile.set(tasks.jar)
    gameVersions.add(BuildConfig.MINECRAFT_VERSION)
    loaders.add(project.name) // fabric (quilt added in fabric/build.gradle.kts)
    dependencies {
        required.project("ukulib")
    }
}