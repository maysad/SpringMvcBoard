package com.javalec.spring_pjt_board.command;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.javalec.spring_pjt_board.dao.BDao;
import com.javalec.spring_pjt_board.dto.BDto;

public class BContentCommand implements BCommand{

	@Override
	public void execute(Model model) {
		
		Map <String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		String bId = request.getParameter("bId");
		
		
		
		/* (httpservletrequest)케스팅 map.get("request") 해준후
		  bId 에서 dao객체보고 contentview해서 가져온 내용을 모두 보여줘라 라는 내용
        */		
		
		BDao dao = new BDao();          //dao에서 dto로 contenView를 뿌려주기위해서 contentView메소드 생성을 한 후 
		BDto dto = dao.contentView(bId);
		
		model.addAttribute("content_view", dto); //dto에서 content_View라는 이름을 참조해서 데이터를 다 얻어올 수 있다.
		
		
	}
	

}
