package models;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class VoiceTalent {
    String firstName;
    String lastName;
    String primaryLanguage;
    String dateOfBirthMonth;
    String countryCode;
    String contactNumber;
    String messengerType;
    String messengerId;
    String email;
    String office;
    String status;
}
