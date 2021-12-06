//package org.example.TelegramProject.controller;
//
//import org.example.TelegramProject.model.UserProfileData;
//import org.example.TelegramProject.repository.UserProfileDataRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//@Controller
//@RequestMapping(path="/")
//public class JPAController {
//    private final UserProfileDataRepository userRepository;
//    UserProfileData user;
//
//    public JPAController(UserProfileDataRepository userRepository) {
//        this.userRepository = userRepository;
//    }
//
//    public @ResponseBody  String saveNewUser () {
//
//        userRepository.save(user);
//        return "Saved";
//    }
//
//    @GetMapping(path="/all")
//    public @ResponseBody Iterable<UserProfileData> getAllUsers() {
//
//        return userRepository.findAll();
//    }
//}
