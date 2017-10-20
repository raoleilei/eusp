package cn.mldn.eusplatform.service.back.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import cn.mldn.eusplatform.dao.IActionDAO;
import cn.mldn.eusplatform.dao.IDeptDAO;
import cn.mldn.eusplatform.dao.IEmpDAO;
import cn.mldn.eusplatform.dao.ILevelDAO;
import cn.mldn.eusplatform.dao.IRoleDAO;
import cn.mldn.eusplatform.service.back.IEmpServiceBack;
import cn.mldn.eusplatform.vo.Dept;
import cn.mldn.eusplatform.vo.Emp;
import cn.mldn.eusplatform.vo.Level;
import cn.mldn.util.factory.Factory;
import cn.mldn.util.service.abs.AbstractService;

public class EmpServiceBackImpl extends AbstractService implements IEmpServiceBack {

	@Override
	public Map<String, Object> login(Emp emp) throws Exception {
		Map<String, Object> result = new HashMap<String, Object>() ;
		IEmpDAO empDAO = Factory.getDAOInstance("emp.dao") ;
		Emp selectEmp = empDAO.findById(emp.getEid()) ;	// 根据雇员编号查询雇员信息
		result.put("emp", selectEmp) ;	// 设置查询出来的雇员信息
		if (selectEmp != null) {	// 雇员登录成功
			IRoleDAO roleDAO = Factory.getDAOInstance("role.dao") ;	// 获取角色信息
			IActionDAO actionDAO = Factory.getDAOInstance("action.dao") ;	// 获取权限信息
			result.put("allRoles", roleDAO.findAllByDept(selectEmp.getDid())) ;	// 根据部门编号查询角色信息
			result.put("allActions", actionDAO.findAllByDept(selectEmp.getDid())) ;
		}
		return result ;
	}

	@Override
	public Map<String, Object> getDnameAndTitle(String eid) throws Exception {
		Map<String,Object> map = new HashMap<String,Object>() ;
		String title = "" ;
		String dname = "" ;
		IEmpDAO empDAO = Factory.getDAOInstance("emp.dao") ;
		ILevelDAO levelDAO = Factory.getDAOInstance("level.dao") ;
		IDeptDAO deptDAO = Factory.getDAOInstance("dept.dao") ;
		Emp emp = empDAO.findById(eid) ;
		if(emp != null) {
			title = levelDAO.findTitleByLid(emp.getLid()) ;
			dname = deptDAO.findById(emp.getDid()).getDname() ;
		}
		map.put("emp", emp) ;
		map.put("title", title) ;
		map.put("dname", dname) ;
		return map ;
	}

	@Override
	public Map<String, Object> getAllDeptAndAllLevel() throws Exception {
		Map<String,Object> map = new HashMap<String,Object>() ;
		ILevelDAO levelDAO = Factory.getDAOInstance("level.dao") ;
		IDeptDAO deptDAO = Factory.getDAOInstance("dept.dao") ;
		List<Dept> allDept = deptDAO.findAll() ;
		List<Level> allLevel = levelDAO.findAll() ;
		map.put("allDept", allDept) ;
		map.put("allLevel", allLevel) ;
		return map;
	}

