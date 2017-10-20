package cn.mldn.eusplatform.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;

import cn.mldn.eusplatform.vo.Schedule;
import cn.mldn.util.dao.IBaseDAO;

public interface IScheduleDAO extends IBaseDAO<Long,Schedule> {
	/**
	 * 根据审核标记和seid列出个人的调度并分页
	 * @param currentPage 当前页
	 * @param lineSize 每页的行数
	 * @param seid 当前员工
	 * @param ids 审核标记id，以set集合保存
	 * @return 调度安排集合
	 * @throws SQLException SQL
	 */
	public List<Schedule> findAllByAuditAndSeid(Long currentPage, Integer lineSize,String seid,Set<Integer> ids) throws SQLException ;
	/**
	 * 根据审核标记和seid列出所有的调度并模糊分页
	 * @param column 模糊查询的列
	 * @param keyWord 模糊查询的关键字
	 * @param currentPage 当前页
	 * @param lineSize 每页的行数
	 * @param seid 当前员工
	 * @param ids 审核标记id，以set集合保存
	 * @return 调度安排集合
	 * @throws SQLException SQL
	 */
	public List<Schedule> findSplitByAuditAndSeid(String column, String keyWord, Long currentPage, Integer lineSize, String seid, Set<Integer> ids) throws SQLException ;
	/**
	 * 根据审核标记和seid取得该标记下的所有记录数
	 * @param seid 当前员工
	 * @param ids 审核标记id，以set集合保存
	 * @return 记录数
	 * @throws Exception SQL
	 */
	public Long getAllCountSeid(String seid,Set<Integer> ids) throws Exception ;
	/**
	 * 根据审核标记、seid和模糊查询取得所有记录数
	 * @param column 模糊查询的行
	 * @param keyWord 模糊查询的关键字
	 * @param seid 当前员工
	 * @param ids 审核标记id，以set集合保存
	 * @return 记录数
	 * @throws Exception SQL
	 */
	public Long getSplitCountSeid(String column,String keyWord,String seid,Set<Integer> ids) throws Exception ;
	/**
	 * 取得最大的调度id
	 * @return 取得最大的调度id
	 * @throws SQLException SQL
	 */
	public Long getMaxSid() throws SQLException ;
	/**
	 * 修改状态
	 * @param sid 要修改的调度编号
	 * @param audit 要改成的状态编号
	 * @return 修改成功返回true
	 * @throws Exception SQL
	 */
	public boolean doEditAudit(Long sid,Integer audit) throws Exception ;
	/**
	 * 修改调度人数
	 * @param sid 要修改的调度编号
	 * @param ecount 要改成的人数
	 * @return 修改成功返回true
	 * @throws Exception SQL 
	 */
	public boolean doEditEcount(Long sid,Integer ecount) throws Exception ;
	/**
	 * 根据审核标记和sid列出所有的调度安排并分页
	 * @param currentPage 当前页
	 * @param lineSize 每页的行数
	 * @param sid 以set集合保存的sid表示的是该雇员的调度安排
	 * @param ids 审核标记id，以set集合保存
	 * @return 调度安排集合
	 * @throws SQLException SQL
	 */
	public List<Schedule> findAllByAuditAndSid(Long currentPage, Integer lineSize,Set<Long> sid,Set<Integer> ids) throws SQLException ;
	/**
	 * 根据审核标记和sid列出所有的调度安排并模糊分页
	 * @param column 模糊查询的列
	 * @param keyWord 模糊查询的关键字
	 * @param currentPage 当前页
	 * @param lineSize 每页的行数
	 * @param sid 以set集合保存的sid表示的是该雇员的调度安排
	 * @param ids 审核标记id，以set集合保存
	 * @return 调度安排集合
	 * @throws SQLException SQL
	 */
	public List<Schedule> findSplitByAuditAndSid(String column, String keyWord, Long currentPage, Integer lineSize,Set<Long> sid,Set<Integer> ids) throws SQLException ;
	/**
	 * 根据审核标记和sid取得该标记下的所有记录数
	 * @param sid 以set集合保存的sid表示的是该雇员的调度安排
	 * @param ids 审核标记id，以set集合保存
	 * @return 记录数
	 * @throws Exception SQL
	 */
	public Long getAllCount(Set<Long> sid,Set<Integer> ids) throws Exception ;
	/**
	 * 根据审核标记、sid和模糊查询取得所有记录数
	 * @param column 模糊查询的行
	 * @param keyWord 模糊查询的关键字
	 * @param sid 以set集合保存的sid表示的是该雇员的调度安排
	 * @param ids 审核标记id，以set集合保存
	 * @return 记录数
	 * @throws Exception SQL
	 */
	public Long getSplitCount(String column,String keyWord,Set<Long> sid,Set<Integer> ids) throws Exception ;

}
