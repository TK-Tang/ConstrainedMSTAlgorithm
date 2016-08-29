import java.util.Comparator;

// Class needed for comparing weights in Edges. 
// An instantation of WeightComparator is put into PriorityQ's constructor.

public class WeightComparator implements Comparator<Edge>{
	
	@Override
	public int compare(Edge x, Edge y){
		
		double a = x.getWeight();
		double b = y.getWeight();
		
		if ( a < b ){
			return -1;
		} else if( a > b){
			return 1;
		}
		return 0;
	}
}
