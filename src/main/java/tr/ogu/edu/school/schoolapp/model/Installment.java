package tr.ogu.edu.school.schoolapp.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "installment")
@NoArgsConstructor
@Data
public class Installment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, updatable = false)
	private Long id;

	@Column(name = "amount", nullable = false)
	private Double amount;

	@Column(name = "due_date", nullable = false)
	private Date dueDate;

	@ManyToOne
	@JoinColumn(name = "term_id", nullable = false)
	private Term term;

	@ManyToOne
	@JoinColumn(name = "student_id", nullable = false)
	private Student student;

	@ManyToOne
	@JoinColumn(name = "payment_id", insertable = false, updatable = false)
	private Payment payment;
}
