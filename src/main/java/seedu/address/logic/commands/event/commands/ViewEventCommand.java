package seedu.address.logic.commands.event.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.event.Event;
import seedu.address.model.event.EventManager;


/**
 * View the list of persons who are in an event.
 */
public class ViewEventCommand extends Command{
    public static final String COMMAND_WORD = "view";

    public static final String MESSAGE_USAGE = COMMAND_WORD + " EVENT NAME: View list of persons in an event.\n"
            + "e.g. view sumobot festival";

    public static final String MESSAGE_SUCCESS = "Viewing event: %1$s";
    public static final String EVENT_DOES_NOT_EXIST = "The event you entered does not exist!";

    public final Event eventToView;

    /**
     * Creates a ViewEventCommand.
     */
    public ViewEventCommand(Event event) {
        requireNonNull(event);
        eventToView = event;
    }

    @Override
    public CommandResult execute(Model model, EventManager eventManager) throws CommandException {
        requireNonNull(eventManager);

        if (!eventManager.hasEvent(eventToView)) {
            throw new CommandException(EVENT_DOES_NOT_EXIST);
        }

        model.updateFilteredPersonList(eventManager.getPersonInEventPredicate(eventToView));
        return new CommandResult(String.format(MESSAGE_SUCCESS, eventToView.getName()));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ViewEventCommand)) {
            return false;
        }

        ViewEventCommand otherViewEventCommand = (ViewEventCommand) other;
        return eventToView.equals(otherViewEventCommand.eventToView);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("eventToView", eventToView)
                .toString();
    }
}