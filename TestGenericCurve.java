package abstractPackage;

public class TestGenericCurve 
{
	public TestGenericCurve()
	{
		testConstructors();
		testSearch();
		testAdd();
		testDelete();
	}
	
	private void testConstructors()
	{
		// Testing different start and end points of positive curve
		// Should round the quantity in order to reach p2
		Curve curve = new Curve(new Point(1, 1.0), new Point(20, 20.0), 10);
		System.out.println("Custom Producer: " + curve.toString());
		// Should print out normally
		curve = new Curve(new Point(1, 1.0), new Point(19, 19.0), 10);
		System.out.println("Custom Producer: " + curve.toString());
		// Should only print out p2
		curve = new Curve(new Point(1, 1.0), new Point(1, 10.0), 10);
		System.out.println("Custom Producer: " + curve.toString());
		// Should swap p1 and p2
		curve = new Curve(new Point(5, 1.0), new Point(1, 1.0), 10);
		System.out.println("Custom Producer: " + curve.toString());
		// Should print out normally
		curve = new Curve(new Point(1, 1.0), new Point(10, 10.0), 10);
		System.out.println("Custom Producer: " + curve.toString());
		
		// Testing different start and end points of negative curve
		// Should round the quantity in order to reach p2
		curve = new Curve(new Point(1, 20.0), new Point(20, 1.0), 10);
		System.out.println("Custom Consumer: " + curve.toString());
		// Should print out normally
		curve = new Curve(new Point(1, 19.0), new Point(19, 1.0), 10);
		System.out.println("Custom Consumer: " + curve.toString());
		// Should only print out p2
		curve = new Curve(new Point(1, 10.0), new Point(1, 1.0), 10);
		System.out.println("Custom Producer: " + curve.toString());
		// Should swap p1 and p2
		curve = new Curve(new Point(5, 1.0), new Point(1, 1.0), 10);
		System.out.println("Custom Producer: " + curve.toString());
		// Should print out normally
		curve = new Curve(new Point(1, 10.0), new Point(10, 1.0), 10);
		System.out.println("Custom Consumer: " + curve.toString());
	}
	
	private void testSearch()
	{
		Curve curve = new Curve(new Point(1, 1.0), new Point(10, 10.0), 10);
		System.out.println("Original Producer: " + curve.toString());
		Point op = new Point(5, 5.0); // should be true
		System.out.println("Is " + op.toString() + " on the curve? " + curve.search(op));
		op = new Point(0, 0.0); // left of curve (false)
		System.out.println("Is " + op.toString() + " on the curve? " + curve.search(op));
		op = new Point(5, 6.0); // above curve (false)
		System.out.println("Is " + op.toString() + " on the curve? " + curve.search(op));
		op = new Point(-1, -1.0); // left of and illegal? point (false)
		System.out.println("Is " + op.toString() + " on the curve? " + curve.search(op));
		op = new Point(5, 4.0); // below curve (false)
		System.out.println("Is " + op.toString() + " on the curve? " + curve.search(op));
		op = new Point(5, 5.009); // above the curve but within tolerance (true)
		System.out.println("Is " + op.toString() + " on the curve? " + curve.search(op));
		op = new Point(1, 1.0); // first point (true)
		System.out.println("Is " + op.toString() + " on the curve? " + curve.search(op));
		op = new Point(10, 10.0); // last point (true)
		System.out.println("Is " + op.toString() + " on the curve? " + curve.search(op));
	}
	
	private void testAdd()
	{	
		Curve curve = new Curve(new Point(1, 1.0), new Point(10, 10.0), 10);
		System.out.println("Original Producer: " + curve.toString());
		Point op = new Point(5, 5.0); // already on the point (ignore)
		curve.add(op);
		System.out.println("Updated Producer: "+ curve.toString());
		op = new Point(-1, 1.0); // negative quantity (ignore)
		curve.add(op);
		System.out.println("Updated Producer: "+ curve.toString());
		op = new Point(1, -1.0); // negative price (ignore)
		curve.add(op);
		System.out.println("Updated Producer: "+ curve.toString());
		op = new Point(12, 12.0); // above and to the right of curve (add to end)
		curve.add(op);
		System.out.println("Updated Producer: "+ curve.toString());
		op = new Point(11, 11.0); // in between two points (add to end then sort)
		curve.add(op);
		System.out.println("Updated Producer: "+ curve.toString());
		op = new Point(5, 7.0); // quantity already on the point (replace);
		curve.add(op);
		System.out.println("Updated Producer: "+ curve.toString());
	}
	
	private void testDelete()
	{
		Curve curve = new Curve(new Point(1, 1.0), new Point(10, 10.0), 10);
		System.out.println("Original Producer: " + curve.toString());
		Point op = new Point(5, 5.0); // on the point (delete);
		curve.delete(op);
		System.out.println("Updated Producer: "+ curve.toString());
		op = new Point(11, 11.0); // not on the point (ignore);
		curve.delete(op);
		System.out.println("Updated Producer: "+ curve.toString());
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TestGenericCurve testGenericCurve = new TestGenericCurve();
	}
}
