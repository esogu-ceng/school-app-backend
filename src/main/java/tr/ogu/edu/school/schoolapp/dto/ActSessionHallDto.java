package tr.ogu.edu.school.schoolapp.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class ActSessionHallDto implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;

	private Date blockedTime;

	private String status;

	private ActSeatDto actSeat;

	private ActSessionInfoDto actSessionInfo;

	private UserDto user;

	private List<ActTicketDto> actTickets;

}