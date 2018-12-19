[![Build Status](https://travis-ci.com/JamesCollerton/Planning_Permission.svg?branch=master)](https://travis-ci.com/JamesCollerton/Planning_Permission)
[![codecov](https://codecov.io/gh/JamesCollerton/Planning_Permission/branch/master/graph/badge.svg)](https://codecov.io/gh/JamesCollerton/Planning_Permission)

# Planning Permission

This is a short Scala Spark project based around planning permissions in the North East of England. It is split into six main exercises in order to explore the data available in the provided `json` file.

## What is Spark?

As this is my first time using Spark I thought it would be good to give a quick overview of what exactly Spark was.

## Running The Exercises

The task is split into six main exercises, each of which will take in two arguments from the command line (with the exception of exercise four). The first argument will represent the location of a resource in the resources folder that we would like to run the exercise over. The second will represent a relative file path of where we would like to write the result to. For example, the following would represent two valid arguments for exercise one:

```
src/main/resources/data/planning-applications-weekly-list.json solutions/ExerciseOne.txt
```

Exercise four requires an additional argument which is the number of agents we would like to limit for. The below represents a valid set of arguments for exercise four.

```
src/main/resources/data/planning-applications-weekly-list.json solutions/ExerciseFour.txt 10
```

The whole project was worked on in `IntelliJ`, so it may be easiest to open it as an `IntelliJ` project and run it from there. **The solutions to each exercise can be found in the solutions folder at the base of the repository**.

## Builds

The project is set up with a [Travis CI](https://travis-ci.com/JamesCollerton/Planning_Permission) build responsible for building the code, testing and running code coverage whenever we push to the master branch of the repository.

## Testing

All testing was done with `ScalaTest`, with code coverage being monitored using [CodeCov](https://codecov.io/gh/JamesCollerton/Planning_Permission).

## Logging

Logging is handled using `slf4j`, `logback` and `LazyLogging`.

## Documentation

The project should compile `ScalaDocs` using `sbt doc`.

## Recognised Points for Improvement

There are a number of points that could be improved about this project, each is contained under the relevant section below.

### Writing to Files

In all of the exercises I collect the results from a `DataFrame` into an array and then output it to a file. However, looking slightly further into Spark there may well be much better ways of doing this.

### Exercise Specific

For each of the following exercises the solution could be improved:

- **Exercise Three:** Assumes the `CASEOFFICER` column exists.
- **Exercise Four:** Assumes the `AGENT` column exists, does not filter for blank agents, does not do any fuzzy matching for names (e.g. removing prefixes).
- **Exercise Five:** Assumes the `CASETEXT` column exists, does not remove punctuation, does not convert everything to the same case, does not remove whitespace.
- **Exercise Six:** Assumes the `PUBLICCONSULTATIONENDDATE` and `PUBLICCONSULTATIONSTARTDATE` columns exist, does not check to see if they are valid date strings, does not check to see if start date is before end date.

### Testing

Although the coverage of the code is good, the testing could be a lot more thorough. Most of the current tests only cover happy paths and don't deal particularly well with invalid files and similar.

### Logging

Logging is minimal and was only introduced to demonstrate an ability to work with the Scala logging tools. Improvements would be to improve the use of logging and also to add a `logback` appender that would be able to write to another location in order to analyse logs centrally, for example using the `ELK` stack. Additionally something like AOP could be used to remove the need for all of the log calls at the entrance and exit of methods.

### Error Handling

Error handling has not been overly prioritised in the program, however an awareness of all of the different types of error that can be thrown should be evident in the testing. For example in question four if you provide an argument which is not a positive integer then the program will throw an exception, however I am fully aware of this. An improvement would be to handle these errors more gracefully. 

### Code Style

Currently there are no code style guidelines implemented. However, in a production system I would have an `IntelliJ XML` file to enforce certain standards.
