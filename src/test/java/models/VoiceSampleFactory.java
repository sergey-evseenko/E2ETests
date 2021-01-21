package models;

import com.github.javafaker.Faker;

public class VoiceSampleFactory {

    public static VoiceSample getVoiceSample(String voiceTalent, String language, String genre, String ageRange, String voiceRange, String characteristic, String sampleType, String fileName, String entryType) {
        Faker faker = new Faker();

        VoiceSample voiceSample = new VoiceSample(
                faker.name().title(),
                voiceTalent,
                language,
                genre,
                ageRange,
                voiceRange,
                characteristic,
                faker.name().title(),
                sampleType,
                System.getProperty("user.dir") + "/src/test/resources/Data/" + fileName,
                fileName,
                entryType,
                faker.name().title(),
                faker.name().title()
        );
        return voiceSample;
    }
}
