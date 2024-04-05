package application;

import java.util.Locale;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import model.entities.Contract;
import model.entities.Installment;
import model.services.ContractService;
import model.services.PaypalService;

public class Program {
	
	public static void main(String[] args) {
		
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		
		DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		
		//Contract instance
		System.out.println("Enter the contract data");
		System.out.print("Number: ");
		int number = sc.nextInt();
		
		sc.nextLine();
		System.out.print("Date (dd/MM/yyyy): ");
		LocalDate date = LocalDate.parse(sc.nextLine(), fmt);
		
		System.out.print("Contract value: ");
		double totalValue = sc.nextDouble();
		
		System.out.print("Enter the number of installments: ");
		int months = sc.nextInt();
		
		Contract contract = new Contract(number, date, totalValue);
		
		//ContractService instance
		ContractService contractService = new ContractService(new PaypalService());
		
		contractService.processContract(contract, months);
		
		System.out.println("Parcelas");
		for (Installment installment : contract.getInstallments()) {
			
			System.out.println(installment.getDueDate().format(fmt) + " - " + String.format("%.2f", installment.getAmount()));
			
		}
		
		sc.close();
		
	}
	
}
