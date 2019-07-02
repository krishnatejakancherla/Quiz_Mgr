package fr.epita.quiz.datamodel;

public class Answer {
	
	private String ans;
	private Quiz quiz;
	private Question question;
	private MultChoice multChce;
	
	
	public Quiz getQuiz() {
		return quiz;
	}


	public void setQuiz(Quiz quiz) {
		this.quiz = quiz;
	}


	public Question getQuestion() {
		return question;
	}


	public void setQuestion(Question question) {
		this.question = question;
	}


	public MultChoice getMultChce() {
		return multChce;
	}


	public void setMultChce(MultChoice multChce) {
		this.multChce = multChce;
	}


	public Answer(String answer) {
		this.ans = answer;
	}
	
	
	public String getText() {
		return ans;
	}

	public void setText(String answer) {
		this.ans = answer;
	}


	@Override
	public String toString() {
		return "Answer \t[text=" + ans + ", \n quiz=" + quiz + ", \n question=" + question + "\n]";
	}
	
	


	
	
	
	
	

}
