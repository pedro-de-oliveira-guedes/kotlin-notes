# Tecnologia Spring Boot
Esse arquivo contem informações importantes sobre o que é o Spring Boot, como funciona e suas aplicações.  

<h2>Índice</h2>
<ul>
    <li><a href="#what-is">O que é Spring Boot</a></li>
    <li><a href="#how-to">Como criar um projeto com Spring Boot</a></li>
    <li>
        <a href="#how-does-it-works">Como funciona o Spring Boot</a>
        <ul>
            <li>
                <a href="#maven">Maven</a>
            </li>
            <li>
                <a href="#folders-structure">Estrutura de pastas</a>
            </li>
            <li>
                <a href="#endpoints">Criando endpoints</a>
            </li>
        </ul>
    </li>
</ul>

<h2 id="what-is">O que é Spring Boot</h2>
SPRING é uma ferramenta de gestão de dependências, similar ao NPM (Node Package Manager), já o SPRING BOOT saiu da necessidade de facilitar os projetos que utilizam o SPRING para serem desenvolvidos. Ele visa:
<ul>
    <li>
        Padronizar o runtime environment para o Spring
    </li>
    <li>
        <b>Definir uma convenção sobre configurações</b>: Antes de tecnologias como essa, existiam diversos arquivos de configuração sobre diversos pacotes e dependências. Com os padrões de projeto do Spring Boot, existe praticamente um único arquivo XML que deve ser editado com as informações, dando também a possibilidade de sobrescrever algumas convenções.
    </li>
    <li>
        <b>Simplifica o gerenciamento de dependências</b>: Resolve conflitos de versão e compatibilidade mais facilmente, sem trabalho para o desenvolvedor.
    </li>
    <li>
        <b>Facilita o teste e deploy</b>: O JAR (executável) das aplicações que utilizam a tecnologia, entregando o JAR embarcado com o TomCat (serviço para ajudar a interpretar as requisições HTTP para JAVA e linguagens derivadas).
    </li>
    <li>
        <b>Monitoração de informações importantes</b>: Juntamente com o TomCat, o Spring Boot, por padrão, já entrega um <i>endpoint</i> para verificação de health metrics do servidor, além de outras informações como as variáveis de ambiente setadas.
    </li>
</ul>

<h2 id="how-to">Como utilizar Spring Boot</h2>
Em ferramentas pagas, como o IntelliJ Premium, há uma integração nativa com um inicializador de projetos Spring Boot. Já para versões gratuitas, é necessário criar o projeto pelo site (link abaixo) e importá-lo no editor de código de preferência.
<blockquote>
    <a href="https://start.spring.io/">start.spring.io</a>
</blockquote>
Para criar o projeto, atente-se ao passo a passo a seguir:
<ol>
    <li>
        Acesse o site acima.
    </li>
    <li>
        Selecionar o gerenciador de projeto utilizado, é ele que realiza a build do código, além de tratar de outros aspectos organizacionais diferentes. Recomenda-se o <i>Maven</i>.
    </li>
    <li>
        Definir qual a linguagem utilizada no projeto. Nesse caso, Kotlin.
    </li>
    <li>
        Escolher a versão do Spring Boot utilizada. Nesse momento (01/2023), a mais recente e estável é a 3.0.2.
    </li>
    <li>
        Definir o Artefato do projeto, que é basicamente o nome da pasta em que ele está definido.
    </li>
    <li>
        Definir o nome do projeto, geralmente é o mesmo do artefato.
    </li>
    <li>
        Definir o ID do grupo. Isso é basicamente um campo de identificação de domínios dos seus projetos, ele é escrito em domínio reverso, ou seja <b>google.com</b> viraria <b>com.google</b>, por exemplo.
    </li>
    <li>
        Dar uma descrição ao projeto (Opcional).
    </li>
    <li>
        Modificar o nome do pacote (Opcional).
    </li>
    <li>
        Escolher a versão do Java utilizada.
    </li>
    <li>
        Adicionar as dependências a serem utilizadas no projeto. Para projetos WEB, as mais comuns são:
        <ul>
            <li>
                <b>Spring Web</b>: Permite construir apps web com APIs REST.
            </li>
            <li>
                <b>Spring Boot DevTools</b>: Disponibiliza Live Reload, facilitando o teste da aplicação.
            </li>
        </ul>
    </li>
        Gerar o projeto e salvá-lo em um diretório memorável.
    </li>
    <li>
        Extrair o projeto gerado para o repositório de trabalho.
    </li>
    <li>
        Importar o projeto no editor de código de preferência.
    </li>
