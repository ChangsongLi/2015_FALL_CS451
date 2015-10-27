
public class Tiles {
	private String c;
	private int s;
	public Tiles(String character, int score){
		c = character;
		s = score;
	}
	
	public int getScoreOfTile(){
		return s;
	}

	public String getNameOfTile(){
		return c;
	}
}
