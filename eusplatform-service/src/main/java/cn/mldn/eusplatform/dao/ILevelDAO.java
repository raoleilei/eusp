package cn.mldn.eusplatform.dao;

import java.sql.SQLException;

import cn.mldn.eusplatform.vo.Level;
import cn.mldn.util.dao.IBaseDAO;

public interface ILevelDAO extends IBaseDAO<Long,Level>{
	/**
	 * 根据等级编号查找该等级的名称
	 * @param lid 等级编号
	 * @return 等级名称
	 * @throws SQLException SQL
	 */
	public String findTitleByLid(Long lid) throws SQLException ;
}
