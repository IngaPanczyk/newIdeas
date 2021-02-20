package com.kodilla.newideas.mapper;

import com.kodilla.newideas.domain.IdeaNotification;
import com.kodilla.newideas.domain.IdeaNotificationDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class IdeaMapper {
    public IdeaNotification mapToIdea (final IdeaNotificationDto ideaNotificationDto){
        return new IdeaNotification(
                ideaNotificationDto.getId(),
                ideaNotificationDto.getSubject(),
                ideaNotificationDto.getDescription(),
                ideaNotificationDto.getReportingDate(),
                ideaNotificationDto.getStatus(),
                ideaNotificationDto.getIdeaExpert(),
                ideaNotificationDto.getUser()
        );
    }
    public IdeaNotificationDto mapToIdeaDto (final IdeaNotification idea){
        return new IdeaNotificationDto(
                idea.getId(),
                idea.getSubject(),
                idea.getDescription(),
                idea.getReportingDate(),
                idea.getStatus(),
                idea.getIdeaExpert(),
                idea.getUser()
        );
    }
    public List<IdeaNotificationDto> mapToIdeaDtoList (final  List<IdeaNotification> ideaList){
        return ideaList.stream()
                .map(t-> new IdeaNotificationDto(t.getId(), t.getSubject(), t.getDescription(), t.getReportingDate(), t.getStatus(), t.getIdeaExpert(), t.getUser()))
                .collect(Collectors.toList());
    }
}
