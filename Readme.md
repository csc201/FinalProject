CSC201 Final Project.

Instructions on How to Import Posbravo2 into Eclipse.
Step1:  Right click on package explorer perspective->Import->git->project from git->URI->paste URI and follow the eclipse           flow (it should be easy from this point).
Step2:  Create new Eclipse project named posbravo2.
Step3:  Right click on posbravo2 project and Import->general->file system and browse to local git folder and select       
        posbravo2 folder then check all the files to import.
Step4:  We expect to see the errors because of the lib folder path configurations. To fix this: Right click on   
        posbravo2->Build Path->Configure Build Path->Click on Add External JARs->Browse to local git folder and posbravo2           and lib folder->select all the files from this folder->click Open->click OK 

Remarks:
posbravo is original code.  
posbravo2 is current development. (This is the project that you need to import)
