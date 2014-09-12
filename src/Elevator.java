/**
 * The <CODE>Elevator</CODE> class contains a reference to an
 * 	Elevator object as well as it's status, current floor and the 
 * 	current request it's handling.
 * 
 * @author Erica Troge (erica.troge@stonybrook.edu) 106861428
 */
public class Elevator {

	private int currentFloor;
	private ElevatorStatus elevatorState;
	private Request request;

	/**
	 * Returns an instance of <code>Elevator</code>.
	 * @param - none
	 * @return
     * 	An instance of Elevator with the specified values
	 */
	public Elevator() {
		this.currentFloor = 1;
		this.elevatorState = ElevatorStatus.IDLE;
		this.request = null;
	}

	/**
	 * Gets the current floor of this Elevator
	 * @param - none
	 * @return
	 * 	the current floor of the specified Elevator
	 */
	public int getCurrentFloor() {
		return currentFloor;
	}

	/**
	 * Sets the current floor of this Elevator
	 * @param currentFloor
	 * @postcondition
	 * 	sets the current floor to the specified value
	 */
	public void setCurrentFloor(int currentFloor) {
		this.currentFloor = currentFloor;
	}

	/**
	 * Gets the elevator state of this Elevator
	 * @param - none
	 * @return
	 * 	the elevator state of the specified Elevator
	 */
	public ElevatorStatus getElevatorState() {
		return elevatorState;
	}

	/**
	 * Sets the elevator state of this Elevator
	 * @param elevatorState
	 * @postcondition
	 * 	sets the elevator state to the specified value
	 */
	public void setElevatorState(ElevatorStatus elevatorState) {
		this.elevatorState = elevatorState;
	}

	/**
	 * Gets the current Request of this Elevator
	 * @param - none
	 * @return
	 * 	the current Request of the specified Elevator
	 */
	public Request getRequest() {
		return request;
	}

	/**
	 * Sets the current Request of this Elevator
	 * @param request
	 * @postcondition
	 * 	sets the current Request to the specified Request object
	 */
	public void setRequest(Request request) {
		this.request = request;
	}
}
