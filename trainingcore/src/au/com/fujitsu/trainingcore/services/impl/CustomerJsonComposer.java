package au.com.fujitsu.trainingcore.services.impl;

import au.com.fujitsu.trainingcore.model.BusinessPartnerModel;
import au.com.fujitsu.trainingcore.services.JsonComposer;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CustomerJsonComposer implements JsonComposer {
    private  String jsonFormat = "{\"d\" : {\"PartnerID\" : \"%s\", \"Name\" : \"%s\", \"Gender\" : \"%s\", \"Email\" : \"%s\", \"Income\" : \"%s\", \"DateOfBirth\" : \"%s\"} }";



    @Override
    public String composeJson(Object obj){

        BusinessPartnerModel businessPartner = (BusinessPartnerModel)obj;

        return String.format(jsonFormat, "", businessPartner.getName(), "Male", businessPartner.getEmail(), "1000", "10.10.2000");
    }

    private String extractValue(String patternStr, String xml) {
        Pattern pattern = Pattern.compile(patternStr);
        Matcher matcher = pattern.matcher(xml);
        if (matcher.find())
        {
            return matcher.group(1);
        } else {
            return "";
        }
    }
}