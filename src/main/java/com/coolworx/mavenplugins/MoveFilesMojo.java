package com.coolworx.mavenplugins;

/*
 * Copyright 2001-2005 The Apache Software Foundation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.logging.Log;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

import java.io.IOException;
import java.util.List;

/**
 * Goal which touches a timestamp file.
 * <p>
 * //* @deprecated Don't use!
 */
@Mojo(name = "move-files", defaultPhase = LifecyclePhase.PACKAGE)
public class MoveFilesMojo
        extends AbstractMojo {
    /**
     * Location of the file.
     */
    @Parameter
    List<Movejob> movejobs;

    Log log = getLog();

    public void execute()
            throws MojoExecutionException {
        log.info("Moving files: ");


        if (movejobs != null && movejobs.size() != 0) {
            log.info(movejobs.size() + " movejobs found");
            for (Movejob job : movejobs) {
                try {
                    job.execute();
                } catch (IOException e) {
                    log.error(String.format("Move FAILED: %s -> %s", job.getSource().getPath(), job.getTargetDir().getPath()));
                    throw new MojoExecutionException(e.getMessage());
                }
                log.info(String.format("Moved %s -> %s", job.getSource().getPath(), job.getTargetDir().getPath()));
            }
        } else {
            log.info("No 'movejobs' found");
        }
    }
}
