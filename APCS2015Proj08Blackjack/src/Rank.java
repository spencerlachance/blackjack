/** 
 * An enumeration of playing card ranks.
 * @author Dr. Mark A. Jones
 */
public enum Rank {
	DEUCE("2"), THREE("3"), FOUR("4"), FIVE("5"), SIX("6"), SEVEN("7"), 
	EIGHT("8"), NINE("9"), TEN("10"), JACK("j"), QUEEN("q"), KING("k"), ACE("a");
	
	private String name;
	
	private Rank(String s) {
		name = s;
	}
	
	@Override
	public String toString() {
		return name;
	}
}
