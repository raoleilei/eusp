package cn.mldn.eusplatform.dao;

import java.sql.SQLException;

import cn.mldn.eusplatform.vo.Dept;
import cn.mldn.util.dao.IBaseDAO;

public interface IDeptDAO extends IBaseDAO<Long, Dept> {
	/**
	 * 根据部门编号修改部门名称和最大人数
	 * @param did 部门信息
	 * @return 修改成功返回true,否则返回false
	 * @throws SQLException SQL
	 */
	public boolean doEditDnameAndMaxnumByDid(Dept vo) throws SQLException;
	/**
	 * 根据部门名称取得部门信息
	 * @param dname 部门名称
	 * @return 部门信息
	 * @throws SQLException SQL
	 */
	public Dept findByDname(String dname) throws SQLException ;
	/**
	 * 根据部门名称查找不含自己的部门名称
	 * @param vo 部门信息
	 * @return 查找到返回true,否则返回false
	 * @throws SQLException SQL 
	 */
	public boolean findDnameByOther(Dept vo) throws SQLException ;
	/**
	 * 根据部门编号查找该部门的名称
	 * @param did 部门编号
	 * @return 部门名称
	 * @throws SQLException SQL
	 */
	public String findDnameByDid(Long did) throws SQLException ;
}
