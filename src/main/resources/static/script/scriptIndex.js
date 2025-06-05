const card = document.getElementById("idRoom");
const token = localStorage.getItem("token");
card.addEventListener("click", function() {
    fetch("/api/v1/validate-auth" , {
        method: "GET",
        headers: {"Authorization": `Bearer ${token}`}

    })
    .then(response =>{
        if(response.ok){
            window.location.href = "/usuario/rooms";
        }
    })
})