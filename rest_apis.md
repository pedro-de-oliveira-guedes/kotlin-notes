# Desenvolvimento de APIs e os padrões RESTful
Esse arquivo contem informações importantes sobre o que são APIs, como funcionam, seus verbos, padrões e especificações.  

<h2>Índice</h2>
<ul>
    <li><a href="#web-services">Web Services</a></li>
    <ul>
        <li><a href="#soap-rest">SOAP V.S. REST</a></li>
    </ul>
    <li><a href="#rest">O que é REST</a></li>
    <li><a href="#params-types">Tipos de parâmetros</a></li>
    <li><a href="#status-code"">STATUS Codes</a></li>
    <li><a href="#http-verbs">Verbos/Métodos/Operações HTTP (REST)</a></li>
    <ul>
        <li><a href="#most-used">Mais utilizados</a></li>
        <li><a href="#less-used">Menos utilizados</a></li>
    </ul>
    <li><a href="#maturity-levels">Níveis de Maturidade do REST</a></li>
    <li><a href="#hateoas">HATEOAS</a></li>
</ul>
  
<h2 id="web-services">Webservices</h2>
Aplicação hospedada na núvem, em que é possível realizar requisições por diversos sistemas, como a linha de comando (curl), navegadores (http), entre outros.  
  
Para *W3C*, os _webservices_ são aplicações de cliente-servidor pequenas, cada uma com uma função diferente, nos quais é possível realizar chamadas e requisições para obter informações e realizar ações.  
  
Esses webservices, no começo, não existia um padrão para receber e devolver informações nas requisições, o que dificultava bastante o desenvolvimento de aplicações duráveis e escaláveis, além de haver um vendor-lockin sempre que um serviço era contratado, já que nenhuma outra empresa utilizava padrões similares de requisição.  
  
Com o tempo, o padrão *SOAP* e *REST* se sobressaíram.  
  
<h3 id="soap-rest">SOAP V.S. REST</h3>
Surgiu em 1998, criado pela Microsoft. Protocolo com envelopes próprios, utilizando HTTP para fazer chamadas RCP trafegando apenas XML.  
  
Já o REST, é um padrão de arquitetura que foi defendido em uma tese para apontar as fragilidades do SOAP. Nela, somente são aceitas requisiçẽos HTTP, que trafegam diferentes tipos de arquivos.  
  
O trabalho com REST é mais simples e mais leve, já que ele não utiliza nenhum tipo de envelope específico e extra, deixando os arquivos na mesma forma que são enviados, seja pelo cliente ou pelo servidor.  
  
---
  
<h2 id="rest">O que é REST</h2>
REST significa REpresentational State Transfer. São definidas algumas regras:
<ol>
    <li><b>Cliente-Servidor:</b> Ambos são separados, se comunicando pelas requisições.</li>
    <li><b>Stateless server:</b> O servidor não guarda informaçẽos sobre o cliente, de forma que cada nova requisição deve contem todas as informações necessárias para a execução da ação.</li>
    <li><b>Cacheable:</b> O servidor deve informar o cliente sobre o que será armazenado em cache, dando ao cliente a escolha de utilizar a memória ou não.</li>
    <li><b>Uniform Interface:</b> Todas as requisições e respostas seguem um mesmo padrão de construção.</li>
        <ol>
            <li>Identificação de recursos e caminho acessado (URI)</li>
            <li>Manipulação dos recursos pela própria representação (as is)</li>
            <li>Mensagens enviadas são auto descritivas (códigos HTTP)</li>
            <li><b>Hypermedia As The Engine Of Application State - HATEOS</b></li>
        </ol>
    <li><b>Sistema em camadas:</b> Melhor processamento de requisições, além da possibilidade de balanceamento de carga, proxies e firewalls.</li>
    <li><b>Código sob Demanda:</b> É uma "regra opcional", na qual o cliente poderia executar o código do servidor por si próprio.</li>
</ol>
  
Webservices com RESTful garantem desenvolvimento mais fácil, rápido e leve, de forma a garantir a escalabilidade.

