# Neptune's Pride Java

A Java wrapper around [Neptune's Pride's](https://np.ironhelmet.com/) API.

Uses [Gson](https://github.com/google/gson) for deserialization.

# Example Usage

```java
import io.github.eutro.neptunespride.NeptunesPride;
import io.github.eutro.neptunespride.types.ScanningData;

public class Main {
    public static void main(String[] args) throws Throwable {
        ScanningData data = NeptunesPride.fetch(args[0], args[1]);
        System.out.printf("Hello, %s!", data.players.get(data.playerUid));
    }
}
```
