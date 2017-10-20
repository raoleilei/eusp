package cn.mldn.eusplatform.vo;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class Schedule implements Serializable {
	private Long sid ;//审核表id 自动增长
	private String seid ;//提出此调度任务的员工编号
	private String aeid ;// 审核者ID
	private Long iid ;//item表ID 类型
	private String title ;// 申请标题
	private Date sdate ;//为任务的执行日期；
	private Date subdate ;//为当前调度的提交日期；
	private Integer audit ;//审核标记：0：待提交，1：待审核，2：未通过，3：进行中，4：待命中，5：带报告，6：已完成
	private String note ;//任务描述
	private Date auddate ;//调度完成时间
	private String anote ;//审核者的审核评语，该评语属于叠加显示
	private Integer ecount ;//参与本次调度的员工信息 人数
	public Long getSid() {
		return sid;
	}
	public void setSid(Long sid) {
		this.sid = sid;
		
	}
	public String getSeid() {
		return seid;
	}
	public void setSeid(String seid) {
		this.seid = seid;
	}
	public String getAeid() {
		return aeid;
	}
	public void setAeid(String aeid) {
		this.aeid = aeid;
	}
	public Long getIid() {
		return iid;
	}
	public void setIid(Long iid) {
		this.iid = iid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Date getSdate() {
		return sdate;
	}
	public void setSdate(Date sdate) {
		this.sdate = sdate;
	}
	public Date getSubdate() {
		return subdate;
	}
	public void setSubdate(Date subdate) {
		this.subdate = subdate;
	}
	public Integer getAudit() {
		return audit;
	}
	public void setAudit(Integer audit) {
		this.audit = audit;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public Date getAuddate() {
		return auddate;
	}
	public void setAuddate(Date auddate) {
		this.auddate = auddate;
	}
	public String getAnote() {
		return anote;
	}
	public void setAnote(String anote) {
		this.anote = anote;
	}
	public Integer getEcount() {
		return ecount;
	}
	public void setEcount(Integer ecount) {
		this.ecount = ecount;
	}
}
