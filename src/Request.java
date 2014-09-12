/**
 * The <CODE>Request</CODE> class contains various information about a specific
 * request of a person at an elevator.
 * 
 * @author Erica Troge (erica.troge@stonybrook.edu) 106861428
 */
public class Request {
	private int sourceFloor;
	private int destinationFloor;
	private int timeEntered;

	/**
	 * Returns an instance of <code>Request</code>.
	 * @param numberOfFloors
	 * 	the number of floors in the building
	 * @return An instance of Request with the specified values.
	 */
	public Request(int numberOfFloors) {
		this.sourceFloor = (int) (Math.random() * numberOfFloors + 1.0);
		this.destinationFloor = (int) (Math.random() * numberOfFloors + 1.0);
	}

	/**
	 * Get the request's source floor
	 * @param - none
	 * @return the source/originating floor of the request
	 */
	public int getSourceFloor() {
		return sourceFloor;
	}

	/**
	 * Set the source floor of the request
	 * @param sourceFloor
	 * 	the source/originating floor of the request
	 * @postcondition source floor is changed to the given parameter
	 */
	public void setSourceFloor(int sourceFloor) {
		this.sourceFloor = sourceFloor;
	}

	/**
	 * Get the request's destination floor
	 * @param - none
	 * @return the destination floor of the request
	 */
	public int getDestinationFloor() {
		return destinationFloor;
	}

	/**
	 * Set the destination floor of the request
	 * @param destinationFloor
	 * 	the destination floor of the request
	 * @postcondition destination floor is changed to the given parameter
	 */
	public void setDestinationFloor(int destinationFloor) {
		this.destinationFloor = destinationFloor;
	}

	/**
	 * Get time that this request was placed on the queue
	 * @param - none
	 * @return the time this request was placed on the queue
	 */
	public int getTimeEntered() {
		return timeEntered;
	}

	/**
	 * Set the time that this request was placed on the queue
	 * @param timeEntered
	 * 	the time that this request was placed on the queue
	 * @postcondition time entered is changed to the given parameter
	 */
	public void setTimeEntered(int timeEntered) {
		this.timeEntered = timeEntered;
	}
}
