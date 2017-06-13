package com.coolworx.mavenplugins;

import org.codehaus.plexus.util.FileUtils;

import java.io.IOException;

/**
 * Created by morti on 6/13/17.
 */
public class Copyjob extends RelocateJob {

    @Override
    protected void doJob() throws IOException {

        FileUtils.copyFile(getSource(),getTarget());
    }
}
