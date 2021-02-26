import java.util.Scanner;
import java.util.Queue;
import java.util.LinkedList;
import java.util.Locale;
import java.lang.StringBuilder;
import java.util.Iterator;
import java.awt.FileDialog;
import java.awt.Frame;
import java.io.File;
import java.lang.Process;

public class RouteFinder{

	public static void main(String[] args) {
		Scanner sc = null;
		String file = "";
		boolean choosing = true;
		while (choosing){
			try{
				FileDialog dialog = new FileDialog((Frame)null, "Select Map to Open");
			    dialog.setMode(FileDialog.LOAD);
			    dialog.setVisible(true);
			    file = dialog.getDirectory() + dialog.getFile();
				sc = new Scanner(new File(file)).useLocale(Locale.US);
				choosing = false;
			}
			catch(Exception ex){
				System.out.println("Choose another file!");
			}
		}

		int v,e;
		v = sc.nextInt();
		e = sc.nextInt();
		Graph graph = new Graph(v);
		for(int i = 0; i < v; i++){
			long id = sc.nextLong();
			Double latitude = sc.nextDouble();
			Double longitude = sc.nextDouble();
			graph.addVertex(id, latitude, longitude);
		}
		for(int i = 0; i < e; i++){
			long id1 = sc.nextLong();
			long id2 = sc.nextLong();
			graph.addEdge(id1,id2);
		}
		sc = new Scanner(System.in).useLocale(Locale.US);
		int n = sc.nextInt();
		Queue<Transportation> queue = new LinkedList<>();
		StringBuilder sb = new StringBuilder("cmd /c python diagram.py \"" + file + "\"");
		for (int i = 0; i < n; i++){
			double startTime = sc.nextDouble();
			while(!queue.isEmpty() && queue.peek().endTime() < startTime)
				queue.remove().end();
			GraphPath path = graph.bestPath(sc.nextLong(), sc.nextLong());
			Iterator<Edge> it = path.iterator();
			StringBuilder command = new StringBuilder(sb);
			command.append(" " + path.size());
			while(it.hasNext()){
				Edge edge = it.next();
				command.append(" " + edge.getSrc().getId() + " " + edge.getDest().getId());
			}
			Transportation t = new Transportation(startTime, path);
			t.start();
			queue.add(t);
			try{
				Runtime.getRuntime().exec(command.toString());	
			}
			catch (Exception ex){
				System.out.println(ex);
				System.out.println("Oops! Something went wrong!");
			}
			
		}
		while(!queue.isEmpty())
			queue.remove().end();
		System.exit(0);	
	}
}