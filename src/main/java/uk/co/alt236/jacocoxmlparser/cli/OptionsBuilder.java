package uk.co.alt236.jacocoxmlparser.cli;

import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionGroup;
import org.apache.commons.cli.Options;
import uk.co.alt236.jacocoxmlparser.resources.Strings;

public class OptionsBuilder {

    /*package*/ static final String ARG_FILE = "i";
    /*package*/ static final String ARG_FILE_LONG = "input";

    /*package*/ static final String ARG_PRINT_PACKAGE_STATS = "p";
    /*package*/ static final String ARG_PRINT_PACKAGE_STATS_LONG = "package";

    /*package*/ static final String ARG_PRINT_GLOBAL_STATS = "g";
    /*package*/ static final String ARG_PRINT_GLOBAL_STATS_LONG = "global";

    private final Strings strings;

    public OptionsBuilder(Strings strings) {
        this.strings = strings;
    }

    public Options compileOptions() {
        final Options options = new Options();

        final OptionGroup outputTypeGroup = new OptionGroup();
        outputTypeGroup.addOption(createOptionPrintGlobal());
        outputTypeGroup.addOption(createOptionPrintPackage());
        outputTypeGroup.setRequired(true);

        options.addOption(createOptionFile());
        options.addOptionGroup(outputTypeGroup);

        return options;
    }

    private Option createOptionFile() {
        final String desc = strings.getString("cli_cmd_file");
        return Option.builder()
                .longOpt(ARG_FILE)
                .hasArg()
                .required(true)
                .desc(desc)
                .build();
    }


    private Option createOptionPrintGlobal() {
        final String desc = strings.getString("cli_cmd_print_global_stats");
        return Option.builder(ARG_PRINT_GLOBAL_STATS)
                .longOpt(ARG_PRINT_GLOBAL_STATS_LONG)
                .hasArg(false)
                .required(false)
                .desc(desc)
                .build();
    }


    private Option createOptionPrintPackage() {
        final String desc = strings.getString("cli_cmd_print_package_stats");
        return Option.builder(ARG_PRINT_PACKAGE_STATS)
                .longOpt(ARG_PRINT_PACKAGE_STATS_LONG)
                .hasArg(false)
                .required(false)
                .desc(desc)
                .build();
    }
}
