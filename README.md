# 💰 Sistema de Gestão de Despesas Pessoais 

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)
![MySQL](https://img.shields.io/badge/MySQL-005C84?style=for-the-badge&logo=mysql&logoColor=white)
![IntelliJ IDEA](https://img.shields.io/badge/IntelliJ_IDEA-000000?style=for-the-badge&logo=intellij-idea&logoColor=white)

> Um sistema backend robusto para controle financeiro via terminal, desenvolvido para consolidar conhecimentos em Java, JDBC e SQL Avançado.

## 💻 Sobre o Projeto

Este projeto nasceu da necessidade pessoal de rastrear gastos mensais de forma categorizada. Diferente de planilhas manuais, o sistema oferece uma interface CLI (Command Line Interface) interativa que persiste os dados em um banco relacional.

O foco técnico foi implementar uma arquitetura **MVC com DAO** manualmente, sem o uso de frameworks ORM (como Hibernate), para demonstrar domínio sobre a manipulação direta de dados e conexões JDBC.

## ⚙️ Funcionalidades

- [x] **Cadastro de Despesas:** Associação de gastos a categorias pré-definidas (Alimentação, Lazer, Transporte, etc).
- [x] **Extrato Detalhado:** Listagem de gastos formatada com padrão brasileiro de moeda e data.
- [x] **Relatórios Inteligentes:** Utilização de SQL para somar gastos agrupados por categoria.
- [x] **Gestão de Histórico:** Opção segura para limpar/zerar o banco de dados.
- [x] **Persistência de Dados:** Conexão robusta com MySQL 8.0+.

## 🛠 Tecnologias Utilizadas

* **Java 18+**: 
* **JDBC (Java Database Connectivity)**: Camada de conexão pura com o banco.
* **MySQL 8**: Banco de dados relacional.
* **Maven**: Gerenciamento de dependências.
* **Padrão DAO**: Separação completa entre Regra de Negócio e Acesso a Dados.

## 🗄️ Modelagem do Banco de Dados

O sistema utiliza duas tabelas principais com relacionamento **One-to-Many** (Uma categoria tem várias despesas).

```sql
CREATE TABLE categorias (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL
);

CREATE TABLE despesas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    descricao VARCHAR(255) NOT NULL,
    valor DECIMAL(10, 2) NOT NULL,
    data DATE NOT NULL,
    categoria_id INT,
    FOREIGN KEY (categoria_id) REFERENCES categorias(id)
);
