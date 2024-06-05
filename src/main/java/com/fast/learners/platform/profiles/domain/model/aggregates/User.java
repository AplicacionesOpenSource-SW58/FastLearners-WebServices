package com.fast.learners.platform.profiles.domain.model.aggregates;
import com.fast.learners.platform.profiles.domain.model.commands.CreateUserCommand;
import com.fast.learners.platform.profiles.domain.model.valueobjects.EmailAddress;
import com.fast.learners.platform.profiles.domain.model.valueobjects.PersonName;
import com.fast.learners.platform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;

@Entity
public class User extends AuditableAbstractAggregateRoot<User>{

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "firstName", column = @Column(name = "pepe")),
            @AttributeOverride(name = "middleName", column = @Column(name = "pepo")),
            @AttributeOverride(name = "lastName", column = @Column(name = "perez")),
    })
    private PersonName name;

    @Embedded
    EmailAddress email;
    private String password;
    private Membership membership;

    public User(String firstName, String middleName, String lastName, String email) {
        this.name = new PersonName(firstName, middleName, lastName);
        this.email = new EmailAddress(email);
    }

    public User(CreateUserCommand command) {
        this.name = new PersonName(command.firstName(), command.middleName(), command.lastName());
        this.email = new EmailAddress(command.email());
    }

    public User() {
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

    // Change password method
    public void updatePassword(String newPassword) {
        this.password = newPassword;
    }

    public String getFullName() {
        return name.getFullName();
    }

    public String getEmailAddress() {
        return email.address();
    }



}