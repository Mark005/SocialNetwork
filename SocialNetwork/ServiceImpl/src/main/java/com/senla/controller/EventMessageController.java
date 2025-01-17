package com.senla.controller;

import com.senla.api.dto.event.EventMessageDto;
import com.senla.api.dto.message.CreateMessageDto;
import com.senla.api.dto.сonstants.Constants;
import com.senla.service.EventMessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Aliaksei Kaspiarovich
 */
@RestController
@RequestMapping(value = "/api/events/{eventId}/messages",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class EventMessageController {

    private final EventMessageService eventMessageService;

    /**
     *
     * @param eventId event ID
     * @param createMessageDto message body
     * @param email
     * @return message
     */
    @PostMapping
    public EventMessageDto createMessage(@PathVariable Long eventId,
            @RequestBody CreateMessageDto createMessageDto,
            @RequestHeader(Constants.EMAIL_HEADER) String email) {
        return eventMessageService.createEventMessage(eventId,
                createMessageDto, email);
    }

    /**
     *
     * @param eventId event ID
     * @param messageId message ID
     * @param email
     * @return message
     */
    @GetMapping("{messageId}")
    public EventMessageDto getMessageById(@PathVariable Long eventId,
            @PathVariable Long messageId,
            @RequestHeader(Constants.EMAIL_HEADER) String email) {
        return eventMessageService.getEventMessageById(eventId, messageId,
                email);
    }

    /**
     *
     * @param eventId event ID
     * @param messageId message ID
     * @param createMessageDto message body
     * @param email
     * @return updated message
     */
    @PutMapping("{messageId}")
    public EventMessageDto updateMessage(@PathVariable Long eventId,
            @PathVariable Long messageId,
            @RequestBody CreateMessageDto createMessageDto,
            @RequestHeader(Constants.EMAIL_HEADER) String email) {
        return eventMessageService.updateEventMessage(eventId, messageId,
                createMessageDto, email);
    }

    /**
     *
     * @param eventId event ID
     * @param messageId message ID
     * @param email
     */
    @DeleteMapping("{messageId}")
    public void deleteMessage(@PathVariable Long eventId, @PathVariable Long messageId,
            @RequestHeader(Constants.EMAIL_HEADER) String email) {
        eventMessageService.deleteEventMessage(eventId, messageId, email);
    }

    /**
     *
     * @param eventId event ID
     * @param email
     * @param pageable pagination information
     * @return messages
     */
    @GetMapping
    public Page<EventMessageDto> findAllMessages(@PathVariable Long eventId,
            @RequestHeader(Constants.EMAIL_HEADER) String email, Pageable pageable) {
        return eventMessageService.findAll(eventId, email, pageable);
    }

}
