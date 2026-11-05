# 🏛️ Sistema Patrimonial

Aplicação desktop para gestão de bens patrimoniais e itens de consumo. O sistema oferece cadastro e consulta de itens, controle de entradas e saídas de estoque, emissão de cautelas de bens patrimoniais e exportação de relatórios.

## ✨ Funcionalidades

- Cadastro, edição, pesquisa e remoção de itens patrimoniais.
- Cadastro, edição, pesquisa e remoção de itens de consumo.
- Registro de entradas e saídas de itens de consumo, com validação de saldo e histórico de movimentações.
- Criação e consulta de cautelas associadas a bens patrimoniais, com geração de recibos.
- **[Novo]** Geração e exportação de relatórios gerenciais em formato Excel (.xlsx).
- **[Novo]** Validação de regras de negócio através de testes automatizados.
- Armazenamento de dados no MariaDB, com acesso estruturado via padrão DAO/JDBC.
- Interface desktop interativa em JavaFX, com telas estilizadas via CSS e FXML.

## 🛠️ Tecnologias

- **Linguagem:** Java 21
- **Interface Gráfica:** JavaFX 21.0.1 (Controls e FXML)
- **Banco de Dados:** MariaDB e MariaDB Connector/J 3.3.3
- **Testes Automatizados:** JUnit 5
- **Manipulação de Arquivos:** Apache POI (Geração de Excel)
- **Build e Gerenciamento:** Maven (com Maven Wrapper e Maven Shade Plugin)

## ✅ Requisitos

- JDK 21 instalado nas variáveis de ambiente.
- MariaDB Server em execução.
- Acesso ao Maven pelo `mvn` ou uso do Maven Wrapper incluído (`mvnw`/`mvnw.cmd`).

## 🗄️ Configuração do banco de dados

1. Crie o banco de dados:

   ```sql
   CREATE DATABASE sistema_patrimonial
       CHARACTER SET utf8mb4
       COLLATE utf8mb4_unicode_ci;
   ```

2. Importe o esquema em um banco novo e vazio. No Windows PowerShell:

   ```powershell
   Get-Content -Raw "src/main/resources/database/esquema_tabelas.sql" | mariadb -u root -p sistema_patrimonial
   ```

   Também é possível executar o arquivo pelo HeidiSQL, DBeaver ou por outro cliente MariaDB conectado ao banco `sistema_patrimonial`.

   ⚠️ **Atenção:** O script SQL contém comandos `DROP TABLE IF EXISTS` e recria as tabelas. Importá-lo em um banco existente apagará os dados dessas tabelas. Faça backup e não use esse arquivo como migração de uma instalação com dados.

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
   *(O arquivo `database.properties` está no `.gitignore`. Não publique credenciais reais no repositório).*

## 🚀 Executar e Testar

### Executar os Testes Automatizados
Para rodar a suíte de testes JUnit e validar as regras de negócio:
```powershell
./mvnw.cmd test
```

### Executar em Ambiente de Desenvolvimento
Para compilar e iniciar a aplicação rapidamente via Maven:
```powershell
./mvnw.cmd clean javafx:run
```

### Gerar e Executar o Executável (Fat JAR)
Para gerar uma versão final da aplicação (ideal para distribuição e portfólio), empacotando todas as dependências:
```powershell
./mvnw.cmd clean package
```
O executável será gerado na pasta `target/`. Para rodar:
```powershell
java -jar target/Sistema-Patrimonial-1.0.0.jar
```
*(Nota: No Linux ou macOS, use `./mvnw` no lugar de `./mvnw.cmd`)*.

O ponto de entrada principal para o executável é a classe `sys.patrimonio.view.AppLauncher`, que carrega o contexto do JavaFX e a interface `view/Home.fxml` de forma segura.

## 🧭 Organização do projeto

```text
Sistema-Patrimonial/
├── src/main/java/sys/patrimonio/
│   ├── config/       # conexão, propriedades e contexto da aplicação (Service Locator)
│   ├── exceptions/   # exceções personalizadas de regras de negócio
│   ├── model/        # entidades e tipos de domínio
│   ├── repository/   # padrão DAO e acesso JDBC ao banco
│   ├── service/      # operações, regras de negócio e validações
│   ├── util/         # utilitários, carregamento de telas e geração de XLSX
│   └── view/         # AppLauncher (ponto de entrada) e controllers JavaFX
│
├── src/main/resources/
│   ├── css/          # folhas de estilo globais e específicas das telas
│   ├── database/     # exemplo de properties e script SQL de criação do esquema
│   ├── icons/        # recursos visuais
│   └── view/         # arquivos FXML contendo o design das telas
│
└── src/test/java/sys/patrimonio/
    └── service/      # testes automatizados unitários (JUnit)
```

## 🩹 Problemas comuns

- **Erro ao carregar `database.properties`:** Confirme que o arquivo foi renomeado corretamente a partir do exemplo, está em `src/main/resources/database/` e não possui extensões ocultas (`.properties.txt`).
- **Falha ao conectar ao MariaDB:** Confira se o serviço está ativo e se a porta (padrão 3306) e as credenciais estão corretas no arquivo de propriedades.
- **Tabela não encontrada:** Confirme que o esquema foi devidamente importado no banco `sistema_patrimonial`.
- **JavaFX não inicia no Fat JAR:** Certifique-se de executar via `AppLauncher` e não via `Main`, para evitar restrições de módulos do Java.

## 📜 Licença

Distribuído sob a licença MIT. Consulte o arquivo [`LICENSE`](LICENSE) para ver os termos.