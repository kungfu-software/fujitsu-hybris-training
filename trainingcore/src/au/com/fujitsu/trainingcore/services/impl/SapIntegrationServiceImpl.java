package au.com.fujitsu.trainingcore.services.impl;

import au.com.fujitsu.trainingcore.services.JsonComposer;
import au.com.fujitsu.trainingcore.services.SapIntegrationService;
import org.apache.commons.codec.binary.Base64;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

/**
 * Created by seabookchen on 19/05/15.
 */
public class SapIntegrationServiceImpl implements SapIntegrationService {
    private static final String CSRFTOKEN_FETCH_URL = "http://falsapcrm01.bassap.au.fjanz.com:8000/sap/opu/odata/sap/ZDHS_CRM_HYBRIS_POC_SRV/";

    private CloseableHttpClient httpClient = HttpClients.createDefault();

    private String endPointUrl;
    private JsonComposer jsonComposer;

    @Override
    public String post2SAP(Object object) {
        return post2SAP(object, getEndPointUrl(), getJsonComposer());
    }

    public String post2SAP(Object object, String endPointUrl, JsonComposer jsonComposer) {
        HttpPost postRequest = new HttpPost(endPointUrl);

        HttpGet csrfTokenRequest = new HttpGet(CSRFTOKEN_FETCH_URL);
        csrfTokenRequest.setHeader("X-CSRF-Token", "Fetch");
        csrfTokenRequest.setHeader("Authorization", getAuthorizationHeader());

        CloseableHttpResponse csrfTokenRequestResponse = null;
        CloseableHttpResponse newCustomerResponse = null;
        String csrfToken = "";
        StringEntity input = null;

        BufferedReader br = null;
        StringBuilder result = new StringBuilder();
        try {
            csrfTokenRequestResponse = httpClient.execute(csrfTokenRequest);
            org.apache.http.Header[] headers = csrfTokenRequestResponse.getAllHeaders();

            List<Header> headerList = Arrays.asList(headers);
            for (Header header:headerList) {
                if ("x-csrf-token".equalsIgnoreCase(header.getName())) {
                    csrfToken = header.getValue();
                    break;
                }
            }

            postRequest.setHeader("x-csrf-token", csrfToken);

            input = new StringEntity(jsonComposer.composeJson(object));
            input.setContentType("application/json");
            postRequest.setEntity(input);

            newCustomerResponse = httpClient.execute(postRequest);
            HttpEntity entity = newCustomerResponse.getEntity();

            br = new BufferedReader(new InputStreamReader(entity.getContent()));
            String line = null;
            while ((line = br.readLine()) != null) {
                result.append(line);
            }
            EntityUtils.consume(entity);

        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

            if (csrfTokenRequestResponse != null) {
                try {
                    csrfTokenRequestResponse.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (newCustomerResponse != null) {
                try {
                    newCustomerResponse.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }

        return result.toString();

    }

    private String getAuthorizationHeader() {
        String username="ChenSeabrook";
        String password="Welcome123";
        String usernamePassword = username + ":" + password;


        String encoding = new String(Base64.encodeBase64(usernamePassword.getBytes()));
        return "Basic " + encoding;
    }

    public String getEndPointUrl() {
        return endPointUrl;
    }

    public void setEndPointUrl(String endPointUrl) {
        this.endPointUrl = endPointUrl;
    }

    public JsonComposer getJsonComposer() {
        return jsonComposer;
    }

    public void setJsonComposer(JsonComposer jsonComposer) {
        this.jsonComposer = jsonComposer;
    }
}
