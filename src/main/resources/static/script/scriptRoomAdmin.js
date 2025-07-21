document.getElementById("positionForm").addEventListener("submit", function(event){
    event.preventDefault();
    const requestRoom = {

            "name": document.getElementById("name").value,
            "amenities": document.getElementById("amenities").value,
            "description": document.getElementById("description").value,
            "capacity": document.getElementById("quantity").value,
            "price_per_night": document.getElementById("price").value,
            "room_img": document.getElementById("active-form").checked

    };

    console.log(requestPosition);
    fetch("/api/v1/room",{
        method: "POST",
        headers: {
                "Content-Type": "application/json"
        },
        body: JSON.stringify(requestRoom)
    })
    .then(response =>{
        if(!response.ok){
            throw new Error ("error al enviar formulario")
        }
        console.log("Posicion Registrada")
        let successModal = new bootstrap.Modal(document.getElementById('successModal'));
        successModal.show();
    })
    .catch(error => console.error("Error en solicitud:", error))
});

document.querySelectorAll(".btnEdit").forEach(btnEdit =>{

    btnEdit.addEventListener("click", function(event){

        let idRoom = this.getAttribute("edit-idRoom");

        if(!idRoom){
            console.log("id vacio")
        }
        fetch(`/api/v1/room/${idRoom}`, {
            method:"GET",
            headers: {
                "Content-Type": "application/json"
            }
        })
        .then(response => {
            if(!response.ok){
                throw new Error ("Error al buscar empleado")
            }
            return response.json();
        })
        .then(responseJson => {
            console.log(responseJson);
            document.getElementById("edit-name").placeholder = responseJson.name;
            document.getElementById("edit-name").value = responseJson.name;
            document.getElementById("edit-description").value = responseJson.description;
            document.getElementById("edit-active").checked = responseJson.employee;


            let editModal = new bootstrap.Modal(document.getElementById('positionEditModal'));

            editModal.show();

            document.getElementById("employeeEditForm").addEventListener("submit", function(event){
                event.preventDefault();

                const requestEditPosition = {
                    "name": document.getElementById("edit-name").value,
                    "description": document.getElementById("edit-description").value,
                    "employee": document.getElementById("edit-active").checked
                }

                console.log(requestEditPosition);

                fetch(`/api/v1/position/${responseJson.id_position}`, {
                    method: "PATCH",
                    headers: {
                        "Content-Type": "application/json"
                    },
                    body: JSON.stringify(requestEditPosition)
                })
                .then(responseEdit => {
                    if(!responseEdit.ok){
                        throw new Error("error");
                    }
                    return responseEdit.json();
                })
                .then(responseEditJson => {
                    let row = document.querySelector(`tr[row-idPosition='${responseEditJson.id_position}']`);
                    if(row){
                        row.cells[0].innerText = responseEditJson.name;
                        row.cells[1].innerText = responseEditJson.description;
                        row.cells[2].innerHTML =
                        `<span class="${responseEditJson.employee ? 'badge bg-success' : 'badge bg-info'}">
                            ${responseEditJson.employee ? 'Si' : 'No'}
                        </span>`;
                    }
                    editModal.hide()
                })
            })
        })
    })

});

document.querySelectorAll(".btnTrash").forEach(btnTrash =>{

    btnTrash.addEventListener("click",function(event){
        let idPosition = this.getAttribute("delete-idPosition");
        let deleteModal = new bootstrap.Modal(document.getElementById('positionDeleteModal'));
        deleteModal.show();

        document.getElementById("deleteForm").addEventListener("submit", function(event){
            event.preventDefault();

            fetch(`/api/v1/position/${idPosition}`, {

                method:"DELETE",
                headers: {
                    "Content-Type": "application/json"
                }

            })
            .then(response => {
                if(!response.ok){
                    throw new Error ("Error al eliminar la posicion")
                }
                deleteModal.hide()
                let row = document.querySelector(`tr[row-idPosition='${idPosition}']`);
                row.innerHTML ="";
            })

        })

    })

})

document.getElementById("positionEditModal").addEventListener("hidden.bs.modal", function(event){
    document.getElementById("edit-name").value="";
    document.getElementById("edit-description").value = "";
    document.getElementById("edit-active").checked = false;
})