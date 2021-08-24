var stompClient = null;

window.onload = connect();

function connect() {
    var socket = new SockJS('/content');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function(frame) {
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/numberProducts', function(contentCart){
            showGreeting(JSON.parse(contentCart.body));
        });
    });
}

function sendNumber() {
    var name = '0';
    stompClient.send("/app/content", {}, {});

}

function showGreeting(message) {
    console.log(message);
    document.getElementById("resultInput").value=message;
}