package exception;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class SystemExceptionHandler {
private static final String LOG_FILE=("src/chatApplication/System.log");
public  static void hadleException(Exception e ){
    try (PrintWriter writer=new PrintWriter(new FileWriter(LOG_FILE,true))){
        writer.println("--Exception Details--");
        writer.println("Timestamp "+ System.currentTimeMillis());
        writer.println("Exception: "+e.getClass().getSimpleName());
        writer.println("Message: "+e.getMessage());
        writer.println("stack Trace: ");
        e.printStackTrace(writer);
} catch (IOException ioe) {
        System.err.println("Failed to write exception details to the log file: "+ioe.getMessage());
    }
}
    
}