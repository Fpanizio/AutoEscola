-- Verifica e cria os ENUMs se não existirem
DO $$ 
BEGIN 
  -- Grau de Escolaridade
  IF NOT EXISTS (SELECT 1 FROM pg_type WHERE typname = 'grauEscolaridadeEnum') THEN 
    CREATE TYPE grauEscolaridadeEnum AS ENUM (
      'Analfabeto',
      'Ensino Fundamental Incompleto',
      'Ensino Fundamental Completo',
      'Ensino Médio Incompleto',
      'Ensino Médio Completo',
      'Ensino Superior Incompleto',
      'Ensino Superior Completo'
    );
  END IF;

  -- Estado Civil
  IF NOT EXISTS (SELECT 1 FROM pg_type WHERE typname = 'estadoCivilEnum') THEN 
    CREATE TYPE estadoCivilEnum AS ENUM (
      'SOLTEIRO',
      'CASADO',
      'DIVORCIADO',
      'VIUVO',
      'SEPARADO',
      'UNIAO_ESTAVEL'
    );
  END IF;

  -- Situação
  IF NOT EXISTS (SELECT 1 FROM pg_type WHERE typname = 'situacaoEnum') THEN 
    CREATE TYPE situacaoEnum AS ENUM ('Ativo', 'Inativo');
  END IF;

  -- Categoria
  IF NOT EXISTS (SELECT 1 FROM pg_type WHERE typname = 'categoriaCnhEnum') THEN 
    CREATE TYPE categoriaCnhEnum AS ENUM ('A', 'B', 'C', 'D', 'E', 'AB');
  END IF;

  -- Sexo Enum
  IF NOT EXISTS (SELECT 1 FROM pg_type WHERE typname = 'sexoEnum') THEN 
    CREATE TYPE sexoEnum AS ENUM ('Masculino', 'Feminino', 'Não Informado');
  END IF;

  -- Cargo Enum
  IF NOT EXISTS (SELECT 1 FROM pg_type WHERE typname = 'cargoEnum') THEN 
    CREATE TYPE cargoEnum AS ENUM (
      'Administrador',
      'Gerente',
      'Instrutor',
      'Professor',
      'Recepcionista'
    );
  END IF;

  IF NOT EXISTS (SELECT 1 FROM pg_type WHERE typname = 'ufEnum') THEN 
    CREATE TYPE ufEnum AS ENUM (
      'AC', 'AL', 'AP', 'AM', 'BA', 'CE', 'DF', 'ES', 'GO', 'MA', 
      'MT', 'MS', 'MG', 'PA', 'PB', 'PR', 'PE', 'PI', 'RJ', 'RN', 
      'RS', 'RO', 'RR', 'SC', 'SP', 'SE', 'TO'
    );
  END IF;

  IF NOT EXISTS (SELECT 1 FROM pg_type WHERE typname = 'orgaoEmissor') THEN 
    CREATE TYPE orgaoEmissor AS ENUM (
    'SSP', 'DETRAN', 'PF', 'PM', 'CNT', 'IFP', 'IPF', 'MA', 'MRE'
);
  END IF;
END $$;