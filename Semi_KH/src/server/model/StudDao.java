package server.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class StudDao {

/*	
         데이터 접근을 목적하는 객체이다. 
         커넥션 같은 것을 하나만 두고 여러 사용자가 DAO의 인터페이스를 사용하여 필요한 자료에 접근 하도록 하는 것이 DAO의 개념이다.
    DB를 사용해 데이터를 조회하거나 조작하는 기능을 전담하도록 만든 오브젝트를 말한다.

	DAO의 사용 이유는 효율적인 커넥션 관리와 보안성 때문이다.
	
	dao는 SQL과 연동하기 위한 연결 고리 이다. 그리고 작업은 Main에서 해주고 그 학생들의 정보는 Dto가 가지고 있다.

*/	
	
	Connection con = null; // 데이터 베이스에 연동할 변수
	Statement stmt = null; // sql 실행에 필요한 변수
	ResultSet rs = null;   // select 구문을 실행하였을 때 결과 값을 저장할 변수
	String sql = null;
	
	public StudDao() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver"); // sql과 연동할 플러그를 만들어 준다.
			con = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:orcl",
					"hr","hr"
					);
			
			stmt = con.createStatement();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	/////리스트가져오기
	public ArrayList<StudDto> list()
	{
		ArrayList<StudDto> res = new ArrayList<>();

		try {

			sql ="select * from stud";
			rs = stmt.executeQuery(sql);
			
			//birth, tel, email, regdate, grade
			while(rs.next())
			{
				StudDto dto = new StudDto();
				dto.id = rs.getInt("id");
				dto.nnn = rs.getString("name");
				dto.bbb = rs.getDate("birth");
				dto.tel = rs.getString("tel");
				dto.email = rs.getString("email");
			//	dto.regdate = rs.getDate("regdate");
				dto.grade = rs.getInt("grade");
				
				res.add(dto);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			close();
		}

		return res;
	}
	
	
	///삽입
	public void insert(StudDto dto)
	{
		try {

			sql = "insert into stud (id,birth, name, email) "
					+ "values ("+dto.id+
					",'"+dto.getBbbStr()+
					"','"+dto.nnn+
					"','"+dto.email+
					"')";
			System.out.println(sql);
			stmt.executeUpdate(sql);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			close();
		}
	}
	
	
	
	public void insertList()
	{
		try {

			//sql = "insert all "
			    con.setAutoCommit(false);
				sql = "insert into stud (id, name, grade) values (71,'그랑죠',1)";
				System.out.println(stmt.executeUpdate(sql));
				sql = "insert into stud (id, name, grade) values (72,'무적캡틴사우루스',2)";
				System.out.println(stmt.executeUpdate(sql));
				sql = "insert into stud (id, name, grade) values (73,'또봇','abcd')";
				System.out.println(stmt.executeUpdate(sql));
				//"select * from dual";
			
		        //	System.out.println(sql);
		    	// System.out.println(stmt.executeUpdate(sql));
		    	//stmt.executeUpdate(sql);
 
				
				con.commit();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}finally{
			close();
		}
	}
	
	
	
	public void close()
	{
		if(rs!=null) try {rs.close();} catch (SQLException e) {}
		if(stmt!=null) try {stmt.close();} catch (SQLException e) {}
		if(con!=null) try {con.close();} catch (SQLException e) {}
		
	}
	
}
