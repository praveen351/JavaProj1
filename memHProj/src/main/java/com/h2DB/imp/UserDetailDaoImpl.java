package com.h2DB.imp;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.h2DB.dao.UserDetailDao;
import com.h2DB.model.UserDetail;
import com.h2DB.util.HibernateCUInterface;

@Component
public class UserDetailDaoImpl implements UserDetailDao {

	private Session session;
	private Transaction transaction;

	@Autowired
	public UserDetailDaoImpl(@Qualifier("Connect") HibernateCUInterface uiconnect) {
		this.session = uiconnect.getSessionFactory().openSession();
		this.transaction = this.session.beginTransaction();
	}

	public UserDetail getUserDetail(int id) {
		Query query = session.createQuery("from UserDetail where id=:idval");
		query.setParameter("idval", id);
		UserDetail userDetail = (UserDetail) query.uniqueResult();

		return userDetail;
	}

	public List<UserDetail> getAllUserDetail() {
		Query query = session.createQuery("from UserDetail");
		List<UserDetail> userList = query.list();
		return userList;
	}

	public int addUserDetail(UserDetail userDetail) {
		this.session.save(userDetail);
		int id = userDetail.getId();
		return id;
	}

	public int updateUserDetail(UserDetail userDetail) {
		Query query = session.createQuery("update UserDetail set firstName=:fname,lastName=:lname,email=:ename,dob=:dob where id=:idval");
		query.setParameter("fname", userDetail.getFirstName());
		query.setParameter("lname", userDetail.getLastName());
		query.setParameter("ename", userDetail.getEmail());
		query.setParameter("dob", userDetail.getDob());
		query.setParameter("idval", userDetail.getId());
		
		int status = query.executeUpdate();
		
		if(status != 1)
			return 0;
		else
			return 1;
	}
	
	public int deleteUserDetail(int id) {
		Query query = session.createQuery("delete from UserDetail where id=:idval");
		query.setParameter("idval", id);
		
		int status = query.executeUpdate();
		
		if(status != 1)
			return 0;
		else
			return 1;
	}
}