/**
 * The <CODE>BooleanSource</CODE> class generates the probability if
 * 	a person will arrive at the elevator in a given time unit
 * 
 * @author Erica Troge (erica.troge@stonybrook.edu) 106861428
 */
public class BooleanSource {
	private double probability;

	/**
	 * A method that makes sure the probability input of the user is
	 * 	in the correct range
	 * @param prob
	 * 	the probability of a person arriving at the elevator
	 * @throws IllegalArgumentException
	 * 	if probability is not between 0.0 and 1.0
	 */
	public BooleanSource(double prob) throws IllegalArgumentException {
		if (prob < 0.0 || prob > 1.0) {
			throw new IllegalArgumentException(
					"Probability is not between 0.0 and 1.0!");
		} else {
			this.probability = prob;
		}
	}

	/**
	 * A method that returns true for the specified probability
	 * @return
	 * 	if a person arrived at the elevator or not based on the probaility
	 */
	public boolean requestArrived() {
		return (Math.random() < this.probability);
	}
}
