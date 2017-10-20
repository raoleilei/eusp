package cn.mldn.eusplatform.dao;

import cn.mldn.eusplatform.vo.Report;
import cn.mldn.util.dao.IBaseDAO;

public interface IReportDAO extends IBaseDAO<Long,Report>{
	/**
	 * 根据sid和eid取得报告的内容
	 * @param sid 调度编号
	 * @param eid 雇员编号
	 * @return 报告内容
	 * @throws Exception SQL 
	 */
	public String findNoteBySidAndEid(Long sid,String eid) throws Exception ;
	/**
	 * 根据sid和eid取得报告
	 * @param sid 调度编号
	 * @param eid 雇员编号
	 * @return 报告
	 * @throws Exception SQL 
	 */
	public Report findBySidAndEid(Long sid,String eid) throws Exception ;
}
