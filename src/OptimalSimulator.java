import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class OptimalSimulator {
	
	public static void simulate(double probOfRequests, int numberOfFloors, 
			int numberOfElevators, int lengthOfSimulation) {
		
		Elevator[] elevators = new Elevator[numberOfElevators];
		BooleanSource probability = new BooleanSource(probOfRequests);
		RequestQueue queue = new RequestQueue();
		int timeUnits = 0; //time it takes for the elevator to move up or down one floor.
		int totalWaitTime = 0; //time until the elevator arrives at the source floor.
		int totalRequests = 0;
		double averageWaitTime = 0.0;
		for(int i = 0; i < numberOfElevators; i++){
			elevators[i] = new Elevator();
		}
		Boolean[] elevatorDirection = new Boolean[numberOfElevators];
		for(int i = 0; i < numberOfElevators; i++){
			elevatorDirection[i] = true; //going up to start
		}

		List<Vector<Request>> listOfRequests = new ArrayList<Vector<Request>>();
		for(int i = 0; i < numberOfElevators; i++){
			listOfRequests.add(i, new RequestQueue());
		}

		
		//code start
		while(timeUnits <= lengthOfSimulation){
			timeUnits++;
			
			if(probability.requestArrived() == true) { //if theres a new request put them on the queue
				queue.enqueue(new Request(numberOfFloors));
				totalRequests++;
			}
			if(!queue.isEmpty()){ //if the queue is not empty proceed.
				for(int i = 0; i < elevators.length; i++){
					if(elevators[i].getElevatorState() == ElevatorStatus.IDLE){ //find an idle elevator
						elevators[i].setElevatorState(ElevatorStatus.TO_SOURCE); //set this elevator to go to source
						listOfRequests.get(i).add(queue.dequeue());//add first in queue to coress. elevator
						
						if(listOfRequests.get(i).get(0).getSourceFloor() - elevators[i].getCurrentFloor() >= 0 ){
							elevatorDirection[i] = true; //going up
						}	//have to remember to set elevators[i] currentFloor later
						else { //going down
							elevatorDirection[i] = false;
						}
						if(queue.isEmpty()){
							break;
						}
					}
				}
				
			}
			for(int i = 0; i < elevators.length; i++){ //for every elevator
				if(elevators[i].getElevatorState() == ElevatorStatus.TO_SOURCE){ //if going to a source
					if(listOfRequests.get(i).size() != 0){
						if(listOfRequests.get(i).get(0).getSourceFloor() == elevators[i].getCurrentFloor()){
							if(elevators[i].getCurrentFloor() == listOfRequests.get(i).get(0).getDestinationFloor()){
								elevators[i].setElevatorState(ElevatorStatus.IDLE);
								//same floor for all three statuses, don't move
							}
						}
					
					else{
						//totalWaitTime++; //else they are waiting
						if(elevators[i].getCurrentFloor() < listOfRequests.get(i).get(0).getSourceFloor()){
							//if the current floor is still not the source then go up one level
							elevators[i].setCurrentFloor(elevators[i].getCurrentFloor()+ 1);
							elevatorDirection[i] = true;
							//have to add more peeps
							if(!queue.isEmpty()){
								for(int k = 0; k < queue.size(); k++){
									if(queue.get(k).getSourceFloor() == elevators[i].getCurrentFloor() &&
											queue.get(k).getDestinationFloor() > queue.get(k).getSourceFloor()){
										listOfRequests.get(i).add(queue.remove(k));
									}
								}
								//go through queue, find peeps with source on this floor going up, deque them
								//and add them to the list
							}
							//if anyone in this elevators dest is on this current floor take them off
							for(int k = 0; k < listOfRequests.get(i).size(); k++){
								if(listOfRequests.get(i).get(k).getDestinationFloor() == elevators[i].getCurrentFloor()){
									listOfRequests.get(i).remove(k);
								}
							}
						}
						else{
							//if the current floor is still not the source then go down one level
							elevators[i].setCurrentFloor(elevators[i].getCurrentFloor()- 1);
							elevatorDirection[i] = false;
							//have to add more peeps
							if(!queue.isEmpty()){
								for(int k = 0; k < queue.size(); k++){
									if(queue.get(k).getSourceFloor() == elevators[i].getCurrentFloor() &&
											queue.get(k).getDestinationFloor() < queue.get(k).getSourceFloor()){
										listOfRequests.get(i).add(queue.remove(k));
									}
								}
								//go through queue, find peeps with source on this floor going down, deque them
								//and add them to the list
							}
							//if anyone in this elevators dest is on this current floor take them off
							for(int k = 0; k < listOfRequests.get(i).size(); k++){
								if(listOfRequests.get(i).get(k).getDestinationFloor() == elevators[i].getCurrentFloor()){
									listOfRequests.get(i).remove(k);
								}
							}
						}
						int highestFloor = 0;
						if(listOfRequests.get(i).size() != 0){
							if(elevators[i].getCurrentFloor() == listOfRequests.get(i).get(0).getSourceFloor()){
								//if they are now equal after the increment change to going to destination now
								if(elevators[i].getCurrentFloor() == listOfRequests.get(i).get(0).getDestinationFloor()){
									//check if others are still going up, if not IDLE, if so change dest to the next in list
									for(int k = 0; k < listOfRequests.get(i).size(); k++){
										if(listOfRequests.get(i).get(k).getDestinationFloor() > listOfRequests.get(i).get(0).getDestinationFloor()){
											highestFloor = listOfRequests.get(i).get(k).getDestinationFloor();
										}
									}
									if(highestFloor > listOfRequests.get(i).get(0).getDestinationFloor()){
										//what about others?
										listOfRequests.get(i).remove(0);
									}
									else {
										//is highest floor is greater then elevator dest floor then set to new dest, otherwise idle
										elevators[i].setElevatorState(ElevatorStatus.IDLE);
										listOfRequests.get(i).removeAllElements();
									}

								}
								else{
									//else change to going to dest
									elevators[i].setElevatorState(ElevatorStatus.TO_DESTINATION);
								}
							}
						} //end != 0

					}
					}
				}
				//never pick up people when going to destination
				else if(elevators[i].getElevatorState() == ElevatorStatus.TO_DESTINATION){ //now going to dest
					if(elevators[i].getCurrentFloor() < listOfRequests.get(i).get(0).getDestinationFloor() &&
							elevatorDirection[i] == true){
						//if anyone in the queue has a sf == to current then deque them.
						for(int k = 0; k < queue.size(); k++){
							if(queue.get(k).getSourceFloor() == elevators[i].getCurrentFloor()){
								listOfRequests.get(i).add(queue.remove(k));
							}
						}
						elevators[i].setCurrentFloor(elevators[i].getCurrentFloor() + 1);
					}
					else{
						for(int k = 0; k < queue.size(); k++){
							if(queue.get(k).getSourceFloor() == elevators[i].getCurrentFloor()){
								listOfRequests.get(i).add(queue.remove(k));
							}
						}
						elevators[i].setCurrentFloor(elevators[i].getCurrentFloor() - 1);
					}
					if(elevators[i].getCurrentFloor() == listOfRequests.get(i).get(0).getDestinationFloor()){
						int highestFloor = 0;
						//kick them off check rest of list to see if keep going up
						for(int k = 0; k < listOfRequests.get(i).size(); k++){
							if(listOfRequests.get(i).get(k).getDestinationFloor() > listOfRequests.get(i).get(0).getDestinationFloor()){
								highestFloor = listOfRequests.get(i).get(k).getDestinationFloor();
							}
						}
						if(highestFloor > listOfRequests.get(i).get(0).getDestinationFloor()){
							listOfRequests.get(i).remove(0);
						}
						else {
							//is highest floor is greater then elevator dest floor then set to new dest, otherwise idle
							elevators[i].setElevatorState(ElevatorStatus.IDLE);
							listOfRequests.get(i).removeAllElements();
						}
						
						
					}
				}
			}
			totalWaitTime += queue.size(); //calc time for all waiting peeps
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






/*
 * Replace the following assumptions by the simulation rules described above:

	Elevators now also have a direction, up or down. As an elevator passes a floor, all waiting passengers are picked 
	up that want to go in that direction.
	An elevator moves until it no longer has any requests (sources or destinations) in the direction it was moving. 
	At this point, it is now idle. In the documentation of this class describe what algorithm is being used to 
	service the requests.
	Elevators can carry an unlimited number of passengers.
	Idle elevators can move in any direction to pick up requests.
	You may keep track of elevator directions in an additional boolean array (true = up, false = down). 
	You may use any additional data structures necessary to keep track of requests that have not yet 
	been handled (such as a list for requests on each floor).

9. Update the main method of the Analyzer class so it asks the user which method (regular or optimal) must be run. In both cases, you must check the input parameters given to you by the user. Make it clear what option (yes/no, 0/1, etc.) runs which algorithm (regular or optimal).
 */
