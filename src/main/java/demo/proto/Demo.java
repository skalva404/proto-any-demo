package demo.proto;

import com.google.protobuf.Any;
import com.google.protobuf.InvalidProtocolBufferException;
import demo.proto.AddressBookProtos.Person;

public class Demo {

    public static void main(String[] args) throws InvalidProtocolBufferException {

        Person.PhoneNumber.Builder dummy = Person.PhoneNumber.newBuilder()
                .setNumber("555-4321")
                .setType(Person.PhoneType.HOME);
        Any any = Any.pack(dummy.build());


        Person john =
                Person.newBuilder()
                        .setId(1234)
                        .setName("John Doe")
                        .setEmail("jdoe@example.com")
                        .setAddress(any)
                        .addPhones(
                                Person.PhoneNumber.newBuilder()
                                        .setNumber("555-4321")
                                        .setType(Person.PhoneType.HOME))
                        .build();


        byte[] bytes = john.toByteArray();
        Person person = Person.parseFrom(bytes);
        System.out.println(person);

        Any address = person.getAddress();
        Person.PhoneNumber unpack = address.unpack(Person.PhoneNumber.class);
        System.out.println(unpack);
    }
}
