package fr.epita.quiz.tests;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * 
 * @author Krishna, Abhigna
 *
 */
public class TestConfiguration {

	public static void main(String[] args) throws IOException {
		Properties props = new Properties();
		File confFile = new File("conf.properties");
		FileInputStream ips = new FileInputStream(confFile);
		props.load(ips);
		
		String dbUrl = props.getProperty("db.url", "");
		
		System.out.println(dbUrl);

	}

}
