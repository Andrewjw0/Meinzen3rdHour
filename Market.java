package abstractPackage;

public class Market 
{
	private Producer myProducer;
	private Consumer myConsumer;
	
	public Market() 
	{
		// Testing different points WITHIN CURVES
		Point testPoint = new Point(5, 6.0); // above producer, on consumer (accept)
		Negotiate(testPoint);
		testPoint = new Point(5, 5.0); // on producer, below consumer (accept)
		Negotiate(testPoint);
		testPoint = new Point(1, 5.0); // above producer, below consumer (accept)
		Negotiate(testPoint);
		testPoint = new Point(10, 10.0); // on producer, above consumer (reject)
		Negotiate(testPoint);
		testPoint = new Point(10, 1.0); // below producer, on consumer (reject)
		Negotiate(testPoint);
		// Testing different points OUTSIDE CURVES
		testPoint = new Point(11, 1.0); // outside producer range (return furthest point right) (reject)
		Negotiate(testPoint);
		testPoint = new Point(0, 1.0); // zero quant (don't go through)
		Negotiate(testPoint);
		testPoint = new Point(1, 0.0); // zero price (don't go through)
		Negotiate(testPoint);
		testPoint = new Point(-1, 1.0); // negative quant (don't go through)
		Negotiate(testPoint);
		testPoint = new Point(1, -1.0); // negative price (don't go through)
		Negotiate(testPoint);
	}
	
	public void Negotiate(Point p) 
	{
		myProducer = new Producer();
		myConsumer = new Consumer();
		
		if (p.getQuant() <= 0)
		{
			System.out.println("Invalid Quantity");
			return;
		}
		
		if (p.getPrice() <= 0)
		{
			System.out.println("Invalid Price");
			return;
		}
		
		Point myProducerResponse = myProducer.respondToBid(p);
		Point myConsumerResponse = myConsumer.respondToBid(p);
		
		if (myProducerResponse.equals(myConsumerResponse))
		{
			System.out.println("Both accept");
		}
		else
		{
			System.out.println(p.toString() + ": No deal");
		}
	}
	
	public static void main(String[] args)
	{
		Market m1 = new Market();
	}
}
