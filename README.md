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

Para rodar esse projeto o ideal é que se utilize outras máquina (pode ser máquina virtual).
1. Dai, compile e rode o código do Servidor (o código main se encontra na classe "Principal" na pasta Servidor), ele vai te fornecer as portas TCP e UDP que está rodando e o Ip que esse servidor ta rodando.
2. Em outra máquina, compile e execute o código do Cliente(o código main se encontra na classe "Principal" na pasta Cliente) e quando for pedido insira o Ip que foi mostrado quando executou o servidor
3. Repita o passo anterior para criar outros clientes.   

Pronto, agora a aplicação estará rodando e poderá fazer o uso dela. Mas perceba que com apenas um cliente e um servidor é possível testar o TCP por completo porém
acaba não sendo possível testar a troca de mensagens, para isso precisa de no mínimo 2 clientes.

obs: É possível testar com uma máquina só rodando duas instâncias e usando Ip de Loopback (127.0.0.1), mas pode resultar em alguns problemas e em
limitações, além de que não é possível testar completo (já que pra testar o UDP é preciso no mínimo dois clientes executando e um servidor)
