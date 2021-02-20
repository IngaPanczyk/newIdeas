package com.kodilla.newideas.controller.domain;

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

    @ManyToOne
    @JoinColumn(name = "status_id")
    private IdeaStatus status;

    @ManyToOne
    @JoinColumn(name = "expert_id")
    private IdeaExpert ideaExpert;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


}
