package com.kodilla.newideas.service;



import com.kodilla.newideas.domain.IdeaExpert;
import com.kodilla.newideas.domain.IdeaNotification;
import com.kodilla.newideas.domain.IdeaStatus;
import com.kodilla.newideas.domain.User;
import com.kodilla.newideas.repository.IdeaExpertDao;
import com.kodilla.newideas.repository.IdeaNotificationDao;
import com.kodilla.newideas.repository.StatusDao;
import com.kodilla.newideas.repository.UserDao;
import jdk.net.SocketFlow;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@NoArgsConstructor
@Transactional
@Service
public class DbService {

    @Autowired
    private IdeaExpertDao ideaExpertDao;
    @Autowired
    private IdeaNotificationDao ideaNotificationDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private StatusDao statusDao;

   // private static DbService dbService;

/*    public static DbService getInstance(){
        if(dbService==null){
            dbService=new DbService();
        }
        return dbService;
    }*/

    //Experts
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

    //Notification
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

    public List<IdeaNotification> filterIdeasByDescription(String description) {
        return ideaNotificationDao.findAll().stream().filter(idea->idea.getDescription().contains(description)).collect(Collectors.toList());
    }
    public List<IdeaNotification> filterIdeasById(Long id) {
        return ideaNotificationDao.findAll().stream().filter(idea->idea.getId().equals(id)).collect(Collectors.toList());
    }
    public String countIdeas() {
        long counter = ideaNotificationDao.findAll().stream().count();
        String count = String.valueOf(counter);
        return count;
    }

    public User saveUser(final User user){
        return userDao.save(user);
    }

    public List<User> getAllUsers() {
        return userDao.findAll();
    }

    public List<IdeaStatus> getAllStatuses(){
        return statusDao.findAll();
    }


/*    public User saveUser(final User user) {
        return userDao.save(user);
    }



    public Optional<User> getUser(final Long id) {
        return userDao.findById(id);
    }

    public void deleteUserById(final Long id) {
        userDao.deleteById(id);
    }*/
}
