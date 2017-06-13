package com.coolworx.mavenplugins.mojos.concat;

import org.apache.maven.plugins.annotations.Parameter;
import org.codehaus.plexus.util.FileUtils;

import java.io.*;
import java.util.List;

/**
 * Created by morti on 6/13/17.
 */
public class Job {
    private byte[] buffer = new byte[4096];

    @Parameter(required = true)
    private List<File> sources;

    @Parameter(required = true)
    private File targetFile;

    public List<File> getSources() {
        return sources;
    }

    public void setSources(List<File> sources) {
        this.sources = sources;
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


    private void checkTargetDir() throws IOException {
        File tDir = targetFile.getParentFile();
        if (!tDir.exists())
            FileUtils.forceMkdir(tDir);
        if (tDir.exists() && tDir.isDirectory()) return;
        throw new IOException(tDir.getAbsolutePath() + " not exists and can not be created");
    }

    public void execute() throws IOException {
        checkTargetDir();
        FileOutputStream fos = new FileOutputStream(getTargetFile());
        int readBytes = 0;
        for (File source : sources) {
            FileInputStream in = new FileInputStream(source);

            while ((readBytes = in.read(buffer)) != -1) {
                fos.write(buffer, 0, readBytes);
            }
            in.close();
        }
        fos.close();
    }
}
