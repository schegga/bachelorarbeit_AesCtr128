# bachelorarbeit_AesCtr128

## Bachelorarbeit: Implementierung und Analyse des AES-CTR-128 Verfahrens in Java

### Ordner `aes128ctr_0_implementation`:
Für die Implemetation wurde das Build-Tool Gradle verwendet. Es muss eine Java-Version am ausführenden System installiert sein. Es wird zur Ausführung die Java-Version verwendet, auf welche die JAVA_HOME Variable des ausführenden Systems zeigt.
Nachdem klonen des Repos gradle builden: Dazu das Terminal im Ordner `aes128ctr_0_implementation` öffnen und `./gradlew build`(unix) oder `gradle build`(windows) ausführen.
- **AesBlockCipher.java**: 
  - Führt die Verschlüsselung eines 16 Byte großen Input-Blocks durch.
  - Generiert Rundenschlüssel mittels `public int[] expandKey128(byte[] key)`.
  - Führt die eigentliche Verschlüsselung eines 16 Byte Inputs mittels `public byte[] blockEncryption128(int[] input, int[] roundKeys)` aus.
  - **Hinweis**: Es kann eine Instanz dieser Klasse erstellt werden und die `blockEncryption128`-Methode kann verwendet werden, um einen einzelnen Block zu verschlüsseln. Sie funktioniert nur auf Eingaben von 16-Byte großen Inputblöcken und von 11 passenden Rundenschlüsseln, die in einem int-Array mit insgesamt 44 Einträgen gespeichert werden. Es wird hier keine Abfrage implementiert, welche die Korrektheit der Aufrufparameter prüft. Die Klasse `CtrAes128` stellt sicher, dass dies passiert. Die Korrektheit der Funktionen bei entsprechender Eingabe wurde in `test/java/aes/core/AesBlockCipherTest.java` validiert mittels geeigneter Testparameter.

- **CtrAes128.java**: 
  - Stellt die Hauptfunktionen `public byte[] ctrAes128EncryptionSequential(byte[] plaintext, AesKey128 aesKey128, int[] nonce)` zur Verfügung, um einen Plaintext sequentiell zu verschlüsseln.
  - Stellt durch den Eingabeparameter `AesKey128 aesKey128` sicher, dass nur Schlüssel der Länge 128-bit eingegeben werden können.
  - Ruft die Funktionen `expandKey128` und `blockEncryption` aus der Klasse `AesBlockCipher.java` auf, um sicherzustellen, dass die passenden Eingaben für diese Funktionen getätigt werden, um korrekt zu verschlüsseln gemaäß des AES-CTR-128 Verfahrens.
  - `public byte[] ctrAes128EncryptionParallel(byte[] plaintext, AesKey128 aesKey128, int[] nonce, int numThreads)` verschlüsselt parallel. Es können hier zusätzlich die Anzahl der Threads bestimmt werden, welche parallel die Verschlüsselung ausführen sollen. 
  - -->Das Ergebnis der beiden Funktionen ist das gleiche. Die Korrekthei wurde in `test/java/aes/modes/CtrTest.java` validiert mittels geeigneter Testparameter.

- **AesKey128.java**: 
  - Dient dazu, sicherzustellen, dass nur Schlüssel der Länge 128-bit als Eingabe für die `ctrVerschlüsselungsfunktionen` in `CtrAes128.java` verwendet werden können.
  - Die korrekte Funktionsweise wurde in `test/java/aes/utils/AesKeyTest.java` validiert mittels geeigneter Testparameter. 

- **Benchmark.java**: 
  - Testet die sequentielle und parallele Implementierung und vergleicht sie mit der äquivalenten Aes128Ctr-Verschlüsselung, die durch die `javax.crypto`-Standardbibliothek bereitgestellt wird.
  - Kann mittels `./gradlew benchmark`(unix) oder `gradle benchmark`(windows)  ausgeführt werden. Dazu das Terminal im Ordner `aes128ctr_0_implementation` öffnen. (Es wird zur Ausführung die Java-Version verwendet, auf welche die JAVA_HOME Variable des ausführenden Systems zeigt.)
  - Innerhalb dieser Klasse werden Testdateien eingelesen aus dem Ordner `aes128ctr_1_testdateien_benchmark/0_input`. Die Variable `String fileName = "input_10000KB.txt";` kann hier geändert werden, um die entsprechende Datei aus diesem `0_input` Ordner auszuwählen. Es können in dieser Datei auch die Anzahl der auszuführenden Verschlüsselungen für die sequentielle, parallele und `javax` Implementierung in der jeweiligen `for`-Schleife angepasst werden. Es wird die Zeit gemessen für jede Verschlüsselung, und der Mittelwert wird berechnet und ausgegeben. Die verschlüsselten Dateien werden in `aes128ctr_1_testdateien_benchmark/1_encrypt` gespeichert.

Alle Tests können mit `./gradlew test`(unix) oder `gradle test`(windows) ausgeführt werden. Dazu das Terminal im Ordner `aes128ctr_0_implementation` öffnen. Es wird zur Ausführung die Java-Version verwendet, auf welche die JAVA_HOME Variable des ausführenden Systems zeigt. Ein Testbericht findet sich in `aes128ctr_0_implementation/build/reports/tests/test/index.html`.

### Ordner `aes128ctr_1_testdateien_benchmark`:

- Dient zur Ablage der Input-Dateien und zur Ablage der verschlüsselten Dateien der Klasse `Benchmark.java`.
- Innerhalb `input_0` befindet sich eine `generate_files.sh`, welche genutzt werden kann, um Input-Dateien des `.txt`-Formats zu erzeugen. Diese können dann auch in `Benchmark.java` getestet werden. Dateien von 1KB bis 10.000KB sind bereits standardmäßig vorhanden um direkt testetn zu können. Größere Dateien müsssen mit `generate_files.sh` erzeugt werden.

### Ordner `aes128ctr_2_analyse_benchmark`:

- Hier sind die Messwerte die für die Bachelorarbeit genommen wurden für den Vergleich der unterschiedlichen Implementierungen. Die Klasse `Benchmark.java` wurde hierfür verwendet um die Messwerte zu erzeugen.
- Es wurden sieben Dateien verwendet. Diese Dateien hatten die Größen 1 KB, 10 KB, 100 KB, 1.000 KB, 10.000 KB, 100.000 KB und 1.000.000 KB. Es wurde auf 2 Systemen getestet. Einerseits auf einer Apple M1 Pro CPU mit macOS Sonoma 14.0 und andererseits auf einem AMD Ryzen 5 4600H Prozessor unter dem Betriebssystem Microsoft Windows 11 Home.
- Die Messwerte wurden in `benchmark_messdaten_macos.xlsx` und `benchmark_messdaten_windows.xlsx` festgehalten.
- Mittels der beiden Python-Skripts `benchmark_analyse_createGraphs_macos.py` und `benchmark_analyse_createGraphs_windows.py`können die Graphen zu den Messwerten erstellt werden. Die Graphen werden in `benchmark_analyse_graphs/` gespeichert. Die Graphen wurden in der Bachelorarbeit verwendet, um die Implementierung zu analysieren.