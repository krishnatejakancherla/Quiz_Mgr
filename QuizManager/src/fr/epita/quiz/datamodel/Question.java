package fr.epita.quiz.datamodel;

/**
 * 
 * @author Krishna, Abhigna
 * POJO for Question
 */
public class Question {
	
	private String content;
	private String topics;
	private Integer difficulty;
	private int id;
	private int qid;
	
	
	public Question(int id, int qid, String content, String topics, Integer difficulty) {
		this.content = content;
		this.topics = topics;
		this.difficulty = difficulty;
		this.id = id;
		this.qid = qid;
	}
	
	
	public Question() {
	}


	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getTopics() {
		return topics;
	}
	public void setTopics(String topics) {
		this.topics = topics;
	}
	public Integer getDifficulty() {
		return difficulty;
	}
	public void setDifficulty(Integer difficulty) {
		this.difficulty = difficulty;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public int getQid() {
		return qid;
	}


	public void setQid(int qid) {
		this.qid = qid;
	}


	@Override
	public String toString() {
		return "Question [content=" + content + ", topics=" + topics + ", difficulty=" + difficulty
				+ "]";
	}
	
	
	
	
	
	

}
