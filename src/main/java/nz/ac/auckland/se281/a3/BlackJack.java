package nz.ac.auckland.se281.a3;

import java.util.ArrayList;
import java.util.List;

import nz.ac.auckland.se281.a3.bot.Bot;
import nz.ac.auckland.se281.a3.bot.Strategy;
import nz.ac.auckland.se281.a3.bot.StrategyFactory;
import nz.ac.auckland.se281.a3.dealer.Dealer;
import nz.ac.auckland.se281.a3.dealer.TargetHighestBidder;
import nz.ac.auckland.se281.a3.dealer.TargetTopWinner;

/**
 * Unless it is specified in the JavaDoc, you cannot change any methods.
 * 
 * You can add new methods and/or new instance fields
 */
public class BlackJack {

	private List<Player> players;
	private Dealer dealer;
	private Deck deck;

	public BlackJack(Deck deck) {
		this.deck = deck;
		players = new ArrayList<>();
		players.add(new Human("Player1")); // add the Human player first.
	}

	/**
	 * Thi constructor is for testing reasons
	 * 
	 * @param cards
	 */
	protected BlackJack(Card... cards) {
		this(new Deck(cards));

	}

	public BlackJack() {
		this(new Deck());
	}

	public List<Player> getPlayers() {
		return players;
	}

	private String getBotStrategy() {
		System.out.println("Choose Bot strategy: random (R) - low risk (LR) - high risk (HR)");
		String result = Main.scanner.next();
		while (!result.equals("R") && !result.equals("LR") && !result.equals("HR") && !result.equals("A")) {
			System.out.println("please type \"R\", \"LR\",  \"HR\"");
			result = Main.scanner.next();
		}
		return result;
	}

	// do not change this method
	public void start() {
		initBots();
		initDealer();
		String res;
		int round = 0;
		do {
			round++;
			for (Participant p : players) {
				p.play(deck, round);
			}
			dealer.play(deck, round);
			printAndUpdateResults(round); // after each game print result and update scoreboard
			System.out.println("Do you want to play again?");
			res = Main.scanner.next();
			while (!res.equals("yes") && !res.equals("no")) {
				System.out.println("please type either \"yes\" or \"no\"");
				res = Main.scanner.next();
			}
		} while (res.equals("yes"));
		printGameStatistics(); // when the user terminates the game print the statistics
	}

	/**
	 * TODO This method initializes the Bots, you should change this method for
	 * Task1
	 */
	protected void initBots() {

		// creates bot objects
		Bot bot1 = new Bot("Bot1");
		Bot bot2 = new Bot("Bot2");

		String botStrategyString = getBotStrategy();
		// create and set Bots strategy here
		Strategy botStrategy = StrategyFactory.createStrategy(botStrategyString);
		bot1.setStrategy(botStrategy);
		bot2.setStrategy(botStrategy);

		// add the bots to the list of players
		players.add(bot1);
		players.add(bot2);
	}

	/**
	 * TODO This method initializes the Dealer, you should change this method for
	 * Task2
	 */
	protected void initDealer() {
		// set the initial strategy using the Strategy pattern
		dealer = new Dealer("Dealer");
		dealer.setStrategy(new TargetHighestBidder(this));
	}

	/**
	 * TODO This method prints and updates the results (wins and losses) you should
	 * change this method for Task 2 and Task 3
	 */
	protected void printAndUpdateResults(int round) {

		// selects the top winner
		Player netWinner = getNetWinner();

		// prints the round results
		System.out.println(winLoss(players.get(0), round));
		System.out.println(winLoss(players.get(1), round));
		System.out.println(winLoss(players.get(2), round));

		// checks if the dealer needs to change strategies
		if (netWinner.getNetWins() >= 2) {
			dealer.setStrategy(new TargetTopWinner(this));
		} else {
			dealer.setStrategy(new TargetHighestBidder(this));
		}

	}

	/**
	 * compares the net wins of the three players and returns the Player object of
	 * the one with the highest number of net wins.
	 * 
	 * @return the Player with the most net wins
	 */
	public Player getNetWinner() {

		// creates variable for the top winner
		Player netWinner;

		// checks if the second player has more wins than the first player
		netWinner = players.get(0);
		if (players.get(1).getNetWins() > netWinner.getNetWins()) {
			netWinner = players.get(1);
		}

		// checks if the third player has more wins than the current highest winner
		if (players.get(2).getNetWins() > netWinner.getNetWins()) {
			netWinner = players.get(2);
		}

		return netWinner;
	}

	/**
	 * TODO This method should print the statistic of the game when it ends
	 */
	protected void printGameStatistics() {

		// loops through all the players and prints each of their statistics
		for (Player player : players) {
			System.out.println(
					player.getName() + " won " + player.getWins() + " times and lost " + player.getLosses() + " times");
		}
	}

	/**
	 * Determines if the input player won the round against the dealer or not and
	 * returns a string describing the round results and how many chips the player
	 * won/lost
	 * 
	 * @param player player that is to be compared with the dealer
	 * @param round  current round number
	 * @return a string describing the result of the round for the input player
	 */
	private String winLoss(Player player, int round) {

		// checks for Dealer having blackjack or player being bust, both cases resulting
		// in a loss.
		if (dealer.getHand().isBlackJack() || player.getHand().isBust()) {
			player.addLoss();
			return "Round " + round + ": " + player.getName() + " lost " + player.getHand().getBet() + " chips";
		}

		// checks if the players score is higher than the dealers, dealer is bust or
		// player has blackjack. If so then player wins
		if (player.getHand().isBlackJack() || player.getHand().getScore() > dealer.getHand().getScore()
				|| dealer.getHand().isBust()) {
			player.addWin();
			return "Round " + round + ": " + player.getName() + " won " + player.getHand().getBet() + " chips";
		}

		// all other cases player loses
		player.addLoss();
		return "Round " + round + ": " + player.getName() + " lost " + player.getHand().getBet() + " chips";
	}

}
