package models;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class VoiceSample {
    String title;
    String voiceTalent;
    String language;
    String internalNote;
    String filePath;
}
