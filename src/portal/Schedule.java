package portal;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;

public class Schedule {
	// Singleton class which holds Arraylist of schedules
	public static ArrayList<String> schedules;
	
	public void deleteSchedule(int index) {
		ArrayList<String> s = readFromFile();
		try {
			s.remove(index); // throws exception if no schedules
			FileWriter fw;
			fw = new FileWriter("schedules.csv", false); // overwrite
			BufferedWriter bw = new BufferedWriter(fw);
		    bw.write(
		    	Arrays.toString(s.toArray())
		    		.replace("[", "")
		    		.replace("]", "")
		    );
		    bw.newLine();
		    bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void addDates(String startDate, String endDate, String amount) {
		SimpleDateFormat s = new SimpleDateFormat("yyyy/MM/dd HH:mm");
		Calendar c = Calendar.getInstance();
		try {
			c.setTime(s.parse(startDate));
		} catch (ParseException e) {
			e.printStackTrace();
			return;
		}
		ArrayList<String> tmpoutput = new ArrayList<String>();
		if (amount != "") {
			// add new schedule X times
			for (int i = 0; i < Integer.parseInt(amount); i++) {
				c.add(Calendar.WEEK_OF_YEAR, 1);
				tmpoutput.add(s.format(c.getTime()));
			}
			addToFile(
				Arrays.toString(tmpoutput.toArray())
					.replace("[", "")
					.replace("]", "")
			);
		} else {
			// add new schedule until date
			Calendar c2 = Calendar.getInstance();
			try {
				c2.setTime(s.parse(endDate));
			} catch (ParseException e) {
				e.printStackTrace();
				return;
			}
			int max = 1000;
			while (c.compareTo(c2) < 1 && max >= 0) {
				max = max - 1;
				tmpoutput.add(s.format(c.getTime()));
				c.add(Calendar.WEEK_OF_YEAR, 1);
			}
			addToFile(
				Arrays.toString(tmpoutput.toArray())
					.replace("[", "")
					.replace("]", "")
			);
		}
	}
	public void addToFile(String schedule) {
		FileWriter fw;
		try {
			fw = new FileWriter("schedules.csv", true);
			BufferedWriter bw = new BufferedWriter(fw);
		    bw.write(schedule);
		    bw.newLine();
		    bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public ArrayList<String> readFromFile() {
		// Each CSV entry is one or more dates seven days apart
		// <date1-week1>,<date2-week2>, ..., <dateN-weekN>
		ArrayList<String> r = new ArrayList<>();
		try (BufferedReader b = new BufferedReader(new FileReader("schedules.csv"))) {
		    String line;
	    	while ((line = b.readLine()) != null) {
			    if (line.strip() != "") {
	    			r.add(line);
	    		}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return r;
	}
}
