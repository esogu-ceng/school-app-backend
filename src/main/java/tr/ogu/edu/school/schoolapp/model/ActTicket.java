package tr.ogu.edu.school.schoolapp.model;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;
import tr.ogu.edu.school.schoolapp.enums.TicketStatus;

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
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "act_ticket_id_seq")
	@SequenceGenerator(name = "act_ticket_id_seq", sequenceName = "act_ticket_id_seq", allocationSize = 1)
	private Long id;

	private String filepath;

	@Temporal(TemporalType.DATE)
	@Column(name = "session_date")
	private Date sessionDate = new Date();

	@Enumerated(EnumType.STRING)
	private TicketStatus status;

	@Column(name = "verification_code")
	private String verificationCode;

	// bi-directional many-to-one association to ActSessionHall
	@ManyToOne
	@JoinColumn(name = "session_hall_id")
	private ActSessionHallSeat actSessionHallSeat;

	// bi-directional many-to-one association to User
	@ManyToOne
	private User user;

}