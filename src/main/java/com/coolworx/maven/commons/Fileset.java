package com.coolworx.maven.commons;

import org.apache.maven.plugins.annotations.Parameter;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by morti on 6/14/17.
 */
public class Fileset {

    @Parameter(defaultValue = "${project.basedir}", property = "basedir")
    File baseDir;

    @Parameter
    String[] includes;

    @Parameter
    String[] excludes;

    public File getBaseDir() {
        return baseDir;
    }

    public String[] getIncludes() {
        return includes;
    }

    public String[] getExcludes() {
        return excludes;
    }

    public List<File> scanFiles() throws IOException {
        setDefaults();
        return new SourceScanner(baseDir, includes, excludes).getFiles();

    }

    private void setDefaults() throws IOException {
        if (baseDir==null) baseDir = new File(".").getCanonicalFile();
        if (includes == null || includes.length == 0) {
            includes = new String[]{"*"};
        }

    }
}
