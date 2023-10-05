package abstractPackage;

public class Market 
{
	private Producer myProducer;
	private Consumer myConsumer;
	
	public Market() 
	{
		myProducer = new Producer();
		myConsumer = new Consumer();
		
		Point startPoint = new Point(10, 1.0);
		
		Point myProducerResponse = myProducer.respondToBid(startPoint);
		Point myConsumerResponse = myConsumer.respondToBid(startPoint);
		
		if (myProducerResponse.equals(myConsumerResponse))
		{
			System.out.println("Both accept");
		}
		else
		{
			System.out.println("No deal");
			System.out.println("Producer: " + myProducerResponse.toString());
			System.out.println("Consumer:" + myConsumerResponse.toString());
		}
	}
	
	public static void main(String[] args)
	{
		Market m1 = new Market();
	}
}
