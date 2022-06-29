package org.design.patterns.validator;


import org.design.patterns.validator.model.Person;
import org.design.patterns.validator.util.MyValidator;
import org.design.patterns.validator.util.Validator;

public class PlayWithMyValidator {

	public static void main(String[] args) {
		Person sarah = new Person("Sarah", 29);
		Person james = new Person(null, 29);
		Person mary = new Person("Mary", -10);
		Person john = new Person("John", 1_000);
		Person linda = new Person(null, 1_000);

		// validate() method is a static method which should return a validator, which should have a on() method
		sarah = MyValidator.validate(p -> p.getName() != null, "The name should not be null")
				// This method should take a person as argument and return a Supplier like object
				.on(sarah)
				// This method does not take any parameter and returns normally a person,
				.validate();
		System.out.println("Sarah: " + sarah);

//		mary = MyValidator.validate(p -> p.getName() != null, "The name should not be null")
//				.thenValidate(p -> p.getAge() > 0 , "The age should be greater than zero")
//				.thenValidate(p -> p.getAge() < 150 , "The age should be lesser than 150")
//				// This method should take a person as argument and return a Supplier like object
//				.on(mary)
//				// This method does not take any parameter and returns normally a person,
//				.validate();
//		System.out.println("Mary: " + mary);

//		john = MyValidator.validate(p -> p.getName() != null, "The name should not be null")
//				.thenValidate(p -> p.getAge() > 0 , "The age should be greater than zero")
//				.thenValidate(p -> p.getAge() < 150 , "The age should be lesser than 150")
//				// This method should take a person as argument and return a Supplier like object
//				.on(john)
//				// This method does not take any parameter and returns normally a person,
//				.validate();
//		System.out.println("John: " + john);

		linda = MyValidator.validate(p -> p.getName() != null, "The name should not be null")
				.thenValidate(p -> p.getAge() > 0 , "The age should be greater than zero")
				.thenValidate(p -> p.getAge() < 150 , "The age should be lesser than 150")
				// This method should take a person as argument and return a Supplier like object
				.on(linda)
				// This method does not take any parameter and returns normally a person,
				.validate();
		System.out.println("Linda: " + linda);
	}
}
