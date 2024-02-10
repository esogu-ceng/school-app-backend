package tr.ogu.edu.school.schoolapp.model;

import java.util.Date;
import java.util.List;
import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The persistent class for the term database table.
 * 
 */
@Entity
@Data
@NoArgsConstructor
public class Term implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Temporal(TemporalType.DATE)
	@Column(name = "end_date")
	private Date endDate;

	
	@Temporal(TemporalType.DATE)
	@Column(name = "start_date")
	private Date startDate;

	@Column(name = "term_name")
	private String termName;

	// bi-directional many-to-one association to Installment
	@OneToMany(mappedBy = "term")
	private List<Installment> installments;

	// bi-directional many-to-many association to Student
	@ManyToMany(mappedBy = "terms")
	private List<Student> students;
	public Term(String termName, Date startDate, Date endDate, List<Student> students) {
		this.termName = termName;
		this.startDate = startDate;
		this.endDate = endDate;
		this.students = students;
		
	}

	public Installment removeInstallment(Installment installment) {
		getInstallments().remove(installment);
		installment.setTerm(null);

		return installment;
	}
	public Installment addInstallment(Installment installment) {
		getInstallments().add(installment);
		installment.setTerm(this);

		return installment;

}
}