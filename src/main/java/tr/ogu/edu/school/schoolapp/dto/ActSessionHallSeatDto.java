package tr.ogu.edu.school.schoolapp.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import lombok.Data;
import tr.ogu.edu.school.schoolapp.enums.SeatStatus;

@Data
public class ActSessionHallSeatDto implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;

	private Date blockedTime;

	private SeatStatus status;

	private ActSeatDto actSeat;

	private ActSessionInfoDto actSessionInfo;

	private UserDto user;

	private List<ActTicketDto> actTickets;

}