package seedu.address.model.person;

import java.util.Collections;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.tag.Tag;

/**
 * Represents a Student in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Student extends Person {

    private final Set<Subject> subjects; // Subject the student is associated with
    private final Set<String> classes; // Classes the student attends

    /**
     * Every field must be present and not null.
     *
     * @param name     Name of the student
     * @param phone    Phone number of the student
     * @param email    Email of the student
     * @param address  Address of the student
     * @param tags     Tags associated with the student
     * @param subjects  Subject the student is taking
     * @param classes  Set of class names the student is attending
     */
    public Student(Name name, Phone phone, Email email, Address address, Set<Tag> tags,
                   Set<Subject> subjects, Set<String> classes) {
        super(name, phone, email, address, tags);
        Objects.requireNonNull(subjects, "Subject must not be null");
        Objects.requireNonNull(classes, "Classes must not be null");
        this.subjects = subjects;
        this.classes = classes;
    }

    public Set<Subject> getSubjects() {
        return Collections.unmodifiableSet(subjects);
    }

    public Set<String> getClasses() {
        return classes;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Student: ")
                .append(getName())
                .append("; Phone: ")
                .append(getPhone())
                .append("; Email: ")
                .append(getEmail())
                .append("; Address: ")
                .append(getAddress())
                .append("; Subject: ")
                .append(getSubjects())
                .append("; Classes: ")
                .append(String.join(", ", classes));

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(tag -> builder.append(tag).append(" "));
        }
        return builder.toString();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Student)) {
            return false;
        }

        Student otherStudent = (Student) other;
        return otherStudent.getName().equals(getName())
                && otherStudent.getPhone().equals(getPhone())
                && otherStudent.getEmail().equals(getEmail())
                && otherStudent.getAddress().equals(getAddress())
                && otherStudent.getSubjects().equals(getSubjects())
                && otherStudent.getClasses().equals(getClasses())
                && otherStudent.getTags().equals(getTags());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getPhone(), getEmail(), getAddress(), getSubjects(), getClasses(), getTags());
    }
}
