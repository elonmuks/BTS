package com.github.elunmuks.BTS;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.*;

class KruskalTest extends Kruskal{
    Kruskal kruskal = new Kruskal();

    @Test
    void testSortEdges() {
        kruskal.graph.graphEdges.add(new GraphEdges(1, 2, 10));
        kruskal.graph.graphEdges.add(new GraphEdges(2, 3, 15));
        kruskal.graph.graphEdges.add(new GraphEdges(3, 4, 13));
        kruskal.sortEdges();
        assertEquals(10.0, kruskal.graph.graphEdges.get(0).weight);
        assertEquals(13.0, kruskal.graph.graphEdges.get(1).weight);
        assertEquals(15.0, kruskal.graph.graphEdges.get(2).weight);
    }

    @Test
    void testIsGraphConnected() throws IOException, URISyntaxException {
        Graph graph = new Graph();

        for (int i = 0; i < 4; i++) {
            graph.addVertex(new GraphVertices());
        }
        graph.addEdges(new GraphEdges(0, 1, 13.0));
        graph.addEdges(new GraphEdges(0, 2, 11.5));
        graph.addEdges(new GraphEdges(0, 3, 22.1));
        graph.addEdges(new GraphEdges(2, 3, 1.2));
        assertTrue(isGraphConnected(graph));
        graph.delEdges(graph.graphEdges.get(0));
        assertFalse(isGraphConnected(graph));
    }

    @Test
    void testIsCyclicGraph() throws IOException, URISyntaxException {
        Graph graph = new Graph();

        for (int i = 0; i < 4; i++) {
            graph.addVertex(new GraphVertices());
        }
        graph.addEdges(new GraphEdges(0, 1, 13.0));
        graph.addEdges(new GraphEdges(0, 2, 11.5));
        graph.addEdges(new GraphEdges(0, 3, 22.1));
        graph.addEdges(new GraphEdges(2, 3, 1.2));
        assertTrue(isCyclicGraph(graph));
        graph.delEdges(graph.graphEdges.get(1));
        assertFalse(isCyclicGraph(graph));
    }

    @Test
    void testRun() throws IOException, URISyntaxException {
        kruskal.graph.graphFromFile();
        kruskal.run();
        assertFalse(kruskal.isCyclicGraph(kruskal.graph));
        assertTrue(kruskal.isGraphConnected(kruskal.graph));
    }
}