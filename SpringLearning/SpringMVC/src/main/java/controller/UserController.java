package controller;

import model.UserEntity;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import service.UserService;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;


@Controller
@RequestMapping("/user")
public class UserController {
    /*  get an object which is used to write log  */
    private static final Log loger = LogFactory.getLog(UserController.class);

    @Autowired
    private UserService userService;

    /**
     * Handling Login event
     * Using object UserForm (entity bean) user to receive the args that registration page sends
     * */
    @RequestMapping("/login")
    public String Login(@ModelAttribute UserEntity user, HttpSession session,Model model){
        // judge whether user input is empty
        if(user.getUserid()== 0 || user.getPassword()== null){
            // log the error message
            loger.info("empty error");
            // use model to record the message
            model.addAttribute("tipMessage","username or password could not be empty");
            // send user to view:login.jsp
            return "login";
        }else{ // when userid and password is not empty
            Map<String, Object> userMap = new HashMap<>();
            // put user information into an HashMap
            userMap.put("userid",user.getUserid());
            userMap.put("password",user.getPassword());
            // take advantage of userService to handle userLogin event
            UserEntity userEntity = userService.userLogin(userMap);
            // when userid and password is correct
            if(userEntity !=null){
                // using session to record userEntity
                session.setAttribute("user",userEntity);
                // log the success message
                loger.info("success");
                // send user to view:main.jsp
                return "main";
            }else{ // when either userid or password is wrong
                // log the error message
                loger.info("input error");
                // use model to record the message
                model.addAttribute("errorMessage","username or password is incorrect");
                return "login";
            }
        }
    }

    /**
     * Handling Registration
     * Using object UserForm (entity bean) user to receive the args that registration page sends
     * */
    @RequestMapping("/register")
    public String register(@ModelAttribute UserEntity userEntity, Model model){
        return "login";
    }
}
