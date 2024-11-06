package seedu.address.logic.commands.event.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalEvents.EMPTY_EVENT;
import static seedu.address.testutil.TypicalEvents.FILLED_EVENT;
import static seedu.address.testutil.TypicalEvents.getTypicalEventManager;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_EVENT;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.event.EventManager;

public class EventAddAllCommandTest {
    private EventAddAllCommand eventAddAllCommand = new EventAddAllCommand(INDEX_FIRST_EVENT);

    @Test
    public void execute_invalidEventIndex_throwsCommandException() {
        Model model = new ModelManager();
        String expectedMsg = Messages.MESSAGE_INVALID_EVENT_DISPLAYED_INDEX;
        assertCommandFailure(eventAddAllCommand, model, expectedMsg);
    }
    @Test
    public void execute_emptyContactList_throwsCommandException() {
        Model model = new ModelManager(new AddressBook(), getTypicalEventManager(), new UserPrefs());
        String expectedMsg = Messages.MESSAGE_EMPTY_CONTACTS_ON_ADDALL;
        assertCommandFailure(eventAddAllCommand, model, expectedMsg);
    }

    @Test
    public void execute_correctInputs_success() {
        EventManager eventManager = new EventManager();
        eventManager.addEvent(EMPTY_EVENT);
        Model model = new ModelManager(getTypicalAddressBook(), eventManager, new UserPrefs());
        CommandResult expectedCommandResult = new CommandResult("Contacts added to Event to be filled "
                + "successfully: \n"
                + "Attendee(s): Alice Pauline, Fiona Kunz\n"
                + "Volunteer(s): Elle Meyer\n"
                + "Vendor(s): Daniel Meier, George Best\n"
                + "Sponsor(s): Benson Meier, Carl Kurz");

        EventManager expectedEventManager = new EventManager();
        expectedEventManager.addEvent(FILLED_EVENT);
        Model expectedModel = new ModelManager(getTypicalAddressBook(), expectedEventManager, new UserPrefs());
        assertCommandSuccess(eventAddAllCommand, model, expectedCommandResult, expectedModel);
    }

    @Test
    void testEquals_sameObject() {
        EventAddAllCommand command = new EventAddAllCommand(INDEX_FIRST_EVENT);
        assertTrue(command.equals(command));
    }

    @Test
    void testEquals_differentType() {
        EventAddAllCommand command = new EventAddAllCommand(INDEX_FIRST_EVENT);
        String otherObject = "NotAnEventAddAllCommand";
        assertFalse(command.equals(otherObject));
    }
}