package com.senla.prototype;

import com.senla.api.dto.user.DtoUser;
import com.senla.api.dto.сonstants.Gender;
import com.senla.api.dto.сonstants.Roles;
import com.senla.api.dto.сonstants.Status;
import com.senla.model.Role;
import com.senla.model.User;
import java.time.LocalDate;

/**
 *
 * @author Aliaksei Kaspiarovich
 */
public class UserPrototype {

    public static User getUser() {
        Role role = new Role(Roles.ROLE_USER);
        return User.builder()
                .email("test@gmail.com")
                .password("password")
                .role(role)
                .status(Status.ACTIVE).build();
    }

    public static DtoUser getUserDto() {
        DtoUser user = new DtoUser();
        user.setId(42L);
        user.setEmail("test@gmail.com");
        user.setBirthday(LocalDate.now());
        user.setFirstName("firstName");
        user.setLastName("lastName");
        user.setSex(Gender.MALE.name());
        user.setPhone("123456789");
        user.setStatus(Status.ACTIVE.name());
        return user;
    }
}
