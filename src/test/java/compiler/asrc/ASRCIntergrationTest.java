package compiler.asrc;

import compiler.graphloader.Importer;
import compiler.prolog.TuProlog;
import org.graphstream.graph.Graph;
import org.junit.Test;
import utils.FileUtils;

import static compiler.asrc.GraphRuleTests.generateGraphProlog;
import static compiler.asrc.GraphRuleTests.graphTest;

public final class ASRCIntergrationTest {
    @Test
    public void intergrationTestDOT() throws Exception {
        Graph graph = Importer.graphFromFile(FileUtils.fromResources("asrc_testgraphs/graph3.dot"));
        TuProlog prolog = generateGraphProlog(graph);
        graphTest(prolog, graph);
    }

    @Test
    public void intergrationTestGXL() throws Exception {
        Graph graph = Importer.graphFromFile(FileUtils.fromResources("gxl/test.gxl"));
        TuProlog prolog = generateGraphProlog(graph);
        graphTest(prolog, graph);
    }

    @Test
    public void intergrationTestGST() throws Exception {
        Graph graph = Importer.graphFromFile(FileUtils.fromResources("gxl/tictactoe.gst"));
        TuProlog prolog = generateGraphProlog(graph);
        graphTest(prolog, graph);
    }

    @Test
    public void intergrationTestGPR() throws Exception {
        Graph graph = Importer.graphFromFile(FileUtils.fromResources("groovegraphs/tictactoe.gps/move.gpr"));
        TuProlog prolog = generateGraphProlog(graph);
        graphTest(prolog, graph);
    }

    @Test
    public void intergrationTestDGS() throws Exception {
        Graph graph = Importer.graphFromFile(FileUtils.fromResources("dgs/graph1.dgs"));
        TuProlog prolog = generateGraphProlog(graph);
        graphTest(prolog, graph);
    }
}