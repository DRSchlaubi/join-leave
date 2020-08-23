import org.apache.tools.ant.filters.ReplaceTokens

plugins {
    java
}

group = "org.example"
version = "1.0-SNAPSHOT"

/**
 * Location of developers plugins directory in gradle.properties.
 */
val spigotPluginsDir: String? by project

repositories {
    mavenCentral()
    maven("https://hub.spigotmc.org/nexus/content/repositories/snapshots")
}

dependencies {
    compileOnly("org.jetbrains", "annotations", "19.0.0")
    compileOnly("org.spigotmc", "spigot-api", "1.16.1-R0.1-SNAPSHOT")
}

tasks {
    processResources {
        from(sourceSets["main"].resources) {
            val tokens = mapOf("version" to version)
            filter(ReplaceTokens::class, mapOf("tokens" to tokens))
        }
    }


    // This allows you to install your plugin using gradle installPlugin
    task<Copy>("installPlugin") {
        dependsOn(jar)
        from(jar)
        include("*.jar")
        into(spigotPluginsDir)
    }
}
