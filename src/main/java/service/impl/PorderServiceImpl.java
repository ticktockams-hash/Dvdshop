package service.impl;

import java.util.List;


import dao.impl.PorderDaoImpl;
import model.Porder;

import service.PorderService;


public class PorderServiceImpl implements PorderService {

	private static PorderDaoImpl porderDaoImpl = new PorderDaoImpl();
	
	@Override
	public void addPorder(Porder porder) {
		porderDaoImpl.add(porder);
	}

	@Override
	public List<Porder> selectAllPorders() {
		return porderDaoImpl.selectAllPorders();
	}

	@Override
	public List<Porder> selectPordersByMemberId(int memberId) {
		return porderDaoImpl.selectByMemberId(memberId);
	}

	@Override
	public void updatePorder(Porder porder) {
		porderDaoImpl.update(porder);
	}

	@Override
	public void deletePorder(int id) {
		porderDaoImpl.delete(id);
	}
	

}