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
import jakarta.persistence.OneToMany;
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

	@Column(length = 32, name = "name", nullable = false)
	private String name;

	@Column(length = 32, name = "surname", nullable = false)
	private String surname;

	@Column(nullable = false)
	private Integer grade;

	@ManyToMany(mappedBy = "students")
	private Set<User> users;

	@ManyToMany
	@JoinTable(name = "student_term", joinColumns = @JoinColumn(name = "student_id"), inverseJoinColumns = @JoinColumn(name = "term_id"))
	private Set<Term> terms;

	@OneToMany
	@JoinColumn(name = "student_id")
	private Set<Installment> installments;
}
