package fr.epita.quiz.tests.datamodel;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import fr.epita.quiz.datamodel.Question;
/**
 * 
 * @author Krishna, Abhigna
 * @QuestionTest
 *
 */
public class QuestionTest {
	 
    @Test
    public void testAnsText(){
    	int id = 1;
    	int qid = 11;
    	String content = " Who is Founder of Java?";
    	String topics = "J2ee";
    	int difficulty = 2;
    	Question ques = new Question(id, qid, content, topics, difficulty);
        assertEquals(id,  ques.getId());
        assertEquals(qid,  ques.getQid());
        assertEquals(content, ques.getContent());
        assertEquals(topics, ques.getTopics());
      //  assertEquals(2, ques.getDifficulty());


    }
}