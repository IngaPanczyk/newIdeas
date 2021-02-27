package com.kodilla.newideas.repository;

import com.kodilla.newideas.domain.IdeaStatus;
import com.kodilla.newideas.domain.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface StatusDao extends CrudRepository<IdeaStatus,Long> {

    @Override
    List<IdeaStatus> findAll();

}
