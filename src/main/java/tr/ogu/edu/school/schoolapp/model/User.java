package tr.ogu.edu.school.schoolapp.model;

import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user")
@NoArgsConstructor
@Data
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(length = 32, name = "name", nullable = false)
	private String name;

	@Column(length = 32, name = "surname", nullable = false)
	private String surname;

	@Column(name = "mail", unique = true)
	private String mail;

	@Column(nullable = false)
	private String password;

	@ManyToMany
	@JoinTable(name = "user_student", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "student_id"))
	private Set<Student> students;

}
