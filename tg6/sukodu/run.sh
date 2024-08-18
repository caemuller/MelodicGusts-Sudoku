#!/bin/bash
javac sukodu/*.java -d ./classes && java -cp ./classes sukodu.SudokuMain
