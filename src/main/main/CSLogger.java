package main;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.UUID;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CSLogger {
	private Logger logger;
	private String logFolder = "logs";
	private String logName;
	private ArrayList<String> logs;
	
	public CSLogger() {
		// make new log file
		// add "ready" to log file
		
		newLog();
	}
	
	private void newLog() {
		logger = Logger.getLogger("MyLog");  
		FileHandler fh;
		try {
            Files.createDirectories(Paths.get(logFolder));
            logName = timeStamp() + randomStr() + ".log";
            fh = new FileHandler(
            	logFolder + File.separator + logName);
			logger.addHandler(fh);
			SimpleFormatter formatter = new SimpleFormatter();  
			fh.setFormatter(formatter);  
			log("ready", "ready");  
		} catch (SecurityException e) {
		    e.printStackTrace();  
		} catch (IOException e) {  
		    e.printStackTrace();  
		}  
	}
	private String timeStamp() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        return dateFormat.format(new Date());
    }
	private String randomStr() {
        return UUID.randomUUID().toString();
	}
	
	public String getLogType(int i) {
		return null;
	}

	public void log(String type, String message) {
		logger.info(type + ":" + message);
	}

	public String getLogDateTime(int i) {
		return "";
	}

	public String getLogValue(int i) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public ArrayList<String> getLog(int i) {
		// 0=date/time
		// 1=type
		// 2=message
		ArrayList<String> n = new ArrayList<String>();
		BufferedReader reader;
		String line;
		int len = 0;
		try {
			reader = new BufferedReader(
				new FileReader(logFolder + File.separator + logName));
			line = reader.readLine();
			while (line != null) {
			    if (len / 2 == i) {
			    	// Nov 09, 2021 10:49:14 AM
			    	String pattern = "^\\w+ \\d+, \\d+ \\d+:\\d+:\\d+ \\w+";
			        Pattern r = Pattern.compile(pattern);
			        Matcher m = r.matcher(line);
			        if (m.find()) {
			        	n.add(m.group(0));
			        }
			        line = reader.readLine();
			        String c[] = line.split(":");
			    	n.add(c[1].trim());
			    	n.add(c[2].trim());
			    	break;
			    }
			    len++;
			    line = reader.readLine();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return n;
	}
	
	public int getLogLength() {
		BufferedReader reader;
		String line;
		int len = 0;
		try {
			reader = new BufferedReader(
				new FileReader(logFolder + File.separator + logName));
			line = reader.readLine();
			while (line != null) {
			    line = reader.readLine();
			    len++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return len / 2;
	}

}
