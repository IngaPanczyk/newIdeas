package com.kodilla.newideas;

import com.kodilla.newideas.controller.IdeaController;
import com.kodilla.newideas.service.DbService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;



public class IdeaFormTestSuite {

    @Autowired
    DbService dbService;

    @Autowired
    IdeaController ideaController;

    @Test
    public void test(){

        //List<IdeaNotification> ideas = dbService.getAllIdeas();
        //System.out.println(ideas.size());
    }

    @Test
    public void test1(){

        //List<IdeaNotificationDto> ideas = ideaController.getIdeas();
        //System.out.println(ideas.size());
    }
}
