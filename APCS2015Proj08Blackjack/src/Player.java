/**
 * Models a Player in a Blackjack game.
 * @author Mark Jones
 */
public class Player {
	
	BlackjackHand hand;   // the Player's hand
	
	/**
	 * Creates a blackjack Player, with a hand.
	 */
	public Player() {
		hand = new BlackjackHand();
	}
	
	/**
	 * Getter for the Player's hand.
	 * @return   the hand
	 */
	public BlackjackHand getHand() {
		return hand;
	}

	/**
	 * Adds a card to the Player's hand.
	 * @param c    the card to be added
	 * @return     the card that was added
	 */
	public BlackjackGCard addCard(BlackjackGCard c) {
		hand.add(c);
		return c;
	}	
	
	/**
	 * Gets the value of the Player's hand.
	 * @return   the value
	 */
	public int handValue() {
		return hand.handValue();
	}

	/**
	 * Clears the state of the Player for the next game
	 * -- clears his hand.
	 */
	public void clear() {
		hand.clear();
	}
	
	/**
	 * The printable String representation of a Player.
	 * @return   the printable representation
	 */
	@Override
	public String toString() {
		return "Player";
	}

	/**
	 * Does the Player have a "blackjack" hand?
	 * @return    true if the Player has a blackjack, false otherwise
	 */
	public boolean hasBlackjack() {
		return hand.isBlackjack();
	}
}
