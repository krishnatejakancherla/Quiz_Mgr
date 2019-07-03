package fr.epita.quiz.services.data;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import fr.epita.quiz.datamodel.Answer;
import fr.epita.quiz.datamodel.MultChoice;
import fr.epita.quiz.datamodel.Question;
import fr.epita.quiz.datamodel.Quiz;
import fr.epita.quiz.exception.CreateFailedException;
import fr.epita.quiz.exception.SearchFailedException;
import fr.epita.quiz.services.ConfigEntry;
import fr.epita.quiz.services.ConfigurationService;

/**
 * 
 * @author Krishna 
 * DAO Layer
 */
public class QuizJDBCDAO {

	private static QuizJDBCDAO instance;

	private static final String INSERT_QUERY = "INSERT INTO QUIZ (name) values(?)";
	private static final String UPDATE_QUERY = "UPDATE QUIZ SET NAME=? WHERE ID = ?";
	private static final String INSERT_QA = "INSERT INTO QUESTION (id, content, topics, difficulty, answer, choiceA, choiceB, choiceC, choiceD) values(?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String UPDATE_QA = "UPDATE QUESTION SET CONTENT=?, TOPICS=?, DIFFICULTY=?, ANSWER=?, CHOICEA=?, CHOICEB=?, CHOICEC=?, CHOICED=?    WHERE QID =?";
	private static final String DELETE_QA = "DELETE FROM QUESTION  WHERE QID=?";
	private static final String DELETE_ALL_QA = "DELETE FROM QUESTION  WHERE ID =?";
	private static final String EXPORT_QUERY = "SELECT QID, CONTENT, TOPICS, DIFFICULTY, ANSWER FROM QUESTION";
	private static final String RET_QUERY = "SELECT ID, NAME FROM QUIZ";
	private static final String RET_ALL_QUERY = "SELECT QID, CONTENT, TOPICS, DIFFICULTY, ANSWER, CHOICEA, CHOICEB, CHOICEC, CHOICED FROM QUESTION WHERE TOPICS=?";
	private static final String CDT_QUERY = "SELECT UNAME, PASSWD FROM CANDIDATE WHERE UNAME=? AND PASSWD=?";
	private static final String ADM_QUERY = "SELECT * FROM ADMIN WHERE UNAME=? AND PASSWD=?";
	private static final String CDT_REG_QUERY = "INSERT INTO CANDIDATE (name,uname,passwd) VALUES (?, ?, ?) ";
	private static final String RET_QUES_QUERY = "SELECT QID, CONTENT, TOPICS, DIFFICULTY, ANSWER, CHOICEA, CHOICEB, CHOICEC, CHOICED from QUESTION WHERE QID=?";

	private static boolean isAuth = false;

	private QuizJDBCDAO() {

	}

	public static QuizJDBCDAO getInstance() {
		if (instance == null) {
			instance = new QuizJDBCDAO();
		}
		return instance;
	}

	/**
	 * Method for getting Connection status
	 * 
	 * @return
	 * @throws SQLException
	 */
	private Connection getConnection() throws SQLException {
		System.out.println("Connection Before");

		ConfigurationService conf = ConfigurationService.getInstance();
		String username = conf.getConfigurationValue("db.username", "");
		String password = conf.getConfigurationValue("db.password", "");
		String url = conf.getConfigurationValue("db.url", "");
		Connection connection = DriverManager.getConnection(url, username, password);
		System.out.println("Conn Succ:"+connection);
		return connection;
	}

	public void create(Quiz quiz) throws CreateFailedException {

		try (Connection connection = getConnection();
				PreparedStatement pstmt = connection.prepareStatement(INSERT_QUERY);) {
			pstmt.setString(1, quiz.getTitle());
			pstmt.execute();
		} catch (SQLException sqle) {
			throw new CreateFailedException(quiz);
		}

	}

	public void update(Quiz quiz) {
		try (Connection connection = getConnection();
				PreparedStatement pstmt = connection.prepareStatement(UPDATE_QUERY);) {
			pstmt.setString(1, quiz.getTitle());
			pstmt.setInt(2, quiz.getId());
			pstmt.execute();
		} catch (SQLException sqle) {
		}

	}

	public boolean deleteQuiz(int id) {
		boolean isSucc = false;
		try (Connection connection = getConnection();
				PreparedStatement pstmt = connection.prepareStatement(DELETE_ALL_QA);) {
			pstmt.setInt(1, id);
			pstmt.execute();
			isSucc = true;
		} catch (SQLException sqle) {
		}
		return isSucc;
	}

	public Quiz getById(int id) {
		return null;

	}

