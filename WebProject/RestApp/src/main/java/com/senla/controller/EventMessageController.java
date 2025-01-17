package com.senla.controller;

import com.senla.api.dto.event.EventMessageDto;
import com.senla.api.dto.message.CreateMessageDto;
import com.senla.client.EventMessageRestClient;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
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

    private final EventMessageRestClient eventMessageRestClient;

    /**
     *
     * @param eventId event ID
     * @param messageId message ID
     * @return message
     */
    @GetMapping("{messageId}")
    public EventMessageDto getMessageById(@PathVariable Long eventId,
            @PathVariable Long messageId) {
        return eventMessageRestClient.getEventMessageById(eventId, messageId);
    }

    /**
     *
     * @param eventId event ID
     * @param createMessageDto message body
     * @return message
     */
    @PostMapping
    public EventMessageDto createMessage(@PathVariable Long eventId,
            @Valid @RequestBody CreateMessageDto createMessageDto) {
        return eventMessageRestClient.createEventMessage(eventId,
                createMessageDto);
    }

    /**
     *
     * @param eventId event ID
     * @param messageId message ID
     * @param createMessageDto message body
     * @return updated message
     */
    @PutMapping("{messageId}")
    public EventMessageDto updateMessage(@PathVariable Long eventId,
            @PathVariable Long messageId,
            @Valid @RequestBody CreateMessageDto createMessageDto) {
        return eventMessageRestClient.updateEventMessage(eventId, messageId,
                createMessageDto);
    }

    /**
     *
     * @param eventId event ID
     * @param messageId message ID
     */
    @DeleteMapping("{messageId}")
    public void deleteMessage(@PathVariable Long eventId, @PathVariable Long messageId) {
        eventMessageRestClient.deleteEventMessage(eventId, messageId);
    }

    /**
     *
     * @param eventId event ID
     * @param pageable pagination information
     * @param request
     * @return messages
     */
    @GetMapping
    public Page<EventMessageDto> findAllMessages(@PathVariable Long eventId,
            Pageable pageable, HttpServletRequest request) {
        return eventMessageRestClient.findAll(eventId, pageable, request);
    }

}
