package cn.mldn.eusplatform.web.action;

import cn.mldn.eusplatform.service.back.ILevelServiceBack;
import cn.mldn.eusplatform.vo.Level;
import cn.mldn.util.action.abs.AbstractAction;
import cn.mldn.util.factory.Factory;
import cn.mldn.util.web.ServletObjectUtil;
import net.sf.json.JSONObject;

public class LevelBackAction extends AbstractAction {
	public void get(Long lid) {
		ILevelServiceBack levelService = Factory.getServiceInstance("level.service.back") ;
		JSONObject obj = new JSONObject() ;
		try {
			obj.put("level", levelService.get(lid)) ;
		} catch (Exception e) {
			e.printStackTrace();
		}
		super.print(obj);
	}
	public void validateSalary() {
		ILevelServiceBack levelService = Factory.getServiceInstance("level.service.back") ;
		String lidtemp = ServletObjectUtil.getParam().getParameter("lid") ;
		String saltemp = ServletObjectUtil.getParam().getParameter("sal") ;
		if(lidtemp == null || "".equals(lidtemp) || saltemp == null || "".equals(saltemp)) {
			super.print(true);
		}else {
			Long lid = Long.parseLong(ServletObjectUtil.getRequest().getParameter("lid")) ;
			double sal = Double.parseDouble(ServletObjectUtil.getRequest().getParameter("sal")) ;
			Level level = null;
			try {
				level = levelService.get(lid);
			} catch (Exception e) {
				e.printStackTrace();
			}
			if(sal <= level.getHisal() && sal >= level.getLosal()) {
				super.print(true);
			}else {
				super.print(false);
			}
		}
	}
}
