-- Cria a tabela de Funcionários
CREATE TABLE IF NOT EXISTS funcionarios (
    -- Dados Pessoais
    id SERIAL PRIMARY KEY,
    nome_completo VARCHAR(100) NOT NULL,
    cpf VARCHAR(14) UNIQUE NOT NULL CHECK (validar_cpf(cpf)),
    rg VARCHAR(12) UNIQUE NOT NULL CHECK (validar_rg(rg)), 
    data_nascimento DATE NOT NULL CHECK (validar_maioridade(data_nascimento)), 
    sexo sexo_enum NOT NULL,
    estado_civil estado_civil_enum NOT NULL,
    nacionalidade VARCHAR(50) NOT NULL,
    naturalidade VARCHAR(50) NOT NULL,

    -- Contato
    logradouro VARCHAR(100) NOT NULL,
    numero_endereco VARCHAR(10) NOT NULL,
    bairro VARCHAR(50) NOT NULL,
    cidade VARCHAR(50) NOT NULL,
    uf VARCHAR(2) NOT NULL CHECK (validar_uf(uf)), 
    cep VARCHAR(9) NOT NULL CHECK (validar_cep(cep)),
    complemento VARCHAR(50) NOT NULL,
    telefone VARCHAR(20) NOT NULL CHECK (validar_telefone(telefone)),
    email VARCHAR(100) NOT NULL CHECK (validar_email(email)),

    -- Profissionais
    -- data_admissao DATE NOT NULL,
    -- data_demissao DATE CHECK (data_demissao IS NULL OR data_demissao > data_admissao),
    -- salario NUMERIC(10, 2) NOT NULL CHECK (salario > 0),
    -- situacao situacao_enum NOT NULL,
    escolaridade grau_escolaridade_enum NOT NULL,
    categoria_cnh categoria_cnh_enum,
    numero_cnh VARCHAR(11) UNIQUE CHECK (validar_cnh(numero_cnh)),
    validade_cnh DATE CHECK (validade_cnh > CURRENT_DATE),
    -- categoria_ensino categoria_cnh_enum NOT NULL,

    -- Contato de emergencia
    contato_emergencia_nome VARCHAR(100) NOT NULL,
    contato_emergencia_parentesco VARCHAR(20) NOT NULL,
    contato_emergencia_telefone VARCHAR(20) NOT NULL CHECK (validar_telefone(contato_emergencia_telefone)),
    
    -- Documentacao
    pis_pasep VARCHAR(14) CHECK NOT NULL (validar_pis_pasep(pis_pasep)),
    ctps VARCHAR(14) CHECK (ctps IS NULL OR validar_ctps(ctps)),
    titulo_eleitor VARCHAR(14) CHECK (validar_titulo_eleitor(titulo_eleitor)),

    -- Observacoes
    observacoes TEXT,
    data_cadastro TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Cria a tabela de Clientes
CREATE TABLE IF NOT EXISTS clientes (
    -- Dados Pessoais
    id SERIAL PRIMARY KEY,
    nome_completo VARCHAR(100) NOT NULL,
    -- cpf VARCHAR(11) UNIQUE NOT NULL CHECK (validar_cpf(cpf)),
    data_nascimento DATE NOT NULL,
    naturalidade VARCHAR(50) NOT NULL,
    nacionalidade VARCHAR(50) NOT NULL,
    escolaridade grau_escolaridade_enum NOT NULL,
    estado_civil estado_civil_enum NOT NULL,
    sexo sexo_enum NOT NULL,
    nome_pai VARCHAR(100) NOT NULL,
    nome_mae VARCHAR(100) NOT NULL,

    -- Contato
    logradouro VARCHAR(100) NOT NULL,
    numero_endereco VARCHAR(10) NOT NULL,
    bairro VARCHAR(50) NOT NULL,
    cidade VARCHAR(50) NOT NULL,
    uf VARCHAR(2) NOT NULL CHECK (validar_uf(uf)),
    cep VARCHAR(9) NOT NULL CHECK (validar_cep(cep)),
    complemento VARCHAR(50),
    telefone VARCHAR(20) NOT NULL CHECK (validar_telefone(telefone)),
    email VARCHAR(100) CHECK (validar_email(email)),
    
    -- Documentacão
    tipo_documento tipo_documento_enum NOT NULL,
    numero_documento VARCHAR(20) NOT NULL,
    orgao_emissor VARCHAR(50),
    uf_emissor VARCHAR(2) CHECK (validar_uf(uf_emissor)),

    -- Emergencia
    contato_emergencia_nome VARCHAR(100),
    contato_emergencia_telefone VARCHAR(20) CHECK (validar_telefone(contato_emergencia_telefone)),

    -- Dados do servico
    -- categoria categoria_cnh_enum NOT NULL,
    -- situacao situacao_enum NOT NULL,

    -- Observacoes
    observacoes TEXT,
    data_cadastro TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    -- Validacão condicional de documentos
    CONSTRAINT validar_documento CHECK (
        (
            tipo_documento = 'CPF'
            AND validar_cpf(numero_documento)
        )
        OR -- CPF sem pontuacão
        (
            tipo_documento = 'RG'
            AND validar_rg(numero_documento)
        )
        OR -- RG sem pontuacão
        (
            tipo_documento = 'Passaporte'
            AND validar_passaporte(numero_documento)
        ) -- Formato BR123456
    )
);

-- Cria a tabela de Veículos
CREATE TABLE IF NOT EXISTS veiculos (
    -- Dados do Veículo
    id SERIAL PRIMARY KEY,
    placa VARCHAR(8) UNIQUE NOT NULL CHECK (validar_placa(placa)),
    renavam VARCHAR(11) UNIQUE NOT NULL CHECK (validar_renavam(renavam)),
    marca VARCHAR(50) NOT NULL,
    modelo VARCHAR(50) NOT NULL,
    ano_fabricacao INTEGER NOT NULL,
    ano_modelo INTEGER NOT NULL,
    categoria VARCHAR(20) NOT NULL,

    -- Dados de atividade
    data_inicio DATE NOT NULL,
    data_limite DATE NOT NULL,

    -- Observacoes
    observacoes TEXT,
    data_cadastro TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);