# Consumer

Essa aplicação visa ser um exemplo de integração em containers entre Spring boot, Kafka e Oracle database.

## Pré requisitos:
* Ter git instalado
* Ter docker instalado
* Ter docker-compose instalado

## Modo de execução:
* (Pode demorar algum tempo) [Build a imagen do Oracle em sua máquina local](https://medium.com/xp-inc/dica-r%C3%A1pida-criando-base-de-dados-oracle-vers%C3%A3o-21-3-0-no-docker-357b05754b84) por meio dos comandos:
  * git clone https://github.com/oracle/docker-images.git
  * cd docker-images/OracleDatabase/SingleInstance/dockerfiles ./buildContainerImage.sh -v 21.3.0 -x
* A partir da pasta raiz da aplicação build a aplicação com o comando:
  * ./gradlew clean build
* (Demora um pouco por conta da inicialização do Oracle, pois o serviço do consumidor depende dele) A partir da pasta raiz do projeto executar o comando:
  * docker-compose up -d
 
## Modo de desenvolvimento:
* Abra o projeto em sua IDE  de preferência
* Tenha um servidor Oracle com o schema e a tabela de paciente configurada, na raiz do projeto existe um script para sua criação chamado init.sql
* Tenha o servidor do kafka rodando
* Configure as variáveis de ambiente em sua IDE de preferêcia ou sistema operacional, são elas:
  * DATABASE_URL
  * DATABASE_USER
  * DATABASE_PASSWORD
  * KAFKA_URL

## Padrão das mensagens para o Kafka
* Formato do Json:
  * { "nome": "nome do paciente", "cpf": "24062719002", "data_nascimento": "2024-10-07" }
* Conforme especificado acima, o formato da data_nascimento deve segui ano, mês e dia no formato:
  * yyyy-MM-dd
* O CPF utilizado deve ser um CPF válido e somente os números sem dígitos.
