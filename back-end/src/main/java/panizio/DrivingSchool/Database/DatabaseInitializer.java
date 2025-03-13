// package panizio.DrivingSchool.database;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.ApplicationArguments;
// import org.springframework.boot.ApplicationRunner;
// import org.springframework.core.io.ClassPathResource;
// import org.springframework.jdbc.core.JdbcTemplate;
// import org.springframework.stereotype.Component;

// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;

// import java.io.IOException;
// import java.util.List;

// @Component
// public class DatabaseInitializer implements ApplicationRunner {

//     private static final Logger logger = LoggerFactory.getLogger(DatabaseInitializer.class);

//     @Autowired
//     private JdbcTemplate jdbcTemplate;

//     // Lista de scripts SQL na ordem correta
//     private final List<String> scripts = List.of(
//             "sql/create-enums.sql",
//             "sql/create-validations.sql",
//             "sql/create-tables.sql"
//     );

//     @Override
//     public void run(ApplicationArguments args) throws Exception {
//         scripts.forEach(script -> {
//             try {
//                 // Log de inicialização do arquivo
//                 logger.info("Inicializando o arquivo: {}", script);

//                 // Lê o arquivo SQL do classpath
//                 ClassPathResource resource = new ClassPathResource(script);
//                 String sql = new String(resource.getInputStream().readAllBytes());

//                 // Log do conteúdo (para depuração)
//                 logger.debug("Conteúdo de {}:\n{}", script, sql.substring(0, Math.min(sql.length(), 200)) + "...");

//                 // Executa o script no banco de dados
//                 jdbcTemplate.execute(sql);
//                 logger.info("Executado: {}", script);
//             } catch (IOException e) {
//                 logger.error("Erro ao ler o arquivo: {}", script, e);
//             } catch (Exception e) {
//                 logger.error("Erro ao executar o arquivo: {}", script, e);
//             }
//         });
//     }
// }