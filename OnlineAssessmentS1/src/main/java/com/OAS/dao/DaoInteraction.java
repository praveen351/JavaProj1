package com.OAS.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.OAS.model.TestQuestionAnswer;
import com.OAS.model.TestSubject;
import com.OAS.model.Users;
import com.OAS.util.HibernateCUInterface;

@Component
public class DaoInteraction {

	private Session session;
	private Transaction transaction;

	@Autowired
	public DaoInteraction(@Qualifier("H2DB") HibernateCUInterface service) {
		this.session = service.getSessionFactory().getCurrentSession();
		this.transaction = this.session.beginTransaction();
	}

	public void addData(List<Users> users, List<TestSubject> tsubjects) {
		if (!transaction.isActive())
			transaction.begin();
		for (Users user : users)
			session.save(user);
		for (TestSubject tsubject : tsubjects)
			session.save(tsubject);
		transaction.commit();
	}

	public List<TestQuestionAnswer> getQuestionAnswer(String name) {
		if (!transaction.isActive())
			transaction.begin();
		String hql = "from testsubject ts inner join testquestionanswer tqa ON ts.testsubjectid = tqa.tstqaid ";
		hql = hql + "where ts.subject_name=:name";
		Query query = session.createQuery(hql);
		List<Object[]> listResult = query.list();
		List<TestQuestionAnswer> testQuestion = new ArrayList<TestQuestionAnswer>();
		for (Object[] aRow : listResult)
			testQuestion.add((TestQuestionAnswer) aRow[1]);
		return testQuestion;
	}
}
