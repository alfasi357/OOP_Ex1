# OOP_Ex1
This repository includes the intrfaces and the implements of undirectional weighted graph.
In this repository i will explain the subject of my project and i will give examples of the uses of the codes.
---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
### Intro:
This project implements undirectional weighted graph, in this project i implement the interfaces of node_info, weighted_grpah and weighted_graph_algorithms.
In the implemetation of node_info i  secure the node_id, the tag and the info of the node.
In the implemetaion of the weighted_graph i implements some actions on the graph like removing edge, connect between two nodes and adding a node, in addition to that i
can check if any edge or node is in the graph, and i have counter such as: node size, edge size and etc.
In the implemetaion of the weighted_graph_algorithms i implement the more complicated examination such as checking if hte graph is connected and the shortest path between 
two nodes.
---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
### Examples:
First i will make a new weighted_graph and new weighted_graph_algorithms and i will initate the weighted_graph in the weighted_graph_algorithms:
 ```bash
        weighted_graph ExampleGraph = new WGraph_DS();
        weighted_graph_algorithms AlgoExGraph = new WGraph_Algo();
        AlgoExGraph.init(ExampleGraph);
```       
Now i will add nodes, remove node, connect between nodes and remove edge between two nodes and i will print the number of nodes and number of edges:
```bash
        ExampleGraph.addNode(1);
        ExampleGraph.addNode(2);
        ExampleGraph.addNode(3);
        ExampleGraph.addNode(4);
        ExampleGraph.removeNode(4);
        ExampleGraph.connect(1,2,3);
        ExampleGraph.connect(1,3,4);
        ExampleGraph.connect(2,3,5);
        ExampleGraph.removeEdge(1,3);
        System.out.println(ExampleGraph.edgeSize());
        System.out.println(ExampleGraph.nodeSize());
```
This time i will show how to set tag and info and then print them:
```bash
       ExampleGraph.getNode(1).setTag(8);
       ExampleGraph.getNode(1).setInfo("red");
       System.out.println(ExampleGraph.getNode(1).getInfo());
       System.out.println(ExampleGraph.getNode(1).getTag());
```
And for the algorithms section i will show how to check if the graph is connected, how to check the shortest path  and the shortest path distance
between two nodes, in addition to that i will show how to save and load the graph:
```bash
        System.out.println(AlgoExGraph.isConnected());
        System.out.println(AlgoExGraph.shortestPathDist(1,3));
        for (int i=0; i<3; i++){
            System.out.println(AlgoExGraph.shortestPath(1,3).get(i).getKey());
        }
        weighted_graph CopyGraph = AlgoExGraph.copy();
        AlgoExGraph.save("C:\\Java Examples\\ExampleGraph.txt");
        AlgoExGraph.load("C:\\Java Examples\\ExampleGraph.txt");
```
 Now i will show the graph in the code:
 ![graph](https://user-images.githubusercontent.com/74153058/99816991-0588d480-2b55-11eb-90ed-b91dce4059d9.PNG)
