package tr.ogu.edu.school.schoolapp.model;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The persistent class for the installment database table.
 * 
 */
@Entity
@Data
@NoArgsConstructor
public class Installment implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Double amount;

	@Temporal(TemporalType.DATE)
	@Column(name = "due_date")
	private Date dueDate;

	// bi-directional many-to-one association to Payment
	@ManyToOne
	private Payment payment;

   // bi-directional many-to-one association to Student
	@ManyToOne
	private Student student;
	
	// bi-directional many-to-one association to Term
	@ManyToOne
	private Term term;

	public Installment(Double amount,Date dueDate, Term term, Student student, Payment payment) {
		this.amount = amount;
		this.dueDate = dueDate;
		this.term = term;
		this.student = student;
		this.payment = payment;
	}
}