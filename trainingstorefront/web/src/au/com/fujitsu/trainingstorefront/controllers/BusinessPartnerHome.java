package au.com.fujitsu.trainingstorefront.controllers;

import au.com.fujitsu.trainingcore.model.BusinessPartnerModel;
import au.com.fujitsu.trainingcore.services.SapIntegrationService;
import au.com.fujitsu.trainingfacade.partnermanagement.BusinessPartnerFacade;
import au.com.fujitsu.trainingstorefront.forms.CustomerForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.validation.Valid;

@Controller
@RequestMapping(value = "/business-partner-home")
public class BusinessPartnerHome {

    @Resource
    private BusinessPartnerFacade businessPartnerFacade;

    @Resource
    private SapIntegrationService sapIntegrationService;

    @RequestMapping(method = RequestMethod.GET, value = "/create")
    public String create(CustomerForm customerForm, final Model model) {
        model.addAttribute("customerForm", new CustomerForm());
        return "newBusinessPartner";
    }


    @RequestMapping(method = RequestMethod.POST, value = "/create")
    public String doBusinessPartnerCreation(@Valid CustomerForm customerForm , final BindingResult bindingResult, final Model model) {

        if (bindingResult.hasErrors()) {
            return "newBusinessPartner";
        }

        String email = customerForm.getEmail();
        String name = customerForm.getName();
        int age = customerForm.getAge();

        boolean isCreated = businessPartnerFacade.createBusinessPartner(email, name, age);

        if (isCreated) {
            model.addAttribute("email", email);
            model.addAttribute("name", name);

            BusinessPartnerModel example = new BusinessPartnerModel();
            example.setName(name);
            example.setAge(age);
            example.setEmail(email);

            String result = sapIntegrationService.post2SAP(example);

            model.addAttribute("sapResponse", result);

            return "businessPartnerCreatedSuccess";
        } else {
            model.addAttribute("serviceError", "Something Wrong, much likely the user email has been used already!!!");
        }

        return "newBusinessPartner";
    }
}
