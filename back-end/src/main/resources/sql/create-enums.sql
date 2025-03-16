-- NÃO ESTÁ SENDO MAIS USADO!

-- Verifica e cria os ENUMs se não existirem
DO $$ 
BEGIN 
  -- Grau de Escolaridade (ajustado para maiúsculas e padrão consistente)
  IF NOT EXISTS (SELECT 1 FROM pg_type WHERE typname = 'grau_escolaridade_enum') THEN 
    CREATE TYPE grau_escolaridade_enum AS ENUM (
      'ANALFABETO',
      'ENSINO_FUNDAMENTAL_INCOMPLETO',
      'ENSINO_FUNDAMENTAL_COMPLETO',
      'ENSINO_MEDIO_INCOMPLETO',
      'ENSINO_MEDIO_COMPLETO',
      'ENSINO_SUPERIOR_INCOMPLETO',
      'ENSINO_SUPERIOR_COMPLETO'
    );
  END IF;

  -- Estado Civil (acentos removidos e padronizado)
  IF NOT EXISTS (SELECT 1 FROM pg_type WHERE typname = 'estado_civil_enum') THEN 
    CREATE TYPE estado_civil_enum AS ENUM (
      'SOLTEIRO',
      'CASADO',
      'DIVORCIADO',
      'VIUVO',
      'SEPARADO',
      'UNIAO_ESTAVEL'
    );
  END IF;

  -- Situação (padronizado para maiúsculas)
  IF NOT EXISTS (SELECT 1 FROM pg_type WHERE typname = 'situacao_enum') THEN 
    CREATE TYPE situacao_enum AS ENUM ('ATIVO', 'INATIVO');
  END IF;

  -- Categoria CNH (padronizado nome do tipo)
  IF NOT EXISTS (SELECT 1 FROM pg_type WHERE typname = 'categoria_cnh_enum') THEN 
    CREATE TYPE categoria_cnh_enum AS ENUM ('A', 'B', 'C', 'D', 'E', 'AB');
  END IF;

  -- Sexo (acentos removidos e padronizado)
  IF NOT EXISTS (SELECT 1 FROM pg_type WHERE typname = 'sexo_enum') THEN 
    CREATE TYPE sexo_enum AS ENUM ('MASCULINO', 'FEMININO', 'NAO_INFORMADO');
  END IF;

  -- Cargo (padronizado para maiúsculas)
  IF NOT EXISTS (SELECT 1 FROM pg_type WHERE typname = 'cargo_enum') THEN 
    CREATE TYPE cargo_enum AS ENUM (
      'ADMINISTRADOR',
      'GERENTE',
      'INSTRUTOR',
      'PROFESSOR',
      'RECEPCIONISTA'
    );
  END IF;

  -- UF (mantido padrão oficial sem alterações)
  IF NOT EXISTS (SELECT 1 FROM pg_type WHERE typname = 'uf_enum') THEN 
    CREATE TYPE uf_enum AS ENUM (
      'AC', 'AL', 'AP', 'AM', 'BA', 'CE', 'DF', 'ES', 'GO', 'MA', 
      'MT', 'MS', 'MG', 'PA', 'PB', 'PR', 'PE', 'PI', 'RJ', 'RN', 
      'RS', 'RO', 'RR', 'SC', 'SP', 'SE', 'TO'
    );
  END IF;

  -- Orgão Emissor (padronizado e sem acentos)
  IF NOT EXISTS (SELECT 1 FROM pg_type WHERE typname = 'orgao_emissor_enum') THEN 
    CREATE TYPE orgao_emissor_enum AS ENUM (
      'SSP', 'DETRAN', 'PF', 'PM', 'CNT', 'IFP', 'IPF', 'MA', 'MRE'
    );
  END IF;
END $$;