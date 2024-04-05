package model.services;

import java.util.List;
import java.util.ArrayList;
import java.time.LocalDate;

import model.entities.Contract;
import model.entities.Installment;

public class ContractService {
	
	private OnlinePaymentService onlinePaymentService;

	public ContractService(OnlinePaymentService onlinePaymentService) {
		this.onlinePaymentService = onlinePaymentService;
	}
	
	public void processContract(Contract contract, Integer months) {
		
		List<Installment> installments = new ArrayList<>();
		double installmentValue = contract.getTotalValue() / months;
		
		for (int i=1; i<=months; i++) {
			double amount = installmentValue + onlinePaymentService.interest(installmentValue, i);
			amount += onlinePaymentService.paymentFee(amount);
			
			LocalDate dueDate = contract.getDate().plusMonths(i);
			
			Installment installment = new Installment(dueDate, amount);
			
			installments.add(installment);
		}
		
		contract.setInstallments(installments);
		
	}
	
}
