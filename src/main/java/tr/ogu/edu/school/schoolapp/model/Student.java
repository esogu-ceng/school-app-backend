package tr.ogu.edu.school.schoolapp.model;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The persistent class for the student database table.
 * 
 */
@Entity
@Data
@NoArgsConstructor
public class Student implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Integer grade;

	private String name;

	private String surname;

	// bi-directional many-to-one association to Installment
	@OneToMany(mappedBy = "student")
	private List<Installment> installments;

	// bi-directional many-to-many association to Term
	@ManyToMany
	@JoinTable(name = "student_term", joinColumns = {
			@JoinColumn(name = "student_id")
	}, inverseJoinColumns = {
			@JoinColumn(name = "term_id")
	})
	private List<Term> terms;

	// bi-directional many-to-many association to User
	@ManyToMany
	@JoinTable(name = "student_user", joinColumns = {
			@JoinColumn(name = "student_id")
	}, inverseJoinColumns = {
			@JoinColumn(name = "user_id")
	})
	private List<User> users;

	public Installment addInstallment(Installment installment) {
		getInstallments().add(installment);
		installment.setStudent(this);

		return installment;
	}

	public Installment removeInstallment(Installment installment) {
		getInstallments().remove(installment);
		installment.setStudent(null);

		return installment;
	}
	public Student(String name, String surname, Integer grade, List<Installment> installments, List<Term> terms, List<User> users) {
	    this.name = name;
	    this.surname = surname;
	    this.grade = grade;
	    this.installments = installments;
	    this.terms = terms;
	    this.users = users;
	}

}