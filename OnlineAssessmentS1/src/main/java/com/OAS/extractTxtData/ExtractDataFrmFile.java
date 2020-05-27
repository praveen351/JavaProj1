package com.OAS.extractTxtData;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import com.OAS.model.TestQuestionAnswer;
import com.OAS.model.TestQuestionOption;
import com.OAS.model.TestSubject;
import com.OAS.model.Users;

@Component
public class ExtractDataFrmFile {

	final private ArrayList<String> hibernateQA;
	final private ArrayList<String> hibernateQO;
	final private ArrayList<String> springQA;
	final private ArrayList<String> springQO;

	public ExtractDataFrmFile() throws IOException {

		String fhibernateQA = new ClassPathResource("static/textFolder/hibernateQA.txt").getFile().getPath();
		String fhibernateQO = new ClassPathResource("static/textFolder/hibernateQO.txt").getFile().getPath();
		String fspringQA = new ClassPathResource("static/textFolder/springQA.txt").getFile().getPath();
		String fspringQO = new ClassPathResource("static/textFolder/springQO.txt").getFile().getPath();

		springQA = new ArrayList<String>(Files.readAllLines(Paths.get(fspringQA), Charset.forName("ISO-8859-1")));
		springQO = new ArrayList<String>(Files.readAllLines(Paths.get(fspringQO), Charset.forName("ISO-8859-1")));
		hibernateQA = new ArrayList<String>(Files.readAllLines(Paths.get(fhibernateQA), Charset.forName("ISO-8859-1")));
		hibernateQO = new ArrayList<String>(Files.readAllLines(Paths.get(fhibernateQO), Charset.forName("ISO-8859-1")));
	}

	public Map<String, Object> extraxtTextFileValues() {
		int j;
		int validator = 0;
		int incrementor = 0;
		List<Users> users = Arrays.asList(new Users("roger@gmail.com", "wipro@123", "Roger", "Kutcher", "admin", null),
				new Users("steve@gmail.com", "wipro@123", "Steve", "Martin", "admin", null));
		List<TestSubject> tsubjects = Arrays.asList(new TestSubject("Spring-L1", 50, 30, null, null),
				new TestSubject("Hibernate-L1", 50, 30, null, null));

		List<TestQuestionAnswer> tqueanswers;
		List<TestQuestionOption> tquesoptions;

		List<String> tempData;
		List<String> tempChildData;

		Map<String, Object> mapData = new HashMap<String, Object>();

		List<List<String>> tempQA = new ArrayList<List<String>>();
		tempQA.add(springQA);
		tempQA.add(hibernateQA);

		List<List<String>> tempQO = new ArrayList<List<String>>();
		tempQO.add(springQO);
		tempQO.add(hibernateQO);

		for (int k = 0; k < 2; k++) {
			tempData = tempQA.get(k);
			tempChildData = tempQO.get(k);
			incrementor = 0;
			tqueanswers = new ArrayList<TestQuestionAnswer>();

			for (int i = 0; i < tempData.size(); i = i + 2) {
				String question = tempData.get(i).trim().split(":")[1];
				String answer = tempData.get(i + 1).trim().split(":")[1];
				tquesoptions = new ArrayList<TestQuestionOption>();
				for (j = validator; j < tempChildData.size(); j = j + 2) {
					String linetext = tempChildData.get(j).trim().split(" ")[0].split("=")[1].replace("\"", "");
					if (linetext.split("-")[0].equals("Q" + Integer.toString(incrementor + 1))) {
						String option = tempChildData.get(j + 1).trim().split("=")[1].replace("\"", "");
						tquesoptions.add(new TestQuestionOption(option, linetext));
						continue;
					}
					break;
				}
				validator = j;
				tqueanswers.add(new TestQuestionAnswer(question, answer, tquesoptions));
				incrementor++;
			}
			tsubjects.get(k).setTestQuestionAnswerList(tqueanswers);
		}

		mapData.put("user", users);
		mapData.put("taskSubject", tsubjects);
		return mapData;
	}
}
