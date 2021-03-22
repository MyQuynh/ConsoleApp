import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateManager {

    public Date convertStringToDate(String sDate1){
        SimpleDateFormat formatDate = new SimpleDateFormat("dd/MM/yyyy");
        Date date;
        try {
            date= formatDate.parse(sDate1);
            return date;
        } catch (ParseException e) {
            System.out.println("Wrong date format, please enter again (dd/MM/yyyy)");
        }
        return null;

    }

    public String convertDateToString(Date date){

        //DateFormat inputFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
        DateFormat outputFormat = new SimpleDateFormat("dd/MM/yyyy");

        return outputFormat.format(date);
    }

}
