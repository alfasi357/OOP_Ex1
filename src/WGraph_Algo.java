package ex1.src;

import java.io.*;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * This interface represents an Undirected (positive) Weighted Graph Theory algorithms including:
 * * 0. clone(); (copy)
 * * 1. init(graph);
 * * 2. isConnected();
 * * 3. double shortestPathDist(int src, int dest);
 * * 4. List<node_data> shortestPath(int src, int dest);
 * * 5. Save(file);
 * * 6. Load(file);
 */
public class WGraph_Algo implements weighted_graph_algorithms {
    private weighted_graph algo;

    public WGraph_Algo() {
        algo = new WGraph_DS();
    }

    /**
     * this method initiate the graph which we operate on
     *
     * @param g
     */
    @Override
    public void init(weighted_graph g) {
        algo = g;
    }

    /**
     * this method returns the graph which we operate on
     *
     * @return
     */
    @Override
    public weighted_graph getGraph() {
        return algo;
    }

    /**
     * this method creates a deep copy of the graph
     *
     * @return
     */
    @Override
    public weighted_graph copy() {
        WGraph_DS algo2 = new WGraph_DS();
        Iterator<node_info> cop = algo.getV().iterator();
        while (cop.hasNext()) {
            node_info temp = cop.next();
            algo2.addNode(temp.getKey());
            algo2.getNode(temp.getKey()).setInfo(temp.getInfo());
            algo2.getNode(temp.getKey()).setTag(temp.getTag());
        }
        Iterator<node_info> nis = algo2.getV().iterator();
        while (nis.hasNext()) {
            node_info temp1 = nis.next();
            Iterator<node_info> ni22 = algo.getV(temp1.getKey()).iterator();
            while (ni22.hasNext()) {
                node_info temp2 = ni22.next();
                algo2.connect(temp1.getKey(), temp2.getKey(), algo.getEdge(temp1.getKey(), temp2.getKey()));
            }
        }
        algo2.setMC(algo.getMC());
        return algo2;
    }

    /**
     * this method check if there is a connected route between every node to node in the graph
     *
     * @return
     */
    @Override
    public boolean isConnected() {

        if (algo.nodeSize() == 0 || algo.nodeSize() == 1)
            return true;
        Iterator<node_info> hold = algo.getV().iterator();
        node_info first = hold.next();
        node_info second = hold.next();
        int src = first.getKey();
        int dest = second.getKey();
        shortestPathDist(src, dest);
        Iterator<node_info> con = algo.getV().iterator();
        while (con.hasNext()) {
            node_info temp = con.next();
            if (temp.getTag() == Integer.MAX_VALUE)
                return false;
        }
        return true;
    }

    /**
     * this method returns the distance of the shortest path depends on the length of the edges
     *
     * @param src  - start node
     * @param dest - end (target) node
     * @return
     */
    @Override
    public double shortestPathDist(int src, int dest) {
        Iterator<node_info> fir = algo.getV().iterator();
        while (fir.hasNext()) {
            node_info temp = fir.next();
            temp.setTag(Integer.MAX_VALUE);
            temp.setInfo("white");
        }
        algo.getNode(src).setTag(0);
        LinkedList<node_info> sel = new LinkedList<>();
        sel.addFirst(algo.getNode(src));
        node_info temp2 = algo.getNode(src);
        temp2.setInfo("grey");
        while (!sel.isEmpty()) {
            Iterator<node_info> itr = algo.getV(temp2.getKey()).iterator();
            while (itr.hasNext()) {
                node_info temp3 = itr.next();
                if (temp3.getTag() == Integer.MAX_VALUE) {
                    sel.addLast(temp3);
                    temp3.setTag(temp2.getTag() + algo.getEdge(temp3.getKey(), temp2.getKey()));
                    temp3.setInfo("grey");
                } else {
                    if (temp3.getTag() > (temp2.getTag() + algo.getEdge(temp2.getKey(), temp3.getKey()))) {
                        temp3.setTag(temp2.getTag() + algo.getEdge(temp3.getKey(), temp2.getKey()));
                        if (!temp3.getInfo().equalsIgnoreCase("grey")) {
                            sel.addLast(temp3);
                            temp3.setInfo("grey");
                        }
                    }
                }
            }
            sel.pollFirst();
            temp2.setInfo("black");
            if (!sel.isEmpty())
                temp2 = sel.getFirst();
        }
        return algo.getNode(dest).getTag();
    }

    /**
     * this method returns the shortest path between two nodes depends on the length of hte edges
     *
     * @param src  - start node
     * @param dest - end (target) nodes
     * @return
     */
    @Override
    public List<node_info> shortestPath(int src, int dest) {
        shortestPathDist(dest, src);
        LinkedList<node_info> path = new LinkedList<>();
        if (src == dest)
            return path;
        if (algo.getNode(src) == null || algo.getNode(dest) == null)
            return path;
        node_info temp = algo.getNode(src);
        path.add(temp);
        while (temp.getTag() != 0) {
            Iterator<node_info> clear = algo.getV(temp.getKey()).iterator();
            while (clear.hasNext()) {
                node_info temp2 = clear.next();
                if (temp.getTag() == temp2.getTag() + algo.getEdge(temp.getKey(), temp2.getKey())) {
                    path.addLast(temp2);
                    temp = temp2;
                    break;
                }
            }
        }
        return path;
    }

    /**
     * Saves this weighted (undirected) graph to the given file name
     *
     * @param file - the file name (may include a relative path).
     * @return true if and only if hte graph was successfully saved
     */
    @Override
    public boolean save(String file) {
        try {
            //Saving of object in a file
            FileOutputStream filen = new FileOutputStream(file);
            ObjectOutputStream out = new ObjectOutputStream(filen);
            // Method for serialization of object
            out.writeObject(algo);
            filen.close();
            out.close();
            return true;
        } catch (IOException ex) {

        }

        return false;
    }

    /**
     * This method load a graph to this graph algorithm.
     * if the file was successfully loaded - the underlying graph
     * of this class will be changed (to the loaded one), in case the
     * graph was not loaded the original graph should remain "as is".
     *
     * @param file - file name
     * @return iff the graph was successfully loaded.
     */
    @Override
    public boolean load(String file) {
        try {
            // Reading the object from a file
            FileInputStream filen = new FileInputStream(file);
            ObjectInputStream in = new ObjectInputStream(filen);
            // Method for deserialization of object
            algo = (WGraph_DS) in.readObject();
            in.close();
            filen.close();
            return true;
        } catch (IOException ex) {

        } catch (ClassNotFoundException ex) {

        }

        return false;
    }
}
