package nz.ac.auckland.se281.a3.bot;

public class StrategyFactory {

	/**
	 * Static method used to create bot strategies based on a string input. Uses
	 * switch case to select the strategy.
	 * 
	 * @param type String input to choose the strategy
	 * @return the selected Strategy object or null if the input type is invalid
	 */
	public static Strategy createStrategy(String type) {

		// checks contents of type and creates strategy based on results
		switch (type) {
		case "R":
			return new BotRandomStrategy();
		case "LR":
			return new LowRiskStrategy();
		case "HR":
			return new HighRiskStrategy();
		default:
			System.out.println("Wrong strategy type.");

		}
		return null;
	}
}
