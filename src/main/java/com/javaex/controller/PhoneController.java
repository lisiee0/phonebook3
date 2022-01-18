package com.javaex.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.annotation.RequestScope;

import com.javaex.dao.PhoneDao;
import com.javaex.vo.PhoneVo;

@Controller
@RequestMapping(value= "/phone")
public class PhoneController {
	
	@RequestMapping(value= "/writeForm", method= {RequestMethod.GET, RequestMethod.POST})
	public String writeForm() {
		System.out.println("PhoneController/writeForm()");
		
		return "/WEB-INF/views/writeForm.jsp";
	}

	
	@RequestMapping(value= "/write", method= {RequestMethod.GET,RequestMethod.POST})
	public String write(@ModelAttribute PhoneVo vo) {		
		System.out.println("PhoneController/write()");

		PhoneDao pd= new PhoneDao();
		pd.personInsert(vo);
		
		return "redirect:/phone/list";
	}
	
	
	@RequestMapping(value= "/list", method= {RequestMethod.GET, RequestMethod.POST})
	public String list(Model model) {
		System.out.println("PhoneController/list()");
		
		// Dao에서 list 가져오기
		PhoneDao pd= new PhoneDao();
		List<PhoneVo> pList= pd.getPersonList();
		
		// Controller --> DispatcherServlet 데이터 보내기 (model)
		model.addAttribute("pl", pList);

		return "/WEB-INF/views/list.jsp";
	}
	
	@RequestMapping("/delete")
	public String delete(@RequestParam("id") int id) {
		System.out.println("PhoneController/delete()");
		
		PhoneDao pd= new PhoneDao();
		pd.personDelete(id);

		return "redirect:/phone/list";
	}
	
	@RequestMapping("/updateForm")
	public String updateForm(@RequestParam("id") int id, Model model) {
		System.out.println("PhoneController/updateForm");
		
		PhoneDao pd= new PhoneDao();
		PhoneVo vo= pd.getPerson(id);
		
		model.addAttribute("vo", vo);
		
		return "/WEB-INF/views/updateForm.jsp";	
	}
	
	
	@RequestMapping("/update")
	public String update(@RequestParam("personId") int id, @ModelAttribute PhoneVo vo) {
		System.out.println("PhoneController/update");
		System.out.println(id);
		
		PhoneDao pd= new PhoneDao();
		PhoneVo pv= pd.getPerson(id);

		pd.personUpdate(vo);
		
		return "redirect:/phone/list";	
	}
	
	
	
	/*
	@RequestMapping(value= "/write", method= {RequestMethod.GET,RequestMethod.POST})
	public String write(@RequestParam("name") String name, 
						@RequestParam("hp") String hp, 
						@RequestParam("company") String company) {		
		System.out.println("PhoneController/write()");
		
		System.out.println(name);
		System.out.println(hp);
		System.out.println(company);
		
		PhoneVo vo= new PhoneVo(name, hp, company);
		
		PhoneDao pd= new PhoneDao();
		pd.personInsert(vo);
		
		return "";
		
	}
	*/
	
}
