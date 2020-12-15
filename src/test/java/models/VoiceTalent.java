package models;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class VoiceTalent {
    String firstName;
    String lastName;
    String email;
    String messengerId;
    String contactNumber;
    String countryCode;
    String primaryLanguage;
}
