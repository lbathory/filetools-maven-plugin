package com.coolworx.maven.plugins.mojos;

import com.coolworx.maven.commons.OneToOneJob;
import org.codehaus.plexus.util.FileUtils;

import java.io.IOException;

/**
 * Created by morti on 6/13/17.
 */
public class Copy extends OneToOneJob {

    @Override
    protected void doJob() throws IOException {

        FileUtils.copyFile(getSource(),getTarget());
    }
}
