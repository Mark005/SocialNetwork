package com.senla.api.dto.event;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.senla.api.dto.user.DtoUser;
import com.senla.api.dto.сonstants.Constants;
import java.time.LocalDateTime;
import lombok.Data;

/**
 *
 * @author Aliaksei Kaspiarovich
 */
@Data
public class EventMessageDto {

    private Long id;

    @JsonFormat(pattern = Constants.DATE_TIME_PATTERN)
    private LocalDateTime posted;

    private String body;
    private DtoUser sender;
    private EventDto event;

}
