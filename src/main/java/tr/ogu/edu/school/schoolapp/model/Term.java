package tr.ogu.edu.school.schoolapp.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
import jakarta.persistence.Transient;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "term")
@NoArgsConstructor
@Data
public class Term {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "term_name", nullable = false, length = 30)
	private String termName;

	@Column(name = "start_date", nullable = false)
	private Date startDate;

	@Column(name = "end_date", nullable = false)
	private Date endDate;

	@ManyToMany
	@JoinTable(name = "student_term", joinColumns = @JoinColumn(name = "term_id"), inverseJoinColumns = @JoinColumn(name = "student_id"))
	private Set<Student> students;
	@OneToMany(mappedBy = "term")
	private List<Installment> installments;
	
	public Term(String termName, Date startDate, Date endDate, Set<Student> students,List<Installment> installments) {
		this.termName = termName;
		this.startDate = startDate;
		this.endDate = endDate;
		this.students = students;
		this.installments=installments;
	}
}
