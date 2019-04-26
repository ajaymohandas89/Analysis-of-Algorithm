import java.util.Comparator;

public class GeoKruskal<V extends GeoPoint, X extends Comparable<X>> extends Kruskal<V, X> {
    public GeoKruskal(EdgeGraph<V, X> graph, Comparator<Edge> comparator) {
        super(graph,comparator);
    }

    /**
     * Method to generate a graph of the MST, given an empty BaseGeoGraph
     * @param geoGraph an empty GeoGraph which will be filled with edges before being returned.
     * @return the geoGraph that was passed as the parameter, but filled with the MST edges.
     */
    public Geo<V, X> getGeoMST(BaseGeoGraph<V, X> geoGraph) {
        EdgeGraph<V, X> mst = super.getMST();
        for (Edge e : mst.edges()) //noinspection unchecked
            geoGraph.addEdge(createEdge(e));
        return geoGraph;
    }

    public GeoEdge<V, X> createEdge(Edge<V, X> edge) {
        V v = edge.get();
        //noinspection unchecked
        return new GeoEdge(v, edge.getOther(v), edge.getAttribute());
    }

}