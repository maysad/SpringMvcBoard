package com.javalec.spring_pjt_board.command;

import org.springframework.ui.Model;

public interface BCommand {

	public void execute(Model model);
	
/*	  ������ �϶�� excute �޼ҵ带 ����� 
	  Model : ��Ʈ�ѷ����� ��� ��ȯ�� �� �����͸� ������ �ִ� ��ü, 
	          ��Ʈ�ѷ��� ��� model ��ü�� �Ѱ� �信�� model ��ü�� ������ �̿� �����ϰԲ� 
	          ex) model.getAtrribute("id",abcde)*/


}
