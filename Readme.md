# CSC201 Final Project
This Readme is a bit lengthy, but should provide all information necessary to contribute to the repository.

## Forking a Repository
The easiest way to fork this project is to go to the top right corner of this page and click *Fork*.

----
## Importing Project into Eclipse
Eclipse and Git Bash don't like cooperating with each other.  You have to trick Eclipse into accepting the cloned repository.

### Setting up Eclipse
- Create a folder called FinalProject
- Open Eclipse and set the workspace to FinalProject
- Create a Java Project named posbravo2
- Close Eclipse

### Adding Git Repository to Eclipse
Make sure you are in the folder **containing** *FinalProject/*, **not** inside *FinalProject/*.  Execute the following commands:

#### Windows
    git clone https://github.com/USERNAME/FinalProject.git temp
    robocopy /is /e ./temp/.git ./FinalProject/.git
    rd /s /q temp
    cd FinalProject
    git reset --hard HEAD
    git fetch

#### Mac / Linux
    git clone https://github.com/USERNAME/FinalProject.git temp
    mv ./temp/.git ./FinalProject/.git
    rm -rf temp
    cd FinalProject
    git reset --hard HEAD
    git fetch

Mac / Linux code hasn't been tested, but should work.  Feel free to test and update code.

### Updating Eclipse
- Open Eclipse and set the workspace to FinalProject
- Refresh the Package Explorer by pressing F5

----
## Adding JAR Files to Classpath
The project will throw errors because the required JAR files aren't automatically added to the classpath.

- Right click posbravo2 -> Build Path -> Configure Build Path...
- Click Libraries tab -> Add External JARs
- Navigate to posbravo2/lib/ -> Select all -> Press Open -> Press OK

----
## Pushing Files to Forked Repository
Pushing the files *Readme.md* and *HelloWorld.java* with the commit message *Sample git*.

    git add Readme.md
    git add HelloWorld.java
    git commit -m "Sample git"
    git push -u origin hibernate

----
## Syncing Fork
Instructions on how to sync forked repositories with original.  Forks should be synchronized after they have been merged with original.

    git remote add upstream https://github.com/csc201/FinalProject.git
    git fetch upstream
    git merge upstream/hibernate
    git commit -m "sync fork"
    git push -u origin hibernate

----
## Remarks
- posbravo is original code.
- posbravo2 is current development (this is the project you need to import).