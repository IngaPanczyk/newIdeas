package com.kodilla.newideas.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Component
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "STATUSES")
public class IdeaStatus {
    @Id
    @Column(name = "status_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long statusId;

    @Column
    public String notificationStatus;

    @JsonIgnore
    @OneToMany(targetEntity = IdeaNotification.class,
            mappedBy = "status",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    public List<IdeaNotification> ideaNotificationList = new ArrayList<>();

    @Override
    public String toString() {
        return notificationStatus;
    }
}
