package com.poscodx.guestbook.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.poscodx.guestbook.repository.GuestbookRepositoryWithJdbcContext;
import com.poscodx.guestbook.repository.GuestbookRepositoryWithRawJdbc;
import com.poscodx.guestbook.vo.GuestbookVo;

@Controller
public class GuestbookController {
	@Autowired
	private GuestbookRepositoryWithRawJdbc guestbookRepository1;

	@Autowired
	private GuestbookRepositoryWithJdbcContext guestbookRepository2;
	
//	@Autowired
//	private GuestbookRepositoryWithJdbcTemplate guestbookRepository3;
	
	@RequestMapping("/")
	public String index(Model model) {
		List<GuestbookVo> list = guestbookRepository1.findAll();
		model.addAttribute("list", list);
		return "index";
	}
	
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public String insert(GuestbookVo vo) {
		guestbookRepository2.insert(vo);
		return "redirect:/";
	}
	
	@RequestMapping("/delete/{no}")
	public String delete(@PathVariable("no") Long no, Model model) {
		model.addAttribute("no", no);
		
		return "delete";
	}
	
	@RequestMapping("/delete")
	public String delete(Long no, String password) {
		guestbookRepository2.deleteByNoAndPassword(no, password);
		return "redirect:/";
	}
}
