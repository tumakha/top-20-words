plugins {
    java
}

group = "com.tumakha"
version = "0.1"

java {
    sourceCompatibility = JavaVersion.VERSION_13
    targetCompatibility = JavaVersion.VERSION_13
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.6.2")
    testImplementation("org.junit.jupiter:junit-jupiter-params")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
    testImplementation("org.assertj:assertj-core:3.16.1")
}

tasks.jar {
    archiveFileName.set("top20words.jar")
}

tasks.test {
    useJUnitPlatform()
    testLogging {
        showStandardStreams = true
        showExceptions = true
        events("passed", "skipped", "failed", "standardOut", "standardError")
    }
}
