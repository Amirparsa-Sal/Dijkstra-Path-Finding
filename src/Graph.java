import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class Graph{

	private ArrayList<LinkedList<Edge>> adjList;
	private HashMap<Long, Integer> map;
	private Node[] nodes;
	private int size;

	public Graph(){
		this(1);
	}

	public Graph(int size){
		adjList = new ArrayList<>(size);
		map = new HashMap<>();
		nodes = new Node[size];
		this.size = 0;
	}

	public void addVertex(long id, double latitude, double longitude){
		Node node = new Node(id, latitude, longitude);
		nodes[size] = node;
		map.put(id, size);
		LinkedList<Edge> list = new LinkedList<>();
		adjList.add(list);
		size++;
	}

	public void addEdge(long id1, long id2){
		Node node1 = nodes[map.get(id1)];
		Node node2 = nodes[map.get(id2)];
		LinkedList<Edge> list1 = adjList.get(map.get(id1));
		list1.add(new Edge(node1, node2));
		LinkedList<Edge> list2 = adjList.get(map.get(id2));
		list2.add(new Edge(node2, node1));
	}

	public GraphPath bestPath(long srcId, long destId){
		boolean[] visited = new boolean[size];
		double[] distances = new double[size];
		int srcIndex = map.get(srcId), destIndex=map.get(destId);
		distances[srcIndex] = 0;
		BinaryMinHeap<IndexedDistance> bmh = new BinaryMinHeap<>(size);
		bmh.add(new IndexedDistance(0,srcIndex));
		for (int i = 0; i < size; i++){
			distances[i] = Double.MAX_VALUE;
			bmh.add(new IndexedDistance(distances[i],i));
		}
		while(!visited[destIndex]){
			IndexedDistance exploring = bmh.extractMin();
			visited[exploring.index] = true;
			LinkedList<Edge> neighbors = adjList.get(exploring.index);
			for (Edge edge : neighbors){
				double weight = exploring.distance + edge.weight();
				int neighborIndex = map.get(edge.getDest().getId());
				if (weight < distances[neighborIndex]){
					distances[neighborIndex] = weight;
					bmh.remove(new IndexedDistance(distances[neighborIndex], neighborIndex));
					bmh.add(new IndexedDistance(weight, neighborIndex));
					nodes[neighborIndex].setPrev(nodes[exploring.index]);
				}
			}
		}
		Node dest = nodes[destIndex], src = nodes[srcIndex];
		Node trav = dest;
		GraphPath path = new GraphPath();
		while(trav != src){
			Edge edge = getEdge(trav.getPrev(),trav);
			path.addEdge(edge);
			trav = trav.getPrev();
		}
		return path;
	}

	private Edge getEdge(Node src, Node dst){
		LinkedList<Edge> edges = adjList.get(map.get(src.getId()));
		for(Edge edge : edges)
			if(edge.getDest() == dst)
				return edge;
		return null;
	}

	private class IndexedDistance implements Comparable<IndexedDistance>{

		Double distance;
		int index;

		IndexedDistance(double distance, int index){
			this.distance = distance;
			this.index = index;
		}

		@Override public int compareTo(IndexedDistance o){
			return distance.compareTo(o.distance);
		}

		@Override public boolean equals(Object o){
			if (o == this)
				return true;
			if (!(o instanceof IndexedDistance))
				return false;
			IndexedDistance obj = (IndexedDistance) o;
			return index == obj.index;
		}
	}
}