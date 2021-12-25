const Url = window.location.protocol + '//' + window.location.host;
var interval = setInterval(loadContent, 30000);
var http = new XMLHttpRequest();
var url = "";
loadContent();


function loadContent() {
    http = new XMLHttpRequest();
    url = Url + "/page/ranking";
    http.open("GET", url, false);
    http.send(null);
    var text = http.responseText.replace("[", "").replace("]", "");
    document.getElementById('count').innerHTML = text + " Aufrufe";

    updateRequestsCount();
    updateJoker();
    updateUnprepared();
    updateLate();
    updateUnreliable();
    updateParty();
    updateSmart();
    updateBeauty();
    updateNoMention();
    updateNoInTime();
    updateDishonorable();
}

function updateRequestsCount() {
    http = new XMLHttpRequest();
    url = Url + "/requests"
    http.open("GET", url, false);
    http.send(null);
    var requests = http.responseText.replace("[", "").replace("]", "");

    http = new XMLHttpRequest();
    url = Url + "/tickets/all"
    http.open("GET", url, false);
    http.send(null);
    var amount = http.responseText;
    var text = requests + " von " + amount + " Stimmen";
    document.getElementById('requests').innerHTML = text;
}

function updateJoker() {
    http = new XMLHttpRequest();
    url = Url + '/teacher/best/joker'
    http.open("GET", url, false);
    http.send(null);
    var text = JSON.parse(http.responseText);
    for (var i = 1; i <= 3; i++) {
        var id = 'joker' + i;
        var t = i + '. ' + text[i - 1];
        document.getElementById(id).innerHTML = t;
    }
}

function updateUnprepared() {
    http = new XMLHttpRequest();
    url = Url + '/teacher/best/unprepared'
    http.open("GET", url, false);
    http.send(null);
    var text = JSON.parse(http.responseText);
    for (var i = 1; i <= 3; i++) {
        var id = 'unprepared' + i;
        var t = i + '. ' + text[i - 1];
        document.getElementById(id).innerHTML = t;
    }
}

function updateLate() {
    http = new XMLHttpRequest();
    url = Url + '/teacher/best/late'
    http.open("GET", url, false);
    http.send(null);
    var text = JSON.parse(http.responseText);
    for (var i = 1; i <= 3; i++) {
        var id = 'late' + i;
        var t = i + '. ' + text[i - 1];
        document.getElementById(id).innerHTML = t;
    }
}

function updateUnreliable() {
    http = new XMLHttpRequest();
    url = Url + '/teacher/best/unreliable'
    http.open("GET", url, false);
    http.send(null);
    var text = JSON.parse(http.responseText);
    for (var i = 1; i <= 3; i++) {
        var id = 'unreliable' + i;
        var t = i + '. ' + text[i - 1];
        document.getElementById(id).innerHTML = t;
    }
}

function updateParty() {
    http = new XMLHttpRequest();
    url = Url + '/teacher/best/party'
    http.open("GET", url, false);
    http.send(null);
    var text = JSON.parse(http.responseText);
    for (var i = 1; i <= 3; i++) {
        var id = 'party' + i;
        var t = i + '. ' + text[i - 1];
        document.getElementById(id).innerHTML = t;
    }
}

function updateSmart() {
    http = new XMLHttpRequest();
    url = Url + '/teacher/best/smart'
    http.open("GET", url, false);
    http.send(null);
    var text = JSON.parse(http.responseText);
    for (var i = 1; i <= 3; i++) {
        var id = 'smart' + i;
        var t = i + '. ' + text[i - 1];
        document.getElementById(id).innerHTML = t;
    }
}

function updateBeauty() {
    http = new XMLHttpRequest();
    url = Url + '/teacher/best/beauty'
    http.open("GET", url, false);
    http.send(null);
    var text = JSON.parse(http.responseText);
    for (var i = 1; i <= 3; i++) {
        var id = 'beauty' + i;
        var t = i + '. ' + text[i - 1];
        document.getElementById(id).innerHTML = t;
    }
}

function updateNoMention() {
    http = new XMLHttpRequest();
    url = Url + '/teacher/best/noMention'
    http.open("GET", url, false);
    http.send(null);
    var text = JSON.parse(http.responseText);
    for (var i = 1; i <= 3; i++) {
        var id = 'noMention' + i;
        var t = i + '. ' + text[i - 1];
        document.getElementById(id).innerHTML = t;
    }
}

function updateNoInTime() {
    http = new XMLHttpRequest();
    url = Url + '/teacher/best/noInTime'
    http.open("GET", url, false);
    http.send(null);
    var text = JSON.parse(http.responseText);
    for (var i = 1; i <= 3; i++) {
        var id = 'noInTime' + i;
        var t = i + '. ' + text[i - 1];
        document.getElementById(id).innerHTML = t;
    }
}

function updateDishonorable() {
    http = new XMLHttpRequest();
    url = Url + '/teacher/best/dishonorable'
    http.open("GET", url, false);
    http.send(null);
    var text = JSON.parse(http.responseText);
    for (var i = 1; i <= 3; i++) {
        var id = 'dishonorable' + i;
        var t = i + '. ' + text[i - 1];
        document.getElementById(id).innerHTML = t;
    }
}