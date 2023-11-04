package tr.ogu.edu.school.schoolapp.mapper;

import tr.ogu.edu.school.schoolapp.dto.StudentDto;
import tr.ogu.edu.school.schoolapp.model.Student;

public class StudentMapper {
	private StudentMapper() {
	}

	public static StudentDto toStudentDto(Student student) {
		StudentDto studentDto = new StudentDto();
		return studentDto;
	}

	public static Student fromStudentDto(StudentDto studentDto) {
		Student student = new Student();
		return student;
	}
}
