package com.sist.web.controller;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sist.web.dao.GoodsDAO;
import com.sist.web.entity.GoodsEntity;
@Controller
@RequestMapping("goods/")
public class GoodsController {
	@Autowired
	private GoodsDAO dao;
	
	@GetMapping("goods_list")
	public String goods_list(Model model)
	{
		List<GoodsEntity> list=dao.goodsListData();
		model.addAttribute("list", list);
		model.addAttribute("main_html", "goods/goods_list");
		return "main";
	}
	
	@GetMapping("goods_detail")
	public String goods_detail(int gno, Model model)
	{
		GoodsEntity vo=dao.findByGno(gno);
		model.addAttribute("vo", vo);
		model.addAttribute("main_html", "goods/goods_detail");
		return "main";	
	}
	
	@GetMapping("goods_find")
	public String goods_find(String name,String page,Model model)
	{
		if(page==null)
			page="1";
		int curpage=Integer.parseInt(page);
		int rowSize=20;
		int start=(rowSize*curpage)-rowSize;
		List<GoodsEntity> list=dao.goodsFindData(name, start);
		   int totalpage=dao.goodsFindTotalPage(name);
		   final int BLOCK=10;
		   int startPage=((curpage-1)/BLOCK*BLOCK)+1;
		   int endPage=((curpage-1)/BLOCK*BLOCK)+BLOCK;
		   if(endPage>totalpage)
			   endPage=totalpage;
		   
		   model.addAttribute("curpage", curpage);
		   model.addAttribute("totalpage", totalpage);
		   model.addAttribute("startPage", startPage);
		   model.addAttribute("endPage", endPage);
		   model.addAttribute("list", list);
		   model.addAttribute("name", name);
		   model.addAttribute("main_html", "goods/goods_find");
		   return "main";
			
	}
	

	
}
