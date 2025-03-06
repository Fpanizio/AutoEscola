package panizio.DrivingSchool.database;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component // Anotação adicionada
public class DatabaseInitializer {
  @Autowired
  private JdbcTemplate jdbcTemplate;

  private final List<String> scripts = List.of(
      "sql/create-enums.sql",
      "sql/create-validations.sql",
      "sql/create-tables.sql");

  public void initDatabase() {
    scripts.forEach(script -> {
      try {
        // Correção na leitura do arquivo
        String sql = new String(
            new ClassPathResource(script).getInputStream().readAllBytes());
        jdbcTemplate.execute(sql);
        System.out.println("Executado: " + script);
      } catch (IOException e) {
        System.err.println("Erro ao ler o arquivo: " + script);
        e.printStackTrace();
      } catch (Exception e) {
        System.err.println("Erro ao executar o arquivo: " + script);
        e.printStackTrace();
      }
    });
  }
}