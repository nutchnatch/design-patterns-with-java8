package org.design.patterns.validator.util;

import org.design.patterns.validator.model.Person;

import java.util.function.Predicate;
import java.util.function.Supplier;

public interface MyValidator {

    ValidatorSupplier on(Person person);

    static MyValidator validate(Predicate<Person> predicate, String errorMessage) {
        return p -> {
            if(predicate.test(p)) {
                return () -> p;
            } else {
                return () -> {
                    MyValidationException exception = new MyValidationException("The object is not valid");
                    exception.addSuppressed(new IllegalArgumentException(errorMessage));
                    throw exception;
                };
            }
        };
    }

    /**
     * Is declared as default, since it is supposed to be called from the fluent call and not specifically
     * from the interface reference. Otherwise, it should be called only by the interface specifically:
     * ex: MyValidator.thenValidate()  --> is invalid (should be called from an instance of the interface)
     * ex: MyValidator.validate()  --> is invalid (since validate() is a static method)
     * MyValidator validator; validator.thenValidate() --> valid
     * @param predicate
     * @param errorMessage
     * @return
     */
    default MyValidator thenValidate(Predicate<Person> predicate, String errorMessage) {
        return p -> {
            try {
                on(p).validate();
                if(predicate.test(p)) {
                    return () -> p;
                } else {
                    // If the predicate is not ok, then create a new exception
                    return () -> {
                        MyValidationException exception = new MyValidationException("The object is not valid");
                        exception.addSuppressed(new IllegalArgumentException(errorMessage));
                        throw exception;
                    };
                }
            } catch(MyValidationException exception) {
                if(predicate.test(p)) { // It means that previous validator is not ok
                    return () -> { throw exception; };
                } else {
                    exception.addSuppressed(new IllegalArgumentException(errorMessage));
                    return () -> { throw exception; };
                }
            }
        };
    }

    interface ValidatorSupplier extends Supplier<Person> {
        default Person validate() {
            return get();
        }
    }

    class MyValidationException extends RuntimeException {
        public MyValidationException(String errorMessage) {
            super(errorMessage);
        }
    }
}
