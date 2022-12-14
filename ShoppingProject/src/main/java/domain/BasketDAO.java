package domain;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import util.DBConnectionPool;

public class BasketDAO
{
//	private DBConnectionPool connPool;
	private static final String RETRIEVE_STMT = "SELECT * FROM c##shoppingbasket where UserID = ? and Validity = 1";
	private static final String GETID_STMT = "SELECT COUNT(BasketID) FROM c##shoppingbasket";
	private static final String ADD_STMT = "INSERT INTO c##shoppingbasket VALUES(?,?,?,?,1)";
	private static final String DELETE_STMT = "UPDATE c##shoppingbasket SET Validity = 2 WHERE UserID = ? AND BasketID = ?";
	private static final String CLEAN_STMT = "UPDATE c##shoppingbasket SET Validity = 2 WHERE UserID = ?";
	
	ArrayList<Basket> basketRetrieve(int userid) throws SQLException
	{
		ArrayList<Basket> baskets = new ArrayList<Basket>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rset = null;
		try
		{
			conn = DBConnectionPool.getPoolConnection();
			stmt = conn.prepareStatement(RETRIEVE_STMT);
			stmt.setInt(1, userid);
			rset = stmt.executeQuery();
			while (rset.next())
			{
				int BasketID = rset.getInt(1);
				int UserID = rset.getInt(2);
				int ProductID = rset.getInt(3);
				int Numbers = rset.getInt(4);
				int Validity = rset.getInt(5);
				baskets.add(new Basket(BasketID, UserID, ProductID, Numbers, Validity));
			}
			return baskets;
		} catch (SQLException se)
		{
			throw new RuntimeException("A database error occurred. " + se.getMessage());
		} catch (Exception e)
		{
			throw new RuntimeException("Exception: " + e.getMessage());
		} finally
		{
			if (rset != null)
			{
				try
				{
					rset.close();
				} catch (SQLException se)
				{
					se.printStackTrace(System.err);
				}
			}
			if (stmt != null)
			{
				try
				{
					stmt.close();
				} catch (SQLException se)
				{
					se.printStackTrace(System.err);
				}
			}
			if (conn != null)
			{
				try
				{
					conn.close();
				} catch (Exception e)
				{
					e.printStackTrace(System.err);
				}
			}
		}
	}
	
	void basketAdd(int userid, int productid, int numbers)
	{
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rset = null;
		try
		{
			conn = DBConnectionPool.getPoolConnection();
			stmt = conn.prepareStatement(GETID_STMT);
			rset = stmt.executeQuery();
			int ID = -1;
			rset.next();
			ID = rset.getInt("COUNT(BasketID)");
			ID++;
			stmt = conn.prepareStatement(ADD_STMT);
			stmt.setInt(1, ID);
			stmt.setInt(2, userid);
			stmt.setInt(3, productid);
			stmt.setInt(4, numbers);
			stmt.executeQuery();
		} catch (SQLException se)
		{
			throw new RuntimeException("A database error occurred. " + se.getMessage());
		} finally
		{
			if (stmt != null)
			{
				try
				{
					stmt.close();
				} catch (SQLException se)
				{
					se.printStackTrace(System.err);
				}
			}
			if (conn != null)
			{
				try
				{
					conn.close();
				} catch (Exception e)
				{
					e.printStackTrace(System.err);
				}
			}
		}
	}
	
	void basketDelete(int userid, int basketid)
	{
		Connection conn = null;
		PreparedStatement stmt = null;
//		ResultSet rset = null;
		try
		{
			conn = DBConnectionPool.getPoolConnection();
			stmt = conn.prepareStatement(DELETE_STMT);
			stmt.setInt(1, userid);
			stmt.setInt(2, basketid);
			stmt.executeQuery();
		} catch (SQLException se)
		{
			throw new RuntimeException("A database error occurred. " + se.getMessage());
		} finally
		{
			if (stmt != null)
			{
				try
				{
					stmt.close();
				} catch (SQLException se)
				{
					se.printStackTrace(System.err);
				}
			}
			if (conn != null)
			{
				try
				{
					conn.close();
				} catch (Exception e)
				{
					e.printStackTrace(System.err);
				}
			}
		}
	}
	
	void basketClean(int userid)
	{
		Connection conn = null;
		PreparedStatement stmt = null;
//		ResultSet rset = null;
		try
		{
			conn = DBConnectionPool.getPoolConnection();
			stmt = conn.prepareStatement(CLEAN_STMT);
			stmt.setInt(1, userid);
			stmt.executeQuery();
		} catch (SQLException se)
		{
			throw new RuntimeException("A database error occurred. " + se.getMessage());
		} finally
		{
			if (stmt != null)
			{
				try
				{
					stmt.close();
				} catch (SQLException se)
				{
					se.printStackTrace(System.err);
				}
			}
			if (conn != null)
			{
				try
				{
					conn.close();
				} catch (Exception e)
				{
					e.printStackTrace(System.err);
				}
			}
		}
	}
}