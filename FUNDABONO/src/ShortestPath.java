import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import javax.swing.JFileChooser;


public class ShortestPath
{
	private static Vertex[] vertexArray;
	private static ArrayList<Vertex> tracePath = new ArrayList<>();
	private static ArrayList<Edge> edgesList = new ArrayList<>();
	static Scanner read;
	static Scanner userInput;
	static JFileChooser fileChoosen;
	public static void main(String[] args) throws FileNotFoundException
	{
		int end =0;
		userInput = new Scanner(System.in);
		createVertexArray();
		fillVertexArray();
		createEdgeList();
		System.out.println("");
		do
		{
			
		System.out.println("Where are you starting? ");
		String starting = userInput.next();
		System.out.println("Where do you want to go? ");
		String ending = userInput.next();
		ArrayList <Vertex> vertexList = new ArrayList<Vertex>(Arrays.asList(vertexArray));
		Vertex startingVertex = vertexList.get(Vertex.nameToIndex(starting, vertexArray));
		Vertex endingVertex = vertexList.get(Vertex.nameToIndex(ending, vertexArray));
		shortestPath(vertexList, edgesList, startingVertex, endingVertex);
		System.out.println("To end press 0 otherwise enter 1 to repeat process:");
		end	= userInput.nextInt();
		} while(end!=0);
	}
	
	public static void showPath(Vertex source, Vertex destination)
	{
		Vertex lastVertex = vertexArray[destination.getIndex()];
		tracePath.add(lastVertex);
		while(lastVertex.getPreviousVertex() != source){// if it is the same then stop.
			lastVertex = vertexArray[lastVertex.getPreviousVertex().getIndex()];
			tracePath.add(lastVertex);
		}
		tracePath.add(vertexArray[source.getIndex()]);
		System.out.println("The shortest path from "+ source.getName()+" to "+ destination.getName());
		for(int i = tracePath.size()-1; i >= 0; i--){
			System.out.println(tracePath.get(i).getName());
		}
	}
	
	public static void createVertexArray()throws FileNotFoundException
	{
		JFileChooser fileChoosen = new JFileChooser();

		if(fileChoosen.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){
			read = new Scanner(fileChoosen.getSelectedFile());
			if(read.hasNextInt()){
				vertexArray = new Vertex[read.nextInt()];
			}
			else throw new FileNotFoundException("Size for the vertex array does not exist. ");
		}
	}

	public static void fillVertexArray(){
		System.out.println("Points: ");
		for(int i = 0; i < vertexArray.length; i++){
			vertexArray[i] = new Vertex(read.next());
			vertexArray[i].setIndex(i);
		}
		for(int i = 0; i< vertexArray.length; i++){
			System.out.println(vertexArray[i].getName());
		}
		System.out.println("____________");
		
	}
	
	public static void createEdgeList()
	{
		System.out.println("Edges: ");
		while(read.hasNext()){
			String starting = read.next();
			String ending = read.next();
			String weight = read.next();
			Vertex.nameToIndex(starting,vertexArray);
			System.out.println("____________");
			System.out.println(starting + "-" + ending);
			System.out.println(weight);
			edgesList.add(new Edge(vertexArray[Vertex.nameToIndex(starting, vertexArray)],vertexArray[Vertex.nameToIndex(ending, vertexArray)], Double.parseDouble(weight)));
		}
	}

	public static void shortestPath(ArrayList<Vertex> vertexList, ArrayList<Edge> edgeList, Vertex location, Vertex destination)
	//verificar esto
	{
		Vertex starting = null;
		Vertex tailTarget = null;
		location.setDistanceFromSource(0);
		int length = vertexList.size();
		for (int index = 0; index < length; index++) 
		{
			vertexList.get(index).setIndex(index);
		}
		System.out.println("____________");
		for (int numberOfVertexes = 0; numberOfVertexes < length - 1; numberOfVertexes++) 
		{
			for (Edge edge : edgeList) 
			{
				if (edge.getStartingVertex().getDistanceFromSource() == Double.POSITIVE_INFINITY)
				{
					continue; 
				}
				starting = edge.getStartingVertex();
				tailTarget = edge.getEndingVertex();
			
				double newDistance = starting.getDistanceFromSource() + edge.getWeight();
				if (newDistance < tailTarget.getDistanceFromSource()) 
				{
					tailTarget.setDistanceFromSource(newDistance);
					tailTarget.setPreviousVertex(starting);
				}
			}
		}

		if (destination.getDistanceFromSource() == Double.MAX_VALUE) 
		{
			System.out.println("There is no path.");
		}

		else 
		{
			showPath(location, destination);
			System.out.println("The Shortest Distance from " + location.getName() + " to " + destination.getName() + " is: "
					+ destination.getDistanceFromSource() + " km");
			for (Vertex vertexes : vertexList) 
			{
				vertexes.setDistanceFromSource(Double.POSITIVE_INFINITY);
				tracePath.clear();
			}
		}

	}

}

