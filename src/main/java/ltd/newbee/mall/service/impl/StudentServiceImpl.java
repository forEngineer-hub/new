package ltd.newbee.mall.service.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ltd.newbee.mall.dao.StudentMapper;
import ltd.newbee.mall.entity.Student;
import ltd.newbee.mall.service.StudentService;
@Service
public class StudentServiceImpl implements StudentService{

	@Autowired
    private StudentMapper studentMapper;
	
	@Override
	public ArrayList<Student> getStudentsListByName(String name) {
		return studentMapper.getStudentListByName(name);
	}

	@Override
	public int insertStudent(Student s) {
		int id = studentMapper.geMaxStudentID();
		s.setStudentId(id + 1);
		return studentMapper.insertStudent(s);
	}
	
}
