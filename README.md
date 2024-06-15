# KVBF
KVBF - **Key-Value Binary File**.
File format to store key-value data in binary form _(like JSON)_

To start using KVBF download this project, 
make new class, import <ins>com.cicdez.kvbf</ins>, 
and write code there

USE JAVA 8!

First steps (Compounds)
---

First of all you need to make your "root" map-kvbf, 
use `CompoundKVBF` object for this:

```java
public class Main {
    public static void main(String[] args) {
        //Makes a compound
        CompoundKVBF myCompound = new CompoundKVBF();
    }
}
```
CompoundKVBF is Map, where you put your keys and values

Put some primitive values as you want:
```java
public class Main {
    public static void main(String[] args) throws IOException {
        CompoundKVBF myCompound = new CompoundKVBF();
        
        //Put some things to map
        myCompound.putString("MyString", "Hello");
        myCompound.putInteger("MyInteger", 282);
        myCompound.putBoolean("MyBoolean", true);
        myCompound.putFloat("MyFloat", 353.69f);
    }
}
```

Use `put` to insert your data to map

Lists
---

Some sort of arrays also exists in KVBF. They called lists:
```java
public class Main {
    public static void main(String[] args) throws IOException {
        //Make list
        ListKVBF list = new ListKVBF();
        
        //Adding some stuff to list
        list.addString("MyString");
        list.addInteger(1);
        list.addInteger(2);
        
        //Some complex stuff
        CompoundKVBF compound = new CompoundKVBF();
        compound.putBoolean("Bar", true);
        
        //Adding more complex stuff
        list.addCompound(compound);
    }
}
```
Default `ListKVBF` can store every possible KVBF object

For more memory efficiency you can use `TypedListKVBF`.
It stores only KVBFs of given type:
```java
public class Main {
    public static void main(String[] args) throws IOException {
        //Making TypedList
        TypedListKVBF typedList = new TypedListKVBF(KVBFTypes.STRING_TYPE);
        
        //Add some stuff
        typedList.add(new StringKVBF("Hello"));
        typedList.add(new StringKVBF("World"));
        
        //Will drop error, because an integer isn't a string
        typedList.add(new IntegerKVBF(56));
    }
}
```

Writing to file
---

To store your data to file call `KVBFileIO#write`:
```java
public class Main {
    public static void main(String[] args) throws IOException {
        //Some useful information
        CompoundKVBF myCompound = new CompoundKVBF();
        myCompound.putString("Name", "C1cDez");
        myCompound.putInteger("IQ", -78);
        
        //Storing data to file 'C:/Output/file.kvbf'
        KVBFileIO.write(new File("C:/Output/file.kvbf"), myCompound);
    }
}
```

Reading from file
---

To read data from file call `KVBFileIO#read`:
```java
public class Main {
    public static void main(String[] args) throws IOException {
        CompoundKVBF myCompound = KVBFileIO.read(new File("C:/Output/file.kvbf"));
        String name = myCompound.getString("Name");
        System.out.println(name);
    }
}
```

---

That's all!
Enjoy!
