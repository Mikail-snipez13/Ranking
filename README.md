# Ranking with voting
The project is a web application based on spring-boot framework.
You can define your own categories and the persons can vote for each category.

## Content
1. [API](#API)
   1. [Token](#Token)
   2. [Teacher](#Teacher)

# API
The Host of the API is `https://teacherranking.ddns.net`

## Token
The path to the token interface is `/tickets`.


## Modes
```https://teacherranking.ddns.net/tickets?mode={value}``` <br>

| value  | description       | request type |
|--------|-------------------|--------------|
| create | create token      | GET          |
| status | get token status  | GET          |
| use    | use token         | POST         |


## Create Mode
URL -> `https://teacherranking.ddns.net/tickets?mode=create` <br>
return -> Token for Voting (e.g. XDFAEF)

## Status Mode

| Parameter    | Description      |
|--------------|------------------|
| mode         | must be 'status' |
| id           | ticket token     |

URL -> `https://teacherranking.ddns.net/tickets?mode=status&id={token}` <br>
return -> String - false | used | doesn't exist

## Use Mode

| Parameter    | Description                    |
|--------------|--------------------------------|
| mode         | must be 'use'                  |
| id           | ticket token                   |
| joker        | teacher name to increase value |
| unprepared   | teacher name to increase value |
| late         | teacher name to increase value |
| spoiled      | teacher name to increase value |
| party        | teacher name to increase value |
| smart        | teacher name to increase value |
| beauty       | teacher name to increase value |
| noMention    | teacher name to increase value |
| noInTime     | teacher name to increase value |
| dishonorable | teacher name to increase value |

URL -> `https://teacherranking.ddns.net/tickets?mode=use&id={token}&joker={teacher}&unprepared={teacher}&late={teacher}&spoiled={teacher}&party={teacher}&smart={teacher}&beauty={teacher}&noMention={teacher}&noInTime={teacher}&dishonorable={teacher}` <br>
return -> nothing

## Teacher
The path to the teacher interface is `/teacher`.

## All teacher
Get all teacher as JSON with the URL path `/teacher/all`.

## Ranking for the categories
The path to get the ranking for specific category is ``/teacher/best/{category}``.

| category     | description       |
|--------------|-------------------|
| joker        | return the 3 best |
| unprepared   | return the 3 best |
| late         | return the 3 best |
| spoiled      | return the 3 best |
| party        | return the 3 best |
| smart        | return the 3 best |
| noMention    | return the 3 best |
| noInTime     | return the 3 best |
| dishonorable | return the 3 best |

