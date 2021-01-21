package models;

import com.github.javafaker.Faker;


public class VoiceTalentFactory {

    public static VoiceTalent getVoiceTalent(String language, String countryCode, String country, String status, String dateOfBirth) {
        Faker faker = new Faker();

        VoiceTalent voiceTalent = new VoiceTalent(
                faker.name().firstName(),
                faker.name().lastName(),
                language,
                dateOfBirth,
                countryCode,
                faker.number().digits(10),
                faker.internet().emailAddress(),
                country,
                status
        );
        return voiceTalent;
    }
}
