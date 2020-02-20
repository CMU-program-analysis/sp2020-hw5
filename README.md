# Homework 5: Context-Sensitive Interprocedural Analysis

For this homework you will be implementing an interprocedural analysis using [Soot](https://github.com/Sable/soot). 

## Requirements
The only prerequisite installation for this homework is JVM version 8+. The code targets Java 8, but this repo has
been tested with a Java 11 JVM as well. The project uses the Gradle build system, which ships with a wrapper that will
download all of the project dependencies for you. Building/testing should be as simple as running `./gradlew build` on
a *nix system, or `gradlew.bat build` on Windows. The project should build successfully, but you will fail two tests 
that we have helpfully provided for you.

## Submission
You will submit your entire repository to Gradescope. Gradescope does not automatically track your repository, so you
must resubmit every time you'd like the autograder to run again.
- Note: The autograder works by cloning your repository and syncing your `src/main/java/hw5/` with a repository based
on this template, except for the `InterAnalysisTransformer.java` and `IntraAnalysisTransformer.java` files. You should not have to create any new files to complete this homework, but if you do, only those
in the `hw5` directory will be included, and any other files you add or modify (e.g., creating a new package, adding a
library to the default `build.gradle`) will not be synced. You can test your submission by cloning a fresh copy of this
repository, replacing all of `hw5` with the contents of your `hw5`, and running `gradlew build`.

## Project Structure
The files you need to modify to complete this homework are in `src/main/java/hw5/`. You will need to modify 
`Context.java`, `Sigma.java`, `IntraSignAnalysis.java`, and `InterSignAnalysis.java`. The `*Transformer.java` files
are drivers for Soot that instantiate (and thus run) your analyses. We have written them for you, but you can use
them to output debugging information if you'd like. There is an example of this usage in `IntraAnalysisTransformer.java`
already. Note that the autograder will use the template version of these files, so your analysis should not rely on them.

## Recommendataions
- We highly recommend an IDE such as [IntelliJ IDEA](https://www.jetbrains.com/idea/) for this project. As a student,
you are eligible for a free license from JetBrains. If you'd like to use IDEA, you can import this homework as a Gradle
 project directly from GitHub using `File -> New -> Project from Version Control... -> Git`. 