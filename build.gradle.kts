import com.github.gradle.node.npm.task.NpxTask

plugins {
    base
    id("com.github.node-gradle.node") version "3.4.0"
}

val downloadNode = true
val asyncApiFile = "https://bit.ly/asyncapi"
val asyncApiTemplate = "@asyncapi/html-template"
val asyncApiOutputDir = "build/dist"

node {
    download.set(downloadNode)
}

val generate by tasks.registering(NpxTask::class) {
    group = "asyncapi"
    command.set("@asyncapi/generator")
    args.addAll(asyncApiFile, asyncApiTemplate)
    if(asyncApiOutputDir.isNotEmpty()) {
        args.add("-o${asyncApiOutputDir}")
        outputs.dir(asyncApiOutputDir)
    }
}

tasks.assemble {
    dependsOn(generate)
}