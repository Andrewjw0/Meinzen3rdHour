package abstractPackage;

import java.util.ArrayList;

abstract class AbstractCurve implements CurveInterface
{
	private ArrayList<Point> newCurve;
	
	public AbstractCurve(Point p1, Point p2, int numPoints) 
	{
		if (p1.getQuant() == p2.getQuant()) 
		{
			newCurve = new ArrayList<Point>(1);
			newCurve.add(p2);
			return;
		}
		
		if (p1.getQuant() > p2.getQuant())
		{
			Point temp = p1;
			p1 = p2;
			p2 = temp;
		}
		
		newCurve = new ArrayList<Point>(numPoints + 1);
		newCurve.add(p1);
		
		int    deltaQ = (int) Math.round((p2.getQuant() - p1.getQuant()) / (numPoints * 1.0));
		double deltaP = (p2.getPrice() - p1.getPrice()) / (numPoints - 1);
		
		for (int i = 1; i < numPoints - 1; i++)
		{
			int    q = p1.getQuant() + deltaQ * (i);
			double p = p1.getPrice() + deltaP * (i);
			newCurve.add(new Point(q, p));
		}
		
		newCurve.add(p2);
	}
	
	public void add(Point aP) {
		// Checks if p is on the line, if so doesn't add
		if (search(aP) == true)
		{
			return;
		}
		
		// Checks if p has a quantity below or equal to 0, if so doesn't add
		if (aP.getQuant() <= 0)
		{
			return;
		}
		
		// Checks if p has a price below or equal to 0, if so doesn't add
		if (aP.getPrice() <= 0)
		{
			return;
		}
		
		// Checks if p has a matching quantity on the line, if so swaps instead
		int index = searchMatchingQuantity(aP);
		
		if (index >= 0) 
		{
			newCurve.set(index, aP);
			return;
		}
		
		newCurve.add(aP);
		System.out.println("a");
		sortQuantity();
	}
	
	/**
	 * Searches for a Point with quantity and price equal to op on the curve.
	 * If there is one, return the index. Otherwise return -1 (impossible index).
	 */
	private int searchMatchingQuantity(Point op)
	{
		ArrayList<Point> tc = newCurve;
		
		for (int i = 0; i < tc.size(); i++)
		{
			Point temp = tc.get(i);
			
			if (op.getQuant() == temp.getQuant())
			{
				return i;
			}
		}
		
		return -1;
	}
	
	abstract void sort();
	
	public void sortPrice() 
	{
		ArrayList<Point> tc = newCurve;
		
		for (int a = 0; a < tc.size(); a++)
		{
			for (int i = 0; i < tc.size() - 1; i++)
			{
				int   rhi = i + 1;
				Point leftP = tc.get(i);
				Point rightP = tc.get(rhi);
				
				if (leftP.getPrice() > rightP.getPrice())
				{
					swap(i, rhi);
				}
			}
		}
	}
	
	public void sortQuantity() 
	{
		ArrayList<Point> tc = newCurve;
		
		for (int a = 0; a < tc.size(); a++)
		{
			for (int i = 0; i < tc.size() - 1; i++)
			{
				int   rhi = i + 1;
				Point leftP = tc.get(i);
				Point rightP = tc.get(rhi);
				
				if (leftP.getQuant() > rightP.getQuant())
				{
					swap(i, rhi);
				}
			}
		}
	}
	
	/**
	 * Swaps i1 and i2's positions, helper method.
	 */
	private void swap(int i1, int i2)
	{
		ArrayList tc = newCurve;
		
		Object temp = tc.set(i1, tc.get(i2));
		tc.set(i2, temp);
	}
	
	public String toString() 
	{
		String temp = "[";
		for (int i=0; i<newCurve.size(); i++) {
			if (i != newCurve.size() - 1) {
				temp += newCurve.get(i).toString() + ", ";
			} else {
				temp += newCurve.get(i).toString();
			}
		}
		temp += "]";
		return temp;
	}
	
	public void delete(Point p) 
	{
		if (search(p))
		{
			newCurve.remove(p);
			newCurve.trimToSize();
		}
	}
	
	/**
	 * Searches for a Point with quantity and price equal to op on the curve.
	 * If there is one, return the index. Otherwise return -1 (impossible index).
	 */
	private int searchForIndex(Point op)
	{
		for (int i = 0; i < newCurve.size(); i++)
		{
			if (newCurve.get(i).equals(op))
			{
				return i;
			}
		}
		
		return -1;
	}
	
	public boolean search(Point op) 
	{
		return newCurve.contains(op);
	}
	
	private Point closestLessOrEqualQuant(Point p)
	{
		Point closest = new Point(0, 0.0);
		
		for (int i = 0; i < newCurve.size(); i++)
		{
			Point temp = newCurve.get(i);
			
			if (p.getQuant() <= temp.getQuant())
			{
				closest = temp;
			}
		}
		
		return closest;
	}
	
	public Point consumerResponse(Point p, double tolerance)
	{
		if (p.getPrice() <= (newCurve.get(searchMatchingQuantity(p)).getPrice() + tolerance))
		{
			return p;
		}
		else
		{
			return closestLessOrEqualQuant(p);
		}
	}
	
	public Point producerResponse(Point p, double tolerance)
	{
		if (p.getPrice() >= (newCurve.get(searchMatchingQuantity(p)).getPrice() - tolerance))
		{
			return p;
		}
		else
		{
			return closestLessOrEqualQuant(p);
		}
	}
}