</ol>

<h2 id="how-does-it-works">Como funciona o Spring Boot</h2>
<h3 id="maven">Maven</h3>
O Spring Boot utiliza o gerenciador de dependências <i>Maven</i>, que busca facilitar a obtenção e organização dos módulos de dependência. Ele mapeia e armazena informações sobre todas as dependências em um arquivo chamado <i>pom.xml</i>(Program Object Model). A partir dele, é possível, além de organizar melhor os pacotes utilizados pelo código, garantir que todos possam ser instalados corretamente de onde quer que seja, sem a necessidade de compartilar todos os arquivos com seus respectivos <i>jar's</i>, o que reduz o peso do repositório.  
<br><br>
O Maven armazena as dependências já utilizadas no caminho <i>/home/[seu-usuario]/.m2/repository</i>, evitando a necessidade de realizar download de pacotes já disponíveis, por exemplo.
<br><br>
<h3 id="folders-structure">Estrutura de pastas</h3>
O projeto gerado pelo Spring Boot possui algumas pastas importantes para sabermos a utilidade. São elas:
<ul>
    <li>
        <b>/.idea</b>: ???
    </li>
    <li>
        <b>/.mvn</b>: ???
    </li>
    <li>
        <b>/src</b>: Arquivos fonte para a geração do aplicativo, além de utilitários diretamente relacionados à programação desenvolvida.
        <ul>
            <li>
                <b>/src/main</b>: Contem o código fonte da aplicação desenvolvida
                <ul>
                    <li>
                        <b>/src/main/kotlin/[ID do Grupo]</b>: O ID do grupo depende do que foi definido no processo de criação do projeto. Dentro dessa pasta está o arquivo de código fonte principal da aplicação.
                    </li>
                    <li>
                        <b>/src/resources</b>: Recursos estáticos utilizados para o desenvolvimento da aplicação. Por padrão, abriga o arquivo <i>application.properties</i>.
                    </li>
                </ul>
            </li>
            <li>
                <b>src/test</b>: Testes desenvolvidos para a aplicação.
                <ul>
                    <li>
                        <b>/test/kotlin/[ID do Grupo]</b>: Arquivo fonte dos testes escritos para a aplicação.
                    </li>
                    <li>
                        <b>/test/resources</b>: Recursos estáticos utilizados nos testes escritos.
                    </li>
                </ul>
            </li>
            <li>
                <b>/src/pom.xml</b>: Configurações do projeto. Para mais informações, acesse o link: <a href="https://www.erudio.com.br/blog/entendendo-o-pom-do-maven/">Entendendo o POM do Maven.</a>
            </li>
        </ul>
    </li>
    <li>
        <b>External Libraries</b>: ???
    </li>
    <li>
        <b>Scratches and Consoles</b>: ???
    </li>
    <li>
        <b>Target</b>: Contem o programa já compilado e pronto para ser executado. Não existe inicialmente, só após a primeira compilação.
    </li>
