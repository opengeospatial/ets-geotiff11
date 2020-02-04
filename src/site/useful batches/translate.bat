cd c:\Users\RDAGCDLJ\Documents\FY20\GeoTIFF\example_tiffs\COG_DEF_OR

setlocal enabledelayedexpansion
for %%f in (*.tif) do (
    set TEMP=%%~nf
    docker run --rm -v %cd%:/COG_DEF_OR osgeo/gdal:alpine-small-latest gdal_translate COG_DEF_OR/!TEMP!.tif COG_DEF_OR/1.1.1/!TEMP!.tif -co GEOTIFF_VERSION=1.1
)

cd c:\Users\RDAGCDLJ\Documents\FY20\GeoTIFF\example_tiffs
