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
import org.apache.maven.plugins.annotations.ResolutionScope;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * Goal which touches a timestamp file.
 * <p>
 * //* @deprecated Don't use!
 */
@Mojo(name = "copy-files", defaultPhase = LifecyclePhase.PACKAGE)
public class CopyFilesMojo
        extends AbstractMojo {
    /**
     * Location of the file.
     */
    @Parameter
    List<Copyjob> copyjobs;

    Log log = getLog();

    public void execute()
            throws MojoExecutionException {
        log.info("Copying files");
        if (copyjobs!=null && copyjobs.size()!=0)
        {  log.info(copyjobs.size() + " copyjobs found");
            for (Copyjob job : copyjobs)
            {
                try {
                    job.execute();
                } catch (IOException e) {
                    log.info(String.format("Copy FAILED: %s -> %s",job.getSource().getPath(),job.getTarget().getAbsolutePath()));

                    throw new MojoExecutionException(e.getMessage());
                }
                log.info(String.format("Copied %s -> %s",job.getSource().getPath(),job.getTarget().getAbsolutePath()));
            }

        }
        else { log.info("No 'copyjobs' found");
        }
    }
}
