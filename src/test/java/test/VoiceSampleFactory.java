package test;

import com.github.javafaker.Faker;
import models.VoiceSample;

import java.io.File;

public class VoiceSampleFactory {

    public static VoiceSample getVoiceSample(String voiceTalent, String language, String genre, String ageRange, String voiceRange, String characteristic, String sampleType, String entryType) {
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
                System.getProperty("user.dir") + File.separator + "src" + File.separator + "test" + File.separator + "resources" + File.separator + "Data" + File.separator + "QA.mp3",
                entryType,
                faker.name().title(),
                faker.name().title()
        );
        return voiceSample;
    }
}
