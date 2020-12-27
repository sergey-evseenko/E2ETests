package models;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class VoiceTalent {
    String firstName;
    String lastName;
    String primaryLanguage;
    String dateOfBirth;
    String countryCode;
    String contactNumber;
    String email;
    String office;
    String status;
}
