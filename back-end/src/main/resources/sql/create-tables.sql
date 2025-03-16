-- Cria a tabela de Funcionários
CREATE TABLE IF NOT EXISTS funcionarios (
    -- Dados Pessoais
    id SERIAL PRIMARY KEY,
    nome_completo VARCHAR(100) NOT NULL,
    cpf VARCHAR(14) UNIQUE NOT NULL CHECK (validar_cpf(cpf)),
    rg VARCHAR(12) UNIQUE NOT NULL CHECK (validar_rg(rg)), 
    data_nascimento DATE NOT NULL CHECK (validar_maioridade(data_nascimento)), 
    sexo VARCHAR(50) NOT NULL,
    estado_civil VARCHAR(50) NOT NULL, 
    nacionalidade VARCHAR(50) NOT NULL,
    naturalidade VARCHAR(50) NOT NULL,

    -- Endereco
    logradouro VARCHAR(100) NOT NULL,
    numero_endereco VARCHAR(10) NOT NULL,
    bairro VARCHAR(50) NOT NULL,
    cidade VARCHAR(50) NOT NULL,
    cep VARCHAR(9) NOT NULL CHECK (validar_cep(cep)),
    complemento VARCHAR(50),

    -- Contato
    telefone VARCHAR(20) NOT NULL CHECK (validar_telefone(telefone)),
    email VARCHAR(100) NOT NULL CHECK (validar_email(email)),

    -- Profissionais
    escolaridade VARCHAR(50) NOT NULL, 
    categoria_cnh VARCHAR(2) NOT NULL, 
    numero_cnh VARCHAR(11) UNIQUE CHECK (validar_cnh(numero_cnh)),
    validade_cnh DATE CHECK (validade_cnh > CURRENT_DATE),

    -- Contato de emergência
    contato_emergencia_nome VARCHAR(100) NOT NULL,
    contato_emergencia_parentesco VARCHAR(20) NOT NULL,
    contato_emergencia_telefone VARCHAR(20) NOT NULL CHECK (validar_telefone(contato_emergencia_telefone)),
    
    -- Documentação
    pis_pasep VARCHAR(14) NOT NULL CHECK (validar_pis_pasep(pis_pasep)),
    ctps VARCHAR(18) CHECK (ctps IS NULL OR validar_ctps(ctps)),
    titulo_eleitor VARCHAR(14) CHECK (validar_titulo_eleitor(titulo_eleitor)),
    uf_emissor VARCHAR(2) NOT NULL,
    orgao_emissor VARCHAR(10) NOT NULL, 

    -- Observações
    observacoes TEXT,
    data_cadastro TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Cria a tabela de Clientes
CREATE TABLE IF NOT EXISTS clientes (
    -- Dados Pessoais
    id SERIAL PRIMARY KEY,
    nome_completo VARCHAR(100) NOT NULL,
    data_nascimento DATE NOT NULL,
    naturalidade VARCHAR(50) NOT NULL,
    nacionalidade VARCHAR(50) NOT NULL,
    escolaridade VARCHAR(50) NOT NULL, 
    estado_civil VARCHAR(50) NOT NULL, 
    sexo VARCHAR(50) NOT NULL, 
    nome_pai VARCHAR(100) NOT NULL,
    nome_mae VARCHAR(100) NOT NULL,

    -- Contato
    logradouro VARCHAR(100) NOT NULL,
    numero_endereco VARCHAR(10) NOT NULL,
    bairro VARCHAR(50) NOT NULL,
    cidade VARCHAR(50) NOT NULL,
    cep VARCHAR(9) NOT NULL CHECK (validar_cep(cep)),
    complemento VARCHAR(50),
    telefone VARCHAR(20) NOT NULL CHECK (validar_telefone(telefone)),
    email VARCHAR(100) CHECK (validar_email(email)),
    
    -- Documentação
    cpf VARCHAR(14) UNIQUE NOT NULL CHECK (validar_cpf(cpf)),
    rg VARCHAR(12) UNIQUE NOT NULL CHECK (validar_rg(rg)), 
    orgao_emissor VARCHAR(10) NOT NULL, 
    uf_emissor VARCHAR(2) NOT NULL,

    -- Emergência
    contato_emergencia_nome VARCHAR(100) NOT NULL,
    contato_emergencia_parentesco VARCHAR(20) NOT NULL,
    contato_emergencia_telefone VARCHAR(20) NOT NULL CHECK (validar_telefone(contato_emergencia_telefone)),

    -- Observações
    observacoes TEXT,
    data_cadastro TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Cria a tabela de Veículos
CREATE TABLE IF NOT EXISTS veiculos (
    -- Dados do Veículo
    id SERIAL PRIMARY KEY,
    placa VARCHAR(8) UNIQUE NOT NULL CHECK (validar_placa(placa)),
    renavam VARCHAR(11) UNIQUE NOT NULL CHECK (validar_renavam(renavam)),
    marca VARCHAR(50) NOT NULL,
    modelo VARCHAR(50) NOT NULL,
    ano_modelo VARCHAR(9) NOT NULL,
    categoria VARCHAR(20) NOT NULL,

    -- Dados de atividade
    data_inicio DATE NOT NULL,
    data_limite DATE NOT NULL,

    -- Observações
    observacoes TEXT,
    data_cadastro TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);