-- Verifica e cria os ENUMs se não existirem
DO $$ 
BEGIN 
  -- Grau de Escolaridade
  IF NOT EXISTS (SELECT 1 FROM pg_type WHERE typname = 'grau_escolaridade_enum') THEN 
    CREATE TYPE grau_escolaridade_enum AS ENUM (
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

  -- Situação
  IF NOT EXISTS (SELECT 1 FROM pg_type WHERE typname = 'situacao_enum') THEN 
    CREATE TYPE situacao_enum AS ENUM ('Ativo', 'Inativo');
  END IF;

  -- Categoria
  IF NOT EXISTS (SELECT 1 FROM pg_type WHERE typname = 'categoria_cnh_enum') THEN 
    CREATE TYPE categoria_cnh_enum AS ENUM ('A', 'B', 'C', 'D', 'E', 'AB');
  END IF;

  -- Sexo Enum
  IF NOT EXISTS (SELECT 1 FROM pg_type WHERE typname = 'sexo_enum') THEN 
    CREATE TYPE sexo_enum AS ENUM ('Masculino', 'Feminino', 'Não Informado');
  END IF;

  -- Cargo Enum
  IF NOT EXISTS (SELECT 1 FROM pg_type WHERE typname = 'cargo_enum') THEN 
    CREATE TYPE cargo_enum AS ENUM (
      'Administrador',
      'Gerente',
      'Instrutor',
      'Professor',
      'Recepcionista'
    );
  END IF;
END $$;