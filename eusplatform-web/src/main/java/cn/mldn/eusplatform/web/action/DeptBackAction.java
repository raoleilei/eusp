package cn.mldn.eusplatform.web.action;

import cn.mldn.eusplatform.service.back.IDeptServiceBack;
import cn.mldn.eusplatform.vo.Dept;
import cn.mldn.util.action.ActionResourceUtil;
import cn.mldn.util.action.abs.AbstractAction;
import cn.mldn.util.factory.Factory;
import cn.mldn.util.web.ModelAndView;
import cn.mldn.util.web.ServletObjectUtil;

public class DeptBackAction extends AbstractAction {
	public ModelAndView list() {
		ModelAndView mav = new ModelAndView(ActionResourceUtil.getPage("dept.list.page")) ;
		IDeptServiceBack deptService = Factory.getServiceInstance("dept.service.back") ;
		try {
			mav.addObjectMap(deptService.list());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mav ;
	}
	public void edit(Dept vo) {
		IDeptServiceBack deptService = Factory.getServiceInstance("dept.service.back") ;
		try {
			super.print(deptService.editDnameAndMaxnumByDid(vo));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void add(Dept vo) {
		IDeptServiceBack deptService = Factory.getServiceInstance("dept.service.back") ;
		Long did = (Long)ServletObjectUtil.getRequest().getSession().getAttribute("did") ;
		vo.setCurrnum(0);
		if(did == 2l) {
			try {
				super.print(deptService.add(vo));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else {
			super.print(false);
		}
		
	}
}
