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
 * The persistent class for the act_category database table.
 * 
 */
@Entity
@Data
@Table(name = "act_category")
public class ActCategory implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	// bi-directional many-to-one association to ActSeat
	@OneToMany(mappedBy = "actCategory")
	private List<ActSeat> actSeats;

	public ActSeat addActSeat(ActSeat actSeat) {
		getActSeats().add(actSeat);
		actSeat.setActCategory(this);

		return actSeat;
	}

	public ActSeat removeActSeat(ActSeat actSeat) {
		getActSeats().remove(actSeat);
		actSeat.setActCategory(null);

		return actSeat;
	}

}