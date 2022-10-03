# FIAPCARD - Avaliação

<p align="center">
<img src="img\trabalho.png" alt="Desenho macro de solução para o Trabalho">
</p>
> O objetivo deste projeto é desenvolver uma solução de pagamentos chama FIAPCARD, um cartão de crédito para ser utilizado pelos
alunos. Para isso a FIAP necessita de um sistema para gerenciamento e integração com outras empresas.
  > Este projeto será utilizado como avaliação da disciplina de Spring e WebServices & Restfull Technologies

### :white_check_mark: Objetivos, Regras e Requisitos

O projeto contem os seguintes requisitos funcionais

- [x] Cadastro de Alunos
- [ ] O cadastro inicial dos potenciais clientes do cartão será realizado via integração com um arquivo .txt
- [ ] As compras realizadas nos cartões dos clientes serão recebidas via integração com uma Autorizadora. Criar os endpoints necessários para receber as realizações de transações.
- [ ] Deve ser possível gerar um extrato via download (endpoint) ou enviado no email do cliente (pode escolher uma opção).

Além disso, é necessário realizar os seguintes requisitos não funcionais obrigatórios (Para avaliação de ambas as disciplinas):

- [x] Utilizar o Spring Framework.
- [x] Criar um arquivo texto/readme.md com as instruções para subir o/os projeto/s. Deve conter:
  - O tema;​
  - As tecnologias escolhidas (linguagem, framework, aplicação etc.);​
  - Endereço do código-fonte (licença open source);​
  - Página da documentação da API.
  - Justificativa para as escolhas.
- [ ] Criar testes unitários e integrados para o/os projeto/s
- [ ] Gerar uma massa simulada de transações
- [x] Documentação Swagger
- [ ] Criar uma aplicação (Consumidor + API) relacionado

Requisitos funcionais opcionais:

- Implementar segurança;​
- Código-fonte armazenado em um VCS (ex. GitHub e BitBucket);​
- Execução da aplicação em nuvem (ex. Heroku, GitHub Pages, AWS);​
- Ferramenta de gestão de dependências;​
- Persistência e/ou caching.​

## 💻 Pré-requisitos

Antes de começar, verifique se você atendeu aos seguintes requisitos:

- Você instalou a versão mais recente de `Docker, Docker-compose, Docker Desktop, etc...`

- Você tenha instalado a `JDK 17 juntamente com sua IDE favorita para Java`.

- Você tenha instalado alguma plataforma de API para desenvolvedores, como o `Postman API ou Insomnia REST`.

- Caso deseje também, pode utilizar o `MySQL Workbench` para visualizar melhor o banco de dados e seus schemas.

## 🚀 Executando o projeto

Para executar o projeto, siga estas etapas:

Acesse o diretorio AvaliacaoFInal via terminal e execute o comando:

```
docker-compose up -d
```

Em seguida execute as três aplicações inclusas no repositório (`43scjAdmCentralApp, 43scjDroneApp, 43scjNotifyApp`). Lembre-se de sempre fazer o Reload das suas dependências Maven e, quando julgar necessário, limpar e refazer o build da aplicação com o comando

```
mvn clean install
```

:heavy_exclamation_mark::heavy_exclamation_mark::heavy_exclamation_mark: <b>ATENÇÃO:</b> Verifique sempre os logs da aplicação na sua IDE para análisar os resultados. Preste atenção as configurações de porta no `application.properties` de cada aplicação e faça as alterações que achar necessárias para execução.

## ☕ Usando e testando o projeto

Para testar o projeto basta abrir a sua plataforma de API (Postman ou Insomnia, por exemplo) e realizar uma requisição HTTP com metodo `POST` qualquer com o seguinte formato :

```
{

}
```

Para validar se o projeto atende as regras, é só alterar os valores da requisição, executa-la novamente e avaliar os logs da aplicação.

## 😄 Fique a vontade para avaliar e contribuir!<br>

Todo feedback é bem-vindo e o processo de melhoria é continuo!

<p align="center"><a href="https://www.linkedin.com/in/caramujox/" alt="Linkedin">
<img src="https://img.shields.io/badge/-Linkedin-0e76a8?style=flat-square&logo=Linkedin&logoColor=white" /></a>
<a href="#" alt="Twitter">
<img src="https://img.shields.io/twitter/follow/camirujo?style=social" /></a>
</p>

[⬆ Voltar ao topo](#projeto-final-integration-and-devtools)<br>
