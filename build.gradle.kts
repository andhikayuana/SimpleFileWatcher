import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.5.10"
    application
}

group = "id.yuana"
version = "1.0.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {

    implementation("com.github.ajalt.clikt:clikt:3.2.0")

    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnit()
}

tasks.withType<KotlinCompile>() {
    kotlinOptions.jvmTarget = "1.8"
}

application {
    mainClass.set("MainKt")
}