package com.kodilla.newideas.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@AllArgsConstructor
@Getter
@Setter
public class UserDto {

    Long userId;
    String username;
    public List<IdeaNotification> ideaNotificationList = new ArrayList<>();
}
