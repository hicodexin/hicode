package com.hicode.oa.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hicode.oa.dao.SchoolDAO;
import com.hicode.oa.service.SchoolService;
import com.hicode.oa.tool.School;

@Service
public class SchoolServiceImpl implements SchoolService {

	@Autowired
	private SchoolDAO schoolDAO;

	@Override
	public School getSchoolByID(Integer id) {
		// TODO Auto-generated method stub
		return schoolDAO.getSchoolByID(id);
	}

	@Override
	public List<School> getSchoolByInfo() {
		// TODO Auto-generated method stub
		return schoolDAO.getSchoolByInfo();
	}

	@Override
	public Integer do_insertSchool(School school) {
		// TODO Auto-generated method stub
		return schoolDAO.do_insertSchool(school);
	}

	@Override
	public Integer do_updateSchool(School school) {
		// TODO Auto-generated method stub
		return schoolDAO.do_updateSchool(school);
	}

}
