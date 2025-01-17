package com.senla.service.impl;

import com.senla.api.dto.user.UserDetailsDto;
import com.senla.mapper.Mapper;
import com.senla.service.IUserDetailService;
import com.senla.service.CustomUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 *
 * @author Aliaksei Kaspiarovich
 */
@Service
@RequiredArgsConstructor
public class MyUserDetailService implements IUserDetailService {

    private final CustomUserService userService;
    private final Mapper mapper;

    /**
     *
     * @param email
     * @return
     */
    @Override
    public UserDetailsDto getUserByEmail(String email) {
        return mapper.map(userService.findUserByEmail(email), UserDetailsDto.class);
    }
}
