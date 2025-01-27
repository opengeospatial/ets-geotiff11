package org.opengis.cite.geotiff11;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * <p>
 * SyncPipe class.
 * </p>
 *
 */
public class SyncPipe implements Runnable {

	/**
	 * <p>
	 * Constructor for SyncPipe.
	 * </p>
	 * @param istrm a {@link java.io.InputStream} object
	 * @param ostrm a {@link java.io.OutputStream} object
	 */
	public SyncPipe(InputStream istrm, OutputStream ostrm) {
		this.istrm_ = istrm;
		this.ostrm_ = ostrm;
	}

	/**
	 * <p>
	 * run.
	 * </p>
	 */
	public void run() {
		try {
			final byte[] buffer = new byte[1024];
			for (int length = 0; (length = this.istrm_.read(buffer)) != -1;) {
				this.ostrm_.write(buffer, 0, length);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	private final OutputStream ostrm_;

	private final InputStream istrm_;

}
