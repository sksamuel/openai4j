plugins {
   java
   id("maven-publish")
   signing
   kotlin("jvm").apply(false).version("1.9.25")
}

subprojects {
   apply(plugin = "java")

   group = "com.sksamuel.openai4j"
   version = Ci.version

   java {
      targetCompatibility = JavaVersion.VERSION_21
      sourceCompatibility = JavaVersion.VERSION_21
   }

   tasks.test {
      useJUnitPlatform()
      testLogging {
         showExceptions = true
         showStandardStreams = true
         exceptionFormat = org.gradle.api.tasks.testing.logging.TestExceptionFormat.FULL
      }
   }
}

val publications: PublicationContainer = (extensions.getByName("publishing") as PublishingExtension).publications
