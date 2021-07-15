package com.github.elunmuks.BTS;

import java.util.ArrayList;
import java.util.Comparator;

// class of the main algorithm of the program
public class Kruskal implements Runnable {
    public Graph graph;
    public ArrayList<AlgorithmSteps> algorithmSteps;
    public int currentStep;
    public Kruskal() {
        graph = new Graph();
        algorithmSteps = new ArrayList<>();
    }
    // sorting the vertices of the graph by their weight
    protected void sortEdges() { graph.graphEdges.sort(Comparator.comparingDouble((o1) -> (o1.weight))); }

    private int getGraphConnectedVerticesCount(boolean[] isVisited, int vertexNumber, Graph graph1) {
        int result = 0;
        for (GraphEdges graphEdges : graph1.graphVertices.get(vertexNumber).vertexEdges) {
            int number = graphEdges.getSecondVertexNumber(vertexNumber);
            if (!isVisited[number]) {
                isVisited[number] = true;
                result += getGraphConnectedVerticesCount(isVisited, number, graph1);
            }
        }
        return result > 0 ? (result + 1) : 1;
    }
    protected boolean isGraphConnected(Graph graph1) {
        boolean[] isVisited = new boolean[graph1.graphVertices.size()];
        if (isVisited.length == 0)
            return true;
        isVisited[0] = true;
        return getGraphConnectedVerticesCount(isVisited, 0, graph1) == graph1.graphVertices.size();
    }
    private boolean isCyclicGraphRec(boolean[] isVisited, int vertexNumber, int previousVertexNumber, Graph graph1) {
        for (GraphEdges graphEdges : graph1.graphVertices.get(vertexNumber).vertexEdges) {
            int number = graphEdges.getSecondVertexNumber(vertexNumber);
            if (number != previousVertexNumber) {
                if (!isVisited[number]) {
                    isVisited[number] = true;
                    if (isCyclicGraphRec(isVisited, number, vertexNumber, graph1)) return true;
                } else {
                    return true;
                }
            }
        }
        return false;
    }
    protected boolean isCyclicGraph(Graph graph1) {
        boolean[] isVisited = new boolean[graph1.graphVertices.size()];

        for (int i = 0; i < isVisited.length; i++) {
            if (!isVisited[i]) {
                isVisited[i] = true;
                if (isCyclicGraphRec(isVisited, i, -1, graph1)) return true;
            }
        }
        return false;
    }
    @Override
    public void run() {
        Graph spanningTree = new Graph();
        if (graph.graphVertices.size() == 0)
            throw new RuntimeException("Graph is empty.");
        if (graph.graphVertices.size() == 1)
            return;
        sortEdges();
        if (!isGraphConnected(graph))
            throw new RuntimeException("Graph is not connected.");
        for (int i = 0; i < graph.graphVertices.size(); i++)
            spanningTree.addVertex(new GraphVertices());
        while (currentStep < graph.graphEdges.size()) {
            spanningTree.addEdges(graph.graphEdges.get(currentStep));
            if (isCyclicGraph(spanningTree)) {
                algorithmSteps.add(new AlgorithmSteps(graph.graphEdges.get(currentStep), false));
                spanningTree.delEdges(graph.graphEdges.get(currentStep));
            } else {
                algorithmSteps.add(new AlgorithmSteps(graph.graphEdges.get(currentStep), true));
            }
            currentStep++;
            if (isGraphConnected(spanningTree)) {
                currentStep = 0;
                graph = spanningTree;
                return;
            }
        }
        currentStep = 0;
        graph = spanningTree;
    }
    public void stepForward() {
        if (currentStep < algorithmSteps.size())
            currentStep++;
    }
    public void stepBack() {
        if (currentStep > 0)
            currentStep--;
    }
}
