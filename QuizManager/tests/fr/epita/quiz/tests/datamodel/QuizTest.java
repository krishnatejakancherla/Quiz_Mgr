package fr.epita.quiz.tests.datamodel;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import fr.epita.quiz.datamodel.Quiz;
/**
 * 
 * @author Krishna, Abhigna
 * @QuizTest
 *
 */
public class QuizTest {
	 
    @Test
    public void testAnsText(){
    	String title = "Java Fundamentals";
        Quiz qu = new Quiz(title);
        assertEquals(title,  qu.getTitle());
    }
}