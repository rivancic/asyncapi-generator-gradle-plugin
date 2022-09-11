# Gradle Script for AsyncAPI code generation

`build.gradle.kts` helper script that delegates installation and execution of `npm` commands to [`com.github.node-gradle.node`](https://github.com/node-gradle/gradle-node-plugin/tree/3.4.0) Gradle plugin.

This way one doesn't need to care about `node` and `npm` installation within JVM project in order to use [AsyncAPI generator](https://github.com/asyncapi/generator).

## Usage

Configuration:

One can configure following variables

| Variable |  Description  |  
|----------|:-------------:|
| `downloadNode` | If true will do local node installation and won't use globally installed one.  | 
| `asyncApiFile` |   Path or URL to AsyncAPI definition    |
| `asyncApiTemplate`| What AsyncAPI template should be generated |
| `asyncApiOutputDir`| Destination directory for generated AsyncAPI code |

Tasks:

| Task                |                       Description                       |  
|---------------------|:-------------------------------------------------------:|
| `generate`          | Generate code based on the configuration form variables | 
| `assemble`          |               Depends on `generate` task                |
| `clean`  |     Basic clean task, will delete `build` directory     |

## Consideration

This is a helper script. As next step this logic should be extracted to configurable standalone task in Gradle `buildSrc`.
As a final solution Standalone Gradle Plugin should be implemented and published to Gradle Plugin Portal.