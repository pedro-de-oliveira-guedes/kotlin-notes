# Desenvolvimento de APIs e os padrões RESTful
Esse arquivo contem informações importantes sobre o que são APIs, como funcionam, seus verbos, padrões e especificações.  
  
## Webservices
Aplicação hospedada na núvem, em que é possível realizar requisições por diversos sistemas, como a linha de comando (curl), navegadores (http), entre outros.  
  
Para *W3C*, os _webservices_ são aplicações de cliente-servidor pequenas, cada uma com uma função diferente, nos quais é possível realizar chamadas e requisições para obter informações e realizar ações.  
  
Esses webservices, no começo, não existia um padrão para receber e devolver informações nas requisições, o que dificultava bastante o desenvolvimento de aplicações duráveis e escaláveis, além de haver um vendor-lockin sempre que um serviço era contratado, já que nenhuma outra empresa utilizava padrões similares de requisição.  
  
Com o tempo, o padrão *SOAP* e *REST* se sobressaíram.  
  
### SOAP V.S. REST
Surgiu em 1998, criado pela Microsoft. Protocolo com envelopes próprios, utilizando HTTP para fazer chamadas RCP trafegando apenas XML.  
  
Já o REST, é um padrão de arquitetura que foi defendido em uma tese para apontar as fragilidades do SOAP. Nela, somente são aceitas requisiçẽos HTTP, que trafegam diferentes tipos de arquivos.  
  
O trabalho com REST é mais simples e mais leve, já que ele não utiliza nenhum tipo de envelope específico e extra, deixando os arquivos na mesma forma que são enviados, seja pelo cliente ou pelo servidor.  
  
---
  
## O que é REST
REST significa REpresentational State Transfer. São definidas algumas regras:
1. **Cliente-Servidor:** Ambos são separados, se comunicando pelas requisições.
2. **Stateless server:** O servidor não guarda informaçẽos sobre o cliente, de forma que cada nova requisição deve contem todas as informações necessárias para a execução da ação.
3. **Cacheable:** O servidor deve informar o cliente sobre o que será armazenado em cache, dando ao cliente a escolha de utilizar a memória ou não.
4. **Uniform Interface:** Todas as requisições e respostas seguem um mesmo padrão de construção.
    1. Identificação de recursos e caminho acessado (URI)
    2. Manipulação dos recursos pela própria representação (as is)
    3. Mensagens enviadas são auto descritivas (códigos HTTP)
    4. **Hypermedia As The Engine Of Application State - HATEOS**
5. **Sistema em camadas:** Melhor processamento de requisições, além da possibilidade de balanceamento de carga, proxies e firewalls.
6. **Código sob Demanda:** É uma "regra opcional", na qual o cliente poderia executar o código do servidor por si próprio.
Webservices com RESTful garantem desenvolvimento mais fácil, rápido e leve, de forma a garantir a escalabilidade.

## Tipos de parâmetros
Na própria URL enviada para a API, há alguns parâmetros posśiveis, sendo eles:
- **PATH Params:** São obrigatórios, dependendo da rota da API acessada, são separados por barras. (https://\<host\>/\<rota\>/\<param1\>/\<param2\>/...)
- **QUERY Params:** Não são obrigatórios, mas também são passados pela URL, separados através de "?" e "&". (https://\<host\>?\<paramName1\>=\<paramValue1\>&\<paramName2\>=\<paramVAlue2\>)  
  
Há também parâmetros enviados no cabeçalho da requisição (**HEADER Params**). Esses são **pares chave valor** e não podem ser enviados por requisições no browser tradicionalmente, sendo necessário um cliente de requisições como o Insomnia ou o Postman.  
  
Por fim, há os **BODY Params** que são enviados no corpo da requisição, também seguindo o padrão **chave valor**, através de clientes específicos de requisições HTTP.  
  
## STATUS Codes
São separados entre:
- **1XX**: São os **Informacionais**, retornam informações sobre a operação atual da requisição. Não são muito utilizados em APIs profissionais.
    - 100 - Continue
    - 101 - Switching Protocols
    - 102 - Processing
- **2XX**: São os de **Sucesso**, que retornam informações mais específicas sobre o resultado da requisição de acordo com o contexto de solicitação.
    - 200 - OK (Requisição executada com sucesso)
    - 201 - Created (Similar ao *200*, mas quer dizer que a requisição foi inserida em uma fila e será processada assim que possível)
    - 202 - Accepted
    - 203 - Non-authoritative Information
    - 204 - No content (Quando um registro é deletado, por exemplo, não é necessário uma resposta com conteúdo, apenas uma confirmação)
    - 205 - Reset Content
    - 206 - Partial Content
    - 207 - Multi-Status
    - 208 - Already Reported
    - 226 - IM Used
- **3XX**: São os de **Redirecionamento**. Não são particularmente muito utilizados nos sites.
    - 300 - Multiple choices
    - 301 - Moved Permanently
    - 302 - Found
    - 303 - See other
    - 304 - Not Modified
    - 305 - Use Proxy
    - 307 - Temporary Redirect
    - 308 - Permanent Redirect
- **4XX**: São os que indicam **Erro do cliente**. São muito utilizados e compõem uma lista muito grande, portanto, somente serão mencionados os mais utilizados.
    - 400 - Bad Request (Algum erro foi cometido pelo cliente ao estruturar a requisição. Seja na URL, no corpo, ou no cabeçalho)
    - 401 - Unauthorized (Acesso a algum endpoint não disponível para o nível de acesso atual do cliente)
    - 403 - Forbidden (Bastante similar ao 401)
    - 404 - Not Found (Nada foi encontrado no endpoint requisitado)
    - 408 - Request Timeout
    - 409 - Conflict (Tentaram criar um objeto que já existe.)
    - 415 - Unsupported Media Type
    - 418 - I'm a teapot (Para quando não há suporte para o que se tenta fazer)
    - 429 - Too Many Requests
    - 444 - Connection Closed Without Response
    - 499 - Client Closed Request
- **5XX**: Representam os **Erros de Servidor**, também são bastante comuns, mas muito extensos.
    - 500 - Internal Server Error (Erros do servidor para os quais não há muitos detalhes. Pode ser o timeout de uma query no BD, por exemplo)
    - 501 - Not Implemented
    - 502 - Bad Gateway
    - 503 - Service Unavailable
    - 504 - Gateway Timeout
    - 511 - Network Authentication Required
    - 599 - Network Connect Timeout Error
  
## Verbos/Métodos/Operações HTTP (REST)
