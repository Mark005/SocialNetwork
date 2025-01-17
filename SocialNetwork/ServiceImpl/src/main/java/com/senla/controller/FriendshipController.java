package com.senla.controller;

import com.senla.api.dto.friendship.FriendshipDto;
import com.senla.api.dto.сonstants.Constants;
import com.senla.service.FriendshipService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
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

    private final FriendshipService friendshipService;

    /**
     *
     * @param friendshipId friendship ID
     * @param email
     * @return friendship
     */
    @GetMapping("{friendshipId}")
    public FriendshipDto getFriendshipById(@PathVariable Long friendshipId,
            @RequestHeader(Constants.EMAIL_HEADER) String email) {
        return friendshipService.getFriendshipById(friendshipId, email);
    }

    /**
     *
     * @param friendId user ID
     * @param email
     * @return friendship
     */
    @PostMapping
    public FriendshipDto addToFriends(@RequestParam Long friendId,
            @RequestHeader(Constants.EMAIL_HEADER) String email) {
        return friendshipService.createFriendship(friendId, email);
    }

    /**
     *
     * @param friendshipId friendship ID
     * @param email
     * @return accepted friendship
     */
    @PutMapping("{friendshipId}")
    public FriendshipDto acceptFriendship(@PathVariable Long friendshipId,
            @RequestHeader(Constants.EMAIL_HEADER) String email) {
        return friendshipService.acceptFriendship(friendshipId, email);
    }

    /**
     *
     * @param friendshipId friendship ID
     * @param email
     */
    @DeleteMapping("{friendshipId}")
    public void declineFriendship(@PathVariable Long friendshipId,
            @RequestHeader(Constants.EMAIL_HEADER) String email) {
        friendshipService.deleteFriendship(friendshipId, email);
    }

    /**
     *
     * @param email
     * @param pageable pagination information
     * @return friend request list
     */
    @GetMapping("requests")
    public Page<FriendshipDto> findMyFriendshipRequests(
            @RequestHeader(Constants.EMAIL_HEADER) String email, Pageable pageable) {
        return friendshipService.findMyFriendshipRequests(email, pageable);
    }

    /**
     *
     * @param email
     * @param pageable pagination information
     * @return friendships
     */
    @GetMapping
    public Page<FriendshipDto> findMyFriends(
            @RequestHeader(Constants.EMAIL_HEADER) String email, Pageable pageable) {
        return friendshipService.findAll(email, pageable);
    }
}
