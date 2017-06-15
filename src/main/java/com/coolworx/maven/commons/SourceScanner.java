package com.coolworx.maven.commons;

import org.codehaus.plexus.util.DirectoryScanner;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by morti on 6/14/17.
 */
public class SourceScanner {
    DirectoryScanner scanner = new DirectoryScanner();

    public SourceScanner(File baseDir,String[] includes, String[] excludes ) {
        scanner.setBasedir(baseDir);
        scanner.setIncludes(includes);
        scanner.setExcludes(excludes);
        scanner.scan();
    }

    public List<File> getFiles() throws IOException {
        List<File> result = new ArrayList<File>();
        for (String fileName : scanner.getIncludedFiles()) {
            result.add(new File(scanner.getBasedir(), fileName).getCanonicalFile());
        }
        return result;
    }
}
