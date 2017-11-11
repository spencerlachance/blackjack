/** 
 * An enumeration of playing card suits.
 * @author Dr. Mark A. Jones
 */
public enum Suit {
	CLUBS, DIAMONDS, HEARTS, SPADES;
	
	@Override
	public String toString() {
		return name().toLowerCase();
	}
}
