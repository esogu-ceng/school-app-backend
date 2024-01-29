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
@Table(name = "users")
@NoArgsConstructor
@Data
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name", nullable = false, length = 32)
	private String name;

	@Column(name = "surname", nullable = false, length = 32)
	private String surname;

	@Column(name = "mail", unique = true, nullable = false, length = 30)
	private String mail;

	@Column(name = "password", nullable = false)
	private String password;
	@ManyToMany
	@JoinTable(name = "student_user", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "student_id"))
	private Set<Student> students;

	public User(String name, String surname, String mail, String password, Set<Student> students) {
		this.name = name;
		this.surname = surname;
		this.mail = mail;
		this.password = password;
		this.students = students;
	}

}
