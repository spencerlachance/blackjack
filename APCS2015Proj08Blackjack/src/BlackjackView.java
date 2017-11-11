public interface BlackjackView {

	/**
	 * Place the card dealt to the player on the canvas.
	 * The card size is rescaled to be consistent with the current deck image size.
	 * @param card  the card to be added
	 */
	void cardDealtToPlayerNotification(BlackjackGCard card);

	/**
	 * Place the card dealt to the dealer on the canvas.
	 * The card size is rescaled to be consistent with the current deck image size.
	 * @param card  the card to be added
	 */
	void cardDealtToDealerNotification(BlackjackGCard card);

	/**
	 * End of game result notification. Dealer and player both bust.
	 */
	void bothBustNotification(int wins, int losses, int ties);

	/**
	 * End of game result notification. Dealer wins, player busts.
	 */
	void youBustDealerWinsNotification(int wins, int losses, int ties);

	/**
	 * End of game result notification. Dealer busts, player wins.
	 */
	void dealerBustYouWinNotification(int wins, int losses, int ties);

	/**
	 * End of game result notification. Player beats dealer.
	 */
	void youBeatDealerNotification(int wins, int losses, int ties);

	/**
	 * End of game result notification. Dealer beats player.
	 */
	void dealerBeatsYouNotification(int wins, int losses, int ties);

	/**
	 * End of game result notification. Dealer and player tie.
	 */
	void bothTieNotification(int wins, int losses, int ties);

	/**
	 * End of game result notification. Player quits game and loses.
	 */
	void quitGameNotification(int wins, int losses, int ties);
}
