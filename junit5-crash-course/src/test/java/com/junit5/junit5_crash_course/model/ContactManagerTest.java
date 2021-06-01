package com.junit5.junit5_crash_course.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.condition.DisabledOnOs;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@TestInstance(value = TestInstance.Lifecycle.PER_CLASS)
class ContactManagerTest {
	ContactManager manager = null;

	@BeforeAll
	public void setUp() {
		System.out.println("Should print before All Test");
	}

	@BeforeEach
	public void teartUp() {
		manager = new ContactManager();
	}

	@Test
	public void shouldCreateContact() {
		// ContactManager manager = new ContactManager();
		manager.addContact("Vitthal", "Jadhav", "0123456789");
		Assertions.assertFalse(manager.getAllContacts().isEmpty());
		Assertions.assertEquals(1, manager.getAllContacts().size());

		Assertions.assertTrue(manager.getAllContacts().stream()
				.filter(contact -> contact.getFirstName().equalsIgnoreCase("Vitthal")
						&& contact.getLastName().equalsIgnoreCase("0123456789Jadhav")
						&& contact.getPhoneNumber().equalsIgnoreCase("0123456789"))
				.findAny().isPresent());

	}

	@Test
	@DisplayName("Should Not Create Contact when First Name Is Null")
	public void showThrowRuntimeExceptionThenFirstNameIsNull() {

		Assertions.assertThrows(RuntimeException.class, () -> {
			manager.addContact(null, "Jadhav", "0123456789");
		});
	}

	@Test
	@DisplayName("Should Not Create Contact when Last Name Is Null")
	public void showThrowRuntimeExceptionThenLastNameIsNull() {
		// ContactManager manager = new ContactManager();
		Assertions.assertThrows(RuntimeException.class, () -> {
			manager.addContact("VItthal", null, "0123456789");
		});
	}

	@Test
	@DisplayName("Should Not Create Contact when PhoneNUmber Is Null")
	public void showThrowRuntimeExceptionThenPhoneNumberIsNull() {
		// ContactManager manager = new ContactManager();
		Assertions.assertThrows(RuntimeException.class, () -> {
			manager.addContact("VItthal", "Jadhav", null);
		});
	}

	@AfterEach
	public void tearDown() {
		System.out.println("Should execute after each test");
	}

	@AfterAll
	public void tearDownAll() {
		System.out.println("should be executed at the end of the Test");
	}

	@Test
	@DisplayName("should create contact only on Window OS")
	@EnabledOnOs(value = OS.MAC, disabledReason = "Enable  Only on window OS")
	public void shouldCreateContactOS() {
		// ContactManager manager = new ContactManager();
		manager.addContact("Vitthal", "Jadhav", "0123456789");
		Assertions.assertFalse(manager.getAllContacts().isEmpty());
		Assertions.assertEquals(1, manager.getAllContacts().size());

		Assertions.assertTrue(manager.getAllContacts().stream()
				.filter(contact -> contact.getFirstName().equalsIgnoreCase("Vitthal")
						&& contact.getLastName().equalsIgnoreCase("0123456789Jadhav")
						&& contact.getPhoneNumber().equalsIgnoreCase("0123456789"))
				.findAny().isPresent());

	}

	@Test
	@DisplayName("should Not create contact  on Window OS")
	@DisabledOnOs(value = OS.WINDOWS, disabledReason = "Disables  Only on window OS")
	public void shouldCreateContactOSDisable() {
		// ContactManager manager = new ContactManager();
		manager.addContact("Vitthal", "Jadhav", "0123456789");
		Assertions.assertFalse(manager.getAllContacts().isEmpty());
		Assertions.assertEquals(1, manager.getAllContacts().size());

		Assertions.assertTrue(manager.getAllContacts().stream()
				.filter(contact -> contact.getFirstName().equalsIgnoreCase("Vitthal")
						&& contact.getLastName().equalsIgnoreCase("0123456789Jadhav")
						&& contact.getPhoneNumber().equalsIgnoreCase("0123456789"))
				.findAny().isPresent());

	}

	@Test
	@DisplayName("Test Contact Creation on Developer Machine")
	public void shouldTestCreateContactOnDev() {

		Assumptions.assumeTrue("DEV".equals(System.getProperty("ENV")));
		manager.addContact("Vitthal", "Jadhav", "0123456789");
		Assertions.assertFalse(manager.getAllContacts().isEmpty());
		Assertions.assertEquals(1, manager.getAllContacts().size());
	}

	@Test
	@DisplayName("Repeat Contact Creatio Test 5 Times")
	@RepeatedTest(value = 5, name = "Repeating contact Creation Test {currentRepetition} of {totalRepetitions}")
	public void shouldTestCreateContactRepeated() {

		manager.addContact("Vitthal", "Jadhav", "0123456789");
		Assertions.assertFalse(manager.getAllContacts().isEmpty());
		Assertions.assertEquals(1, manager.getAllContacts().size());
	}

	@Test
	@DisplayName("ParameterizedTest")
	@ParameterizedTest
	@ValueSource(strings = {"0123456789"})
	public void shouldTestCreateContactUsingValueSource(String phoneNumber) {
		manager.addContact("Vitthal", "Jadhav", phoneNumber);
		Assertions.assertFalse(manager.getAllContacts().isEmpty());
		Assertions.assertEquals(1, manager.getAllContacts().size());
	}
}
