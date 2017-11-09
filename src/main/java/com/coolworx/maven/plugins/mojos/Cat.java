package com.coolworx.maven.plugins.mojos;

import com.coolworx.maven.commons.ManyToOneJob;
import org.apache.maven.model.FileSet;
import org.codehaus.plexus.util.FileUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by morti on 6/13/17.
 */
public class Cat extends ManyToOneJob {
    private byte[] buffer = new byte[4096];


    protected void doJob() throws IOException {
        FileOutputStream fos = new FileOutputStream(getTargetFile());
        int readBytes = 0;
        List<File> files = getFiles().scanFiles();
        log.info("Concatenating "+files.size() + " files");
        for (File source : getFiles().scanFiles()) {
            log.info("Concat "+source.getPath());
            FileInputStream in = new FileInputStream(source);

            while ((readBytes = in.read(buffer)) != -1) {
                fos.write(buffer, 0, readBytes);
            }
            in.close();
        }
        fos.close();
        log.info("Result is in "+getTargetFile().getPath());
    }
}
