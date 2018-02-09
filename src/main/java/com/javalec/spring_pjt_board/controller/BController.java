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
	
	BCommand command;  //command 객체 초기화 하지 않고 선언  
	
	@RequestMapping("/list")
	public String list(Model model) {
		
		System.out.println("list() Method 시작");
		
		command = new BListCommand();         
		command.execute(model);
		
		return "list";	
	}
	
	
	
	@RequestMapping("/write_view")        //글을 작성하는 화면 
	public String write_view(Model model) {
		System.out.println("write_view() Method 시작");
		
		
		return "write_view";
	}
	
	
	
	@RequestMapping("/write")           // 글을 쓰는 (실제로 작성)하는 화면 
	public String write(HttpServletRequest request,Model model) {
		
		System.out.println("write() Method 시작");
		model.addAttribute("request", request);
		command = new BWhiteCommand();
		command.execute(model);
		
		
		return "redirect:list";   //작성을 다 하고 list페이지로 이동 redirect로 list페이지로 넘김
	}
	

	@RequestMapping("content_view")        //내용을 보는 페이지   
	public String content_view(HttpServletRequest request, Model model) {
		System.out.println("content_view() Method 시작");
		
		model.addAttribute("request", request);
		command = new BContentCommand();
		command.execute(model);
		
		
		return "content_view";
	}
	
	@RequestMapping(method= RequestMethod.POST, value="/Modify")     //수정하는 페이지
	public String Modify(HttpServletRequest request, Model model) {
		System.out.println("Modify() Method 시작 ");
		
		model.addAttribute("request", request);
		command = new BModifyCommand();
		command.execute(model);
		
		return "rediect:list";
	}
	
	@RequestMapping("/reply_view")   //답글을 보는 페이지
	public String reply_view(HttpServletRequest request, Model model) {
		System.out.println("reply_view() Method 실행");
		
		model.addAttribute("request", request);
		command = new BReplyViewCommand();
		command.execute(model);
		
		return "reply_view";
	}
	
	
	@RequestMapping("/reply")     //답글을 직접 다는 페이지
	public String reply(HttpServletRequest request, Model model) {
		System.out.println("reply() Method 실행");
		
		model.addAttribute("request", request);
		command = new BReplyCommand();
		command.execute(model);
	
		return "redirect:list";
	}
	
	@RequestMapping("/delete")    //삭제 하는 페이지 
	public String delete(HttpServletRequest request, Model model) {
		
		model.addAttribute("request", request);
		command = new BDeleteCommand();
		command.execute(model);
		
		
		return "redirect:list";
	}
	
}
