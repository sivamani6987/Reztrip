package com.tt.utility;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Layout;
import org.apache.log4j.spi.ErrorCode;
import java.io.File;
import java.io.IOException;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
public class NewLogForEachRunFileAppender  extends FileAppender {

	public NewLogForEachRunFileAppender() {
	}

	public NewLogForEachRunFileAppender(Layout layout, String filename,
			boolean append, boolean bufferedIO, int bufferSize)
					throws IOException {
		super(layout, filename, append, bufferedIO, bufferSize);
	}

	public NewLogForEachRunFileAppender(Layout layout, String filename,
			boolean append) throws IOException {
		super(layout, filename, append);
	}

	public NewLogForEachRunFileAppender(Layout layout, String filename)
			throws IOException {
		super(layout, filename);
	}

	public void activateOptions() {
		if (fileName != null) {
			try {
				fileName = getNewLogFileName();
				setFile(fileName, fileAppend, bufferedIO, bufferSize);
			} catch (Exception e) {
				errorHandler.error("Error while activating log options", e,
						ErrorCode.FILE_OPEN_FAILURE);
			}
		}
	}

	private String getNewLogFileName() {
		if (fileName != null) {
			final String DOT = ".";
			final String HIPHEN = "-";
			final File logFile = new File(fileName);
			final String fileName = logFile.getName();
			String newFileName = "";

			final int dotIndex = fileName.indexOf(DOT);


			if (dotIndex <= 2) {

				// the file name has an extension. so, insert the time stamp
				// between the file name and the extension
				newFileName = fileName.substring(0, dotIndex) + HIPHEN
						+ getcurrentdateandtime()+ DOT
						+ fileName.substring(dotIndex + 1);
			} else {
				// the file name has no extension. So, just append the timestamp
				// at the end.
				newFileName = fileName + HIPHEN + getcurrentdateandtime();

			}
			return logFile.getParent() + File.separator + newFileName;
		}
		return null;
	}
	public  String getcurrentdateandtime(){
		String str = null;
		try{
			DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
			Date date = new Date();
			str= dateFormat.format(date);
			str = str.replace(" ", "").replaceAll("/", "").replaceAll(":", "");
		}
		catch(Exception e){

		}
		return str;
	}

	}


