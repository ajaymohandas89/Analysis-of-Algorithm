
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * This is a generic solution for Kruskal's algorithm to find the minimum spanning tree of an edge-weighted graph
 *
 * @tparam V is the type of each vertex.
 *
 */
public class Kruskal<V, X extends Comparable<X>> implements Iterable<Edge> {
    private EdgeGraph<V, X> graph;
    private Iterable<Edge<V, X>> mst;
    private Comparator<Edge> comparator;
    private SizedIterable<Edge<V,X>> edges;
    private V temp;
    public Kruskal(EdgeGraph<V, X> graph,Comparator<Edge> comparator) {
        //TODO takes a graph and outputs an Iterable.
        this.edges = graph.edges();
        this.graph = graph;
        this.comparator = comparator;
        this.getMST();

    }

    Kruskal() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public EdgeGraph<V, X> getMST() {
        //TODO use union find and sort to find the minimum spanning tree of an edge-weighted graph
        ArrayList<Edge> edge_list= new ArrayList<>();
        EdgeGraph<V,X> mst_tree = new Graph_Edges<>();
        this.mst = new Bag_Array<Edge<V,X>>();
        
        Map<V,Integer> map = new HashMap<>();
        int i=0;
       /* for (V v : graph.vertices()) {
            map.put(v,i++);
        }*/
        
        Iterator<V> v_iter = graph.vertices().iterator();
        while(v_iter.hasNext()){
            temp = v_iter.next();
            System.out.println(temp+"  "+i);
            map.put(temp, i++);
        }
        
        UF uf = new UF_HWQUPC(graph.vertices().size(),false);
        for (Edge<V,X> edge:edges) edge_list.add(edge);
        Collections.sort(edge_list,comparator);
        
        while(!edge_list.isEmpty() && mst_tree.edges().size()<graph.vertices().size()-1){
            Edge e=edge_list.get(0);
            V v = (V) e.get();
            V w = (V)e.getOther(v);
            
            int v_map = map.get(v);
            int w_map = map.get(w);
            
            if (!uf.isConnected(v_map,w_map)){
                uf.union(v_map, w_map);
                mst_tree.addEdge(e);
                ((Bag_Array<Edge<V,X>>) this.mst).add(e);
                //System.out.println(mst_tree.edges().size());
            }
            edge_list.remove(0);
        }
        return mst_tree;
    }

    @Override
    public Iterator<Edge> iterator() {
        ArrayList<Edge> result = new ArrayList<>();
        for (Edge<V, X> edge : mst) result.add(edge);
        return result.iterator();
    }

    public static <V, X extends Comparable<X>> Edge<V, X> createEdge(V v1, V v2, X x) {
        return new Edge<>(v1, v2, x);
    }
}