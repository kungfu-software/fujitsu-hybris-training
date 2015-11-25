package au.com.fujitsu.trainingstorefront.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/home")
public class Home {

    @RequestMapping(method = RequestMethod.GET)
    public String home() {
        return "home";
    }
}
