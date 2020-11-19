package ex1;

import org.junit.jupiter.api.Test;

import java.util.List;

import static ex1.WGraph_DSTest.creator;
import static org.junit.jupiter.api.Assertions.*;

class WGraph_AlgoTest {

    @Test
    void isConnected() {
        weighted_graph iC = creator();
        weighted_graph_algorithms ic1 = new WGraph_Algo();
        ic1.init(iC);
        iC.removeEdge(1,2);
        iC.removeEdge(1,3);
        iC.removeEdge(1,4);
        iC.removeEdge(1,5);
        iC.removeEdge(1,6);
        iC.removeEdge(1,7);
        iC.removeEdge(1,8);
        iC.removeEdge(1,9);
        iC.removeEdge(1,10);
        assertFalse(ic1.isConnected());

    }

    @Test
    void shortestPathDist() {
        weighted_graph spd = creator();
        weighted_graph_algorithms spd1 = new WGraph_Algo();
        spd1.init(spd);
        spd.removeEdge(1,4);
        assertEquals(9,spd1.shortestPathDist(1,4));
    }

    @Test
    void shortestPath() {
        weighted_graph sp = creator();
        weighted_graph_algorithms sp1 = new WGraph_Algo();
        sp1.init(sp);
        sp.removeEdge(2,6);
        sp.removeEdge(1,6);
        sp.removeEdge(3,6);
    List<node_info> path = sp1.shortestPath(2,6);
       for(int i=0; i<3; i++){
           assertEquals((i+1)*2,path.get(i).getKey());
       }
    }



    @Test
    void save_load() {
        weighted_graph sl = creator();
        weighted_graph_algorithms sl1 = new WGraph_Algo();
       sl1.init(sl);
       sl1.save("C:\\Users\\avi alfasi\\Desktop\\\u200F\u200Fתיקיה חדשה (9)\\graphtest.txt");
       weighted_graph_algorithms forload1 = new WGraph_Algo();
       forload1.load("C:\\Users\\avi alfasi\\Desktop\\\u200F\u200Fתיקיה חדשה (9)\\graphtest.txt");
       assertEquals(sl1.getGraph(), forload1.getGraph());
    }
}
