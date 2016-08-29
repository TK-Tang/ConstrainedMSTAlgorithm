import java.util.HashMap;

public class Graph {
	
	// Graph contains a map of Edges and Nodes
	public HashMap<String, Node> nodeMap = new HashMap<String, Node>();
	public HashMap<String, Edge> edgeMap = new HashMap<String, Edge>();
	public HashMap<String, Node> leafMap = new HashMap<String, Node>();
	
	public void addNode(String i){
		nodeMap.put(i , new Node());
	}
	
	public void addEdge(String i, Edge e){
		edgeMap.put(i, e);
	}
	
	public void addLeaf(String i, Node n){
		leafMap.put(i, n);
	}
	
	public Node getNode(String i){
		return nodeMap.get(i);
	}
	
	public Edge getEdge(String i){
		return edgeMap.get(i);
	}
	
	public Node getLeaf(String i){
		return leafMap.get(i);
	}
	
	public HashMap<String, Node> getNodeMap(){
		return nodeMap;
	}
	
	public HashMap<String, Edge> getEdgeMap(){
		return edgeMap;
	}
	
	public HashMap<String, Node> getLeafMap(){
		return leafMap;
	}
}
