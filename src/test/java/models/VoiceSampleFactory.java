package models;

import com.github.javafaker.Faker;

import java.util.Random;

public class VoiceSampleFactory {

    public static VoiceSample getVoiceSample(String fileName) {
        Faker faker = new Faker();
        Random random = new Random();
        String[] language = new String[]{"Abkhazian", "Afar", "Afrikaans", "Albanian", "Amharic"};
        String[] genre = new String[]{"Animation", "Documentary", "Live Action", "Music/Vocals", "Pre-school Animation"};
        String[] ageRange = new String[]{"0-12", "13-17", "18-29", "30-40", "41-50"};
        String[] voiceRange = new String[]{"High-Pitched", "Low-Pitched", "Middle-Pitched"};
        String[] characteristic = new String[]{"Airy", "Animated", "Articulated", "Breathy", "Edgy"};
        String[] sampleType = new String[]{"Acting", "Song"};

        VoiceSample voiceSample = new VoiceSample(
                faker.name().title(),
                "John Doe",
                language[random.nextInt(language.length)],
                genre[random.nextInt(genre.length)],
                ageRange[random.nextInt(ageRange.length)],
                voiceRange[random.nextInt(voiceRange.length)],
                characteristic[random.nextInt(characteristic.length)],
                faker.name().title(),
                sampleType[random.nextInt(sampleType.length)],
                System.getProperty("user.dir") + "/src/test/resources/Data/" + fileName,
                fileName,
                faker.internet().url(),
                faker.name().title(),
                faker.name().title()
        );
        return voiceSample;
    }
}
