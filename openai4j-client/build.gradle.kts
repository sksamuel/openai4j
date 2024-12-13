plugins {
   java
   `maven-publish`
   kotlin("jvm")
}

dependencies {
   implementation(libs.vertx.core)
   implementation(libs.vertx.web.client)

   implementation("com.fasterxml.jackson.core:jackson-databind:2.15.2")
   implementation("com.fasterxml.jackson.core:jackson-core:2.15.2")
   implementation("com.fasterxml.jackson.core:jackson-annotations:2.15.2")

   testImplementation("io.kotest:kotest-runner-junit5:5.5.4")
   testImplementation("io.kotest:kotest-assertions-core:5.5.4")
   testImplementation("io.kotest:kotest-property:5.5.4")
}

kotlin {
   jvmToolchain(21)
}

apply("../publish.gradle.kts")
