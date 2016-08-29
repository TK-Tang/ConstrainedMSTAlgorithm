import java.util.Scanner;
import java.io.*;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Comparator;
import java.util.ArrayList;
import java.text.DecimalFormat;
import java.math.RoundingMode;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class A1{
	public static void main(String[] args){
		
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			Graph g = new Graph();
			
			int n = Integer.parseInt(br.readLine());
			int m = Integer.parseInt(br.readLine());
			double totalWeight = 0;
			
			// PriorityQueue for finding minimum weight edges
			Comparator<Edge> comparator = new WeightComparator();
			PriorityQueue<Edge> visitedEdge= new PriorityQueue<Edge>(m, comparator);
			ArrayList<Node> visitedNode = new ArrayList<Node>();
			
			DecimalFormat format = new DecimalFormat("#.##");
			
			// Adding nodes to map in graph
			for (int i = 0 ; i < n ; i++){
				g.addNode(String.valueOf(i));
			}
			
			// Adding leaf nodes to map in graph
			Scanner X = new Scanner(br.readLine());
			while(X.hasNext()){
				String s = X.next();
				// System.out.println(g.getNode(s));
				g.addLeaf(s, g.getNode(s));
			}
			
			// Adding edges to the map in graph
			for(int i = 0 ; i < m ; i++){
				Scanner currentLine = new Scanner(br.readLine());
				String sa = currentLine.next();
				String sb = currentLine.next();
				Node a = g.getNode(sa);
				Node b = g.getNode(sb);
				double w = Double.parseDouble(currentLine.next());
				
				Edge e = new Edge(a, b, w);
				
				g.addEdge(String.valueOf(i), e);
				
				// Add edge to node's map containing list of nodes if:
				// 1) Edge is between two nodes that are not in X
				// 2) Edge is between a node in X and a node not in X. Add edge to node in X only.
				if (!g.getLeafMap().containsValue(a) && !g.getLeafMap().containsValue(b)){
					a.addEdge(String.valueOf(i), e);
					b.addEdge(String.valueOf(i), e);
					// System.out.println("Both not in X: " + sa + " " + sb);
					
				} else if (g.getLeafMap().containsValue(a) && !g.getLeafMap().containsValue(b)){
					a.addEdge(String.valueOf(i), e);
					// System.out.println(sa + " is in X");
				} else if (!g.getLeafMap().containsValue(a) && g.getLeafMap().containsValue(b)) {
					b.addEdge(String.valueOf(i), e);
					// System.out.println(sb + " is in X");
				}
				
				currentLine.close();
			}
			
			X.close();
			
			/*
			// print all edges
			for (Map.Entry<String, Edge> entry : g.getEdgeMap().entrySet()) {
				String key = entry.getKey();
				Edge value = entry.getValue();
				System.out.println(key + " " + value + " Node Endpoints: " + value.getNodeA() + " " + value.getNodeB() + " Weight: " + value.getWeight());
				
			}
			
			// Print PQ
			while( visitedEdge.size() != 0){
				System.out.println(visitedEdge.peek().getWeight() + " " + visitedEdge.remove());
			}
			
			// print all nodes
			for (Map.Entry<String, Node> entry : g.getNodeMap().entrySet()) {
				String key = entry.getKey();
				Node value = entry.getValue();
				System.out.println(key + " " + value + " Size: " + value.getEdgeMap().size());
				for (Map.Entry<String, Edge> e : value.getEdgeMap().entrySet()){
					System.out.print(e.getValue() + " ");
				}
				System.out.println();
			}
			
			// print all leafs
			for (Map.Entry<String, Node> entry : g.getLeafMap().entrySet()) {
				String key = entry.getKey();
				Node value = entry.getValue();
				System.out.println(key + " " + value);
			}
			*/
			
			Node nodePointer = null;
			int nodeCount = 0;
			
			// Pick a starting node that's not part of X subset
			
			for(int i = 0 ; i < n ; i++){
				if(!g.getLeafMap().containsKey(Integer.toString(i))){
					nodePointer = g.getNode(Integer.toString(i));
					break;
				}
			}
			
			
			//Begin Prim's algorithm
			while (nodeCount < n - g.getLeafMap().size() - 1){
				visitedNode.add(nodePointer);
					
				for(Edge e : nodePointer.getEdgeMap().values()){
					if (!visitedEdge.contains(e)){
						visitedEdge.add(e);
					}
				}
				
				while(true){
					Edge currentShortest = visitedEdge.remove();
					
					if (!visitedNode.contains(currentShortest.getNodeA())){
						nodePointer = currentShortest.getNodeA();
						totalWeight = totalWeight + currentShortest.getWeight();
						//System.out.println(totalWeight + " " + currentShortest.getWeight());
						break;
					} else if(!visitedNode.contains(currentShortest.getNodeB())) {
						nodePointer = currentShortest.getNodeB();
						totalWeight = totalWeight + currentShortest.getWeight();
						//System.out.println(totalWeight + " " + currentShortest.getWeight());
						break;
					}
				}
				
				nodeCount = nodeCount + 1;
			}			
			
			for (Node node : g.getLeafMap().values()) {
				double weight = node.getMinEdge();
				totalWeight = totalWeight + weight;
				//System.out.println(totalWeight + " " + weight);
			}
								   
			System.out.println(format.format(totalWeight));
			
		} catch (IOException e){
			System.out.println("(Uninformative) Error");
		}
	}
}

