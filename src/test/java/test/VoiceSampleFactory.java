package test;

import models.VoiceSample;
import org.fluttercode.datafactory.impl.DataFactory;

public class VoiceSampleFactory {

    public static VoiceSample getVoiceSample() {
        DataFactory df = new DataFactory();
        VoiceSample voiceSample = new VoiceSample(
                df.getRandomText(10),
                "3 Germane Obrien",
                "Russian",
                df.getRandomText(100),
                "src/test/resources/QA.mp3"
        );
        return voiceSample;
    }
}
