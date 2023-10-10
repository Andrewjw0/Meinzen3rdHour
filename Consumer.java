package abstractPackage;

public class Consumer 
{
	private Curve myCCurve = new Curve(new Point(1, 10.0), new Point(10, 1.0), 10);
	private double tolerance = 0.01;
	
	public Point respondToBid(Point p)
	{
		return myCCurve.response(p, tolerance, true);
	}
}
