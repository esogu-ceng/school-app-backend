package tr.ogu.edu.school.schoolapp.mapper;

import tr.ogu.edu.school.schoolapp.dto.StudentDto;
import tr.ogu.edu.school.schoolapp.model.Student;

public class StudentMapper {
	private StudentMapper() {
	}

	public static StudentDto toStudentDto(Student student) {
		StudentDto studentDto = new StudentDto();
		studentDto.setId(student.getId());
		studentDto.setName(student.getName());
		studentDto.setSurname(student.getSurname());
		studentDto.setGrade(student.getGrade());
		return studentDto;
	}

	public static Student fromStudentDto(StudentDto studentDto) {
		Student student = new Student();
		student.setId(studentDto.getId());
		student.setName(studentDto.getName());
		student.setSurname(studentDto.getSurname());
		student.setGrade(studentDto.getGrade());
		return student;
	}
}
