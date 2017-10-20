package cn.mldn.eusplatform.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import cn.mldn.eusplatform.dao.IScheduleEmpDAO;
import cn.mldn.eusplatform.vo.ScheduleEmp;
import cn.mldn.util.dao.abs.AbstractDAO;

public class ScheduleEmpDAOImpl extends AbstractDAO implements IScheduleEmpDAO {

	@Override
	public Set<Long> findSidByEid(String eid) throws Exception {
		Set<Long> set = new HashSet<Long>() ;
		String sql = "select sid from schedule_emp where eid = ?" ;
		super.pstmt = super.conn.prepareStatement(sql) ;
		super.pstmt.setString(1, eid);
		ResultSet rs = super.pstmt.executeQuery() ;
		while(rs.next()) {
			set.add(rs.getLong(1)) ;
		}
		return set;
	}
	
	@Override
	public boolean doRemoveBySidAndEid(Long sid, String eid) throws Exception {
		String sql = "delete from schedule_emp where sid=? and eid=? " ;
		super.pstmt = super.conn.prepareStatement(sql) ;
		super.pstmt.setLong(1, sid);
		super.pstmt.setString(2, eid);
		return super.pstmt.executeUpdate() > 0;
	}
	
	@Override
	public boolean doRemoveBySid(Long sid) throws Exception {
		String sql = "delete from schedule_emp where sid = ? " ;
		super.pstmt = super.conn.prepareStatement(sql) ;
		super.pstmt.setLong(1, sid);
		return super.pstmt.executeUpdate() > 0 ;
	}
	
	@Override
	public Set<String> findEidBySid(Long sid) throws Exception {
		Set<String> set = new HashSet<String>() ;
		String sql = "select eid from schedule_emp where sid = ?" ;
		super.pstmt = super.conn.prepareStatement(sql) ;
		super.pstmt.setLong(1, sid);
		ResultSet rs = super.pstmt.executeQuery() ;
		while(rs.next()) {
			set.add(rs.getString(1)) ;
		}
		return set;
	}
	
	@Override
	public boolean doCreate(ScheduleEmp vo) throws SQLException {
		String sql = "insert into schedule_emp(sid,eid) values (?,?) " ;
		super.pstmt = super.conn.prepareStatement(sql) ;
		super.pstmt.setLong(1, vo.getSid());
		super.pstmt.setString(2, vo.getEid());
		return super.pstmt.executeUpdate() > 0 ;
	}

	@Override
	public boolean doEdit(ScheduleEmp vo) throws SQLException {
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
	public ScheduleEmp findById(Long id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ScheduleEmp> findAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ScheduleEmp> findAll(Long currentPage, Integer lineSize) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ScheduleEmp> findSplit(String column, String keyWord, Long currentPage, Integer lineSize)
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
	
}
