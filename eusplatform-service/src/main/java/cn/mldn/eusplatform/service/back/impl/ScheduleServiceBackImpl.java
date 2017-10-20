package cn.mldn.eusplatform.service.back.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import cn.mldn.eusplatform.dao.IDeptDAO;
import cn.mldn.eusplatform.dao.IEmpDAO;
import cn.mldn.eusplatform.dao.IItemDAO;
import cn.mldn.eusplatform.dao.ILevelDAO;
import cn.mldn.eusplatform.dao.IReportDAO;
import cn.mldn.eusplatform.dao.IScheduleDAO;
import cn.mldn.eusplatform.dao.IScheduleEmpDAO;
import cn.mldn.eusplatform.service.back.IScheduleServiceBack;
import cn.mldn.eusplatform.vo.Dept;
import cn.mldn.eusplatform.vo.Emp;
import cn.mldn.eusplatform.vo.Item;
import cn.mldn.eusplatform.vo.Schedule;
import cn.mldn.eusplatform.vo.ScheduleEmp;
import cn.mldn.util.factory.Factory;
import cn.mldn.util.service.abs.AbstractService;
import cn.mldn.util.web.ServletObjectUtil;

public class ScheduleServiceBackImpl extends AbstractService implements IScheduleServiceBack{
	
	@Override
	public List<Item> addPre() throws Exception {
		IItemDAO itemDAO = Factory.getDAOInstance("item.dao") ;
		return itemDAO.findAll();
	}

	@Override
	public boolean add(Schedule vo) throws Exception {
		IScheduleDAO scheduleDAO = Factory.getDAOInstance("schedule.dao") ;
		IScheduleEmpDAO scheduleEmpDAO = Factory.getDAOInstance("scheduleemp.dao") ;
		ScheduleEmp scheduleEmp = new ScheduleEmp() ;
		scheduleEmp.setEid(vo.getSeid());
		vo.setSubdate(new Date());
		vo.setAudit(0);
		vo.setEcount(1);
		if(scheduleDAO.doCreate(vo)) {
			scheduleEmp.setSid(scheduleDAO.getMaxSid());
			return scheduleEmpDAO.doCreate(scheduleEmp) ;
		}
		return false ;
	}
	
