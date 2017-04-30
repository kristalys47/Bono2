import java.util.ArrayList;
import java.util.Arrays;

public class Vertex{
	private String name;
	private boolean wasPassed;
	private ArrayList<Edge> adjacentEdges;
	private double minimumDistance = Double.POSITIVE_INFINITY; // These should later be relaxed
	private Vertex previousVertex = null; // Will be changed as needed
	private int index;
	public Vertex(String name)
	{
		this.name = name;
		this.adjacentEdges = new ArrayList <>();
	}
	
	public Vertex(){

		this.adjacentEdges = new ArrayList <>();
	}
	
	public void setName(String newName)
	{
		this.name = newName;
	}
	
	public void setWasVisited(boolean value)
	{	
		this.wasPassed = value;	
	}

	public void setDistanceFromSource(double distance)
	{
		this.minimumDistance = distance;
	}
	
	public void setPreviousVertex(Vertex previousVertex)
	{	
		this.previousVertex = previousVertex;
	}
	
	public void setIndex(int index)
	{
		this.index = index;
	}
	
	public String getName()
	{
		return this.name;
	}

	public boolean getWasVisited()
	{	
		return this.wasPassed;
	}
	
	public double getDistanceFromSource()
	{	
		return this.minimumDistance;
	}

	public int getIndex()
	{
		return this.index;
	}
	
	public Vertex getPreviousVertex()
	{	
		return this.previousVertex;
	}
	
	public static int nameToIndex(String name, Vertex[] vertexArray)
	{
		ArrayList<Vertex> vertexList = new ArrayList<Vertex>(Arrays.asList(vertexArray));
		for(Vertex vertex: vertexArray)
		{
			if(vertex.getName().toLowerCase().equals(name.toLowerCase()))
			{
				return vertexList.indexOf(vertex);
			}
		}
		return -1;
	}
	
	public void addNewEdge(Edge theNewEdge)
	{
		this.adjacentEdges.add(theNewEdge);
	}
	
	public ArrayList<Edge> adjacentEdges()
	{
		return this.adjacentEdges;
	}

}