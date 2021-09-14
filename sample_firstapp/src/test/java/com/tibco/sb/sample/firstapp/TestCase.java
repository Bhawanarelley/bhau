package com.tibco.sb.sample.firstapp;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.streambase.sb.unittest.Expecter;
import com.streambase.sb.unittest.JSONSingleQuotesTupleMaker;
import com.streambase.sb.unittest.SBServerManager;
import com.streambase.sb.unittest.ServerManagerFactory;

/**
 * Example test case
 */
public class TestCase {

    private static SBServerManager server;

    /**
     * Set up the server
     * @throws Exception Initialization failed
     */
    @BeforeClass
    public static void setupServer() throws Exception {
        // create a StreamBase server and load applications once for all tests in this class
        server = ServerManagerFactory.getEmbeddedServer();
        server.startServer();
        server.loadApp("com.tibco.sb.sample.firstapp.firstapp");
    }

    /**
     * Stop the server
     * @throws Exception Shutdown failed
     */
    @AfterClass
    public static void stopServer() throws Exception {
        if (server != null) {
            server.shutdownServer();
            server = null;
        }
    }

    /**
     * Start the containers
     * @throws Exception Container startup error
     */
    @Before
    public void startContainers() throws Exception {
        // before each test, startup fresh container instances
        server.startContainers();
    }

    /**
     * Test big trade
     * @throws Exception Test failure
     */
    @Test
    public void testBigTrade() throws Exception {
        // JSONSingleQuotesTupleMaker converts a JSON string with single quote to a tuple,
        // and this tuple is fed into TradesIn input.
        server.getEnqueuer("TradesIn").enqueue(
            JSONSingleQuotesTupleMaker.MAKER,
            "{'symbol':'FOO','quantity':10000}"
        );

        // Expect this tuple from BigTrades output.
        new Expecter(server.getDequeuer("BigTrades")).expect(
            JSONSingleQuotesTupleMaker.MAKER,
            "{'symbol':'FOO','quantity':10000}"
        );

        // Expect nothing from AllTheRest output.
        new Expecter(server.getDequeuer("AllTheRest")).expectNothing();
    }

    /**
     * Test all the rest
     * @throws Exception Test failure
     */
    @Test
    public void testAllTheRest() throws Exception {
        server.getEnqueuer("TradesIn").enqueue(
            JSONSingleQuotesTupleMaker.MAKER,
            "{'symbol':'FOO','quantity':9999}"
        );

        new Expecter(server.getDequeuer("AllTheRest")).expect(
            JSONSingleQuotesTupleMaker.MAKER,
            "{'symbol':'FOO','quantity':9999}"
        );

        new Expecter(server.getDequeuer("BigTrades")).expectNothing();
    }

    /**
     * Stop containers
     * @throws Exception Container stop failed
     */
    @After
    public void stopContainers() throws Exception {
        // after each test, dispose of the container instances
        server.stopContainers();
    }
}
