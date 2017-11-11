/**
 * Models a blackjack card.  The value of a blackjack card is computed.
 * @author Mark Jones
 */
@SuppressWarnings("serial")
public class BlackjackGCard extends GCard {

	public BlackjackGCard(Rank r, Suit s) {
		super(r, s);
	}
	
	/**
	 * Convenience method for making a deck of BlackjackGCards.
	 * @return        the new deck
	 */
	public static Deck makeDeck() {
		Deck deck = new Deck();
		for (Suit s : Suit.values()) {
			for (Rank r : Rank.values()) {
				deck.add((Card) new BlackjackGCard(r, s));
			}
		}
		return deck;
	}
	
	/**
	 * The value of a Blackjack card, counting an ACE as 1.
	 */
	public int value() {
		switch(getRank().toString().charAt(0)) {
			case 'a':
				return 1;
			case '2':
				return 2;
			case '3':
				return 3;
			case '4':
				return 4;
			case '5':
				return 5;
			case '6':
				return 6;
			case '7':
				return 7;
			case '8':
				return 8;
			case '9':
				return 9;
			case '1':
				return 10;
			case 'j':
				return 10;
			case 'q':
				return 10;
			case 'k':
				return 10;
			default:
				return 0;
		}
	}
}
