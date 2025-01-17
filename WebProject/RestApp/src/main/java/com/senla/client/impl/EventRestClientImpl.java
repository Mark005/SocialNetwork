package com.senla.client.impl;

import com.senla.api.dto.event.CreateEventDto;
import com.senla.api.dto.event.EventDto;
import com.senla.client.EventRestClient;
import com.senla.api.dto.сonstants.Constants;
import com.senla.client.HttpHeaderBuilder;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author Aliaksei Kaspiarovich
 */
@Service
@RequiredArgsConstructor
public class EventRestClientImpl implements EventRestClient {

    private final RestTemplate restTemplate;
    private final HttpHeaderBuilder httpHeaderBuilder;
    private static final String URL = "/api/events/";
    private static final String USERS = "/users/";

    @Override
    public EventDto getEventById(Long eventId) {
        return restTemplate.exchange(Constants.HOST_PORT + URL + eventId,
                HttpMethod.GET, new HttpEntity<>(httpHeaderBuilder.build()),
                EventDto.class).getBody();
    }

    @Override
    public EventDto createEvent(CreateEventDto createEventDto) {
        return restTemplate.exchange(Constants.HOST_PORT + URL,
                HttpMethod.POST,
                new HttpEntity<>(createEventDto, httpHeaderBuilder.build()),
                EventDto.class).getBody();
    }

    @Override
    public EventDto updateEvent(Long eventId, CreateEventDto createEventDto) {
        return restTemplate.exchange(Constants.HOST_PORT + URL + eventId,
                HttpMethod.PUT,
                new HttpEntity<>(createEventDto, httpHeaderBuilder.build()),
                EventDto.class).getBody();
    }

    @Override
    public void deleteEvent(Long eventId) {
        restTemplate.exchange(Constants.HOST_PORT + URL + eventId,
                HttpMethod.DELETE, new HttpEntity<>(httpHeaderBuilder.build()),
                Void.class);
    }

    @Override
    public EventDto addUser(Long eventId, Long userId) {
        return restTemplate.exchange(Constants.HOST_PORT + URL + eventId + USERS + userId,
                HttpMethod.PUT, new HttpEntity<>(httpHeaderBuilder.build()),
                EventDto.class).getBody();
    }

    @Override
    public EventDto deleteUser(Long eventId, Long userId) {
        return restTemplate.exchange(Constants.HOST_PORT + URL + eventId + USERS + userId,
                HttpMethod.DELETE, new HttpEntity<>(httpHeaderBuilder.build()),
                EventDto.class).getBody();
    }

    @Override
    public Page<EventDto> findMyEvents(Pageable pageable, HttpServletRequest request) {
        String requestParam = request.getQueryString();
        String url = null;
        if (requestParam == null) {
            url = Constants.HOST_PORT + URL;
        } else {
            url = Constants.HOST_PORT + URL + Constants.QUESTION + requestParam;
        }
        return restTemplate.exchange(url, HttpMethod.GET,
                new HttpEntity<>(httpHeaderBuilder.build()),
                new ParameterizedTypeReference<RestResponsePage<EventDto>>() {
        }).getBody();
    }

}
