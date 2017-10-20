package cn.mldn.eusplatform.service.back.impl;

import cn.mldn.eusplatform.dao.ILevelDAO;
import cn.mldn.eusplatform.service.back.ILevelServiceBack;
import cn.mldn.eusplatform.vo.Level;
import cn.mldn.util.factory.Factory;
import cn.mldn.util.service.abs.AbstractService;

public class LevelServiceBackImpl extends AbstractService implements ILevelServiceBack {

	@Override
	public Level get(Long id) throws Exception {
		ILevelDAO levelDAO = Factory.getDAOInstance("level.dao") ;
		return levelDAO.findById(id);
	}

}
