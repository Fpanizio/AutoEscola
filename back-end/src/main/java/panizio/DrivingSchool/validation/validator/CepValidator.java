package panizio.DrivingSchool.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import panizio.DrivingSchool.validation.annotation.ValidCEP;

import org.springframework.web.client.RestTemplate;

public class CepValidator implements ConstraintValidator<ValidCEP, String> {

    @Override
    public boolean isValid(String cep, ConstraintValidatorContext context) {
        if (cep == null || cep.isEmpty()) return false;

        // Remove caracteres não numéricos
        cep = cep.replaceAll("[^0-9]", "");

        // Verifica se o CEP tem 8 dígitos
        if (cep.length() != 8) return false;

        // Consulta a API ViaCEP para verificar existência
        try {
            RestTemplate restTemplate = new RestTemplate();
            String url = "https://viacep.com.br/ws/" + cep + "/json/";
            ViaCepResponse response = restTemplate.getForObject(url, ViaCepResponse.class);
            return response != null && !response.isErro(); // Retorna true se o CEP existir
        } catch (Exception e) {
            return false; // Falha na requisição ou CEP inválido
        }
    }

    // Classe mínima para mapear a resposta da API
    private static class ViaCepResponse {
        private boolean erro;

        public boolean isErro() {
            return erro;
        }
    }
}