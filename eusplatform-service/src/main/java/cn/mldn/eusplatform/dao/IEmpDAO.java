package cn.mldn.eusplatform.dao;

import java.util.List;

import cn.mldn.eusplatform.vo.Emp;
import cn.mldn.util.dao.IBaseDAO;

public interface IEmpDAO extends IBaseDAO<String, Emp> {
	/**
	 * 根据部门编号以及等级小于等于lid的所有雇员信息，不能含有已经在调度中的
	 * @param did 部门编号
	 * @param lid 等级编号
	 * @param sid 调度编号
	 * @return 满足条件的所有雇员
	 * @throws Exception SQL
	 */
	public List<Emp> findByDidAndLid(Long did,Long lid,Long sid) throws Exception ;
}
