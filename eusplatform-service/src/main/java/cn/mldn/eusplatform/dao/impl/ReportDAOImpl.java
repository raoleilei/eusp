package cn.mldn.eusplatform.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;

import cn.mldn.eusplatform.dao.IReportDAO;
import cn.mldn.eusplatform.vo.Report;
import cn.mldn.util.dao.abs.AbstractDAO;

public class ReportDAOImpl extends AbstractDAO implements IReportDAO {

	@Override
	public boolean doCreate(Report vo) throws SQLException {
		String sql = "insert into Report(sid,eid,subdate,note) values(?,?,?,?)" ; 
		super.pstmt = super.conn.prepareStatement(sql) ;
		super.pstmt.setLong(1, vo.getSid());
		super.pstmt.setString(2, vo.getEid());
		super.pstmt.setDate(3, new java.sql.Date(vo.getSubdate().getTime()));
		super.pstmt.setString(4, vo.getNote());
		return super.pstmt.executeUpdate() > 0 ;
	}

	@Override
	public boolean doEdit(Report vo) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean doRemove(Long id) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean doRemove(Set<Long> ids) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Report findById(Long id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Report> findAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Report> findAll(Long currentPage, Integer lineSize) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Report> findSplit(String column, String keyWord, Long currentPage, Integer lineSize) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long getAllCount() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long getSplitCount(String column, String keyWord) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String findNoteBySidAndEid(Long sid, String eid) throws Exception {
		String note = "" ;
		String sql = "select note from report where sid = ? and eid = ? " ;
		super.pstmt = super.conn.prepareStatement(sql) ;
		super.pstmt.setLong(1, sid);
		super.pstmt.setString(2, eid);
		ResultSet rs = super.pstmt.executeQuery() ;
		if(rs.next()) {
			note = rs.getString(1) ;
		}
		return note;
	}

	@Override
	public Report findBySidAndEid(Long sid, String eid) throws Exception {
		Report report = null ;
		String sql = "select srid,sid,eid,subdate,note from report where sid = ? and eid = ? " ;
		super.pstmt = super.conn.prepareStatement(sql) ;
		super.pstmt.setLong(1, sid);
		super.pstmt.setString(2, eid);
		ResultSet rs = super.pstmt.executeQuery() ;
		if(rs.next()) {
			report = new Report() ;
			report.setSrid(rs.getLong(1));
			report.setSid(rs.getLong(2));
			report.setEid(rs.getString(3));
			report.setSubdate(rs.getDate(4));
			report.setNote(rs.getString(5));
		}
		return report;
	}

}
