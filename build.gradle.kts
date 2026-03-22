plugins {
    kotlin("jvm") version "1.9.21"
    application
    id("org.openjfx.javafxplugin") version "0.0.14"
}

application {
    mainClass.set("MainKt")
}

dependencies {
    implementation(kotlin("stdlib"))

    testImplementation("org.junit.jupiter:junit-jupiter:5.11.2")
    testImplementation("org.junit.jupiter:junit-jupiter-params:5.11.2")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(17)
}
javafx {
    version = "17"
    modules("javafx.controls")
}