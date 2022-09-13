package com.rivancic.asyncapi.gradle;

import org.gradle.api.provider.MapProperty;
import org.gradle.api.provider.Property;

/**
 * <pre>{@code
 * Usage: ag [options] <asyncapi> <template>
 *
 * Following options aren't yet implemented:
 *   -d, --disable-hook [hooks...]  disable a specific hook type or hooks from given hook type
 *   -i, --install                  installs the template and its dependencies (defaults to false)
 *   -n, --no-overwrite <glob>      glob or path of the file(s) to skip when regenerating
 *   --watch-template               watches the template directory and the AsyncAPI document, and re-generate the files when changes occur. Ignores the output directory. This flag should be used only for template development.
 *   --map-base-url <url:folder>    maps all schema references from base url to local folder
 *   -h, --help                     display help for command
 *   }</pre>
 */
public interface AsyncApiExtension {

    /**
     * You want a specific version of the generator because your template might not be compatible with the latest
     * generator. Check what version you need and perform installation, specifying the exact version.
     */
    Property<String> getVersion();

    /**
     * <b>asyncapi:</b> Local path or URL pointing to AsyncAPI specification file
     */
    Property<String> getAsyncapi();

    /**
     * <b>template:</b> Name of the generator template like for example <i>@asyncapi/html-template</i> or <i><a href="https://github.com/asyncapi/html-template">https://github.com/asyncapi/html-template</a></i>
     */
    Property<String> getTemplate();

    /**
     * <b>-o, --output [outputDir]:</b> directory where to put the generated files (defaults to current directory)
     */
    Property<String> getOutput();

    /**
     * <b>-V, --version:</b> output the version number
     */
    Property<Boolean> getPrintVersion();

    /**
     * <b>--debug:</b> enable more specific errors in the console
     */
    Property<Boolean> getDebug();

    /**
     * <b>--force-write:</b> force writing of the generated files to given directory even if it is a git repo with unstaged files or not empty dir (defaults to false)
     *
     * @return force-write flag value
     */
    Property<Boolean> getForceWrite();

    /**
     * <b>-p, --param [name=value]:</b> additional param to pass to templates
     *
     * @return map of additional parameters
     */
    MapProperty<String, String> getParameters();
}
