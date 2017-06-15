package com.coolworx.maven.commons;

import org.junit.Test;

import java.io.File;

import static org.junit.Assert.*;

/**
 * Created by morti on 6/14/17.
 */
public class SourceScannerTest {

    @Test
    public void getFiles() throws Exception {
        SourceScanner scanner = new SourceScanner(new File("."), new String[]{"**/pom.xml", "**/*.sample"}, new String[]{"**/*pre*"});
        for (File file : scanner.getFiles()) {
            System.out.println(file.getAbsolutePath());
        }
    }

}