package server;

import java.util.ArrayList;

import jdbc_p.model.StudDao;
import jdbc_p.model.StudDto;

public class StudDataMain {

	public static void main(String[] args) {
		// DataMain에서 생성자를 통해 여기서 수정 및 변경 , 추가를 한다

		new User_info().insertList();
		ArrayList<UserDao> list = new UserDao().list();
		
		for (UserDto dto : list) {
			System.out.println(dto);
		}
		
	/*	StudDto dto = new StudDto();
		dto.setId(11);
		dto.setNnn("김태희");
		dto.setEmail("eee@eee.com");
		dto.setBbbStr("1988-08-08");
		new StudDao().insert(dto);
	*/
	}

}
