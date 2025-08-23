package service.impl;

import java.util.List;


import dao.impl.EmployDaoImpl;
import model.Employ;
import service.EmployService;

public class EmployServiceImpl implements EmployService {
	private static EmployDaoImpl employDaoImpl = new EmployDaoImpl();

	public static void main(String[] args) {

	}

	@Override
	public void addEmploy(Employ employ) {
		employDaoImpl.add(employ); 
	}

	@Override
	public Employ login(String username, String password) {
		return getSingleEmploy(employDaoImpl.selectByUsernameAndPassword(username, password));
	}

	@Override
	public Employ findEmploy(String username) {
		return getSingleEmploy(employDaoImpl.selectByUsername(username));
	}

	@Override
	public boolean isUsernameTaken(String username) {
		return !employDaoImpl.selectByUsername(username).isEmpty();
	}
	
	@Override
	public List<Employ> selectAllEmploys() {
		return employDaoImpl.selectAllEmploys();
	}

	@Override
	public void updateEmploy(Employ employ) {
		employDaoImpl.update(employ);
	}

	@Override
	public void deleteEmploy(int id) {
		employDaoImpl.delete(id);
	}

	@Override
	public Employ findEmploy(int id) {
		return getSingleEmploy(employDaoImpl.selectById(id));
	}


	private Employ getSingleEmploy(List<Employ> employs) {
		return employs.isEmpty() ? null : employs.get(0);
	}
}