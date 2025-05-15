
        function setRoomFromQuery() {
            const params = new URLSearchParams(window.location.search);
            const room = params.get('room');
            if(room) {
                const select = document.getElementById('roomSelect');
                for (let option of select.options) {
                    if(option.value === room) {
                        option.selected = true;
                        break;
                    }
                }
            }
        }

        document.addEventListener('DOMContentLoaded', () => {
            setRoomFromQuery();

            document.getElementById('reviewForm').addEventListener('submit', (e) => {
                e.preventDefault();
                const room = e.target.room.value;
                const rating = e.target.rating.value;
                const comment = e.target.comment.value.trim();

                if (!room || !rating || !comment) {
                    alert('Por favor, completa todos los campos.');
                    return;
                }

                // Aquí para el envío al backend
                alert(`Gracias por tu review para "${room}" con calificación de ${rating} estrellas.`);

                // Resetear formulario
                e.target.reset();
            });
        });