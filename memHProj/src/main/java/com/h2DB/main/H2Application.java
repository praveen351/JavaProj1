package com.h2DB.main;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.h2DB.model.UserDetail;
import com.h2DB.service.UserDetailService;
import com.h2DB.serviceImp.UserDetailServiceImpl;

@Configuration
@ComponentScan(basePackages = { "com.h2DB.imp", "com.h2DB.serviceImp", "com.h2DB.util" })
public class H2Application {

	public static void main(String[] args) {
		
		ApplicationContext ctx = new AnnotationConfigApplicationContext(H2Application.class);
		
		UserDetailService service = ctx.getBean(UserDetailServiceImpl.class);
		
		UserDetail user1 = new UserDetail("Ramesh", "Fadatare", "rameshfadatare@javaguides.com", "07-03-1996");
		UserDetail user2 = new UserDetail("Raja", "Samal", "praja@gmail.com", "06-03-1998");
		UserDetail user3 = new UserDetail("Kaja", "Kumar", "praveen@gmail.com", "06-01-1998");
		UserDetail user4 = new UserDetail("Praja", "SamalK", "kumar@gmail.com", "13-10-1996");
		
		service.addUserDetail(user1);
		service.addUserDetail(user2);
		service.addUserDetail(user3);
		service.addUserDetail(user4);
		
		List<UserDetail> listData = service.getAllUserDetail();
		for (UserDetail userDetail : listData)
			System.out.println(userDetail);
		
		UserDetail userdetail = service.getUserDetail(3);
		System.out.println(userdetail);
		
		UserDetail users = new UserDetail("Pkaja", "KSamal", "skamal@gmail.com", "13-10-1996");
		users.setId(3);
		int statdetail = service.updateUserDetail(users);
		System.out.println(statdetail);
		
		UserDetail userdetails = service.getUserDetail(3);
		System.out.println(userdetails);
		
		int deleteid = service.deleteUserDetail(3);
		System.out.println(deleteid);
		
		listData = service.getAllUserDetail();
		for (UserDetail userDetail : listData)
			System.out.println(userDetail);
	}
}
