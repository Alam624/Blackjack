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
		Bot bot1 = new Bot("Bot1");
		Bot bot2 = new Bot("Bot2");
		String botStrategyString = getBotStrategy(); // UNCOMMENT THIS
		// create and set Bots strategy here
		Strategy botStrategy = StrategyFactory.createStrategy(botStrategyString);
		bot1.SetStrategy(botStrategy);
		bot2.SetStrategy(botStrategy);
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
		dealer.SetStrategy(new TargetHighestBidder(this));
	}

	/**
	 * TODO This method prints and updates the results (wins and losses) you should
	 * change this method for Task 2 and Task 3
	 */
	protected void printAndUpdateResults(int round) {

		Player netWinner = getNetWinner();

		System.out.println(winLoss(players.get(0), round));
		System.out.println(winLoss(players.get(1), round));
		System.out.println(winLoss(players.get(2), round));
		if (netWinner.getNetWins() >= 2) {
			dealer.SetStrategy(new TargetTopWinner(this));
		} else {
			dealer.SetStrategy(new TargetHighestBidder(this));
		}
	}

	public Player getNetWinner() {

		Player netWinner;

		netWinner = players.get(0);
		if (players.get(1).getNetWins() > netWinner.getNetWins()) {
			netWinner = players.get(1);
		}
		if (players.get(2).getNetWins() > netWinner.getNetWins()) {
			netWinner = players.get(2);
		}

		return netWinner;
	}

	/**
	 * TODO This method should print the statistic of the game when it ends
	 */
	protected void printGameStatistics() {

	}

	private String winLoss(Player player, int round) {

		if (dealer.getHand().isBlackJack() || player.getHand().isBust()) {
			player.addLoss();
			return "Round " + round + ": " + player.getName() + " lost $" + players.get(0).getHand().getBet();
		}

		if (player.getHand().isBlackJack() || player.getHand().getScore() > dealer.getHand().getScore()
				|| dealer.getHand().isBust()) {
			player.addWin();
			return "Round " + round + ": " + player.getName() + " won $" + players.get(0).getHand().getBet();
		}

		player.addLoss();
		return "Round " + round + ": " + player.getName() + " lost $" + players.get(0).getHand().getBet();
	}

}
