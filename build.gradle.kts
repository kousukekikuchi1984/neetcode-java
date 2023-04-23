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
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.7.1")
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(20))
    }
}

tasks.test {
    useJUnitPlatform()
}

sourceSets {
    main {
        java {
            srcDirs("src/main/java/dev/kou2kkkt")
        }
    }
    test {
        java {
            srcDirs("src/test/java/dev/kou2kkkt")
        }
    }
}