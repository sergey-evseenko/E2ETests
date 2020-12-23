package test;

import models.VoiceTalent;
import org.fluttercode.datafactory.impl.DataFactory;


public class VoiceTalentFactory {

    public static VoiceTalent getVoiceTalent() {
        DataFactory df = new DataFactory();
        VoiceTalent voiceTalent = new VoiceTalent(
                df.getFirstName(),
                df.getLastName(),
                df.getEmailAddress(),
                df.getRandomWord(),
                df.getNumberText(10),
                "AD 376",
                "Russian"
        );
        return voiceTalent;
    }
}
