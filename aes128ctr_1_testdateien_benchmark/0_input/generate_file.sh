#!/bin/bash

# Überprüfen, ob ein Argument angegeben wurde
if [ "$#" -ne 1 ]; then
    echo "Usage: $0 <file size in KB>"
    exit 1
fi

# Dateigröße aus dem ersten Argument holen
SIZE_KB=$1
TARGET_SIZE=$(( $SIZE_KB * 1000 ))

# Temporäre Datei leeren
> temp.txt

# Daten hinzufügen, bis die gewünschte Größe erreicht ist
while [ $(stat -f%z temp.txt) -lt $TARGET_SIZE ]; do
    dd if=/dev/urandom bs=1M count=1 2>/dev/null | LC_ALL=C tr -dc 'a-zA-Z0-9!@#$%^&*_+-' >> temp.txt
done


# Schneiden Sie die Datei auf die gewünschte Größe
head -c $TARGET_SIZE temp.txt > input_${SIZE_KB}KB.txt

# Löschen Sie die temporäre Datei
rm temp.txt

echo "File input_${SIZE_KB}KB.txt created successfully!"

# ./generate_file.sh 1000  # Erzeugt eine 1000KB (1MB) Datei