	@Override
	public boolean add(Emp vo) throws Exception {
		IDeptDAO deptDAO = Factory.getDAOInstance("dept.dao") ;
		ILevelDAO levelDAO = Factory.getDAOInstance("level.dao") ;
		IEmpDAO empDAO = Factory.getDAOInstance("emp.dao") ;
		if(empDAO.findById(vo.getEid())!=null) {
			return false ;
		}
		Long lid = vo.getLid() ;
		if(lid == 0 || lid == 1) {
			return false ;
		}
		if(lid == 2 && vo.getDid() != 1) {
			Dept dept = deptDAO.findById(vo.getDid()) ;
			if(dept.getCurrnum() >= dept.getMaxnum()) {
				return false ;
			}else {
				Emp oldEmp = empDAO.findById(dept.getEid()) ; //之前的部门经理
				if(oldEmp == null) {
					dept.setEid(vo.getEid());
					dept.setCurrnum(dept.getCurrnum() + 1);
					if(deptDAO.doEdit(dept)) {
						return empDAO.doCreate(vo) ;
					}
				}else {
					oldEmp.setLid(3l);
					oldEmp.setSalary(levelDAO.findById(oldEmp.getLid()).getHisal());
					if(empDAO.doEdit(oldEmp)) {
						dept.setEid(vo.getEid());
						dept.setCurrnum(dept.getCurrnum() + 1);
						if(deptDAO.doEdit(dept)) {
							return empDAO.doCreate(vo) ;
						}
					}
				}
			}
		}else {
			Dept dept = deptDAO.findById(vo.getDid()) ;
			if(dept.getCurrnum() >= dept.getMaxnum()) {
				return false ;
			}else {
				dept.setCurrnum(dept.getCurrnum() + 1);
				if(deptDAO.doEdit(dept)) {
					return empDAO.doCreate(vo) ;
				}
			}
		}
		return false;
	}
	@Override
	public Map<String, Object> list(long currentPage,int lineSize,String column,String keyWord) throws Exception {
		Map<String,Object> map = new HashMap<String,Object>() ;
		Map<String,String> allTitle = new HashMap<String,String>() ;
		Map<String,String> allDname = new HashMap<String,String>() ;
		List<Emp> list = null ;
		Long allRecorders = 0l ;
		IDeptDAO deptDAO = Factory.getDAOInstance("dept.dao") ;
		ILevelDAO levelDAO = Factory.getDAOInstance("level.dao") ;
		IEmpDAO empDAO = Factory.getDAOInstance("emp.dao") ;
		if(column == null || "".equals(column) || keyWord == null || "".equals(keyWord)) {
			list = empDAO.findAll(currentPage, lineSize) ;
			Iterator<Emp> iter = list.iterator() ;
			while(iter.hasNext()) {
				Emp emp = iter.next() ;
				allTitle.put(emp.getEid(), levelDAO.findTitleByLid(emp.getLid())) ;
				allDname.put(emp.getEid(), deptDAO.findDnameByDid(emp.getDid())) ;
			}
			allRecorders = empDAO.getAllCount() ;
		}else {
			list = empDAO.findSplit(column, keyWord, currentPage, lineSize) ;
			Iterator<Emp> iter = list.iterator() ;
			while(iter.hasNext()) {
				Emp emp = iter.next() ;
				allTitle.put(emp.getEid(), levelDAO.findTitleByLid(emp.getLid())) ;
				allDname.put(emp.getEid(), deptDAO.findDnameByDid(emp.getDid())) ;
			}
			allRecorders = empDAO.getSplitCount(column, keyWord) ;
		}
		map.put("allEmp", list) ;
		map.put("allTitle", allTitle) ;
		map.put("allDname", allDname) ;
		map.put("allRecorders", allRecorders) ;
		return map;
	}
	@Override
	public Map<String, Object> editPre(String eid) throws Exception {
		Map<String,Object> map = new HashMap<String,Object>() ;
		ILevelDAO levelDAO = Factory.getDAOInstance("level.dao") ;
		IDeptDAO deptDAO = Factory.getDAOInstance("dept.dao") ;
		IEmpDAO empDAO = Factory.getDAOInstance("emp.dao") ;
		List<Dept> allDept = deptDAO.findAll() ;
		List<Level> allLevel = levelDAO.findAll() ;
		Emp emp = empDAO.findById(eid) ;
		map.put("allDept", allDept) ;
		map.put("allLevel", allLevel) ;
		map.put("emp",emp) ;
		return map;
	}
	@Override
	public boolean edit(Emp vo) throws Exception {
		IDeptDAO deptDAO = Factory.getDAOInstance("dept.dao") ;
		ILevelDAO levelDAO = Factory.getDAOInstance("level.dao") ;
		IEmpDAO empDAO = Factory.getDAOInstance("emp.dao") ;
		Emp oldEmp = empDAO.findById(vo.getEid()) ; //之前得雇员信息
		if(vo.getLid() == 0 || vo.getLid() == 1) { //不允许增加总监和总裁，可以修改原来的总监和总裁的信息
			if(vo.getDid() == oldEmp.getDid() && vo.getLid() == oldEmp.getLid()) {
				return empDAO.doEdit(vo) ;
			}else {
				return false ;
			}
		}	
		if(deptDAO.findById(vo.getDid()).getCurrnum() > deptDAO.findById(vo.getDid()).getMaxnum() || (deptDAO.findById(vo.getDid()).getCurrnum() == deptDAO.findById(vo.getDid()).getMaxnum() && oldEmp.getDid() != vo.getDid())) {
			return false ;
		}else if(deptDAO.findById(vo.getDid()).getCurrnum() == deptDAO.findById(vo.getDid()).getMaxnum() && oldEmp.getDid() == vo.getDid()){
			if(oldEmp.getLid() == vo.getLid()) { //没有变等级
				return empDAO.doEdit(vo) ;
			}else { //变等级了
				if(vo.getLid() > oldEmp.getLid()) { //升经理
					Dept dept = deptDAO.findById(vo.getDid()) ;
					Emp oldLeaderEmp = empDAO.findById(dept.getEid()) ;//之前的经理
					oldLeaderEmp.setLid(3l);
					oldLeaderEmp.setSalary(levelDAO.findById(oldLeaderEmp.getLid()).getHisal());
					if(empDAO.doEdit(oldLeaderEmp)) {
						dept.setEid(vo.getEid());
						if(deptDAO.doEdit(dept)) {
							return empDAO.doEdit(vo) ;
						}
					}
				}else { //降为员工
					Dept dept = deptDAO.findById(vo.getDid()) ;
					dept.setEid(null);
					if(deptDAO.doEdit(dept)) {
						return empDAO.doEdit(vo) ;
					}
				}
			}
		}else {
			if(oldEmp.getDid() == vo.getDid()) { //本部门的操作
				if(oldEmp.getLid() == vo.getLid()) { //没有变等级
					return empDAO.doEdit(vo) ;
				}else { //变等级了
					if(vo.getLid() > oldEmp.getLid()) { //升经理
						Dept dept = deptDAO.findById(vo.getDid()) ;
						Emp oldLeaderEmp = empDAO.findById(dept.getEid()) ;//之前的经理
						oldLeaderEmp.setLid(3l);
						oldLeaderEmp.setSalary(levelDAO.findById(oldLeaderEmp.getLid()).getHisal());
						if(empDAO.doEdit(oldLeaderEmp)) {
							dept.setEid(vo.getEid());
							if(deptDAO.doEdit(dept)) {
								return empDAO.doEdit(vo) ;
							}
						}
					}else { //降为员工
						Dept dept = deptDAO.findById(vo.getDid()) ;
						dept.setEid(null);
						if(deptDAO.doEdit(dept)) {
							return empDAO.doEdit(vo) ;
						}
					}
				}
			}else { //不是一个部门
				if(vo.getLid() == 2 && vo.getDid() != 1) { //修改为不是管理部的经理
					Dept dept = deptDAO.findById(vo.getDid()) ; //修改的部门
					Dept oldDept = deptDAO.findById(oldEmp.getDid()) ; //该雇员之前得部门
					Emp oldLeaderEmp = empDAO.findById(dept.getEid()) ;//修改部门之前的经理
					if(oldLeaderEmp == null) { //修改的部门没有经理
						dept.setEid(vo.getEid());
						dept.setCurrnum(dept.getCurrnum() + 1);
						oldDept.setCurrnum(oldDept.getCurrnum() - 1);
						if(oldEmp.getLid() == 2) { //之前就是经理
							oldDept.setEid(null);
						}
						if(deptDAO.doEdit(dept) && deptDAO.doEdit(oldDept)) { //原来和现在的部门都修改成功后进行雇员修改
							return empDAO.doEdit(vo) ;
						}
					}else {
						oldLeaderEmp.setLid(3l);
						oldLeaderEmp.setSalary(levelDAO.findById(oldLeaderEmp.getLid()).getHisal());
						if(empDAO.doEdit(oldLeaderEmp)) {
							dept.setEid(vo.getEid());
							dept.setCurrnum(dept.getCurrnum() + 1);
							oldDept.setCurrnum(oldDept.getCurrnum() - 1);
							if(oldEmp.getLid() == 2) { //之前就是经理
								oldDept.setEid(null);
							}
							if(deptDAO.doEdit(dept) && deptDAO.doEdit(oldDept)) { //原来和现在的部门都修改成功后进行雇员修改
								return empDAO.doEdit(vo) ;
							}
						}
					}
				}else { //管理部的经理（和普通员工一样）和所有的普通员工
					Dept dept = deptDAO.findById(vo.getDid()) ;
					Dept oldDept = deptDAO.findById(oldEmp.getDid()) ; //该雇员之前得部门
					dept.setCurrnum(dept.getCurrnum() + 1);
					oldDept.setCurrnum(oldDept.getCurrnum() - 1);
					if(oldEmp.getLid() == 2) { //之前是经理
						oldDept.setEid(null);
					}
					if(deptDAO.doEdit(dept) && deptDAO.doEdit(oldDept)) {
						return empDAO.doEdit(vo) ;
					}
				}
			}
		}
		return false;
	}
}
