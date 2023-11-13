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
@Table(name = "student")
@NoArgsConstructor
@Data
public class Student {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name", length = 32, nullable = false)
	private String name;

	@Column(name = "surname", length = 32, nullable = false)
	private String surname;

	@Column(name = "grade", nullable = false)
	private Integer grade;

	@ManyToMany
	@JoinTable(name = "student_user", joinColumns = @JoinColumn(name = "student_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
	private Set<User> users;

	@ManyToMany
	@JoinTable(name = "student_term", joinColumns = @JoinColumn(name = "student_id"), inverseJoinColumns = @JoinColumn(name = "term_id"))
	private Set<Term> terms;

	public Student(String name, String surname, Integer grade, Set<User> users, Set<Term> terms) {
		this.name = name;
		this.surname = surname;
		this.grade = grade;
		this.users = users;
		this.terms = terms;
	}
}
