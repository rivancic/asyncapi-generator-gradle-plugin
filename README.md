# AsyncAPI Gradle Plugin

AsyncAPI Gradle plugin that delegates installation and execution of `npm` commands to [`com.github.node-gradle.node`](https://github.com/node-gradle/gradle-node-plugin/tree/3.4.0) Gradle plugin.

This way one doesn't need to care about `node` and `npm` installation within JVM project in order to use [AsyncAPI generator](https://github.com/asyncapi/generator).

## Usage

To use the async generator in you Gradle build, apply the plugin:

```kotlin
plugins {
  id("com.rivancic.asyncapi-gradle-plugin") version "0.2.0"
}
```

Plugin is available on [Gradle Plugin Portal under com.rivancic.asyncapi-gradle-plugin](https://plugins.gradle.org/plugin/com.rivancic.asyncapi-gradle-plugin).


### Configuration

One can configure following parameters through asyncapi extension:

`asyncapi` and `template` parameters are required to be set.

```kotlin
asyncapi {

  // You want a specific version of the generator because your template might not be compatible with the latest 
  // generator. Check what version you need and perform installation, specifying the exact version.
  version.set("1.14.1")
  
  // Local path or URL pointing to AsyncAPI specification file
  asyncapi.set(null)

  // Name of the generator template like for example <i>@asyncapi/html-template</i> or 
  // <i>https://github.com/asyncapi/html-template</i>
  template.set(null)

  // Directory where to put the generated files (defaults to current directory)
  output.set("build/asyncapi")

  // Output the version number
  printVersion.set(false)

  // Enable more specific errors in the console
  debug.set(false)

  // Force writing of the generated files to given directory even if it is a git repo with unstaged files or not empty 
  // dir (defaults to false)
  forceWrite.set(false)

  // Additional parameters that will be passed to templates. Have to be provided as a map.
  parameters.set(mapOf<String, String>())
}
```

It's planned to support all the [AsyncAPI CLI options](https://github.com/asyncapi/generator#cli-usage).

Additionally one can leverage [configuration](https://github.com/node-gradle/gradle-node-plugin/blob/3.4.0/docs/usage.md#configuring-the-plugin) 
of `com.github.node-gradle.node` plugin with `node` extension. For example:

```kotlin
node {
  download.set(true)
  version.set("21.1.0")
  ...
}
```

Tasks:

| Task               |                       Description                       |  
|--------------------|:-------------------------------------------------------:|
| `asyncApiGenerate` | Generate code based on the configuration form variables | 
| `assemble`         |         Depends on `asyncApiGenerate` task              |
| `clean`            |     Basic clean task, will delete `build` directory     |
