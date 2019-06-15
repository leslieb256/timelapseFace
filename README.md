## TODO
Line 121 change to load eye classifier and mouth classifier
Then run these on tyhe image to return the loaction? I guess

Compare locations back to image see ifthey look resonable.

Then use Opencv to rotate image.
Mayebe best way to test this is to get location readings and output modified jpg?
 https://www.tutorialspoint.com/javaexamples/rotate_image.htm - rotate image using opencv.
https://opencv-java-tutorials.readthedocs.io/en/latest/02-first-java-application-with-opencv.html
https://github.com/opencv-java/face-detection/blob/master/src/it/polito/teaching/cv/FaceDetectionController.java


# Tutorials
https://opencv-java-tutorials.readthedocs.io/en/latest/02-first-java-application-with-opencv.html
https://github.com/opencv-java/face-detection/blob/master/src/it/polito/teaching/cv/FaceDetectionController.java

https://dzone.com/articles/face-and-eyes-detection-opencv

this looks promising
http://www.magicandlove.com/blog/2018/08/19/face-landmark-detection-in-opencv-face-module-with-processing/

More promising:
Might need this as well: https://github.com/opencv/opencv_3rdparty/tree/contrib_face_alignment_20170818
https://docs.opencv.org/3.4.2/d2/d42/tutorial_face_landmark_detection_in_an_image.html

Use MCS-mouth to fid the mouth
I think eye returns the locatin of both eyes. need to test.

# Notes
You might get this error: libdc1394 error: Failed to initialize libdc1394
Apparently it is a driver for camera hardware which I am not using so should not be an issue

Haar Casacde files were taken from this github repo:
https://github.com/voidstellar/haar-cascade-files
No copyright notice but I figured worth a shoutout.
This might just be a copy of the opencv/opencv github cascades but I could not find an easy way to download them all from there


 - Display help if no options given (currently executes but later will be able to choose stuff.
 
 Many thanks to StackOverflow.com without whose help this would not have been possible.
Test landscape image from Dave Perret (Github/@recuser) 

I am getting this error when I run the jar file:
OpenJDK 64-Bit Server VM warning: You have loaded library /tmp/opencv_openpnp50594631890146920/nu/pattern/opencv/linux/x86_64/libopencv_java342.so which might have disabled stack guard. The VM will try to fix the stack guard now.
It's highly recommended that you fix the library with 'execstack -c <libfile>', or link it with '-z noexecstack'.
Does not appear to stop my application from running but noting it here for future trouble shooting.

