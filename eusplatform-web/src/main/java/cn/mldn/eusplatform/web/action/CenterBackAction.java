package cn.mldn.eusplatform.web.action;

import cn.mldn.eusplatform.service.back.IReportServiceBack;
import cn.mldn.eusplatform.service.back.IScheduleServiceBack;
import cn.mldn.eusplatform.vo.Report;
import cn.mldn.util.action.ActionResourceUtil;
import cn.mldn.util.action.abs.AbstractAction;
import cn.mldn.util.factory.Factory;
import cn.mldn.util.web.ModelAndView;
import cn.mldn.util.web.ServletObjectUtil;
import cn.mldn.util.web.SplitPageUtil;

public class CenterBackAction extends AbstractAction {
	public ModelAndView EmpListPrepare() {
		ModelAndView mav = new ModelAndView(ActionResourceUtil.getPage("center.prepare.page")) ;
		IScheduleServiceBack scheduleService = Factory.getServiceInstance("schedule.service.back") ;
		String eid = (String)ServletObjectUtil.getRequest().getSession().getAttribute("eid") ;
		SplitPageUtil splitPage = new SplitPageUtil("申请标题:title","center.prepare.action") ;
		try {
			mav.addObjectMap(scheduleService.EmpListPrepare(splitPage.getCurrentPage(), splitPage.getLineSize(), splitPage.getColumn(), splitPage.getKeyWord()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		mav.addObject("loginEid", eid);
		return mav ;
	}
	public ModelAndView submitTask(Long sid) {
		ModelAndView mav = new ModelAndView(ActionResourceUtil.getPage("forward.page")) ;
		IScheduleServiceBack scheduleService = Factory.getServiceInstance("schedule.service.back") ;
		try {
			if(scheduleService.editAudit(sid, 5)) {
				super.setUrlAndMsg(mav, "center.prepare.action", "task.add.success");
			}else {
				super.setUrlAndMsg(mav, "center.prepare.action", "task.add.failure");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mav ; 
	}
	public ModelAndView EmpList() {
		ModelAndView mav = new ModelAndView(ActionResourceUtil.getPage("center.list.page")) ;
		IScheduleServiceBack scheduleService = Factory.getServiceInstance("schedule.service.back") ;
		SplitPageUtil splitPage = new SplitPageUtil("申请标题:title","center.prepare.action") ;
		try {
			mav.addObjectMap(scheduleService.EmpList(splitPage.getCurrentPage(), splitPage.getLineSize(), splitPage.getColumn(), splitPage.getKeyWord()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mav ;
	}
	public ModelAndView addReport(Report vo) {
		ModelAndView mav = new ModelAndView(ActionResourceUtil.getPage("forward.page")) ;
		IReportServiceBack reportService = Factory.getServiceInstance("report.service.back") ;
		try {
			if(reportService.add(vo)) {
				super.setUrlAndMsg(mav, "center.list.action", "vo.add.success", "报告");
			}else {
				super.setUrlAndMsg(mav, "center.list.action", "vo.add.failure", "报告");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mav ;
	}
	public ModelAndView scheduleReport(Long sid) {
		ModelAndView mav = new ModelAndView(ActionResourceUtil.getPage("center.report.page")) ;
		IReportServiceBack reportService = Factory.getServiceInstance("report.service.back") ;
		try {
			mav.addObjectMap(reportService.scheduleReport(sid));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mav ; 
	}
}
