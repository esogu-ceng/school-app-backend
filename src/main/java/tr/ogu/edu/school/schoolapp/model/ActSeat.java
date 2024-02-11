package tr.ogu.edu.school.schoolapp.model;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

/**
 * The persistent class for the act_seat database table.
 * 
 */
@Entity
@Data
@Table(name = "act_seat")
public class ActSeat implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String line;

	private Integer no;

	// bi-directional many-to-one association to ActCategory
	@ManyToOne
	@JoinColumn(name = "category_id")
	private ActCategory actCategory;

	// bi-directional many-to-one association to ActHall
	@ManyToOne
	@JoinColumn(name = "hall_id")
	private ActHall actHall;

	// bi-directional many-to-one association to ActSessionHall
	@OneToMany(mappedBy = "actSeat")
	private List<ActSessionHallSeat> actSessionHalls;

	public ActSessionHallSeat addActSessionHall(ActSessionHallSeat actSessionHall) {
		getActSessionHalls().add(actSessionHall);
		actSessionHall.setActSeat(this);

		return actSessionHall;
	}

	public ActSessionHallSeat removeActSessionHall(ActSessionHallSeat actSessionHall) {
		getActSessionHalls().remove(actSessionHall);
		actSessionHall.setActSeat(null);

		return actSessionHall;
	}

}