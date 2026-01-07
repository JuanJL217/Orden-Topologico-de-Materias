package Grafo;

import java.util.*;
import java.util.function.Consumer;

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

    public Optional<ArrayList<T>> getAdyacentesById(Integer nodeId) {
        if (!repositoryNode.containsKey(nodeId)) {
            return Optional.empty();
        }

        ArrayList<T> result = new ArrayList<>();

        grafo.get(nodeId).forEach( id ->
                result.add(repositoryNode.get(id))
        );

        return Optional.of(result);
    }

    public Optional<ArrayList<T>> getAllNodes() {
        if (grafo.isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(new ArrayList<>(repositoryNode.values()));
    }

    public void forEachNode(Consumer<T> action) {
        repositoryNode.values().forEach(action);
    }

    public void forEachNeighbor(Integer fromId, Consumer<T> action) {
        if (!repositoryNode.containsKey(fromId)) {
            return;
        }

        grafo.get(fromId).forEach(id ->
                action.accept(repositoryNode.get(id))
        );

    }
}

