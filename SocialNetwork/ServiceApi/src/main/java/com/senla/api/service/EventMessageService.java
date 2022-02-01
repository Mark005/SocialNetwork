package com.senla.api.service;

import com.senla.api.dto.event.EventMessageDto;
import com.senla.api.dto.message.CreateMessageDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 *
 * @author Aliaksei Kaspiarovich
 */
public interface EventMessageService {

    EventMessageDto createEventMessage(Long eventId, CreateMessageDto createMessageDto);

    EventMessageDto getEventMessageById(Long eventId, Long messageId);

    EventMessageDto updateEventMessage(Long eventId, Long messageId,
            CreateMessageDto createMessageDto);

    void deleteEventMessage(Long eventId, Long messageId);

    Page<EventMessageDto> findAll(Long eventId, Pageable pageable);

}
