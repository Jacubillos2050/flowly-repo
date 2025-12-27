package com.project.flowly.shared.validation;

import java.util.regex.Pattern;
/**
 * Validador reutilizable de direcciones de correo electrónico.
 * Basado en una expresión regular robusta pero pragmática.
 */
public final class EmailValidator {

    // Patrón optimizado: acepta la mayoría de emails reales, rechaza obvios inválidos
    private static final String EMAIL_PATTERN =
            "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@" +
                    "(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

    private static final Pattern PATTERN = Pattern.compile(EMAIL_PATTERN);

    private EmailValidator() {
        // Utilidad estática: constructor privado
    }

    /**
     * Valida si un string es una dirección de correo válida.
     *
     * @param email dirección a validar (puede ser null o vacío)
     * @return true si es válido, false en caso contrario
     */
    public static boolean isValid(String email) {
        if (email == null || email.isBlank()) {
            return false;
        }
        if (email.length() > 254) { // RFC 5321: máximo 254 caracteres
            return false;
        }
        return PATTERN.matcher(email).matches();
    }

    /**
     * Valida y lanza excepción si el email es inválido.
     *
     * @param email dirección a validar
     * @param fieldName nombre del campo (para mensaje de error)
     * @throws IllegalArgumentException si es inválido
     */
    public static void validate(String email, String fieldName) {
        if (!isValid(email)) {
            throw new IllegalArgumentException(
                    String.format("Invalid email address for field '%s': '%s'", fieldName, email)
            );
        }
    }

    /**
     * Versión sin nombre de campo.
     */
    public static void validate(String email) {
        validate(email, "email");
    }
}
