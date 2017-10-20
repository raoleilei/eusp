package cn.mldn.eusplatform.service.back.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import cn.mldn.eusplatform.dao.IDeptDAO;
import cn.mldn.eusplatform.dao.IEmpDAO;
import cn.mldn.eusplatform.service.back.IDeptServiceBack;
import cn.mldn.eusplatform.vo.Dept;
import cn.mldn.util.factory.Factory;
import cn.mldn.util.service.abs.AbstractService;

public class DeptServiceBackImpl extends AbstractService implements IDeptServiceBack {

	@Override
	public Map<String,Object> list() throws Exception {
		IEmpDAO empDAO = Factory.getDAOInstance("emp.dao") ;
		IDeptDAO deptDAO = Factory.getDAOInstance("dept.dao") ;
		Map<String,Object> map = new HashMap<String,Object>() ;
		Map<String,String> leaderEname = new HashMap<String,String>() ;
		List<Dept> list = deptDAO.findAll() ;
		Iterator<Dept> iter = list.iterator() ;
		while(iter.hasNext()) {
			Dept dept = iter.next() ;
			if(dept.getEid() != null && !("".equals(dept.getEid()))) {
				leaderEname.put(dept.getEid(), empDAO.findById(dept.getEid()).getEname()) ;
			}
		}
		map.put("allDept", list) ;
		map.put("leaderEname", leaderEname) ;
		return map;
	}

	@Override
	public boolean editDnameAndMaxnumByDid(Dept vo) throws Exception {
		IDeptDAO deptDAO = Factory.getDAOInstance("dept.dao") ;
		if(deptDAO.findDnameByOther(vo) == false) {
			return deptDAO.doEditDnameAndMaxnumByDid(vo) ;
		}
		return false;
	}

	@Override
	public boolean add(Dept vo) throws Exception {
		IDeptDAO deptDAO = Factory.getDAOInstance("dept.dao") ;
		if(deptDAO.findByDname(vo.getDname()) == null) {
			return deptDAO.doCreate(vo) ;
		}
		return false;
	}

}
