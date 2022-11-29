import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

public class ObjectOperator {
 
  public void writeObject( Object object, OutputStream outputStream ) throws Exception {

    // FileOutputStream fileOutputStream = new FileOutputStream(path);
    ObjectOutputStream objectOutputStream = new ObjectOutputStream( outputStream );

    objectOutputStream.writeObject(object);
    objectOutputStream.flush();

    // objectOutputStream.close();
    // fileOutputStream.close();

  }

  public Object readObject( InputStream inputStream ) throws Exception {

    // FileInputStream fileInputStream = new FileInputStream( path );
    ObjectInputStream objectInputStream = new ObjectInputStream( inputStream );

    Object object = objectInputStream.readObject();

    // objectInputStream.close();
    // fileInputStream.close();

    return object;

  }

}
