package cn.mldn.eusplatform.dao;

import java.util.Set;

import cn.mldn.eusplatform.vo.ScheduleEmp;
import cn.mldn.util.dao.IBaseDAO;

public interface IScheduleEmpDAO extends IBaseDAO<Long,ScheduleEmp>{
	/**
	 * 根据调度编号取得所有的雇员编号
	 * @param sid 调度编号
	 * @return 以set集合保存雇员编号
	 * @throws Exception SQL 
	 */
	public Set<String> findEidBySid(Long sid) throws Exception ;
	/**
	 * 删除指定调度编号的所有记录
	 * @return 全部删除成功返回true
	 * @throws Exception SQL
	 */
	public boolean doRemoveBySid(Long sid) throws Exception ;
	/**
	 * 根据调度编号和调度雇员编号删除调度雇员信息
	 * @param sid 调度编号
	 * @param eid 雇员编号
	 * @return 删除成功返回true
	 * @throws Exception SQL
	 */
	public boolean doRemoveBySidAndEid(Long sid,String eid) throws Exception ;
	/**
	 * 根据雇员编号取得所有的调度编号
	 * @param eid 雇员编号
	 * @return 以set集合保存调度编号
	 * @throws Exception SQL 
	 */
	public Set<Long> findSidByEid(String eid) throws Exception ;
}
