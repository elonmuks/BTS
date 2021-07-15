package com.github.elunmuks.BTS;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.*;

class GraphTest {
    Graph graph = new Graph();
    @Test
    void addVertex() {
        GraphVertices graphVertex = new GraphVertices();
        graph.addVertex(graphVertex);
        assertEquals(graphVertex, graph.graphVertices.get(0));
    }

    @Test
    void addEdges() {
        Graph graph = new Graph();
        graph.addVertex(new GraphVertices());
        graph.addVertex(new GraphVertices());
        GraphEdges graphEdge = new GraphEdges(1, 0, 10);
        graph.addEdges(graphEdge);
        assertEquals(graphEdge, graph.graphEdges.get(0));
        assertEquals(graphEdge, graph.graphVertices.get(0).vertexEdges.get(0));
        assertEquals(graphEdge, graph.graphVertices.get(1).vertexEdges.get(0));
    }

    @Test
    void delEdges() {
        Graph graph = new Graph();

        for (int i = 0; i < 4; i++) {
            graph.addVertex(new GraphVertices());
        }
        graph.addEdges(new GraphEdges(0, 1, 13.0));
        graph.addEdges(new GraphEdges(0, 2, 11.5));
        graph.addEdges(new GraphEdges(0, 3, 22.1));
        graph.addEdges(new GraphEdges(2, 3, 1.2));

        GraphEdges graphEdge = graph.graphEdges.get(1);
        graph.delEdges(graphEdge);
        assertFalse(graph.graphEdges.contains(graphEdge));
        assertFalse(graph.graphVertices.get(0).vertexEdges.contains(graphEdge));
        assertFalse(graph.graphVertices.get(2).vertexEdges.contains(graphEdge));
    }

    @Test
    void delVertex() {
        Graph graph = new Graph();
        for (int i = 0; i < 4; i++) {
            graph.addVertex(new GraphVertices());
        }
        graph.addEdges(new GraphEdges(0, 1, 13.0));
        graph.addEdges(new GraphEdges(0, 2, 11.5));
        graph.addEdges(new GraphEdges(0, 3, 22.1));
        graph.addEdges(new GraphEdges(2, 3, 1.2));
        GraphVertices graphVertex = graph.graphVertices.get(0);
        GraphEdges graphEdge0 = graph.graphVertices.get(0).vertexEdges.get(0);
        GraphEdges graphEdge1 = graph.graphVertices.get(0).vertexEdges.get(1);
        GraphEdges graphEdge2 = graph.graphVertices.get(0).vertexEdges.get(2);
        graph.delVertex(graphVertex);
        assertFalse(graph.graphVertices.contains(graphVertex));
        assertFalse(graph.graphEdges.contains(graphEdge0));
        assertFalse(graph.graphEdges.contains(graphEdge1));
        assertFalse(graph.graphEdges.contains(graphEdge2));
    }
}