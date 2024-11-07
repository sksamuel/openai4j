pluginManagement {
  plugins {
    kotlin("jvm") version "2.0.20"
  }
}

plugins {
  id("org.gradle.toolchains.foojay-resolver-convention") version "0.8.0"
}

rootProject.name = "openai4j"

include(
   ":openai4j-java",
)

enableFeaturePreview("STABLE_CONFIGURATION_CACHE")
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

dependencyResolutionManagement {
   repositories {
      mavenCentral()
      mavenLocal()
      maven("https://oss.sonatype.org/content/repositories/snapshots/")
      maven("https://s01.oss.sonatype.org/content/repositories/snapshots/")
   }
   versionCatalogs {
      create("libs") {
         val vertx = "4.5.10"
         library("vertx-core", "io.vertx:vertx-core:$vertx")
         library("vertx-web-client", "io.vertx:vertx-web-client:$vertx")

      }
   }
}
