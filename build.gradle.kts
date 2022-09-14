plugins {
  id("com.gradle.plugin-publish") version "1.0.0"
}

java {
  sourceCompatibility = JavaVersion.VERSION_1_8
  targetCompatibility = JavaVersion.VERSION_1_8
}

repositories {
  maven {
    url = uri("https://plugins.gradle.org/m2/")
  }
}

dependencies {
  implementation("com.github.node-gradle:gradle-node-plugin:3.4.0")
}

gradlePlugin {
  plugins {
    create("asyncApi") {
      id = "com.rivancic.asyncapi-gradle-plugin"
      displayName = "AsyncAPI Code Generator"
      description =
        "Plugin that generates code based on AsyncAPI file specification. It wraps original npm AsyncApi generator."
      implementationClass = "com.rivancic.asyncapi.gradle.AsyncApiGeneratorPlugin"
    }
  }
}

pluginBundle {
  website = "https://github.com/rivancic/asyncapi-generator-gradle-plugin"
  vcsUrl = "https://github.com/rivancic/asyncapi-generator-gradle-plugin"
  tags = listOf("asyncapi", "generator")
}