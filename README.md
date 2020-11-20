# OOP_Ex1
This repository includes the intrfaces and the implements of undirectional weighted graph.
In this repository i will explain the subject of my project and i will give examples of the uses of the codes.
-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

Intro:
This project implements undirectional weighted graph, in this project i implement the interfaces of node_info, weighted_grpah and weighted_graph_algorithms.
In the implemetation of node_info i  secure the node_id, the tag and the info of the node.
In the implemetaion of the weighted_graph i implements some actions on the graph like removing edge, connect between two nodes and adding a node, in addition to that i
can check if any edge or node is in the graph, and i have counter such as: node size, edge size and etc.
In the implemetaion of the weighted_graph_algorithms i implement the more complicated examination such as checking if hte graph is connected and the shortest path between 
two nodes.
------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
Examples:
First i will make a new weighted_graph and new weighted_graph_algorithms and i will initate the weighted_graph in the weighted_graph_algorithms:
 ```bash
        weighted_graph ExampleGraph = new WGraph_DS();
        weighted_graph_algorithms AlgoExGraph = new WGraph_Algo();
        AlgoExGraph.init(ExampleGraph);
        ```
