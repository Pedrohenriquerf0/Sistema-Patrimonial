# Sistema Patrimonial
# 🏛️ Sistema Patrimonial

Aplicação desktop para gestão de bens patrimoniais e itens de consumo. O sistema oferece cadastro e consulta de itens, controle de entradas e saídas de estoque e emissão e consulta de cautelas de bens patrimoniais.

## Funcionalidades
## ✨ Funcionalidades

- Cadastro, edição, pesquisa e remoção de itens patrimoniais.
- Cadastro, edição, pesquisa e remoção de itens de consumo.
- Registro de entradas e saídas de itens de consumo, com consulta ao histórico de movimentações.
- Criação e consulta de cautelas associadas a bens patrimoniais.
- Armazenamento de dados no MariaDB, com acesso JDBC.
- Interface desktop em JavaFX, com telas definidas em FXML.

## Tecnologias
## 🛠️ Tecnologias

- Java 21
- JavaFX 21.0.1 (Controls e FXML)
- MariaDB e MariaDB Connector/J 3.3.3
- Maven e JavaFX Maven Plugin

## Requisitos
## ✅ Requisitos

- JDK 21.
- MariaDB Server em execução.
- Acesso ao Maven pelo `mvn` ou uso do Maven Wrapper incluído (`mvnw`/`mvnw.cmd`).

## Configuração do banco de dados
## 🗄️ Configuração do banco de dados

1. Crie o banco de dados:

   ```sql
   CREATE DATABASE sistema_patrimonial
       CHARACTER SET utf8mb4
       COLLATE utf8mb4_unicode_ci;
   ```

2. Importe o esquema em um banco novo e vazio. No Windows PowerShell:

   ```powershell
   Get-Content -Raw "src/main/resources/database/esquema tabelas.sql" | mariadb -u root -p sistema_patrimonial
   ```

   Também é possível executar o arquivo pelo HeidiSQL ou por outro cliente MariaDB, conectado ao banco `sistema_patrimonial`.

   **Atenção:** `esquema tabelas.sql` contém comandos `DROP TABLE IF EXISTS` e recria as tabelas. Importá-lo em um banco existente pode apagar os dados dessas tabelas. Faça backup e não use esse arquivo como migração de uma instalação com dados.
   ⚠️ **Atenção:** `esquema tabelas.sql` contém comandos `DROP TABLE IF EXISTS` e recria as tabelas. Importá-lo em um banco existente pode apagar os dados dessas tabelas. Faça backup e não use esse arquivo como migração de uma instalação com dados.

3. Crie a configuração local copiando o exemplo:

   ```powershell
   Copy-Item "src/main/resources/database/database.example.properties" "src/main/resources/database/database.properties"
   ```

4. Edite `src/main/resources/database/database.properties` e informe o banco, usuário e senha corretos:

   ```properties
   db.url=jdbc:mariadb://localhost:3306/sistema_patrimonial
   db.username=SEU_USUARIO
   db.password=SUA_SENHA
   ```

   O exemplo contém valores ilustrativos (`banco_de_dados` e `SUA_SENHA_AQUI`); substitua-os antes de executar. O arquivo `database.properties` está no `.gitignore`. Não publique credenciais reais no repositório.

## Executar
## 🚀 Executar

Na raiz do projeto, compile o código:

```powershell
./mvnw.cmd clean package
```

Para iniciar a aplicação:

```powershell
./mvnw.cmd javafx:run
```

No Linux ou macOS, use `./mvnw` no lugar de `./mvnw.cmd`. Também é possível abrir o projeto na IDE como projeto Maven, configurar o JDK 21 e executar `sys.patrimonio.view.Main`.

O banco precisa estar configurado e acessível ao iniciar o aplicativo. O ponto de entrada está em `src/main/java/sys/patrimonio/view/Main.java` e o inicializador carrega a interface `view/Home.fxml`.

## Organização do projeto
## 🧭 Organização do projeto

```text
src/main/java/sys/patrimonio/
├── config/       # conexão e contexto da aplicação
├── exceptions/   # exceções da aplicação
├── model/        # entidades e tipos de domínio
├── repository/   # repositórios e acesso JDBC
├── service/      # operações e regras de negócio
├── util/         # utilitários e carregamento de telas
└── view/         # inicialização JavaFX e controllers

src/main/resources/
├── css/          # estilos da interface
├── database/     # exemplo de configuração e esquema SQL
├── icons/        # ícones
└── view/         # telas FXML
```

## Problemas comuns
## 🩹 Problemas comuns

- **Erro ao carregar `database.properties`:** confirme que o arquivo está em `src/main/resources/database/` e se chama exatamente `database.properties`.
- **Falha ao conectar ao MariaDB:** confira se o serviço está ativo e se `db.url`, `db.username` e `db.password` estão corretos.
- **Tabela não encontrada:** confirme que o esquema foi importado no banco `sistema_patrimonial`.
- **JavaFX não inicia:** confirme que está usando JDK 21 e execute a aplicação com `./mvnw.cmd javafx:run` no Windows ou `./mvnw javafx:run` em Linux/macOS.

## Licença
## 📜 Licença

Distribuído sob a licença MIT. Consulte o arquivo [`LICENSE`](LICENSE) para ver os termos.
