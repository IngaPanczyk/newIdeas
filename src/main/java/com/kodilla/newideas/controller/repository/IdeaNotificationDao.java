package com.kodilla.newideas.controller.repository;


import com.kodilla.newideas.controller.domain.IdeaNotification;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public interface IdeaNotificationDao extends CrudRepository<IdeaNotification, Long> {

    @Override
    List<IdeaNotification> findAll();

    @Override
    Optional<IdeaNotification> findById(Long id);

    @Override
    IdeaNotification save(IdeaNotification ideaNotification);

    @Override
    long count();

}
