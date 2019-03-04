package uk.co.alt236.jacocoxmlparser;

import org.apache.commons.cli.*;
import uk.co.alt236.jacocoxmlparser.cli.CommandHelpPrinter;
import uk.co.alt236.jacocoxmlparser.cli.CommandLineOptions;
import uk.co.alt236.jacocoxmlparser.cli.JarDetails;
import uk.co.alt236.jacocoxmlparser.cli.OptionsBuilder;
import uk.co.alt236.jacocoxmlparser.resources.Strings;

import java.io.File;

public class Main {

    private Main() {
        //NOOP
    }

    public static void main(String[] args) throws Exception {
        final Strings strings = new Strings();
        final CommandLineOptions cliOptions = parseArgs(strings, args);

        if (cliOptions != null) {

            final File file = getFile(cliOptions.getInputFile());
            new JacocoXmlParser().parse(cliOptions, file);
        }
    }

    private static CommandLineOptions parseArgs(Strings strings, String[] args) {
        final CommandLineParser parser = new DefaultParser();
        final Options options = new OptionsBuilder(strings).compileOptions();
        final CommandLineOptions retVal;

        if (args.length == 0) {
            final JarDetails jarDetails = new JarDetails(Main.class);
            new CommandHelpPrinter(strings, jarDetails, options).printHelp();
            retVal = null;
        } else {
            CommandLine line = null;

            try {
                line = parser.parse(options, args);
            } catch (final ParseException exp) {
                final String message = exp.getMessage();
                System.err.println(message);
                System.exit(1);
            }

            retVal = new CommandLineOptions(line);
        }

        return retVal;
    }

    private static File getFile(final String filePath) {
        final File file = new File(filePath);

        if (!file.exists()) {
            System.err.println("File does not exist: " + filePath);
            System.exit(1);
        }

        if (file.isDirectory()) {
            System.err.println("File is a directory: " + filePath);
            System.exit(1);
        }

        return file;
    }
}
