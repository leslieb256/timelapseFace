## TODO
To rotate face maybe pick up on relative location of eyes and nose/mouth.
Then use Opencv to rotate image.
Mayebe best way to test this is to get location readings and output modified jpg?
 https://www.tutorialspoint.com/javaexamples/rotate_image.htm - rotate image using opencv.
https://opencv-java-tutorials.readthedocs.io/en/latest/02-first-java-application-with-opencv.html
https://github.com/opencv-java/face-detection/blob/master/src/it/polito/teaching/cv/FaceDetectionController.java

 - Display help if no options given (currently executes but later will be able to choose stuff.
 
 Many thanks to StackOverflow.com without whose help this would not have been possible.
Test landscape image from Dave Perret (Github/@recuser) 

I am getting this error when I run the jar file:
OpenJDK 64-Bit Server VM warning: You have loaded library /tmp/opencv_openpnp50594631890146920/nu/pattern/opencv/linux/x86_64/libopencv_java342.so which might have disabled stack guard. The VM will try to fix the stack guard now.
It's highly recommended that you fix the library with 'execstack -c <libfile>', or link it with '-z noexecstack'.
Does not appear to stop my application from running but noting it here for future trouble shooting.

