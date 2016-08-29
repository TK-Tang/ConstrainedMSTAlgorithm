public class Edge{
	private Node a;
	private Node b;
	double w;
	
	public Edge(Node a, Node b, double w){
		this.a = a;
		this.b = b;
		this.w = w;
	}
	
	public Node getNodeA(){
		return a;
	}
	
	public Node getNodeB(){
		return b;
	}
	
	public double getWeight(){
		return w;
	}
}
