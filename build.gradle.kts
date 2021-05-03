plugins {
    java
    `maven-publish`
}

group = "io.github.eutro"
version = "0.1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = sourceCompatibility
}

dependencies {
    implementation("com.google.code.gson:gson:2.8.6")
    implementation("com.google.http-client:google-http-client:1.39.2")
    implementation("com.google.http-client:google-http-client-gson:1.39.2")
}

tasks.register<Jar>("sourceJar") {
    archiveClassifier.set("sources")
    from(sourceSets.main.get().allSource)
}

publishing {
    publications {
        register<MavenPublication>("maven") {
            from(components.named("java").get())
            artifact(tasks["sourceJar"])
        }
    }
}
