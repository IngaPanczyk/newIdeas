package com.kodilla.newideas.controller.domain;

import java.util.ArrayList;
import java.util.List;

public class IdeaStatusDto {

    public Long statusId;
    public String notificationStatus;
    public List<IdeaNotification> ideaNotificationList = new ArrayList<>();
}
