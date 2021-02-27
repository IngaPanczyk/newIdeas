package com.kodilla.newideas.controller;



import com.kodilla.newideas.domain.IdeaExpertDto;
import com.kodilla.newideas.domain.IdeaNotificationDto;
import com.kodilla.newideas.domain.User;
import com.kodilla.newideas.domain.UserDto;
import com.kodilla.newideas.exception.ExpertNotFoundException;
import com.kodilla.newideas.exception.IdeaNotFoundException;
import com.kodilla.newideas.mapper.IdeaExpertMapper;
import com.kodilla.newideas.mapper.IdeaMapper;
import com.kodilla.newideas.mapper.UserMapper;
import com.kodilla.newideas.service.DbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/idea")
public class IdeaController {

    @Autowired
    private DbService service;
    @Autowired
    private IdeaExpertMapper ideaExpertMapper;
    @Autowired
    private IdeaMapper ideaMapper;
    @Autowired
    private UserMapper userMapper;


    @RequestMapping(method = RequestMethod.GET, value = "getExperts")
    public List<IdeaExpertDto> getExperts() {
        return ideaExpertMapper.mapToIdeaExpertDtoList(service.getAllExperts());
    }

    @RequestMapping(method = RequestMethod.GET, value = "getExpert")
    public IdeaExpertDto getExpert(@RequestParam Long id) throws ExpertNotFoundException {
        return ideaExpertMapper.mapToIdeaExpertDto(service.getExpert(id).orElseThrow(ExpertNotFoundException::new));
    }

    @RequestMapping(method = RequestMethod.POST, value = "createExpert", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createExpert(@RequestBody IdeaExpertDto ideaExpertDto) {
        service.saveExpert(ideaExpertMapper.mapToIdeaExpert(ideaExpertDto));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "deleteExpertById")
    public void deleteExpertById(@RequestParam Long id) {
        service.deleteExpertById(id);
    }


    @RequestMapping(method = RequestMethod.GET, value = "getIdeas")
    public List<IdeaNotificationDto> getIdeas() {
        return ideaMapper.mapToIdeaDtoList(service.getAllIdeas());
    }

    @RequestMapping(method = RequestMethod.GET, value = "getIdeasByDescription")
    public List<IdeaNotificationDto> getIdeasByDescription(@RequestParam String description)  {
        return ideaMapper.mapToIdeaDtoList(service.filterIdeasByDescription(description));
    }

    @RequestMapping(method = RequestMethod.GET, value = "getIdea")
    public IdeaNotificationDto getIdea(@RequestParam Long id) throws IdeaNotFoundException {
        return ideaMapper.mapToIdeaDto(service.getIdea(id).orElseThrow(IdeaNotFoundException::new));
    }

    @RequestMapping(method = RequestMethod.POST, value = "createIdea", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createIdea(@RequestBody IdeaNotificationDto ideaNotificationDto) {
        service.saveIdea(ideaMapper.mapToIdea(ideaNotificationDto));
    }

    @RequestMapping(method = RequestMethod.PUT, value = "updateIdea")
    public IdeaNotificationDto updateIdes(@RequestBody IdeaNotificationDto ideaDto) {
        return ideaMapper.mapToIdeaDto((service.saveIdea(ideaMapper.mapToIdea(ideaDto))));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "deleteIdeaById")
    public void deleteIdeaById(@RequestParam Long id) {
        service.deleteIdeaById(id);
    }

/*    @RequestMapping(method = RequestMethod.GET, value = "getUsers")
    public List<UserDto> getUsers() {
        return userMapper.mapToUserDtoList(service.getAllUsers());
    }

    @RequestMapping(method = RequestMethod.GET, value = "getUser")
    public UserDto getUser(@RequestParam Long id) throws ExpertNotFoundException {
        return userMapper.mapToUserDto(service.getUser(id).orElseThrow(ExpertNotFoundException::new));
    }

    @RequestMapping(method = RequestMethod.POST, value = "createUser", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createUser(@RequestBody UserDto userDto) {
        service.saveUser(userMapper.mapToUser(userDto));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "deleteUserById")
    public void deleteUserById(@RequestParam Long id) {
        service.deleteUserById(id);
    }*/



}
