package tr.ogu.edu.school.schoolapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tr.ogu.edu.school.schoolapp.model.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
	@Query("SELECT s FROM Student s JOIN s.users u WHERE u.id = :userId")
	List<Student> findStudentsByUserId(@Param("userId") Long userId);
}
