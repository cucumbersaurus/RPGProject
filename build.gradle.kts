val exposedVersion: String by project
val paperVersion: String by project
val kotlinVersion: String by project

plugins {
    `java-library`
    `maven-publish`
    java
    kotlin("jvm") version "1.9.22"
}

repositories {
    mavenLocal()
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib:$kotlinVersion")
    implementation("org.jetbrains.kotlin:kotlin-reflect:$kotlinVersion")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")

    implementation("org.jetbrains.exposed:exposed-core:$exposedVersion")
    implementation("org.jetbrains.exposed:exposed-jdbc:$exposedVersion")
    implementation("org.jetbrains.exposed:exposed-json:$exposedVersion")
    implementation("org.jetbrains.exposed:exposed-kotlin-datetime:$exposedVersion")
    implementation("org.xerial:sqlite-jdbc:3.41.2.2")

    implementation("io.github.monun:kommand-api:3.1.7")
    implementation("io.github.monun:heartbeat-coroutines:0.0.5")

    implementation("io.typst:bukkit-kotlin-serialization:1.0.0")

    compileOnly("io.papermc.paper:paper-api:$paperVersion-R0.1-SNAPSHOT")

    testImplementation(libs.org.jetbrains.kotlin.kotlin.test)
}

group = "rpg"
version = "1.0-SNAPSHOT"
description = "rpg"
java.sourceCompatibility = JavaVersion.VERSION_17

//publishing {
//    publications.create<MavenPublication>("maven") {
//        from(components["java"])
//    }
//}

tasks.withType<JavaCompile>() {
    options.encoding = "UTF-8"
}

tasks.withType<Javadoc>() {
    options.encoding = "UTF-8"
}

tasks.jar{
    val path = System.getenv("path")
    destinationDirectory = File(path)
}

tasks{
//    jar{
//        dependsOn(processResources)
//    }

    processResources {
        filteringCharset = Charsets.UTF_8.name() // We want UTF-8 for everything
        val props = mapOf(
            "version" to project.version,
            "apiVersion" to "1.20",
            "exposedVersion" to exposedVersion,
            "kotlinVersion" to kotlinVersion,
        )
        inputs.properties(props)
        filesMatching("plugin.yml") {
            expand(props)
        }
    }
}
