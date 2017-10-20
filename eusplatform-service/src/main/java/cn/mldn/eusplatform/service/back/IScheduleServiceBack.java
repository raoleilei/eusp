package cn.mldn.eusplatform.service.back;

import java.util.List;
import java.util.Map;

import cn.mldn.eusplatform.vo.Item;
import cn.mldn.eusplatform.vo.Schedule;

public interface IScheduleServiceBack {
	/**
	 * 增加调度前的申请类型动态加载
	 * @return 以List集合的形式返回
	 * @throws Exception SQL
	 */
	public List<Item> addPre() throws Exception ;
	/**
	 * 增加调度
	 * @param vo 要增加的调度信息
	 * @return 增加成功返回true,否则返回false
	 * @throws Exception SQL
	 */
	public boolean add(Schedule vo) throws Exception ;
	/**
	 * 列出所有自己的调度信息
	 * @param currentPage 当前页
	 * @param lineSize 每页的行数
	 * @param column 模糊查询的行
	 * @param keyWord 模糊查询的关键字
	 * @return 返回map
	 * 1、key = allSchedule,value = 所有的自己的调度信息
	 * 2、key = allRecorders,value = 所有的记录数
	 * @throws Exception SQL
	 */
	public Map<String,Object> listSelf(long currentPage,int lineSize,String column,String keyWord) throws Exception ;
	/**
	 * 修改状态
	 * @param sid 要修改的调度编号
	 * @param audit 要改成的状态编号
	 * @return 修改成功返回true
	 * @throws Exception SQL
	 */
	public boolean editAudit(long sid,int audit) throws Exception ;
	/**
	 * 根据调度编号取得调度信息，同时所有的调度的雇员信息
	 * @param sid 调度编号
	 * @return 以map形式返回 
	 * 1、key = schedule,value表示的是调度信息
	 * 2、key = allScheduleEmp,value表示所有的调度人员
	 * 3、key = itemTitle,value表示的是调度类型
	 * 4、key = dname,value表示的是雇员对应的部门
	 * 5、key = levelTitle,value表示的是雇员对应的等级
	 * @throws Exception SQL
	 */
	public Map<String,Object> scheduleShow(long sid) throws Exception ;
	/**
	 * 修改调度前的回显操作
	 * @param sid 调度编号
	 * @return 以map形式返回
	 * 1、key = schedule,value表示的是调度信息
	 * 2、key = allItem,value表示的是所有的任务类型
	 * @throws Exception SQL
	 */
	public Map<String,Object> editPre(long sid) throws Exception ;
	/**
	 * 修改调度
	 * @param vo 调度的信息
	 * @return 修改成功返回true
	 * @throws Exception SQL
	 */
	public boolean edit(Schedule vo) throws Exception ;
	/**
	 * 删除调度，同时删除调度雇员表
	 * @param sid 调度编号
	 * @return 删除成功返回true
	 * @throws Exception SQL 
	 */
	public boolean delete(long sid) throws Exception ;
	/**
	 * 根据调度编号查询出所有的调度人员以及查询出所有的部门信息
	 * @param sid 调度编号
	 * @return 以map形式返回
	 * 1、key = allScheduleEmp, value表示的是该编号下的所有的调度人员
	 * 2、key = schedule, value表示的是调度信息
	 * 3、key = allDept, value表示的是所有部门
	 * 4、key = title, value表示的是调度人员的等级名称
	 * 5、key = dname, value表示的是调度人员的部门名称
	 * @throws Exception SQL 
	 */
	public Map<String,Object> getScheduleEmpBySidAndAllDept(long sid) throws Exception ;
	/**
	 * 删除指定调度编号和雇员编号的调度雇员信息,同时将调度记录中的人数-1
	 * @param sid 调度编号
	 * @param eid 雇员编号
	 * @return 删除成功返回true
	 * @throws Exception SQL
	 */
	public boolean empDelete(long sid,String eid) throws Exception ;
	/**
	 * 根据部门编号和等级编号取得所有的雇员信息，不能含有已经在调度中的
	 * @param did 部门编号
	 * @param did 调度编号
	 * @return 以map形式返回
	 * 1、key = allEmp, value表示的是对应部门编号和等级编号的所有雇员
	 * 3、key = dname, value表示的是雇员对应的部门名称
	 * 2、key = title, value表示的是雇员对应的等级名称
	 * @throws Exception SQL
	 */
	public Map<String,Object> getEmpByDnameAndTitle(long did,long sid) throws Exception ;
	/**
	 * 增加一个调度雇员，要修改schedule中的ecount，同时在schedule_emp中增加一条记录
	 * @param sid 调度编号
	 * @param eid 调度的雇员编号
	 * @return 增加成功返回true
	 * @throws Exception SQL
	 */
	public boolean addScheduleEmp(long sid,String eid) throws Exception ;
	/**
	 * 查询出全部为待审核的调度申请并进行分页
	 * @param currentPage 当前页
	 * @param lineSize 每页的行数
	 * @param column 进行模糊查询的列
	 * @param keyWord 进行模糊查询的关键字
	 * @return 以map形式返回
	 * 1、key = allSchedule,value = 所有的待审核的调度申请
	 * 2、key = allRecorders,value = 所有的记录数
	 * @throws Exception SQL
	 */
	public Map<String,Object> findAllByAuditPrepare(long currentPage,int lineSize,String column,String keyWord) throws Exception ;
	/**
	 * 修改调度信息：flag=0||sdate<subdate，表示未通过，flag=1&&sdate==subdate，表示进行中，flag=1&&sdate>subdate，表示待命中
	 * @param sid 调度编号
	 * @param flag 标记
	 * @param aNote 审核备注
	 * @return 修改成功返回true
	 * @throws Exception SQL
	 */
	public boolean editByAuditPrepare(Long sid,int flag,String aNote) throws Exception ;
	/**
	 * 列出所有调度信息
	 * @param currentPage 当前页
	 * @param lineSize 每页的行数
	 * @param column 模糊查询的行
	 * @param keyWord 模糊查询的关键字
	 * @return 返回map
	 * 1、key = allSchedule,value = 所有的调度信息
	 * 2、key = allRecorders,value = 所有的记录数
	 * @throws Exception SQL
	 */
	public Map<String,Object> list(long currentPage,int lineSize,String column,String keyWord) throws Exception ;
	/**
	 * 个人待处理任务，只显示audit=3&&audit=4
	 * @param currentPage 当前页
	 * @param lineSize 每页的行数
	 * @param column 模糊查询的行
	 * @param keyWord 模糊查询的关键字
	 * @return 返回map
	 * 1、key = allSchedule,value = 调度信息
	 * 2、key = allRecorders,value = 记录数 
	 * @throws Exception SQL
	 */
	public Map<String,Object> EmpListPrepare(long currentPage,int lineSize,String column,String keyWord) throws Exception ;
	/**
	 * 个人历史任务的列表显示
	 * @param currentPage 当前页
	 * @param lineSize 每页的行数
	 * @param column 进行模糊查询的列
	 * @param keyWord 进行模糊查询的关键字
	 * @return 以map形式返回
	 * 1、key = allSchedule,value = 调度信息
	 * 2、key = allRecorders,value = 记录数 
	 * @throws Exception SQL
	 */
	public Map<String,Object> EmpList(long currentPage,int lineSize,String column,String keyWord) throws Exception ;
}
