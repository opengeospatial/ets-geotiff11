# GeoTiff 1.1 Test-Suite

## Scope
The GeoTiff 1.1 Test-Suite is an Executable Test Suite (ETS) that verifies implementations against the GeoTiff 1.1 (and 1.0) specifications.

See specification documentation [here](https://github.com/opengeospatial/geotiff).

Two conformance classes are defined:
  * [TIFF](http://www.opengis.net/spec/GeoTIFF/1.1/conf/Core)
  * [Raster2Model_CoordinateTransformation_GeoKey](http://www.opengis.net/spec/GeoTIFF/1.1/conf/Raster2Model_CoordinateTransformation_GeoKey)
 
## Bugs
Issue tracker is available at github.

## How to run the tests
The test suite is built using [Apache Maven v3](https://maven.apache.org/). The options
for running the suite are summarized below.

#### 1. Integrated development environment (IDE)

Use a Java IDE such as Eclipse, NetBeans, or IntelliJ. Clone the repository and build the project.

Set the main class to run: `org.opengis.cite.geotiff11.TestNGController`

Arguments: The first argument must refer to an XML properties file containing the
required test run arguments. If not specified, the default location at `$
{user.home}/test-run-props.xml` will be used.

You can modify the sample file in `src/main/config/test-run-props.xml`

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE properties SYSTEM "http://java.sun.com/dtd/properties.dtd">
<properties version="1.0">
  <comment>Test run arguments</comment>
  <entry key="iut">[GEOTIFF HERE].tiff</entry>
</properties>
```

The TestNG results file (`testng-results.xml`) will be written to a subdirectory
in `${user.home}/testng/` having a UUID value as its name.

#### 2. Command shell (console)

One of the build artifacts is an "all-in-one" JAR file that includes the test
suite and all of its dependencies; this makes it very easy to execute the test
suite in a command shell:

`java -jar target/ets-geotiff11-0.4-SNAPSHOT-aio.jar  [-o|--outputDir $TMPDIR] [xml-file]`

Where `xml-file` is the path to the properties XML file, e.g., `src/main/config/test-run-props.xml`.

#### 3. OGC test harness

Use [TEAM Engine](https://github.com/opengeospatial/teamengine), the official OGC test harness.
The latest test suite release are usually available at the [beta testing facility](http://cite.opengeospatial.org/te2/).
You can also [build and deploy](https://github.com/opengeospatial/teamengine) the test
harness yourself and use a local installation.