	/**
	 * 
	 * @param quizCriterion
	 * @return
	 * @throws SearchFailedException
	 */
	public List<Quiz> search(Quiz quizCriterion) throws SearchFailedException {
		String searchQuery = ConfigurationService.getInstance()
				.getConfigurationValue(ConfigEntry.DB_QUERIES_QUIZ_SEARCHQUERY, "");
		List<Quiz> quizList = new ArrayList<>();
		try (Connection connection = getConnection();

				PreparedStatement pstmt = connection.prepareStatement(searchQuery)) {

			pstmt.setInt(1, quizCriterion.getId());
			pstmt.setString(2, "%" + quizCriterion.getTitle() + "%");

			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("ID");
				String topic = rs.getString("NAME");
				Quiz quiz = new Quiz(topic);
				quiz.setId(id);
				quizList.add(quiz);
			}

			rs.close();
		} catch (SQLException e) {
			throw new SearchFailedException(quizCriterion);
		}
		return quizList;
	}

	/**
	 * 
	 * @param ans
	 * @return
	 * @throws CreateFailedException
	 */
	public boolean createQues(Answer ans) throws CreateFailedException {
		boolean isSucc = false;
		try (Connection connection = getConnection();
				PreparedStatement pstmt = connection.prepareStatement(INSERT_QA);) {
			pstmt.setInt(1, ans.getQuestion().getId());
			pstmt.setString(2, ans.getQuestion().getContent());
			pstmt.setString(3, ans.getQuestion().getTopics());
			pstmt.setInt(4, ans.getQuestion().getDifficulty());
			pstmt.setString(5, ans.getText());
			pstmt.setString(6, ans.getMultChce().getChcA());
			pstmt.setString(7, ans.getMultChce().getChcB());
			pstmt.setString(8, ans.getMultChce().getChcC());
			pstmt.setString(9, ans.getMultChce().getChcD());

			pstmt.execute();
			isSucc = true;

		} catch (SQLException sqle) {
			throw new CreateFailedException(ans);
		}
		return isSucc;

	}

	/**
	 * 
	 * @param ans
	 * @return
	 * @throws CreateFailedException
	 */
	public boolean updtQues(Answer ans) throws CreateFailedException {
		boolean isSucc = false;
		try (Connection connection = getConnection();
				PreparedStatement pstmt = connection.prepareStatement(UPDATE_QA);) {

			pstmt.setString(1, ans.getQuestion().getContent());
			pstmt.setString(2, ans.getQuestion().getTopics());
			pstmt.setInt(3, ans.getQuestion().getDifficulty());
			pstmt.setString(4, ans.getText());
			pstmt.setString(5, ans.getMultChce().getChcA());
			pstmt.setString(6, ans.getMultChce().getChcB());
			pstmt.setString(7, ans.getMultChce().getChcC());
			pstmt.setString(8, ans.getMultChce().getChcD());
			pstmt.setInt(9, ans.getQuestion().getQid());

			pstmt.execute();
			isSucc = true;

		} catch (SQLException sqle) {
			throw new CreateFailedException(ans);
		}
		return isSucc;

	}

	/**
	 * 
	 * @param qid
	 * @return
	 * @throws CreateFailedException
	 */
	public boolean delQues(int qid) throws CreateFailedException {
		boolean isSucc = false;
		try (Connection connection = getConnection();
				PreparedStatement pstmt = connection.prepareStatement(DELETE_QA);) {
			pstmt.setInt(1, qid);
			pstmt.execute();
			isSucc = true;

		} catch (SQLException sqle) {
			throw new CreateFailedException(qid);
		}
		return isSucc;

	}

	/**
	 * 
	 * @return
	 */
	public List<Quiz> retreiveTitle() {
		List<Quiz> quizList = new ArrayList<>();
		try (Connection connection = getConnection();
				PreparedStatement pstmt = connection.prepareStatement(RET_QUERY)) {

			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("ID");
				String topic = rs.getString("NAME");
				Quiz quiz = new Quiz(topic);
				quiz.setId(id);
				quizList.add(quiz);
			}

			rs.close();
		} catch (SQLException e) {
		}
		return quizList;

	}

	/**
	 * 
	 * @param string
	 * @return
	 */
	public List<Answer> retreiveAllQues(String string) {
		List<Answer> ansList = new ArrayList<>();

		try (Connection connection = getConnection();
				PreparedStatement pstmt = connection.prepareStatement(RET_ALL_QUERY)) {
			pstmt.setString(1, string);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {

				System.out.println(rs.getInt(1) + " -- " + rs.getString(2) + " -- " + rs.getString(3) + " -- "
						+ rs.getInt(4) + " -- " + rs.getString(5) + " -- " + rs.getString(6) + " -- " + rs.getString(7)
						+ " -- " + rs.getString(8) + " -- " + rs.getString(9));

				Answer ans = new Answer(rs.getString(5));
				ans.setQuestion(new Question(0, rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4)));
				ans.setMultChce(new MultChoice(rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9)));

				ansList.add(ans);

			}

			rs.close();
		} catch (SQLException e) {
		}
		return ansList;

	}

	/**
	 * 
	 * @param uname
	 * @param pwd
	 * @return
	 */
	public boolean candidateLogin(String uname, String pwd) {

		try (Connection connection = getConnection();
				PreparedStatement pstmt = connection.prepareStatement(CDT_QUERY);) {
			pstmt.setString(1, uname);
			pstmt.setString(2, pwd);
			ResultSet rs = pstmt.executeQuery();
			if ((uname.equals(rs.getString("UNAME"))) && (pwd.equals(rs.getString("PASSWD")))) {
				isAuth = true;
			}

		} catch (SQLException sqle) {
		}
		return true;
	}

	/**
	 * 
	 * @param name
	 * @param uname
	 * @param pwd
	 * @return
	 */
	public boolean candidateRegister(String name, String uname, String pwd) {
		boolean isCandAuth = false;
		try (Connection connection = getConnection();
				PreparedStatement pstmt = connection.prepareStatement(CDT_REG_QUERY);) {
			pstmt.setString(1, name);
			pstmt.setString(2, uname);
			pstmt.setString(3, pwd);
			pstmt.execute();
			isCandAuth = true;
		} catch (SQLException sqle) {
		}
		return isCandAuth;
	}

	/**
	 * 
	 * @return
	 * @throws DocumentException
	 * @throws FileNotFoundException
	 */
	public boolean exportQuiz() throws FileNotFoundException, DocumentException {
		//List<Question> qList = new ArrayList<>();
		boolean isExpSucc = false;
		try (Connection connection = getConnection();
				PreparedStatement pstmt = connection.prepareStatement(EXPORT_QUERY)) {
			ResultSet rs = pstmt.executeQuery();

			Document docRpt = new Document();
			PdfWriter.getInstance(docRpt, new FileOutputStream("Quiz.pdf"));
			docRpt.open();
			PdfPTable col = new PdfPTable(5);
			col.addCell("ID");
			col.addCell("Question");
			col.addCell("Topic");
			col.addCell("Difficulty");
			col.addCell("Answer");

			PdfPCell tblCell;
			while (rs.next()) {

				String qid = rs.getString("QID");
				tblCell = new PdfPCell(new Phrase(qid));
				col.addCell(tblCell);
				String content = rs.getString("CONTENT");
				tblCell = new PdfPCell(new Phrase(content));
				col.addCell(tblCell);
				String topics = rs.getString("TOPICS");
				tblCell = new PdfPCell(new Phrase(topics));
				col.addCell(tblCell);
				String diff = rs.getString("DIFFICULTY");
				tblCell = new PdfPCell(new Phrase(diff));
				col.addCell(tblCell);
				String ans = rs.getString("ANSWER");
				tblCell = new PdfPCell(new Phrase(ans));
				col.addCell(tblCell);


			}
			docRpt.add(col);
			docRpt.close();
			isExpSucc = true;
			rs.close();
		} catch (SQLException e) {
		}
		return isExpSucc;
	}

	/**
	 * 
	 * @param id
	 * @return
	 */
	public HashMap<String, String> retreiveQues(int id) {
		HashMap<String, String> retMap = new HashMap<String, String>();

		try (Connection connection = getConnection();
				PreparedStatement pstmt = connection.prepareStatement(RET_QUES_QUERY)) {
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				retMap.put("QID", rs.getString(1));
				retMap.put("CONTENT", rs.getString(2));
				retMap.put("TOPICS", rs.getString(3));
				retMap.put("DIFFICULTY", rs.getString(4));
				retMap.put("ANSWER", rs.getString(5));
				retMap.put("CHOICEA", rs.getString(6));
				retMap.put("CHOICEB", rs.getString(7));
				retMap.put("CHOICEC", rs.getString(8));
				retMap.put("CHOICED", rs.getString(9));

			}

			rs.close();
		} catch (SQLException e) {
		}
		return retMap;

	}

	public boolean admLogin(String uname, String pwd) {
		try (Connection connection = getConnection();
				PreparedStatement pstmt = connection.prepareStatement(ADM_QUERY);) {
			pstmt.setString(1, uname);
			pstmt.setString(2, pwd);
			ResultSet rs = pstmt.executeQuery();


			if ((rs.getString(1).equals(uname)) && (rs.getString(2).equals(pwd))) {
				isAuth = true;

			}
		} catch (SQLException sqle) {
		}
		return isAuth;
	}

}
