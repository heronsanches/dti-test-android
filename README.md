## Desafio dti - Desenvolvedor Android 
* [Arquivo com instruções do desafio disponível aqui](https://github.com/heronsanches/dti-test-api/blob/master/docs/DesafioAndroiddti.pdf)

### Ambiente de Desenvolvimento
* Android Studio 3.5
* Fedora 30

### Instruções de configuração
* Existe dois arquivos config.xml (app/src/debug/res/values/ e app/src/main/res/values/) os quais contêm informações
de configuração da aplicação. O que está sob **debug** é para fins de debug, qualquer outro build type utilizará o outro.
Esse arquivo tem duas configurações:
````
<resources>
    <string name="dti_api" translatable="false">http://localhost:8080/dti-api/</string>
    <bool name="uses_cleartext_traffic">true</bool>
</resources>
````
* dti_api: informa o endereço base para acessar os serviços web necessários para login e cadastro de usuário.
* uses_cleartext_traffic: se true, permite enviar dados abertos, ou seja, através de conexão HTTP.
