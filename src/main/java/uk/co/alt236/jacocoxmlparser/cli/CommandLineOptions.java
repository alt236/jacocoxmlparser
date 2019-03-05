package uk.co.alt236.jacocoxmlparser.cli;

import org.apache.commons.cli.CommandLine;

public class CommandLineOptions {

    private final CommandLine commandLine;

    public CommandLineOptions(final CommandLine commandLine) {
        this.commandLine = commandLine;
    }

    public String getInputFile() {
        return commandLine.getOptionValue(OptionsBuilder.ARG_FILE_LONG);
    }

    public boolean isPrintPackageStats() {
        return commandLine.hasOption(OptionsBuilder.ARG_PRINT_PACKAGE_STATS_LONG);
    }

    public boolean isPrintGlobalStats() {
        return commandLine.hasOption(OptionsBuilder.ARG_PRINT_GLOBAL_STATS_LONG);
    }

    public boolean isColorise() {
        return commandLine.hasOption(OptionsBuilder.ARG_COLORISE);
    }
}
