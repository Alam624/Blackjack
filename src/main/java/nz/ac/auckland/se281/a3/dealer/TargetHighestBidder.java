package nz.ac.auckland.se281.a3.dealer;

import java.util.List;

import nz.ac.auckland.se281.a3.BlackJack;
import nz.ac.auckland.se281.a3.Hand;
import nz.ac.auckland.se281.a3.Participant.Action;
import nz.ac.auckland.se281.a3.Player;

public class TargetHighestBidder implements DealerStrategy {

	private List<Player> players;

	/**
	 * The constructor for the TargetHighestBidder class. Creates the object and
	 * sets the players from the current BlackJack game using the getPlayers()
	 * method.
	 * 
	 * @param currentGame the current BlackJack game
	 */
	public TargetHighestBidder(BlackJack currentGame) {
		this.players = currentGame.getPlayers();

	}

	@Override
	public Action action(Hand dealerHand) {

		// finds the highest bidder of the three players
		Player target = getMaxBidder(players.get(0), players.get(1), players.get(2));

		// if the targeted player doesn't have blackjack
		if (!target.getHand().isBlackJack()) {
			// dealer plays to beat target
			if (dealerHand.getScore() >= target.getHand().getScore() || target.getHand().isBust()) {
				return Action.HOLD;
			} else {
				return Action.HIT;
			}
		} else { // otherwise play safely
			if (dealerHand.getScore() >= 17) {
				return Action.HOLD;
			} else {
				return Action.HIT;
			}
		}
	}

	/**
	 * finds the player that has bid the highest in the current round by comparing
	 * each or their bids and then returning the player with the highest bid.
	 * 
	 * @param p1 player 1/human player that needs to have its bid compared
	 * @param b1 bot 1 player to compare bid
	 * @param b2 bot 2 player to compare bid
	 * @return the player object that has the highest bid
	 */
	private Player getMaxBidder(Player p1, Player b1, Player b2) {

		// set human player as highest bidder
		Player maxBidder = p1;

		// compares bot 1 with highest bidder, change maxBidder accordingly
		if (maxBidder.getHand().getBet() < b1.getHand().getBet()) {
			maxBidder = b1;
		}

		// compares bot 2 with the highest bidder and change maxBidder accordingly
		if (maxBidder.getHand().getBet() < b2.getHand().getBet()) {
			maxBidder = b2;
		}

		return maxBidder;
	}

}
