package org.opengis.cite.geotiff10;

import java.io.InputStream;
import java.io.OutputStream;

public class SyncPipe implements Runnable
{
public SyncPipe(InputStream istrm, OutputStream ostrm) {
      this.istrm_ = istrm;
      this.ostrm_ = ostrm;
  }
  public void run() {
      try
      {
          final byte[] buffer = new byte[1024];
          for (int length = 0; (length = this.istrm_.read(buffer)) != -1; )
          {
              this.ostrm_.write(buffer, 0, length);
          }
      }
      catch (Exception e)
      {
          e.printStackTrace();
      }
  }
  private final OutputStream ostrm_;
  private final InputStream istrm_;
}