document.getElementById("employeeForm").addEventListener("submit", function(event){
    event.preventDefault();
    const requestEmployeeRegistration = {

        "employee_request":{
            "id_position": parseInt(document.getElementById("position").value),
            "names": document.getElementById("names").value,
            "surnames": document.getElementById("surnames").value,
            "gender": document.querySelector("input[name=gender]:checked").value,
            "email": document.getElementById("email").value,
            "birth_date": document.getElementById("birthDate").value,
            "phone": document.getElementById("phone").value
        },

        "user_employee_web_request":{
            "user_type": "EMPLOYEE",
            "password": document.getElementById("password").value
        }

    };

    console.log(requestEmployeeRegistration);
    fetch("/api/v1/employee/employee-registration",{
        method: "POST",
        headers: {
                "Content-Type": "application/json"
        },
        body: JSON.stringify(requestEmployeeRegistration)
    })
    .then(response =>{
        if(!response.ok){
            throw new Error ("error al enviar formulario")
        }
        console.log("Empleado y Usuario Registrado")
        let successModal = new bootstrap.Modal(document.getElementById('successModal'));
        successModal.show();
    })
    .catch(error => console.error("Error en solicitud:", error))
});

document.querySelectorAll(".btnEdit").forEach(btnEdit =>{

    btnEdit.addEventListener("click", function(event){

        let idEmployee = this.getAttribute("edit-idEmployee");

        if(!idEmployee){
            console.log("id vacio")
        }
        fetch(`/api/v1/employee/${idEmployee}`, {
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
            document.getElementById("edit-surnames").placeholder = responseJson.surnames;
            document.getElementById("edit-surnames").value = responseJson.surnames;
            document.getElementById("edit-position").value = responseJson.position.id_position;

            if(responseJson.gender === "MASCULINO"){
                document.getElementById("edit-masculino").checked = true;
            }else if(responseJson.gender === "FEMENINO"){
                document.getElementById("edit-femenino").checked = true;
            }else{
                document.getElementById("edit-otro").checked = true;
            }
            document.getElementById("edit-active").checked = responseJson.active;


            let editModal = new bootstrap.Modal(document.getElementById('employeeEditModal'));

            editModal.show();

            document.getElementById("employeeEditForm").addEventListener("submit", function(event){
                event.preventDefault();

                const requestEditEmployee = {
                    "id_position": parseInt(document.getElementById("edit-position").value),
                    "surnames": document.getElementById("edit-surnames").value,
                    "gender": document.querySelector("input[name=edit-gender]:checked").value,
                    "active": document.getElementById("edit-active").checked
                }

                console.log(requestEditEmployee);

                fetch(`/api/v1/employee/${responseJson.id_employee}`, {
                    method: "PATCH",
                    headers: {
                        "Content-Type": "application/json"
                    },
                    body: JSON.stringify(requestEditEmployee)
                })
                .then(responseEdit => {
                    if(!responseEdit.ok){
                        throw new Error("error");
                    }
                    return responseEdit.json();
                })
                .then(responseEditJson => {
                    let row = document.querySelector(`tr[row-idEmployee='${responseEditJson.id_employee}']`);
                    if(row){
                        row.cells[0].innerText = responseEditJson.surnames;
                        row.cells[1].innerText = responseEditJson.gender;
                        row.cells[2].innerText = responseEditJson.position.name;
                        row.cells[4].innerHTML =
                         `<span class="${responseEditJson.active ? 'badge bg-success' : 'badge bg-secondary'}">
                                 ${responseEditJson.active ? 'Activo' : 'No activo'}
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
        let idEmployee = this.getAttribute("delete-idEmployee");
        let deleteModal = new bootstrap.Modal(document.getElementById('employeeDeleteModal'));
        deleteModal.show();

        document.getElementById("deleteForm").addEventListener("submit", function(event){
            event.preventDefault();

            fetch(`/api/v1/employee/${idEmployee}`, {

                method:"DELETE",
                headers: {
                    "Content-Type": "application/json"
                }

            })
            .then(response => {
                if(!response.ok){
                    throw new Error ("Error al eliminar el empleado")
                }
                deleteModal.hide()
                let row = document.querySelector(`tr[row-idEmployee='${idEmployee}']`);
                row.innerHTML ="";
            })

        })

    })

})

document.getElementById("employeeEditModal").addEventListener("hidden.bs.modal", function(event){
    document.getElementById("edit-surnames").value="";
    document.getElementById("edit-position").value = "";

    document.getElementById("edit-masculino").checked = false;
    document.getElementById("edit-femenino").checked = false;
    document.getElementById("edit-otro").checked = false;

    document.getElementById("edit-active").checked = false;
})