const Url = window.location.protocol + '//' + window.location.host;
var p = document.getElementById("count");
var interval = setInterval(loadCount, 30000);
var xmlHttp = new XMLHttpRequest();
var url = "";
loadCount();


        function loadCount() {
            xmlHttp = new XMLHttpRequest();
            url = Url + "/page";
            xmlHttp.open("GET", url, false);
            xmlHttp.send(null);
            text = xmlHttp.responseText.replace("[", "").replace("]", "");
            p.innerHTML = text + " mal aufgerufen";


            xmlHttp = new XMLHttpRequest();
            url = Url + "/requests"
            xmlHttp.open("GET", url, false);
            xmlHttp.send(null);
            text = xmlHttp.responseText.replace("[", "").replace("]", "") + " haben abgestimmt";
            document.getElementById('requests').innerHTML = text;
        }

        function sendTicket() {
            xmlHttp = new XMLHttpRequest();
            var ticket = document.getElementById('ticketinput').value.toUpperCase();

            var unprepared = document.getElementById('unprepared').value.toLowerCase();
            var joker = document.getElementById('joker').value.toLowerCase();
            var late = document.getElementById('late').value.toLowerCase();
            var spoiled = document.getElementById('spoiled').value.toLowerCase();
            var party = document.getElementById('party').value.toLowerCase();
            var smart = document.getElementById('smart').value.toLowerCase();
            var beauty = document.getElementById('beauty').value.toLowerCase();
            var noMention = document.getElementById('noMention').value.toLowerCase();
            var noInTime = document.getElementById('noInTime').value.toLowerCase();
            var dishonorable = document.getElementById('dishonorable').value.toLowerCase();

            var url = Url + "/tickets?mode=use&id=0";

            if (ticket != "" && unprepared != "" && joker != "" && late != "" && spoiled != "" && party != "" && smart != "" && beauty != "" && noMention != "" && noInTime != "" && dishonorable != "") {
                url = Url + "/tickets?mode=use&id=" + ticket + "&unprepared=" + unprepared + "&joker=" + joker
                        + "&late=" + late + "&spoiled=" + spoiled + "&party=" + party + "&smart=" + smart + "&beauty=" + beauty
                        + "&noMention=" + noMention + "&noInTime=" + noInTime + "&dishonorable=" + dishonorable;
                xmlHttp.open("GET", url , false);
                xmlHttp.send(null);
                response = xmlHttp.responseText;
                if (response == "doesn't exist") {
                    window.open(Url + "/failed");
                }
                else if (response == 'used') {
                    window.open(Url + "/used")
                }
                else {
                    window.open(Url + "/response")
                }
            }
            else {
                document.getElementById('error').innerHTML = "Konnte nicht gesendet werden. Überprüfe deine Angaben"
            }

        }