package models;

import com.github.javafaker.Faker;

import java.util.Random;


public class VoiceTalentFactory {

    public static VoiceTalent getVoiceTalent() {
        Faker faker = new Faker();
        Random random = new Random();
        String[] language = new String[]{"Abkhazian", "Afar", "Afrikaans", "Albanian", "Amharic"};
        String[] countryCode = new String[]{"Afghanistan (93)", "Albania (355)", "Algeria (213)", "American Samoa (1-684)", "Andorra (376)"};
        String[] country = new String[]{"Amsterdam", "Bangkok", "Beirut", "Bengaluru", "Bergen"};
        String[] status = new String[]{"Active", "Inactive", "Pending"};
        String[] dateOfBirth = new String[]{"January 01 2001", "February 02 2002", "March 03 2003", "April 04 2004", "May 05 2005"};

        VoiceTalent voiceTalent = new VoiceTalent(
                faker.name().firstName(),
                faker.name().lastName(),
                language[random.nextInt(language.length)],
                dateOfBirth[random.nextInt(dateOfBirth.length)],
                countryCode[random.nextInt(countryCode.length)],
                faker.number().digits(10),
                faker.internet().emailAddress(),
                country[random.nextInt(country.length)],
                status[random.nextInt(status.length)]
        );
        return voiceTalent;
    }
}
