# Ranking with voting
The project is a web application based on spring-boot framework.
You can define your own categories and the persons can vote for each category.

## Content
1. [API](#API)
   1. [Token](#Token)

## API
### Token
The API url is https://teacherranking.ddns.net/tickets.

#### Modes
```https://teacherranking.ddns.net/tickets?mode={value}```

- Value = create | status | use

#### Create Mode
Request type -> GET <br>
Parameter needed-> mode <br>
URL -> https://teacherranking.ddns.net/tickets?mode=create <br>
return -> Token for Voting (e.g. XDFAEF)

#### Status Mode
Request type -> GET <br>

| Parameter    | Description      |
|--------------|------------------|
| mode         | must be 'status' |
| id           | ticket token     |

URL -> https://teacherranking.ddns.net/tickets?mode=status&id={token} <br>
return -> String - false | used | doesn't exist

#### Use Mode
Request type -> POST <br>

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
| noMention    | teacher name to increase value |
| noInTime     | teacher name to increase value |
| dishonorable | teacher name to increase value |

URL -> https://teacherranking.ddns.net/tickets?mode=use&id={token}&joker={teacher}&unprepared={teacher}&late={teacher}&spoiled={teacher}&party={teacher}&smart={teacher}&beauty={teacher}&noMention={teacher}&noInTime={teacher}&dishonorable={teacher} <br>
return -> nothing
