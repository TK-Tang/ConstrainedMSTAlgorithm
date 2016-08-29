import java.util.HashMap;

public class Node {
	
	public HashMap<String, Edge> edgeMap = new HashMap<String, Edge>();
	
	public void addEdge(String i, Edge e){
		edgeMap.put(i, e);
	}
	
	public double getMinEdge(){
		double weightA = 99;
		double weightB = 99;
		for(Edge e: edgeMap.values()){
			weightB = e.getWeight();
			
			if (weightB < weightA){
				weightA = weightB;
			}
		}
		
		return weightA;
	}
	
	public Edge getEdge(String i){
		return edgeMap.get(i);
	}
	
	public HashMap<String, Edge> getEdgeMap(){
		return this.edgeMap;
	}
}
