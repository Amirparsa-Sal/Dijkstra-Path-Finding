public class Node{

	private double latitude;
	private double longitude;
	private long id;
	private Node prev;

	public Node(long id, double latitude, double longitude){
		this.id = id;
		this.latitude = latitude;
		this.longitude = longitude;
		prev = null;
	}

	public double getLatitude(){return latitude;}

	public double getLongitude(){return longitude;}

	public long getId(){return id;}

	public Node getPrev(){return prev;}

	public void setPrev(Node prev){this.prev = prev;}

	public static double distance(Node node1, Node node2){return Math.sqrt(Math.pow(node1.latitude-node2.latitude, 2) + Math.pow(node1.longitude - node2.longitude, 2));}

}