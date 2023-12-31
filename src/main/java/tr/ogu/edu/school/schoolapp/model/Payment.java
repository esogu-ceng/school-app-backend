package tr.ogu.edu.school.schoolapp.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "payment")
@NoArgsConstructor
@Data
public class Payment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, updatable = false)
	private Long id;

	@Column(name = "amount", nullable = false)
	private Double amount;

	@Column(name = "payment_date", nullable = false)
	private Date paymentDate;

	public Payment(Double amount, Date paymentDate) {
		this.amount = amount;
		this.paymentDate = paymentDate;
	}

}
