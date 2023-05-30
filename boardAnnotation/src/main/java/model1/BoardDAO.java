package model1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BoardDAO {
	
	Connection conn = null;
	
	public BoardDAO() {
		// TODO Auto-generated constructor stub
		
		String url = "jdbc:mariadb://localhost:3306/board";
		String user = "board";
		String password ="123456";
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			this.conn = DriverManager.getConnection(url, user, password);
			
		
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("에러1 " + e.getMessage());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("에러2 " + e.getMessage());
		}
		
		System.out.println("db연결 성공");
		
	}
	
	public ArrayList<BoardTO> boardList(){
		ArrayList<BoardTO> lists = new ArrayList();
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String sql="select seq, subject, writer, mail, password, content, hit, wip, wdate from board1";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				BoardTO to = new BoardTO();
				
				to.setSeq(rs.getString("seq"));
				to.setSubject(rs.getString("subject"));
				to.setWriter(rs.getString("writer"));
				to.setMail(rs.getString("mail"));
				to.setPassword(rs.getString("password"));
				to.setContent(rs.getString("content"));
				to.setHit(rs.getString("hit"));
				to.setWip(rs.getString("wip"));
				to.setWdate(rs.getString("wdate"));
				
				lists.add(to);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("에러1 " + e.getMessage());
		}finally {
			if(rs!=null)try {rs.close();} catch(SQLException e) {}
			if(conn!=null)try {conn.close();} catch(SQLException e) {}
			if(pstmt!=null)try {pstmt.close();} catch(SQLException e) {}
		}
		
		return lists;
	}

	
	public int WriteOk(BoardTO to) {
		int flag = 1;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			//seq subject writer mail password content hit wip now()
			String sql = "INSERT INTO board1 (subject, writer, mail, password, content, hit, wip, wdate) VALUES (?, ?, ?, ?, ?, 0, ?, NOW())";
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, to.getSubject());
			pstmt.setString(2, to.getWriter());
			pstmt.setString(3, to.getMail());
			pstmt.setString(4, to.getPassword());
			pstmt.setString(5, to.getContent());
			pstmt.setString(6, to.getWip());
			
			int result = pstmt.executeUpdate();
			
			if (result == 1) {
				flag = 0;
			} else if (result == 0) {
				flag = 1;
				System.out.println("실패");
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("에러1 " + e.getMessage());
		}finally {
			if(rs!=null)try {rs.close();} catch(SQLException e) {}
			if(conn!=null)try {conn.close();} catch(SQLException e) {}
			if(pstmt!=null)try {pstmt.close();} catch(SQLException e) {}
		}
		
		
		return flag;
	}
	
	public BoardTO view(BoardTO to) {
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			String sql = "update board1 set hit=hit+1 where seq=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, to.getSeq());
			
			pstmt.executeUpdate();
			
			sql = "select subject, writer, mail, wip, wdate, hit, content from board1 where seq=?";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, to.getSeq());
			
			rs = pstmt.executeQuery();
			
			if (rs.next( ) ) {
				
				to.setSubject(rs.getString("subject"));
				to.setWriter(rs.getString("writer"));
				to.setMail(rs.getString("mail"));
				to.setWip(rs.getString("wip"));
				to.setWdate(rs.getString("wdate"));
				to.setHit(rs.getString("hit"));
				to.setContent(rs.getString("content"));
				//emot = "emot" + rs.getString( "emot" ) + ".png");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if(rs!=null)try {rs.close();} catch(SQLException e) {}
			if(conn!=null)try {conn.close();} catch(SQLException e) {}
			if(pstmt!=null)try {pstmt.close();} catch(SQLException e) {}
		}
		
		
		return to;
	}
	
	public BoardTO delete(BoardTO to) {

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "select subject, writer from board1 where seq=?";
			pstmt = conn.prepareStatement( sql );
			pstmt.setString( 1, to.getSeq() );
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				
				to.setSubject(rs.getString("subject"));
				to.setWriter(rs.getString("writer"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(rs!=null)try {rs.close();} catch(SQLException e) {}
			if(conn!=null)try {conn.close();} catch(SQLException e) {}
			if(pstmt!=null)try {pstmt.close();} catch(SQLException e) {}
		}
		
		return to;
	}
	
	public int delete_ok(BoardTO to) {
		int flag =2;
		
		PreparedStatement pstmt = null;

		try {
			String sql = "delete from board1 where seq=? and password=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, to.getSeq());
			pstmt.setString(2, to.getPassword());
			
			int result = pstmt.executeUpdate();
			
			if(result==1){
				flag=0;
			}else if(result==0){
				flag=1;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(conn!=null)try {conn.close();} catch(SQLException e) {}
			if(pstmt!=null)try {pstmt.close();} catch(SQLException e) {}
		}
		return flag;
	}
	
	
	public BoardTO modify(BoardTO to) {
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "select subject, writer, mail, content from board1 where seq=?";
			pstmt = conn.prepareStatement( sql );
			pstmt.setString( 1, to.getSeq() );
			
			rs = pstmt.executeQuery();
			
			if( rs.next() ) {
				to.setSubject( rs.getString( "subject" ) );
				to.setWriter( rs.getString( "writer" ) );
				to.setMail( rs.getString( "mail" ) );
				to.setContent( rs.getString( "content" ) );
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(rs!=null)try {rs.close();} catch(SQLException e) {}
			if(conn!=null)try {conn.close();} catch(SQLException e) {}
			if(pstmt!=null)try {pstmt.close();} catch(SQLException e) {}
		}
		
		
		return to;
	}
	
	public int modify_ok(BoardTO to) {
		int flag = 2;
		PreparedStatement pstmt = null;
		
		try {
			String sql = "update board1 set subject=?, mail=?, content=? where seq=? and password=?";
			pstmt = conn.prepareStatement( sql );
			pstmt.setString( 1, to.getSubject() );
			pstmt.setString( 2, to.getMail() );
			pstmt.setString( 3, to.getContent() );
			pstmt.setString( 4, to.getSeq() );
			pstmt.setString( 5, to.getPassword() );
			
			int result = pstmt.executeUpdate();
			if( result == 1 ) {
				flag = 0;
			} else if( result == 0 ) {
				flag = 1;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(conn!=null)try {conn.close();} catch(SQLException e) {}
			if(pstmt!=null)try {pstmt.close();} catch(SQLException e) {}
		}
		return flag;
	}
	
}
