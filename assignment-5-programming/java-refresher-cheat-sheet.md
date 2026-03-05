# Java Refresher Cheat Sheet

*With comparisons to Go, Python, and Haskell*

---

## 1. Basics

### Hello World

```java
public class Main {
    public static void main(String[] args) {
        System.out.println("Hello, world!");
    }
}
```

| Go | Python | Haskell |
|---|---|---|
| `fmt.Println("Hello")` | `print("Hello")` | `main = putStrLn "Hello"` |

Since Java 21, you can simplify with an unnamed main:

```java
void main() {
    System.out.println("Hello, world!");
}
```

### Variables & Type Inference

```java
int x = 42;                    // explicit type
var name = "Jonathan";          // local type inference (Java 10+)
final double PI = 3.14159;     // immutable binding
```

- `var` is local only — no inference on method signatures, fields, or return types.
- `final` ≈ Go's implicit immutability for certain types, Haskell's default immutability, Python's convention-only `UPPER_CASE`.

### Primitive Types

| Java | Size | Go equiv | Python equiv | Haskell equiv |
|---|---|---|---|---|
| `byte` | 8-bit | `int8` | `int` | `Int8` |
| `short` | 16-bit | `int16` | `int` | `Int16` |
| `int` | 32-bit | `int32` | `int` | `Int` |
| `long` | 64-bit | `int64` | `int` | `Integer` |
| `float` | 32-bit | `float32` | `float` | `Float` |
| `double` | 64-bit | `float64` | `float` | `Double` |
| `boolean` | 1-bit | `bool` | `bool` | `Bool` |
| `char` | 16-bit | `rune` | `str[0]` | `Char` |

Primitives are not objects. Their boxed versions (`Integer`, `Double`, etc.) are — autoboxing converts between them.

---

## 2. Control Flow

### Conditionals

```java
// Standard if-else
if (x > 0) {
    // ...
} else if (x == 0) {
    // ...
} else {
    // ...
}

// Switch expression (Java 14+) — returns a value
var label = switch (day) {
    case MONDAY, FRIDAY -> "busy";
    case SATURDAY, SUNDAY -> "free";
    default -> "normal";
};
```

### Pattern Matching in Switch (Java 21+)

```java
// Approximating Haskell-style pattern matching on sum types
sealed interface Shape permits Circle, Rect {}
record Circle(double r) implements Shape {}
record Rect(double w, double h) implements Shape {}

double area(Shape s) {
    return switch (s) {
        case Circle c -> Math.PI * c.r() * c.r();
        case Rect r   -> r.w() * r.h();
    };
}
```

Haskell equivalent for comparison:

```haskell
data Shape = Circle Double | Rect Double Double
area (Circle r)  = pi * r * r
area (Rect w h)  = w * h
```

### Loops

```java
// for loop (C-style)
for (int i = 0; i < 10; i++) { ... }

// enhanced for (like Python's `for x in xs`, Go's `for _, x := range xs`)
for (var item : list) { ... }

// while / do-while
while (cond) { ... }
do { ... } while (cond);
```

---

## 3. OOP Essentials

### Classes & Interfaces

```java
// Interface — like Go interface but nominal
public interface Printable {
    void print();                         // abstract method
    default String format() { return ""; } // default impl (Java 8+)
}

// Abstract class — partial implementation
public abstract class Animal {
    private String name;
    public Animal(String name) { this.name = name; }
    public abstract String speak();
    public String getName() { return name; }
}

// Concrete class
public class Dog extends Animal implements Printable {
    public Dog(String name) { super(name); }
    @Override public String speak() { return "Woof"; }
    @Override public void print() { System.out.println(getName()); }
}
```

**Key differences:**

| Concept | Java | Go | Haskell |
|---|---|---|---|
| Inheritance | `extends` (single) | Embedding (composition) | No inheritance; typeclasses |
| Interfaces | Nominal (`implements`) | Structural (implicit) | Typeclasses |
| Abstract methods | `abstract` keyword | N/A | Typeclass minimal definition |
| Multiple inheritance | Interfaces only | Multiple embedding | Multiple typeclass instances |

### Records (Java 16+)

Immutable data carriers — like Haskell's `data` or Python's `@dataclass(frozen=True)`:

```java
public record Point(double x, double y) {}

// Auto-generates: constructor, getters (x(), y()), equals, hashCode, toString
var p = new Point(3.0, 4.0);
double dist = Math.sqrt(p.x() * p.x() + p.y() * p.y());
```

### Sealed Types (Java 17+)

Restrict which classes can extend a type — enables exhaustive pattern matching:

```java
public sealed interface Expr permits Lit, Add, Mul {}
public record Lit(int value) implements Expr {}
public record Add(Expr left, Expr right) implements Expr {}
public record Mul(Expr left, Expr right) implements Expr {}
```

This is Java's closest analog to Haskell ADTs. The compiler can verify exhaustive matching in switch expressions.

---

## 4. Generics

### Basics

