package models;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class VoiceSample {
    String title;
    String voiceTalent;
    String language;
    String genre;
    String ageRange;
    String voiceRange;
    String characteristic;
    String internalNote;
    String sampleType;
    String filePath;
    String entryType;
    String projectTitle;
    String roleName;
}
