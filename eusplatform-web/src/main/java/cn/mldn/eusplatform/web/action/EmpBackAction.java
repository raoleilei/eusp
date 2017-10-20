package cn.mldn.eusplatform.web.action;

import java.util.Date;
import java.util.Map;

import cn.mldn.eusplatform.service.back.IEmpServiceBack;
import cn.mldn.eusplatform.vo.Emp;
import cn.mldn.util.DateJsonValueProcessor;
import cn.mldn.util.action.ActionResourceUtil;
import cn.mldn.util.action.abs.AbstractAction;
import cn.mldn.util.enctype.PasswordUtil;
import cn.mldn.util.factory.Factory;
import cn.mldn.util.web.ModelAndView;
import cn.mldn.util.web.ServletObjectUtil;
import cn.mldn.util.web.SplitPageUtil;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

public class EmpBackAction extends AbstractAction {
	public void getDnameAndTitle(String eid) {
		IEmpServiceBack empService = Factory.getServiceInstance("emp.service.back") ;
		Long did = (Long)ServletObjectUtil.getRequest().getSession().getAttribute("did") ;
		try {
			Map<String,Object> map = empService.getDnameAndTitle(eid) ;
			Emp emp = (Emp)map.get("emp") ;
			JSONObject obj = new JSONObject() ;
			JsonConfig config = new JsonConfig() ;
			DateJsonValueProcessor dataValue = new DateJsonValueProcessor();
			config.registerJsonValueProcessor(Date.class, dataValue);
			obj.put("emp", JSONObject.fromObject(emp,config)) ;
			obj.put("title", map.get("title")) ;
			obj.put("dname", map.get("dname")) ;
			obj.put("loginMemberDid", did) ;
			super.print(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public ModelAndView addPre() {
		ModelAndView mav = new ModelAndView(ActionResourceUtil.getPage("emp.add.page")) ;
		IEmpServiceBack empService = Factory.getServiceInstance("emp.service.back") ;
		try {
			Map<String,Object> map = empService.getAllDeptAndAllLevel() ;
			mav.addObjectMap(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mav ;
	}
	public ModelAndView add(Emp vo) {
		ModelAndView mav = new ModelAndView(ActionResourceUtil.getPage("forward.page")) ;
		IEmpServiceBack empService = Factory.getServiceInstance("emp.service.back") ;
		vo.setIneid((String)ServletObjectUtil.getRequest().getSession().getAttribute("eid")) ;
		vo.setPassword(PasswordUtil.encoder(vo.getPassword()));
		String photo = "nophoto.png" ;
		if(ServletObjectUtil.getParam().isUpload("photo")) {
			photo = ServletObjectUtil.getParam().createUploadFileName("photo").get(0) ;
		}
		vo.setPhoto(photo);
		try {
			if(empService.add(vo)) {
				ServletObjectUtil.getParam().saveUploadFile("photo", ServletObjectUtil.getApplication().getRealPath("/upload/emp/") + vo.getPhoto()) ;
				super.setUrlAndMsg(mav, "emp.list.action", "vo.add.success", "雇员");
			}else {
				super.setUrlAndMsg(mav, "emp.addpre.action", "vo.add.failure", "雇员");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mav ;
	}
	public ModelAndView list() {
		ModelAndView mav = new ModelAndView(ActionResourceUtil.getPage("emp.list.page")) ;
		IEmpServiceBack empService = Factory.getServiceInstance("emp.service.back") ;
		Long did = (Long)ServletObjectUtil.getRequest().getSession().getAttribute("did") ;
		mav.addObject("loginMemberDid", did);
		SplitPageUtil splitPage = new SplitPageUtil("姓名:ename","emp.list.action") ;
		try {
			mav.addObjectMap(empService.list(splitPage.getCurrentPage(), splitPage.getLineSize(), splitPage.getColumn(), splitPage.getKeyWord()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mav ;
	}
	public ModelAndView editPre(String eid) {
		ModelAndView mav = new ModelAndView(ActionResourceUtil.getPage("emp.edit.page")) ;
		IEmpServiceBack empService = Factory.getServiceInstance("emp.service.back") ;
		try {
			mav.addObjectMap(empService.editPre(eid));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mav ;
	}
	public ModelAndView edit(Emp vo) {
		ModelAndView mav = new ModelAndView(ActionResourceUtil.getPage("forward.page")) ;
		IEmpServiceBack empService = Factory.getServiceInstance("emp.service.back") ;
		String eid = ServletObjectUtil.getParam().getParameter("eid") ;
		vo.setEid(eid);
		vo.setIneid((String)ServletObjectUtil.getRequest().getSession().getAttribute("eid")) ;
		vo.setPassword(PasswordUtil.encoder(vo.getPassword()));
		String photo = ServletObjectUtil.getParam().getParameter("pic") ;
		if(ServletObjectUtil.getParam().isUpload("photo")) {
			photo = ServletObjectUtil.getParam().createUploadFileName("photo").get(0) ;
		}
		vo.setPhoto(photo);
		try {
			if(empService.edit(vo)) {
				if(ServletObjectUtil.getParam().isUpload("photo")) {
					ServletObjectUtil.getParam().saveUploadFile("photo", ServletObjectUtil.getApplication().getRealPath("/upload/emp/") + vo.getPhoto()) ;
				}
				super.setUrlAndMsg(mav, "emp.list.action", "vo.edit.success", "雇员");
			}else {
				super.setUrlAndMsg(mav, "emp.list.action", "vo.edit.failure", "雇员");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mav ;
	}
}
