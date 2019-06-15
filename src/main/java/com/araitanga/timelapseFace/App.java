package com.araitanga.timelapseFace;

import org.apache.commons.cli.Options;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.OptionBuilder;
import org.apache.commons.cli.ParseException;

import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.Metadata;
import com.drew.metadata.MetadataException;
import com.drew.metadata.Directory;
import com.drew.metadata.exif.ExifIFD0Directory;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.objdetect.CascadeClassifier;
import org.opencv.imgcodecs.Imgcodecs;



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
				File imageFile = new File("../inputImg/EC060031.JPG");
				rotateImage(imageFile);
				System.out.println("Now runing Open cv stuff");
				useOpenCV();

			}
		}
		catch(ParseException exp) {
			System.out.println("Failed to parse options. Reason :"+ exp.getMessage() );
		}		
    }

	/**
	 *	@param	imageFile	The jpg format file to read the exif tag from
	 *	@return	int			The image orientation as an int. -1 means nothing found.
	 */
	static int readExifTag(File imageFile){
		int orientation = -1;
		try{
			Metadata metadata = ImageMetadataReader.readMetadata(imageFile);
			Directory directory = metadata.getFirstDirectoryOfType(ExifIFD0Directory.class);
			orientation = directory.getInt(ExifIFD0Directory.TAG_ORIENTATION);
		}
		catch(ImageProcessingException ipx){
			System.out.println("Failed read metadata from image");
		}
		catch(IOException iox){
			System.out.println("Failed read file from image");
		}
		catch(MetadataException mex){
			System.out.println("Failed read tag from image");
		}
		return orientation;		
	}
	
	static void rotateImage(File imageFile) {
		System.out.println("now printg the retutn " + readExifTag(imageFile));
	}
	
	static void useOpenCV(){
		/**	
			loads the c/c++ libraries from the nu.pattern package so that when
			System.loadLibrary tries to load them they are available.
		*/
		nu.pattern.OpenCV.loadShared();
		// loads the library for the current os.
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        
		// if libraries have not loaded this will fail.
		Mat mat = Mat.eye(3, 3, CvType.CV_8UC1);
        System.out.println("mat = " + mat.dump());
		
        // face cascade classifier
        CascadeClassifier faceCascade;
        int absoluteFaceSize;
        String classifierPath;
        
        faceCascade = new CascadeClassifier();
        absoluteFaceSize = 0;
        classifierPath = "resources/haarcascades/haarcascade_frontalface_alt.xml";
        
        // Load the Haar Cascade Classifier
        faceCascade.load(classifierPath);
        System.out.println("Face detection cascade loaded from: " + classifierPath);

		
        Mat img = Imgcodecs.imread("EC060031.JPG", Imgcodecs.CV_LOAD_IMAGE_GRAYSCALE);
	}


/**	
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
	**/

}
