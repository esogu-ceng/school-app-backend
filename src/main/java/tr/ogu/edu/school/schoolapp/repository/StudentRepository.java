package tr.ogu.edu.school.schoolapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tr.ogu.edu.school.schoolapp.model.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
	List<Student> getByUserId(Long userId);
}
