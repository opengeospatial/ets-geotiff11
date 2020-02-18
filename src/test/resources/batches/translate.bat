cd c:\Users\RDAGCDLJ\Documents\FY20\GeoTIFF\example_tiffs\USGS

setlocal enabledelayedexpansion
for %%f in (*.tif) do (
    set TEMP=%%~nf
    docker run --rm -v %cd%:/USGS osgeo/gdal:alpine-small-latest gdal_translate USGS/!TEMP!.tif USGS/1.1.1/!TEMP!.tif -co GEOTIFF_VERSION=1.1
)

pause
