package com.coolworx.maven.plugins.mojos;

import com.coolworx.maven.commons.ManyToOneJob;
import org.apache.commons.codec.digest.DigestUtils;

import java.io.*;
import java.util.List;

/**
 * Created by morti on 6/15/17.
 */
public class Checksum extends ManyToOneJob {

    protected void doJob() throws IOException {
        List<File> files = getFiles().scanFiles();
        PrintWriter pw = new PrintWriter(new FileWriter(getTargetFile()));
        int indent = getIndent(files);
        for (File file : files) {
            String checksum = getChecksum(file);
            pw.printf("%-"+indent+"s  %s\n", file.getName(), checksum);
        }
        pw.close();
    }

    private String getChecksum(File file) throws IOException {
        FileInputStream fin = new FileInputStream(file);
        String result = DigestUtils.md5Hex(fin);
        fin.close();
        return result;
    }

    private int getIndent(List<File> files) {
        int indent = 0;
        for (File file : files) {
            if (file.getName().length() > indent)
                indent = file.getName().length();
        }
        indent += 4;
        return indent;
    }


}
