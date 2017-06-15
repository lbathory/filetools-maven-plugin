package com.coolworx.maven.commons;

import org.apache.maven.plugins.annotations.Parameter;
import org.codehaus.plexus.util.FileUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

/**
 * Created by morti on 6/14/17.
 */
public abstract class ManyToOneJob {
   /* @Parameter
    private List<File> sources;
    */
    @Parameter
    private Fileset files;

    @Parameter(required = true)
    private File targetFile;

    public Fileset getFiles() {
        return files;
    }

    public void setFiles(Fileset files) {
        this.files = files;
    }

    public File getTargetFile() {
        return targetFile;
    }

    public void setTargetFile(File targetFile) {
        this.targetFile = targetFile;
    }

    public void checkSource(File source) throws FileNotFoundException {
        if (!source.isFile())
            throw new FileNotFoundException(source.getAbsolutePath());
    }


    protected void checkTargetDir() throws IOException {
        File tDir = targetFile.getParentFile();
        if (!tDir.exists())
            FileUtils.forceMkdir(tDir);
        if (tDir.exists() && tDir.isDirectory()) return;
        throw new IOException(tDir.getAbsolutePath() + " not exists and can not be created");
    }

    public void execute() throws IOException {
        checkTargetDir();
        doJob();
    }

    protected abstract void doJob() throws IOException;
}