```java
public class Box<T> {
    private T value;
    public Box(T value) { this.value = value; }
    public T get() { return value; }
}

// Bounded type parameters — like Haskell's typeclass constraints
public <T extends Comparable<T>> T max(T a, T b) {
    return a.compareTo(b) >= 0 ? a : b;
}
```

Haskell equivalent: `max :: (Ord a) => a -> a -> a`

### Wildcards (PECS: Producer Extends, Consumer Super)

```java
// Read from (producer) — upper bound
void printAll(List<? extends Number> nums) { ... }

// Write to (consumer) — lower bound
void addInts(List<? super Integer> list) { list.add(42); }
```

**Critical caveat:** Java generics are erased at runtime. `List<String>` and `List<Integer>` are the same type at runtime. This is unlike Haskell's parametric polymorphism (which is real) and more like Go's pre-1.18 `interface{}` workaround.

---

## 5. Functional Java (Java 8+)

### Lambdas & Functional Interfaces

```java
// Lambda syntax
Function<Integer, Integer>  square = x -> x * x;
BiFunction<Integer, Integer, Integer> add = (a, b) -> a + b;
Predicate<String>           isLong = s -> s.length() > 10;
Consumer<String>            log    = System.out::println;  // method reference

// Key functional interfaces (java.util.function):
// Function<T, R>      — T -> R
// BiFunction<T, U, R> — (T, U) -> R
// Predicate<T>        — T -> boolean
// Consumer<T>         — T -> void (side effect)
// Supplier<T>         — () -> T
```

### Streams API

```java
List<String> names = List.of("Alice", "Bob", "Charlie", "Dave");

// Pipeline: filter -> transform -> collect
List<String> result = names.stream()
    .filter(n -> n.length() > 3)
    .map(String::toUpperCase)
    .sorted()
    .collect(Collectors.toList());
// ["ALICE", "CHARLIE", "DAVE"]

// Reduction
int sum = IntStream.rangeClosed(1, 100).sum();

// Grouping
Map<Integer, List<String>> byLength = names.stream()
    .collect(Collectors.groupingBy(String::length));
```

**Comparison to Haskell:**

| Operation | Java Stream | Haskell |
|---|---|---|
| Filter | `.filter(p)` | `filter p xs` |
| Map | `.map(f)` | `map f xs` / `fmap f xs` |
| Fold/reduce | `.reduce(id, op)` | `foldl' op id xs` |
| Take | `.limit(n)` | `take n xs` |
| Flatmap | `.flatMap(f)` | `concatMap f xs` / `>>= f` |

Streams are lazy (like Haskell lists) but single-use (unlike Haskell lists).

### Optional\<T\> (Java's Maybe)

```java
Optional<String> name = Optional.of("Jonathan");
Optional<String> empty = Optional.empty();

// Chaining — like Haskell's >>= for Maybe
String upper = name
    .filter(n -> n.length() > 3)
    .map(String::toUpperCase)
    .orElse("UNKNOWN");
```

Haskell equivalent:

```haskell
name >>= (\n -> guard (length n > 3) >> return n) <&> map toUpper
```

---

## 6. Collections

### Core Interfaces & Implementations

```
Collection
├── List      →  ArrayList (array-backed), LinkedList
├── Set       →  HashSet, TreeSet (sorted), LinkedHashSet (ordered)
└── Queue     →  PriorityQueue, ArrayDeque

Map (separate hierarchy)
└── Map       →  HashMap, TreeMap (sorted), LinkedHashMap (ordered)
```

### Common Operations

```java
// Creation (immutable)
var list = List.of(1, 2, 3);
var set  = Set.of("a", "b", "c");
var map  = Map.of("key", "value", "k2", "v2");

// Mutable creation
var list = new ArrayList<>(List.of(1, 2, 3));
var map  = new HashMap<String, Integer>();
map.put("a", 1);
map.getOrDefault("b", 0);
map.computeIfAbsent("c", k -> k.length());

// Iteration
map.forEach((k, v) -> System.out.println(k + "=" + v));
```

**Go comparison:** Go has slices and maps built-in; Java's collections are library types with richer APIs but more ceremony.

**Python comparison:** Python's `list`, `dict`, `set` are simpler; Java's are more explicitly typed and offer sorted/concurrent variants.

---

## 7. Error Handling

### Exceptions

```java
// Checked exceptions — must be declared or caught
public String readFile(String path) throws IOException {
    return Files.readString(Path.of(path));
}

// Unchecked exceptions — RuntimeException subclasses
throw new IllegalArgumentException("bad input");

// Try-with-resources (like Python's `with`, Go's `defer`)
try (var reader = new BufferedReader(new FileReader(path))) {
    return reader.readLine();
} catch (FileNotFoundException e) {
    // handle
} catch (IOException e) {
    // handle
} finally {
    // always runs
}
```

### Comparison

| Style | Language | Approach |
|---|---|---|
| Exceptions (checked + unchecked) | Java | Compiler-enforced for checked |
| Value-based errors | Go | `val, err := f(); if err != nil` |
| Exceptions only (unchecked) | Python | `try/except` |
| Monadic (Either/Maybe) | Haskell | `Either Error Value`, composed with `>>=` |

---

## 8. Concurrency

### Traditional Threads

