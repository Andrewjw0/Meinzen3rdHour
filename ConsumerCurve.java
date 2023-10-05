package abstractPackage;

public class ConsumerCurve extends AbstractCurve 
{
	private double tolerance = 0.01;
	
	public ConsumerCurve(Point p1, Point p2, int numPoints)
	{
		super(p1, p2, numPoints);
	}
	
	public void sort()
	{
		super.sortQuantity();
	}
}