package fr.epita.quiz.tests.services.data;

import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;

import org.junit.Test;

import com.itextpdf.text.DocumentException;

import fr.epita.quiz.datamodel.Answer;
import fr.epita.quiz.datamodel.Quiz;
import fr.epita.quiz.exception.CreateFailedException;
import fr.epita.quiz.services.data.QuizJDBCDAO;

public class QuizJDBCDAOTest {

	private static QuizJDBCDAO dao = QuizJDBCDAO.getInstance();

	@Test
	public void retTitleTest() {
		List<Quiz> quizLst = dao.retreiveTitle();
		assertEquals(false, quizLst.isEmpty());
	}

	@Test
	public void delQuesTest() throws CreateFailedException {
		boolean isSucc = dao.delQues(0);
		assertEquals(true, isSucc);

	}

	@Test(expected = NullPointerException.class)
	public void createQuesTest() throws CreateFailedException, NullPointerException {
		Answer ans = new Answer("Java");
		boolean isSucc = dao.createQues(ans);
		assertEquals(true, isSucc);

	}

	@Test
	public void exportQuizTest() throws FileNotFoundException, DocumentException {
		boolean isExp = dao.exportQuiz();
		assertEquals(true, isExp);
	}

	@Test
	public void retreiveAllQuesTest() {
		List<Answer> ansList = dao.retreiveAllQues("Fruit");
		assertEquals(false, ansList.isEmpty());

	}

	@Test
	public void retreiveQuesTest() {
		HashMap<String, String> retMap = dao.retreiveQues(2);
		assertEquals(true, retMap.isEmpty());
	}
	
	@Test
	public void candidateLoginTest() {
		boolean isAuth = dao.candidateLogin("kt", "kt");
		assertEquals(true, isAuth);

	}
}
