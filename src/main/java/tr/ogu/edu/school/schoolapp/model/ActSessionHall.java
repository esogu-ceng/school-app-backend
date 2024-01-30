package tr.ogu.edu.school.schoolapp.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

/**
 * The persistent class for the act_session_hall database table.
 * 
 */
@Entity
@Data
@Table(name = "act_session_hall")
public class ActSessionHall implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Temporal(TemporalType.DATE)
	@Column(name = "blocked_time")
	private Date blockedTime;

	private String status;

	// bi-directional many-to-one association to ActSeat
	@ManyToOne
	@JoinColumn(name = "seat_id")
	private ActSeat actSeat;

	// bi-directional many-to-one association to ActSessionInfo
	@ManyToOne
	@JoinColumn(name = "session_id")
	private ActSessionInfo actSessionInfo;

	// bi-directional many-to-one association to User
	@ManyToOne
	private User user;

	// bi-directional many-to-one association to ActTicket
	@OneToMany(mappedBy = "actSessionHall")
	private List<ActTicket> actTickets;

	public ActTicket addActTicket(ActTicket actTicket) {
		getActTickets().add(actTicket);
		actTicket.setActSessionHall(this);

		return actTicket;
	}

	public ActTicket removeActTicket(ActTicket actTicket) {
		getActTickets().remove(actTicket);
		actTicket.setActSessionHall(null);

		return actTicket;
	}

}