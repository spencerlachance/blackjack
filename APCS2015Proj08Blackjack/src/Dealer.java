/**
 * Models a Dealer in a blackjack game.  The dealer shares attributes of a Player
 * but has other characteristics as well -- has a deck, deals cards, has a hole card, etc. 
 * 
 * @author Mark Jones
 *
 */
public class Dealer extends Player {
	
	Deck deck;   // the deck of cards the dealer deals from
	
	/**
	 * Creates a Dealer and initializes a deck of cards.
	 */
	public Dealer() {
		super();
		deck = BlackjackGCard.makeDeck();
		deck.shuffle();
	}
	
	/**
	 * Deals a card to a player (or himself).
	 * @param player    the player who receives the card.
	 * @return    the card that was dealt
	 */
	public BlackjackGCard deal(Player player) {
		BlackjackGCard c = (BlackjackGCard) deck.deal();
		player.addCard(c);
		return c;
	}

	/**
	 * Deals a card face-up to a player (or himself).
	 * @param player    the player who receives the card.
	 * @return    the card that was dealt
	 */
	public BlackjackGCard dealFaceUp(Player player) {
		BlackjackGCard c = deal(player);
		c.turnFaceUp();
		return c;
	}

	/**
	 * Deals a card face-down to a player (or himself).
	 * @param player    the player who receives the card.
	 * @return    the card that was dealt
	 */
	public BlackjackGCard dealFaceDown(Player player) {
		BlackjackGCard c = deal(player);
		c.turnFaceDown();
		return c;
	}
	
	/**
	 * Clears the state of the Dealer for the next game
	 * -- clears his hand and re-initializes a new deck of cards.
	 */
	@Override
	public void clear() {
		super.clear();
		deck = BlackjackGCard.makeDeck();
		deck.shuffle();
	}
	
	/**
	 * Gets the hole card (the second, initially face-down
	 * card) dealt to the Dealer.
	 * @return   the hole card
	 */
	public BlackjackGCard holeCard() {
		return (BlackjackGCard) getHand().get(1);
	}
	
	/**
	 * The printable String representation of a Dealer.
	 * @return   the printable representation
	 */
	@Override
	public String toString() {
		return "Dealer";
	}
}
