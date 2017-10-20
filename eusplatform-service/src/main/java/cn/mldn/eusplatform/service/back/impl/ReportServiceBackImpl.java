package cn.mldn.eusplatform.service.back.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
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
import cn.mldn.eusplatform.service.back.IReportServiceBack;
import cn.mldn.eusplatform.vo.Emp;
import cn.mldn.eusplatform.vo.Report;
import cn.mldn.eusplatform.vo.Schedule;
import cn.mldn.util.factory.Factory;
import cn.mldn.util.service.abs.AbstractService;
import cn.mldn.util.web.ServletObjectUtil;

public class ReportServiceBackImpl extends AbstractService implements IReportServiceBack {

	@Override
	public boolean add(Report vo) throws Exception {
		IReportDAO reportDAO = Factory.getDAOInstance("report.dao") ;
		IScheduleDAO scheduleDAO = Factory.getDAOInstance("schedule.dao") ;
		IScheduleEmpDAO scheduleEmpDAO = Factory.getDAOInstance("scheduleemp.dao") ;
		String loginEid = (String) ServletObjectUtil.getRequest().getSession().getAttribute("eid") ;
		vo.setEid(loginEid);
		vo.setSubdate(new Date());
		boolean flag = false ;
		boolean temp = true ;
		Set<String> eids = scheduleEmpDAO.findEidBySid(vo.getSid()) ;
		Iterator<String> iter = eids.iterator() ;
		while(iter.hasNext()) {
			String eid = iter.next() ;
			String note = reportDAO.findNoteBySidAndEid(vo.getSid(), eid) ;
			if(!loginEid.equals(eid)) {
				if(note == null || "".equals(note)) {
					temp = false ;
				}
			}
		}
		if(reportDAO.doCreate(vo)) {
			flag = true ;
		}
		if(temp) {
			scheduleDAO.doEditAudit(vo.getSid(), 6) ;
		}
		return flag;
	}

	@Override
	public Map<String, Object> scheduleReport(Long sid) throws Exception {
		IReportDAO reportDAO = Factory.getDAOInstance("report.dao") ;
		ILevelDAO levelDAO = Factory.getDAOInstance("level.dao") ;
		IDeptDAO deptDAO = Factory.getDAOInstance("dept.dao") ;
		IItemDAO itemDAO = Factory.getDAOInstance("item.dao") ;
		IEmpDAO empDAO = Factory.getDAOInstance("emp.dao") ;
		IScheduleDAO scheduleDAO = Factory.getDAOInstance("schedule.dao") ;
		IScheduleEmpDAO scheduleEmpDAO = Factory.getDAOInstance("scheduleemp.dao") ;
		Map<String,Object> map = new HashMap<String,Object>() ;
		Map<String,String> levelTitle = new HashMap<String,String>() ;
		Map<String,String> dname = new HashMap<String,String>() ;
		Map<String,Object> empReport = new HashMap<String,Object>() ;
		Schedule schedule = scheduleDAO.findById(sid) ;
		String itemTitle = itemDAO.findById(schedule.getIid()).getTitle() ;
		String seidReport = reportDAO.findNoteBySidAndEid(schedule.getSid(), schedule.getSeid()) ;
		List<Emp> allEmp = new ArrayList<Emp>() ; 
		int count = 0 ;
		Set<String> allEmpEid = scheduleEmpDAO.findEidBySid(schedule.getSid()) ;
		Iterator<String> iter = allEmpEid.iterator() ;
		while(iter.hasNext()) {
			Report report = null ;
			String eid = iter.next() ;
			Emp emp = empDAO.findById(eid) ;
			levelTitle.put(eid, levelDAO.findTitleByLid(emp.getLid())) ;
			dname.put(eid, deptDAO.findDnameByDid(emp.getDid())) ;
			if(reportDAO.findBySidAndEid(schedule.getSid(), eid) != null) {
				report = reportDAO.findBySidAndEid(schedule.getSid(), eid) ;
				count ++ ;
			}
			empReport.put(eid, report) ;
			allEmp.add(emp) ;
		}
		map.put("schedule", schedule) ;
		map.put("itemTitle",itemTitle) ;
		map.put("allEmp", allEmp) ;
		map.put("dname", dname) ;
		map.put("levelTitle",levelTitle) ;
		map.put("empReport", empReport) ;
		map.put("count",count) ;
		map.put("seidReport", seidReport) ;
		return map;
	}

}
