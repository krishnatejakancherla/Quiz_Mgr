package fr.epita.quiz.tests.datamodel;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import fr.epita.quiz.datamodel.MultChoice;
/**
 * 
 * @author Krishna, Abhigna
 * @MultiChoiceTest
 */
public class MultChoiceTest {
	 
    @Test
    public void testAnsText(){
    	String chcA = "java";
    	String chcB = "python";
    	String chcC = "sql";
    	String chcD = "db";

        MultChoice mult = new MultChoice(chcA, chcB, chcC, chcD);
        assertEquals(chcA,  mult.getChcA());
        assertEquals(chcB,  mult.getChcB());
        assertEquals(chcC,  mult.getChcC());
        assertEquals(chcD,  mult.getChcD());

    }
}