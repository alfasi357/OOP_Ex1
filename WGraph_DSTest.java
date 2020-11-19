
package ex1;

import org.junit.jupiter.api.Test;


import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

class WGraph_DSTest {


    public static weighted_graph creator() {
        weighted_graph gur = new WGraph_DS();
        for (int i = 1; i < 11; i++) {
            gur.addNode(i);
        }
       for (int j=1; j<10;j++ ){
           for(int k=j+1; k<11;k++){
               gur.connect(j,k,j+k);
           }
       }

        return gur;
    }

    @Test
    void nodeSize() {
        int sum = creator().nodeSize();
        assertEquals(10, sum);

    }

    @Test
    void edgeSize() {
       int esum = creator().edgeSize();
       assertEquals(45,esum);
    }

    @Test
    void getV() {
       Collection<node_info> esum = creator().getV();
assertNotNull(esum);

    }


    @Test
    void hasEdge() {
       assertTrue(creator().hasEdge(1,8));
        assertTrue(creator().hasEdge(2,3));
        assertTrue(creator().hasEdge(4,5));
        assertFalse(creator().hasEdge(4,4));
        assertFalse(creator().hasEdge(1,11));
        assertFalse(creator().hasEdge(9,12));
    }

    @Test
    void connect() {
       weighted_graph con = creator();
        con.addNode(11);
       con.addNode(12);
       con.connect(10,11,21);
       con.connect(10,12,22);
assertEquals(12,con.nodeSize());
assertEquals(47, con.edgeSize());
assertEquals(22, con.getEdge(10,12));
    }


    @Test
    void removeNode() {
      weighted_graph check = creator();
       check.removeNode(10);
       assertEquals(36, check.edgeSize());
       assertEquals(9,check.nodeSize());
    }

    @Test
    void removeEdge() {
        weighted_graph check =creator();
        check.removeEdge(3,9);
        check.removeEdge(4,6);
        assertEquals(43,check.edgeSize());
        assertEquals(-1,check.getEdge(4,6));
    }


}