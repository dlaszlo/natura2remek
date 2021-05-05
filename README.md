## Natura2Remek

A program a NaturaSoft számlázó program főkönyvi feladását alakítja át a Remek könyvelő program input formátumára.

### Beállítások 

kontir.yml

### Futtatás

```
./gradlew bootRun
```

Az input állományt a "NaturaSoft könyvtár"-ba (lásd kontir.yml) kell másolni, a következő fájlnévvel: ÉÉÉÉ_HH_feladas.csv. 
Például: 2021_04_feladas.csv.

Az output állomány a "Remek könyvtár"-ba (lásd kontir.yml) kerül, a következő fájlnévvel: ÉÉÉÉ_HH_remek.csv. 
Például: 2021_04_remek.csv.


