document.querySelectorAll(".btnReservation").forEach(btnReservation => {

    btnReservation.addEventListener("click", function(event){

        let idRoom = this.getAttribute("idRoom");
        window.location.href = `/usuario/reservation/${idRoom}`;
    })
})