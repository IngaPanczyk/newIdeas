package com.kodilla.newideas.repository;

import com.kodilla.newideas.domain.IdeaExpert;
import com.kodilla.newideas.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


//@Repository
public interface UserDao extends CrudRepository<User, Long> {
/*    @Override
    User save(User user);

    @Override
    List<User> findAll();

    @Override
    Optional<User> findById(Long id);

    @Override
    long count();
}*/
}