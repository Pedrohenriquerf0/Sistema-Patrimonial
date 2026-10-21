# 🏛️ Sistema Patrimonial

Aplicação desktop para controle de bens patrimoniais e itens de consumo. O sistema está sendo desenvolvido com **Java, JavaFX e MariaDB**, com telas FXML, acesso a dados JDBC e regras de negócio organizadas em serviços. 💻📦

## ✨ Funcionalidades implementadas

- 🏷️ Cadastro, consulta e remoção de itens patrimoniais.
- 📦 Cadastro e consulta de itens de consumo.
- 🔄 Registro de movimentações de itens de consumo.
- 📝 Registro e consulta de cautelas de itens patrimoniais.
- 🖥️ Interface desktop com JavaFX e FXML.
- 🗄️ Persistência usando MariaDB e JDBC.

> 🚧 O projeto continua em desenvolvimento. Consulte esta página novamente para acompanhar novos recursos e melhorias!

## 🛠️ Tecnologias

- Java 21
- MariaDB Server 12.2.2
- JavaFX 21.0.1 (Controls e FXML)
- Maven

## ✅ Pré-requisitos

1. ☕ Instale o JDK 21 e configure `JAVA_HOME`.
2. 🐬 Instale o MariaDB Server 12.2.2 e deixe o serviço iniciado.
3. 🧰 Instale o Apache Maven ou use o Maven integrado da sua IDE.
4. 🧾 O esquema SQL incluído no projeto está em `src/main/resources/database/esquema tabelas.sql`. Ele cria as tabelas usadas pela aplicação.

## 🗄️ Configurar o MariaDB

1. ▶️ Inicie o serviço do MariaDB.
2. 🏗️ Crie o banco de dados:

   ```sql
   CREATE DATABASE sistema_patrimonial
       CHARACTER SET utf8mb4
       COLLATE utf8mb4_unicode_ci;
   ```

3. 📥 Para configurar um banco **novo e vazio**, importe o esquema SQL incluído no projeto. Ele cria as tabelas `item_patrimoniado`, `item_consumo`, `cautelas` e `movimentacao_consumo`:

   ```bash
   mariadb -u root -p sistema_patrimonial < "src/main/resources/database/esquema tabelas.sql"
   ```

   No Windows, também é possível abrir `src/main/resources/database/esquema tabelas.sql` no HeidiSQL ou no cliente MariaDB e executar seu conteúdo conectado ao banco `sistema_patrimonial`.

   ⚠️ **Atenção:** o script contém `DROP TABLE IF EXISTS` e recria as tabelas. Executá-lo em um banco que já tenha dados pode apagá-los. Use-o somente para preparar um banco vazio. Para atualizar uma instalação existente, faça backup e use uma migração apropriada; não execute esse script como atualização.

4. ⚙️ Crie o arquivo local de configuração a partir do exemplo. Na raiz do repositório:

   ```powershell
   Copy-Item "src/main/resources/database/database.example.properties" "src/main/resources/database/database.properties"
   ```

5. 🔐 Edite `src/main/resources/database/database.properties` com os dados do seu servidor:

   ```properties
   db.url=jdbc:mariadb://localhost:3306/sistema_patrimonial
   db.username=root
   db.password=SUA_SENHA
   ```

   Use as credenciais de um usuário MariaDB que tenha permissão de leitura e escrita nesse banco. O arquivo com credenciais reais é ignorado pelo Git; não o publique. 🔒

## 🚀 Executar pela IDE (IntelliJ IDEA)

1. 📂 Clone o repositório e abra a pasta do projeto que contém o `pom.xml`.
2. ⏳ Aguarde a IDE importar o projeto Maven e baixar as dependências.
3. ☕ Configure o Project SDK para JDK 21.
4. 🗄️ Conclua a configuração do MariaDB acima.
5. ▶️ Abra `src/main/java/sys/patrimonio/view/Main.java` e execute `Main.main()` (Run `Main`).

A classe `Main` inicializa a aplicação JavaFX. O `AppLauncher` carrega as telas e configura os repositórios que usam o MariaDB.

## 🧪 Compilar e executar pelo terminal

Na pasta que contém o `pom.xml`, compile o projeto:

```bash
mvn clean package
```

O `pom.xml` configura o `javafx-maven-plugin`. Com o banco configurado, inicie a aplicação pelo Maven:

```bash
mvn javafx:run
```

O comando `mvn clean package` verifica a compilação e gera o JAR em `target/`. O JAR não empacota os runtimes do JavaFX; por isso, `java -jar` não é o comando de inicialização configurado neste projeto. Para iniciar pela IDE, siga os passos acima.

## 🧭 Estrutura do código

```text
src/main/java/sys/patrimonio/
├── config/       # conexão e contexto da aplicação
├── exceptions/   # exceções da aplicação
├── model/        # entidades e tipos de domínio
├── repository/   # repositórios e DAOs JDBC
├── service/      # regras e operações de negócio
├── util/         # utilitários
└── view/         # inicialização JavaFX e controllers

src/main/resources/
├── css/          # estilos
├── database/     # exemplo de configuração e esquema SQL
└── view/         # interfaces FXML
```

## 🩹 Problemas comuns

- **📄 Arquivo `database.properties` não encontrado:** confirme se criou `src/main/resources/database/database.properties` e recarregue o projeto Maven.
- **🔌 Falha ao conectar:** verifique se o serviço MariaDB está ativo, se URL/porta/banco estão corretos e se usuário e senha funcionam.
- **🧱 Tabela não encontrada:** confirme que executou `src/main/resources/database/esquema tabelas.sql` no banco configurado.
- **🪟 JavaFX não inicia:** confirme o JDK 21 e aguarde a sincronização Maven. Pelo terminal, tente iniciar com `mvn javafx:run`.

## 📜 Licença

Este projeto está disponível sob a licença **MIT**. Você pode usar, copiar, modificar e distribuir o software, inclusive em projetos comerciais, desde que mantenha o aviso de direitos autorais e o texto da licença nas cópias ou partes substanciais do projeto. O software é fornecido sem garantias.

Consulte o arquivo [`LICENSE`](LICENSE) para ler o texto completo.
