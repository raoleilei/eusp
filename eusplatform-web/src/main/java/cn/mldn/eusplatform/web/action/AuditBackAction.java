package cn.mldn.eusplatform.web.action;

import cn.mldn.eusplatform.service.back.IScheduleServiceBack;
import cn.mldn.util.action.ActionResourceUtil;
import cn.mldn.util.action.abs.AbstractAction;
import cn.mldn.util.factory.Factory;
import cn.mldn.util.web.ModelAndView;
import cn.mldn.util.web.ServletObjectUtil;
import cn.mldn.util.web.SplitPageUtil;

public class AuditBackAction extends AbstractAction {
	public ModelAndView prepareAudit() {
		ModelAndView mav = new ModelAndView(ActionResourceUtil.getPage("audit.prepare.page")) ;
		Long did = (Long)ServletObjectUtil.getRequest().getSession().getAttribute("did") ;
		IScheduleServiceBack scheduleService = Factory.getServiceInstance("schedule.service.back") ;
		SplitPageUtil splitPage = new SplitPageUtil("申请标题:title","audit.prepare.action") ;
		if(did == 2l) {
			try {
				mav.addObjectMap(scheduleService.findAllByAuditPrepare(splitPage.getCurrentPage(), splitPage.getLineSize(), splitPage.getColumn(), splitPage.getKeyWord())) ;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else {
			mav.setUrl(ActionResourceUtil.getPage("error.page"));
		}
		return mav ; 
	}
	public ModelAndView editByAuditPrepare(Long sid,int flag,String aNote) {
		ModelAndView mav = new ModelAndView(ActionResourceUtil.getPage("forward.page")) ;
		Long did = (Long)ServletObjectUtil.getRequest().getSession().getAttribute("did") ;
		IScheduleServiceBack scheduleService = Factory.getServiceInstance("schedule.service.back") ;
		if(did == 2l) {
			try {
				if(scheduleService.editByAuditPrepare(sid, flag, aNote)) {
					super.setUrlAndMsg(mav, "audit.prepare.action", "vo.add.success", "审核");
				}else {
					super.setUrlAndMsg(mav, "audit.prepare.action", "vo.add.failure", "审核");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else {
			mav.setUrl(ActionResourceUtil.getPage("error.page"));
		}
		return mav ; 
	}
	public ModelAndView list() {
		ModelAndView mav = new ModelAndView(ActionResourceUtil.getPage("audit.list.page")) ;
		IScheduleServiceBack scheduleService = Factory.getServiceInstance("schedule.service.back") ;
		SplitPageUtil splitPage = new SplitPageUtil("申请标题:title", "audit.list.action") ;
		try {
			mav.addObjectMap(scheduleService.list(splitPage.getCurrentPage(), splitPage.getLineSize(), splitPage.getColumn(), splitPage.getKeyWord()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mav ;
	}
}
