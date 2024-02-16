package tr.ogu.edu.school.schoolapp.model;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The persistent class for the user database table.
 * 
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "mail", unique = true, length = 100)
	private String mail;

	@Column(name = "tckn", unique = true, nullable = false, length = 11)
	private String tckn;

	private String name;

	private String surname;

	@Column(name = "password", nullable = false)
	private String password;

	// bi-directional many-to-one association to ActSessionHall
	@OneToMany(mappedBy = "user")
	private List<ActSessionHallSeat> actSessionHalls;

	// bi-directional many-to-one association to ActTicket
	@OneToMany(mappedBy = "user")
	private List<ActTicket> actTickets;

	// bi-directional many-to-many association to Student
	@ManyToMany(mappedBy = "users")
	private List<Student> students;

	public ActSessionHallSeat addActSessionHall(ActSessionHallSeat actSessionHall) {
		getActSessionHalls().add(actSessionHall);
		actSessionHall.setUser(this);

		return actSessionHall;
	}

	public ActSessionHallSeat removeActSessionHall(ActSessionHallSeat actSessionHall) {
		getActSessionHalls().remove(actSessionHall);
		actSessionHall.setUser(null);

		return actSessionHall;
	}

	public ActTicket addActTicket(ActTicket actTicket) {
		getActTickets().add(actTicket);
		actTicket.setUser(this);

		return actTicket;
	}

	public ActTicket removeActTicket(ActTicket actTicket) {
		getActTickets().remove(actTicket);
		actTicket.setUser(null);

		return actTicket;
	}

}