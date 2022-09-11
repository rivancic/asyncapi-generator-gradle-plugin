package com.rivancic.asyncapi.gradle;

import org.gradle.api.GradleException;
import org.gradle.api.Plugin;
import org.gradle.api.Project;
import com.github.gradle.node.npm.task.NpxTask;

import java.util.ArrayList;
import java.util.List;

public class AsyncApiGeneratorPlugin implements Plugin<Project> {

  private static final String GENERATE_TASK_NAME = "generate";
  private static final String ASYNCAPI_TASK_GROUP = "asyncapi";
  public static final String ASYNCAPI_GENERATOR_COMMAND = "@asyncapi/generator";
  private static final String ASYNCAPI_EXTENSION_NAME = "asyncapi";

  /**
   * Register {@value #GENERATE_TASK_NAME} task that will generate code out of AsyncAPI specification.
   *
   * @param project The target object
   */
  @Override
  public void apply(Project project) {

    project.getPluginManager().apply("base");
    project.getPluginManager().apply("com.github.node-gradle.node");

    AsyncApiExtension asyncApiExtension = project.getExtensions().create(ASYNCAPI_EXTENSION_NAME, AsyncApiExtension.class);

    project.getTasks().register(GENERATE_TASK_NAME, NpxTask.class, task -> {
      task.setGroup(ASYNCAPI_TASK_GROUP);
      task.getCommand().set(ASYNCAPI_GENERATOR_COMMAND + getAsyncApiVersion(asyncApiExtension));

      List<String> parameters = new ArrayList<>();
      parameters.add(getAsyncApiFile(asyncApiExtension));
      parameters.add(getAsyncApiTemplate(asyncApiExtension));
      parameters.add("-o" + getAsyncApiOutputDirectory(asyncApiExtension));
      if (asyncApiExtension.getPrintVersion().getOrElse(false)) {
        parameters.add("-V");
      }
      if (asyncApiExtension.getDebug().getOrElse(false)) {
        parameters.add("--debug");
      }
      if (asyncApiExtension.getForceWrite().getOrElse(false)) {
        parameters.add("--force-write");
      }
      task.getArgs().addAll(parameters);

      task.getOutputs().dir(getAsyncApiOutputDirectory(asyncApiExtension));
    });
  }

  private static String getAsyncApiVersion(AsyncApiExtension asyncApiExtension) {
    return asyncApiExtension.getVersion().map(v -> "@" + v).getOrElse("");
  }

  private static String getAsyncApiOutputDirectory(AsyncApiExtension asyncApiExtension) {
    return asyncApiExtension.getOutput().orElse("build/asyncapi").get();
  }

  private static String getAsyncApiTemplate(AsyncApiExtension asyncApiExtension) {
    String asyncApiTemplate;
    if (asyncApiExtension.getTemplate().isPresent()) {
      asyncApiTemplate = asyncApiExtension.getTemplate().get();
    } else {
      throw new GradleException("Async API Template has to be provided as asyncApiTemplate property");
    }
    return asyncApiTemplate;
  }

  private static String getAsyncApiFile(AsyncApiExtension asyncApiExtension) {
    String asyncApiFile;
    if (asyncApiExtension.getAsyncapi().isPresent()) {
      asyncApiFile = asyncApiExtension.getAsyncapi().get();
    } else {
      throw new GradleException("Async API File has to be provided as asyncApiFile property");
    }
    return asyncApiFile;
  }
}
