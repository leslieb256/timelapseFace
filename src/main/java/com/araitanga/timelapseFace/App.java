package com.araitanga.timelapseFace;

import org.apache.commons.cli.Options;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.OptionBuilder;
import org.apache.commons.cli.ParseException;

import com.drew.imaging.ImageInformation;
import com.drew.imaging.Metadata;
import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;

import java.io.IOException;
import java.io.File;

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
	public static class ImageInformation {
		public final int orientation;
		public final int width;
		public final int height;

		public ImageInformation(int orientation, int width, int height) {
			this.orientation = orientation;
			this.width = width;
			this.height = height;
		}

		public String toString() {
			return String.format("%dx%d,%d", this.width, this.height, this.orientation);
		}
	}
	
	public static ImageInformation readImageInformation(File imageFile)  throws IOException, MetadataException, ImageProcessingException {
    Metadata metadata = ImageMetadataReader.readMetadata(imageFile);
    Directory directory = metadata.getFirstDirectoryOfType(ExifIFD0Directory.class);
    JpegDirectory jpegDirectory = metadata.getFirstDirectoryOfType(JpegDirectory.class);

    int orientation = 1;
    try {
        orientation = directory.getInt(ExifIFD0Directory.TAG_ORIENTATION);
    } catch (MetadataException me) {
        logger.warn("Could not get orientation");
    }
    int width = jpegDirectory.getImageWidth();
    int height = jpegDirectory.getImageHeight();

    return new ImageInformation(orientation, width, height);
}

}
