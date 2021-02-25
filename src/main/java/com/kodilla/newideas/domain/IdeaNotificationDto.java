package com.kodilla.newideas.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class IdeaNotificationDto {

    private Long id;
    private String subject;
    private String description;
    private LocalDate reportingDate;
    private IdeaStatus status; //tylko ID i powinno być Dto
    private IdeaExpert ideaExpert; // tylko ID
    private User user;

}
