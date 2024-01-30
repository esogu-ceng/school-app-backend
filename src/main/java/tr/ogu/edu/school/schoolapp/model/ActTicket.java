package tr.ogu.edu.school.schoolapp.model;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

/**
 * The persistent class for the act_ticket database table.
 * 
 */
@Entity
@Data
@Table(name = "act_ticket")
public class ActTicket implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String filepath;

	@Temporal(TemporalType.DATE)
	@Column(name = "session_date")
	private Date sessionDate;

	private String status;

	@Column(name = "verification_code")
	private String verificationCode;

	// bi-directional many-to-one association to ActSessionHall
	@ManyToOne
	@JoinColumn(name = "session_hall_id")
	private ActSessionHall actSessionHall;

	// bi-directional many-to-one association to User
	@ManyToOne
	private User user;

}