package au.com.fujitsu.trainingcore.services;

import au.com.fujitsu.trainingcore.model.BusinessPartnerModel;

/**
 * Created by seabookchen on 11/06/15.
 */
public interface BusinessPartnerService {

    void createBusinessPartner(String email, String name, int age);

    void updateBusinessPartner(String email, String name, int age);

    void deleteBusinessPartner(String email);

    BusinessPartnerModel findBusinessPartner(String email);
}
