# Processing 3 Maven Eclipse template

This is a template for setting up a Maven project for Processing 3
 (more specifically: 3.2.3) in Eclipse. It has the required dependencies 
 in the pom file, and I've collected the native libraries  in a separate 
 repository (see below).

# Enabled Processing libraries

This template comes with the following Processing Libraries:

* Video
* PDF
* Net
* Serial

All these libraries are enabled by default, if you want to disable
some, remove the relevant librarie from the dependencies in the 
pom file (they are annotated there).

Make sure you download the native libraries before attempting to run.

# Downloading and using native libraries

For these Processing libraries native libraries (.so and .dll files) are
required to run (besides the jar files Maven collects):

* Video
* Serial

I've compiled them here for you to download:

https://github.com/wilcotomassen/processing-3-nativelibs/archive/v3.2.3.zip

After downloading, place them as a 'native-libs' folder next to your 'src' 
folder (they are ignored in the .git-ignore to save repo space). When you
run the application,  make sure to call NativeLibraryPath.setLibraryPath() 
in the Main or Setup function, and have native.library.path as a system
property.