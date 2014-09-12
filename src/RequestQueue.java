/**
 * The <CODE>RequestQueue</CODE> class is derived as a subclass of Vector
 * 	from the Java API and contains a queue of requests that a specific 
 * 	elevator can handle.
 * 
 * @author Erica Troge (erica.troge@stonybrook.edu) 106861428
 */
import java.util.Vector;
public class RequestQueue extends Vector<Request> {

	/**
	 * Returns an instance of <code>RequestQueue</code>.
	 * @param - none
	 * @return An instance of RequestQueue.
	 */
	public RequestQueue() {
	}

	/**
	 * A method adds a request to the RequestQueue at the end of it
	 * @param myRequest
	 * 	the specified Request that is to be added
	 * @postcondition
	 * 	the Request is added to the end of the queue
	 */
	public void enqueue(Request myRequest) {
		this.add(myRequest);
	}

	/**
	 * A method that removes the Request from the front of the queue
	 * @return - null
	 * 	if size of the queue is 0
	 * @return 
	 * 	the Request that was removed 
	 */
	public Request dequeue() {
		if (this.size() > 0) {
			return this.remove(0);
		}
		return null;
	}

	/**
	 * A method that gets the size of the queue
	 * @return 
	 * 	the size of the queue using the Vector class method
	 */
	public int size() {
		return super.size();
	}

	/**
	 * A method that sees if the queue is empty
	 * @return 
	 * 	if the queue is empty using the Vector class method
	 */
	public boolean isEmpty() {
		return super.isEmpty();
	}
}
