package com.aitrain.users.domain.usecase;

import com.aitrain.users.domain.exceptions.EmailAlreadyExistException;
import com.aitrain.users.domain.exceptions.ValoracionInvalidDataException;
import com.aitrain.users.domain.exceptions.ValoracionNotFoundException;
import com.aitrain.users.domain.exceptions.ValoracionYaExisteException;
import com.aitrain.users.domain.model.Usuario;
import com.aitrain.users.domain.model.Valoracion;
import com.aitrain.users.domain.model.gateway.UsuarioGateway;
import com.aitrain.users.domain.model.gateway.ValoracionGateway;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public class ValoracionUseCase {

    private final ValoracionGateway valoracionGateway;
    private final UsuarioGateway usuarioGateway;

    public Valoracion crearValoracion(String emailUsuario, Valoracion valoracion) {

        if (emailUsuario == null || emailUsuario.trim().isEmpty()) {
            throw new ValoracionInvalidDataException("El email del usuario es obligatorio");
        }

        // VALIDACIÓN REAL QUE SI FUNCIONA
        Usuario usuario = usuarioGateway.buscarPorEmail(emailUsuario);

        if (usuario == null) {
            throw new ValoracionNotFoundException("No existe un usuario registrado con el email: " + emailUsuario);
        }

        Valoracion existente = valoracionGateway.buscarPorEmailUsuario(emailUsuario);
        if (existente != null) {
            throw new ValoracionYaExisteException("El usuario ya tiene una valoración registrada.");
        }

        // === Resto de validaciones normales ===
        if (valoracion.getPesoKg() == null || valoracion.getPesoKg() <= 0) {
            throw new ValoracionInvalidDataException("El peso debe ser mayor que 0");
        }

        if (valoracion.getEstaturaCm() == null || valoracion.getEstaturaCm() <= 0) {
            throw new ValoracionInvalidDataException("La estatura debe ser mayor que 0");
        }

        if (valoracion.getGenero() == null || valoracion.getGenero().trim().isEmpty()) {
            throw new ValoracionInvalidDataException("El campo género es obligatorio");
        }

        if (valoracion.getEdad() == null || valoracion.getEdad() <= 0) {
            throw new ValoracionInvalidDataException("La edad debe ser mayor que 0");
        }

        if (valoracion.getNivelActividad() == null || valoracion.getNivelActividad().trim().isEmpty()) {
            throw new ValoracionInvalidDataException("El nivel de actividad es obligatorio");
        }

        if (valoracion.getDiasEntrenamiento() == null ||
                valoracion.getDiasEntrenamiento() < 1 ||
                valoracion.getDiasEntrenamiento() > 7) {
            throw new ValoracionInvalidDataException("Los días de entrenamiento deben estar entre 1 y 7");
        }

        if (valoracion.getTiempoPorSesionMin() == null || valoracion.getTiempoPorSesionMin() <= 0) {
            throw new ValoracionInvalidDataException("El tiempo por sesión debe ser mayor que 0");
        }

        if (valoracion.getObjetivo() == null || valoracion.getObjetivo().trim().isEmpty()) {
            throw new ValoracionInvalidDataException("El objetivo es obligatorio");
        }

        // Calcular IMC
        double alturaMetros = valoracion.getEstaturaCm() / 100.0;
        double imcCalculado = valoracion.getPesoKg() / (alturaMetros * alturaMetros);
        valoracion.setImc(imcCalculado);

        valoracion.setEmailUsuario(emailUsuario);

        return valoracionGateway.guardarValoracion(valoracion);
        }

    public Valoracion obtenerValoracionPorEmail(String emailUsuario) {
        if (emailUsuario == null || emailUsuario.trim().isEmpty()) {
            throw new ValoracionInvalidDataException("El email del usuario es obligatorio");
        }

        Valoracion valoracion = valoracionGateway.buscarPorEmailUsuario(emailUsuario);

        if (valoracion == null) {
            throw new ValoracionNotFoundException("No existe una valoración registrada para el usuario: " + emailUsuario);
        }

        return valoracion;
    }

    public Valoracion actualizarValoracion(String emailUsuario, Valoracion nuevosDatos) {

        if (emailUsuario == null || emailUsuario.trim().isEmpty()) {
            throw new ValoracionInvalidDataException("El email del usuario es obligatorio");
        }

        // 1️⃣ Verificar que exista el usuario
        Usuario usuario = usuarioGateway.buscarPorEmail(emailUsuario);
        if (usuario == null) {
            throw new ValoracionNotFoundException("No existe un usuario registrado con el email: " + emailUsuario);
        }

        // 2️⃣ Buscar la valoración actual
        Valoracion existente = valoracionGateway.buscarPorEmailUsuario(emailUsuario);
        if (existente == null) {
            throw new ValoracionNotFoundException("No existe una valoración registrada para este usuario.");
        }

        // 3️⃣ Validaciones
        if (nuevosDatos.getPesoKg() == null || nuevosDatos.getPesoKg() <= 0) {
            throw new ValoracionInvalidDataException("El peso debe ser mayor que 0");
        }

        if (nuevosDatos.getEstaturaCm() == null || nuevosDatos.getEstaturaCm() <= 0) {
            throw new ValoracionInvalidDataException("La estatura debe ser mayor que 0");
        }

        if (nuevosDatos.getGenero() == null || nuevosDatos.getGenero().trim().isEmpty()) {
            throw new ValoracionInvalidDataException("El campo género es obligatorio");
        }

        if (nuevosDatos.getEdad() == null || nuevosDatos.getEdad() <= 0) {
            throw new ValoracionInvalidDataException("La edad debe ser mayor que 0");
        }

        if (nuevosDatos.getNivelActividad() == null || nuevosDatos.getNivelActividad().trim().isEmpty()) {
            throw new ValoracionInvalidDataException("El nivel de actividad es obligatorio");
        }

        if (nuevosDatos.getDiasEntrenamiento() == null ||
                nuevosDatos.getDiasEntrenamiento() < 1 ||
                nuevosDatos.getDiasEntrenamiento() > 7) {
            throw new ValoracionInvalidDataException("Los días de entrenamiento deben estar entre 1 y 7");
        }

        if (nuevosDatos.getTiempoPorSesionMin() == null || nuevosDatos.getTiempoPorSesionMin() <= 0) {
            throw new ValoracionInvalidDataException("El tiempo por sesión debe ser mayor que 0");
        }

        if (nuevosDatos.getObjetivo() == null || nuevosDatos.getObjetivo().trim().isEmpty()) {
            throw new ValoracionInvalidDataException("El objetivo es obligatorio");
        }

        // 4️⃣ Calcular IMC actualizado
        double alturaMetros = nuevosDatos.getEstaturaCm() / 100.0;
        double imcCalculado = nuevosDatos.getPesoKg() / (alturaMetros * alturaMetros);
        nuevosDatos.setImc(imcCalculado);

        // 5️⃣ Mantener email y ID originales
        nuevosDatos.setId(existente.getId());
        nuevosDatos.setEmailUsuario(emailUsuario);

        // 6️⃣ Guardar cambios
        return valoracionGateway.actualizarValoracion(nuevosDatos);
    }

    public void eliminarValoracion(String emailUsuario) {

        if (emailUsuario == null || emailUsuario.trim().isEmpty()) {
            throw new ValoracionInvalidDataException("El email del usuario es obligatorio");
        }

        Valoracion existente = valoracionGateway.buscarPorEmailUsuario(emailUsuario);

        if (existente == null) {
            throw new ValoracionNotFoundException(
                    "No existe una valoración registrada para el email: " + emailUsuario
            );
        }

        valoracionGateway.eliminarValoracion(emailUsuario);
    }


}