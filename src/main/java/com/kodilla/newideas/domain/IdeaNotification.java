package com.kodilla.newideas.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "NOTIFICATION")
public class IdeaNotification {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    @Column(name = "notification_id", unique = true)
    private Long id;
    @Column
    private String subject;
    @Column
    private String description;
    @Column
    private LocalDate reportingDate;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "status_id")
    private IdeaStatus status;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "expert_id")
    private IdeaExpert ideaExpert;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


}
