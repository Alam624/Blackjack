package nz.ac.auckland.se281.a3.bot;

import nz.ac.auckland.se281.a3.Hand;
import nz.ac.auckland.se281.a3.Participant.Action;

public interface Strategy {

	/**
	 * Method that decides what action the bot will perform: either HIT or HOLD. The
	 * way this is calculated will change depending on the strategy that the bot is
	 * assigned.
	 * 
	 * @param hand hand that the bot currently has
	 * @return Action.HIT or Action.HOLD
	 */
	public Action action(Hand hand);

	/**
	 * Method that decides how much the bot will bet on the current round. Changes
	 * based on the current strategy that the bot has been assigned
	 * 
	 * @return the value of the bet
	 */
	public int bet();
}
