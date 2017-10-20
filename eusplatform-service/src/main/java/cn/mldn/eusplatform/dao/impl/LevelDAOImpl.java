package cn.mldn.eusplatform.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import cn.mldn.eusplatform.dao.ILevelDAO;
import cn.mldn.eusplatform.vo.Level;
import cn.mldn.util.dao.abs.AbstractDAO;

public class LevelDAOImpl extends AbstractDAO implements ILevelDAO {

	@Override
	public String findTitleByLid(Long lid) throws SQLException {
		String title = "" ;
		String sql = "select title from level where lid = ? " ;
		super.pstmt = super.conn.prepareStatement(sql) ;
		super.pstmt.setLong(1,lid) ;
		ResultSet rs = super.pstmt.executeQuery() ;
		if(rs.next()) {
			title = rs.getString(1) ;
		}
		return title;
	}
	
	@Override
	public boolean doCreate(Level vo) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean doEdit(Level vo) throws SQLException {
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
	public Level findById(Long id) throws SQLException {
		Level level = null ;
		String sql = "select lid,title,losal,hisal from level where lid = ? " ;
		super.pstmt = super.conn.prepareStatement(sql) ;
		super.pstmt.setLong(1, id);
		ResultSet rs = super.pstmt.executeQuery() ;
		if(rs.next()) {
			level = new Level() ;
			level.setLid(rs.getLong(1));
			level.setTitle(rs.getString(2));
			level.setLosal(rs.getDouble(3));
			level.setHisal(rs.getDouble(4));
		}
		return level ;
	}

	@Override
	public List<Level> findAll() throws SQLException {
		List<Level> list = new ArrayList<Level>() ;
		String sql = "select lid,title,losal,hisal from level" ;
		super.pstmt = super.conn.prepareStatement(sql) ;
		ResultSet rs = super.pstmt.executeQuery() ;
		while(rs.next()) {
			Level level = new Level() ;
			level.setLid(rs.getLong(1));
			level.setTitle(rs.getString(2));
			level.setLosal(rs.getDouble(3));
			level.setHisal(rs.getDouble(4));
			list.add(level) ;
		}
		return list ;
	}

	@Override
	public List<Level> findAll(Long currentPage, Integer lineSize) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Level> findSplit(String column, String keyWord, Long currentPage, Integer lineSize) throws Exception {
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
