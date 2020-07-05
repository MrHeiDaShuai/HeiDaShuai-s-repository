package chessplay;


    // TODO: Auto-generated Javadoc
/**
     * The Class Location.
     *����λ����
     * @date 2020��7��4��
     * @author zengcongying
     * @version v1.0
     */
    
public class Location{

	/** The x. */
	private int x;//ĳ������λ�ú����꣬0-14
	
	/** The y. */
	private int y;//ĳ������λ�������꣬0-14
	
	/** The is black. */
	private boolean isBlack;
	
	/** The score. */
	private int score;//�Ը�λ�õĴ�ķ���

	/**
	 * Instantiates a new location.
	 */
	public Location(){}
	
	/**
	 * Instantiates a new location.
	 *
	 * @param x the x
	 * @param y the y
	 * @param isBlack the is black
	 */
	public Location(int x, int y, boolean isBlack){
		this.x = x;
		this.y = y;
		this.isBlack=isBlack;
	}
	
	/**
	 * Instantiates a new location.
	 *
	 * @param x the x
	 * @param y the y
	 * @param isBlack the is black
	 * @param score the score
	 */
	public Location(int x, int y, boolean isBlack, int score){
		this(x, y, isBlack);
		this.score = score;
	}

	/**
	 * Gets the x.
	 *
	 * @return the x
	 */
	public int getX(){return this.x;}
	
	/**
	 * Sets the x.
	 *
	 * @param x the new x
	 */
	public void setX(int x){this.x = x;}
	
	/**
	 * Gets the y.
	 *
	 * @return the y
	 */
	public int getY(){return this.y;} 
	
	/**
	 * Sets the y.
	 *
	 * @param y the new y
	 */
	public void setY(int y){this.y = y;}

	/**
	 * Checks if is black.
	 *
	 * @return true, if is black
	 */
	public boolean isBlack() {return isBlack;}
	
	/**
	 * Sets the black.
	 *
	 * @param isBlack the new black
	 */
	public void setBlack(boolean isBlack) {this.isBlack = isBlack;}
	
	/**
	 * Gets the score.
	 *
	 * @return the score
	 */
	public int getScore(){return this.score;}
	
	/**
	 * Sets the score.
	 *
	 * @param score the new score
	 */
	public void setScore(int score){this.score = score;}
	

}
