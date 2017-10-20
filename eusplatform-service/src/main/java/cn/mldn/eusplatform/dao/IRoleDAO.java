package cn.mldn.eusplatform.dao;

import java.sql.SQLException;
import java.util.Set;

import cn.mldn.eusplatform.vo.Role;
import cn.mldn.util.dao.IBaseDAO;

public interface IRoleDAO extends IBaseDAO<Long,Role> {
	/**
	 * 根据部门编号查询该部门的所有角色信息
	 * @param did 部门编号
	 * @return 该部门的所有角色数据
	 * @throws SQLException JDBC
	 */
	public Set<String> findAllByDept(Long did) throws SQLException ;
}
