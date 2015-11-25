package au.com.fujitsu.trainingfacade.partnermanagement;

/**
 * Created by seabookchen on 11/06/15.
 */
public interface BusinessPartnerFacade {
    int nextYearBusinessPartnerBirthdayAge(String email);

    boolean sendBusinessPartnerEmail(String email, String emailBody);

    boolean createBusinessPartner(String email, String name, int age);
}
