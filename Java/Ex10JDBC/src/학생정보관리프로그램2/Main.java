package 학생정보관리프로그램2;

import java.util.Scanner;

public class Main {

   public static void main(String[] args) {

      Scanner sc = new Scanner(System.in);
      Controller conn = new Controller();
      
      while(true) {
         
         System.out.print("[1]학생등록 [2]학생전체조회 [3]학생검색 "
               + "[4]학생정보수정 [5]학생정보삭제 [6]프로그램종료 >> ");
         int select = sc.nextInt();
         
         if(select == 1) {
            // 학생 등록
            System.out.print("이름 : ");
            String name = sc.next();
            System.out.print("나이 : ");
            int age = sc.nextInt();
            System.out.print("성별 : ");
            String gender = sc.next();
            System.out.print("전공 : ");
            String major = sc.next();
            // 입력받은 4개의 데이터를 StudentDTO 자료형으로 바꿔주자
            StudentDTO dto = new StudentDTO(name, age, gender, major);
            // dto를 데이터 베이스에 등록하려면
            // Controller에 있는 insert로 전달
            
            Controller con = new Controller();
            con.insert(dto);
            
            
            
         }else if(select == 2) {
            // 학생 전체 조회
         }else if(select == 3) {
            // 학생 검색
         }else if(select == 4) {
        	// 학생 정보 수정 - update()
				System.out.print("이름을 입력하세요 : ");
				String name = sc.next();
				System.out.print("나이를 입력하세요 : ");
				int age = sc.nextInt();
				System.out.print("수정할 전공을 입력하세요 :");
				String major = sc.next();
				// StudentDTO 자료형으로 바꿔주세요 - 캡슐화
				StudentDTO dto = new StudentDTO(name, age, major);
				Controller con = new Controller();
				con.update(dto);
				// "학생 정보 수정 성공"/"학생 정보 수정 실패"
        	 
         }else if(select == 5) {
        	// 학생 정보 삭제
				System.out.print("이름을 입력하세요 : ");
				String name = sc.next();
				System.out.print("나이를 입력하세요 : ");
				int age = sc.nextInt();
				// StudentDTO 캡슐화를 진행 - dto
				StudentDTO dto = new StudentDTO(name, age);
				Controller con = new Controller();
				con.delete(dto);
				// "학생 정보 삭제 성공"/"학생 정보 삭제 실패"
         }else {
            System.out.println("프로그램 종료!");
            break;
         }
         
      }
      
      
   }

}
