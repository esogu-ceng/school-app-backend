package tr.ogu.edu.school.schoolapp.model;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

/**
 * The persistent class for the act_hall database table.
 * 
 */
@Entity
@Data
@Table(name = "act_hall")
public class ActHall implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	// bi-directional many-to-one association to ActSeat
	@OneToMany(mappedBy = "actHall")
	private List<ActSeat> actSeats;

	// bi-directional many-to-one association to ActSessionInfo
	@OneToMany(mappedBy = "actHall")
	private List<ActSessionInfo> actSessionInfos;

	public ActSeat addActSeat(ActSeat actSeat) {
		getActSeats().add(actSeat);
		actSeat.setActHall(this);

		return actSeat;
	}

	public ActSeat removeActSeat(ActSeat actSeat) {
		getActSeats().remove(actSeat);
		actSeat.setActHall(null);

		return actSeat;
	}

	public ActSessionInfo addActSessionInfo(ActSessionInfo actSessionInfo) {
		getActSessionInfos().add(actSessionInfo);
		actSessionInfo.setActHall(this);

		return actSessionInfo;
	}

	public ActSessionInfo removeActSessionInfo(ActSessionInfo actSessionInfo) {
		getActSessionInfos().remove(actSessionInfo);
		actSessionInfo.setActHall(null);

		return actSessionInfo;
	}

}