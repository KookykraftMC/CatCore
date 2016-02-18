package com.kookykraftmc.commands;

import java.util.List;

import com.kookykraftmc.utils.NameUtils;
import org.bukkit.command.CommandSender;

/**
 * Super class for commands
 */
public abstract class AbstractCommand {

    private String name, usage;

    /**
     * Constructor for the command super class
     *
     * @param name
     * @param usage
     */
    public AbstractCommand(String name, String usage) {
        this.name = name;
        this.usage = usage;
    }

    /**
     * Get the name of the command used after the /
     *
     * @return The command name
     */
    public String getName() {
        return name;
    }

    /**
     * Get the usage of the command
     * <p>
     * Usage can be /nameofcommand [arguments...]
     *
     * @return
     */
    public String getUsage() {
        return "/" + name + " " + usage;
    }

    /**
     * Return the permission of the command
     * <p>
     * The permission will be catcore.[nameofcommand]
     *
     * @return The command permission
     */
    public String getPermission() {
        return "catcore." + name;
    }

    /**
     * Execute the command
     *
     * @param sender The sender of the command
     * @param args the arguments typed after the command
     * @return true if successful, false otherwise. Returning false will send the usage to the sender
     *
     * @throws CommandException if anything was wrongly typed this is thrown sending the sender a warning.
     */
    public abstract boolean execute(CommandSender sender, String[] args) throws CommandException;

    /**
     * Tab complete the command
     *
     * @param sender The sender of the command
     * @param args The arguments typed after the command
     * @return A list of tab completeable arguments
     */
    public abstract List<String> tabComplete(CommandSender sender, String[] args);

    /**
     * Parse an int from the given string
     *
     * @param parse The string to parse
     * @return the int parsed
     *
     * @throws CommandException If the string typed isn't an int, this will send the sender a message.
     */
    public int parseInt(String parse) throws CommandException {
        return parseInt(parse, "number");
    }

    /**
     * Parse an double from the given string.
     *
     * @param parse The string to parse.
     * @return The double parsed.
     *
     * @throws CommandException If the string typed isn't an double, this will send the sender a message.
     */
    public double parseDouble(String parse) throws CommandException {
        return parseDouble(parse, "number");
    }

    /**
     * Parse an long from the given string.
     *
     * @param parse The string to parse.
     * @return The long parsed.
     *
     * @throws CommandException If the string typed isn't an long, this will send the sender a message.
     */
    public long parseLong(String parse) throws CommandException {
        return parseLong(parse, "number");
    }

    /**
     * Parse an float from the given string.
     *
     * @param parse The string to parse.
     * @return The float parsed.
     *
     * @throws CommandException If the string typed isn't an float, this will send the sender a message.
     */
    public float parseFloat(String parse) throws CommandException {
        return parseFloat(parse, "number");
    }

    /**
     * Parse an int from the given string.
     *
     * @param parse The string to parse.
     * @param criteria What the int is used for.
     * @return The int parsed.
     *
     * @throws CommandException If the string typed isn't an int, this will send the sender a message.
     */
    public int parseInt(String parse, String criteria) throws CommandException {
        try {
            return Integer.parseInt(parse);
        } catch (Exception e) {
            throw new CommandException("'" + parse + "' is not a vaild " + criteria + ".");
        }
    }

    /**
     * Parse an double from the given string.
     *
     * @param parse The string to parse.
     * @param criteria What the double is used for.
     * @return The double parsed.
     *
     * @throws CommandException If the string typed isn't an double, this will send the sender a message.
     */
    public double parseDouble(String parse, String criteria) throws CommandException {
        try {
            return Double.parseDouble(parse);
        } catch (Exception e) {
            throw new CommandException("'" + parse + "' is not a vaild " + criteria + ".");
        }
    }

    /**
     * Parse an long from the given string.
     *
     * @param parse The string to parse.
     * @param criteria What the long is used for.
     * @return The long parsed.
     *
     * @throws CommandException If the string typed isn't an long, this will send the sender a message.
     */
    public long parseLong(String parse, String criteria) throws CommandException {
        try {
            return Long.parseLong(parse);
        } catch (Exception e) {
            throw new CommandException("'" + parse + "' is not a vaild " + criteria + ".");
        }
    }

    /**
     * Parse an float from the given string.
     *
     * @param parse The string to parse.
     * @param criteria What the long is used for.
     * @return The float parsed.
     *
     * @throws CommandException If the string typed isn't an float, this will send the sender a message.
     */
    public float parseFloat(String parse, String criteria) throws CommandException {
        try {
            return Float.parseFloat(parse);
        } catch (Exception e) {
            throw new CommandException("'" + parse + "' is not a vaild " + criteria + ".");
        }
    }

    public boolean parseBoolean(String parse, String criteria) throws CommandException {
        if (parse.equalsIgnoreCase("true") || parse.equalsIgnoreCase("on")) {
            return true;
        }

        if (parse.equalsIgnoreCase("false") || parse.equalsIgnoreCase("off")) {
            return false;
        }

        throw new CommandException(NameUtils.getInstance().capitalizeString(criteria, false) + " can only be 'true' or 'false', not '" + parse + "'.");
    }

    /**
     * Turn a the given boolean into "Enabled" or "Disabled".
     *
     * @param converting The boolean converting.
     * @return The converted boolean.
     */
    public String booleanToString(boolean converting) {
        return converting ? "enabled" : "disabled";
    }
}
