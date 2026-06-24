# Calculadora RMI Distribuída

Um sistema distribuído em Java utilizando a arquitetura RMI (*Remote Method Invocation*). O projeto é um serviço de cálculos processados remotamente, acessados por uma interface gráfica inspirada no iOS. Desenvolvido para a disciplina de Sistemas Computacionais Distribuídos (IFSULDEMINAS - Campus Machado).

### Funcionalidades

* **Operações Matemáticas:** Soma, subtração, multiplicação, divisão, porcentagem, módulo (%), raiz e potência.
* **Operações Específicas:** Fatorial (n!) e conversão de bases numéricas (Decimal para Binário e Hexadecimal).
* **Interface Estilo iOS:** UI em Java Swing reproduzindo o layout e o comportamento (Dark Mode) da calculadora do iPhone.
* **Processamento Distribuído:** O cliente atua apenas como interface visual (Thin Client). A lógica e os logs são processados no servidor.
* **Tratamento de Exceções:** Proteção contra divisão por zero, exibindo "Erro" de forma amigável no visor.

### Tecnologias Utilizadas

* **Linguagem:** Java (Java SE)
* **Arquitetura:** Java RMI 
* **Interface Gráfica:** Java Swing / AWT

### Como Executar

1. Clone o repositório ou baixe os arquivos mantendo a estrutura de pacotes (`br/edu/ifsuldeminas/mch/sd/rmi/...`).
2. Abra o projeto na sua IDE ou prepare a compilação via terminal.
3. Inicie **primeiro o servidor**, executando a classe principal do pacote `server` (porta 1099):
   ```bash
   java br.edu.ifsuldeminas.mch.sd.rmi.server.Server
   ```
4. Com o servidor rodando, abra um novo terminal e execute a classe do cliente:
   ```bash
   java br.edu.ifsuldeminas.mch.sd.rmi.client.GuiClient
   ```
