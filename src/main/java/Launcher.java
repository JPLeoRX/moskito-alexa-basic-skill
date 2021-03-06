/**
    Copyright 2014-2015 Amazon.com, Inc. or its affiliates. All Rights Reserved.

    Licensed under the Apache License, Version 2.0 (the "License"). You may not use this file except in compliance with the License. A copy of the License is located at

        http://aws.amazon.com/apache2.0/

    or in the "license" file accompanying this file. This file is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
 */

import com.amazon.speech.speechlet.Speechlet;
import com.amazon.speech.speechlet.SpeechletV2;
import com.amazon.speech.speechlet.servlet.SpeechletServlet;
import moskito.MoskitoSpeechletV2;
import org.apache.log4j.PropertyConfigurator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

/*
 *
 * RUN THIS USING
 * !!!!!!!!!!
 * bst proxy http 9999
 * mvn exec:java -Dexec.executable=java -DdisableRequestSignatureCheck=true -Dexec.args=$@
 *
 * https://happy-undefined-8TaW13.bespoken.link/moskito
 *
 * https://adorable-francis.bespoken.link
 *
 * !!!!!!!!!!
 */

/**
 * Shared launcher for executing all sample skills within a single servlet container.
 */
public final class Launcher {
    private static final Logger LOGGER = LogManager.getLogger();

    private static final int PORT = 9999;
    private static final String HTTPS_SCHEME = "https";

    /**
     * Main entry point. Starts a Jetty server.
     *
     * @param args ignored.
     * @throws Exception if anything goes wrong.
     */
    public static void main(final String[] args) throws Exception {
        // Configure logging to output to the console with default level of INFO
        PropertyConfigurator.configure(Thread.currentThread().getContextClassLoader().getResource("log4j.properties"));

        Server server = newServer();

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");

        server.setHandler(context);

        context.addServlet(new ServletHolder(createServlet(new MoskitoSpeechletV2())), "/moskito");

        server.start();
        server.join();
    }

    private static SpeechletServlet createServlet(final SpeechletV2 speechletV2) {
        SpeechletServlet servlet = new SpeechletServlet();
        servlet.setSpeechlet(speechletV2);
        return servlet;
    }

    private static SpeechletServlet createServlet(final Speechlet speechlet) {
        SpeechletServlet servlet = new SpeechletServlet();
        servlet.setSpeechlet(speechlet);
        return servlet;
    }

    private static Server newServer() {
        Server server = new Server(PORT);
        return server;
    }
}