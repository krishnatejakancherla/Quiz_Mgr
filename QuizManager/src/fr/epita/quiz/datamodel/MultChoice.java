package fr.epita.quiz.datamodel;

/**
 * 
 * @author Krishna, Abhigna
 * @POJO 
 */
public class MultChoice {

	// VAriables
	private String chcA;
	private String chcB;
	private String chcC;
	private String chcD;

	public MultChoice(String chcA, String chcB, String chcC, String chcD) {
		this.chcA = chcA;
		this.chcB = chcB;
		this.chcC = chcC;
		this.chcD = chcD;
	}

	public String getChcA() {
		return chcA;
	}

	public void setChcA(String chcA) {
		this.chcA = chcA;
	}

	public String getChcB() {
		return chcB;
	}

	public void setChcB(String chcB) {
		this.chcB = chcB;
	}

	public String getChcC() {
		return chcC;
	}

	public void setChcC(String chcC) {
		this.chcC = chcC;
	}

	public String getChcD() {
		return chcD;
	}

	public void setChcD(String chcD) {
		this.chcD = chcD;
	}

}
