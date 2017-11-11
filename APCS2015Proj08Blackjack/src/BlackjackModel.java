/**
 * Abstractly models a Blackjack game.
 * @author Mark Jones
 *
 */
public class BlackjackModel {

	// instance variables
	BlackjackView bv;
	Player player = new Player();
	Dealer dealer = new Dealer();
	BlackjackGCard topCard = (BlackjackGCard) dealer.deal(player);
	int wins = 0, losses = 0, ties = 0;

	/**
	 * Create a BlackjackModel (given a BlackjackView for notifications).
	 * @param blackjackView
	 */
	public BlackjackModel(BlackjackView blackjackView) {
		bv = blackjackView;
		player.clear();
		dealer.clear();
	}
	
	/**
	 * Starts a Blackjack Game
	 */
	public void startGame() {
		bv.cardDealtToPlayerNotification(dealer.dealFaceUp(player));
		bv.cardDealtToDealerNotification(dealer.dealFaceUp(dealer));
		bv.cardDealtToPlayerNotification(dealer.dealFaceUp(player));
		bv.cardDealtToDealerNotification(dealer.dealFaceDown(dealer));
	}
	
	/**
	 * Gives the player another card
	 */
	public void hit() {
		bv.cardDealtToPlayerNotification(dealer.dealFaceUp(player));
	}
	
	/**
	 * Deals another card to the dealer according to the house rules and then decides the winner.
	 */
	public void stay() {
		while (dealer.handValue() < 17)
			bv.cardDealtToDealerNotification(dealer.dealFaceUp(dealer));

		if (player.handValue() > 21 && dealer.handValue() > 21) {
			ties++;
			bv.bothBustNotification(wins, losses, ties);
			return;
		}
		if (player.handValue() > 21 && dealer.handValue() < 21) {
			losses++;
			bv.youBustDealerWinsNotification(wins, losses, ties);
			return;
		}
		if (player.handValue() < 21 && dealer.handValue() > 21) {
			wins++;
			bv.dealerBustYouWinNotification(wins, losses, ties);
			return;
		}
		if (player.handValue() <= 21 && dealer.handValue() <= 21 && player.handValue() > dealer.handValue()) {
			wins++;
			bv.youBeatDealerNotification(wins, losses, ties);
			return;
		}
		if (player.handValue() <= 21 && dealer.handValue() <= 21 && player.handValue() < dealer.handValue()) {
			losses++;
			bv.dealerBeatsYouNotification(wins, losses, ties);
			return;
		}
		if (player.hasBlackjack() && !dealer.hasBlackjack()) {
			wins++;
			bv.youBeatDealerNotification(wins, losses, ties);
			return;
		}
		if (!player.hasBlackjack() && dealer.hasBlackjack()) {
			losses++;
			bv.dealerBeatsYouNotification(wins, losses, ties);
			return;
		}
		if (player.handValue() == dealer.handValue()) {
			ties++;
			bv.bothTieNotification(wins, losses, ties);
			return;
		}
	}
	
	/**
	 * Stops the current game.
	 */
	public void quitGame() {
		losses++;
		bv.quitGameNotification(wins, losses, ties);
	}
}