<h2 id="params-types">Tipos de parâmetros</h2>
Na própria URL enviada para a API, há alguns parâmetros posśiveis, sendo eles:
<ul>
    <li><b>PATH Params:</b> São obrigatórios, dependendo da rota da API acessada, são separados por barras. (https://[host]/[rota]/[param1]/[param2]/...)</li>
    <li><b>QUERY Params:</b> Não são obrigatórios, mas também são passados pela URL, separados através de "?" e "&". (https://[host]?[paramName1]=[paramValue1]&[paramName2]=[paramVAlue2])  </li>
</ul>
  
Há também parâmetros enviados no cabeçalho da requisição (<b>HEADER Params</b>). Esses são <b>pares chave valor</b> e não podem ser enviados por requisições no browser tradicionalmente, sendo necessário um cliente de requisições como o Insomnia ou o Postman.  
  
Por fim, há os <b>BODY Params</b> que são enviados no corpo da requisição, também seguindo o padrão <b>chave valor</b>, através de clientes específicos de requisições HTTP.  
  
<h2 id="status-code">STATUS Codes</h2>
São separados entre:
<ul>
    <li><b>1XX</b>: São os <b>Informacionais</b>, retornam informações sobre a operação atual da requisição. Não são muito utilizados em APIs profissionais.</li>
        <ul>
            <li>100 - Continue</li>
            <li>101 - Switching Protocols</li>
            <li>102 - Processing</li>
        </ul>
    <li><b>2XX</b>: São os de <b>Sucesso</b>, que retornam informações mais específicas sobre o resultado da requisição de acordo com o contexto de solicitação.</li>
        <ul>
            <li>200 - OK (Requisição executada com sucesso)</li>
            <li>201 - Created (Similar ao *200*, mas quer dizer que a requisição foi inserida em uma fila e será processada assim que possível)</li>
            <li>202 - Accepted</li>
            <li>203 - Non-authoritative Information</li>
            <li>204 - No content (Quando um registro é deletado, por exemplo, não é necessário uma resposta com conteúdo, apenas uma confirmação)</li>
            <li>205 - Reset Content</li>
            <li>206 - Partial Content</li>
            <li>207 - Multi-Status</li>
            <li>208 - Already Reported</li>
            <li>226 - IM Used</li>
        </ul>
    <li><b>3XX</b>: São os de <b>Redirecionamento</b>. Não são particularmente muito utilizados nos sites.</li>
        <ul>
            <li>300 - Multiple choices</li>
            <li>301 - Moved Permanently</li>
            <li>302 - Found</li>
            <li>303 - See other</li>
            <li>304 - Not Modified</li>
            <li>305 - Use Proxy</li>
            <li>307 - Temporary Redirect</li>
            <li>308 - Permanent Redirect</li>
        </ul>
    <li><b>4XX</b>: São os que indicam <b>Erro do cliente</b>. São muito utilizados e compõem uma lista muito grande, portanto, somente serão mencionados os mais utilizados.</li>
        <ul>
            <li>400 - Bad Request (Algum erro foi cometido pelo cliente ao estruturar a requisição. Seja na URL, no corpo, ou no cabeçalho)</li>
            <li>401 - Unauthorized (Acesso a algum endpoint não disponível para o nível de acesso atual do cliente)</li>
            <li>403 - Forbidden (Bastante similar ao 401)</li>
            <li>404 - Not Found (Nada foi encontrado no endpoint requisitado)</li>
            <li>408 - Request Timeout</li>
            <li>409 - Conflict (Tentaram criar um objeto que já existe.)</li>
            <li>415 - Unsupported Media Type</li>
            <li>418 - I'm a teapot (Para quando não há suporte para o que se tenta fazer)</li>
            <li>429 - Too Many Requests</li>
            <li>444 - Connection Closed Without Response</li>
            <li>499 - Client Closed Request</li>
        </ul>
    <li><b>5XX</b>: Representam os <b>Erros de Servidor</b>, também são bastante comuns, mas muito extensos.</li>
        <ul>
            <li>500 - Internal Server Error (Erros do servidor para os quais não há muitos detalhes. Pode ser o timeout de uma query no BD, por exemplo)</li>
            <li>501 - Not Implemented</li>
            <li>502 - Bad Gateway</li>
            <li>503 - Service Unavailable</li>
            <li>504 - Gateway Timeout</li>
            <li>511 - Network Authentication Required</li>
            <li>599 - Network Connect Timeout Error</li>
        </ul>
</ul>
  
<h2 id="http-verbs">Verbos/Métodos/Operações HTTP (REST)</h2>
<h3 id="most-used">Mais utilizados</h3>
São comandos utilizados para comunicar com o servidor, indicando quais são as operações realizadas. Eles são:
<ul>
    <li>
        <b>GET</b>: Fazendo paralelo com bancos de dados, é equivalente ao *SELECT*. Utilizado para ler ou recuperar uma representação de recursos, que vêm ao cliente através de arquivos XMLou JSON, além de um HTTP com código de sucesso ou falha.
        <ul>
            <li>Suporta parâmetros pela URL (PATH ou QUERY) e pelo HEADER, não tendo suporte para parãmetros enviados no BODY da requisição.</li>
        </ul>
    </li>
    <li>
        <b>POST</b>: Aplicado na criação de recursos, como a inserção de novos objetos no banco de dados. Pode retornar o conteúdo criado para o cliente na resposta de sucesso, com código 200 ou 201.
        <ul>
            <li>Suporta parâmetros pela URL (PATH ou QUERY), além do HEADER e do BODY (mais indicado)</li>
        </ul>
    </li>
    <li>
        <b>PUT</b>: Utilizado na atualização de informações, sem que o identificador seja alterado, por exemplo. Ou seja, se quer alterar o nome do objeto, mas manter o ID, o indicado é o PUT.Retorna códigos 200 ou 204 (quando não há conteúdo de resposta) após a operação.
        <ul>
            <li>Suporta parâmetros pela URL (PATH e QUERY), além do HEADER e do BODY (mais indicado).</li>
        </ul>
    </li>
    <li>
        <b>DELETE</b>: Utilizado para remover elementos do banco de dados. Geralemente não retorna nada no corpo de resposta, junto com o código 204. Mas pode também retornar uma mensagem personalizada.
        <ul>
            <li>Suporta parâmetros pela URL (PATH e QUERY, sendo o PATH o mais indicado), pelo HEADER e pelo BODY.  </li>
        </ul>
    </li>
</ul>
<h3 id="less-used">Menos utilizados</h3>
Além desses verbos, que são os mais conhecidos, existem outros que podem ser úteis na criação de APIs mais complexas. São eles:
<ul>
    <li>
        <b>PATCH</b>: Utilizado para updates parciais de recursos, a fim de economizar banda de internet, além de promover maior performance às requisições. Há porém um risco de colisão de múltiplos PATCHEs simultâneos, que podem desconfigurar a entrada no banco de dados.
        <ul>
            <li>Um <b>exemplo de utilização</b> seria um objeto que possui 100 campos, mas somente queremos atualizar um deles. Nesse caso, é mais econômico enviar apenas o ID do objeto juntamente com campo que se quer atualizar e o valor, o que torna a performance da requisição muito melhor.</li>
        </ul>
    </li>
    <li>
        <b>HEAD</b>: Bastante similar ao GET, mas retorna apenas uma linha de conteúdo e o HEADER da resposta, sem o BODY de conteúdo.
    </li>
    <li>
        <b>TRACE</b>: Recupera o conteúdo de uma requisiçaõ HTTP feita anteriormente. Geralmente é utilizado para *debug* durante o processo de desenvolvimento.
    </li>
    <li>
        <b>OPTIONS</b>: Retorna as operações HTTP disponibilizadas pela API utilizada. Na requisição, o cliente pode tanto informar a URL que se deseja verificar, quando um "*" para indicar todo o servidor.
    </li>
    <li>
        <b>CONNECT</b>: Estabelece uma conexão de rede com o servidor via HTTP, serve como teste de conexão, para saber se o servidor está funcionando.
    </li>
</ul>

<h2 id="maturity-levels">Níveis de Maturidade do REST</h2>
Leonard Richardson classificou os quatro níveis de maturidade da API, sendo que o REST é o inicial e RESTful é o mais avançado. A seguir temos as características de cada nível:
<ol>
    <li>
        <b>The Swap of POX</b>: POX significa <i>Plain Old Xml</i>. Nesse nível, as APIs simplesmente utilizam HTTP como o padrão de comunicação, de forma que isso é o suficiente para caracterizá-las como REST. Todas as requisições são feitas para um único <i>endpoint</i>.
    </li>
    <li>
        <b>Resources</b>: Há diferentes <i>endpoints</i> para diferentes tipos de operações realizadas pela API. Mas não há uma preocupação para utilizar os verbos corretos para as requisições, de forma que, no geral, só são utilizados os verbos <b>GET</b> e <b>POST</b>.
    </li>
    <li>
        <b>HTTP Verbs</b>: A API passa a utilizar os verbos HTTP corretos.
    </li>
    <li>
        <b>Hypermedia Controls</b>: Referente à sigla HATEOAS. A resposta da requisição, além de utilizar os códigos corretos, retorna também referências para outras operações que podem ser realizadas no objeto pela API.
    </li>
</ol>

<h2 id="hateoas">HATEOAS</h2>
Significa <i>Hypermedia As The Engine Of Application State</i> e é uma restrição arquitetural de aplicações REST. Ela permite navegar pelas outras funcionalidades da API a partir da resposta obtida da última requisição.  

<code>
[
    "id": 1,
    "nome": Pedro
    "links": [
        {
            "rel": "self",
            "href": "https://pedrinho.com/api/quem_eh/1",
            "type": "application/json",
            "action": "GET"
        },
        ...
    ]
]
</code>

A utilização do HATEOAS nas APIs facilita o desenvolvimento, na medida que fornece somente os passos possíveis, evitando retrabalho. Em um aplicativo de banco por exemplo, se estamos com saldo positivo e fazemos uma verificação de extrado, um HATEOAS de resposta possível nos indicaria operações de saque, depósito e transferência, por exemplo. Já se o saldo é negativo, não faz sentido dar ao usuário as opções de saque ou transferência, retornando uma referência apenas para depósito.

<h2 id=""></h2>