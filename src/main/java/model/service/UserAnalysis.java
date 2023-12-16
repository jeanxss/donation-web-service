package model.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import model.User;
import model.dao.jdbc.UserDAO;

// an example business class
public class UserAnalysis {
	private UserDAO dao;
	
	public UserAnalysis() {}
	
	public UserAnalysis(UserDAO dao) {
		super();
		this.dao = dao;
	}

	// an example business method
	

}
