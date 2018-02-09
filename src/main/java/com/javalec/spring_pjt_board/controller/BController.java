package com.javalec.spring_pjt_board.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.javalec.spring_pjt_board.command.BCommand;
import com.javalec.spring_pjt_board.command.BContentCommand;
import com.javalec.spring_pjt_board.command.BDeleteCommand;
import com.javalec.spring_pjt_board.command.BListCommand;
import com.javalec.spring_pjt_board.command.BModifyCommand;
import com.javalec.spring_pjt_board.command.BReplyCommand;
import com.javalec.spring_pjt_board.command.BReplyViewCommand;
import com.javalec.spring_pjt_board.command.BWhiteCommand;

@Controller
public class BController {
	
	BCommand command;  //command ��ü �ʱ�ȭ ���� �ʰ� ����  
	
	@RequestMapping("/list")
	public String list(Model model) {
		
		System.out.println("list() Method ����");
		
		command = new BListCommand();         
		command.execute(model);
		
		return "list";	
	}
	
	
	
	@RequestMapping("/write_view")        //���� �ۼ��ϴ� ȭ�� 
	public String write_view(Model model) {
		System.out.println("write_view() Method ����");
		
		
		return "write_view";
	}
	
	
	
	@RequestMapping("/write")           // ���� ���� (������ �ۼ�)�ϴ� ȭ�� 
	public String write(HttpServletRequest request,Model model) {
		
		System.out.println("write() Method ����");
		model.addAttribute("request", request);
		command = new BWhiteCommand();
		command.execute(model);
		
		
		return "redirect:list";   //�ۼ��� �� �ϰ� list�������� �̵� redirect�� list�������� �ѱ�
	}
	

	@RequestMapping("content_view")        //������ ���� ������   
	public String content_view(HttpServletRequest request, Model model) {
		System.out.println("content_view() Method ����");
		
		model.addAttribute("request", request);
		command = new BContentCommand();
		command.execute(model);
		
		
		return "content_view";
	}
	
	@RequestMapping(method= RequestMethod.POST, value="/Modify")     //�����ϴ� ������
	public String Modify(HttpServletRequest request, Model model) {
		System.out.println("Modify() Method ���� ");
		
		model.addAttribute("request", request);
		command = new BModifyCommand();
		command.execute(model);
		
		return "rediect:list";
	}
	
	@RequestMapping("/reply_view")   //����� ���� ������
	public String reply_view(HttpServletRequest request, Model model) {
		System.out.println("reply_view() Method ����");
		
		model.addAttribute("request", request);
		command = new BReplyViewCommand();
		command.execute(model);
		
		return "reply_view";
	}
	
	
	@RequestMapping("/reply")     //����� ���� �ٴ� ������
	public String reply(HttpServletRequest request, Model model) {
		System.out.println("reply() Method ����");
		
		model.addAttribute("request", request);
		command = new BReplyCommand();
		command.execute(model);
	
		return "redirect:list";
	}
	
	@RequestMapping("/delete")    //���� �ϴ� ������ 
	public String delete(HttpServletRequest request, Model model) {
		
		model.addAttribute("request", request);
		command = new BDeleteCommand();
		command.execute(model);
		
		
		return "redirect:list";
	}
	
}
