package com.coolworx.maven.plugins.mojos;

import com.coolworx.maven.commons.OneToOneJob;
import org.codehaus.plexus.util.FileUtils;

import java.io.IOException;

/**
 * Created by morti on 6/13/17.
 */
public class Move extends OneToOneJob {
        public void doJob() throws IOException {
            FileUtils.rename(getSource(),getTarget());
        }
}
