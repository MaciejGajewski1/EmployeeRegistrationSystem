package com.emplregsys.ers.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ContactDetailsDto {
    private String phoneNumber;
    private String email;
    private String street;
    private String postalCode;
    private String city;

    public ContactDetails toContactDetails() {
        ContactDetails contactDetails = new ContactDetails();
        contactDetails.setPhoneNumber(this.phoneNumber);
        contactDetails.setEmail(this.email);
        contactDetails.setStreet(this.street);
        contactDetails.setPostalCode(this.postalCode);
        contactDetails.setCity(this.city);
        return contactDetails;
    }
}
