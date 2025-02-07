let boton = document.getElementById("btregistrar");

boton.addEventListener("click", evento => {
    evento.preventDefault(); // Prevenir el comportamiento predeterminado del botón
    registrarLibro();
});

let registrarLibro = async () => {
    let formData = new FormData();
    formData.append("titulo", document.getElementById("titulo").value);
    formData.append("autor", document.getElementById("autor").value);
    formData.append("file", document.getElementById("rutaPdf").files[0]);

    const peticion = await fetch("http://localhost:8080/api/libros", {
        method: 'POST',
        body: formData
    });

    if (peticion.ok) {
        alert("Libro registrado con éxito");
        obtenerLibros(); // Actualizar la lista de libros después de registrar uno nuevo
    } else {
        alert("Error al registrar el libro");
    }
};

let obtenerLibros = async () => {
    const respuesta = await fetch("http://localhost:8080/api/libros");
    if (respuesta.ok) {
        const libros = await respuesta.json();
        console.log(libros); // Imprimir los libros en la consola
        mostrarLibros(libros);
    } else {
        console.error("Error al obtener los libros");
    }
};

let mostrarLibros = (libros) => {
    let listaLibros = document.getElementById("listaLibros");
    listaLibros.innerHTML = ""; // Limpiar la lista antes de agregar los libros

    libros.forEach(libro => {
        let li = document.createElement("li");
        li.className = "list-group-item";

        // Crear el enlace para el PDF
        let enlacePdf = document.createElement("a");
        enlacePdf.href = `/${libro.rutaPdf}`;
        enlacePdf.textContent = "Ver PDF";
        enlacePdf.target = "_blank"; // Abrir en una nueva pestaña

        li.innerHTML = `Título: ${libro.titulo}, Autor: ${libro.autor}, `;
        li.appendChild(enlacePdf); // Agregar el enlace al elemento de la lista

        listaLibros.appendChild(li);
    });
};

// Llamar a obtenerLibros cuando se cargue la página
document.addEventListener("DOMContentLoaded", obtenerLibros);