#Ranking with voting
The project is a web application based on spring-boot framework.
You can define your own categories and the persons can vote for each category.

##Content
1. [API](#API)
   1. [Token](###Token)
   2. [Ranking](###Ranking list)

##API
###Token
The API url is https://teacherranking.ddns.net/tickets.

####Modes
```https://teacherranking.ddns.net/tickets?mode={value}```

- Value = create | status | use

####Create Mode
Request type -> GET <br>
Parameter needed-> mode <br>
URL -> https://teacherranking.ddns.net/tickets?mode=create <br>
return -> Token for Voting (e.g. XDFAEF)

####Status Mode
Request type -> GET <br>
Parameter needed-> mode - id <br>
URL -> https://teacherranking.ddns.net/tickets?mode=mode <br>
return -> boolean - whether it was used

####Use Mode
Request type -> POST <br>
Parameter needed-> mode - id - teacher - joker - smart - noMention - dishonorable <br>
URL -> https://teacherranking.ddns.net/tickets?mode=mode <br>
return -> boolean - whether it was used

###Ranking lists