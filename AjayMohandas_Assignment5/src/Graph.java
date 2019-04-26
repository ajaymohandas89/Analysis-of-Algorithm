public interface Graph<Vertex, Adjacent> {

    /**
     * Get the vertices of this Graph as an Iterable.
     * @return the vertices
     */
    SizedIterable<Vertex> vertices();

    /**
     * Get the entities which are adjacent to the given vertex.
     * @param vertex the vertex whose adjacent entities we want.
     * @return the adjacent entities as an Iterable.
     */
    SizedIterable<Adjacent> adjacent(Vertex vertex);

}