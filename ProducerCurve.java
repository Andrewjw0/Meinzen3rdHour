package abstractPackage;

public class ProducerCurve extends AbstractCurve {

	public ProducerCurve(Point p1, Point p2, int numPoints) 
	{
		super(p1, p2, numPoints);
	}
	
	public void sort() 
	{
		super.sortQuantity();
	}
}