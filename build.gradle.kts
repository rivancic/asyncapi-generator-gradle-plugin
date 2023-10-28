plugins {
  id("com.gradle.plugin-publish") version "1.2.1"
}

java {
  sourceCompatibility = JavaVersion.VERSION_1_8
  targetCompatibility = JavaVersion.VERSION_1_8
}

tasks.wrapper {
  distributionType= Wrapper.DistributionType.ALL
}

repositories {
  maven {
    url = uri("https://plugins.gradle.org/m2/")
  }
}

dependencies {
  implementation("com.github.node-gradle:gradle-node-plugin:7.0.0") {
    because("This plugin enables you to use a lot of Node.js-based technologies as part of your Gradle build without having Node.js installed locally on your system.")
  }
}

gradlePlugin {
  website = "https://github.com/rivancic/asyncapi-generator-gradle-plugin"
  vcsUrl = "https://github.com/rivancic/asyncapi-generator-gradle-plugin"
  plugins {
    create("asyncApi") {
      id = "com.rivancic.asyncapi-gradle-plugin"
      displayName = "AsyncAPI Code Generator"
      description =
        "Plugin that generates code based on AsyncAPI file specification. It wraps original npm AsyncApi generator."
      implementationClass = "com.rivancic.asyncapi.gradle.AsyncApiGeneratorPlugin"
      tags = listOf("asyncapi", "generator")
    }
  }
}