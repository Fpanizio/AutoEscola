-- Cria a tabela de Funcionários
CREATE TABLE IF NOT EXISTS funcionarios (
    -- Dados Pessoais
    id SERIAL PRIMARY KEY,
    nome_completo VARCHAR(100) NOT NULL,
    cpf VARCHAR(14) UNIQUE NOT NULL CHECK (validarCpf(cpf)),
    rg VARCHAR(12) UNIQUE NOT NULL CHECK (validarRg(rg)), 
    data_nascimento DATE NOT NULL CHECK (validarMaioridade(data_nascimento)), 
    sexo sexoEnum NOT NULL,
    estado_civil estadoCivilEnum NOT NULL,
    nacionalidade VARCHAR(50) NOT NULL,
    naturalidade VARCHAR(50) NOT NULL,

    --endereço
    logradouro VARCHAR(100) NOT NULL,
    numero_endereco VARCHAR(10) NOT NULL,
    bairro VARCHAR(50) NOT NULL,
    cidade VARCHAR(50) NOT NULL,
    cep VARCHAR(9) NOT NULL CHECK (validarCep(cep)),
    complemento VARCHAR(50),

    -- Contato
    telefone VARCHAR(20) NOT NULL CHECK (validarTelefone(telefone)),
    email VARCHAR(100) NOT NULL CHECK (validarEmail(email)),

    -- Profissionais
    escolaridade grauEscolaridadeEnum NOT NULL,
    categoria_cnh categoriaCnhEnum NOT NULL,
    numero_cnh VARCHAR(11) UNIQUE CHECK (validarCnh(numero_cnh)),
    validade_cnh DATE CHECK (validade_cnh > CURRENT_DATE),

    -- Contato de emergência
    contato_emergencia_nome VARCHAR(100) NOT NULL,
    contato_emergencia_parentesco VARCHAR(20) NOT NULL,
    contato_emergencia_telefone VARCHAR(20) NOT NULL CHECK (validarTelefone(contato_emergencia_telefone)),
    
    -- Documentação
    pis_pasep VARCHAR(14) NOT NULL CHECK (validarPisPasep(pis_pasep)),
    ctps VARCHAR(18) CHECK (ctps IS NULL OR validarCtps(ctps)),
    titulo_eleitor VARCHAR(14) CHECK (validarTituloEleitor(titulo_eleitor)),
    uf_emissor ufEnum NOT NULL,
    orgao_emissor orgaoEmissor NOT NULL,

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
    escolaridade grauEscolaridadeEnum NOT NULL,
    estado_civil estadoCivilEnum NOT NULL,
    sexo sexoEnum NOT NULL,
    nome_pai VARCHAR(100) NOT NULL,
    nome_mae VARCHAR(100) NOT NULL,

    -- Contato
    logradouro VARCHAR(100) NOT NULL,
    numero_endereco VARCHAR(10) NOT NULL,
    bairro VARCHAR(50) NOT NULL,
    cidade VARCHAR(50) NOT NULL,
    cep VARCHAR(9) NOT NULL CHECK (validarCep(cep)),
    complemento VARCHAR(50),
    telefone VARCHAR(20) NOT NULL CHECK (validarTelefone(telefone)),
    email VARCHAR(100) CHECK (validarEmail(email)),
    
    -- Documentação
    cpf VARCHAR(14) UNIQUE NOT NULL CHECK (validarCpf(cpf)),
    rg VARCHAR(12) UNIQUE NOT NULL CHECK (validarRg(rg)), 
    orgao_emissor orgaoEmissor NOT NULL,
    uf_emissor ufEnum NOT NULL,

    -- Emergência
    contato_emergencia_nome VARCHAR(100) NOT NULL,
    contato_emergencia_parentesco VARCHAR(20) NOT NULL,
    contato_emergencia_telefone VARCHAR(20) NOT NULL CHECK (validarTelefone(contato_emergencia_telefone)),

    -- Observações
    observacoes TEXT,
    data_cadastro TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Cria a tabela de Veículos
CREATE TABLE IF NOT EXISTS veiculos (
    -- Dados do Veículo
    id SERIAL PRIMARY KEY,
    placa VARCHAR(8) UNIQUE NOT NULL CHECK (validarPlaca(placa)),
    renavam VARCHAR(11) UNIQUE NOT NULL CHECK (validarRenavam(renavam)),
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