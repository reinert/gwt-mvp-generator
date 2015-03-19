package com.growbit.tools.gmg;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;

public class GenericFile {

    private final String packageName;
    private final String name;
    private final String extension;
    private final String content;

    public GenericFile(String packageName, String name, String extension, String content) {
        this.packageName = packageName;
        this.name = name;
        this.extension = extension;
        this.content = content;
    }

    public void writeTo(Path directory) throws IOException {
        Path outputDirectory = directory;
        if (!packageName.isEmpty()) {
            for (String packageComponent : packageName.split("\\.")) {
                outputDirectory = outputDirectory.resolve(packageComponent);
            }
            Files.createDirectories(outputDirectory);
        }

        Path outputPath = outputDirectory.resolve(name + '.' + extension);

        try (Writer writer = new OutputStreamWriter(Files.newOutputStream(outputPath))) {
            writer.write(content);
        }
    }

    public void writeTo(Appendable appendable) throws IOException {
        appendable.append(content);
    }
}
