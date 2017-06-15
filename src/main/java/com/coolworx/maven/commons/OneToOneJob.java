package com.coolworx.maven.commons;

import org.codehaus.plexus.util.FileUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by morti on 6/13/17.
 */
public abstract class OneToOneJob {

    private File source;
    private File targetDir;
    private String targetName;

    public File getSource() {
        return source;
    }

    public void setSource(File source) {
        this.source = source;
    }

    public File getTargetDir() {
        return targetDir;
    }

    public void setTargetDir(File targetDir) {
        this.targetDir = targetDir;
    }

    public String getTargetName() {
        return targetName;
    }

    public void setTargetName(String targetName) {
        this.targetName = targetName;
    }

    public void checkSource() throws FileNotFoundException {
        if (!source.isFile())
            throw new FileNotFoundException(source.getAbsolutePath());
    }

    public File getTarget() {
        File parentDir = targetDir != null ? targetDir : source.getParentFile();
        return targetName == null ?
                new File(parentDir, source.getName()) :
                new File(parentDir, targetName);
    }

    private void checkTargetDir() throws IOException {
        File tDir = getTarget().getParentFile();
        if (!tDir.exists())
            FileUtils.forceMkdir(tDir);
        if (tDir.exists() && tDir.isDirectory()) return;
        throw new IOException(tDir.getAbsolutePath() + " not exists and can not be created");
    }

    protected abstract void doJob() throws IOException;

    public void execute() throws IOException {
        checkTargetDir();
        doJob();
    }
}
