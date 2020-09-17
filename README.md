# demo
To execute the application you should use this command :
```
./mvnw spring-boot:run -Dspring-boot.run.arguments="--file.name=[the real name of the file.txt] --word.to.match=[the word to match]"
```
For exemple :
```
./mvnw spring-boot:run -Dspring-boot.run.arguments="--file.name=inPut.txt --word.to.match=word"
```

If the input word match with any word of the input file the result will be saved in outPut file, a new file will be generated.
