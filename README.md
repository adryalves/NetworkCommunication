# Comunicação com Sockets em Java
Esta aplicação implementa uma comunicação servidor e cliente utilizando Sockets em Java com GUI. Por meio do protocolo TCP o cliente se conecta 
ao servidor e consegue entrar e sair dos grupos, já por meio do Protocolo UDP os clientes conectados que estejam no mesmo grupo conseguem
trocar mensagem via chat uns com os outros dentro desse grupo.

## Funcionalidades 

- **Servidor**:
  - Gerencia a criação e remoção de grupos.
  - Gerencia a entrada e saída de clientes dos grupos.
  - Recebe mensagens de clientes e as retransmite para todos os membros do grupo (exceto quem enviou).


- **Cliente**:
  - Conecta-se ao servidor utilizando o endereço IP do servidor.
  - Entra e sai de grupos através de TCP.
  - Envia e recebe mensagens de outros clientes do mesmo grupo via UDP.

## Como executar

Para rodar esse projeto o ideal é que se utilize outras máquina (pode ser máquina virtual) para rodar tanto o servidor quanto o cliente máquinaas diferente, quanto diferentes clientes. Além disso, é preciso executar primeiro o servidor.

**Como Executar o Servidor**
1. Entre na pasta Servidor
   
2. Compile o projeto:
    ```sh
    javac Principal.java
    ```
3. Execute o projeto:
    ```sh
    java Principal
    ```
4. Será mostrado no Console as portas TCP e UDP que está rodando e o Ip que esse servidor ta rodando.

**Como executar o Cliente**
1. Entre na pasta Cliente
   
2. Compile o projeto:
    ```sh
    javac Principal.java
    ```
3. Execute o projeto:
    ```sh
    java Principal
    ```
4. A primeira tela vai pedir o Ip do servidor com o qual ele vai se conectar, logo insira Ip que foi mostrado quando executou o servidor
5. Repita os passos anteriores para criar outros clientes.   

Pronto, agora a aplicação estará rodando e poderá fazer o uso dela. Mas perceba que com apenas um cliente e um servidor é possível testar o TCP por completo porém
acaba não sendo possível testar a troca de mensagens, para isso precisa de no mínimo 2 clientes. Caso queira executar no VSCode, ao invés do 2° e 3° passo apenas ir na classe
Principal e executar.

obs: É possível testar com uma máquina só rodando duas instâncias e usando Ip de Loopback (127.0.0.1), mas pode resultar em alguns problemas e em
limitações, além de que não é possível testar completo (já que pra testar o UDP é preciso no mínimo dois clientes executando e um servidor)


![image](https://github.com/user-attachments/assets/1835021d-4df6-4d8f-b95a-09606f8e82b2) ![image](https://github.com/user-attachments/assets/9dda7d01-920b-4af8-9718-5a71d7ab6042)
![image](https://github.com/user-attachments/assets/117eee84-2e25-443f-a6c2-506a5d15ab86) ![image](https://github.com/user-attachments/assets/73a2b05e-e40a-487c-a5c1-64c4764459ea)




