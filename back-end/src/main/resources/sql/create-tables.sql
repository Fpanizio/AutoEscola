-- Cria a tabela de Funcionários
CREATE TABLE IF NOT EXISTS funcionarios (
    -- Dados Pessoais
    id SERIAL PRIMARY KEY,
    nomeCompleto VARCHAR(100) NOT NULL,
    cpf VARCHAR(14) UNIQUE NOT NULL CHECK (validarCpf(cpf)),
    rg VARCHAR(12) UNIQUE NOT NULL CHECK (validarRg(rg)), 
    dataNascimento DATE NOT NULL CHECK (validarMaioridade(dataNascimento)), 
    sexo sexo_enum NOT NULL,
    estadoCivil estado_civil_enum NOT NULL,
    nacionalidade VARCHAR(50) NOT NULL,
    naturalidade VARCHAR(50) NOT NULL,

    -- Contato
    logradouro VARCHAR(100) NOT NULL,
    numeroEndereco VARCHAR(10) NOT NULL,
    bairro VARCHAR(50) NOT NULL,
    cidade VARCHAR(50) NOT NULL,
    uf VARCHAR(2) NOT NULL CHECK (validarUf(uf)), 
    cep VARCHAR(9) NOT NULL CHECK (validarCep(cep)),
    complemento VARCHAR(50),
    telefone VARCHAR(20) NOT NULL CHECK (validarTelefone(telefone)),
    email VARCHAR(100) NOT NULL CHECK (validarEmail(email)),

    -- Profissionais
    escolaridade grau_escolaridade_enum NOT NULL,
    categoriaCnh categoria_cnh_enum,
    numeroCnh VARCHAR(11) UNIQUE CHECK (validarCnh(numeroCnh)),
    validadeCnh DATE CHECK (validadeCnh > CURRENT_DATE),

    -- Contato de emergência
    contatoEmergenciaNome VARCHAR(100) NOT NULL,
    contatoEmergenciaParentesco VARCHAR(20) NOT NULL,
    contatoEmergenciaTelefone VARCHAR(20) NOT NULL CHECK (validarTelefone(contatoEmergenciaTelefone)),
    
    -- Documentação
    pisPasep VARCHAR(14) NOT NULL CHECK (validarPisPasep(pisPasep)),
    ctps VARCHAR(18) CHECK (ctps IS NULL OR validarCtps(ctps)),
    tituloEleitor VARCHAR(14) CHECK (validarTituloEleitor(tituloEleitor)),

    -- Observações
    observacoes TEXT,
    dataCadastro TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Cria a tabela de Clientes
CREATE TABLE IF NOT EXISTS clientes (
    -- Dados Pessoais
    id SERIAL PRIMARY KEY,
    nomeCompleto VARCHAR(100) NOT NULL,
    dataNascimento DATE NOT NULL,
    naturalidade VARCHAR(50) NOT NULL,
    nacionalidade VARCHAR(50) NOT NULL,
    escolaridade grau_escolaridade_enum NOT NULL,
    estadoCivil estado_civil_enum NOT NULL,
    sexo sexo_enum NOT NULL,
    nomePai VARCHAR(100) NOT NULL,
    nomeMae VARCHAR(100) NOT NULL,

    -- Contato
    logradouro VARCHAR(100) NOT NULL,
    numeroEndereco VARCHAR(10) NOT NULL,
    bairro VARCHAR(50) NOT NULL,
    cidade VARCHAR(50) NOT NULL,
    uf VARCHAR(2) NOT NULL CHECK (validarUf(uf)),
    cep VARCHAR(9) NOT NULL CHECK (validarCep(cep)),
    complemento VARCHAR(50),
    telefone VARCHAR(20) NOT NULL CHECK (validarTelefone(telefone)),
    email VARCHAR(100) CHECK (validarEmail(email)),
    
    -- Documentação
    cpf VARCHAR(14) UNIQUE NOT NULL CHECK (validarCpf(cpf)),
    rg VARCHAR(12) UNIQUE NOT NULL CHECK (validarRg(rg)), 
    orgaoEmissor VARCHAR(50),
    ufEmissor VARCHAR(2) CHECK (validarUf(ufEmissor)),

    -- Emergência
    contatoEmergenciaNome VARCHAR(100),
    contatoEmergenciaTelefone VARCHAR(20) CHECK (validarTelefone(contatoEmergenciaTelefone)),

    -- Observações
    observacoes TEXT,
    dataCadastro TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Cria a tabela de Veículos
CREATE TABLE IF NOT EXISTS veiculos (
    -- Dados do Veículo
    id SERIAL PRIMARY KEY,
    placa VARCHAR(8) UNIQUE NOT NULL CHECK (validarPlaca(placa)),
    renavam VARCHAR(11) UNIQUE NOT NULL CHECK (validarRenavam(renavam)),
    marca VARCHAR(50) NOT NULL,
    modelo VARCHAR(50) NOT NULL,
    anoFabricacao INTEGER NOT NULL,
    anoModelo INTEGER NOT NULL,
    categoria VARCHAR(20) NOT NULL,

    -- Dados de atividade
    dataInicio DATE NOT NULL,
    dataLimite DATE NOT NULL,

    -- Observações
    observacoes TEXT,
    dataCadastro TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);