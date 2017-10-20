package cn.mldn.eusplatform.service.back;

import java.util.Map;

import cn.mldn.eusplatform.vo.Report;

public interface IReportServiceBack {
	/**
	 * 进行个人报告的提交,要取得本次调度的所有人,如果只有你没有提交，则在提交后将audit变成6
	 * @param vo 个人报告
	 * @return 增加成功返回true
	 * @throws Exception SQL
	 */
	public boolean add(Report vo) throws Exception ;
	/**
	 * 根据sid取得本次调度的基本信息，以及调度人员的基本信息
	 * @param sid 调度编号 
	 * @return 以map形式返回
	 * 1、key = schedule,value表示的是调度的信息
	 * 2、key = itemTitle,value表示的任务类型名称
	 * 3、key = allEmp,value表示是的本次调度的所有人员信息
	 * 4、key = dname,value表示的是调度人员对应的部门名称
	 * 5、key = levelTitle,value表示的是调度人员对应的等级名称
	 * 6、key = empReport,value表示的是调度人员对应的报告信息
	 * 7、key = count,value表示的是已报告的人数
	 * 8、key = seidReport,value表示的是组织者报告
	 * @throws Exception SQL
	 */
	public Map<String,Object> scheduleReport(Long sid) throws Exception ;
}
