package com.kodilla.newideas.service;



import com.kodilla.newideas.domain.IdeaExpert;
import com.kodilla.newideas.domain.IdeaNotification;
import com.kodilla.newideas.repository.IdeaExpertDao;
import com.kodilla.newideas.repository.IdeaNotificationDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class DbService {
    @Autowired
    private IdeaExpertDao ideaExpertDao;
    @Autowired
    private IdeaNotificationDao ideaNotificationDao;


    public List<IdeaExpert> getAllExperts() {
        return ideaExpertDao.findAll();
    }

    public Optional<IdeaExpert> getExpert(final Long id) {
        return ideaExpertDao.findById(id);
    }

    public Optional<IdeaExpert> getExpertByName(final String expertName) {
        return ideaExpertDao.findIdeaExpertByExpertName(expertName);
    }

    public IdeaExpert saveExpert(final IdeaExpert ideaExpert){
        return ideaExpertDao.save(ideaExpert);
    }

    public void deleteExpertByName(final String expertName){
        ideaExpertDao.deleteByExpertName(expertName);
    }

    public void deleteExpertById(final Long id){
        ideaExpertDao.deleteById(id);
    }


    public IdeaNotification saveIdea(final IdeaNotification ideaNotification){
        return ideaNotificationDao.save(ideaNotification);
    }

    public List<IdeaNotification> getAllIdeas() {
        return ideaNotificationDao.findAll();
    }

    public Optional<IdeaNotification> getIdea(final Long id) {
        return ideaNotificationDao.findById(id);
    }

    public void deleteIdeaById(final Long id){
        ideaNotificationDao.deleteById(id);
    }

}