</ul>
<br>
<h3 id="endpoints">Criando endpoints</h3>
Nessa seção, há um passo a passo básico sobre como configurar um <i>endpoint</i> para a aplicação desenvolvida. Antes há alguns conceitos importantes para entendimento:
<ul>
    <li>
        <b>Annotations</b>: Palavras reservadas do Spring Boot que realizam tarefas de import e configuração na aplicação. Elas sempre são precedidas por um "<i>@</i>". A primeira delas é "<b><i>@SpringBootApplication</i></b>, que define a aplicação atual como sendo configurada pelo Spring Boot. Essa anotação realiza diversos imports em arquivos paralelos que configuram o ambiente de desenvolvimento.
    </li>
    <li>
        <b>Spring Controllers</b>: Controla a comunicação da API com o mundo exterior, definindo a forma de resposta como arquivos crus, sem visualização. Para incluir um, é utilizada a anotação "<b><i>@RestController</i></b>", que é uma união das anotações "<b><i>@Controller</i></b>" e "<b><i>@ResponseBody</i></b>" de versões anteriores.
    </li>
    <li>
        <b>Request Mapping</b>: É a forma de definir os <i>endpoints</i> utilizados na aplicação. Quando ela estiver rodando e uma requisição na WEB for feita para o endereço especificado na anotação de mapeamento, o bloco de código abaixo será executado. Para utilizar este recurso, se usa a notação "<b><i>@RequestMapping("/[endpoint]")</i></b>" imediatamente acima de uma função.
    </li>
    <li>
        <b>Request Parameters</b>: É a forma de definir os parâmetros que podem ser passados pela URL, tanto os de PATH quanto os de QUERY, pelo menos para requisições do tipo GET. Para utilizá-los, você deve primeiro definir um Request Mapping para uma função. A partir disso, a função que recebeu o mapeamento do endpoint pode também receber parâmetros. Esses parâmetros são os mesmos que devem ser contidos na URL. Eles se parecem com isso:
        <br>
        <code>
            @RequestParam(value="[Nome do parâmetro na URL]", defaultValue = "[Somente utilizado para QUERY params, é o valor que assume a variável quando nenhum parâmetro é passado]") [Nome da variável associada]: [Tipo da variável/parâmetro][Se for uma variável dispensável, como para QUERY PARAMS, é adicionado uma <b>?</b> aqui]
        </code>
        <br>
        <blockquote>
            <b>Observação:</b> Para utilizar a variável associada ao parâmetro em uma string é utilizada a notação nativa do Kotlin de acesso a variáveis, que consiste em posicionar um <b>$</b> antes do nome da variável a ser utilizada.
        </blockquote>
    </li>
</ul>
<br>
Após o entendimento de alguns conceitos, podemos definir um passo a passo de como criar uma rota básica de GET para a aplicação.
<ol>
    <li>
        Gere o projeto Spring Boot e o importe no editor de código de escolha.
    </li>
    <li>
        Crie um arquivo de classe do Kotlin dentro da pasta que contem o arquivo principal da aplicação (/src/main/kotlin/[ID do seu grupo]).
    </li>
    <li>
        Defina um construtor e modele a classe da forma que preferir para a utilização.
    </li>
    <li>
        Crie uma classe para o Controlador.
    </li>
    <li>
        Antes da declaração da classe, inclua a notação "<i>@RestController</i>".
    </li>
    <li>
        Modele a classe, de forma que pelo menos uma função seja incluída.
    </li>
    <li>
        Inclua um mapeamento de endpoint ("<i>@RequestMapping (/[nome do endpoint])</i>") para a função escolhida, exatamente acima dela. Lembre-se de que essa função precisa retornar algo.
    </li>
    <li>
        Nos parâmetros da função escolhida, inclua os parâmetros que a rota admite, seguindo as recomendações acima.
    </li>
    <li>
        Se estiver usando o IntelliJ, basta clicar com o botão direito sobre o arquivo principal localizado em "<i>/src/main/kotlin/[ID do grupo]</i>" e selecionar a opção de rodar a aplicação.
    </li>
    <li>
        Por padrão, o TomCat utiliza a porta 8080, portanto basta acessar "<i>localhost:8080/[sua rota aqui]</i>" para testar e conferir os resultados.
    </li>
</ol>