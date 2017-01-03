REM set the class path,
REM assumes the build was executed with maven copy-dependencies
SET ECAFETERIA_CP=utente.consoleapp\target\ecafeteria.utente.consoleapp-1.0-SNAPSHOT.jar;utente.consoleapp\target\dependency\*;

REM call the java VM, e.g, 
java -cp %ECAFETERIA_CP% eapli.ecafeteria.utente.consoleapp.ECafeteriaUtenteApp
