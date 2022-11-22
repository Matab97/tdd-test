package banking;

import java.util.Objects;

/**
 * The concrete Account holder of Person type.
 */
public class Person extends AccountHolder{
    private String firstName;
    private String lastName;

    public Person(String firstName, String lastName, int idNumber) {
        super(idNumber);
        this.firstName =firstName ;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public boolean isSame(Person person)
    {
        return person.getIdNumber() == this.getIdNumber() &&
                Objects.equals(person.getFirstName(), this.firstName) &&
                Objects.equals(person.getLastName(), this.lastName);
    }

}
