package cn.mldn.eusplatform.service.back;

import cn.mldn.eusplatform.vo.Level;

public interface ILevelServiceBack {
	/**
	 * 根据等级编号查找等级信息
	 * @param lid 等级编号
	 * @return 等级信息
	 * @throws Exception SQL
	 */
	public Level get(Long id) throws Exception ;
}
