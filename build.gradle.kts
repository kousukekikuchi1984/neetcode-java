plugins {
    id("java")
    id("application")
}

group = "dev.kou2kkkt"
version = "1.0-SNAPSHOT"

application {
    mainClass.set("dev.kou2kkkt.Main")
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(20))
    }
}

tasks.test {
    useJUnitPlatform()
}