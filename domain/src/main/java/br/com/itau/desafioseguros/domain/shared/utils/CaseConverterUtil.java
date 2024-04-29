package br.com.itau.desafioseguros.domain.shared.utils;

import br.com.itau.desafioseguros.domain.exceptions.UtilityClassException;

public class CaseConverterUtil {

    private CaseConverterUtil() {
        throw new UtilityClassException();
    }

    public static String convertCamelCaseToSnakeCase(String camelCase) {
        StringBuilder result = new StringBuilder();
        char c = camelCase.charAt(0);
        result.append(Character.toLowerCase(c));

        for (int i = 1; i < camelCase.length(); i++) {
            char ch = camelCase.charAt(i);

            if (Character.isUpperCase(ch)) {
                result.append('_');
                result.append(Character.toLowerCase(ch));
            } else {
                result.append(ch);
            }
        }
        return result.toString();
    }

}
