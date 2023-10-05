package abstractPackage;

public class Point 
{
	private double price;
	private int    quant;
	private final double TOLORANCE = 0.01;
	
	/**
	 * default construction of a point
	 */
	public Point()
	{
		price = 0.0;
		quant = 0;
	}
	
	/**
	 * point constructed with parameters to create a specific point
	 * @param q
	 * @param p
	 */
	public Point(int q, double p)
	{
		price = p;
		quant = q;
	}
	
	public String toString()
	{
		String s = "("+this.getQuant()+","+this.getPrice()+")";
		return s;
	}
	
	/**
	 * get quantity
	 * @return
	 */
	public int getQuant()
	{
		return quant;
	}
	
	/**
	 * get Price
	 * @return
	 */
	public double getPrice()
	{
		return price;
	}
	/**
	 * set price
	 * @param p
	 */
	
	public void setPrice(double p)
	{
		price = p;
	}
	
	/**
	 * set quantity
	 * @param q
	 */
	public void setQuant(int q)
	{
		quant = q;
	}
	
	/**
	 * checks if an object is a point and if so directs it to equals(point a)
	 * Overrides Object.equals()
	 */
	public boolean equals(Object o)
	{
		if(o instanceof Point)
		{
			Point p = (Point) o;
			return equals(p);
		}
		return false;
	}
	
	/**
	 * compares two points and returns a boolean regarding their relation and accounts for a rounding tolerance
	 * true = equal, false != equal
	 * overloads Object.equals()
	 * @param a
	 * @return
	 */
	public boolean equals(Point a)
	{
		if ((Math.abs(this.getPrice() - a.getPrice()) < TOLORANCE) && (this.getQuant() == a.getQuant()))
		{
			return true;
			
		}
		return false;
		
	}	
}
