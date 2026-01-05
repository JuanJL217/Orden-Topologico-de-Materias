package Grafo;

import Materia.Materia;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Grafo<T> {

    private HashMap<Integer, T> repositoryNode;
    private HashMap<Integer, ArrayList<Integer>> grafo;

    private boolean isDirected;

    public Grafo(boolean _isDirected) {
        this.repositoryNode = new HashMap<>();
        this.grafo = new HashMap<>();
        this.isDirected = _isDirected;
    }


    public boolean addNode(Integer nodeId, T node) {
        if (grafo.containsKey(nodeId)) {
            return false;
        }
        grafo.put(nodeId, new ArrayList<>());
        repositoryNode.put(nodeId, node);
        return true;
    }

    public T getNode(Integer id) {
        if(!repositoryNode.containsKey(id)) {
            return null;
        }
        return this.repositoryNode.get(id);
    }

    public boolean addEdge(Integer nodeId_A, Integer nodeId_B) {
        if (!repositoryNode.containsKey(nodeId_A) || !repositoryNode.containsKey(nodeId_B)) {
            return false;
        }

        this.grafo.get(nodeId_A).add(nodeId_B);

        if (!this.isDirected) {
            grafo.get(nodeId_B).add(nodeId_A);
        }

        return true;
    }

    public ArrayList<T> getAdyacentesById(Integer nodeId) {
        if (!repositoryNode.containsKey(nodeId)) {
            return null;
        }

        ArrayList<Integer> adyacentesId = this.grafo.get(nodeId);
        ArrayList<T> result = new ArrayList<>();

        for (Integer id : adyacentesId) {
            result.add(repositoryNode.get(id));
        }

        return result;
    }
}

