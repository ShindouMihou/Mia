## Mia
A simple, easy-to-use, pure-Java and dependency-free localization library.

## ❓ What is Mia?
Mia is a very simple to use localization library that allows you to grab localized strings that your application 
uses in a readable and clean format while also having a simple API to use.

## 📦 Installation
To install with Maven, all you have to do is add this dependency:
```xml
<dependency>
    <groupId>pw.mihou</groupId>
    <artifactId>Mia</artifactId>
    <version>1.0.0</version>
</dependency>
```

If you use Gradle, you can also use the following:
```groovy
implementation 'pw.mihou:Mia:1.0.0'
```

## 🗯 How does Mia files look?
You can have a glance at the `examples` folder for a full glance of what a `.mia` file look but in general, Mia has 
a few rules in place similar to a `.env` file:
- A `.mia` file can tell Mia to use a different name by adding `#(some_name_here)` line inside the file, as seen on `en_US.mia` file.
- A `.mia` file can have no categories at all but they will be listed as `no_category` by the parser.
- A `.mia` file **MUST HAVE A KEY-VALUE** in the format: `key=value` **without quotations**.
- A `.mia` file can have categories by specifying them before the items: `#[some_category_here]`.
- All items after a category line will be marked as part of that category.

If you only want to know the syntaxes of a `.mia` file, here are the list of them:
- `#(some_name_here)`: This is used to specify the name of the locale (by default, Mia will use the file name unless this line is added).
- `#[some_category_here]`: This is used to specify the categories of the items that follows after this line (this will end until the next category line).
- `key=value`: This is used to specify the key-value pair locale, remember that quotations will be included, so do not wrap the value with quotations.

## 🎁 How to use the API?
To use the Mia API, you first have to create a `Mia` object by specifying the directory where the `.mia` files are located. You can 
also opt to use the default setting which uses `locales` folder as the directory.
```java
Mia customDirectory = Mia.from("some_directory");
Mia mia = Mia.ofDefault();
```

After creating the Mia object, it will immediately start to rumble through the directory for all `.mia` files and parses them accordingly
before saving them into different `LocalMia` objects where you can fetch them accordingly, to fetch a `LocalMia` object, 
all you have to do is use the method `getLocale(locale)`, for example (using `examples` folder):
```java
Mia mia = Mia.from("examples");

// Get the English locale (en_US.mia).
LocalMia english = Mia.getLocale("english");

// Prints the line "You did not input a name!" from the en_US.mia file.
// This is O(1).
System.out.println(english.get("errors", "no_name"));

// Prints the line "You did not input a name!" from the en_US.mia file.
// This is not O(1).
System.out.println(english.get("no_name"));
```