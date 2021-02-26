public class Transportation{

	private double startTime;
	private double endTime;
	private GraphPath path;
	private static int count = 0;
	private int id;

	public Transportation(double startTime, GraphPath path){
		this.path = path;
		this.startTime = startTime;
		this.endTime = startTime + path.time();
		count++;
		id = count;
	}

	public void start(){
		System.out.println("Transportation " + id + " started at " + startTime + "	    Estimated Time: " + (endTime - startTime));
		System.out.print("Path: " + path);
		path.increaseTraffic();
	}

	public void end(){
		System.out.println("Transportation " + id + " finished at " + endTime);
		path.decreaseTraffic();
	}

	public double endTime(){
		return endTime;
	}
}