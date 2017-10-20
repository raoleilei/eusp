package cn.mldn.eusplatform.web.action;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import cn.mldn.eusplatform.service.back.IScheduleServiceBack;
import cn.mldn.eusplatform.vo.Emp;
import cn.mldn.eusplatform.vo.Schedule;
import cn.mldn.util.DateJsonValueProcessor;
import cn.mldn.util.action.ActionResourceUtil;
import cn.mldn.util.action.abs.AbstractAction;
import cn.mldn.util.factory.Factory;
import cn.mldn.util.web.ModelAndView;
import cn.mldn.util.web.ServletObjectUtil;
import cn.mldn.util.web.SplitPageUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

public class ScheduleBackAction extends AbstractAction {
	public ModelAndView addPre() { 
		ModelAndView mav = new ModelAndView(ActionResourceUtil.getPage("schedule.add.page")) ;
		IScheduleServiceBack scheduleService = Factory.getServiceInstance("schedule.service.back") ;
		try {
			mav.addObject("allItem", scheduleService.addPre());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mav ;
	}
	public ModelAndView add(Schedule vo) {
		ModelAndView mav = new ModelAndView(ActionResourceUtil.getPage("forward.page")) ;
		vo.setSeid((String)ServletObjectUtil.getRequest().getSession().getAttribute("eid"));
		IScheduleServiceBack scheduleService = Factory.getServiceInstance("schedule.service.back") ;
		try {
			if(scheduleService.add(vo)) {
				super.setUrlAndMsg(mav, "schedule.listself.action", "vo.add.success", "调度");
			}else {
				super.setUrlAndMsg(mav, "schedule.addpre.page", "vo.add.failure", "调度");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mav ;
	}
	public ModelAndView listSelf() {
		ModelAndView mav = new ModelAndView(ActionResourceUtil.getPage("schedule.listself.page")) ;
		IScheduleServiceBack scheduleService = Factory.getServiceInstance("schedule.service.back") ;
		SplitPageUtil splitPage = new SplitPageUtil("申请标题:title", "schedule.listself.action") ;
		try {
			mav.addObjectMap(scheduleService.listSelf(splitPage.getCurrentPage(), splitPage.getLineSize(), splitPage.getColumn(), splitPage.getKeyWord()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mav ;
	}
	public ModelAndView submitRequest(Long sid) {
		ModelAndView mav = new ModelAndView(ActionResourceUtil.getPage("schedule.listself.action")) ;
		IScheduleServiceBack scheduleService = Factory.getServiceInstance("schedule.service.back") ;
		try {
			scheduleService.editAudit(sid, 1) ;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mav ;
	}
	public ModelAndView scheduleShow(Long sid) {
		ModelAndView mav = new ModelAndView(ActionResourceUtil.getPage("schedule.show.page")) ;
		IScheduleServiceBack scheduleService = Factory.getServiceInstance("schedule.service.back") ;
		try {
			mav.addObjectMap(scheduleService.scheduleShow(sid));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mav ;
	}
	public ModelAndView auditScheduleApply(Long sid) {
		ModelAndView mav = new ModelAndView(ActionResourceUtil.getPage("audit.schedule.apply.page")) ;
		Long did = (Long)ServletObjectUtil.getRequest().getSession().getAttribute("did") ;
		IScheduleServiceBack scheduleService = Factory.getServiceInstance("schedule.service.back") ;
		try {
			mav.addObjectMap(scheduleService.scheduleShow(sid));
		} catch (Exception e) {
			e.printStackTrace();
		}
		mav.addObject("did", did);
		return mav ;
	}
	public ModelAndView editPre(Long sid) {
		ModelAndView mav = new ModelAndView(ActionResourceUtil.getPage("schedule.edit.page")) ;
		IScheduleServiceBack scheduleService = Factory.getServiceInstance("schedule.service.back") ;
		try {
			mav.addObjectMap(scheduleService.editPre(sid));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mav ;
	}
	public ModelAndView edit(Schedule vo) {
		ModelAndView mav = new ModelAndView(ActionResourceUtil.getPage("forward.page")) ;
		vo.setSeid((String)ServletObjectUtil.getRequest().getSession().getAttribute("eid"));
		IScheduleServiceBack scheduleService = Factory.getServiceInstance("schedule.service.back") ;
		try {
			if(scheduleService.edit(vo)) {
				super.setUrlAndMsg(mav, "schedule.listself.action", "vo.edit.success", "调度");
			}else {
				super.setUrlAndMsg(mav, "schedule.editpre.page", "vo.edit.failure", "调度");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mav ;
	}
	public ModelAndView delete(Long sid) {
		ModelAndView mav = new ModelAndView(ActionResourceUtil.getPage("forward.page")) ;
		IScheduleServiceBack scheduleService = Factory.getServiceInstance("schedule.service.back") ;
		try {
			if(scheduleService.delete(sid)) {
				super.setUrlAndMsg(mav, "schedule.listself.action", "vo.delete.success", "调度");
			}else {
				super.setUrlAndMsg(mav, "schedule.listself.page", "vo.delete.failure", "调度");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mav ;
	}
	public ModelAndView scheduleEmpEdit(long sid) {
		ModelAndView mav = new ModelAndView(ActionResourceUtil.getPage("schedule.emp.edit.page")) ;
		IScheduleServiceBack scheduleService = Factory.getServiceInstance("schedule.service.back") ;
		try {
			mav.addObjectMap(scheduleService.getScheduleEmpBySidAndAllDept(sid));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mav ;
	}
	public void empDelete(long sid,String eid) {
		IScheduleServiceBack scheduleService = Factory.getServiceInstance("schedule.service.back") ;
		try {
			super.print(scheduleService.empDelete(sid, eid)) ;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void getEmpByDid(long did,long sid) {
		IScheduleServiceBack scheduleService = Factory.getServiceInstance("schedule.service.back") ;
		JSONObject obj = new JSONObject() ;
		JSONArray arr = new JSONArray() ;
		try {
			Map<String,Object> map = scheduleService.getEmpByDnameAndTitle(did,sid) ;
			JsonConfig config = new JsonConfig() ;
			DateJsonValueProcessor dataValue = new DateJsonValueProcessor();
			config.registerJsonValueProcessor(Date.class, dataValue);
			Iterator<Emp> iter = ((List<Emp>)map.get("allEmp")).iterator() ;
			while(iter.hasNext()) {
				Emp emp = iter.next() ;
				arr.add(JSONObject.fromObject(emp,config)) ;
			}
			obj.put("allEmp",arr) ;
			obj.put("title", map.get("title")) ;
			obj.put("dname", map.get("dname")) ;
		} catch (Exception e) {
			e.printStackTrace();
		}
		super.print(obj);
	}
	public void addScheduleEmp(Long sid,String eid) {
		IScheduleServiceBack scheduleService = Factory.getServiceInstance("schedule.service.back") ;
		try {
			super.print(scheduleService.addScheduleEmp(sid, eid)) ;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
