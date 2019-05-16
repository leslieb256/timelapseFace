package com.araitanga.timelapseFace;

import org.apache.commons.cli.Options;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.OptionBuilder;
import org.apache.commons.cli.ParseException;
import java.io.IOException;

/**
 * Timelapse Face processor
 *
 */
public class App 
{
 public static void main(String[] args) {
		// Parse commandline options using Apache Commons Cli
		
		// create options object to hold options
		Options options = new Options();
		// add valid options to the options object
		options.addOption(OptionBuilder.withLongOpt("help")
		                               .withDescription("print this message")
									   .hasArg(false)
									   .isRequired(false)
									   .create("h"));
		//Add explanatory text
		String helpHeader = "Processes and aligns photos to make a video";
		String helpFooter = "\nFor more inforamtion see: @TODO";
		// parse the options from the commandline
		CommandLineParser parser = new DefaultParser();
		try {
			CommandLine cmd = parser.parse(options, args);
			// check which options were selected
			if(cmd.hasOption("help")) {
				HelpFormatter formatter = new HelpFormatter();
				formatter.printHelp("java -jar timelapseFace.jar", helpHeader, options, helpFooter,true);
			}
			else {
				System.out.println("Now running app");
			}
		}
		catch(ParseException exp) {
			System.out.println("Failed to parse options. Reason :"+ exp.getMessage() );
		}

		
    }
}
