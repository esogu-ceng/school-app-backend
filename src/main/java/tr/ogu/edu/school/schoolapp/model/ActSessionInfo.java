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
 * The persistent class for the act_session_info database table.
 * 
 */
@Entity
@Data
@Table(name = "act_session_info")
public class ActSessionInfo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Temporal(TemporalType.DATE)
	@Column(name = "activity_date")
	private Date activityDate;

	@Column(name = "activity_name")
	private String activityName;

	private double fee;

	// bi-directional many-to-one association to ActSessionHall
	@OneToMany(mappedBy = "actSessionInfo")
	private List<ActSessionHall> actSessionHalls;

	// bi-directional many-to-one association to ActHall
	@ManyToOne
	@JoinColumn(name = "hall_id")
	private ActHall actHall;

	public ActSessionHall addActSessionHall(ActSessionHall actSessionHall) {
		getActSessionHalls().add(actSessionHall);
		actSessionHall.setActSessionInfo(this);

		return actSessionHall;
	}

	public ActSessionHall removeActSessionHall(ActSessionHall actSessionHall) {
		getActSessionHalls().remove(actSessionHall);
		actSessionHall.setActSessionInfo(null);

		return actSessionHall;
	}
}