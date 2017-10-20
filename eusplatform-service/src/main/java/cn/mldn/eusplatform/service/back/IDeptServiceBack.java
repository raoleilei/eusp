package cn.mldn.eusplatform.service.back;

import java.util.Map;

import cn.mldn.eusplatform.vo.Dept;

public interface IDeptServiceBack {
	/**
	 * 取得所有的部门列表以及部门的领导姓名
	 * @return 以Map集合的方式返回
	 * key="allDept",value表示所有的部门信息
	 * key="leaderEname",value表示部门的领导姓名
	 * @throws Exception SQL
	 */
	public Map<String,Object> list() throws Exception ;
	/**
	 * 根据部门编号修改部门名称（不能重复）和最大人数
	 * @param vo 部门信息
	 * @return 修改成功返回true,否则返回false
	 * @throws Exception SQL
	 */
	public boolean editDnameAndMaxnumByDid(Dept vo) throws Exception ;
	/**
	 * 增加一个部门(部门名称不能重复)
	 * @param vo 增加的部门
	 * @return 增加成功返回true
	 * @throws Exception SQL
	 */
	public boolean add(Dept vo) throws Exception ;
}
