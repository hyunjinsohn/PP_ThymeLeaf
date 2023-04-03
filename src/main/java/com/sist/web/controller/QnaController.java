package com.sist.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sist.web.dao.QnaDAO;
import com.sist.web.entity.QnaEntity;


@Controller
@RequestMapping("qna/")
public class QnaController {
	@Autowired
	private QnaDAO dao;
	
	@GetMapping("qna_list")
	public String qna_list(String page,Model model)
	{
		if(page==null)
			page="1";
		int curpage=Integer.parseInt(page);
		int rowSize=10;
		   int start=(curpage*rowSize)-rowSize;
		   List<QnaEntity> list=dao.qnaListData(start);
		   int totalpage=dao.qnaTotalPage();
		   model.addAttribute("curpage", curpage);
		   model.addAttribute("totalpage", totalpage);
		   model.addAttribute("list", list);
		   model.addAttribute("main_html", "qna/qna_list");
		return "main";
	}
	
	@GetMapping("qna_detail")
	public String qna_detail(int qno, Model model)
	{
		QnaEntity vo=dao.findByQno(qno);				// => hit 수 증가
		vo=dao.findByQno(qno);
		model.addAttribute("vo", vo);
		model.addAttribute("main_html", "qna/qna_detail");
		return "main";
	}
	
	@GetMapping("qna_update")
	public String qna_update(int qno, Model model)
	{
		QnaEntity vo=dao.findByQno(qno);
		model.addAttribute("vo", vo);
		model.addAttribute("main_html", "qna/qna_update");
		return "main";
	}
	
	/*
	 * @GetMapping("board_delete") public String board_delete(int no, Model model) {
	 * model.addAttribute("no", no); model.addAttribute("main_html",
	 * "board/board_delete"); return "main"; }
	 * 
	 * @GetMapping("board_insert") public String board_insert(Model model) {
	 * model.addAttribute("main_html", "board/board_insert"); return "main"; }
	 * 
	 * @PostMapping("board_insert_ok") public String board_insert_ok(BoardEntity vo)
	 * { dao.save(vo); return "redirect:../board/board_list"; }
	 */
}
