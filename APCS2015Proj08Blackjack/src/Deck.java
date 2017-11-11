import java.util.ArrayList;
import java.util.Collections;

/** 
 * Models a standard deck of playing cards.
 * @author Dr. Mark A. Jones
 */

@SuppressWarnings("serial")
public class Deck extends ArrayList<Card> {
	
	/** Make a new deck of cards. */
	public Deck() {
		super();
	}
	
	/** Deal a card.
	 * @return  the top card from the deck
	 */
	public Card deal() {
		if (isEmpty()) return null;
		return remove(0);
	}
	
	/** Shuffle the remaining cards in the deck. */
	public void shuffle() {
		Collections.shuffle(this);
	}
}