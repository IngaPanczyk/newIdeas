package com.kodilla.newideas.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kodilla.newideas.mainView.MainView;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "EXPERTS")
public final class IdeaExpert {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "expert_id", unique = true)
    private Long expertId;
    @Column
    private String expertName;

    @JsonIgnore
    @OneToMany(targetEntity = IdeaNotification.class,
            mappedBy = "ideaExpert",
            cascade = CascadeType.MERGE,
            fetch = FetchType.EAGER

    )
    public List<IdeaNotification> ideaNotificationList = new ArrayList<>();

    public IdeaExpert(String expertName, List<IdeaNotification> ideaNotificationList) {
        this.expertName = expertName;
        this.ideaNotificationList = ideaNotificationList;
    }


    @Override
    public String toString() {
        return expertName;
    }
}
