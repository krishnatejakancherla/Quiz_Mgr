package fr.epita.quiz.tests.datamodel;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import fr.epita.quiz.datamodel.Answer;
/**
 * 
 * @author Krishna, Abhigna
 * @AnsewrTest
 *
 */
public class AnswerTest {
	 
    @Test
    public void testAnsText(){
    	String answer = "java";
        Answer ans = new Answer(answer);
        assertEquals("java",  ans.getText());
    }
}