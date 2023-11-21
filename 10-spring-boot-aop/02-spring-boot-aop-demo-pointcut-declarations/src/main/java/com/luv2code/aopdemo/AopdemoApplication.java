package com.luv2code.aopdemo;

import com.luv2code.aopdemo.dao.AccountDAO;
import com.luv2code.aopdemo.dao.MembershipDAO;
import com.luv2code.aopdemo.service.TrafficFortuneService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class AopdemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(AopdemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner (AccountDAO theAccountDAO,
												MembershipDAO theMembershipDAO,
												TrafficFortuneService theTrafficFortuneService) {
		return runner -> {
			// demoTheBeforeAdvice(theAccountDAO, theMembershipDAO);
			// demoTheAfterReturningAdvice(theAccountDAO);
			// demoTheAfterThrowingAdvice(theAccountDAO)
			// demoTheAfterAdvice(theAccountDAO);
			// demoTheAroundAdvice(theTrafficFortuneService);
			// demoTheAroundAdviceHandleException(theTrafficFortuneService);
			demoTheAroundAdviceRethrowException(theTrafficFortuneService);
		};
	}

	private void demoTheAroundAdviceRethrowException(TrafficFortuneService theTrafficFortuneService) {
		System.out.println("\n\nMain program: demoTheAroundAdviceRethrowException");
		System.out.println("\nCalling getFortune()");
		boolean tripWire = true;
		System.out.println("\nMy fortune is: " + theTrafficFortuneService.getFortune(tripWire));
		System.out.println("Finished");
	}

	private void demoTheAroundAdviceHandleException(TrafficFortuneService theTrafficFortuneService) {
		System.out.println("\n\nMain program: demoTheAroundAdviceHandleException");
		System.out.println("\nCalling getFortune()");
		boolean tripWire = true;
		System.out.println("\nMy fortune is: " + theTrafficFortuneService.getFortune(tripWire));
		System.out.println("Finished");
	}

	private void demoTheAroundAdvice(TrafficFortuneService theTrafficFortuneService) {
		System.out.println("\n\nMain program: demoTheAroundAdvice");
		System.out.println("\nCalling getFortune()");
		System.out.println("\nMy fortune is: " + theTrafficFortuneService.getFortune());
		System.out.println("Finished");
	}

	private void demoTheAfterAdvice(AccountDAO theAccountDAO) {
		// call the method to find the accounts
		List<Account> theAccounts = null;

		try {
			// add a boolean flag to simulate exceptions
			boolean tripWire = false;

			theAccounts = theAccountDAO.findAccounts(tripWire);
		} catch(Exception e) {
			System.out.println("\n\nMain program ... caught exception: " + e);
		}

		// display the accounts
		System.out.println("\n\nMain program: demoTheAfterAdvice");
		System.out.println("-------------------");
		System.out.println(theAccounts);
		System.out.println("\n");
	}

	private void demoTheAfterThrowingAdvice(AccountDAO theAccountDAO) {
		// call the method to find the accounts
		List<Account> theAccounts = null;

		try {
			// add a boolean flag to simulate exceptions
			boolean tripWire = true;

			theAccounts = theAccountDAO.findAccounts(tripWire);
		} catch(Exception e) {
			System.out.println("\n\nMain program ... caught exception: " + e);
		}

		// display the accounts
		System.out.println("\n\nMain program: demoTheAfterThrowingAdvice");
		System.out.println("-------------------");
		System.out.println(theAccounts);
		System.out.println("\n");
	}

	private void demoTheAfterReturningAdvice(AccountDAO theAccountDAO) {
		// call the method to find the accounts
		List<Account> theAccounts = theAccountDAO.findAccounts();

		// display the accounts
		System.out.println("\n\nMain program: demoTheAfterReturningAdvice");
		System.out.println("-------------------");
		System.out.println(theAccounts);
		System.out.println("\n");
	}

	private void demoTheBeforeAdvice(AccountDAO theAccountDAO, MembershipDAO theMembershipDAO) {
		// call the business method
		Account theAccount = new Account();
		theAccount.setName("Madhu");
		theAccount.setLevel("Platinum");
		theAccountDAO.addAccount(theAccount, true);

		// call the accountDAO getter/setter methods
		theAccountDAO.setName("foobar");
		theAccountDAO.setServiceCode("silver");

		String name = theAccountDAO.getName();
		String code = theAccountDAO.getServiceCode();

		// call dowork method from AccountDAO
		theAccountDAO.doWork();

		// call the membership business method
		theMembershipDAO.addSillyMember();

		// call the membership boolean method
		theMembershipDAO.addBooleanMember();

		// call the membership sleep method
		theMembershipDAO.goToSleep();

	}
}
