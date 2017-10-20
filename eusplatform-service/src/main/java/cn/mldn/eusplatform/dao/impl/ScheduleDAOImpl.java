package cn.mldn.eusplatform.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import cn.mldn.eusplatform.dao.IScheduleDAO;
import cn.mldn.eusplatform.vo.Schedule;
import cn.mldn.util.dao.abs.AbstractDAO;

public class ScheduleDAOImpl extends AbstractDAO implements IScheduleDAO {
	
	@Override
	public boolean doEditEcount(Long sid, Integer ecount) throws Exception {
		String sql = "update schedule set ecount=? where sid=? " ;
		super.pstmt = super.conn.prepareStatement(sql) ;
		super.pstmt.setInt(1, ecount);
		super.pstmt.setLong(2, sid);
		return super.pstmt.executeUpdate() > 0 ;
	}
	
	@Override
	public boolean doEditAudit(Long sid, Integer audit) throws Exception {
		String sql = "update schedule set audit=? where sid=? " ;
		super.pstmt = super.conn.prepareStatement(sql) ;
		super.pstmt.setInt(1, audit);
		super.pstmt.setLong(2, sid);
		return super.pstmt.executeUpdate() > 0 ;
	}
	
	@Override
	public boolean doCreate(Schedule vo) throws SQLException {
		String sql = "insert into schedule(seid,aeid,iid,title,sdate,subdate,audit,note,auddate,anote,ecount) values (?,?,?,?,?,?,?,?,?,?,?)" ;
		super.pstmt = super.conn.prepareStatement(sql) ;
		super.pstmt.setString(1, vo.getSeid());
		super.pstmt.setString(2, vo.getAeid());
		super.pstmt.setLong(3, vo.getIid());
		super.pstmt.setString(4, vo.getTitle());
		super.pstmt.setDate(5, new java.sql.Date(vo.getSdate().getTime()));
		super.pstmt.setDate(6,new java.sql.Date(vo.getSubdate().getTime())) ;
		super.pstmt.setInt(7, vo.getAudit());
		super.pstmt.setString(8, vo.getNote());
		super.pstmt.setDate(9, null);
		super.pstmt.setString(10, vo.getAnote());
		super.pstmt.setInt(11, vo.getEcount());
		return super.pstmt.executeUpdate() > 0 ;
	}

	@Override
	public boolean doEdit(Schedule vo) throws SQLException {
		String sql = "update schedule set seid=?,aeid=?,iid=?,title=?,sdate=?,subdate=?,audit=?,note=?,auddate=?,anote=?,ecount=? where sid=?" ;
		super.pstmt = super.conn.prepareStatement(sql) ;
		super.pstmt.setString(1, vo.getSeid());
		super.pstmt.setString(2, vo.getAeid());
		super.pstmt.setLong(3, vo.getIid());
		super.pstmt.setString(4, vo.getTitle());
		super.pstmt.setDate(5, new java.sql.Date(vo.getSdate().getTime()));
		super.pstmt.setDate(6,new java.sql.Date(vo.getSubdate().getTime())) ;
		super.pstmt.setInt(7, vo.getAudit());
		super.pstmt.setString(8, vo.getNote());
		if(vo.getAuddate() == null) {
			super.pstmt.setDate(9, null);
		}else {
			super.pstmt.setDate(9, new java.sql.Date(vo.getAuddate().getTime()));
		}
		super.pstmt.setString(10, vo.getAnote());
		super.pstmt.setInt(11, vo.getEcount());
		super.pstmt.setLong(12, vo.getSid());
		return super.pstmt.executeUpdate() > 0 ;
	}

	@Override
	public boolean doRemove(Long id) throws SQLException {
		String sql = "delete from schedule where sid = ? " ;
		super.pstmt = super.conn.prepareStatement(sql) ;
		super.pstmt.setLong(1, id);
		return super.pstmt.executeUpdate() > 0;
	}

