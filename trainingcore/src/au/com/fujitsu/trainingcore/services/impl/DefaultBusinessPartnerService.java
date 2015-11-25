package au.com.fujitsu.trainingcore.services.impl;

import au.com.fujitsu.trainingcore.model.BusinessPartnerModel;
import au.com.fujitsu.trainingcore.services.BusinessPartnerService;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import org.apache.commons.lang.StringUtils;
import org.springframework.util.Assert;

import javax.annotation.Resource;


public class DefaultBusinessPartnerService implements BusinessPartnerService {

    @Resource
    private FlexibleSearchService flexibleSearchService;

    @Resource
    private ModelService modelService;

    @Override
    public void createBusinessPartner(String email, String name, int age) {
        Assert.notNull(email, "Email can not be empty");
        Assert.notNull(name, "Name can not be empty");
        Assert.isTrue(age >= 0, "Age should be greater than 0");

        BusinessPartnerModel businessPartner = new BusinessPartnerModel();
        businessPartner.setEmail(email);
        businessPartner.setName(name);
        businessPartner.setAge(age);

        modelService.save(businessPartner);
    }

    @Override
    public void updateBusinessPartner(String email, String name, int age) {
        Assert.notNull(email, "Email can not be empty");
        Assert.notNull(name, "Name can not be empty");
        Assert.isTrue(age >= 0, "Age should be greater than 0");

        BusinessPartnerModel businessPartner = new BusinessPartnerModel();
        businessPartner.setEmail(email);

        BusinessPartnerModel existingModel = flexibleSearchService.getModelByExample(businessPartner);

        existingModel.setName(name);
        existingModel.setAge(age);

        modelService.save(existingModel);
    }

    @Override
    public void deleteBusinessPartner(String email) {
        Assert.notNull(email, "Email can not be empty");

        BusinessPartnerModel businessPartner = new BusinessPartnerModel();
        businessPartner.setEmail(email);

        BusinessPartnerModel existingModel = flexibleSearchService.getModelByExample(businessPartner);

        modelService.remove(existingModel);

    }

    @Override
    public BusinessPartnerModel findBusinessPartner(String email) {
        BusinessPartnerModel businessPartner = new BusinessPartnerModel();
        businessPartner.setEmail(email);

        BusinessPartnerModel existingModel = flexibleSearchService.getModelByExample(businessPartner);

        return existingModel;
    }
}
