# JGraphV
A **J**ava library for **Graph** **V**isualization that runs on Android, Web, and Desktop.  JGraphV is powered by OpenGL.  It is designed to be preformant on graphs with thounds of nodes while supporting panning and zooming to floating point precision.

## Sample graphs

Web/mobile web demos:

* [A directed graph with 5000 nodes.](http://rma350.github.io/graph/JGraphVWebDemo.html)
* [An undirected graph with 5000 nodes and curved edges.](http://rma350.github.io/undirectedgraph/JGraphVWebDemo.html)

## How it works

A platform API for all platforms is specified in `JGraphVCore` project under `com.github.rma350.jgraphv.core.portable`.  Each platform must provide a common API to OpenGL through `GL.java` (an intersection of GLES and WebGL), basic linear algebra, native arrays, and logging.  The `JGraphVCore` project builds on this platform API to provide basic tools for building graphs, rendering them with OpenGL, viewing through an orthographic camera, and responding to input.

There are three additional projects `JGraphVAndroid`, `JGraphVDesktop` and `JGraphVWeb`, that provide implementations of the platform API from `JGraphVCore` and contain minimal runnable applications using the engine.

## Developer set up

* Make sure you have the Java 7 JDK installed
* Install a recent version of eclipse (works with Juno, newer tends to be better)
* Install the android eclipse plugin (see http://developer.android.com/sdk/installing/installing-adt.html)
* Install the GWT eclipse plugin (http://www.gwtproject.org/usingeclipse.html)
* git clone anywhere on your local machine
* Create a workspace anywhere on your local machine
* From eclipse in your new workspace: `File->Import->General->Existing Projects into Workspace`.  Under `Select root directory` click `Browse`, then navigate to to `[location where you git-cloned]/jgraphv/`.  Under the `Projects` box there should be 4 projects in a list all checked, `JGraphVAndroid`, `JGraphVCore`, `JGraphVDesktop` and `JGraphVWeb`.  Click `Finish`.

If all is well, then everything should just build in eclipse.  Cross platform development is hard!  If you don't care about web or android, you can skip installing their eclipse plugins and just not import these projects.


## Run locally

* Android: plug your android device (with developer options enabled) into your computer.  From eclipse, right on `JGraphVAndroid` select `Run As -> Android Application`.
* Desktop: Right click on `JGraphVDesktop->src/com/github/rma350/jgraphv/desktop/Main.java` and select `Run As -> Java Application`.
* Web: Right click on the `JGraphVWeb` project and select `Google->GWT Compile`.  Select `com.github.rma350.jgraphv.web.JGraphVWeb.java` as the entry point module, the `Log level` to `Info`, and the `Output style` to `Pretty`.  Click `Compile`.  You can then visit the local file from your web browser `JGraphVWeb/war/JGraphVWebDemo.html`.
