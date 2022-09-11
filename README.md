# AsyncAPI Gradle Plugin

AsyncAPI Gradle plugin that delegates installation and execution of `npm` commands to [`com.github.node-gradle.node`](https://github.com/node-gradle/gradle-node-plugin/tree/3.4.0) Gradle plugin.

This way one doesn't need to care about `node` and `npm` installation within JVM project in order to use [AsyncAPI generator](https://github.com/asyncapi/generator).

## Usage

Configuration:

One can configure following parameters through asyncapi extension:

```groovy
asyncapi {

  // You want a specific version of the generator because your template might not be compatible with the latest 
  // generator. Check what version you need and perform installation, specifying the exact version.
  version = "1.9.8"
  
  // Local path or URL pointing to AsyncAPI specification file
  asyncapi = null

  // Name of the generator template like for example <i>@asyncapi/html-template</i> or 
  // <i>https://github.com/asyncapi/html-template</i>
  template = null

  // Directory where to put the generated files (defaults to current directory)
  output = "build/asyncapi"

  // Output the version number
  printVersion = false

  // Enable more specific errors in the console
  debug = false

  // Force writing of the generated files to given directory even if it is a git repo with unstaged files or not empty 
  // dir (defaults to false)
  forceWrite = false
}
```

It's planned to support all the [AsyncAPI CLI options](https://github.com/asyncapi/generator#cli-usage).

Additionally one can leverage [configuration](https://github.com/node-gradle/gradle-node-plugin/blob/3.4.0/docs/usage.md#configuring-the-plugin) 
of `com.github.node-gradle.node` plugin with `node` extension. For example:

```groovy
node {
  download = false
  version = "16.14.0"
  ...
}
```

Tasks:

| Task                |                       Description                       |  
|---------------------|:-------------------------------------------------------:|
| `generate`          | Generate code based on the configuration form variables | 
| `assemble`          |               Depends on `generate` task                |
| `clean`  |     Basic clean task, will delete `build` directory     |