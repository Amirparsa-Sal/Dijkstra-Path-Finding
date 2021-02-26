public class Edge{

	private Node src;
	private Node dest;
	private double length;
	private int traffic;
	private final double CONS = 0.3;

	public Edge(Node src, Node dest){
		this.src = src;
		this.dest = dest;
		this.length = Node.distance(src,dest);
		traffic = 0;
	}

	public void increaseTraffic(){traffic++;}

	public void decreaseTraffic(){
		if (traffic == 0)
			return;
		traffic--;
	}	

	public double weight(){
		return length * (1 + CONS * (traffic));
	}

	public Node getDest(){return dest;}

	public Node getSrc(){return src;}

	@Override public String toString(){
		return src.getId() + " -> " + dest.getId();
	}
}