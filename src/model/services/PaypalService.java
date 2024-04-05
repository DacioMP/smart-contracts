package model.services;

public class PaypalService implements OnlinePaymentService {

	private final Double paypalInterest = 0.01;
	private final Double paypalFee = 0.02;
	
	
	public double paymentFee(double amount) {
		return amount * paypalFee;
	}
	
	public double interest(double amount, int months) {		
		
		return (amount * paypalInterest) * months;
	}
	
}
