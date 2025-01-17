package com.senla.controller;

import com.senla.api.dto.friendship.FriendshipDto;
import com.senla.client.FriendshipRestClient;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Aliaksei Kaspiarovich
 */
@RestController
@RequestMapping(value = "/api/friendships",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class FriendshipController {

    private final FriendshipRestClient friendshipRestClient;

    /**
     *
     * @param friendshipId friendship ID
     * @return friendship
     */
    @GetMapping("{friendshipId}")
    public FriendshipDto getFriendshipById(@PathVariable Long friendshipId) {
        return friendshipRestClient.getFriendshipById(friendshipId);
    }

    /**
     *
     * @param friendId user ID
     * @return friendship
     */
    @PostMapping
    public FriendshipDto addToFriends(@RequestParam Long friendId) {
        return friendshipRestClient.createFriendship(friendId);
    }

    /**
     *
     * @param friendshipId friendship ID
     * @return accepted friendship
     */
    @PutMapping("{friendshipId}")
    public FriendshipDto acceptFriendship(@PathVariable Long friendshipId) {
        return friendshipRestClient.acceptFriendship(friendshipId);
    }

    /**
     *
     * @param friendshipId friendship ID
     */
    @DeleteMapping("{friendshipId}")
    public void declineFriendship(@PathVariable Long friendshipId) {
        friendshipRestClient.deleteFriendship(friendshipId);
    }

    /**
     *
     * @param pageable pagination information
     * @param request
     * @return friend request list
     */
    @GetMapping("requests")
    public Page<FriendshipDto> findMyFriendshipRequests(Pageable pageable,
            HttpServletRequest request) {
        return friendshipRestClient.findMyFriendshipRequests(pageable, request);
    }

    /**
     *
     * @param pageable pagination information
     * @param request
     * @return friendships
     */
    @GetMapping
    public Page<FriendshipDto> findMyFriends(Pageable pageable, HttpServletRequest request) {
        return friendshipRestClient.findAll(pageable, request);
    }
}
