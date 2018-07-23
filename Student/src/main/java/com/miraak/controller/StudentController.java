package com.miraak.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.miraak.entity.Student;
import com.miraak.entity.StudentExample;
import com.miraak.entity.StudentExample.Criteria;
import com.miraak.mapper.StudentMapper;

@Controller
public class StudentController {

	@Autowired
	StudentMapper studentMapper;

	@RequestMapping("getAll")
	public String gatAll(Model model,Student s) {

		StudentExample example = new StudentExample();

		Criteria criteria = example.createCriteria();

		if(s.getName()!="" && s.getName()!=null) {

			criteria.andNameLike("%"+s.getName()+"%");

		}
		/*if(s.getBirthday()!="" && s.getBirthday()!=null) {

			criteria.andBirthdayBetween(null, null);

		}*/
		List<Student> list = studentMapper.selectByExample(example );

		model.addAttribute("list", list);

		return "list";

	}



}
