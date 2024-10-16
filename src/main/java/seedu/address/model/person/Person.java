package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

import seedu.address.commons.util.ToStringBuilder;

/**
 * Represents a Person in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Person implements Comparable<Person> {

    // Identity fields
    private final Name name;
    private final Phone phone;
    private final Email email;

    // Data fields
    private final Address address;

    private final TelegramUsername telegramUsername;

    /**
     * Every field must be present and not null
     */
    public Person(Name name, Phone phone, Email email, Address address,
                  TelegramUsername telegramUsername) {
        requireAllNonNull(name, phone, email, address, telegramUsername);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.telegramUsername = telegramUsername;
    }

    public Name getName() {
        return name;
    }

    public Phone getPhone() {
        return phone;
    }

    public Email getEmail() {
        return email;
    }

    public Address getAddress() {
        return address;
    }
    public TelegramUsername getTelegramUsername() {
        return telegramUsername;
    }

    /**
     * Returns true if both persons have the same phone number or email or telegram handle.
     * If either one or both has no telegram handle, then ignore telegram handle.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSamePerson(Person otherPerson) {
        if (otherPerson == this) {
            return true;
        }

        if (otherPerson == null) {
            return false;
        }

        // If either party has no telegram username, then just check for uniqueness in phone and email
        if (!getTelegramUsername().hasUsername() || !otherPerson.getTelegramUsername().hasUsername()) {
            return otherPerson.getPhone().equals(getPhone())
                    || otherPerson.getEmail().equals(getEmail());
        }

        // Otherwise, check that phone, email and telegram username are all unique
        return otherPerson.getPhone().equals(getPhone())
                || otherPerson.getEmail().equals(getEmail())
                || otherPerson.getTelegramUsername().equals(getTelegramUsername());
    }

    /**
     * Returns true if both persons have the same phone number.
     */
    public boolean isSamePhone(Person otherPerson) {
        if (otherPerson == this) {
            return true;
        }

        return otherPerson != null && otherPerson.getPhone().equals(getPhone());
    }

    /**
     * Returns true if both persons have the same email.
     */
    public boolean isSameEmail(Person otherPerson) {
        if (otherPerson == this) {
            return true;
        }

        return otherPerson != null && otherPerson.getEmail().equals(getEmail());
    }

    /**
     * Returns true if both persons have the same identity and data fields.
     * This defines a stronger notion of equality between two persons.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Person)) {
            return false;
        }

        Person otherPerson = (Person) other;
        return name.equals(otherPerson.name)
                && phone.equals(otherPerson.phone)
                && email.equals(otherPerson.email)
                && address.equals(otherPerson.address)
                && telegramUsername.equals(otherPerson.telegramUsername);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, address);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("name", name)
                .add("phone", phone)
                .add("email", email)
                .add("address", address)
                .add("telegram", telegramUsername)
                .toString();
    }

    @Override
    public int compareTo(Person other) {
        return this.getName().compareTo(other.getName());
    }
}
