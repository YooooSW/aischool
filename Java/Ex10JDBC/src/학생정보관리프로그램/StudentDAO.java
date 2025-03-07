package 학생정보관리프로그램;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {
   
	String id = "hr";
	String pw = "12345";
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	
   
   // 데이터베이스 연결 메서드
   private Connection getConnection() throws SQLException, ClassNotFoundException {
      Class.forName("oracle.jdbc.driver.OracleDriver");
      return DriverManager.getConnection(url, id, pw);
   }
   
   // 학생 등록 메서드
   public void insert(StudentDTO dto) {
      String sql = "INSERT INTO STUDENT VALUES (?, ?, ?, ?)";
      try (Connection con = getConnection(); 
           PreparedStatement psmt = con.prepareStatement(sql)) {
         psmt.setString(1, dto.getName());
         psmt.setInt(2, dto.getAge());
         psmt.setString(3, dto.getGender());
         psmt.setString(4, dto.getMajor());
         int result = psmt.executeUpdate();
         if(result > 0) {
            System.out.println("학생 등록 성공");
         } else {
            System.out.println("학생 등록 실패");
         }
      } catch (ClassNotFoundException | SQLException e) {
         e.printStackTrace();
         System.out.println("데이터베이스 작업 중 오류 발생");
      }
   }
   
   // 학생 전체 조회 메서드
   public List<StudentDTO> selectAllStudents() {
      List<StudentDTO> studentList = new ArrayList<>();
      String sql = "SELECT * FROM STUDENT";
      try (Connection con = getConnection(); 
           PreparedStatement psmt = con.prepareStatement(sql);
           ResultSet rs = psmt.executeQuery()) {
         while (rs.next()) {
            StudentDTO student = new StudentDTO(
               rs.getString("NAME"),
               rs.getInt("AGE"),
               rs.getString("GENDER"),
               rs.getString("MAJOR")
            );
            studentList.add(student);
         }
      } catch (ClassNotFoundException | SQLException e) {
         e.printStackTrace();
         System.out.println("데이터베이스 조회 중 오류가 발생했습니다.");
      }
      return studentList;
   }
   
   // 학생 검색 메서드
   public StudentDTO searchStudentByName(String name) {
      String sql = "SELECT * FROM STUDENT WHERE NAME = ?";
      try (Connection con = getConnection(); 
           PreparedStatement psmt = con.prepareStatement(sql)) {
         psmt.setString(1, name);
         try (ResultSet rs = psmt.executeQuery()) {
            if (rs.next()) {
               return new StudentDTO(
                  rs.getString("NAME"),
                  rs.getInt("AGE"),
                  rs.getString("GENDER"),
                  rs.getString("MAJOR")
               );
            } else {
               System.out.println("해당 이름의 학생을 찾을 수 없습니다.");
            }
         }
      } catch (ClassNotFoundException | SQLException e) {
         e.printStackTrace();
         System.out.println("데이터베이스 조회 중 오류가 발생했습니다.");
      }
      return null;
   }
   
   // 학생 정보 수정 메서드
   public void updateStudentMajor(String name, String major) {
      String sql = "UPDATE STUDENT SET MAJOR = ? WHERE NAME = ?";
      try (Connection conn = getConnection(); 
           PreparedStatement psmt = conn.prepareStatement(sql)) {
         psmt.setString(1, major);
         psmt.setString(2, name);
         int result = psmt.executeUpdate();
         if (result > 0) {
            System.out.println("전공 수정 성공");
         } else {
            System.out.println("전공 수정 실패");
         }
      } catch (ClassNotFoundException | SQLException e) {
         e.printStackTrace();
      }
   }
   
   // 학생 정보 삭제 메서드
   public void deleteStudentByName(String name) {
      String sql = "DELETE FROM STUDENT WHERE NAME = ?";
      try (Connection con = getConnection(); 
           PreparedStatement psmt = con.prepareStatement(sql)) {
         psmt.setString(1, name);
         int result = psmt.executeUpdate();
         if (result > 0) {
            System.out.println("학생 정보 삭제 성공");
         } else {
            System.out.println("학생 정보 삭제 실패");
         }
      } catch (ClassNotFoundException | SQLException e) {
         e.printStackTrace();
      }
   }
}