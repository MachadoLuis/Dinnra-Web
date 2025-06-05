function webSocket (event){
    if(event.data === "shutdown"){
        localStorage.clear();
        console.log("Conexion con el backend terminada");
    }
}
const socket = new WebSocket("ws://localhost:8080/ws/shutdown");
socket.onmessage = webSocket;
