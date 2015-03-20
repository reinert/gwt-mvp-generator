package com.growbit.tools.gmg;

import java.nio.file.Path;
import java.nio.file.Paths;

import com.martiansoftware.jsap.FlaggedOption;
import com.martiansoftware.jsap.JSAP;
import com.martiansoftware.jsap.JSAPResult;
import com.martiansoftware.jsap.Parameter;
import com.martiansoftware.jsap.SimpleJSAP;
import com.martiansoftware.jsap.UnflaggedOption;

public final class Generator {

    private Generator() {
    }

    public static void main(String[] args) throws Exception {
        final SimpleJSAP jsap = new SimpleJSAP(
                "gmg",
                "GWT MVP Generator - Generates mvp classes from growbit's archetype for a new app area",
                new Parameter[] {
                        new FlaggedOption("directory", JSAP.STRING_PARSER, JSAP.NO_DEFAULT, JSAP.NOT_REQUIRED, 'd',
                                "directory", "The root directory from which the classes will be generated"
                                        + " (does not include the package structure)."),
                        new FlaggedOption("package", JSAP.STRING_PARSER, JSAP.NO_DEFAULT, JSAP.REQUIRED, 'p', "package",
                                "The package of the classes. It's directory structure will be created if not exists."),
                        new UnflaggedOption("name", JSAP.STRING_PARSER, JSAP.NO_DEFAULT, JSAP.REQUIRED, JSAP.NOT_GREEDY,
                                "The name of the area or entity to which the mvp classes refer."
                                        + " It will be used as a prefix for all generated classes.")
                }
        );

        final JSAPResult config = jsap.parse(args);
        if (jsap.messagePrinted()) {
            System.out.println(jsap.getHelp());
            System.exit(1);
        }

        final String name = config.getString("name");
        final String pack = config.getString("package");
        final String directory = config.contains("directory")
                ? config.getString("directory") : System.getProperty("user.dir");

        final Path outputPath = Paths.get(directory);
        final Package p = new Package(pack);
        final Entity e = new Entity(name);

        final ViewInterface vi = new ViewInterface(p, e);
        final ViewImplClass vic = new ViewImplClass(vi);
        final ViewImplUiXml viux = new ViewImplUiXml(vic);
        final PresenterClass pc = new PresenterClass(vi);

        // Print to System.out
//        vi.asJavaFile().writeTo(System.out);
//        vic.asJavaFile().writeTo(System.out);
//        System.out.println(viux.asGenericFile());
//        pc.asJavaFile().writeTo(System.out);

        // Write to path
        vi.asJavaFile().writeTo(outputPath);
        vic.asJavaFile().writeTo(outputPath);
        viux.asGenericFile().writeTo(outputPath);
        pc.asJavaFile().writeTo(outputPath);
    }
}
