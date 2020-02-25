import urllib.request
import shutil
from bs4 import BeautifulSoup

url = "ftp://rockyftp.cr.usgs.gov/"

soup = BeautifulSoup(open("C:/Users/RDAGCDLJ/Documents/FY20/GeoTIFF/ets-geotiff11/src/test/resources/batches/Index of _vdelivery_Datasets_Staged_Elevation_1_TIFF_.html"), "html.parser")

for a in soup.select("td a"): # <td data-value="s14w171/"><a class="icon dir" href="ftp://rockyftp.cr.usgs.gov/vdelivery/Datasets/Staged/Elevation/1/TIFF/s14w171/">s14w171/</a></td>
    title = a.string[0:-1]
    print(a.string[0:-1])
    url = a.get("href") + "USGS_1_" + title + ".tif"
    # print(url)
    file_name = "C:/Users/RDAGCDLJ/Documents/FY20/GeoTIFF/example_tiffs/USGS_1/" + title + ".tif"
    #print(file_name)

    with urllib.request.urlopen(url) as response, open(file_name, 'wb') as out_file:
        shutil.copyfileobj(response, out_file)

# # Download the file from `url` and save it locally under `file_name`:
# url = 'ftp://rockyftp.cr.usgs.gov/vdelivery/Datasets/Staged/Elevation/1/TIFF/n06e162/USGS_1_n06e162.tif'
# file_name = "C:/Users/RDAGCDLJ/Documents/FY20/GeoTIFF/example_tiffs/test.tif"

# with urllib.request.urlopen(url) as response, open(file_name, 'wb') as out_file:
#     shutil.copyfileobj(response, out_file)
