document.getElementById('registerForm').addEventListener('submit', async function (e) {
  e.preventDefault();

  const usuario = {
    nombre: document.getElementById('nombre').value,
    cedula: document.getElementById('cedula').value,
    email: document.getElementById('email').value,
    password: document.getElementById('password').value,
    role: document.getElementById('role').value,
    edad: parseInt(document.getElementById('edad').value)
  };

  const mensaje = document.getElementById('mensaje');

  try {
    const response = await fetch('http://localhost:8080/api/aitrain/usuario/save', {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(usuario)
    });

    if (response.ok) {
      mensaje.style.color = 'green';
      mensaje.textContent = '✅ Usuario registrado correctamente';
      document.getElementById('registerForm').reset();
    } else {
      const errorText = await response.text();
      mensaje.style.color = 'red';
      mensaje.textContent = `❌ Error al registrar usuario: ${errorText}`;
    }
  } catch (error) {
    console.error('Error:', error);
    mensaje.style.color = 'red';
    mensaje.textContent = '⚠️ No se pudo conectar al servidor';
  }
});
