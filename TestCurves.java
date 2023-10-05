package abstractPackage;

public class TestCurves 
{
	public TestCurves()
	{
//		testProducerConstructors();
//		testProducerSearch();
//		testProducerAdd();
//		testProducerDelete();
//		testConsumerConstructors();
//		testConsumerSearch();
//		testConsumerAdd();
//		testConsumerDelete();
	}
	
	private void testProducerConstructors()
	{
		// Testing different start and end points
		// Should round the quantity in order to reach p2
		ProducerCurve pc = new ProducerCurve(new Point(1, 1.0), new Point(20, 20.0), 10);
		System.out.println("Custom Producer: " + pc.toString());
		// Should print out normally
		pc = new ProducerCurve(new Point(1, 1.0), new Point(19, 19.0), 10);
		System.out.println("Custom Producer: " + pc.toString());
		// Should only print out p2
		pc = new ProducerCurve(new Point(1, 1.0), new Point(1, 10.0), 10);
		System.out.println("Custom Producer: " + pc.toString());
		// Should swap p1 and p2
		pc = new ProducerCurve(new Point(5, 1.0), new Point(1, 1.0), 10);
		System.out.println("Custom Producer: " + pc.toString());
		// Should print out normally
		pc = new ProducerCurve(new Point(1, 1.0), new Point(10, 10.0), 10);
		System.out.println("Custom Producer: " + pc.toString());
	}
	
	private void testProducerSearch()
	{
		ProducerCurve pc = new ProducerCurve(new Point(1, 1.0), new Point(10, 10.0), 10);
		System.out.println("Original Producer: " + pc.toString());
		Point op = new Point(5, 5.0); // should be true
		System.out.println("Is " + op.toString() + " on the curve? " + pc.search(op));
		op = new Point(0, 0.0); // left of curve (false)
		System.out.println("Is " + op.toString() + " on the curve? " + pc.search(op));
		op = new Point(5, 6.0); // above curve (false)
		System.out.println("Is " + op.toString() + " on the curve? " + pc.search(op));
		op = new Point(-1, -1.0); // left of and illegal? point (false)
		System.out.println("Is " + op.toString() + " on the curve? " + pc.search(op));
		op = new Point(5, 4.0); // below curve (false)
		System.out.println("Is " + op.toString() + " on the curve? " + pc.search(op));
		op = new Point(5, 5.009); // above the curve but within tolerance (true)
		System.out.println("Is " + op.toString() + " on the curve? " + pc.search(op));
		op = new Point(1, 1.0); // first point (true)
		System.out.println("Is " + op.toString() + " on the curve? " + pc.search(op));
		op = new Point(10, 10.0); // last point (true)
		System.out.println("Is " + op.toString() + " on the curve? " + pc.search(op));
	}
	
	private void testProducerAdd()
	{	
		ProducerCurve pc = new ProducerCurve(new Point(1, 1.0), new Point(10, 10.0), 10);
		System.out.println("Original Producer: " + pc.toString());
		Point op = new Point(5, 5.0); // already on the point (ignore)
		pc.add(op);
		System.out.println("Updated Producer: "+ pc.toString());
		op = new Point(-1, 1.0); // negative quantity (ignore)
		pc.add(op);
		System.out.println("Updated Producer: "+ pc.toString());
		op = new Point(1, -1.0); // negative price (ignore)
		pc.add(op);
		System.out.println("Updated Producer: "+ pc.toString());
		op = new Point(12, 12.0); // above and to the right of curve (add to end)
		pc.add(op);
		System.out.println("Updated Producer: "+ pc.toString());
		op = new Point(11, 11.0); // in between two points (add to end then sort)
		pc.add(op);
		System.out.println("Updated Producer: "+ pc.toString());
		op = new Point(5, 7.0); // quantity already on the point (replace);
		pc.add(op);
		System.out.println("Updated Producer: "+ pc.toString());
	}
	
	private void testProducerDelete()
	{
		ProducerCurve pc = new ProducerCurve(new Point(1, 1.0), new Point(10, 10.0), 10);
		System.out.println("Original Producer: " + pc.toString());
		Point op = new Point(5, 5.0); // on the point (delete);
		pc.delete(op);
		System.out.println("Updated Producer: "+ pc.toString());
		op = new Point(11, 11.0); // not on the point (ignore);
		pc.delete(op);
		System.out.println("Updated Producer: "+ pc.toString());
	}
	
