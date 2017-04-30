
public class Edge{
	private Vertex startingVertex, endingVertex;
	private double weight;

	public Edge(Vertex startingVertex, Vertex endingVertex, double weight)
	{
		this.startingVertex  = startingVertex;
		this.endingVertex = endingVertex;
		this.weight = weight;
	}

	public void setStartingVertex(Vertex newStartingVertex)
	{ 
		this.startingVertex  = newStartingVertex;
	}

	public void setEndingVertex(Vertex newEndingVertex)
	{ 
		this.endingVertex = newEndingVertex;
	}

	public void setWeight(double weight)
	{	
		this.weight = weight;
	}

	public Vertex getStartingVertex()
	{ 
		return this.startingVertex;	
	}

	public Vertex getEndingVertex()
	{ 
		return this.endingVertex; 
	}

	public double getWeight()
	{	
		return this.weight;
	}

}

