package tr.ogu.edu.school.schoolapp.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import tr.ogu.edu.school.schoolapp.model.Student;
import tr.ogu.edu.school.schoolapp.service.StudentService;


@RestController
@AllArgsConstructor
@RequestMapping("/students")

public class StudentController {
	private final StudentService studentService;
	
	@GetMapping
	public List<Student> getByUserId(Long userId) {
		return studentService.getByUserId(userId);
	}

	@GetMapping
	public List<Student> getAllStudents() {
		return studentService.getAllStudents();
	}

	@GetMapping("/{id}")
	public Optional<Student> getStudentById(@RequestParam Long id) {
		return studentService.getStudentById(id);
	}

	@PostMapping
	public Student saveStudent(@RequestBody Student student) {
		return studentService.saveStudent(student);
	}

	@DeleteMapping("/{id}")
	public void deleteStudent(@RequestParam Long id) {
		studentService.deleteStudent(id);
	}
}