```java
// Creating threads
Thread t = new Thread(() -> System.out.println("Hello from thread"));
t.start();
t.join();

// Synchronized access
synchronized (lock) {
    sharedState++;
}
```

### Virtual Threads (Java 21+ — Project Loom)

```java
// Lightweight threads — conceptually similar to goroutines
try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
    IntStream.range(0, 10_000).forEach(i ->
        executor.submit(() -> {
            Thread.sleep(Duration.ofSeconds(1));
            return i;
        })
    );
}
```

### CompletableFuture (Async/Promise-like)

```java
CompletableFuture.supplyAsync(() -> fetchData())
    .thenApply(data -> process(data))
    .thenAccept(result -> save(result))
    .exceptionally(ex -> { log(ex); return null; });
```

### Comparison

| Concept | Java | Go | Haskell |
|---|---|---|---|
| Lightweight threads | Virtual threads (21+) | Goroutines | Green threads (RTS) |
| Message passing | BlockingQueue | Channels | Chan / TChan |
| Shared state | synchronized / Lock | Mutex | MVar / TVar (STM) |
| Async composition | CompletableFuture | select {} | async / STM |

---

## 9. Build Tools & Project Structure

### Maven (pom.xml)

```xml
<project>
  <groupId>com.example</groupId>
  <artifactId>myapp</artifactId>
  <version>1.0</version>
  <dependencies>
    <dependency>
      <groupId>org.junit.jupiter</groupId>
      <artifactId>junit-jupiter</artifactId>
      <version>5.10.0</version>
      <scope>test</scope>
    </dependency>
  </dependencies>
</project>
```

### Gradle (build.gradle.kts — Kotlin DSL)

```kotlin
plugins {
    java
}
dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter:5.10.0")
}
```

### Standard Project Layout

```
src/
├── main/
│   ├── java/com/example/   ← source code
│   └── resources/           ← config files, etc.
└── test/
    └── java/com/example/   ← test code
```

**Go comparison:** `go build` / `go.mod` — far simpler. Java's build ecosystem is more complex but more configurable.

---

## 10. Testing (JUnit 5)

```java
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

class MathTest {
    @Test
    void additionWorks() {
        assertEquals(4, 2 + 2);
    }

    @Test
    void throwsOnNull() {
        assertThrows(NullPointerException.class, () -> {
            String s = null;
            s.length();
        });
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5})
    void allPositive(int n) {
        assertTrue(n > 0);
    }
}
```

---

## 11. Useful Modern Idioms

### Text Blocks (Java 13+)

```java
String json = """
    {
        "name": "Jonathan",
        "role": "security engineer"
    }
    """;
```

### Instanceof Pattern Matching (Java 16+)

```java
// Old way
if (obj instanceof String) {
    String s = (String) obj;
    System.out.println(s.length());
}

// New way — binds variable in one step
if (obj instanceof String s) {
    System.out.println(s.length());
}
```

### Helpful String / Collection Methods

```java
// String
"hello".isBlank();                    // true for whitespace-only
"a,b,c".split(",");                   // ["a", "b", "c"]
"  hi  ".strip();                     // "hi" (Unicode-aware trim)

// Collections utility
Collections.unmodifiableList(list);   // immutable view
List.copyOf(mutableList);             // immutable copy
Stream.toList();                      // Java 16+ — immutable list from stream
```

---

## 12. Quick Rosetta Stone

| Task | Java | Go | Python | Haskell |
|---|---|---|---|---|
| Print | `System.out.println(x)` | `fmt.Println(x)` | `print(x)` | `print x` |
| String format | `"Hi %s".formatted(n)` | `fmt.Sprintf("Hi %s", n)` | `f"Hi {n}"` | `"Hi " ++ n` |
| List/slice literal | `List.of(1,2,3)` | `[]int{1,2,3}` | `[1,2,3]` | `[1,2,3]` |
| Map literal | `Map.of("a",1)` | `map[string]int{"a":1}` | `{"a":1}` | `Map.fromList [("a",1)]` |
| Null check | `if (x != null)` | `if x != nil` | `if x is not None` | Use `Maybe` |
| Lambda | `x -> x + 1` | `func(x int) int { return x+1 }` | `lambda x: x+1` | `\x -> x + 1` |
| Main entry | `public static void main(String[] args)` | `func main()` | `if __name__ == "__main__":` | `main :: IO ()` |

---

## 13. Common Pitfalls

- **`==` vs `.equals()`**: `==` compares references for objects; `.equals()` compares values. Always use `.equals()` for String comparison.
- **Null pointer exceptions**: Java has no built-in null safety. Use `Optional`, `@NonNull` annotations, or defensive checks.
- **Mutability by default**: Unlike Haskell, everything is mutable unless you make it `final` or use immutable collections.
- **Type erasure**: Generic type info is gone at runtime — no `instanceof List<String>`.
- **Checked exceptions**: Lambdas in streams don't play well with checked exceptions. Wrap them or use unchecked variants.
- **Autoboxing overhead**: `List<Integer>` boxes every int. For performance-critical code, use primitive arrays or specialized collections.
