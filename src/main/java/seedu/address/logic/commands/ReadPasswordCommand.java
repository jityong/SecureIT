package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ClipboardUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.password.Password;


/**
 * Reads a password identified using it's displayed index from the password book.
 */
public class ReadPasswordCommand extends Command {
    public static final String COMMAND_WORD = "read";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + " : Opens and accesses the password identified by "
            + "the index number used in the display list. \n"
            + "Parameters: INDEX (must be positive integer)"
            + "Some example ...";

    public static final String MESSAGE_SUCCESS = "Results are shown on the right panel. \nPassword is copied."
            + " Use copy command to copy username/website";

    private final Index targetIndex;

    public ReadPasswordCommand(Index index) {
        this.targetIndex = index;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Password> lastShownList = model.getFilteredPasswordList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PASSWORD_DISPLAYED_INDEX);
        }
        Password passwordToRead = lastShownList.get(targetIndex.getZeroBased());
        ClipboardUtil.copyToClipboard(passwordToRead.getPasswordValue().getNonEncryptedPasswordValue(), null);
        return CommandResult.builder("Results are shown on the right panel")
                .setObject(passwordToRead)
                .setIndex(targetIndex)
                .build();
    }
}
