/**
 * The <CODE>Analyzer</CODE> class contains the main method
 * 	that lets the user input their preferred values for the
 * 	Elevator simulation and runs the simulation.
 * 
 * @author Erica Troge (erica.troge@stonybrook.edu) 106861428
 */
import java.util.Scanner;
public class Analyzer {
	static Scanner input = new Scanner(System.in);
	static boolean validInput = true;
	static int answer;

	/**
	 * The main method prompts the user to enter specific information, such
	 * 	as the probability of arrival for Requests, number of floors, number
	 * 	of elevators and length of the simulation and then runs the 
	 * 	simulation.
	 * @param args
	 */
	public static void main(String[] args) {	
		System.out.println("Welcome to the Elevator simulator!\n");
		System.out.println("Which method would you like to run? Type \'0\' " +
				"for Regular and \'1\' for Optimal: ");
		answer = input.nextInt();
		if(answer != 1 && answer != 0){
			System.out.println("ERROR: Wrong input, restart the program!");
			System.exit(0);
		}
		do {
			runProgram();
		} while (validInput == false);
	}

	/**
	 * A method that checks for invalid inputs from the user and runs
	 * 	the simulation program
	 */
	private static void runProgram() {
		System.out.print("Please enter the probability of " +
				"arrival for Requests: ");
		double probOfRequests = input.nextDouble();
		if(probOfRequests < 0.0 || probOfRequests > 1.0){
			System.out.println("ERROR: Probability is not in the " +
					"correct range. Let's start over!\n");
			validInput = false;
			return;
		}

		System.out.print("Please enter the number of floors: ");
		int numberOfFloors = input.nextInt();
		if(numberOfFloors < 2){
			System.out.println("ERROR: Number of floors is less than 2. " +
					"Let's start over!\n");
			validInput = false;
			return;
		}

		System.out.print("Please enter the number of elevators: ");
		int numberOfElevators = input.nextInt();
		if(numberOfElevators < 1){
			System.out.println("ERROR: Number of elevators is less than 1. " +
					"Let's start over!\n");
			validInput = false;
			return;
		}

		System.out.print("Please enter the length of the simulation" +
				" (in time units): ");
		int lengthOfSim = input.nextInt();
		if(lengthOfSim < 1){
			System.out.println("ERROR: Time units are less than 1. " +
					"Let's start over!\n");
			validInput = false;
			return;
		}
		validInput = true;
		if(answer == 0){
			try {
				Simulator.simulate(probOfRequests, numberOfFloors, 
						numberOfElevators, lengthOfSim);
			}
			catch(IllegalArgumentException e){
				System.out.println("\nIssue: " + e.getMessage());
			}
		}
		else if(answer == 1){
			try {
				OptimalSimulator.simulate(probOfRequests, numberOfFloors, 
						numberOfElevators, lengthOfSim);
			}
			catch(IllegalArgumentException e){
				System.out.println("\nIssue: " + e.getMessage());
			}
		}
	}
}

