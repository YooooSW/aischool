package 학생정보관리프로그램2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentDAO {
	Connection conn = null;
	PreparedStatement psmt = null;
	ResultSet rs = null;

	// 데이터베이스 연결하는 기능 - dbOpen()
	public void dbOpen() {
		// 1. 동적로딩
		// 2. 연결권한
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// 2. 데이터 베이스 연결 권한 확인
			String id = "hr";
			String pw = "12345";
			String url = "jdbc:oracle:thin:@localhost:1521:xe";

			try {
				conn = DriverManager.getConnection(url, id, pw);
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("연결 실패");
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("동적 로딩 실패");
		}
	}

	// 자원 반납 - dbClose()
	public void dbClose() {
		try {
			// rs는 사용된적이 없으면 반납하지 않도록
			// insert, update, delete메서드에서 실행시켜도 반납 x
			if (psmt != null)
				psmt.close();
			if (conn != null)
				conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// dto를 데이터베이스에 저장하는 - 학생등록
	public int insert(StudentDTO dto) {

		// 사용자가 입력했었던
		// name, age, gender, major - dto에 들어있는 상태

		Connection conn = null;
		PreparedStatement psmt = null;

		int cnt = 0;
		// jdbc 코드
		// 1. 동적 로딩
		try {
			dbOpen();

			String sql = "INSERT INTO STUDENT VALUES(?,?,?,?)";
			psmt = conn.prepareStatement(sql);
			// - ? 인자 채워주는 작업
			psmt.setString(1, dto.getName());
			psmt.setInt(2, dto.getAge());
			psmt.setString(3, dto.getGender());
			psmt.setString(4, dto.getMajor());
			// dto에 사용자 입력했었던 데이터가 담겨있으므로

			cnt = psmt.executeUpdate();
			// cnt가 데이터베이스랑 연결하고나서 받아온 결과값
			// cnt를 View한테 어떤 값인지를 알려줘야겠다.

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("sql문 실행 오류");
		} finally {
			dbClose();
		}
		return cnt;

	}

	
	public int update(StudentDTO dto) {
		dbOpen();
		
		int cnt = 0;
		// jdbc 코드
		// 1. 동적 로딩
		try {
			
			 // SQLExcetion - 연결 권한
			
	         String sql = "UPDATE STUDNET SET MAJOR = ? WHERE NAME = ? AND AGE = ?";
	         psmt = conn.prepareStatement(sql);
	         // - ? 인자 채워주는 작업
	         psmt.setString(1, dto.getName());
	         psmt.setInt(2, dto.getAge());
	         psmt.setString(3, dto.getMajor());
	         
	         // dto에 사용자 입력했었던 데이터가 담겨있으므로
	         cnt = psmt.executeUpdate();
	         // cnt가 데이터베이스랑 연결하고나서 받아온 결과값
	         // cnt를 View한테 어떤 값인지를 알려줘야겠다.	         
		}catch (SQLException e) {
			e.printStackTrace();
			System.out.println("sql문 실행 오류");
		}finally {
			dbClose();
		}
		return cnt;
		
		
		
	}
	
	public int delete(StudentDTO dto) {
		dbOpen();
		
		int cnt = 0;
		// jdbc 코드
		// 1. 동적 로딩
		try {
			
			 // SQLExcetion - 연결 권한
			
	         String sql = "DELETE FROM STUDENT WHERE NAME = ? AND AGE = ?";
	         psmt = conn.prepareStatement(sql);
	         // - ? 인자 채워주는 작업
	         psmt.setString(1, dto.getName());
	         psmt.setInt(2, dto.getAge());
	  
	         
	         // dto에 사용자 입력했었던 데이터가 담겨있으므로
	         cnt = psmt.executeUpdate();
	         // cnt가 데이터베이스랑 연결하고나서 받아온 결과값
	         // cnt를 View한테 어떤 값인지를 알려줘야겠다.	         
		}catch (SQLException e) {
			e.printStackTrace();
			System.out.println("sql문 실행 오류");
		}finally {
			dbClose();
		}
		return cnt;
	
	
	
	}
}
