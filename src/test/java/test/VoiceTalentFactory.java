package test;

import com.github.javafaker.Faker;
import models.VoiceTalent;
import org.fluttercode.datafactory.impl.DataFactory;


public class VoiceTalentFactory {

    public static VoiceTalent getVoiceTalent() {
        DataFactory df = new DataFactory();
        Faker faker = new Faker();
        String[] languages = {"Russian", "English", "German"};
        String[] months = {"June", "July", "March"};
        String[] countryCodes = {"BELARUS (375)", "ALBANIA (355)", "GERMANY (49)"};
        String[] messengers = {"Skype", "Line", "WeChat"};
        String[] countries = {"Berlin", "Beirut", "Amasterdam"};
        String[] statuses = {"Active", "Inactive", "Pending"};


        VoiceTalent voiceTalent = new VoiceTalent(
                faker.name().firstName(),
                faker.name().lastName(),
                df.getItem(languages),
                df.getItem(months),
                df.getItem(countryCodes),
                faker.number().digits(10),
                df.getItem(messengers),
                faker.name().username(),
                faker.internet().emailAddress(),
                df.getItem(countries),
                df.getItem(statuses)
        );
        return voiceTalent;
    }
}
