package test;

import com.github.javafaker.Faker;
import models.VoiceSample;

public class VoiceSampleFactory {

    public static VoiceSample getVoiceSample(String voiceTalent, String language, String genre, String ageRange, String voiceRange, String characteristic, String sampleType, String filePath, String entryType) {
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
                filePath,
                entryType,
                faker.name().title(),
                faker.name().title()
        );
        return voiceSample;
    }
}
