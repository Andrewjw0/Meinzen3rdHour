package abstractPackage;

public class Producer 
{
	private ProducerCurve myPCurve = new ProducerCurve(new Point(1, 1.0), new Point(10, 10.0), 10);
	private double tolerance = 0.01;
	
	public Point respondToBid(Point p)
	{
		return myPCurve.response(p, tolerance, false);
	}
}
