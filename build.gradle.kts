plugins {
    java
    id("fabric-loom") version "1.2.18"
    `maven-publish`
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}

val minecraftVersion = "1.21.1"
val yarnMappings = "1.21.1+build.1"
val loaderVersion = "0.14.11"
val fabricApiVersion = "1.0.0+1.21.1"

repositories {
    mavenCentral()
    maven { url = uri("https://maven.fabricmc.net/") }
}

dependencies {
    minecraft("com.mojang:minecraft:$minecraftVersion")
    mappings("net.fabricmc:yarn:$yarnMappings:v2")
    modImplementation("net.fabricmc:fabric-api:$fabricApiVersion")
}

loom {
    accessWidenerPath.set(file("src/main/resources/modid.accesswidener"))
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            from(components["java"])
        }
    }
}
