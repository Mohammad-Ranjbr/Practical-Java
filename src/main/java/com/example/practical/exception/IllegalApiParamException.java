package com.example.practical.exception;

import java.io.Serial;

public class IllegalApiParamException extends RuntimeException {

    // Serialization means converting a Java object into a byte stream, so that:
    // you can store it (for example, in a file or database), or send it over the network (for example, from a client to a server or between microservices).
    // And deserialization means the opposite: reconstructing the object from the bytes.
    // Now what is the role of serialVersionUID?
    // When serializing, Java stores a version number (the same serialVersionUID) with the object. If the class structure changes later (for example, a field is added/removed), Java checks:
    // If the version number is different, it throws an InvalidClassException error.
    // So: serialVersionUID guarantees that the version of the stored object is compatible with the current class.
    // From Java 14 onwards, if you include a field called serialVersionUID in the class, it is better to use the @Serial annotation to indicate that this field is for serialization.
    // Why is it used in Exception?
    // In practice, we usually don't serialize exceptions. But since RuntimeException itself is Serializable, for completeness, custom exception classes should also have a serialVersionUID — even if it's not used.

    @Serial
    private static final long serialVersionUID = 1L;

    public IllegalApiParamException(String message) {
        super(message);
    }

}
