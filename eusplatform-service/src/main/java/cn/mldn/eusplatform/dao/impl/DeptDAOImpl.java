package cn.mldn.eusplatform.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import cn.mldn.eusplatform.dao.IDeptDAO;
import cn.mldn.eusplatform.vo.Dept;
import cn.mldn.util.dao.abs.AbstractDAO;

public class DeptDAOImpl extends AbstractDAO implements IDeptDAO {
	
	@Override
	public String findDnameByDid(Long did) throws SQLException {
		String dname = "" ;
		String sql = "select dname from dept where did = ? " ;
		super.pstmt = super.conn.prepareStatement(sql) ;
		super.pstmt.setLong(1,did) ;
		ResultSet rs = super.pstmt.executeQuery() ;
		if(rs.next()) {
			dname = rs.getString(1) ;
		}
		return dname;
	}
	
	@Override
	public boolean findDnameByOther(Dept vo) throws SQLException {
		String sql = "select dname from dept where dname=? and did != ?" ;
		super.pstmt = super.conn.prepareStatement(sql) ;
		super.pstmt.setString(1, vo.getDname());
		super.pstmt.setLong(2, vo.getDid());
		ResultSet rs = super.pstmt.executeQuery() ;
		while(rs.next()) {
			return true ;
		}
		return false;
	}
	
	@Override
	public Dept findByDname(String dname) throws SQLException {
		Dept dept = null ;
		String sql = "select did,dname,eid,maxnum,currnum from dept where dname=?" ;
		super.pstmt = super.conn.prepareStatement(sql) ;
		super.pstmt.setString(1, dname);
		ResultSet rs = super.pstmt.executeQuery() ;
		if(rs.next()) {
			dept = new Dept() ;
			dept.setDid(rs.getLong(1));
			dept.setDname(rs.getString(2));
			dept.setEid(rs.getString(3));
			dept.setMaxnum(rs.getInt(4));
			dept.setCurrnum(rs.getInt(5));
		}
		return dept ;
	}
	
	@Override
	public boolean doEditDnameAndMaxnumByDid(Dept vo) throws SQLException {
		String sql = "update dept set dname=?,maxnum=? where did=?" ;
		super.pstmt = super.conn.prepareStatement(sql) ;
		super.pstmt.setString(1, vo.getDname());
		super.pstmt.setInt(2, vo.getMaxnum());
		super.pstmt.setLong(3, vo.getDid());
		return super.pstmt.executeUpdate() > 0;
	}
	
	@Override
	public boolean doCreate(Dept vo) throws SQLException {
		String sql = "insert into dept(dname,eid,maxnum,currnum) values (?,?,?,?)" ;
		super.pstmt = super.conn.prepareStatement(sql) ;
		super.pstmt.setString(1, vo.getDname());
		super.pstmt.setString(2, vo.getEid());
		super.pstmt.setInt(3, vo.getMaxnum());
		super.pstmt.setInt(4,vo.getCurrnum()) ;
		return super.pstmt.executeUpdate() > 0 ;
	}

	@Override
	public boolean doEdit(Dept vo) throws SQLException {
		String sql = "update dept set dname=?,eid=?,maxnum=?,currnum=? where did = ? " ;
		super.pstmt = super.conn.prepareStatement(sql) ;
		super.pstmt.setString(1, vo.getDname());
		super.pstmt.setString(2, vo.getEid());
		super.pstmt.setInt(3, vo.getMaxnum());
		super.pstmt.setInt(4, vo.getCurrnum());
		super.pstmt.setLong(5, vo.getDid());
		return super.pstmt.executeUpdate() > 0 ;
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
	public Dept findById(Long id) throws SQLException {
		Dept dept = null ;
		String sql = "select did,dname,eid,maxnum,currnum from dept where did = ? " ;
		super.pstmt = super.conn.prepareStatement(sql) ;
		super.pstmt.setLong(1, id);
		ResultSet rs = super.pstmt.executeQuery() ;
		if(rs.next()) {
			dept = new Dept() ;
			dept.setDid(rs.getLong(1));
			dept.setDname(rs.getString(2));
			dept.setEid(rs.getString(3));
			dept.setMaxnum(rs.getInt(4));
			dept.setCurrnum(rs.getInt(5));
		}
		return dept ;
	}

	@Override
	public List<Dept> findAll() throws SQLException {
		List<Dept> list = new ArrayList<Dept>() ;
		String sql = "select did,dname,eid,maxnum,currnum from dept" ;
		super.pstmt = super.conn.prepareStatement(sql) ;
		ResultSet rs = super.pstmt.executeQuery() ;
		while(rs.next()) {
			Dept dept = new Dept() ;
			dept.setDid(rs.getLong(1));
			dept.setDname(rs.getString(2));
			dept.setEid(rs.getString(3));
			dept.setMaxnum(rs.getInt(4));
			dept.setCurrnum(rs.getInt(5));
			list.add(dept) ;
		}
		return list ;
	}

	@Override
	public List<Dept> findAll(Long currentPage, Integer lineSize) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Dept> findSplit(String column, String keyWord, Long currentPage, Integer lineSize) throws Exception {
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
