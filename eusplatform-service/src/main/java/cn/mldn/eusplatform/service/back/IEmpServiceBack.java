package cn.mldn.eusplatform.service.back;

import java.util.Map;

import cn.mldn.eusplatform.vo.Emp;

public interface IEmpServiceBack {
	/**
	 * 实现用户的登录处理操作，该操作要执行如下的几步：
	 * 1、调用IMemberDAO.findById()方法根据用户名查找用户的信息，随后进行密码比对，如果密码正确则表示登录成功；
	 * 2、当登录成功之后应该根据用户编号获得用户对应的角色与权限数据信息；
	 * @param emp 包含有用户登录名（mid）与密码（password）两个重要的信息
	 * @return 返回的内容包含有如下数据：<br>
	 * 1、key = emp、value = 登录成功后的用户信息，如果没有返回雇员对象则表示登录失败；
	 * 2、key = allRoles、value = 用户对应的所有角色，Set集合；
	 * 3、key = allActions、value = 用户对应的所有权限，Set集合；
	 * @throws Exception 抛出状态的处理异常
	 */
	public Map<String, Object> login(Emp emp) throws Exception;
	/**
	 * 根据雇员编号列出雇员信息，并根据等级编号和部门编号取得对应的等级名称和部门名称
	 * @param eid 雇员编号
	 * @return 返回的内容包含有如下数据：<br>
	 * 1、key = emp、value = 雇员信息
	 * 2、key = title、value = 等级名称
	 * 3、key = dname、value = 部门名称
	 * @throws Exception SQL
	 */
	public Map<String, Object> getDnameAndTitle(String eid) throws Exception ;
	/**
	 * 取得所有的部门以及所有的等级
	 * @return 返回的内容包含有如下数据：<br>
	 * 1、key = allDept、value = 所有的部门信息
	 * 2、key = allLevel、value = 所有的等级信息
	 * @throws Exception SQL
	 */
	public Map<String, Object> getAllDeptAndAllLevel() throws Exception ;
	/**
	 * 进行雇员的增加操作
	 * 1、不允许增加总监和总裁
	 * 2、判断该部门人数是否已满，没满将当前人数+1，满了则增加失败
	 * 3、判断是否是经理，是经理则进行替换，将原来的经理降一个等级，工资为普通员工的最高工资
	 * @param vo 雇员信息
	 * @return 增加成功返回true
	 * @throws Exception SQL 
	 */
	public boolean add(Emp vo) throws Exception ;
	/**
	 * 模糊查询雇员信息以及该雇员所对应的等级名称和部门名称
	 * @param currentPage 当前页
	 * @param lineSize 每页的行数
	 * @param column 模糊查询的列
	 * @param keyWord 模糊查询的关键字
	 * @return 返回的内容包含有如下数据：<br>
	 * 1、key = "allEmp",value = 所有的雇员信息
	 * 2、key = "allTitle",value = 所有雇员所对应的等级名称
	 * 3、key = "allDname",value = 所有雇员所对应的部门名称
	 * 4、key = "allRecorders",value = 所有的记录数
	 * @throws Exception SQL
	 */
	public Map<String,Object> list(long currentPage,int lineSize,String column,String keyWord) throws Exception ;
	/**
	 * 编辑雇员前的回显操作
	 * @param eid 雇员编号
	 * @return 
	 * 1、key = allDept、value = 所有的部门信息
	 * 2、key = allLevel、value = 所有的等级信息
	 * 3、key = emp、value = 雇员信息
	 * @throws Exception SQL
	 */
	public Map<String,Object> editPre(String eid) throws Exception ;
	/**
	 * 进行雇员的修改操作
	 * 1、不允许增加总监和总裁,但允许修改本身是总监和总裁的信息
	 * 2、判断要修改的部门人数是否已满并且调度的是否是同一个部门
	 * 3、判断修改的是否是经理，是经理则进行替换，将原来的经理降一个等级，工资为普通员工的最高工资
	 * 4、判断之前员工是否是经理，如果是则该部门的经理为空
	 * 5、修改的部门人数  + 1 ，之前的部门人数 - 1
	 * @param vo 雇员信息
	 * @return 增加成功返回true
	 * @throws Exception SQL 
	 */
	public boolean edit(Emp vo) throws Exception ;
}
