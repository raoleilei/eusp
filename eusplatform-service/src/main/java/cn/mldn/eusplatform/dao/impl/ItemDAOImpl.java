package cn.mldn.eusplatform.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import cn.mldn.eusplatform.dao.IItemDAO;
import cn.mldn.eusplatform.vo.Item;
import cn.mldn.util.dao.abs.AbstractDAO;

public class ItemDAOImpl extends AbstractDAO implements IItemDAO{

	@Override
	public boolean doCreate(Item vo) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean doEdit(Item vo) throws SQLException {
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
	public Item findById(Long id) throws SQLException {
		Item item = null ;
		String sql = "select iid,title from item where iid = ?" ;
		super.pstmt = super.conn.prepareStatement(sql) ;
		super.pstmt.setLong(1, id);
		ResultSet rs = super.pstmt.executeQuery() ;
		if(rs.next()) {
			item = new Item() ;
			item.setIid(rs.getLong(1));
			item.setTitle(rs.getString(2));
		}
		return item;
	}

	@Override
	public List<Item> findAll() throws SQLException {
		List<Item> list = new ArrayList<Item>() ;
		String sql = "select iid,title from item" ;
		super.pstmt = super.conn.prepareStatement(sql) ;
		ResultSet rs = super.pstmt.executeQuery() ;
		while(rs.next()) {
			Item item = new Item() ;
			item.setIid(rs.getLong(1));
			item.setTitle(rs.getString(2));
			list.add(item) ;
		}
		return list ;
	}

	@Override
	public List<Item> findAll(Long currentPage, Integer lineSize) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Item> findSplit(String column, String keyWord, Long currentPage, Integer lineSize) throws Exception {
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
