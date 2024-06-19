import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        // JDBC 드라이버 로딩
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return;
        }

        // 데이터베이스 연결
        String url = "jdbc:mysql://localhost:3306/ETParse?serverTimezone=UTC&characterEncoding=UTF-8"; //  데이터베이스 URL 입력
        String user = "root"; //  데이터베이스 사용자 이름 입력
        String password = "gksthf0601"; //  데이터베이스 비밀번호 입력

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            Service service = new Service();

            // 현재 강의 테이블 데이터 삽입
            service.insertCurrentLecturesTable(conn, "2024subData.txt", "2024-1");

            // 이전 강의 테이블 데이터 삽입
            for (int year = 2018; year <= 2023; year++) {
                for (int semester = 1; semester <= 2; semester++) {
                    String fileName = String.format("everytime%d_%d.txt", year, semester);
                    service.insertEverytimeTable(conn, fileName);
                }
            }


            // SubjectTypeExtractor 호출
            SubjectTypeExtractor.main(new String[] { "수정이 필요한 JSON 파일 경로" });

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
