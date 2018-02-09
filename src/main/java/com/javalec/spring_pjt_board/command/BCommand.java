package com.javalec.spring_pjt_board.command;

import org.springframework.ui.Model;

public interface BCommand {

	public void execute(Model model);
	
/*	  실행을 하라고 excute 메소드를 만들고 
	  Model : 컨트롤러에서 뷰로 전환할 때 데이터를 가지고 있는 객체, 
	          컨트롤러가 뷰로 model 객체를 넘겨 뷰에서 model 객체의 데이터 이용 가능하게끔 
	          ex) model.getAtrribute("id",abcde)*/


}
