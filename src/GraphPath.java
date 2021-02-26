import java.util.Deque;
import java.util.ArrayDeque;
import java.lang.StringBuilder;
import java.util.Iterator;

public class GraphPath{

	private Deque<Edge> edges;

	public GraphPath(){
		edges = new ArrayDeque<>();
	}

	public void addEdge(Edge edge){edges.push(edge);}

	public double time(){
		double time = 0;
		for (Edge edge : edges)
			time += edge.weight();
		return time*120;
	}

	public void increaseTraffic(){
		for (Edge edge : edges)
			edge.increaseTraffic();
	}

	public void decreaseTraffic(){
		for (Edge edge : edges)
			edge.decreaseTraffic();
	}

	public Iterator<Edge> iterator(){return edges.iterator();}

	public int size(){return edges.size();}
	
	@Override public String toString(){
		StringBuilder sb = new StringBuilder("");
		for (Edge edge : edges)
			sb.append(edge + "   ");
		sb.append("\n");
		return sb.toString();
	}

}