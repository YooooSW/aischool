package 학생정보관리프로그램;

import java.util.List;

public class Controller {

   private StudentDAO dao;

   public Controller() {
      dao = new StudentDAO();
   }

   // 학생 등록 기능
   public void insert(StudentDTO dto) {
      dao.insert(dto);
   }

   // 학생 전체 조회 기능
   public void selectAll() {
      List<StudentDTO> students = dao.selectAllStudents();
      if (students.isEmpty()) {
         System.out.println("등록된 학생이 없습니다.");
      } else {
         for (StudentDTO student : students) {
            displayStudent(student);
         }
      }
   }

   // 학생 검색 기능
   public void search(String name) {
      StudentDTO student = dao.searchStudentByName(name);
      if (student != null) {
         displayStudent(student);
      }
   }

   // 학생 정보 수정 기능
   public void update(String name, String major) {
      dao.updateStudentMajor(name, major);
   }

   // 학생 정보 삭제 기능
   public void delete(String name) {
      dao.deleteStudentByName(name);
   }

   // 학생 정보를 출력하는 메서드
   private void displayStudent(StudentDTO student) {
      System.out.println("이름: " + student.getName());
      System.out.println("나이: " + student.getAge());
      System.out.println("성별: " + student.getGender());
      System.out.println("전공: " + student.getMajor());
      System.out.println("-------------------------");
   }
}