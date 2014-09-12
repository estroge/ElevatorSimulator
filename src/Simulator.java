/**
 * The <CODE>Simulator</CODE> class contains a single method
 * 	that takes in the user's inputs and outputs
 * 	the total wait time, total requests and average wait time of 
 * 	the simulation.
 * 
 * @author Erica Troge (erica.troge@stonybrook.edu) 106861428
 */
public class Simulator {

	/**
	 * A method that simulates a building's elevator system that processes 
	 * 	passenger Requests
	 * @param probOfRequests
	 * 	the probability that a new Request will arrive
	 * @param numberOfFloors
	 * 	the number of floors in the building
	 * @param numberOfElevators
	 * 	the number of Elevators in the building
	 * @param lengthOfSimulation
	 * 	the length of the simulation in time units
	 * @postcondition
	 * 	Prints the total wait time, total requests and average wait time of
	 * 	the simulation.
	 */
	public static void simulate(double probOfRequests, int numberOfFloors, 
			int numberOfElevators, int lengthOfSimulation) {

		Elevator[] elevators = new Elevator[numberOfElevators];
		BooleanSource probability = new BooleanSource(probOfRequests);
		RequestQueue queue = new RequestQueue();
		int timeUnits = 0;
		int totalWaitTime = 0; 	
		int totalRequests = 0;
		double averageWaitTime = 0.0;
		for(int i = 0; i < numberOfElevators; i++){
			elevators[i] = new Elevator();
		}

		while(timeUnits <= lengthOfSimulation){
			timeUnits++;

			if(probability.requestArrived() == true) { //simulate
				queue.enqueue(new Request(numberOfFloors));

				totalRequests++;
			}
			if(!queue.isEmpty()){
				for(int i = 0; i < elevators.length; i++){
					if(elevators[i].getElevatorState() == ElevatorStatus.IDLE){
						elevators[i].setElevatorState
							(ElevatorStatus.TO_SOURCE);
						elevators[i].setRequest(queue.dequeue());
						if(queue.isEmpty()){
							break;
						}
					}
				}
				totalWaitTime += queue.size();
			}
			for(int i = 0; i < elevators.length; i++){
				if(elevators[i].getElevatorState() == 
						ElevatorStatus.TO_SOURCE){
					if(elevators[i].getRequest().getSourceFloor() == 
							elevators[i].getCurrentFloor()){
						if(elevators[i].getCurrentFloor() == elevators[i]
								.getRequest().getDestinationFloor()){
							elevators[i].setElevatorState(ElevatorStatus.IDLE);
						}
						//totalWaitTime += 0;
					}
					else{
						totalWaitTime++;
						if(elevators[i].getCurrentFloor() < elevators[i]
								.getRequest().getSourceFloor()){
							elevators[i].setCurrentFloor(elevators[i]
									.getCurrentFloor()+ 1);
						}
						else{
							elevators[i].setCurrentFloor(elevators[i]
									.getCurrentFloor()- 1);
						}
						if(elevators[i].getCurrentFloor() == elevators[i]
								.getRequest().getSourceFloor()){
							if(elevators[i].getCurrentFloor() == elevators[i]
									.getRequest().getDestinationFloor()){
								elevators[i].setElevatorState
									(ElevatorStatus.IDLE);
							}
							else{
								elevators[i].setElevatorState
									(ElevatorStatus.TO_DESTINATION);
							}
						}

					}
				}
				else if(elevators[i].getElevatorState() == 
						ElevatorStatus.TO_DESTINATION){
					if(elevators[i].getCurrentFloor() < elevators[i]
							.getRequest().getDestinationFloor()){
						elevators[i].setCurrentFloor(elevators[i]
								.getCurrentFloor()+ 1);
					}
					else{
						elevators[i].setCurrentFloor(elevators[i]
								.getCurrentFloor()- 1);
					}
					if(elevators[i].getCurrentFloor() == elevators[i]
							.getRequest().getDestinationFloor()){
						elevators[i].setElevatorState(ElevatorStatus.IDLE);
					}
				}
			}

		} 	
		System.out.print("\n");
		System.out.println("Total Wait Time: " + totalWaitTime);
		System.out.println("Total Requests: " + totalRequests);
		averageWaitTime = totalWaitTime/(double)totalRequests;
		if(totalRequests != 0){
			System.out.printf("Average Wait Time: %.2f", averageWaitTime);
		}
		else{
			System.out.printf("Average Wait Time: 0.00");
		}

	}
}		

