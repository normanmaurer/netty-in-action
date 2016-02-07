This Repository contains the source-code for all chapters of the book [Netty in Action](http://manning.com/maurer)
by Norman Maurer and Marvin Allen Wolfthal.


Enjoy! Feedback and PR's welcome!


Prerequisites

	JDK 1.7.0u71 or better

	Maven 3.2.3 or better


If you want to build everything at once, from the top directory run

	mvn install


If you want to build only single projects then from the top directory first run

	mvn install -pl utils


This will make the utils jar available to all the projects.
