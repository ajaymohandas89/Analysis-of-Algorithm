import java.util.HashMap;
import java.util.Map;

/**
 * Abstract class for Graphs.
 * This should be extended by both directed and undirected graphs.
 * @tparam V the vertex type.
 * @tparam Adj the adjacency type: can be vertex or edge.
 */
abstract public class AbstractGraph<V, Adj> implements Graph<V, Adj>{

    @Override
    public SizedIterable<V> vertices() {
        return SizedIterableImpl.create(adjacentEdges.keySet());
    }

    @Override
    public SizedIterable<Adj> adjacent(V v) {
        return adjacentEdges.get(v);
    }

    protected Bag<Adj> getAdjacencyBag(V vertex) {
        return adjacentEdges.computeIfAbsent(vertex, k -> new Bag_Array<>());
    }

    protected final Map<V, Bag<Adj>> adjacentEdges = new HashMap<>();
}