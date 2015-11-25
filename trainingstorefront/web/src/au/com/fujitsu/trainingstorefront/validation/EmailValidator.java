package au.com.fujitsu.trainingstorefront.validation;

import java.util.regex.Pattern;

import au.com.fujitsu.trainingstorefront.forms.CustomerForm;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;


/**
 * Validator for Emails
 */
@Component("emailValidator")
public class EmailValidator implements Validator
{

    public static final String EMAIL_REGEX = "\\b[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}\\b";

    @Override
    public boolean supports(final Class<?> aClass)
    {
        return CustomerForm.class.equals(aClass);
    }

    @Override
    public void validate(final Object object, final Errors errors)
    {
        final CustomerForm updateEmailForm = (CustomerForm) object;
        final String email = updateEmailForm.getEmail();


        if (StringUtils.isEmpty(email))
        {
            errors.rejectValue("email", "profile.email.invalid");
        }
        else if (StringUtils.length(email) > 255 || !Pattern.matches(EMAIL_REGEX, email))
        {
            errors.rejectValue("email", "profile.email.invalid");
        }

    }
}
