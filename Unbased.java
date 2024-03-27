import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.FileOutputStream;
import java.util.zip.InflaterInputStream;
import java.util.Base64;
import java.util.Scanner;

public class Unbased {

  public static void pipe(InputStream paramInputStream, OutputStream paramOutputStream) throws Exception {
    byte[] arrayOfByte = new byte[1024];
    int i;
    while ((i = paramInputStream.read(arrayOfByte)) > 0)
      paramOutputStream.write(arrayOfByte, 0, i); 
  }
  
  public static void unzip(byte[] paramArrayOfbyte) throws Exception {
    InflaterInputStream inflaterInputStream = new InflaterInputStream(new ByteArrayInputStream(paramArrayOfbyte));
    pipe(inflaterInputStream, System.out);
  }

  public static void main(String args[]) throws Exception {
    StringBuilder sb = new StringBuilder();
    Scanner scanner = new Scanner(System.in);
    while (scanner.hasNext()) {
      sb.append(scanner.nextLine());
    }
    scanner.close();
    unzip(Base64.getDecoder().decode(sb.toString()));
  }
}
