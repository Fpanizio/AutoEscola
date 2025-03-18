-- Verifica e cria as funções de validação
-- Padronização: snake_case e termos em português sem acentos

-- CPF: XXX.XXX.XXX-XX
CREATE OR REPLACE FUNCTION validar_cpf(cpf TEXT)
RETURNS BOOLEAN AS $$
BEGIN
  RETURN cpf ~ '^\d{3}\.\d{3}\.\d{3}-\d{2}$';
END;
$$ LANGUAGE plpgsql;

-- RG: XX.XXX.XXX-X
CREATE OR REPLACE FUNCTION validar_rg(rg TEXT)
RETURNS BOOLEAN AS $$
BEGIN
  RETURN rg ~ '^\d{2}\.\d{3}\.\d{3}-\d{1}$';
END;
$$ LANGUAGE plpgsql;

-- CEP: XXXXX-XXX
CREATE OR REPLACE FUNCTION validar_cep(cep TEXT)
RETURNS BOOLEAN AS $$
BEGIN
  RETURN cep ~ '^\d{5}-\d{3}$';
END;
$$ LANGUAGE plpgsql;

-- Telefone: +XX (XX) XXXXX-XXXX
CREATE OR REPLACE FUNCTION validar_telefone(telefone TEXT)
RETURNS BOOLEAN AS $$
BEGIN
  RETURN telefone ~ '^\+\d{2}\s\(\d{2}\)\s\d{4,5}-\d{4}$';
END;
$$ LANGUAGE plpgsql;

-- Salário: Valor positivo
CREATE OR REPLACE FUNCTION validar_salario(salario NUMERIC)
RETURNS BOOLEAN AS $$
BEGIN
  RETURN salario > 0;
END;
$$ LANGUAGE plpgsql;

-- PIS/PASEP: XXX.XXXXX.XX-X
CREATE OR REPLACE FUNCTION validar_pis_pasep(pis_pasep TEXT)
RETURNS BOOLEAN AS $$
BEGIN
  RETURN pis_pasep ~ '^\d{3}\.\d{5}\.\d{2}-\d{1}$';
END;
$$ LANGUAGE plpgsql;

-- CTPS: XXXXXXX-XX.XXXX-XX
CREATE OR REPLACE FUNCTION validar_ctps(ctps TEXT)
RETURNS BOOLEAN AS $$
BEGIN
  RETURN ctps ~ '^\d{7}-\d{2}\.\d{4}-\d{2}$';
END;
$$ LANGUAGE plpgsql;

-- Título de Eleitor: XXXXXXXXXXXX
CREATE OR REPLACE FUNCTION validar_titulo_eleitor(titulo TEXT) 
RETURNS BOOLEAN AS $$
BEGIN
    -- Verifica se o título segue o formato correto: 1234.5678.0123
    RETURN titulo ~ '^\d{4}\.\d{4}\.\d{4}$';
END;
$$ LANGUAGE plpgsql;

-- Telefone de Emergência: +XX (XX) XXXXX-XXXX
CREATE OR REPLACE FUNCTION validar_contato_emergencia(telefone TEXT)
RETURNS BOOLEAN AS $$
BEGIN
  RETURN telefone ~ '^\+\d{2}\s\(\d{2}\)\s\d{4,5}-\d{4}$';
END;
$$ LANGUAGE plpgsql;

-- E-mail: Validação básica sem acentos
CREATE OR REPLACE FUNCTION validar_email(email TEXT)
RETURNS BOOLEAN AS $$
BEGIN
  RETURN email ~* '^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,}$';
END;
$$ LANGUAGE plpgsql;

-- CNH: 11 dígitos
CREATE OR REPLACE FUNCTION validar_cnh(cnh TEXT)
RETURNS BOOLEAN AS $$
BEGIN
  RETURN cnh ~ '^\d{11}$';
END;
$$ LANGUAGE plpgsql;

-- Passaporte: Uma letra maiúscula seguida de 7 dígitos
CREATE OR REPLACE FUNCTION validar_passaporte(passaporte TEXT)
RETURNS BOOLEAN AS $$
BEGIN
  RETURN passaporte ~ '^[A-Z]{1}\d{7}$';
END;
$$ LANGUAGE plpgsql;

-- Valida se é maior de idade
CREATE OR REPLACE FUNCTION validar_maioridade(data_nascimento DATE)
RETURNS BOOLEAN AS $$
BEGIN
  RETURN data_nascimento <= CURRENT_DATE - INTERVAL '18 years';
END;
$$ LANGUAGE plpgsql;

-- Validação do RENAVAM
CREATE OR REPLACE FUNCTION validar_renavam(renavam TEXT)
RETURNS BOOLEAN AS $$
BEGIN
  RETURN renavam ~ '^\d{11}$';
END;
$$ LANGUAGE plpgsql;

-- Valida a placa do veículo (ANTIGO E NOVO)
CREATE OR REPLACE FUNCTION validar_placa(placa TEXT)
RETURNS BOOLEAN AS $$
BEGIN
  RETURN 
    placa ~ '^[A-Z]{3}-\d{4}$' OR
    placa ~ '^[A-Z]{3}\d{1}[A-Z]{1}\d{2}$';
END;
$$ LANGUAGE plpgsql;