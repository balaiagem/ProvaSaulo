package Utilitario;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Funcoes {
    
    public static Calendar stringToDate (String sData) throws ParseException{
    
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date data = sdf.parse(sData);
        Calendar retorno = Calendar.getInstance();
        retorno.setTime(data);

        return retorno;
    }
    
    public static String dateToString(Calendar C){
        Date data = C.getInstance().getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(data);
    }
    
}
