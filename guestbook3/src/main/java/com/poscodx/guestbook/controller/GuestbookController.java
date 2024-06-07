package com.poscodx.guestbook.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.poscodx.guestbook.repository.GuestbookRepositoryWithJdbcContext;
import com.poscodx.guestbook.repository.GuestbookRepositoryWithJdbcTemplate;
import com.poscodx.guestbook.repository.GuestbookRepositoryWithRawJdbc;
import com.poscodx.guestbook.service.GuestBookService;
import com.poscodx.guestbook.vo.GuestbookVo;

@Controller
public class GuestbookController {
//	@Autowired
//	private GuestbookRepositoryWithJdbcContext guestbookRepository;
	
	@Autowired
	private GuestBookService guestBookService;
	
	@RequestMapping("/")
	public String index(Model model) {
		List<GuestbookVo> list = guestBookService.getContentsList();
		
		model.addAttribute("list", list);
		return "index";
	}
	
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public String insert(GuestbookVo vo) {
//		guestbookRepository.insert(vo);
		guestBookService.addContents(vo);
		return "redirect:/";
	}
	
	@RequestMapping("/delete/{no}")
	public String delete(@PathVariable("no") Long no, Model model) {
		model.addAttribute("no", no);
		
		return "delete";
	}
	
	@RequestMapping("/delete")
	public String delete(Long no, String password) {
//		guestbookRepository.deleteByNoAndPassword(no, password);
		guestBookService.deleteContents(no, password);
		return "redirect:/";
	}
}
