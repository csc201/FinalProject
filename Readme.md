# CSC201 Final Project
----
## Adding JAR files to classpath
The project will throw errors because the required JAR files aren't automatically added to the classpath.

| Step 1 | Right click posbravo2 -> Build Path -> Configure Build Path... |
| Step 2 | Click Libraries tab -> Add External JARs |
| Step 3 | Navigate to posbravo2/lib/ -> Select all -> Press Open -> Press OK |

The JAR files should now be in the classpath.

## Syncing Fork
Instructions on how to sync forked repositories with original.  Forks should be synchronized after they have been merged with original.

    git remote add upstream https://github.com/csc201/FinalProject.git
    git fetch upstream
    git merge upstream/hibernate
    git commit -m "sync fork"
    git push -u origin hibernate

## Remarks
- posbravo is original code.
- posbravo2 is current development (this is the project you need to import).