	@Override
	public Map<String, Object> listSelf(long currentPage, int lineSize, String column, String keyWord)
			throws Exception {
		Map<String,Object> map = new HashMap<String,Object>() ;
		IScheduleDAO scheduleDAO = Factory.getDAOInstance("schedule.dao") ;
		String seid = (String)ServletObjectUtil.getRequest().getSession().getAttribute("eid") ;
		Set<Integer> audit = new HashSet<Integer>() ;
		Collections.addAll(audit, 0,1,2,3,4,5,6) ;
		if(column == null || "".equals(column) || keyWord == null || "".equals(keyWord)) {
			map.put("allSchedule", scheduleDAO.findAllByAuditAndSeid(currentPage, lineSize, seid, audit)) ;
			map.put("allRecorders", scheduleDAO.getAllCountSeid(seid, audit)) ;
		}else {
			map.put("allSchedule", scheduleDAO.findSplitByAuditAndSeid(column, keyWord, currentPage, lineSize, seid, audit)) ;
			map.put("allRecorders", scheduleDAO.getSplitCountSeid(column, keyWord, seid, audit)) ;
		}
		return map;
	}
	@Override
	public boolean editAudit(long sid, int audit) throws Exception {
		IScheduleDAO scheduleDAO = Factory.getDAOInstance("schedule.dao") ;
		return scheduleDAO.doEditAudit(sid, audit);
	}
	@Override
	public Map<String, Object> scheduleShow(long sid) throws Exception {
		IScheduleDAO scheduleDAO = Factory.getDAOInstance("schedule.dao") ;
		IScheduleEmpDAO scheduleEmpDAO = Factory.getDAOInstance("scheduleemp.dao") ;
		IEmpDAO empDAO = Factory.getDAOInstance("emp.dao") ;
		IDeptDAO deptDAO = Factory.getDAOInstance("dept.dao") ;
		ILevelDAO levelDAO = Factory.getDAOInstance("level.dao") ;
		IItemDAO itemDAO = Factory.getDAOInstance("item.dao") ;
		Map<String,Object> map = new HashMap<String,Object>() ;
		Map<String,String> dname = new HashMap<String,String>() ;
		Map<String,String> title = new HashMap<String,String>() ;
		List<Emp> allScheduleEmp = new ArrayList<Emp>() ;
		Schedule schedule = scheduleDAO.findById(sid) ;
		Set<String> allEmpEid = scheduleEmpDAO.findEidBySid(sid) ;
		Iterator<String> iter = allEmpEid.iterator() ;
		while(iter.hasNext()) {
			String eid = iter.next() ;
			Emp emp = empDAO.findById(eid) ;
			allScheduleEmp.add(emp) ;
			dname.put(eid, deptDAO.findDnameByDid(emp.getDid())) ;
			title.put(eid, levelDAO.findTitleByLid(emp.getLid())) ;
		}
		map.put("schedule",schedule) ;
		map.put("allScheduleEmp",allScheduleEmp) ;
		map.put("itemTitle",itemDAO.findById(schedule.getIid()).getTitle()) ;
		map.put("dname",dname) ;
		map.put("title",title) ;
		return map;
	}
	@Override
	public Map<String, Object> editPre(long sid) throws Exception {
		IScheduleDAO scheduleDAO = Factory.getDAOInstance("schedule.dao") ;
		IItemDAO itemDAO = Factory.getDAOInstance("item.dao") ;
		Map<String,Object> map = new HashMap<String,Object>() ;
		map.put("schedule", scheduleDAO.findById(sid)) ;
		map.put("allItem", itemDAO.findAll()) ;
		return map;
	}
	@Override
	public boolean edit(Schedule vo) throws Exception {
		IScheduleDAO scheduleDAO = Factory.getDAOInstance("schedule.dao") ;
		Schedule schedule = scheduleDAO.findById(vo.getSid()) ;
		if(schedule.getAudit() == 0 || schedule.getAudit() == 1 ) {
			vo.setAudit(schedule.getAudit());
			vo.setEcount(schedule.getEcount());
			vo.setSubdate(new Date());
			return scheduleDAO.doEdit(vo) ;
		}else {
			return false ;
		}
	}
	@Override
	public boolean delete(long sid) throws Exception {
		IScheduleDAO scheduleDAO = Factory.getDAOInstance("schedule.dao") ;
		IScheduleEmpDAO scheduleEmpDAO = Factory.getDAOInstance("scheduleemp.dao") ;
		if(scheduleEmpDAO.doRemoveBySid(sid)) {
			return scheduleDAO.doRemove(sid) ;
		}
		return false;
	}
	@Override
	public Map<String, Object> getScheduleEmpBySidAndAllDept(long sid) throws Exception {
		IScheduleDAO scheduleDAO = Factory.getDAOInstance("schedule.dao") ;
		IScheduleEmpDAO scheduleEmpDAO = Factory.getDAOInstance("scheduleemp.dao") ;
		IEmpDAO empDAO = Factory.getDAOInstance("emp.dao") ;
		IDeptDAO deptDAO = Factory.getDAOInstance("dept.dao") ;
		ILevelDAO levelDAO = Factory.getDAOInstance("level.dao") ;
		Map<String,Object> map = new HashMap<String,Object>() ;
		Map<String,String> dname = new HashMap<String,String>() ;
		Map<String,String> title = new HashMap<String,String>() ;
		List<Emp> allScheduleEmp = new ArrayList<Emp>() ;
		List<Dept> allDept = deptDAO.findAll() ;
		Schedule schedule = scheduleDAO.findById(sid) ;
		Set<String> eids = scheduleEmpDAO.findEidBySid(sid) ;
		Iterator<String> iter = eids.iterator() ;
		while(iter.hasNext()) {
			String eid = iter.next() ;
			Emp emp = empDAO.findById(eid) ;
			allScheduleEmp.add(emp) ;
			dname.put(eid, deptDAO.findDnameByDid(emp.getDid())) ;
			title.put(eid, levelDAO.findTitleByLid(emp.getLid())) ;
		}
		map.put("allScheduleEmp", allScheduleEmp) ;
		map.put("schedule",schedule) ;
		map.put("allDept", allDept) ;
		map.put("dname", dname) ;
		map.put("title", title) ;
		return map;
	}
	@Override
	public boolean empDelete(long sid, String eid) throws Exception {
		IScheduleDAO scheduleDAO = Factory.getDAOInstance("schedule.dao") ;
		IScheduleEmpDAO scheduleEmpDAO = Factory.getDAOInstance("scheduleemp.dao") ;
		if(scheduleEmpDAO.doRemoveBySidAndEid(sid, eid)) {
			return scheduleDAO.doEditEcount(sid, scheduleDAO.findById(sid).getEcount() - 1) ;
		}
		return false;
	}
	@Override
	public Map<String, Object> getEmpByDnameAndTitle(long did,long sid) throws Exception {
		long lid = (Long)ServletObjectUtil.getRequest().getSession().getAttribute("lid") ;
		IEmpDAO empDAO = Factory.getDAOInstance("emp.dao") ;
		ILevelDAO levelDAO = Factory.getDAOInstance("level.dao") ;
		IDeptDAO deptDAO = Factory.getDAOInstance("dept.dao") ;
		Map<String,Object> map = new HashMap<String,Object>() ;
		Map<String,String> title = new HashMap<String,String>() ;
		Map<String,String> dname = new HashMap<String,String>() ;
		List<Emp> allEmp = empDAO.findByDidAndLid(did, lid,sid) ;
		Iterator<Emp> iter = allEmp.iterator() ;
		while(iter.hasNext()) {
			Emp emp = iter.next() ;
			dname.put(emp.getEid(), deptDAO.findDnameByDid(emp.getDid())) ;
			title.put(emp.getEid(), levelDAO.findTitleByLid(emp.getLid())) ;
		}
		map.put("allEmp", allEmp) ;
		map.put("dname", dname) ;
		map.put("title", title) ;
		return map;
	}
	@Override
	public boolean addScheduleEmp(long sid, String eid) throws Exception {
		IScheduleDAO scheduleDAO = Factory.getDAOInstance("schedule.dao") ;
		IScheduleEmpDAO scheduleEmpDAO = Factory.getDAOInstance("scheduleemp.dao") ;
		ScheduleEmp scheduleEmp = new ScheduleEmp() ;
		scheduleEmp.setSid(sid);
		scheduleEmp.setEid(eid);
		if(scheduleDAO.doEditEcount(sid, scheduleDAO.findById(sid).getEcount() + 1)) {
			return scheduleEmpDAO.doCreate(scheduleEmp) ;
		}
		return false;
	}
	@Override
	public Map<String, Object> findAllByAuditPrepare(long currentPage, int lineSize, String column, String keyWord)
			throws Exception {
		IScheduleDAO scheduleDAO = Factory.getDAOInstance("schedule.dao") ;
		Map<String,Object> map = new HashMap<String,Object>() ;
		Set<Integer> audit = new HashSet<Integer>() ;
		audit.add(1) ;
		if(column == null || "".equals(column) || keyWord == null || "".equals(keyWord)) {
			map.put("allSchedule", scheduleDAO.findAllByAuditAndSeid(currentPage, lineSize, null, audit)) ;
			map.put("allRecorders", scheduleDAO.getAllCountSeid(null, audit)) ;
		}else {
			map.put("allSchedule", scheduleDAO.findSplitByAuditAndSeid(column, keyWord, currentPage, lineSize, null, audit)) ;
			map.put("allRecorders", scheduleDAO.getSplitCountSeid(column, keyWord, null, audit)) ;
		}
		return map;
	}
	@Override
	public boolean editByAuditPrepare(Long sid, int flag ,String aNote) throws Exception {
		IScheduleDAO scheduleDAO = Factory.getDAOInstance("schedule.dao") ;
		String aeid = (String)ServletObjectUtil.getRequest().getSession().getAttribute("eid") ;
		Schedule schedule = scheduleDAO.findById(sid) ;
		schedule.setAuddate(new Date());
		if(schedule.getAnote() == null || "".equals(schedule.getAnote())) {
			schedule.setAnote(aNote);
		}else {
			schedule.setAnote(schedule.getAnote() + "\n" + aNote);
		}
		schedule.setAeid(aeid);
		if(flag == 0 || schedule.getSdate().getTime() < schedule.getSubdate().getTime()) {
			schedule.setAudit(2);
			return scheduleDAO.doEdit(schedule) ;
		}else if(flag == 0 || schedule.getSdate().getTime() == schedule.getSubdate().getTime()) {
			schedule.setAudit(3);
			return scheduleDAO.doEdit(schedule) ;
		}else {
			schedule.setAudit(4);
			return scheduleDAO.doEdit(schedule) ;
		}
	}
	@Override
	public Map<String, Object> list(long currentPage, int lineSize, String column, String keyWord)
			throws Exception {
		Map<String,Object> map = new HashMap<String,Object>() ;
		IScheduleDAO scheduleDAO = Factory.getDAOInstance("schedule.dao") ;
		Set<Integer> audit = new HashSet<Integer>() ;
		Collections.addAll(audit, 0,1,2,3,4,5,6) ;
		if(column == null || "".equals(column) || keyWord == null || "".equals(keyWord)) {
			map.put("allSchedule", scheduleDAO.findAllByAuditAndSeid(currentPage, lineSize, null, audit)) ;
			map.put("allRecorders", scheduleDAO.getAllCountSeid(null, audit)) ;
		}else {
			map.put("allSchedule", scheduleDAO.findSplitByAuditAndSeid(column, keyWord, currentPage, lineSize, null, audit)) ;
			map.put("allRecorders", scheduleDAO.getSplitCountSeid(column, keyWord, null, audit)) ;
		}
		return map;
	}
	@Override
	public Map<String, Object> EmpListPrepare(long currentPage, int lineSize, String column, String keyWord)
			throws Exception {
		Map<String,Object> map = new HashMap<String,Object>() ;
		String eid = (String)ServletObjectUtil.getRequest().getSession().getAttribute("eid") ;
		IScheduleEmpDAO scheduleEmpDAO = Factory.getDAOInstance("scheduleemp.dao") ;
		IScheduleDAO scheduleDAO = Factory.getDAOInstance("schedule.dao") ;
		Set<Long> sid = scheduleEmpDAO.findSidByEid(eid) ;
		Set<Integer> audit = new HashSet<Integer>() ;
		audit.add(3) ;
		audit.add(4) ;
		if(column == null || "".equals(column) || keyWord == null || "".equals(keyWord)) {
			map.put("allSchedule", scheduleDAO.findAllByAuditAndSid(currentPage, lineSize, sid, audit)) ;
			map.put("allRecorders", scheduleDAO.getAllCount(sid, audit)) ;
		}else {
			map.put("allSchedule", scheduleDAO.findSplitByAuditAndSid(column, keyWord, currentPage, lineSize, sid, audit)) ;
			map.put("allRecorders", scheduleDAO.getSplitCount(column, keyWord, sid, audit)) ;
		}
		return map;
	}
	@Override
	public Map<String, Object> EmpList(long currentPage, int lineSize, String column, String keyWord) throws Exception {
		Map<String,Object> map = new HashMap<String,Object>() ;
		String eid = (String)ServletObjectUtil.getRequest().getSession().getAttribute("eid") ;
		IScheduleEmpDAO scheduleEmpDAO = Factory.getDAOInstance("scheduleemp.dao") ;
		IScheduleDAO scheduleDAO = Factory.getDAOInstance("schedule.dao") ;
		IReportDAO reportDAO = Factory.getDAOInstance("report.dao") ;
		int flag = 0 ; //flag为0表示此人该调度未提交报告
		Map<Long,Integer> mapFlag = new HashMap<Long,Integer>() ;
		Set<Long> sid = scheduleEmpDAO.findSidByEid(eid) ;
		Set<Integer> audit = new HashSet<Integer>() ;
		audit.add(5) ;
		audit.add(6) ;
		if(column == null || "".equals(column) || keyWord == null || "".equals(keyWord)) {
			List<Schedule> allSchedule = scheduleDAO.findAllByAuditAndSid(currentPage, lineSize, sid, audit) ;
			Iterator<Schedule> iter = allSchedule.iterator() ;
			while(iter.hasNext()) {
				Schedule schedule = iter.next() ;
				String note = reportDAO.findNoteBySidAndEid(schedule.getSid(), eid) ;
				if(note != null && !("".equals(note))) {
					flag = 1 ; //已经填了报告
				}
				mapFlag.put(schedule.getSid(), flag) ; 
			}
			map.put("flag", mapFlag) ;
			map.put("allSchedule", allSchedule) ;
			map.put("allRecorders", scheduleDAO.getAllCount(sid, audit)) ;
		}else {
			List<Schedule> allSchedule = scheduleDAO.findAllByAuditAndSid(currentPage, lineSize, sid, audit) ;
			Iterator<Schedule> iter = allSchedule.iterator() ;
			while(iter.hasNext()) {
				Schedule schedule = iter.next() ;
				String note = reportDAO.findNoteBySidAndEid(schedule.getSid(), eid) ;
				if(note != null && !("".equals(note))) {
					flag = 1 ; //已经填了报告
				}
				mapFlag.put(schedule.getSid(), flag) ; 
			}
			map.put("flag", mapFlag) ;
			map.put("allSchedule", scheduleDAO.findSplitByAuditAndSid(column, keyWord, currentPage, lineSize, sid, audit)) ;
			map.put("allRecorders", scheduleDAO.getSplitCount(column, keyWord, sid, audit)) ;
		}
		return map;
	}
}
