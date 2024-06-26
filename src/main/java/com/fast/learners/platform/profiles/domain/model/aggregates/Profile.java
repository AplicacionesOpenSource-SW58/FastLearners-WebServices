package com.fast.learners.platform.profiles.domain.model.aggregates;
import com.fast.learners.platform.profiles.domain.model.commands.CreateProfileCommand;
import com.fast.learners.platform.profiles.domain.model.valueobjects.EmailAddress;
import com.fast.learners.platform.profiles.domain.model.valueobjects.Membership;
import com.fast.learners.platform.profiles.domain.model.valueobjects.PersonName;
import com.fast.learners.platform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;

@Entity
@Table(name = "profiles")
public class Profile extends AuditableAbstractAggregateRoot<Profile> {


    private PersonName name;

    @Embedded
    private Membership membership;
    private EmailAddress email;
    private String password;

    public Profile(String firstName, String middleName, String lastName, String email, String password, String membership) {
        this.name = new PersonName(firstName, middleName, lastName);
        this.email = new EmailAddress(email);
        this.membership = new Membership(membership);
    }

    public Profile(CreateProfileCommand command) {
        this.name = new PersonName(command.firstName(), command.middleName(), command.lastName());
        this.email = new EmailAddress(command.email());
        this.password = command.password();
        this.membership = new Membership(command.membership());
    }

    public Profile() {
    }

    public void updateName(String firstName, String middleName, String lastName) {
        this.name = new PersonName(firstName, middleName, lastName);
    }

    public void updateEmail(String email) {
        this.email = new EmailAddress(email);
    }

    public void updateMembership(Membership newMembership) {
        this.membership = newMembership;
    }

    public void updatePassword(String newPassword) {
        this.password = newPassword;
    }

    public String getFullName() {
        return name.getFullName();
    }

    public String getEmailAddress() {
        return email.getEmail();
    }

    public String getMembership() {
        return this.membership.getMembership();
    }
}
