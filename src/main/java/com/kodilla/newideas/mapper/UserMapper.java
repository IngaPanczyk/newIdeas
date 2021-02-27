package com.kodilla.newideas.mapper;

import com.kodilla.newideas.domain.User;
import com.kodilla.newideas.domain.UserDto;
import org.springframework.stereotype.Component;


import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper {
    public User mapToUser(final UserDto userDto){
        return new User(
                userDto.getUserId(),
                userDto.getUsername(),
                userDto.getIdeaNotificationList()
        );
    }

    public UserDto mapToUserDto(final User user){
        return new UserDto(
                user.getUserId(),
                user.getUsername(),
                user.getIdeaNotificationList()
        );
    }

    public List<UserDto> mapToUserDtoList(final List<User> userList){
        return userList.stream()
                .map(t->new UserDto(t.getUserId(),t.getUsername(),t.getIdeaNotificationList()))
                .collect(Collectors.toList());
    }
}
