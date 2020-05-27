package com.h2DB.serviceImp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.h2DB.dao.UserDetailDao;
import com.h2DB.model.UserDetail;
import com.h2DB.service.UserDetailService;

@Component
public class UserDetailServiceImpl implements UserDetailService {
	@Autowired
	private UserDetailDao userDetailDao;
	
	public UserDetail getUserDetail(int id) {
		return userDetailDao.getUserDetail(id);
	}
	
	public List<UserDetail> getAllUserDetail() {
		return userDetailDao.getAllUserDetail();
	}
	public UserDetailDao getUserDetailDao() {
		return userDetailDao;
	}
	
	public int addUserDetail(UserDetail userDetail) {
		return userDetailDao.addUserDetail(userDetail);
	}
	
	public int updateUserDetail(UserDetail userDetail) {
		return userDetailDao.updateUserDetail(userDetail);
	}
	
	public int deleteUserDetail(int id) {
		return userDetailDao.deleteUserDetail(id);
	}
}