package tr.ogu.edu.school.schoolapp.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

/**
 * The persistent class for the payment database table.
 * 
 */
@Entity
@Data
public class Payment implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Double amount;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "payment_date")
	private Date paymentDate;

	// bi-directional many-to-one association to Installment
	@OneToMany(mappedBy = "payment")
	private List<Installment> installments;

	public Installment addInstallment(Installment installment) {
		getInstallments().add(installment);
		installment.setPayment(this);

		return installment;
	}

	public Installment removeInstallment(Installment installment) {
		getInstallments().remove(installment);
		installment.setPayment(null);

		return installment;
	}

}