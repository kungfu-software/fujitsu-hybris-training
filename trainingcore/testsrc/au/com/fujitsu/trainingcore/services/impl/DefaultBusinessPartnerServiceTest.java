package au.com.fujitsu.trainingcore.services.impl;


import au.com.fujitsu.trainingcore.model.BusinessPartnerModel;
import au.com.fujitsu.trainingcore.services.BusinessPartnerService;
import de.hybris.bootstrap.annotations.IntegrationTest;
import de.hybris.platform.servicelayer.ServicelayerTest;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


import javax.annotation.Resource;

@IntegrationTest
public class DefaultBusinessPartnerServiceTest extends ServicelayerTest {
    private static final Logger LOG = LoggerFactory.getLogger(DefaultBusinessPartnerServiceTest.class);

    @Resource
    private BusinessPartnerService businessPartnerService;


    @Test
    public void testCreateBusinessPartner() {
        LOG.info("This is test create Business partner");

        businessPartnerService.createBusinessPartner("test@test1.com", "tester", 10);
        BusinessPartnerModel businessPartner = businessPartnerService.findBusinessPartner("test@test1.com");

        assertThat("tester", equalTo(businessPartner.getName()));
    }

}
