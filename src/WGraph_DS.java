package ex1.src;

import java.io.Serializable;
import java.time.Instant;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

public class WGraph_DS implements weighted_graph, Serializable {
    private HashMap<Integer, node_info> weighted_graph;
    private HashMap<Integer, HashMap<Integer, node_info>> Ni;
    private HashMap<Integer, HashMap<Integer, Double>> edges;

    private int edgesize;
    private int mc;

    /**
     * This class represents an undirectional weighted graph.
     * The implementation should be based on an efficient compact representation
     */

    public WGraph_DS() {
        weighted_graph = new HashMap<>();
        Ni = new HashMap<>();
        edges = new HashMap<>();

    }

    /**
     * return the node_data by the node_id
     *
     * @param key - the node_id
     * @return the node_data by the node_id, null if none.
     */
    @Override
    public node_info getNode(int key) {
        return weighted_graph.get(key);
    }

    /**
     * return true if and only if there is an edge between node1 and node2
     *
     * @param node1
     * @param node2
     * @return true if there is and edge between thw two given nodes.
     */
    @Override
    public boolean hasEdge(int node1, int node2) {
        if (Ni.get(node1).get(node2) != null)
            return true;
        return false;
    }

    /**
     * return the weight of the edge (node1, node1). In case
     * there is no such edge - should return -1
     *
     * @param node1
     * @param node2
     * @return
     */
    @Override
    public double getEdge(int node1, int node2) {
        if (!hasEdge(node1, node2))
            return -1;
        return edges.get(node1).get(node2);

    }

    /**
     * this method add a node_data to the graph, by given key
     *
     * @param key
     */
    @Override
    public void addNode(int key) {
        node_info n = new NodeInfo(key);
        weighted_graph.put(key, n);
        HashMap<Integer, node_info> temp = new HashMap<Integer, node_info>();
        Ni.put(key, temp);
        HashMap<Integer, Double> temp1 = new HashMap<Integer, Double>();
        n.setInfo("");
        edges.put(key, temp1);
        mc++;
    }

    /**
     * this method add a given edge (w) between two given nodes (node1, node2)
     *
     * @param node1
     * @param node2
     * @param w
     */
    @Override
    public void connect(int node1, int node2, double w) {
        if (weighted_graph.get(node1) == null || weighted_graph.get(node2) == null)
            return;
        if (hasEdge(node1, node2))
            return;
        if (node1 == node2)
            return;
        Ni.get(node1).put(node2, weighted_graph.get(node2));
        Ni.get(node2).put(node1, weighted_graph.get(node1));
        edges.get(node1).put(node2, w);
        edges.get(node2).put(node1, w);
        mc++;
        edgesize++;
    }

    /**
     * this method returns a pointer of the collection representing all the node_data in the graph
     *
     * @return
     */
    @Override
    public Collection<node_info> getV() {
        return weighted_graph.values();
    }

    /**
     * this method returns a pointer of the collection representing all the nodes that connected to the given node_id
     *
     * @param node_id
     * @return
     */
    @Override
    public Collection<node_info> getV(int node_id) {
        return Ni.get(node_id).values();
    }

    /**
     * this method delete a node by his key, and also delete all the edges of the node
     *
     * @param key
     * @return
     */
    @Override
    public node_info removeNode(int key) {
        if (weighted_graph.get(key) == null)
            return null;
        Iterator<node_info> ba = Ni.get(key).values().iterator();
        while (ba.hasNext()) {
            node_info temp = ba.next();
            Ni.get(temp.getKey()).remove(key);
            edges.get(temp.getKey()).remove(key);
        }
        mc++;
        edgesize = edgesize - Ni.get(key).size();
        return weighted_graph.remove(key);

    }

    /**
     * this method delete an edge between two nodes by given 2 node's ids.
     *
     * @param node1
     * @param node2
     */
    @Override
    public void removeEdge(int node1, int node2) {
        if (weighted_graph.get(node1) == null || weighted_graph.get(node2) == null)
            return;
        if (hasEdge(node1, node2) == false)
            return;
        edges.get(node1).remove(node2);
        edges.get(node2).remove(node1);
        Ni.get(node1).remove(node2);
        Ni.get(node2).remove(node1);
        edgesize--;
        mc++;
    }

    /**
     * this method compare between given object and the class's graph and return true iff they identical.
     *
     * @param obj
     * @return
     */
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof WGraph_DS)) {
            return false;
        }
        WGraph_DS oe = (WGraph_DS) obj;
        if (this.nodeSize() != oe.nodeSize()) {
            return false;
        }
        if (this.getMC() != oe.getMC())
            return false;

        Iterator<node_info> jar = this.getV().iterator();
        while (jar.hasNext()) {
            node_info temp = jar.next();
            if (oe.getNode(temp.getKey()) == null)
                return false;
            if (oe.getNode(temp.getKey()).getTag() != temp.getTag())
                return false;
            if (!oe.getNode(temp.getKey()).getInfo().equals(temp.getInfo()))
                return false;
            Iterator<node_info> nei = this.getV(temp.getKey()).iterator();
            while (nei.hasNext()) {
                node_info temp1 = nei.next();
                if (!oe.hasEdge(temp1.getKey(), temp.getKey()))
                    return false;
                if (oe.getEdge(temp1.getKey(), temp.getKey()) != this.getEdge(temp1.getKey(), temp.getKey()))
                    return false;
            }


        }


        return true;
    }

    /**
     * this method returns the quantity of the nodes in the graph.
     *
     * @return
     */
    @Override
    public int nodeSize() {
        return weighted_graph.size();
    }

    /**
     * this method returns the quantity of the edges in the graph
     *
     * @return
     */
    @Override
    public int edgeSize() {
        return edgesize;
    }

    /**
     * this method returns the number of actions that been done on changing the graph
     *
     * @return
     */
    @Override
    public int getMC() {
        return mc;
    }

    /**
     * this method set the number of actions done on the graph.
     *
     * @param mc
     */
    public void setMC(int mc) {
        this.mc = mc;
    }

    /**
     * this private class represents the set of operations applicable on a
     * node (vertex) in an (undirectional) weighted graph.
     */
    private class NodeInfo implements node_info, Serializable {
        private int key;
        private String Info;
        private double Tag;

        public NodeInfo(int key) {
            this.key = key;
        }

        /**
         * this method returns the node_id of the node_data
         *
         * @return
         */
        @Override
        public int getKey() {
            return key;
        }

        /**
         * this method returns the info of the node
         *
         * @return
         */
        @Override
        public String getInfo() {
            return Info;
        }

        /**
         * this method used to change the info of the node
         *
         * @param s
         */
        @Override
        public void setInfo(String s) {
            Info = s;
        }

        /**
         * this method returns the tag of the node
         *
         * @return
         */
        @Override
        public double getTag() {
            return Tag;
        }

        /**
         * this method used ot change the tag of the node
         *
         * @param t - the new value of the tag
         */
        @Override
        public void setTag(double t) {
            Tag = t;
        }
    }

}
