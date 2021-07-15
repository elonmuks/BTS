package com.github.elunmuks.BTS;

//
public class GraphEdges {
    public int firstVertex;
    public int secondVertex;
    public double weight;

    public GraphEdges(int firstVertex, int secondVertex, double weight) {
        this.firstVertex = firstVertex;
        this.secondVertex = secondVertex;
        this.weight = weight;
    }
    public boolean isNeededEdgeByVertexNumber(int vertexNumber) {
        return vertexNumber == firstVertex || vertexNumber == secondVertex;
    }
    public int getSecondVertexNumber(int vertexNumber) {
        int res = (vertexNumber == firstVertex) ? secondVertex : (vertexNumber == secondVertex) ? firstVertex : -1;
        if (res == -1)
            throw new RuntimeException("Vertex number does not belong to edge.");
        return res;
    }
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof GraphEdges) {
            return ((GraphEdges) obj).firstVertex == firstVertex &&
                    ((GraphEdges) obj).secondVertex == secondVertex && ((GraphEdges) obj).weight == weight;
        }
        return false;
    }
}
