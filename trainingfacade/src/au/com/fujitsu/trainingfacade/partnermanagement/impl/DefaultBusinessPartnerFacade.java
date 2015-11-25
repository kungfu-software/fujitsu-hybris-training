package au.com.fujitsu.trainingfacade.partnermanagement.impl;

import au.com.fujitsu.trainingcore.model.BusinessPartnerModel;
import au.com.fujitsu.trainingcore.services.BusinessPartnerService;
import au.com.fujitsu.trainingfacade.partnermanagement.BusinessPartnerFacade;
import org.springframework.util.Assert;

import javax.annotation.Resource;

public class DefaultBusinessPartnerFacade implements BusinessPartnerFacade {

    @Resource
    private BusinessPartnerService businessPartnerService;

    @Override
    public int nextYearBusinessPartnerBirthdayAge(String email) {

        BusinessPartnerModel businessPartner = businessPartnerService.findBusinessPartner(email);

        int age = businessPartner.getAge() + 1;
        businessPartnerService.updateBusinessPartner(email, businessPartner.getName(), age);

        return age;
    }

    @Override
    public boolean sendBusinessPartnerEmail(String email, String emailBody) {

        // This is for demo purpose

        return true;
    }

    @Override
    public boolean createBusinessPartner(String email, String name, int age) {
        Assert.notNull(email, "Email cannot be empty");
        Assert.notNull(name, "Name cannot be empty");
        Assert.isTrue(age >= 0, "Age needs to be greater than 0");

        try {
            businessPartnerService.createBusinessPartner(email, name, age);
        } catch (Exception e) {
            return false;
        }


        return true;
    }
}
