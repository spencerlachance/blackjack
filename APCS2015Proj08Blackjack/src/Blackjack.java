import java.awt.Color;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JLabel;

import acm.graphics.GLabel;
import acm.program.*;

/**
 * Blackjack game.
 * @author Mark Jones
 * @version 1.0
 */
@SuppressWarnings("serial")
public class Blackjack extends GraphicsProgram implements BlackjackView {
	
	private static final int INITIAL_WIDTH = 700;
	private static final int INITIAL_HEIGHT = 300;
	private BlackjackModel bm;
	private BlackjackGCard topCard;
	private Player player;
	private Dealer dealer;
	private int pHandSize = -1, dHandSize = -1;	// used to stagger the cards in each player's hands
	private JLabel nLabel = new JLabel();	// top notification label
	private JLabel sLabel = new JLabel();	// wins, losses, ties
	GLabel deckLabel = new GLabel("Deck");	// only declared to use its size to place each label above cards
	private boolean gameIsOver = false;		// a variable that freezes buttons once a game is finished

	/**
	 * Entry point when running Blackjack as an application.
	 * @param args
	 */
	public static void main(String[] args) {
		(new Blackjack()).start();
	}

	/**
	 * Constructor for Blackjack when running as an application.
	 */
	public Blackjack() {
	}

	/** 
	 * Create the Model (with this for callbacks).
	 * Set up the GUI.
	 */
	public void init() {

		bm = new BlackjackModel(this);
		player = bm.player;	// the same player and dealer are taken from the model
		dealer = bm.dealer;

		setSize(INITIAL_WIDTH, INITIAL_HEIGHT);
		setBackground(Color.LIGHT_GRAY);

		nLabel.setText("Welcome to Blackjack, press hit or stay to get started!");	// Setting up the swing interactors
		add(nLabel, NORTH);
		add(new JButton("New Game"), SOUTH);
		add(new JButton("Quit Game"), SOUTH);
		add(new JButton("Hit"), SOUTH);
		add(new JButton("Stay"), SOUTH);
		sLabel.setText("0 wins, 0 losses, 0 ties");
		add(sLabel, SOUTH);
		
		addActionListeners();
	}
	
	/**
	 * Run a new round of blackjack.
	 */
	public void run() {
		for(int i = 0; i < 51; i++) {	// puts a 52 card deck onto the canvas
			topCard = (BlackjackGCard) dealer.deck.get(i);
			add(topCard, 0.05*getWidth(), 0.5*getHeight()-topCard.cardHeight()/2);
		}
		add(deckLabel, 0.05*getWidth(), 0.5*getHeight() - topCard.cardHeight()/2 - deckLabel.getAscent());	// Adding the Labels above each set of cards
		add(new GLabel("Dealer"), 0.2*getWidth(), 0.5*getHeight() - topCard.cardHeight()/2 - deckLabel.getAscent());
		add(new GLabel("Player"), 0.5*getWidth(), 0.5*getHeight() - topCard.cardHeight()/2 - deckLabel.getAscent());
		
		bm.startGame();
	}

	/** 
	 * Handler for button actions.
	 */
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Hit") && !gameIsOver)	// if the user clicks the "Hit" button
			bm.hit();
		if (e.getActionCommand().equals("Stay") && !gameIsOver) {	// if the user clicks the "Stay" button
			dealer.holeCard().turnFaceUp();
			bm.stay();
			gameIsOver = true;
		}
		if (e.getActionCommand().equals("New Game")) {	// if the user clicks the "New Game" button
			dealer.clear();
			player.clear();
			pHandSize = -1;
			dHandSize = -1;
			removeAll();
			gameIsOver = false;
			run();
		}
		if (e.getActionCommand().equals("Quit Game") && !gameIsOver) {	// if the user clicks the "Quit Game" button
			bm.quitGame();
			gameIsOver = true;
		}
	}

	/*************  Notifications  ***************/

	/**
	 * Place the card dealt to the player on the canvas.
	 * The card size is rescaled to be consistent with the current deck image size.
	 * @param card  the card to be added
	 */
	public void cardDealtToPlayerNotification(BlackjackGCard card) {
		pHandSize++;
		add(card, 0.5*getWidth() + 20*pHandSize, 0.5*getHeight()-card.cardHeight()/2);
	}

	/**
	 * Place the card dealt to the dealer on the canvas.
	 * The card size is rescaled to be consistent with the current deck image size.
	 * @param card  the card to be added
	 */
	public void cardDealtToDealerNotification(BlackjackGCard card) {
		dHandSize++;
		add(card, 0.2*getWidth() + 20*dHandSize, 0.5*getHeight()-card.cardHeight()/2);
	}

	/**
	 * End of game result notification. Dealer and player both bust.
	 */
	public void bothBustNotification(int wins, int losses, int ties) {
		nLabel.setText("You and the dealer both go bust. You tie.");
		sLabel.setText(wins + " wins, " + losses + " losses, " + ties + " ties");
	}

	/**
	 * End of game result notification. Dealer wins, player busts.
	 */
	public void youBustDealerWinsNotification(int wins, int losses, int ties) {
		nLabel.setText("Your total is over 21, you go bust. The Dealer wins!");
		sLabel.setText(wins + " wins, " + losses + " losses, " + ties + " ties");
	}

	/**
	 * End of game result notification. Dealer busts, player wins.
	 */
	public void dealerBustYouWinNotification(int wins, int losses, int ties) {
		nLabel.setText("The Dealer's total is over 21, he goes bust. You win!");
		sLabel.setText(wins + " wins, " + losses + " losses, " + ties + " ties");
	}

	/**
	 * End of game result notification. Player beats dealer.
	 */
	public void youBeatDealerNotification(int wins, int losses, int ties) {
		nLabel.setText("Your total is higher than the dealer. You win!");
		sLabel.setText(wins + " wins, " + losses + " losses, " + ties + " ties");
	}

	/**
	 * End of game result notification. Dealer beats player.
	 */
	public void dealerBeatsYouNotification(int wins, int losses, int ties) {
		nLabel.setText("The Dealer's total is higher than yours. The Dealer wins!");
		sLabel.setText(wins + " wins, " + losses + " losses, " + ties + " ties");
	}

	/**
	 * End of game result notification. Dealer and player tie.
	 */
	public void bothTieNotification(int wins, int losses, int ties) {
		nLabel.setText("You and the dealer's totals are equal. You tie.");
		sLabel.setText(wins + " wins, " + losses + " losses, " + ties + " ties");
	}

	/**
	 * End of game result notification. Player quits game and loses.
	 */
	public void quitGameNotification(int wins, int losses, int ties) {
		nLabel.setText("You quit the game. You lose!");
		sLabel.setText(wins + " wins, " + losses + " losses, " + ties + " ties");
	}
}

