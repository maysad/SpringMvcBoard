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
		
		
		
		/* (httpservletrequest)�ɽ��� map.get("request") ������
		  bId ���� dao��ü���� contentview�ؼ� ������ ������ ��� ������� ��� ����
        */		
		
		BDao dao = new BDao();          //dao���� dto�� contenView�� �ѷ��ֱ����ؼ� contentView�޼ҵ� ������ �� �� 
		BDto dto = dao.contentView(bId);
		
		model.addAttribute("content_view", dto); //dto���� content_View��� �̸��� �����ؼ� �����͸� �� ���� �� �ִ�.
		
		
	}
	

}
