/**
 * A BlackjackHand is a Hand with BlackjackCards in it.
 * This is simpler than parameterizing Hand<BlackjackCard>.
 * A BlackjackHand knows how to value itself, determine if
 * a hand is a blackjack, etc.
 * 
 * @author Dr. Mark A. Jones
 */
@SuppressWarnings("serial")
public class BlackjackHand extends Hand {

	/**
	 * Creates a BlackjackHand.
	 */
	public BlackjackHand() {
		super();
	}
	
	/**
	 * Returns the highest legal value for a blackjack hand.
	 * @return
	 */
	public int handValue() {
		int handValue = 0;
		BlackjackGCard card;
		for (int i = 0; i < size(); i++) {
			card = (BlackjackGCard) get(i);
			handValue += card.value();
		}
		if (hasAce() && 21 - handValue >= 10) {	// if the hand has an ace and counting it as 11 would keep the total
			handValue += 10;					// under 21, it is counted as 11
		}
		return handValue;
	}

	/**
	 * Determines whether a hand is a "blackjack" (a two-card hand worth 21).
	 * @return  true if a blackjack hand, false otherwise
	 */
	public boolean isBlackjack() {
		if (size() == 2 && handValue() == 21)
			return true;
		else
			return false;
	}
	
	/**
	 * Determines if a hand has an ace in it.
	 * @return	true if the hand has an ace
	 */
	public boolean hasAce() {
		BlackjackGCard card;
		for(int i = 0; i < size(); i++) {
			card = (BlackjackGCard) get(i);
			if (card.getRank().toString().equals("a")) {
				return true;
			}
		}
		return false;
	}
}