	@Override
	public boolean doRemove(Set<Long> ids) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Schedule findById(Long id) throws SQLException {
		Schedule schedule = null ;
		String sql = "select sid,seid,aeid,iid,title,sdate,subdate,audit,note,auddate,anote,ecount from schedule where sid = ?" ;
		super.pstmt = super.conn.prepareStatement(sql) ;
		super.pstmt.setLong(1, id);
		ResultSet rs = super.pstmt.executeQuery() ;
		if(rs.next()) {
			schedule = new Schedule() ;
			schedule.setSid(rs.getLong(1));
			schedule.setSeid(rs.getString(2));
			schedule.setAeid(rs.getString(3));
			schedule.setIid(rs.getLong(4));
			schedule.setTitle(rs.getString(5));
			schedule.setSdate(rs.getDate(6));
			schedule.setSubdate(rs.getDate(7));
			schedule.setAudit(rs.getInt(8));
			schedule.setNote(rs.getString(9));
			schedule.setAuddate(rs.getDate(10));
			schedule.setAnote(rs.getString(11));
			schedule.setEcount(rs.getInt(12));
		}
		return schedule ;
	}

	@Override
	public List<Schedule> findAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Schedule> findAll(Long currentPage, Integer lineSize) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Schedule> findSplit(String column, String keyWord, Long currentPage, Integer lineSize)
			throws Exception {
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
	public List<Schedule> findAllByAuditAndSeid(Long currentPage, Integer lineSize, String seid, Set<Integer> ids)
			throws SQLException {
		List<Schedule> all = new ArrayList<>();
		StringBuffer sb = new StringBuffer("select sid,seid,aeid,iid,title,sdate,subdate,audit,note,auddate,anote,ecount from schedule where 1=1 ") ;
		if(seid != null) {
			sb.append(" and seid='").append(seid).append("'") ;
		}
		if(ids != null && ids.size() != 0) {
			Iterator<Integer> iterIds = ids.iterator() ;
			sb.append(" and audit in (");
			while(iterIds.hasNext()) {
				sb.append(iterIds.next()).append(",") ;
			}
			sb.delete(sb.length()-1, sb.length()).append(")") ;
		}
		sb.append(" order by audit asc") ;
		sb.append(" limit ?,?") ;
		super.pstmt = super.conn.prepareStatement(sb.toString());
		super.pstmt.setLong(1, (currentPage - 1) * lineSize);
		super.pstmt.setLong(2, lineSize);
		ResultSet rs = super.pstmt.executeQuery() ;
		while (rs.next()) {
			Schedule vo = new Schedule();
			vo.setSid(rs.getLong(1));
			vo.setSeid(rs.getString(2));
			vo.setAeid(rs.getString(3));
			vo.setIid(rs.getLong(4));
			vo.setTitle(rs.getString(5));
			vo.setSdate(rs.getDate(6));
			vo.setSubdate(rs.getDate(7));
			vo.setAudit(rs.getInt(8));
			vo.setNote(rs.getString(9));
			vo.setAuddate(rs.getDate(10));
			vo.setAnote(rs.getString(11));
			vo.setEcount(rs.getInt(12));
			all.add(vo);
		}
		return all;
	}

	@Override
	public List<Schedule> findSplitByAuditAndSeid(String column, String keyWord, Long currentPage, Integer lineSize,
			String seid, Set<Integer> ids) throws SQLException {
		List<Schedule> all = new ArrayList<>();
		StringBuffer sb = new StringBuffer("select sid,seid,aeid,iid,title,sdate,subdate,audit,note,auddate,anote,ecount from schedule WHERE " + column + " LIKE ? ") ;
		if(seid != null) {
			sb.append(" and seid='").append(seid).append("'") ;
		}
		if(ids != null && ids.size() != 0) {
			Iterator<Integer> iterIds = ids.iterator() ;
			sb.append(" and audit in (");
			while(iterIds.hasNext()) {
				sb.append(iterIds.next()).append(",") ;
			}
			sb.delete(sb.length()-1, sb.length()).append(")") ;
		}
		sb.append(" order by audit asc") ;
		sb.append(" limit ?,?") ;
		super.pstmt = super.conn.prepareStatement(sb.toString());
		super.pstmt.setString(1, "%" + keyWord + "%");
		super.pstmt.setLong(2, (currentPage - 1) * lineSize);
		super.pstmt.setLong(3, lineSize);
		ResultSet rs = super.pstmt.executeQuery() ;
		while (rs.next()) {
			Schedule vo = new Schedule();
			vo.setSid(rs.getLong(1));
			vo.setSeid(rs.getString(2));
			vo.setAeid(rs.getString(3));
			vo.setIid(rs.getLong(4));
			vo.setTitle(rs.getString(5));
			vo.setSdate(rs.getDate(6));
			vo.setSubdate(rs.getDate(7));
			vo.setAudit(rs.getInt(8));
			vo.setNote(rs.getString(9));
			vo.setAuddate(rs.getDate(10));
			vo.setAnote(rs.getString(11));
			vo.setEcount(rs.getInt(12));
			all.add(vo);
		}
		return all;
	}

	@Override
	public Long getAllCountSeid(String seid, Set<Integer> ids) throws Exception {
		StringBuffer sb = new StringBuffer("select count(*) from schedule where 1=1 ") ;
		if(seid != null) {
			sb.append(" and seid='").append(seid).append("'") ;
		}
		if(ids != null && ids.size() != 0) {
			Iterator<Integer> iterIds = ids.iterator() ;
			sb.append(" and audit in (");
			while(iterIds.hasNext()) {
				sb.append(iterIds.next()).append(",") ;
			}
			sb.delete(sb.length()-1, sb.length()).append(")") ;
		}
		sb.append(" order by audit asc") ;
		super.pstmt = super.conn.prepareStatement(sb.toString());
		ResultSet rs = super.pstmt.executeQuery();
		if(rs.next()){
			return rs.getLong(1);
		}
		return 0L;
	}

	@Override
	public Long getSplitCountSeid(String column, String keyWord, String seid, Set<Integer> ids) throws Exception {
		StringBuffer sb = new StringBuffer("select count(*) from schedule where ? like ? ") ;
		if(seid != null) {
			sb.append(" and seid='").append(seid).append("'") ;
		}
		if(ids != null && ids.size() != 0) {
			Iterator<Integer> iterIds = ids.iterator() ;
			sb.append(" and audit in (");
			while(iterIds.hasNext()) {
				sb.append(iterIds.next()).append(",") ;
			}
			sb.delete(sb.length()-1, sb.length()).append(")") ;
		}
		sb.append(" order by audit asc") ;
		super.pstmt = super.conn.prepareStatement(sb.toString());
		super.pstmt.setString(1, column);
		super.pstmt.setString(2, "%"+ keyWord +"%");
		ResultSet rs = super.pstmt.executeQuery();
		if(rs.next()){
			return rs.getLong(1);
		}
		return 0L;
	}
	@Override
	public Long getMaxSid() throws SQLException {
		Long sid = -1l ;
		String sql = "select max(sid) from schedule" ;
		super.pstmt = super.conn.prepareStatement(sql) ;
		ResultSet rs = super.pstmt.executeQuery() ;
		if(rs.next()) {
			sid = rs.getLong(1) ;
		}
		return sid ;
	}
	@Override
	public List<Schedule> findAllByAuditAndSid(Long currentPage, Integer lineSize, Set<Long> sids, Set<Integer> ids)
			throws SQLException {
		List<Schedule> all = new ArrayList<>();
		StringBuffer sb = new StringBuffer("select sid,seid,aeid,iid,title,sdate,subdate,audit,note,auddate,anote,ecount from schedule where 1=1 ") ;
		if(sids != null && sids.size() != 0) {
			Iterator<Long> iterSid = sids.iterator() ;
			sb.append(" and sid in (");
			while(iterSid.hasNext()) {
				sb.append(iterSid.next()).append(",") ;
			}
			sb.delete(sb.length()-1,sb.length()).append(")") ;
		}
		if(ids != null && ids.size() != 0) {
			Iterator<Integer> iterIds = ids.iterator() ;
			sb.append(" and audit in (");
			while(iterIds.hasNext()) {
				sb.append(iterIds.next()).append(",") ;
			}
			sb.delete(sb.length()-1, sb.length()).append(")") ;
		}
		sb.append(" order by audit asc") ;
		sb.append(" limit ?,?") ;
		super.pstmt = super.conn.prepareStatement(sb.toString());
		super.pstmt.setLong(1, (currentPage - 1) * lineSize);
		super.pstmt.setLong(2, lineSize);
		ResultSet rs = super.pstmt.executeQuery() ;
		while (rs.next()) {
			Schedule vo = new Schedule();
			vo.setSid(rs.getLong(1));
			vo.setSeid(rs.getString(2));
			vo.setAeid(rs.getString(3));
			vo.setIid(rs.getLong(4));
			vo.setTitle(rs.getString(5));
			vo.setSdate(rs.getDate(6));
			vo.setSubdate(rs.getDate(7));
			vo.setAudit(rs.getInt(8));
			vo.setNote(rs.getString(9));
			vo.setAuddate(rs.getDate(10));
			vo.setAnote(rs.getString(11));
			vo.setEcount(rs.getInt(12));
			all.add(vo);
		}
		return all;
	}

	@Override
	public List<Schedule> findSplitByAuditAndSid(String column, String keyWord, Long currentPage, Integer lineSize,
			Set<Long> sids, Set<Integer> ids) throws SQLException {
		List<Schedule> all = new ArrayList<>();
		StringBuffer sb = new StringBuffer("select sid,seid,aeid,iid,title,sdate,subdate,audit,note,auddate,anote,ecount from schedule WHERE " + column + " LIKE ? ") ;
		if(sids != null && sids.size() != 0) {
			Iterator<Long> iterSid = sids.iterator() ;
			sb.append(" and sid in (");
			while(iterSid.hasNext()) {
				sb.append(iterSid.next()).append(",") ;
			}
			sb.delete(sb.length()-1,sb.length()).append(")") ;
		}
		if(ids != null && ids.size() != 0) {
			Iterator<Integer> iterIds = ids.iterator() ;
			sb.append(" and audit in (");
			while(iterIds.hasNext()) {
				sb.append(iterIds.next()).append(",") ;
			}
			sb.delete(sb.length()-1, sb.length()).append(")") ;
		}
		sb.append(" order by audit asc") ;
		sb.append(" limit ?,?") ;
		super.pstmt = super.conn.prepareStatement(sb.toString());
		super.pstmt.setString(1, "%" + keyWord + "%");
		super.pstmt.setLong(2, (currentPage - 1) * lineSize);
		super.pstmt.setLong(3, lineSize);
		ResultSet rs = super.pstmt.executeQuery() ;
		while (rs.next()) {
			Schedule vo = new Schedule();
			vo.setSid(rs.getLong(1));
			vo.setSeid(rs.getString(2));
			vo.setAeid(rs.getString(3));
			vo.setIid(rs.getLong(4));
			vo.setTitle(rs.getString(5));
			vo.setSdate(rs.getDate(6));
			vo.setSubdate(rs.getDate(7));
			vo.setAudit(rs.getInt(8));
			vo.setNote(rs.getString(9));
			vo.setAuddate(rs.getDate(10));
			vo.setAnote(rs.getString(11));
			vo.setEcount(rs.getInt(12));
			all.add(vo);
		}
		return all;
	}

	@Override
	public Long getAllCount(Set<Long> sids, Set<Integer> ids) throws Exception {
		StringBuffer sb = new StringBuffer("select count(*) from schedule where 1=1 ") ;
		if(sids != null && sids.size() != 0) {
			Iterator<Long> iterSid = sids.iterator() ;
			sb.append(" and sid in (");
			while(iterSid.hasNext()) {
				sb.append(iterSid.next()).append(",") ;
			}
			sb.delete(sb.length()-1,sb.length()).append(")") ;
		}
		if(ids != null && ids.size() != 0) {
			Iterator<Integer> iterIds = ids.iterator() ;
			sb.append(" and audit in (");
			while(iterIds.hasNext()) {
				sb.append(iterIds.next()).append(",") ;
			}
			sb.delete(sb.length()-1, sb.length()).append(")") ;
		}
		sb.append(" order by audit asc") ;
		super.pstmt = super.conn.prepareStatement(sb.toString());
		ResultSet rs = super.pstmt.executeQuery();
		if(rs.next()){
			return rs.getLong(1);
		}
		return 0L;
	}

	@Override
	public Long getSplitCount(String column, String keyWord, Set<Long> sids, Set<Integer> ids) throws Exception {
		StringBuffer sb = new StringBuffer("select count(*) from schedule where ? like ? ") ;
		if(sids != null && sids.size() != 0) {
			Iterator<Long> iterSid = sids.iterator() ;
			sb.append(" and sid in (");
			while(iterSid.hasNext()) {
				sb.append(iterSid.next()).append(",") ;
			}
			sb.delete(sb.length()-1,sb.length()).append(")") ;
		}
		if(ids != null && ids.size() != 0) {
			Iterator<Integer> iterIds = ids.iterator() ;
			sb.append(" and audit in (");
			while(iterIds.hasNext()) {
				sb.append(iterIds.next()).append(",") ;
			}
			sb.delete(sb.length()-1, sb.length()).append(")") ;
		}
		sb.append(" order by audit asc") ;
		super.pstmt = super.conn.prepareStatement(sb.toString());
		super.pstmt.setString(1, column);
		super.pstmt.setString(2, "%"+ keyWord +"%");
		ResultSet rs = super.pstmt.executeQuery();
		if(rs.next()){
			return rs.getLong(1);
		}
		return 0L;
	}
	
}
