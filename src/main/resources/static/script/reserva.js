        document.addEventListener('DOMContentLoaded', () => {
            const plantillaActiva = document.querySelector('#reservas-activas .card-reserva');
            const plantillaPasada = document.querySelector('#reservas-pasadas .card-reserva');

            const contenedorActivas = document.getElementById('reservas-activas');
            const contenedorPasadas = document.getElementById('reservas-pasadas');

            // Función para parsear fechas en formato "1 may. - 2 may."
            function parseDateES(dateStr) {
                const monthMap = {
                    'ene': 0, 'feb': 1, 'mar': 2, 'abr': 3, 'may': 4, 'jun': 5,
                    'jul': 6, 'ago': 7, 'sep': 8, 'oct': 9, 'nov': 10, 'dic': 11
                };
                let parts = dateStr.toLowerCase().replace('.', '').split(' ');
                let day = parseInt(parts[0], 10);
                let monthShort = parts[1];
                let month = monthMap[monthShort];
                let year = new Date().getFullYear();
                if (isNaN(day) || month === undefined) return null;
                return new Date(year, month, day);
            }

            // Función para parsear las fechas de estadía del texto "1 may. - 2 may."
            function parseStayDates(stayStr) {
                const parts = stayStr.split('-').map(s => s.trim());
                if (parts.length === 2) {
                    return {
                        startDate: parseDateES(parts[0]),
                        endDate: parseDateES(parts[1])
                    };
                }
                return { startDate: null, endDate: null };
            }

            // Cargar ofertas iniciales en reservas (si quieres cargar las ofertas al iniciar, descomenta esta parte)

            const ofertas = document.querySelectorAll('#ofertas-hoteles .col-md-4');
            const today = new Date();

            ofertas.forEach((oferta, index) => {
                const nombre = oferta.querySelector('.card-title')?.textContent.trim();
                const fechasTexto = oferta.querySelector('.stay-dates')?.textContent.trim();
                if (!nombre || !fechasTexto) return;

                const { startDate, endDate } = parseStayDates(fechasTexto);
                if (!startDate || !endDate) return;

                const esActiva = today <= endDate;
                const clon = (esActiva ? plantillaActiva : plantillaPasada).cloneNode(true);
                clon.style.display = 'block';

                clon.querySelector('.nombre-habitacion').textContent = nombre;
                clon.querySelector('.fecha-reserva').textContent = today.toLocaleDateString('es-ES');
                clon.querySelector('.fechas-estadia').textContent = fechasTexto;
                clon.querySelector('.status').textContent = esActiva ? 'Activa' : 'Pasada';

                const btn = clon.querySelector('.btn-accion');
                if (esActiva) {
                    btn.textContent = 'Cancelar';
                    btn.className = 'btn btn-danger btn-accion';
                    btn.addEventListener('click', () => {
                        if (confirm(`¿Deseas cancelar tu reserva en ${nombre}?`)) {
                            clon.remove();
                        }
                    });
                    contenedorActivas.appendChild(clon);
                } else {
                    btn.textContent = 'Dejar Review';
                    btn.className = 'btn btn-primary btn-accion';
                    btn.addEventListener('click', () => {

                        window.location.href = `dejar-review.html?room=${encodeURIComponent(nombre)}`;
                    });
                    contenedorPasadas.appendChild(clon);
                }
            });


            // Escuchar clics en botones "Consultar oferta"
            document.querySelectorAll('.btn-reservar').forEach(btn => {
                btn.addEventListener('click', () => {
                    const card = btn.closest('.col-md-4');

                    const nombre = card.querySelector('.card-title')?.textContent.trim();
                    const fechasTexto = card.querySelector('.stay-dates')?.textContent.trim();

                    if (!nombre || !fechasTexto) return;

                    const { startDate, endDate } = parseStayDates(fechasTexto);
                    if (!startDate || !endDate) return;

                    const today = new Date();

                    // Verificar si ya existe una reserva activa con ese nombre y fechas para evitar duplicados
                    const reservasExistentes = [...contenedorActivas.querySelectorAll('.card-reserva')];
                    const existe = reservasExistentes.some(reserva => {
                        const nombreRes = reserva.querySelector('.nombre-habitacion')?.textContent.trim();
                        const fechasRes = reserva.querySelector('.fechas-estadia')?.textContent.trim();
                        return nombreRes === nombre && fechasRes === fechasTexto;
                    });

                    if (existe) {
                        alert('Esta reserva ya está en tus reservas activas.');
                        return;
                    }

                    // Clonar plantilla activa y llenar datos
                    const clon = plantillaActiva.cloneNode(true);
                    clon.style.display = 'block';
                    clon.querySelector('.nombre-habitacion').textContent = nombre;
                    clon.querySelector('.fecha-reserva').textContent = today.toLocaleDateString('es-ES');
                    clon.querySelector('.fechas-estadia').textContent = fechasTexto;
                    clon.querySelector('.status').textContent = 'Activa';
                    clon.querySelector('.status').className = 'status activa';

                    const btnCancelar = clon.querySelector('.btn-accion');
                    btnCancelar.textContent = 'Cancelar';
                    btnCancelar.className = 'btn btn-danger btn-accion';
                    btnCancelar.addEventListener('click', () => {
                        if (confirm(`¿Deseas cancelar tu reserva en ${nombre}?`)) {
                            clon.remove();
                        }
                    });

                    contenedorActivas.appendChild(clon);
                });
            });

            // Eliminar plantillas para no mostrarlas como reservas reales
            plantillaActiva.remove();
            plantillaPasada.remove();
        });