# Sistema Patrimonial 🗄️

> Sistema de controle e gestão de patrimônio desenvolvido em Java.

O **Sistema Patrimonial** é uma aplicação desenvolvida em Java para controle, organização e gestão de bens de uma instituição ou empresa. Permite cadastrar, localizar, movimentar e acompanhar itens patrimoniais e de consumo, incluindo o registro de cautelas de equipamentos.

---

## 🚧 Status do Projeto

Em desenvolvimento. A lógica de negócio principal já está funcional via linha de comando. Interface gráfica e persistência em banco de dados estão em implementação.

* ✅ Cadastro de itens
* ✅ Listagem de itens
* ✅ Busca de itens
* ✅ Remoção de itens
* ✅ Criação de cautela (registro de responsabilidade sobre equipamento)
* ✅ Movimentação de item de consumo
* 🔄 Interface gráfica com JavaFX — em desenvolvimento
* 🔄 Persistência de dados com MariaDB — em implementação (dados atualmente tratados em memória/estrutura local)

---

## 🛠️ Tecnologias

* **[Java](https://www.oracle.com/java/):** Linguagem principal do projeto.
* **[JavaFX](https://openjfx.io/):** Interface gráfica (em desenvolvimento).
* **[MariaDB](https://mariadb.org/):** Banco de dados relacional para persistência (em implementação).

---

## 📋 Pré-requisitos

* JDK instalado (versão compatível com JavaFX).
* MariaDB instalado e configurado (necessário apenas quando a integração com banco de dados estiver concluída).

---

## ▶️ Como executar (versão atual — linha de comando)

1. Clone o repositório:
   ```
   git clone https://github.com/Pedrohenriquerf0/Sistema-Patrimonial.git
   ```
2. Compile e execute a classe principal do projeto pela sua IDE de preferência (IntelliJ, Eclipse, NetBeans) ou via terminal com `javac`/`java`.

*Instruções detalhadas de execução via terminal serão adicionadas conforme a integração com MariaDB for concluída.*

---

## 🗺️ Próximos passos

* Finalizar interface gráfica com JavaFX.
* Concluir integração com MariaDB para persistência de dados.
* Adicionar relatórios de movimentação e cautela.