	/**
	 * Tests both types of constructors.
	 */
	private void testConsumerConstructors()
	{
		// Testing different start and end points
		// Should round the quantity in order to reach p2
		ConsumerCurve cc = new ConsumerCurve(new Point(1, 20.0), new Point(20, 1.0), 10);
		System.out.println("Custom Consumer: " + cc.toString());
		// Should print out normally
		cc = new ConsumerCurve(new Point(1, 19.0), new Point(19, 1.0), 10);
		System.out.println("Custom Consumer: " + cc.toString());
		// Should only print out p2
		cc = new ConsumerCurve(new Point(1, 1.0), new Point(1, 10.0), 10);
		System.out.println("Custom Producer: " + cc.toString());
		// Should swap p1 and p2
		cc = new ConsumerCurve(new Point(5, 1.0), new Point(1, 1.0), 10);
		System.out.println("Custom Producer: " + cc.toString());
		// Should print out normally
		cc = new ConsumerCurve(new Point(1, 10.0), new Point(10, 1.0), 10);
		System.out.println("Custom Consumer: " + cc.toString());
	}
	
	/**
	 * Tests a variety of Points going through the search method
	 * uses default Points as reference
	 */
	private void testConsumerSearch()
	{
		ConsumerCurve cc = new ConsumerCurve(new Point(1, 10.0), new Point(10, 1.0), 10);
		System.out.println("Original Consumer: " + cc.toString());
		Point op = new Point(5, 6.0); // should be true
		System.out.println("Is " + op.toString() + " on the curve? " + cc.search(op));
		op = new Point(0, 0.0); // left of curve (false)
		System.out.println("Is " + op.toString() + " on the curve? " + cc.search(op));
		op = new Point(5, 7.0); // above curve (false)
		System.out.println("Is " + op.toString() + " on the curve? " + cc.search(op));
		op = new Point(-1, -1.0); // left of and illegal point (false)
		System.out.println("Is " + op.toString() + " on the curve? " + cc.search(op));
		op = new Point(5, 5.0); // below curve (false)
		System.out.println("Is " + op.toString() + " on the curve? " + cc.search(op));
		op = new Point(5, 6.009); // above the curve but within tolerance (true)
		System.out.println("Is " + op.toString() + " on the curve? " + cc.search(op));
		op = new Point(1, 10.0); // first point (true)
		System.out.println("Is " + op.toString() + " on the curve? " + cc.search(op));
		op = new Point(10, 1.0); // last point (true)
		System.out.println("Is " + op.toString() + " on the curve? " + cc.search(op));
	}
	
	/**
	 * Tests  adding a variety of Points
	 * uses default Points as reference
	 */
	private void testConsumerAdd()
	{
		ConsumerCurve cc = new ConsumerCurve(new Point(1, 10.0), new Point(10, 1.0), 10);
		System.out.println("Original Consumer: " + cc.toString());
		Point op = new Point(5, 6.0); // already on the curve (ignore)
		cc.add(op);
		System.out.println("Updated Consumer: "+ cc.toString());
		op = new Point(-1, 1.0); // negative quantity (ignore)
		cc.add(op);
		System.out.println("Updated Consumer: "+ cc.toString());
		op = new Point(1, -1.0); // negative price (ignore)
		cc.add(op);
		System.out.println("Updated Consumer: "+ cc.toString());
		op = new Point(12, 1.0); // right of curve (add to end)
		cc.add(op);
		System.out.println("Updated Consumer: "+ cc.toString());
		op = new Point(11, 1.0); // in between two points (add to end then sort)
		cc.add(op);
		System.out.println("Updated Consumer: "+ cc.toString());
		op = new Point(5, 8.0); // quantity already on the curve (replace);
		cc.add(op);
		System.out.println("Updated Consumer: "+ cc.toString());
	}
	
	/**
	 * Tests deleting a variety of Points
	 * uses default Points as references
	 */
	private void testConsumerDelete()
	{
		// Default constructor
		ConsumerCurve cc = new ConsumerCurve(new Point(1, 10.0), new Point(10, 1.0), 10);
		System.out.println("Original Consumer: " + cc.toString());
		Point op = new Point(5, 6.0); // on the curve (delete);
		cc.delete(op);
		System.out.println("Updated Consumer: "+ cc.toString());
		op = new Point(11, 11.0); // not on the curve (ignore);
		cc.delete(op);
		System.out.println("Updated Consumer: "+ cc.toString());
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TestCurves testCurves = new TestCurves();
	}
}
